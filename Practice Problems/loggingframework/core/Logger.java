package loggingframework.core;

import loggingframework.appender.Appender;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Logger {
    private final String loggerName;
    private volatile LogLevel logLevel;
    private final List<Appender>appenders;

    public Logger(String loggerName,LogLevel logLevel) {
        this.logLevel = logLevel;
        this.loggerName = loggerName;
        //if a thread A is doing any operation on appenders. and thread B tries to append a new appender,so it will cause
        // concurrent modification exception, so to prevent it we are using CopyOnWriteArrayList<>(), it create a new list,
        // add appender to it and then replace the old list with new list, so it will not cause concurrent modification exception
        this.appenders = new CopyOnWriteArrayList<>();//thread safe //only when writing<reading
    }

    public void setLogLevel(LogLevel level){
        this.logLevel =   level;
    }

    public void addAppender(Appender appender){
        this.appenders.add(appender);
    }

    public void log(LogLevel level, String message){
        if(level.getLevel()<this.logLevel.getLevel()){
            return;
        }

        LogMessage log = new LogMessage(message,level, loggerName);
        appenders.forEach(appender -> appender.append(log));
    }

    public void debug( String message){
        log(logLevel.DEBUG,message);
    }

    public void info( String message){
        log(logLevel.INFO,message);
    }

    public void warn( String message){
        log(logLevel.WARNING,message);
    }

    public void fatal( String message){
        log(logLevel.FATAL,message);
    }


}
