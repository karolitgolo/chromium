package pl.itgolo.libs.chromium;

import org.cef.CefApp;
import org.junit.Test;
import pl.itgolo.libs.chromium.Actions.*;
import pl.itgolo.libs.chromium.Classes.LogOutConsole;
import pl.itgolo.libs.chromium.Interfaces.IBrowserTest;
import pl.itgolo.libs.chromium.Services.LogService;
import spark.Spark;

import java.util.Arrays;
import java.util.List;

/**
 * The type Browser sequence fix test.
 */
public class BrowserSequenceFixTest {

    /**
     * The Browser tests.
     */
    List<IBrowserTest> browserTests;

    private void declareBrowserTests() {
        browserTests = Arrays.asList(
                new NavigateToActionTest(),
                new NavigateToAjaxTest(),
                new NavigateToTimeoutTest(),
                new NavigateToAttemptTest(),
                new ExecuteScriptTest(),
                new SetAttributeTest(),
                new GetElementsEmptyTest(),
                new GetElementsTest(),
                new FillInputTest(),
                new FillTextAreaTest(),
                new SelectRadioTest(),
                new SelectRadioTest(),
                new SelectCheckBoxTest(),
                new SelectOptionTest(),
                new ClickTest(),
                new ClickReloadTest()
        );
    }

    /**
     * Sequence tests.
     *
     * @throws Exception the exception
     */
    @Test
    public void sequenceTests() throws Exception {
        LogService.logOuts.addAll(Arrays.asList(new LogOutConsole()));
        declareBrowserTests();
        for (IBrowserTest browserTest : browserTests) {
            browserTest.before();
        }
        Spark.awaitInitialization();
        Browser browser = new Browser();
        for (IBrowserTest browserTest : browserTests) {
            browserTest.test(browser);
        }
        for (IBrowserTest browserTest : browserTests) {
            browserTest.afterAll();
        }
        Spark.stop();
        browser.getJFrame().cefApp.shutdownFix();
        CefApp.getInstance().dispose();
    }

}