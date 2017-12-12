package pl.itgolo.libs.chromium;

import org.cef.CefApp;
import org.junit.Test;
import pl.itgolo.libs.chromium.Actions.NavigateToActionTest;
import pl.itgolo.libs.chromium.Actions.NavigateToAjaxTest;
import pl.itgolo.libs.chromium.Actions.NavigateToTimeoutTest;
import pl.itgolo.libs.chromium.Interfaces.IBrowserTest;
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
                new NavigateToTimeoutTest()
        );
    }

    /**
     * Sequence tests.
     *
     * @throws Exception the exception
     */
    @Test
    public void sequenceTests() throws Exception {
        declareBrowserTests();
        for (IBrowserTest browserTest : browserTests) {
            browserTest.before();
        }
        Spark.awaitInitialization();

        System.out.println("browser create");
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