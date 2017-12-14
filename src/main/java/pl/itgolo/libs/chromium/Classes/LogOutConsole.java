package pl.itgolo.libs.chromium.Classes;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.codehaus.plexus.util.ExceptionUtils;
import pl.itgolo.libs.chromium.Interfaces.ILogOut;
import pl.itgolo.libs.chromium.Services.ConsoleService;

/**
 * The type Log out console.
 */
public class LogOutConsole implements ILogOut {

    private Log log;

    @Override
    public void setLog(Log log) {
        this.log = log;
    }

    @Override
    public void printOut() {
        if (log == null) {
            return;
        }
        switch (log.getLevel()) {
            case INFO:
                logOutInfo();
                break;
            case DEBUG:
                logOutDebug();
                break;
            case WARNING:
                logOutWarn();
                break;
            case ERROR:
                logOutError();
                break;
        }
    }

    private void logOutError() {
        String message = String.format("ERROR: [%1$s] (%2$s.java:%4$s %3$s)   %5$s",
                DateFormatUtils.format(log.getDate(), "yyyy-MM-dd HH:mm:ss"),
                log.getCallerClass(),
                log.getCallerMethod(),
                log.getCallerLine(),
                (log.getMessage() == null) ? "" : log.getMessage()
        );
        if (log.getException() != null){
            String stackTrace = ExceptionUtils.getStackTrace(log.getException());
            message += "\n" + stackTrace.trim();
        }
        System.out.println(ConsoleService.setColorRed(message));
    }

    private void logOutWarn() {
        String message = String.format("WARN:  [%1$s] (%2$s.java:%4$s %3$s)   %5$s",
                DateFormatUtils.format(log.getDate(), "yyyy-MM-dd HH:mm:ss"),
                log.getCallerClass(),
                log.getCallerMethod(),
                log.getCallerLine(),
                log.getMessage()
        );
        System.out.println(ConsoleService.setColorYellow(message));
    }

    private void logOutDebug() {
        String message = String.format("DEBUG: [%1$s] (%2$s.java:%4$s %3$s)   %5$s",
                DateFormatUtils.format(log.getDate(), "yyyy-MM-dd HH:mm:ss"),
                log.getCallerClass(),
                log.getCallerMethod(),
                log.getCallerLine(),
                log.getMessage()
        );
        System.out.println(ConsoleService.setColorCyan(message));
    }

    private void logOutInfo() {
        String message = String.format("INFO:  [%1$s] (%2$s.java:%4$s %3$s)   %5$s",
                DateFormatUtils.format(log.getDate(), "yyyy-MM-dd HH:mm:ss"),
                log.getCallerClass(),
                log.getCallerMethod(),
                log.getCallerLine(),
                log.getMessage()
        );
        System.out.println(ConsoleService.setColorWhite(message));
    }
}
