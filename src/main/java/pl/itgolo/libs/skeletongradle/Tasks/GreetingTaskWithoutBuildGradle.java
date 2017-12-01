package pl.itgolo.libs.skeletongradle.Tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 30.11.2017
 * Time: 22:40
 * Project name: skeletongradle
 *
 * @author Karol Golec <karol.rebigo@gmail.com>
 */
public class GreetingTaskWithoutBuildGradle extends DefaultTask {

    public String message = "hello from GreetingTaskWithoutBuildGradle";

    @TaskAction
    void greet(){
        System.out.println(message);
    }
}
