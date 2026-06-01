package loggingframework.demo;

import loggingframework.Formatter.DefaultFormatter;
import loggingframework.Formatter.Formatter;
import loggingframework.appender.AsyncAppender;
import loggingframework.appender.ConsoleAppender;
import loggingframework.appender.FileAppender;
import loggingframework.core.LogLevel;
import loggingframework.core.Logger;
import loggingframework.core.LoggerManager;

public class AsyncDemo {

    public static void main(String[] args) throws InterruptedException {
        Logger logger = LoggerManager.getInstance().getLogger(AsyncDemo.class.getName());


        logger.setLogLevel(LogLevel.INFO);
        Formatter formatter = new DefaultFormatter();

        FileAppender fileAppender = new FileAppender("async-demo.log",formatter);
        ConsoleAppender consoleAppender = new ConsoleAppender(formatter);

        AsyncAppender appender = new AsyncAppender(fileAppender,1000);
        logger.addAppender(appender);
        logger.addAppender(consoleAppender);


        System.out.println("Start async logging");

        Runnable task = () -> {
            for (int i = 0; i < 10; i++) {
                logger.info("Async log message " + i);
                try {
                    Thread.sleep(200); // Simulate some delay
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Thread t1 = new Thread(task, "Worker1");
        Thread t2 = new Thread(task, "Worker2");

        long start = System.currentTimeMillis();

        t1.start();
        t2.start();

            try {
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }


        long end = System.currentTimeMillis();
        System.out.println("Main thread finished in " + (end - start) + " ms");

        Thread.sleep(2000);







    }
}
