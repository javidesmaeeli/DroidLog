package org.esmaeeli.droid.log;

import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Logs all messages into a file without any formatting.
 * Note that the user of this class is responsible for managing write permission if the log file
 * resides in external storage.
 * The logger does not throw an exception if opening the file results in errors, but does not write
 * anything to file.
 *
 * @author Javid Esmaeeli
 */
public class SimpleFileLog implements ILog, Closeable {

    private boolean canWrite;
    private PrintWriter writer;
    private LogLevel level;

    public SimpleFileLog(LogLevel level, File file, boolean append) {
        this.level = level;

        try {
            writer = new PrintWriter(new FileWriter(file, append));
            canWrite = true;
        } catch (IOException | SecurityException e) {
            canWrite = false;
        }
    }

    @Override
    public void setLogLevel(LogLevel level) {
        this.level = level;
    }

    @Override
    public void e(String message) {
        if (!canWrite || level.ordinal() < LogLevel.ERROR.ordinal()) {
            return;
        }
        writer.append(message).append("\n");
    }

    @Override
    public void w(String message) {
        if (!canWrite || level.ordinal() < LogLevel.WARNING.ordinal()) {
            return;
        }
        writer.append(message).append("\n");
    }

    @Override
    public void i(String message) {
        if (!canWrite || level.ordinal() < LogLevel.INFO.ordinal()) {
            return;
        }
        writer.append(message).append("\n");
    }

    @Override
    public void d(String message) {
        if (!canWrite || level.ordinal() < LogLevel.DEBUG.ordinal()) {
            return;
        }
        writer.append(message).append("\n");
    }

    @Override
    public void v(String message) {
        if (!canWrite || level.ordinal() < LogLevel.VERBOSE.ordinal()) {
            return;
        }
        writer.append(message).append("\n");
    }

    @Override
    public void wtf(String message) {
        if (!canWrite || level.ordinal() < LogLevel.ERROR.ordinal()) {
            return;
        }
        writer.append(message).append("\n");
    }

    @Override
    public void e(Throwable throwable) {
        if (!canWrite || level.ordinal() < LogLevel.ERROR.ordinal()) {
            return;
        }
        throwable.printStackTrace(writer);
        writer.append("\n");
    }

    @Override
    public void w(Throwable throwable) {
        if (!canWrite || level.ordinal() < LogLevel.WARNING.ordinal()) {
            return;
        }
        throwable.printStackTrace(writer);
        writer.append("\n");
    }

    @Override
    public void i(Throwable throwable) {
        if (!canWrite || level.ordinal() < LogLevel.INFO.ordinal()) {
            return;
        }
        throwable.printStackTrace(writer);
        writer.append("\n");
    }

    @Override
    public void d(Throwable throwable) {
        if (!canWrite || level.ordinal() < LogLevel.DEBUG.ordinal()) {
            return;
        }
        throwable.printStackTrace(writer);
        writer.append("\n");
    }

    @Override
    public void v(Throwable throwable) {
        if (!canWrite || level.ordinal() < LogLevel.VERBOSE.ordinal()) {
            return;
        }
        throwable.printStackTrace(writer);
        writer.append("\n");
    }

    @Override
    public void wtf(Throwable throwable) {
        if (!canWrite || level.ordinal() < LogLevel.ERROR.ordinal()) {
            return;
        }
        throwable.printStackTrace(writer);
        writer.append("\n");
    }

    @Override
    public void close() throws IOException {
        canWrite = false;
        if (writer != null) {
            writer.flush();
            writer.close();
        }
    }

}
