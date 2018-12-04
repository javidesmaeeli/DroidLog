package org.esmaeeli.droid.log;

import android.util.Log;

/**
 * Default implementation for ILog based on android logcat with proper formatting.
 * The formatting will display a hyper-link in log-cat and provides easy access to the code where
 * the message has been logged.
 *
 * @author Javid Esmaeeli
 */
public class LogCat implements ILog {

    private static final String DIVIDER = "    ->  ";

    private LogLevel logLevel;

    public LogCat(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    @Override
    public void setLogLevel(LogLevel level) {
        this.logLevel = level;
    }

    //region String Log
    @Override
    public void e(String message) {
        if (logLevel.ordinal() < LogLevel.ERROR.ordinal()) {
            return;
        }
        StackTraceElement element = getCallingLine();
        Log.e(getBareClassName(element), message + toString(element));
    }

    @Override
    public void w(String message) {
        if (logLevel.ordinal() < LogLevel.WARNING.ordinal()) {
            return;
        }
        StackTraceElement element = getCallingLine();
        Log.w(getBareClassName(element), message + toString(element));
    }

    @Override
    public void i(String message) {
        if (logLevel.ordinal() < LogLevel.INFO.ordinal()) {
            return;
        }
        StackTraceElement element = getCallingLine();
        Log.i(getBareClassName(element), message + toString(element));
    }

    @Override
    public void d(String message) {
        if (logLevel.ordinal() < LogLevel.DEBUG.ordinal()) {
            return;
        }
        StackTraceElement element = getCallingLine();
        Log.d(getBareClassName(element), message + toString(element));
    }

    @Override
    public void v(String message) {
        if (logLevel.ordinal() < LogLevel.VERBOSE.ordinal()) {
            return;
        }
        StackTraceElement element = getCallingLine();
        Log.v(getBareClassName(element), message + toString(element));
    }

    @Override
    public void wtf(String message) {
        if (logLevel.ordinal() < LogLevel.ERROR.ordinal()) {
            return;
        }
        StackTraceElement element = getCallingLine();
        Log.wtf(getBareClassName(element), message + toString(element));
    }
    //endregion

    //region Throwable Log
    @Override
    public void e(Throwable throwable) {
        if (logLevel.ordinal() < LogLevel.ERROR.ordinal()) {
            return;
        }
        StackTraceElement element = getCallingLine();
        Log.e(getBareClassName(element), toString(element), throwable);
    }

    @Override
    public void w(Throwable throwable) {
        if (logLevel.ordinal() < LogLevel.WARNING.ordinal()) {
            return;
        }
        StackTraceElement element = getCallingLine();
        Log.w(getBareClassName(element), toString(element), throwable);
    }

    @Override
    public void i(Throwable throwable) {
        if (logLevel.ordinal() < LogLevel.INFO.ordinal()) {
            return;
        }
        StackTraceElement element = getCallingLine();
        Log.i(getBareClassName(element), toString(element), throwable);
    }

    @Override
    public void d(Throwable throwable) {
        if (logLevel.ordinal() < LogLevel.DEBUG.ordinal()) {
            return;
        }
        StackTraceElement element = getCallingLine();
        Log.d(getBareClassName(element), toString(element), throwable);
    }

    @Override
    public void v(Throwable throwable) {
        if (logLevel.ordinal() < LogLevel.VERBOSE.ordinal()) {
            return;
        }
        StackTraceElement element = getCallingLine();
        Log.v(getBareClassName(element), toString(element), throwable);
    }

    @Override
    public void wtf(Throwable throwable) {
        if (logLevel.ordinal() < LogLevel.ERROR.ordinal()) {
            return;
        }
        StackTraceElement element = getCallingLine();
        Log.wtf(getBareClassName(element), toString(element), throwable);
    }
    //endregion

    protected int getStackTraceIndexOfCaller() {
        return 4;
    }

    private StackTraceElement getCallingLine() {
        return Thread.currentThread().getStackTrace()[getStackTraceIndexOfCaller()];
    }

    private String getBareClassName(StackTraceElement element) {
        int start = element.getClassName().lastIndexOf(".") + 1;
        int end = element.getClassName().indexOf("$");
        if (end == -1) {
            return element.getClassName().substring(start);
        }
        return element.getClassName().substring(start, end);
    }

    private String toString(StackTraceElement e) {
        return DIVIDER + e.getClassName().substring(e.getClassName().lastIndexOf(".") + 1) + "." + e.getMethodName() + "(" + e.getFileName() + ":" + e.getLineNumber() + ")";
    }
}
