# Skeleton Gradle App
In this skeleton, you can fast create app of Java.

## Create my new Gradle App
- Download skeleton ```git clone git@github.com:karolitgolo/skeletongradle.git```
- git remote set-url origin ```git@github.com:user/repo.git```
- Update file ```gradle.properties```
- Update file ```settings.gradle```
- Update name artifact in packages
- Remove ```example``` from file name ```env.properties.example``` and configuration this
- Update file ```build.gradle```
- Configuration file ```env.properties```
- Write my code of application
- Add tests

## Publish package in Bintray

For publish package in Bintray use in gradle ```publishAppBintray```.
In this command run be all tests unit, integration and functional.

## Deploy app new version

First change version application in ```gradle.properties``` file.

New version application will be upload to server FTP by command gradle
```publishNewVersionApp```. Gradle before run tasks for tests unit,
integration and functional.

