package pl.itgolo.libs.chromium.Abstracts;

import spark.Spark;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

/**
 * The type Action test.
 */
public class BrowserTest {

    /**
     * Spark url string.
     *
     * @param path the path
     * @return the string
     */
    protected String sparkUrl(String path) {
        return String.format("http://localhost:%1$s/%2$s", Spark.port(), path);
    }

    /**
     * Render content string.
     *
     * @param htmlFile the html file
     * @return the string
     */
    protected String renderContent(String htmlFile) {
        try {
            File file = new File("src/functional-test/resources/" + htmlFile);
            return new String(Files.readAllBytes(file.toPath()), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
