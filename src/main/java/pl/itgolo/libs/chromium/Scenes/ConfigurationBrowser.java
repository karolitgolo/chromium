package pl.itgolo.libs.chromium.Scenes;

import lombok.Data;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 14.12.2017
 * Time: 11:24
 * Project name: chromium
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
@Data
public class ConfigurationBrowser {

    private Boolean enableGeolocation;

    private Boolean enableVisible;

    private Boolean enableJSDialog;

    public ConfigurationBrowser() {
        this.enableGeolocation = true;
        this.enableVisible = true;
        this.enableJSDialog = true;
    }
}
