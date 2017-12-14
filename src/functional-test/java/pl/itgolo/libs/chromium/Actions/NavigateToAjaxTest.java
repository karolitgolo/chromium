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
public class NavigateToAjaxTest extends BrowserTest implements IBrowserTest {

    /**
     * Before.
     */
    @Override
    public void before() {
        Spark.get("/navigateToAjax", (req, res) -> renderContent("pl/itgolo/libs/chromium/Actions/navigateToAjax.html"));
    }

    /**
     * Test.
     *
     * @param browser the browser
     */
    @Override
    public void test(Browser browser) throws ChromiumException {
        browser.actions.navigateTo(sparkUrl("navigateToAjax"));
        Element elementOld = browser.actions.getElement("#container", 8, 100);
        Assert.assertTrue(elementOld.text().contains("old content"));
        browser.actions.waitElement("#container:contains(New content)", 7);
        Element element = browser.actions.getElement("#container", 8, 100);
        Assert.assertTrue(element.text().contains("New content"));
    }

    /**
     * After all.
     */
    @Override
    public void afterAll() {

    }
}
