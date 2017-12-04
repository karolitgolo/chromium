package pl.itgolo.libs.skeletongradle;

import org.gradle.testkit.runner.BuildResult;
import org.junit.Assert;
import org.junit.Test;
import pl.itgolo.libs.skeletongradle.Abstract.PluginFunctionalTest;

import java.util.HashMap;
import java.util.Map;

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
    public void systemOutResultTaskHello() throws Exception {
        BuildResult result = createGradleRunner("src/functional-test/resources/pl/itgolo/libs/skeletongradle/templateBuild.gradle")
                .withArguments("hello", "--stacktrace")
                .withDebug(true)
                .build();
        Assert.assertTrue(result.getOutput().contains("BUILD SUCCESSFUL"));
    }

    @Test
    public void extensionGreetingPluginWithPropertyInBuildGradleFile() throws Exception {
        Map<Object, Object> templateProps = new HashMap<>();
        templateProps.put("greetingMessage", "Hi from Gradle");
        BuildResult result = createGradleRunner("src/functional-test/resources/pl/itgolo/libs/skeletongradle/templateBuildWithExtension.gradle", templateProps)
                .withArguments("hello", "--stacktrace")
                .withDebug(true)
                .build();
        Assert.assertTrue(result.getOutput().contains("Hi from Gradle"));
    }

    @Test
    public void taskGreetingExecuteFromBuildGradleFile() throws Exception {
        BuildResult result = createGradleRunner("src/functional-test/resources/pl/itgolo/libs/skeletongradle/templateBuildWithTask.gradle")
                .withArguments("helloTask", "--stacktrace")
                .withDebug(true)
                .build();
        Assert.assertTrue(result.getOutput().contains("greetings from GreetingTask"));
    }

    @Test
    public void taskGreetingWithoutBuildGradle() throws Exception {
        BuildResult result = createGradleRunner("src/functional-test/resources/pl/itgolo/libs/skeletongradle/templateBuild.gradle")
                .withArguments("hello2", "--stacktrace")
                .withDebug(true)
                .build();
        Assert.assertTrue(result.getOutput().contains("greeting from GreetingTaskWithoutBuildGradle"));
    }
}