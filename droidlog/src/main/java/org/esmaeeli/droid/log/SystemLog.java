package org.esmaeeli.droid.log;

/**
 * Logs all messages with System.out or System.err
 * This also can be used in off-device testing like {@link NoLog}
 *
 * @author Javid Esmaeeli
 */
public class SystemLog implements ILog {

    private LogLevel level;

    public SystemLog(LogLevel level) {
        this.level = level;
    }

    @Override
    public void setLogLevel(LogLevel level) {
        this.level = level;
    }

    @Override
    public void e(String message) {
        if (level.ordinal() < LogLevel.ERROR.ordinal()) {
            return;
        }
        System.err.println("E: " + message);
    }

    @Override
    public void w(String message) {
        if (level.ordinal() < LogLevel.WARNING.ordinal()) {
            return;
        }
        System.out.println("W: " + message);
    }

    @Override
    public void i(String message) {
        if (level.ordinal() < LogLevel.INFO.ordinal()) {
            return;
        }
        System.out.println("I: " + message);
    }

    @Override
    public void d(String message) {
        if (level.ordinal() < LogLevel.DEBUG.ordinal()) {
            return;
        }
        System.out.println("D: " + message);
    }

    @Override
    public void v(String message) {
        if (level.ordinal() < LogLevel.VERBOSE.ordinal()) {
            return;
        }
        System.out.println("V: " + message);
    }

    @Override
    public void wtf(String message) {
        if (level.ordinal() < LogLevel.ERROR.ordinal()) {
            return;
        }
        System.err.println("F: " + message);
    }

    @Override
    public void e(Throwable throwable) {
        if (level.ordinal() < LogLevel.ERROR.ordinal()) {
            return;
        }
        System.err.println("E: " + throwable.getMessage());
    }

    @Override
    public void w(Throwable throwable) {
        if (level.ordinal() < LogLevel.WARNING.ordinal()) {
            return;
        }
        System.out.println("W: " + throwable.getMessage());
    }

    @Override
    public void i(Throwable throwable) {
        if (level.ordinal() < LogLevel.INFO.ordinal()) {
            return;
        }
        System.out.println("I: " + throwable.getMessage());
    }

    @Override
    public void d(Throwable throwable) {
        if (level.ordinal() < LogLevel.DEBUG.ordinal()) {
            return;
        }
        System.out.println("D: " + throwable.getMessage());
    }

    @Override
    public void v(Throwable throwable) {
        if (level.ordinal() < LogLevel.VERBOSE.ordinal()) {
            return;
        }
        System.out.println("V: " + throwable.getMessage());
    }

    @Override
    public void wtf(Throwable throwable) {
        if (level.ordinal() < LogLevel.ERROR.ordinal()) {
            return;
        }
        System.err.println("F: " + throwable.getMessage());
    }
}
