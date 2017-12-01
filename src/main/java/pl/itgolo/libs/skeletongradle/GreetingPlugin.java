package pl.itgolo.libs.skeletongradle;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import pl.itgolo.libs.skeletongradle.Extensions.GreetingPluginExtension;
import pl.itgolo.libs.skeletongradle.Tasks.GreetingTaskWithoutBuildGradle;

/**
 * The type Greeting plugin.
 */
public class GreetingPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        GreetingPluginExtension greetingPluginExtension = project.getExtensions().create("greeting", GreetingPluginExtension.class);
        project.task("hello").doLast((task) -> {
            System.out.println("Hello {} 123");
            System.out.println(greetingPluginExtension.getMessage());
        });
        GreetingTaskWithoutBuildGradle task = project.getTasks().create("hello2", GreetingTaskWithoutBuildGradle.class);
        task.message = "greeting from GreetingTaskWithoutBuildGradle";
    }
}
