package pl.itgolo.libs.skeletongradle;

import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.testfixtures.ProjectBuilder;
import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.gradle.tooling.BuildLauncher;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

import static org.junit.Assert.*;

/**
 * The type Greeting plugin test.
 */
public class GreetingPluginTest {

    /**
     * Apply.
     *
     * @throws Exception the exception
     */
    @Test
    public void apply() throws Exception {
        Project project = ProjectBuilder.builder().withName("skeleton-gradle").build();
        project.getPluginManager().apply(GreetingPlugin.class);
        Assert.assertNotNull(project.getPlugins().hasPlugin(GreetingPlugin.class));
    }

    @Rule
    public TemporaryFolder testProjectDir = new TemporaryFolder();

    @Test
    public void displaySystemOutResultTaskHello() throws Exception {
        URL resources = GreetingPluginTest.class.getClassLoader().getResource("pl/itgolo/libs/skeletongradle/GreetingPlugin.class");
       File buildFile = testProjectDir.newFile("build.gradle");
        //File buildFile = testProjectDir.newFile("build.gradle");
        String contentBuildFile = "plugins {" +
                //"id 'com.palantir.idea-test-fix' version '0.1.0' " +
                "id 'pl.itgolo.libs.skeletongradle'" +
                "}";
        Files.write(Paths.get(buildFile.getPath()), contentBuildFile.getBytes(), StandardOpenOption.WRITE);
        BuildResult result = GradleRunner.create()
                .withProjectDir(testProjectDir.getRoot())
                .withPluginClasspath(Arrays.asList(new File(resources.toString())))
                .withArguments("hello")
                .build();
        System.out.println(result.getOutput());
    }


}