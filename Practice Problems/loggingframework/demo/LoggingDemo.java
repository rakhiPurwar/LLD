package loggingframework.demo;

import loggingframework.Formatter.DefaultFormatter;
import loggingframework.appender.AsyncAppender;
import loggingframework.appender.ConsoleAppender;
import loggingframework.appender.FileAppender;
import loggingframework.core.LogLevel;
import loggingframework.core.Logger;
import loggingframework.core.LoggerManager;

public class LoggingDemo {
    public static void main(String[] args) {

        Logger logger = LoggerManager.getInstance().getLogger(LoggingDemo.class.getName());


        logger.addAppender(new ConsoleAppender(new DefaultFormatter()));
        FileAppender fileAppender = new FileAppender("async-demo.log",(new DefaultFormatter()));
        logger.addAppender(fileAppender);
        logger.setLogLevel(LogLevel.FATAL);


        logger.warn("This is an error message");
        //imp
        logger.fatal("This is an error message");

        //another imp
        logger.setLogLevel(LogLevel.INFO);
        logger.setLogLevel(LogLevel.WARNING);

        logger.warn("This is an error message");

    }
}
