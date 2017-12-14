package pl.itgolo.libs.chromium.Services;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import pl.itgolo.libs.chromium.Classes.Log;
import pl.itgolo.libs.chromium.Interfaces.ILogOut;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Log service.
 */
public class LogService {

    /**
     * The constant logProperty.
     */
    public static ObjectProperty<Log> logProperty = new SimpleObjectProperty<>();

    /**
     * The constant logOuts.
     */
    public static List<ILogOut> logOuts = new ArrayList<>();

    /**
     * Set log.
     *
     * @param log the log
     */
    public static void setLog(Log log){
        synchronized (LogService.class){
            logProperty.set(log);
            callLogOut(log);
        }
    }

    /**
     * Set log.
     *
     * @param level   the level
     * @param message the message
     */
    public static void setLog(Log.Level level, String message){
        synchronized (LogService.class){
            Log log = new Log(level, message, getCallerClassName(), getCallerMethodName(), getCallerLineNumber());
            logProperty.set(log);
            callLogOut(log);
        }
    }

    /**
     * Set log.
     *
     * @param level     the level
     * @param message   the message
     * @param exception the exception
     */
    public static void setLog(Log.Level level, String message, Throwable exception){
        synchronized (LogService.class){
            Log log = new Log(level, message, exception, getCallerClassName(), getCallerMethodName(), getCallerLineNumber());
            logProperty.set(log);
            callLogOut(log);
        }
    }

    /**
     * Debug.
     *
     * @param message the message
     */
    public static void debug(String message){
        synchronized (LogService.class){
            Log log = new Log(Log.Level.DEBUG, message, getCallerClassName(), getCallerMethodName(), getCallerLineNumber());
            logProperty.set(log);
            callLogOut(log);
        }
    }

    /**
     * Info.
     *
     * @param message the message
     */
    public static void info(String message){
        synchronized (LogService.class){
            Log log = new Log(Log.Level.INFO, message, getCallerClassName(), getCallerMethodName(), getCallerLineNumber());
            logProperty.set(log);
            callLogOut(log);
        }
    }

    /**
     * Warn.
     *
     * @param message the message
     */
    public static void warn(String message){
        synchronized (LogService.class){
            Log log = new Log(Log.Level.WARNING, message, getCallerClassName(), getCallerMethodName(), getCallerLineNumber());
            logProperty.set(log);
            callLogOut(log);
        }
    }

    /**
     * Error.
     *
     * @param message the message
     */
    public static void error(String message){
        synchronized (LogService.class){
            Log log = new Log(Log.Level.ERROR, message, getCallerClassName(), getCallerMethodName(), getCallerLineNumber());
            logProperty.set(log);
            callLogOut(log);
        }
    }

    /**
     * Error.
     *
     * @param message   the message
     * @param exception the exception
     */
    public static void error(String message, Throwable exception){
        synchronized (LogService.class){
            Log log = new Log(Log.Level.ERROR, message, exception, getCallerClassName(), getCallerMethodName(), getCallerLineNumber());
            logProperty.set(log);
            callLogOut(log);
        }
    }

    /**
     * Error.
     *
     * @param exception the exception
     */
    public static void error(Throwable exception){
        synchronized (LogService.class){
            Log log = new Log(Log.Level.ERROR, exception, getCallerClassName(), getCallerMethodName(), getCallerLineNumber());
            logProperty.set(log);
            callLogOut(log);
        }
    }

    private static void callLogOut(Log log) {
        for (ILogOut logOut : logOuts) {
            logOut.setLog(log);
            logOut.printOut();
        }
    }

    /**
     * Gets caller class name.
     *
     * @return the caller class name
     */
    private static String getCallerClassName() {
        StackTraceElement[] stElements = new Throwable().getStackTrace();
        for (int i = 0; i < stElements.length; i++){
            if (!stElements[i].getClassName().equals(LogService.class.getName())) {
                return stElements[i].getClassName();
            }
        }
        return null;
    }

    /**
     * Gets caller method name.
     *
     * @return the caller method name
     */
    private static String getCallerMethodName() {
        StackTraceElement[] stElements = new Throwable().getStackTrace();
        for (int i = 0; i < stElements.length; i++){
            if (!stElements[i].getClassName().equals(LogService.class.getName())) {
                return  stElements[i].getMethodName();
            }
        }
        return null;
    }

    /**
     * Gets caller line number.
     *
     * @return the caller line number
     */
    private static Integer getCallerLineNumber() {
        StackTraceElement[] stElements = new Throwable().getStackTrace();
        for (int i = 0; i < stElements.length; i++){
            if (!stElements[i].getClassName().equals(LogService.class.getName())) {
                return stElements[i].getLineNumber();
            }
        }
        return null;
    }
}
