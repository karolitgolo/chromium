package pl.itgolo.libs.chromium.Actions;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.itgolo.libs.chromium.Browser;
import pl.itgolo.libs.chromium.Exceptions.ChromiumException;

/**
 * The type Browser actions.
 */
public class BrowserActions {


    private final NavigateToAction navigateToAction;

    private final GetSourceAction getSourceAction;

    private final GetElementAction getElementAction;

    private final GetElementsAction getElementsAction;

    private final WaitElementAction waitElementAction;

    private final ExecuteScriptAction executeScriptAction;

    private final SetAttributeAction setAttributeAction;

    private final FillInputAction fillInputAction;

    private final FillTextAreaAction fillTextAreaAction;

    private final SelectRadioAction selectRadioAction;

    private final SelectCheckBoxAction selectCheckBoxAction;

    private final SelectOptionAction selectOptionAction;

    private final ClickAction clickAction;

    private final ClickReloadAction clickReloadAction;

    private final ResizeAction resizeAction;

    private Browser browser;

    /**
     * Instantiates a new Browser actions.
     *
     * @param browser the browser
     */
    public BrowserActions(Browser browser) {
        this.browser = browser;
        this.navigateToAction = new NavigateToAction(browser);
        this.getSourceAction = new GetSourceAction(browser);
        this.getElementAction = new GetElementAction(browser);
        this.waitElementAction = new WaitElementAction(browser);
        this.executeScriptAction = new ExecuteScriptAction(browser);
        this.setAttributeAction = new SetAttributeAction(browser);
        this.getElementsAction = new GetElementsAction(browser);
        this.fillInputAction = new FillInputAction(browser);
        this.fillTextAreaAction = new FillTextAreaAction(browser);
        this.selectRadioAction = new SelectRadioAction(browser);
        this.selectCheckBoxAction = new SelectCheckBoxAction(browser);
        this.selectOptionAction = new SelectOptionAction(browser);
        this.clickAction = new ClickAction(browser);
        this.clickReloadAction = new ClickReloadAction(browser);
        this.resizeAction = new ResizeAction(browser);
    }

    /**
     * Navigate to.
     *
     * @param url                      the url
     * @param timeout                  the timeout
     * @param attempts                 the attempts
     * @param sleepAttemptMilliseconds the sleep attempt milliseconds
     * @throws ChromiumException the chromium exception
     */
    public void navigateTo(String url, Integer timeout, Integer attempts, Integer sleepAttemptMilliseconds) throws ChromiumException {
        this.navigateToAction.launch(url, timeout, attempts, sleepAttemptMilliseconds);
    }

    /**
     * Navigate to.
     *
     * @param url     the url
     * @param timeout the timeout
     * @throws ChromiumException the chromium exception
     */
    public void navigateTo(String url, Integer timeout) throws ChromiumException {
        this.navigateToAction.launch(url, timeout, 1, 1000);
    }

    /**
     * Navigate to.
     *
     * @param url the url
     * @throws ChromiumException the chromium exception
     */
    public void navigateTo(String url) throws ChromiumException {
        this.navigateToAction.launch(url, 60, 1, 1000);
    }

    /**
     * Gets source.
     *
     * @return the source
     * @throws ChromiumException the chromium exception
     */
    public String getSource() throws ChromiumException {
        return this.getSourceAction.launch(1, 1, 1);
    }

    /**
     * Gets source.
     *
     * @param timeout                  the timeout
     * @param attempts                 the attempts
     * @param sleepAttemptMilliseconds the sleep attempt milliseconds
     * @return the source
     * @throws ChromiumException the chromium exception
     */
    public String getSource(Integer timeout, Integer attempts, Integer sleepAttemptMilliseconds) throws ChromiumException {
        return this.getSourceAction.launch(timeout, attempts, sleepAttemptMilliseconds);
    }

    /**
     * Gets element.
     *
     * @param cssSelector the css selector
     * @return the element
     * @throws ChromiumException the chromium exception
     */
    public Element getElement(String cssSelector) throws ChromiumException {
        return this.getElementAction.launch(cssSelector, 1, 50);
    }

    /**
     * Gets element.
     *
     * @param cssSelector                   the css selector
     * @param timeout                       the timeout
     * @param sleepBetweenCheckMilliseconds the sleep between check milliseconds
     * @return the element
     * @throws ChromiumException the chromium exception
     */
    public Element getElement(String cssSelector, Integer timeout, Integer sleepBetweenCheckMilliseconds) throws ChromiumException {
        return this.getElementAction.launch(cssSelector, timeout, sleepBetweenCheckMilliseconds);
    }

    /**
     * Wait element.
     *
     * @param cssSelector the css selector
     * @param timeout     the timeout
     * @throws ChromiumException the chromium exception
     */
    public void waitElement(String cssSelector, Integer timeout) throws ChromiumException {
        this.waitElementAction.launch(cssSelector, timeout, 75);
    }

    /**
     * Wait element.
     *
     * @param cssSelector                   the css selector
     * @param timeout                       the timeout
     * @param sleepBetweenCheckMilliseconds the sleep between check milliseconds
     * @throws ChromiumException the chromium exception
     */
    public void waitElement(String cssSelector, Integer timeout, Integer sleepBetweenCheckMilliseconds) throws ChromiumException {
        this.waitElementAction.launch(cssSelector, timeout, sleepBetweenCheckMilliseconds);
    }

    /**
     * Execute script string.
     *
     * @param script the script
     * @return the string
     * @throws ChromiumException the chromium exception
     */
    public String executeScript(String script) throws ChromiumException {
        return this.executeScriptAction.launch(script, "'script executed'", 0);
    }

