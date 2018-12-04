package org.esmaeeli.droid.log;

import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Provides a well-formatted logging to a file. Also logs to logcat using {@link LogCat}.
 * Note that the user of this class is responsible for managing write permission if the log file
 * resides in external storage.
 * The logger does not throw an exception if opening the file results in errors, but does not write
 * anything to file.
 *
 * @author Javid Esmaeeli
 */
public class FileLog implements ILog, Closeable {

    private static final String PREFIX_ERR = "ERR";
    private static final String PREFIX_WRN = "WRN";
    private static final String PREFIX_INF = "INF";
    private static final String PREFIX_DBG = "DBG";
    private static final String PREFIX_VRB = "VRB";
    private static final String PREFIX_WTF = "WTF";

    private FileCat logCat;
    private LogLevel level;
    private boolean canWrite;
    private PrintWriter writer;
    private DateFormat dateFormat;

    public FileLog(LogLevel level, LogLevel logCatLevel, File file, boolean append) {
        this.logCat = new FileCat(logCatLevel);
        this.level = level;

        try {
            writer = new PrintWriter(new FileWriter(file, append));
            canWrite = true;
        } catch (IOException | SecurityException e) {
            canWrite = false;
        }

        this.dateFormat = SimpleDateFormat.getTimeInstance(SimpleDateFormat.MEDIUM, Locale.ENGLISH);
    }

    @Override
    public void setLogLevel(LogLevel level) {
        this.level = level;
    }

    @Override
    public void e(String message) {
        logCat.e(message);
        if (level.ordinal() < LogLevel.ERROR.ordinal()) {
            return;
        }
        append(PREFIX_ERR, message);
    }

    @Override
    public void w(String message) {
        logCat.w(message);
        if (level.ordinal() < LogLevel.WARNING.ordinal()) {
            return;
        }
        append(PREFIX_WRN, message);
    }

    @Override
    public void i(String message) {
        logCat.i(message);
        if (level.ordinal() < LogLevel.INFO.ordinal()) {
            return;
        }
        append(PREFIX_INF, message);
    }

    @Override
    public void d(String message) {
        logCat.d(message);
        if (level.ordinal() < LogLevel.DEBUG.ordinal()) {
            return;
        }
        append(PREFIX_DBG, message);
    }

    @Override
    public void v(String message) {
        logCat.v(message);
        if (level.ordinal() < LogLevel.VERBOSE.ordinal()) {
            return;
        }
        append(PREFIX_VRB, message);
    }

    @Override
    public void wtf(String message) {
        logCat.wtf(message);
        if (level.ordinal() < LogLevel.ERROR.ordinal()) {
            return;
        }
        append(PREFIX_WTF, message);
    }

    @Override
    public void e(Throwable throwable) {
        logCat.e(throwable);
        if (level.ordinal() < LogLevel.ERROR.ordinal()) {
            return;
        }
        append(PREFIX_ERR, throwable);
    }

    @Override
    public void w(Throwable throwable) {
        logCat.w(throwable);
        if (level.ordinal() < LogLevel.WARNING.ordinal()) {
            return;
        }
        append(PREFIX_WRN, throwable);
    }

    @Override
    public void i(Throwable throwable) {
        logCat.i(throwable);
        if (level.ordinal() < LogLevel.INFO.ordinal()) {
            return;
        }
        append(PREFIX_INF, throwable);
    }

    @Override
    public void d(Throwable throwable) {
        logCat.d(throwable);
        if (level.ordinal() < LogLevel.DEBUG.ordinal()) {
            return;
        }
        append(PREFIX_DBG, throwable);
    }

    @Override
    public void v(Throwable throwable) {
        logCat.v(throwable);
        if (level.ordinal() < LogLevel.VERBOSE.ordinal()) {
            return;
        }
        append(PREFIX_VRB, throwable);
    }

    @Override
    public void wtf(Throwable throwable) {
        logCat.wtf(throwable);
        if (level.ordinal() < LogLevel.ERROR.ordinal()) {
            return;
        }
        append(PREFIX_WTF, throwable);
    }

    public void emptyLine() {
        if (canWrite && level != LogLevel.DISABLE) {
            writer.append("\n");
        }
    }

    private void append(String type, Throwable throwable) {

        if (canWrite) {
            writer.append(Calendar.getInstance().getTime().toString()).append(": ").append(type).append(" -> ");
            throwable.printStackTrace(writer);
            writer.append("\n");
        }
    }

    private void append(String type, String message) {

        if (canWrite) {
            writer.append(dateFormat.format(Calendar.getInstance().getTime())).append(": ").append(type).append(" -> ");
            writer.append(message).append("\n");
        }
    }

    @Override
    public void close() {
        canWrite = false;
        if (writer != null) {
            writer.flush();
            writer.close();
        }
    }

    private static class FileCat extends LogCat {

        private FileCat(LogLevel logLevel) {
            super(logLevel);
        }

        @Override
        protected int getStackTraceIndexOfCaller() {
            return 5;
        }
    }
}
