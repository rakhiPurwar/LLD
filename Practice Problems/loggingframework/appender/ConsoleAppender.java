package loggingframework.appender;

import loggingframework.Formatter.Formatter;
import loggingframework.core.LogMessage;

public class ConsoleAppender implements Appender{
    private final Formatter formatter;

    public ConsoleAppender(Formatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void append(LogMessage logMessage) {
        System.out.println(formatter.format(logMessage));
    }
}
