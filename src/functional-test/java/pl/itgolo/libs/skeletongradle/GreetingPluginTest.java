package pl.itgolo.libs.skeletongradle;

import org.gradle.testkit.runner.BuildResult;
import org.junit.Assert;
import org.junit.Test;
import pl.itgolo.libs.skeletongradle.Abstract.PluginFunctionalTest;

/**
 * The type Greeting plugin test.
 */
public class GreetingPluginTest extends PluginFunctionalTest {

    /**
     * Display system out result task hello.
     *
     * @throws Exception the exception
     */
    @Test
    public void displaySystemOutResultTaskHello() throws Exception {
        BuildResult result = createGradleRunner("pl/itgolo/libs/skeletongradle/templateBuild.gradle")
                .withArguments("hello", "--stacktrace")
                .withDebug(true)
                .build();
        Assert.assertTrue(result.getOutput().contains("BUILD SUCCESSFUL"));
    }
}