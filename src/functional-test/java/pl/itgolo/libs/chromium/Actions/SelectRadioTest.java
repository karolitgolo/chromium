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
public class SelectRadioTest extends BrowserTest implements IBrowserTest {

    /**
     * Before.
     */
    @Override
    public void before() {
        Spark.get("/selectRadio", (req, res) -> renderContent("pl/itgolo/libs/chromium/Actions/selectRadio.html"));
    }

    /**
     * Test.
     *
     * @param browser the browser
     */
    @Override
    public void test(Browser browser) throws ChromiumException {
        browser.actions.navigateTo(sparkUrl("selectRadio"));
        browser.actions.selectRadio("input[name='gender']", "m");
        browser.actions.selectRadio("input[name='gender']", "m");
        browser.actions.selectRadio("input[name='gender']", "o");
        Boolean hasException = false;
        try {
            browser.actions.selectRadio("input[name='gender']", "z");
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
