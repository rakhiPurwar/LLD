package loggingframework.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoggerManager {
    //singleton
    private static final LoggerManager INSTANCE = new LoggerManager();
    private final Map<String,Logger> loggers ;

    public LoggerManager() {
        this.loggers = new ConcurrentHashMap<>();//multiple threads can access it concurrently without issues
         //bucket level locking
    }

    private static LoggerManager getInstance(){
        return INSTANCE;
    }


    public Logger getLogger(String name){
        return loggers.computeIfAbsent(name, key-> new Logger(key, LogLevel.INFO));
    }
}

//LoggerManager is a Singleton registry/cache that gives one Logger object per logger name.
//Manager gives logger.
//Map remembers logger.
//computeIfAbsent creates only if missing.

//Application asks LoggerManager for Logger
//LoggerManager returns existing/new Logger
//Logger does filtering and dispatching
//Appender writes output
