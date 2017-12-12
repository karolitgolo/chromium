package pl.itgolo.libs.chromium.Actions;

import org.junit.Assert;
import pl.itgolo.libs.chromium.Abstracts.BrowserTest;
import pl.itgolo.libs.chromium.Browser;
import pl.itgolo.libs.chromium.Exceptions.ChromiumException;
import pl.itgolo.libs.chromium.Interfaces.IBrowserTest;
import spark.Spark;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 12.12.2017
 * Time: 12:33
 * Project name: chromium
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class NavigateToTimeoutTest extends BrowserTest implements IBrowserTest {

    /**
     * Before.
     */
    @Override
    public void before() {
        Spark.get("/navigateToTimeout", (req, res) -> {
            NavigateToAction.sleep(15000);
            return "loaded";
        });
    }

    /**
     * Test.
     *
     * @param browser the browser
     */
    @Override
    public void test(Browser browser) throws ChromiumException {
        Boolean hasError = false;
        try {
            browser.actions.navigateTo(sparkUrl("navigateToTimeout"), 5);
        } catch (Exception e) {
            hasError = true;
        }
        Assert.assertTrue(hasError);
    }

    /**
     * After all.
     */
    @Override
    public void afterAll() {

    }
}
