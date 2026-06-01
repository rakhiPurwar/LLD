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

    public static LoggerManager getInstance(){
        return INSTANCE;
    }


    public Logger getLogger(String name){
        return loggers.computeIfAbsent(name, key-> new Logger(key, LogLevel.INFO));
    }
}
