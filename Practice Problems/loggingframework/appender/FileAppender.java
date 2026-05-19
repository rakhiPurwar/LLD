package loggingframework.appender;

import loggingframework.Formatter.Formatter;
import loggingframework.core.LogMessage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

// implements Appender so the framework can call append() without knowing where logs go.
// You can swap FileAppender with ConsoleAppender without changing any other code. (Open/Closed Principle)
public class FileAppender implements Appender {

    // Knows how to convert a LogMessage into a formatted string e.g. "[INFO] 2026-04-25 - message"
    // Formatter decides the format, FileAppender decides where to write. Separate responsibilities.
    private final Formatter formatter;

    // Actual connection to the file on disk.
    // Buffered = writes are held in memory and sent to disk in chunks (faster than char by char).
    public final BufferedWriter writer;

    // Private mutex object. Only one thread can hold this lock at a time.
    // Private so external code cannot accidentally lock on it and cause deadlocks.
    // Both append() and shutDown() use the SAME lock so they can never run at the same time.
    private final Object lock = new Object();


    public FileAppender(Formatter formatter, String filePath) {
        this.formatter = formatter;
        try {
            // Opens (or creates) the file at filePath for writing.
            // Paths.get(filePath) converts the string path into a Path object.
            // If file exists it is overwritten (truncated) by default.
            this.writer = Files.newBufferedWriter(Paths.get(filePath));
        } catch (IOException e) {
            // IOException is a checked exception — every caller would have to handle it.
            // If the file can't be opened, the appender is useless — no point continuing.
            // Wrapping in RuntimeException signals: this is unrecoverable, fail fast.
            throw new RuntimeException("Failed to initialize FileAppender", e);
        }

        // Registers a callback that JVM calls automatically when program exits (Ctrl+C, System.exit(), etc.)
        // JVM shutdown sequence:
        //   1. Shutdown hooks start running (in parallel with normal threads)
        //   2. JVM waits for ALL hooks to finish
        //   3. Only then JVM exits — memory freed, threads killed
        // Without this hook: BufferedWriter data sitting in memory is LOST on abrupt exit.
        // this::shutDown is a method reference, equivalent to () -> this.shutDown()
        Runtime.getRuntime()
                .addShutdownHook(new Thread(this::shutDown));
    }

    @Override
    public void append(LogMessage logMessage) {
        // synchronized(lock) means only ONE thread can be inside this block at a time.
        // Without this: two threads writing simultaneously → garbled/mixed log lines.
        // Uses explicit lock object (not synchronized on method) so external code
        // cannot interfere by locking on 'this' from outside the class.
        synchronized (lock) {
            try {
                // formatter.format() converts LogMessage → readable string
                writer.write(formatter.format(logMessage));

                // adds \n (or \r\n on Windows) so each log is on its own line
                writer.newLine();

                // forces data from memory buffer → disk immediately.
                // Without flush(): logs stay in RAM buffer and may never reach the file
                // if the program crashes before the buffer fills up.
                // flush() has nothing to do with memory release — that is handled by GC separately.
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void shutDown() {
        // Uses the SAME lock as append() — critical for correctness.
        // Scenario without lock:
        //   Thread A: inside append(), mid-write
        //   Thread B (shutdown hook): calls shutDown(), closes the file
        //   Thread A: tries to write to a closed file → CRASH / data corruption
        //
        // With the same lock:
        //   Thread B cannot enter shutDown() until Thread A releases the lock from append().
        //   They are guaranteed to never overlap.
        synchronized (lock){
            try{
                // Flush explicitly before close — defensive coding.
                // close() calls flush() internally too, but being explicit guarantees
                // no data is lost even if close() behaves unexpectedly.
                writer.flush();

                // Releases the file handle (OS resource). Not related to memory release.
                // Memory (Java objects) is released by GC, not by close().
                writer.close();
            }catch (IOException e){
                throw new RuntimeException("Failed to close file appender", e);
            }
        }
    }

}

/*
 FLOW SUMMARY:

 LogMessage object
       ↓
 FileAppender.append()
       ↓
 formatter.format()  →  "2026-04-25 [INFO] User logged in"
       ↓
 BufferedWriter.write()  →  [ Memory Buffer in RAM ]
       ↓  flush()
 OS File Cache
       ↓  OS decides
 Actual file on disk

 If flush() is skipped and program crashes → data in memory buffer is LOST (not a memory issue, a data loss issue)
 If close() is skipped → file handle leak (OS resource), data may be incomplete
 If lock is skipped → two threads can write/close simultaneously → crash or corruption
*/