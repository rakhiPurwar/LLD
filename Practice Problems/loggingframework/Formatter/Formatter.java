package loggingframework.Formatter;

import loggingframework.core.LogMessage;

public interface Formatter {
    String format(LogMessage logMessage);
}