    /**
     * Execute script string.
     *
     * @param script  the script
     * @param timeout the timeout
     * @return the string
     * @throws ChromiumException the chromium exception
     */
    public String executeScript(String script, Integer timeout) throws ChromiumException {
        return this.executeScriptAction.launch(script, "'script executed'", timeout);
    }

    /**
     * Execute script string.
     *
     * @param script         the script
     * @param objectJSReturn the object js return
     * @param timeout        the timeout
     * @return the string
     * @throws ChromiumException the chromium exception
     */
    public String executeScript(String script, String objectJSReturn, Integer timeout) throws ChromiumException {
        return this.executeScriptAction.launch(script, objectJSReturn, timeout);
    }

    /**
     * Execute script string.
     *
     * @param script         the script
     * @param objectJSReturn the object js return
     * @return the string
     * @throws ChromiumException the chromium exception
     */
    public String executeScript(String script, String objectJSReturn) throws ChromiumException {
        return this.executeScriptAction.launch(script, objectJSReturn, 0);
    }

    /**
     * Sets attribute.
     *
     * @param cssSelector   the css selector
     * @param attributeName the attribute name
     * @param value         the value
     * @param timeout       the timeout
     * @throws ChromiumException the chromium exception
     */
    public void setAttribute(String cssSelector, String attributeName, String value, Integer timeout) throws ChromiumException {
        this.setAttributeAction.launch(cssSelector, attributeName, value, timeout);
    }

    /**
     * Sets attribute.
     *
     * @param cssSelector   the css selector
     * @param attributeName the attribute name
     * @param value         the value
     * @throws ChromiumException the chromium exception
     */
    public void setAttribute(String cssSelector, String attributeName, String value) throws ChromiumException {
        this.setAttributeAction.launch(cssSelector, attributeName, value,  3);
    }

    /**
     * Gets elements.
     *
     * @param cssSelector the css selector
     * @return the elements
     * @throws ChromiumException the chromium exception
     */
    public Elements getElements(String cssSelector) throws ChromiumException {
        return this.getElementsAction.launch(cssSelector, 1, 50);
    }

    /**
     * Gets elements.
     *
     * @param cssSelector                   the css selector
     * @param timeout                       the timeout
     * @param sleepBetweenCheckMilliseconds the sleep between check milliseconds
     * @return the elements
     * @throws ChromiumException the chromium exception
     */
    public Elements getElements(String cssSelector, Integer timeout, Integer sleepBetweenCheckMilliseconds) throws ChromiumException {
        return this.getElementsAction.launch(cssSelector, timeout, sleepBetweenCheckMilliseconds);
    }

    /**
     * Fill input.
     *
     * @param cssSelector the css selector
     * @param value       the value
     * @param timeout     the timeout
     * @throws ChromiumException the chromium exception
     */
    public void fillInput(String cssSelector, String value, Integer timeout) throws ChromiumException {
        this.fillInputAction.launch(cssSelector,value, timeout);
    }

    /**
     * Fill input.
     *
     * @param cssSelector the css selector
     * @param value       the value
     * @throws ChromiumException the chromium exception
     */
    public void fillInput(String cssSelector, String value) throws ChromiumException {
        this.fillInputAction.launch(cssSelector,value, 10);
    }

    /**
     * Fill text area.
     *
     * @param cssSelector the css selector
     * @param value       the value
     * @param timeout     the timeout
     * @throws ChromiumException the chromium exception
     */
    public void fillTextArea(String cssSelector, String value, Integer timeout) throws ChromiumException {
        this.fillTextAreaAction.launch(cssSelector,value, timeout);
    }

    /**
     * Fill text area.
     *
     * @param cssSelector the css selector
     * @param value       the value
     * @throws ChromiumException the chromium exception
     */
    public void fillTextArea(String cssSelector, String value) throws ChromiumException {
        this.fillTextAreaAction.launch(cssSelector,value, 10);
    }

    /**
     * Select radio.
     *
     * @param cssSelector the css selector
     * @param value       the value
     * @throws ChromiumException the chromium exception
     */
    public void selectRadio(String cssSelector, String value) throws ChromiumException {
        this.selectRadioAction.launch(cssSelector, value);
    }

    /**
     * Select check box.
     *
     * @param cssSelector the css selector
     * @param checked     the checked
     * @throws ChromiumException the chromium exception
     */
    public void selectCheckBox(String cssSelector, Boolean checked) throws ChromiumException {
        this.selectCheckBoxAction.launch(cssSelector, checked);
    }

    /**
     * Select option.
     *
     * @param cssSelector the css selector
     * @param value       the value
     * @throws ChromiumException the chromium exception
     */
    public void selectOption(String cssSelector, String value) throws ChromiumException {
        this.selectOptionAction.launch(cssSelector, value);
    }

    /**
     * Click.
     *
     * @param cssSelector the css selector
     * @throws ChromiumException the chromium exception
     */
    public void click(String cssSelector) throws ChromiumException {
        this.clickAction.launch(cssSelector);
    }

    /**
     * Click reload.
     *
     * @param cssSelector the css selector
     * @param timeout     the timeout
     * @throws ChromiumException the chromium exception
     */
    public void clickReload(String cssSelector, Integer timeout) throws ChromiumException {
        this.clickReloadAction.launch(cssSelector, timeout);
    }

    /**
     * Resize.
     *
     * @param width  the width
     * @param height the height
     * @throws ChromiumException the chromium exception
     */
    public void resize(Integer width, Integer height) throws ChromiumException {
        this.resizeAction.launch(width, height);
    }
}
