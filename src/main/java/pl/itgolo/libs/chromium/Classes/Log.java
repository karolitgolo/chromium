package pl.itgolo.libs.chromium.Classes;

import lombok.Data;

import java.util.Calendar;

/**
 * The type Log.
 */
@Data
public class Log {

    /**
     * The enum Level.
     */
    public enum Level {
        /**
         * Info level.
         */
        INFO, /**
         * Debug level.
         */
        DEBUG, /**
         * Warning level.
         */
        WARNING, /**
         * Error level.
         */
        ERROR
    }

    private Level level;

    private String message;

    private Calendar date;

    private Throwable exception;

    private String callerClass;

    private String callerMethod;

    private Integer callerLine;

    /**
     * Instantiates a new Log.
     *
     * @param level   the level
     * @param message the message
     */
    public Log(Level level, String message, String callerClass, String callerMethod, Integer callerLine) {
        this.level = level;
        this.message = message;
        this.callerClass = callerClass;
        this.callerMethod = callerMethod;
        this.callerLine = callerLine;
        setOtherFields();
    }

    /**
     * Instantiates a new Log.
     *
     * @param level     the level
     * @param message   the message
     * @param exception the exception
     */
    public Log(Level level, String message, Throwable exception, String callerClass, String callerMethod, Integer callerLine) {
        this.level = level;
        this.message = message;
        this.exception = exception;
        this.callerClass = callerClass;
        this.callerMethod = callerMethod;
        this.callerLine = callerLine;
        setOtherFields();
    }

    /**
     * Instantiates a new Log.
     *
     * @param level     the level
     * @param exception the exception
     */
    public Log(Level level, Throwable exception, String callerClass, String callerMethod, Integer callerLine) {
        this.level = level;
        this.exception = exception;
        this.callerClass = callerClass;
        this.callerMethod = callerMethod;
        this.callerLine = callerLine;
        setOtherFields();
    }

    private void setOtherFields() {
        this.date = Calendar.getInstance();
    }
}
