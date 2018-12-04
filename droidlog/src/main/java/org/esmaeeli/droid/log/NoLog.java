package org.esmaeeli.droid.log;

/**
 * This class does not log anything at all. It's most useful for off-device testing where you can
 * pass and instance of NoLog to {@link JLog#setDefault(ILog)} before running your tests.
 *
 * @author Javid Esmaeeli
 */
public class NoLog implements ILog {

    @Override
    public void setLogLevel(LogLevel level) {

    }

    @Override
    public void e(String message) {

    }

    @Override
    public void w(String message) {

    }

    @Override
    public void i(String message) {

    }

    @Override
    public void d(String message) {

    }

    @Override
    public void v(String message) {

    }

    @Override
    public void wtf(String message) {

    }

    @Override
    public void e(Throwable throwable) {

    }

    @Override
    public void w(Throwable throwable) {

    }

    @Override
    public void i(Throwable throwable) {

    }

    @Override
    public void d(Throwable throwable) {

    }

    @Override
    public void v(Throwable throwable) {

    }

    @Override
    public void wtf(Throwable throwable) {

    }

}
