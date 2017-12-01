package pl.itgolo.libs.skeletongradle.Extensions;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 30.11.2017
 * Time: 22:07
 * Project name: skeletongradle
 *
 * @author Karol Golec <karol.rebigo@gmail.com>
 */
public class GreetingPluginExtension {
    String message = "Hello from GreetingPlugin";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
