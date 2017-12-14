package pl.itgolo.libs.chromium.Factories;

import javafx.beans.property.SimpleObjectProperty;
import lombok.Data;
import org.cef.CefSettings;
import org.cef.OS;
import pl.itgolo.libs.chromium.Exceptions.ChromiumException;
import pl.itgolo.libs.chromium.Scenes.BrowserJFrame;
import pl.itgolo.libs.chromium.Scenes.ConfigurationBrowser;
import pl.itgolo.libs.chromiumresources.Actions.CreateBinJcef;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The type Browser factory.
 */
@Data
public class BrowserFactory {

    private ConfigurationBrowser configurationBrowser;
    /**
     * The Profile name.
     */
    String profileName;

    /**
     * The Dir chromium.
     */
    File dirChromium;

    /**
     * Instantiates a new Browser factory.
     *
     * @param profileName the profile name
     * @param dirChromium the dir chromium
     * @param configurationBrowser
     * @throws ChromiumException the chromium exception
     */
    public BrowserFactory(String profileName, File dirChromium, ConfigurationBrowser configurationBrowser) throws ChromiumException {
        this.profileName = profileName;
        this.dirChromium = dirChromium;
        this.configurationBrowser = configurationBrowser;
        copyChromiumFiles();
    }

    /**
     * Copy chromium files.
     *
     * @throws ChromiumException the chromium exception
     */
    void copyChromiumFiles() throws ChromiumException {
        try {
            CreateBinJcef createBinJcef = new CreateBinJcef(new File("."));
            createBinJcef.copyFromResource();
        } catch (Exception e) {
            throw new ChromiumException(e);
        }
    }

    /**
     * Browser j frame browser j frame.
     *
     * @return the browser j frame
     * @throws ChromiumException the chromium exception
     */
    public BrowserJFrame browserJFrame() throws ChromiumException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        SimpleObjectProperty<BrowserJFrame> browserJFrame = new SimpleObjectProperty<>();
        SimpleObjectProperty<Throwable> errorCreateBrowserWindow = new SimpleObjectProperty<>();
        executorService.submit(() -> {
            try {
                browserJFrame.set(new BrowserJFrame(this.profileName, this.dirChromium, this.configurationBrowser));
            } catch (Exception e) {
                errorCreateBrowserWindow.set(e);
            }
        });
        executorService.shutdown();
        if (errorCreateBrowserWindow.get() != null) {
            throw new ChromiumException(errorCreateBrowserWindow.get());
        }
        waitForLoadBrowserJFrame();
        waitForLoadBrowserChromium(browserJFrame.get());
        return browserJFrame.get();
    }

    /**
     * Wait for load browser j frame.
     *
     * @throws ChromiumException the chromium exception
     */
    public static void waitForLoadBrowserJFrame() throws ChromiumException {
        try {
            for (Integer i = 0; i <= 30 * 5; i++) {
                if (BrowserJFrame.isLoadedChromium) {
                    break;
                }
                if (i.equals(30 * 5)) {
                    throw new ChromiumException("Not detect loaded jFrame with chromium");
                }
                Thread.sleep(200);
            }
        } catch (Exception e) {
            throw new ChromiumException(e);
        }
    }

    /**
     * Wait for load browser chromium.
     *
     * @throws ChromiumException the chromium exception
     */
    public void waitForLoadBrowserChromium(BrowserJFrame browserJFrame) throws ChromiumException {
        try {
            for (Integer i = 0; i <= 30 * 5; i++) {
                if (browserJFrame != null){
                    if (!browserJFrame.cefBrowser.isLoading()) {
                        break;
                    }
                }
                if (i.equals(30 * 5)) {
                    throw new ChromiumException("Not detect loaded browser of chromium");
                }
                Thread.sleep(200);
            }
            Thread.sleep(1);
        } catch (Exception e) {
            throw new ChromiumException(e);
        }
    }

    public static CefSettings getCefSettings(File dirChromium, String profileName) {
        CefSettings settings = new CefSettings();
        File cacheDir = new File(dirChromium, "profiles/" + profileName + "/cache");
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        settings.cache_path = cacheDir.getAbsolutePath();
        settings.ignore_certificate_errors = true;
        settings.log_severity = CefSettings.LogSeverity.LOGSEVERITY_ERROR;
        File logsDir = new File(dirChromium, "profiles/" + profileName + "/logs");
        if (!logsDir.exists()) {
            logsDir.mkdirs();
        }
        settings.log_file = logsDir.getAbsolutePath() + "/log.txt";
        settings.ignore_certificate_errors = true;
        settings.windowless_rendering_enabled = OS.isLinux();
        return settings;
    }
}
