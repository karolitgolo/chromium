package pl.itgolo.libs.chromium.Scenes;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.cef.CefApp;
import org.cef.CefClient;
import org.cef.CefSettings;
import org.cef.OS;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefMessageRouter;
import org.cef.browser.CefRequestContext;
import org.cef.handler.CefAppHandlerAdapter;
import org.cef.handler.CefDisplayHandlerAdapter;
import org.cef.handler.CefLoadHandlerAdapter;
import org.cef.handler.CefRequestContextHandlerAdapter;
import org.cef.network.CefCookieManager;
import org.codehaus.plexus.util.FileUtils;
import pl.itgolo.libs.chromium.Factories.BrowserFactory;
import pl.itgolo.libs.chromium.Scenes.Detailed.dialog.DownloadDialog;
import pl.itgolo.libs.chromium.Scenes.Detailed.handler.*;
import pl.itgolo.libs.chromium.Scenes.Detailed.ui.ControlPanel;
import pl.itgolo.libs.chromium.Scenes.Detailed.ui.MyMenuBar;
import pl.itgolo.libs.chromium.Scenes.Detailed.ui.StatusPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

/**
 * The type Browser j frame.
 */
public class BrowserJFrame extends JFrame {

    /**
     * The constant isLoadingProperty.
     */
    public static BooleanProperty isLoadingProperty = new SimpleBooleanProperty(false);

    /**
     * The Cef client.
     */
    public final CefClient cefClient;
    public final ConfigurationBrowser configurationBrowser;

    /**
     * The Error msg.
     */
    String errorMsg = "";
    /**
     * The Cef browser.
     */
    public final CefBrowser cefBrowser;

    /**
     * The Control pane.
     */
    ControlPanel controlPane;

    /**
     * The Status panel.
     */
    StatusPanel statusPanel;

    /**
     * The Cef cookie manager.
     */
    public final CefCookieManager cefCookieManager;

    /**
     * The Cef app.
     */
    public CefApp cefApp;


    /**
     * The Profile name.
     */
    String profileName;

    /**
     * The Dir chromium.
     */
    File dirChromium;

    /**
     * The Cookie path.
     */
    String cookiePath;

    /**
     * The constant isLoadedChromium.
     */
    public static Boolean isLoadedChromium = false;


