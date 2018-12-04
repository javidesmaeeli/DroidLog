package org.esmaeeli.droid.log;

/**
 * An interface for logging with level management.
 *
 * @author Javid Esmaeeli
 */

public interface ILog {

    void setLogLevel(LogLevel level);

    void e(String message);

    void w(String message);

    void i(String message);

    void d(String message);

    void v(String message);

    void wtf(String message);

    void e(Throwable throwable);

    void w(Throwable throwable);

    void i(Throwable throwable);

    void d(Throwable throwable);

    void v(Throwable throwable);

    void wtf(Throwable throwable);

}
