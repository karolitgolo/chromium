# Chromium Windows XP Java Library
This package it works with architecture processor 32 and 64 bit.
Library automation match bin files Jcef to architecture processor.

This library launch Jcef in swing jFrame. This library add actions
for operations in website, example: fill input, get element, select checkbox.

## Add dependencies
For start work with this library, add dependencies in ```build.gradle```

```
repositories {
    maven {
        url "http://dl.bintray.com/itgolo/libs"
    }
}
dependencies {
    compile 'pl.itgolo.libs:chromium:${latestVersion}'
}
```

This library ise Lombok library. Intelij IDEA require in setting
```Enable annotation processing```.

## Create browser
For run browser use code:

```
Browser browser = new Browser();
```

## Configurations
For configuration browser use code:

```
ConfigurationBrowser config = new ConfigurationBrowser();
config.setEnableGeolocation(false);
config.setEnableJSDialog(false);
Browser browser = new Browser(config);
```

All options of configuration exist in file ```ConfigurationBrowser.java```

You can hide and show jFrame of browser by:

```
browser.visible.hide();
browser.visible.show();
```

## Actions browser

All actions exist in package ```pl.itgolo.libs.chromium.Actions```.

Example run action for navigate to website and wait for loaded:
```
browser.actions.navigateTo("http://host");
```

## Publish package in Bintray

For publish package in Bintray use in gradle ```publishLibBintray```.
In this command run be all tests unit, integration and functional.

## Include licenses
Chromium, gluegen, jogl required licenses include to external
application.

## Log
In your application can be use internal log service for debug and other.
If you want use, add for begin launching application:

```
ILogOut logOutConsole = new LogOutConsole();
ILogOut logOutFile = new LogOutFile(new File("build/logChromium.txt"));
LogService.logOuts.addAll(Arrays.asList(logOutConsole, logOutFile));
```

You can log by:
```
LogService.debug("debug");
LogService.warn("warn");
LogService.error("error");
LogService.info("info");
```

You can write other logout by implement ```ILogOut``` interface.