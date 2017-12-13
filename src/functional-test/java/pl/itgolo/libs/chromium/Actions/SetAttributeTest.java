package pl.itgolo.libs.chromium.Actions;

import org.jsoup.nodes.Element;
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
public class SetAttributeTest extends BrowserTest implements IBrowserTest {

    /**
     * Before.
     */
    @Override
    public void before() {
        Spark.get("/setAttribute", (req, res) -> renderContent("pl/itgolo/libs/chromium/Actions/setAttribute.html"));
    }

    /**
     * Test.
     *
     * @param browser the browser
     */
    @Override
    public void test(Browser browser) throws ChromiumException {
        browser.actions.navigateTo(sparkUrl("setAttribute"));
        browser.actions.setAttribute("#username","value", "new content");
        Element username = browser.actions.getElement("#username");
        System.out.println(username.attr("value"));
        Assert.assertTrue(username.attr("value").equals("new content"));

    }

    /**
     * After all.
     */
    @Override
    public void afterAll() {

    }
}
