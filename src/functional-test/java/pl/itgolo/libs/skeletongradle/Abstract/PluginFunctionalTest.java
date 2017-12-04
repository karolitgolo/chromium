package pl.itgolo.libs.skeletongradle.Abstract;

import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Properties;

/**
 * The type Plugin functional test.
 */
public abstract class PluginFunctionalTest {


    /**
     * The Test build gradle file.
     */
    protected File testBuildGradleFile;

    /**
     * The Props.
     */
    protected Properties props;

    /**
     * The Env.
     */
    protected Properties env;

    /**
     * The Test project dir.
     */
    @Rule
    public final TemporaryFolder testProjectDir = new TemporaryFolder();

    /**
     * Sets .
     *
     * @throws IOException        the io exception
     * @throws URISyntaxException the uri syntax exception
     */
    @Before
    public void setup() throws IOException, URISyntaxException {
        testBuildGradleFile = testProjectDir.newFile("build.gradle");
        props = new Properties();
        props.load(new FileInputStream("gradle.properties"));
        if (Files.exists(Paths.get("env.properties"))){
            env = new Properties();
            env.load(new FileInputStream("env.properties"));
        }
        publishToMavenLocal();
    }

    /**
     * Build from template resource string.
     *
     * @param resource the resource
     * @param props    the props
     * @return the string
     * @throws IOException the io exception
     */
    protected static String buildFromTemplateResource(String resource, Properties props) throws IOException {
        String path = URLDecoder.decode(new File(resource).getCanonicalPath(), "UTF-8");
        String template =  new String(Files.readAllBytes(Paths.get(path)));
        for (Map.Entry<Object, Object> propertyEntry : props.entrySet()) {
            String key = propertyEntry.getKey().toString();
            String value = propertyEntry.getValue().toString();
            if (template.contains("{{" + key + "}}")){
                template = template.replace("{{" + key + "}}", value);
            }
        }
        if (template.contains("{{")){
            throw new IOException("Found `{{` characters in content from template");
        }
        return template;
    }


    /**
     * Publish to maven local build result.
     *
     * @return the build result
     */
    protected static BuildResult publishToMavenLocal() {
        BuildResult result =  GradleRunner.create().withProjectDir(new File(".")).withArguments("publishToMavenLocal").build();
        return result;
    }

    /**
     * Create temp build gradle file path.
     *
     * @param resource the resource
     * @return the path
     * @throws IOException the io exception
     */
    protected Path createTempBuildGradleFile(String resource) throws IOException {
        String templateBuild = buildFromTemplateResource(resource, props);
        return Files.write(Paths.get(testBuildGradleFile.getPath()), templateBuild.getBytes(), StandardOpenOption.WRITE);
    }

    /**
     * Create gradle runner gradle runner.
     *
     * @param resource the resource
     * @return the gradle runner
     * @throws IOException the io exception
     */
    public GradleRunner createGradleRunner(String resource) throws IOException {
        createTempBuildGradleFile(resource);
        return GradleRunner.create().withProjectDir(testProjectDir.getRoot());
    }

    /**
     * Create gradle runner gradle runner.
     *
     * @param resource   the resource
     * @param properties the properties
     * @return the gradle runner
     * @throws IOException the io exception
     */
    public GradleRunner createGradleRunner(String resource, Map<Object, Object> properties) throws IOException {
        for (Map.Entry<Object, Object> propertyEntry : properties.entrySet()) {
            String key = (String) propertyEntry.getKey();
            String value = (String) propertyEntry.getValue();
            props.setProperty(key, value);
        }
        createTempBuildGradleFile(resource);
        return GradleRunner.create().withProjectDir(testProjectDir.getRoot());
    }

    /**
     * Props to map map.
     *
     * @param file the file
     * @param map  the map
     * @return the map
     * @throws IOException the io exception
     */
    protected Map<Object, Object> propsToMap(String file, Map<Object, Object> map) throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream(file));
        for (Map.Entry<Object, Object> propertyEntry : props.entrySet()) {
            String key = propertyEntry.getKey().toString();
            String value = propertyEntry.getValue().toString();
            map.put(key, value);
        }
        return map;
    }
}
