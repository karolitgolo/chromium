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
public class ExecuteScriptTest extends BrowserTest implements IBrowserTest {

    /**
     * Before.
     */
    @Override
    public void before() {
        Spark.get("/executeScript", (req, res) -> renderContent("pl/itgolo/libs/chromium/Actions/executeScript.html"));
    }

    /**
     * Test.
     *
     * @param browser the browser
     */
    @Override
    public void test(Browser browser) throws ChromiumException {
        browser.actions.navigateTo(sparkUrl("executeScript"));
        String returnJS = browser.actions.executeScript("", "'test'");
        Assert.assertTrue(returnJS.equals("test"));
        String returnJS2 = browser.actions.executeScript("var example = 'exampleMy';", "example");
        Assert.assertTrue(returnJS2.equals("exampleMy"));
        Element element = browser.actions.getElement("#container");
        Assert.assertTrue(element.text().equals("old content"));
        browser.actions.executeScript("document.querySelector(\"#container\").innerHTML = \"new content\";");
        Element element2 = browser.actions.getElement("#container");
        Assert.assertTrue(element2.text().equals("new content"));
    }

    /**
     * After all.
     */
    @Override
    public void afterAll() {

    }
}
