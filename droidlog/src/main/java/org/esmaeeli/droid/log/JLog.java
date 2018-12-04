package org.esmaeeli.droid.log;

/**
 * A wrapper for {@link ILog} with a singleton-like API for logging that provides a default logger
 * to be used across the whole application.
 *
 * @author Javid Esmaeeli
 */
public class JLog {

    private static ILog defaultLog = null;

    public static ILog get() {
        if (defaultLog == null) {
            synchronized (JLog.class) {
                if (defaultLog == null) {
                    defaultLog = new LogCat(LogLevel.DISABLE);
                }
            }
        }
        return defaultLog;
    }

    public static void setDefault(ILog log) {
        synchronized (JLog.class) {
            defaultLog = log;
        }
    }

}
