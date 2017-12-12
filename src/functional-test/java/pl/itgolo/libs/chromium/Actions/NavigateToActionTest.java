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
public class NavigateToActionTest extends BrowserTest implements IBrowserTest {

    /**
     * Before.
     */
    @Override
    public void before() {
        Spark.get("/navigateTo", (req, res) -> renderContent("pl/itgolo/libs/chromium/Actions/navigateTo.html"));
    }

    /**
     * Test.
     *
     * @param browser the browser
     */
    @Override
    public void test(Browser browser) throws ChromiumException {
        browser.actions.navigateTo(sparkUrl("navigateTo"));
        Assert.assertTrue(browser.actions.getSource().contains("Hello World"));
    }

    /**
     * After all.
     */
    @Override
    public void afterAll() {

    }
}
