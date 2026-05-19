package loggingframework.core;

import java.time.LocalDateTime;

public class LogMessage {

    private final String message;
    private final LogLevel logLevel;
    private final LocalDateTime timestamp;
    private final String loggerName;
    private final String thread;

    public LogMessage(String message, LogLevel logLevel, String loggerName) {
        this.message = message;
        this.logLevel = logLevel;
        this.timestamp = LocalDateTime.now();
        this.loggerName = loggerName;
        this.thread = Thread.currentThread().getName();
    }

    public String getMessage() {
        return message;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public String getThread() {
        return thread;
    }
}
