package loggingframework.appender;

import loggingframework.core.LogMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class AsyncAppender implements Appender{
    private final LinkedBlockingQueue<LogMessage> queue;
    private final Appender appender;
    private final Thread workerThread;
    private volatile boolean running = true;

    private static final int BATCH_SIZE = 50;
    public AsyncAppender(Appender appender,int capacity) {
        this.queue = new LinkedBlockingQueue<>(capacity);
        this.appender = appender;
        this.workerThread = new Thread(this::processLogs, "AsyncLogger-worker");
        workerThread.start();
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdowm));
    }


    //append
    @Override
    public void append(LogMessage logMessage) {
        //if q capacity full, so blocking q will wait/block it until q doesnt have space.
        try {
            queue.put(logMessage);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    //process
    private void processLogs() {
       while (running || !queue.isEmpty()){
           try {
               List<LogMessage> batch = new ArrayList<>();
               queue.drainTo(batch,BATCH_SIZE);
                //q.take() also blocking op, it ill until u get some meessage thus avoiding re reunning processlogs
               if(batch.isEmpty()){
                   batch.add(queue.take());
               }

               for(LogMessage message: batch){
                   appender.append(message);
               }
           } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
           }
       }
    }

    //shutdown

    private void shutdowm() {
        this.running = false;
        workerThread.interrupt();
    }

}
