# Skeleton Gradle Plugin
In this skeleton, you can fast create plugin of Gradle.

## Create my new Gradle Plugin
- Download skeleton ```git clone git@github.com:karolitgolo/skeletongradle.git```
- Update file ```gradle.properties```
- Update file ```settings.gradle```
- Remove ```example``` from file name ```env.properties.example``` and configuration this
- Add dependencies to ```gradle/dependencies.gradle``` file
- Instead in ```build.gradle``` file, configure of my application in ```gradle/applyToBuild.gradle``` file
- Write my code of application
- Add tests
- Deploy plugin ```gradle publishPluginJCenter``` to ```jCenter``` repository or
```gradle publishPluginBintray``` for ```bintray``` repository.