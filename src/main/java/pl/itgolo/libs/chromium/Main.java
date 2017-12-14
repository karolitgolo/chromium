package pl.itgolo.libs.chromium;

import pl.itgolo.libs.chromium.Classes.LogOutConsole;
import pl.itgolo.libs.chromium.Exceptions.ChromiumException;
import pl.itgolo.libs.chromium.Scenes.ConfigurationBrowser;
import pl.itgolo.libs.chromium.Services.LogService;

import java.io.IOException;
import java.util.Arrays;

/**
 * The type Main service.
 */
public class Main {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws ChromiumException the chromium exception
     */
    public static void main(String[] args) throws IOException, ChromiumException {
        LogService.logOuts.addAll(Arrays.asList(new LogOutConsole()));
        ConfigurationBrowser config = new ConfigurationBrowser();
        config.setEnableGeolocation(false);
        config.setEnableJSDialog(false);
        Browser browser = new Browser(config);
        browser.actions.navigateTo("http://google.pl");
    }


}


