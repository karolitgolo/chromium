package pl.itgolo.libs.chromium.Classes;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.codehaus.plexus.util.ExceptionUtils;
import pl.itgolo.libs.chromium.Interfaces.ILogOut;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * The type Log out console.
 */
public class LogOutFile implements ILogOut {

    private Log log;

    private File file;

    public LogOutFile(File file) throws IOException {
        this.file = file;
        createLogFile();
    }

    private void createLogFile() throws IOException {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
    }

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
        if (log.getException() != null) {
            String stackTrace = ExceptionUtils.getStackTrace(log.getException());
            message += "\n" + stackTrace.trim();
        }
        message += System.lineSeparator();
        try {
            Files.write(Paths.get(file.getCanonicalPath()), message.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void logOutWarn() {
        String message = String.format("WARN:  [%1$s] (%2$s.java:%4$s %3$s)   %5$s",
                DateFormatUtils.format(log.getDate(), "yyyy-MM-dd HH:mm:ss"),
                log.getCallerClass(),
                log.getCallerMethod(),
                log.getCallerLine(),
                log.getMessage()
        );
        message += System.lineSeparator();
        try {
            Files.write(Paths.get(file.getCanonicalPath()), message.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void logOutDebug() {
        String message = String.format("DEBUG: [%1$s] (%2$s.java:%4$s %3$s)   %5$s",
                DateFormatUtils.format(log.getDate(), "yyyy-MM-dd HH:mm:ss"),
                log.getCallerClass(),
                log.getCallerMethod(),
                log.getCallerLine(),
                log.getMessage()
        );
        message += System.lineSeparator();
        try {
            Files.write(Paths.get(file.getCanonicalPath()), message.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void logOutInfo() {
        String message = String.format("INFO:  [%1$s] (%2$s.java:%4$s %3$s)   %5$s",
                DateFormatUtils.format(log.getDate(), "yyyy-MM-dd HH:mm:ss"),
                log.getCallerClass(),
                log.getCallerMethod(),
                log.getCallerLine(),
                log.getMessage()
        );
        message += System.lineSeparator();
        try {
            Files.write(Paths.get(file.getCanonicalPath()), message.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