    /**
     * Instantiates a new Browser j frame.
     *
     * @param profileName          the profile name
     * @param dirChromium          the dir chromium
     * @param configurationBrowser the configuration browser
     */
    public BrowserJFrame(String profileName, File dirChromium, ConfigurationBrowser configurationBrowser) {
        this.dirChromium = dirChromium;
        this.profileName = profileName;
        setCookiesPath(dirChromium);
        this.configurationBrowser = configurationBrowser;
        CefSettings settings = BrowserFactory.getCefSettings(dirChromium, this.profileName);
        CefApp.addAppHandler(new AppHandler(new String[]{}));
        cefApp = CefApp.getInstance(settings);
        fixDeleteDatabasesIncognitoDir();
        cefClient = cefApp.createClient();
        DownloadDialog downloadDialog = new DownloadDialog(this);
        configCefClient(downloadDialog);
        CefRequestContext requestContext = null;
        if (cookiePath != null) {
            cefCookieManager = CefCookieManager.createManager(cookiePath, true);
            requestContext = CefRequestContext.createContext(
                    new CefRequestContextHandlerAdapter() {
                        @Override
                        public CefCookieManager getCookieManager() {
                            return cefCookieManager;
                        }
                    });
        } else {
            cefCookieManager = CefCookieManager.getGlobalManager();

        }

        cefBrowser = cefClient.createBrowser("about:blank",
                OS.isLinux(),
                false,
                requestContext);
        getContentPane().add(createContentPanel(), BorderLayout.CENTER);
        MyMenuBar menuBar = new MyMenuBar(this,
                cefBrowser,
                controlPane,
                downloadDialog,
                cefCookieManager);

        menuBar.addBookmark("Binding Test", "client://tests/binding_test.html");
        menuBar.addBookmark("Binding Test 2", "client://tests/binding_test2.html");
        menuBar.addBookmark("Download Test", "http://cefbuilds.com");
        menuBar.addBookmark("Geolocation Test", "http://slides.html5rocks.com/#geolocation");
        menuBar.addBookmark("Login Test (username:pumpkin, password:pie)", "http://www.colostate.edu/~ric/protect/your.html");
        menuBar.addBookmark("Certificate-error Test", "https://www.k2go.de");
        menuBar.addBookmark("Resource-Handler Test", "http://www.foo.bar/");
        menuBar.addBookmark("Scheme-Handler Test 1: (scheme \"client\")", "client://tests/handler.html");
        menuBar.addBookmark("Scheme-Handler Test 2: (scheme \"search\")", "search://do a barrel roll/");
        menuBar.addBookmark("Spellcheck test", "client://tests/spellcheck.html");
        menuBar.addBookmark("Test local Storage", "client://tests/localstorage.html");
        menuBar.addBookmarkSeparator();
        menuBar.addBookmark("javachromiumembedded", "https://bitbucket.org/chromiumembedded/java-cef");
        menuBar.addBookmark("chromiumembedded", "https://bitbucket.org/chromiumembedded/cef");
        setJMenuBar(menuBar);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                CefApp.getInstance().dispose();
                cefApp.shutdownFix();
                dispose();
            }

        });
        setSize(1100, 700);
        if (this.configurationBrowser.getEnableVisible()){
            setVisible(true);
        }
    }

    private void configCefClient(DownloadDialog downloadDialog) {
        cefClient.addContextMenuHandler(new ContextMenuHandler(this));
        cefClient.addDownloadHandler(downloadDialog);
        cefClient.addDragHandler(new DragHandler());
        if (this.configurationBrowser.getEnableGeolocation()){
            cefClient.addGeolocationHandler(new GeolocationHandler(this));
        }
        if (this.configurationBrowser.getEnableJSDialog()){
            cefClient.addJSDialogHandler(new JSDialogHandler());
        }
        cefClient.addKeyboardHandler(new KeyboardHandler());
        cefClient.addRequestHandler(new RequestHandler(this));
        CefMessageRouter msgRouter = CefMessageRouter.create();
        msgRouter.addHandler(new MessageRouterHandler(), true);
        msgRouter.addHandler(new MessageRouterHandlerEx(cefClient), false);
        msgRouter.addHandler(new MessageRouterReturnJSHandler(cefClient), false);
        cefClient.addMessageRouter(msgRouter);
        cefClient.addDisplayHandler(new CefDisplayHandlerAdapter() {
            @Override
            public void onAddressChange(CefBrowser browser, String url) {
                controlPane.setAddress(browser, url);
            }

            @Override
            public void onTitleChange(CefBrowser browser, String title) {
                setTitle(title);
            }

            @Override
            public void onStatusMessage(CefBrowser browser, String value) {
                statusPanel.setStatusText(value);
            }
        });
        cefClient.addLoadHandler(new CefLoadHandlerAdapter() {

            @Override
            public void onLoadingStateChange(CefBrowser browser,
                                             boolean isLoading,
                                             boolean canGoBack,
                                             boolean canGoForward) {
                controlPane.update(browser, isLoading, canGoBack, canGoForward);
                statusPanel.setIsInProgress(isLoading);

                if (!isLoading && !errorMsg.isEmpty()) {
                    browser.loadString(errorMsg, controlPane.getAddress());
                    errorMsg = "";
                }
                isLoadedChromium = true;
               // browser.getIdentifier();
               // isLoadingProperty.set(isLoading);
            }

            @Override
            public void onLoadError(CefBrowser browser,
                                    int frameIdentifer,
                                    ErrorCode errorCode,
                                    String errorText,
                                    String failedUrl) {
                if (errorCode != ErrorCode.ERR_NONE && errorCode != ErrorCode.ERR_ABORTED) {
                    errorMsg = "<html><head>";
                    errorMsg += "<title>Error while loading</title>";
                    errorMsg += "</head><body>";
                    errorMsg += "<h1>" + errorCode + "</h1>";
                    errorMsg += "<h3>Failed to load " + failedUrl + "</h3>";
                    errorMsg += "<p>" + (errorText == null ? "" : errorText) + "</p>";
                    errorMsg += "</body></html>";
                    browser.stopLoad();
                }
            }

            @Override
            public void onLoadEnd(CefBrowser browser, int frameIdentifier, int httpStatusCode) {

            }
        });
    }

    private void fixDeleteDatabasesIncognitoDir() {
        CefApp.addAppHandler(new CefAppHandlerAdapter(null) {
            @Override
            public void stateHasChanged(CefApp.CefAppState state) {
                if (state == CefApp.CefAppState.TERMINATED) {
                    File databasesIncognitoDir = new File("databases-incognito");
                    if (databasesIncognitoDir.exists()) {
                        try {
                            FileUtils.deleteDirectory(databasesIncognitoDir);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    private void setCookiesPath(File dirChromium) {
        File cookiesDir = new File(dirChromium, "profiles/" + this.profileName + "/cookies");
        if (!cookiesDir.exists()) {
            cookiesDir.mkdirs();
        }
        this.cookiePath = cookiesDir.getAbsolutePath();
    }


    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new BorderLayout());
        controlPane = new ControlPanel(cefBrowser);
        statusPanel = new StatusPanel();
        contentPanel.add(controlPane, BorderLayout.NORTH);
        contentPanel.add(cefBrowser.getUIComponent(), BorderLayout.CENTER);
        contentPanel.add(statusPanel, BorderLayout.SOUTH);
        return contentPanel;
    }
}