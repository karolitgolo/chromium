package pl.itgolo.libs.chromium.Interfaces;

import pl.itgolo.libs.chromium.Browser;

/**
 * The interface Browser test.
 */
public interface IBrowserTest {

    /**
     * Before.
     *
     * @throws Exception the exception
     */
    void before() throws Exception;

    /**
     * Test.
     *
     * @param browser the browser
     * @throws Exception the exception
     */
    void test(Browser browser) throws Exception;

    /**
     * After all.
     *
     * @throws Exception the exception
     */
    void afterAll() throws Exception;
}
