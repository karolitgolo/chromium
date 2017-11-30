package pl.itgolo.libs.skeletongradle;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * The type Greeting plugin.
 */
public class GreetingPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.task("hello").doLast((task) -> {
            System.out.println("Hello {} 123");
        });
    }
}
