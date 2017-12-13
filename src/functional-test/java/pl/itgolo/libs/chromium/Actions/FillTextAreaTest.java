package pl.itgolo.libs.chromium.Actions;

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
public class FillTextAreaTest extends BrowserTest implements IBrowserTest {

    /**
     * Before.
     */
    @Override
    public void before() {
        Spark.get("/fillTextArea", (req, res) -> renderContent("pl/itgolo/libs/chromium/Actions/fillTextArea.html"));
    }

    /**
     * Test.
     *
     * @param browser the browser
     */
    @Override
    public void test(Browser browser) throws ChromiumException {
        browser.actions.navigateTo(sparkUrl("fillTextArea"));
        browser.actions.fillTextArea("#username","new content");
    }

    /**
     * After all.
     */
    @Override
    public void afterAll() {

    }
}
