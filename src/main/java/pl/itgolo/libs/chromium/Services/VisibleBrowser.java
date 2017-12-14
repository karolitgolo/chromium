package pl.itgolo.libs.chromium.Services;

import pl.itgolo.libs.chromium.Browser;

/**
 * The type Visible browser.
 */
public class VisibleBrowser {

    private Browser browser;

    /**
     * Instantiates a new Visible browser.
     *
     * @param browser the browser
     */
    public VisibleBrowser(Browser browser) {
        this.browser = browser;
    }

    /**
     * Hide.
     */
    public void hide() {
        browser.getJFrame().setVisible(false);
    }

    /**
     * Show.
     */
    public void show() {
        browser.getJFrame().setVisible(true);
    }
}
