package pl.itgolo.libs.chromium.Actions;

import org.jsoup.select.Elements;
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
public class GetElementsTest extends BrowserTest implements IBrowserTest {

    /**
     * Before.
     */
    @Override
    public void before() {
        Spark.get("/getElements" , (req, res) -> renderContent("pl/itgolo/libs/chromium/Actions/getElements.html"));
    }

    /**
     * Test.
     *
     * @param browser the browser
     */
    @Override
    public void test(Browser browser) throws ChromiumException {
        browser.actions.navigateTo(sparkUrl("getElements"));
        Elements elements = browser.actions.getElements(".container");
        Assert.assertTrue(elements.size() == 2);
        String content = elements.get(1).html();
        Assert.assertTrue(content.equals("content 2"));
    }

    /**
     * After all.
     */
    @Override
    public void afterAll() {

    }
}
