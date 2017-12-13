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
public class ClickReloadTest extends BrowserTest implements IBrowserTest {

    /**
     * Before.
     */
    @Override
    public void before() {
        Spark.get("/clickReload", (req, res) -> renderContent("pl/itgolo/libs/chromium/Actions/clickReload.html"));
        Spark.get("/clickReloadNext", (req, res) -> renderContent("pl/itgolo/libs/chromium/Actions/clickReloadNext.html"));
        Spark.get("/clickReloadTimeout", (req, res) ->{
            NavigateToAction.sleep(8000);
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
        browser.actions.navigateTo(sparkUrl("clickReload"));
        browser.actions.clickReload("#button", 45);
        String nextContent = browser.actions.getElement("#next").html();
        Assert.assertTrue(nextContent.equals("Next content"));
        Boolean hasException = false;
        try {
            browser.actions.clickReload("#timeout", 4);
        } catch (Exception e) {
        hasException = true;
        }
        Assert.assertTrue(hasException);
    }

    /**
     * After all.
     */
    @Override
    public void afterAll() {

    }
}
