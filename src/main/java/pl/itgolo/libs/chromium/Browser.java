package pl.itgolo.libs.chromium;

import lombok.Getter;
import org.cef.network.CefCookieManager;
import pl.itgolo.libs.chromium.Actions.BrowserActions;
import pl.itgolo.libs.chromium.Exceptions.ChromiumException;
import pl.itgolo.libs.chromium.Factories.BrowserFactory;
import pl.itgolo.libs.chromium.Scenes.BrowserJFrame;
import pl.itgolo.libs.chromium.Scenes.ConfigurationBrowser;
import pl.itgolo.libs.chromium.Services.VisibleBrowser;

import java.io.File;

/**
 * The type Browser.
 */
public class Browser {

    private ConfigurationBrowser configurationBrowser;
    private BrowserFactory factory;

    @Getter
   private BrowserJFrame jFrame;

    @Getter
    private CefCookieManager cookieManager;

    /**
     * The Actions.
     */
    public BrowserActions actions;
    public VisibleBrowser visible;


    /**
     * Instantiates a new Browser.
     *
     * @throws ChromiumException the chromium exception
     */
    public Browser() throws ChromiumException {
        initialize("default");
    }

    public Browser(ConfigurationBrowser configurationBrowser) throws ChromiumException {
        this.configurationBrowser = configurationBrowser;
        initialize("default");
    }


    /**
     * Instantiates a new Browser.
     *
     * @param profileName the profile name
     * @throws ChromiumException the chromium exception
     */
    public Browser(String profileName) throws ChromiumException {
        initialize(profileName);
    }

    public Browser(String profileName, ConfigurationBrowser configurationBrowser) throws ChromiumException {
        this.configurationBrowser = configurationBrowser;
        initialize(profileName);
    }

    private void initialize(String profileName) throws ChromiumException {
        if (this.configurationBrowser == null){
            this.configurationBrowser = new ConfigurationBrowser();
        }
        factory = new BrowserFactory(profileName, new File("app/chromium"), this.configurationBrowser);
        jFrame = this.factory.browserJFrame();
        actions = new BrowserActions(this);
        cookieManager = jFrame.cefCookieManager;
        this.visible = new VisibleBrowser(this);
    }
}
