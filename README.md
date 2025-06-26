# Gradle plugin for Android 12 android.jar injection

A simple Gradle plugin for automatically adding the Android 12 `android.jar` as a compile only dependency. This allows building against private and system level APIs with ease in Android Studio. `android.jar` sourced from https://github.com/Reginer/aosp-android-jar.

## Usage

```kts
// settings.gradle.kts

pluginManagement {
    repositories {
        maven {
            url = uri("https://jitpack.io")
        }
        ...
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "im.agg.android12-system-jars") {
                useModule("com.github.agg23.android12-framework-plugin:android12-system-jars-plugin:${requested.version}")
            }
        }
    }
}
```

```kts
// build.gradle.kts

plugins {
    // NOTE: This plugin must be preceeded by either the Kotlin or Java plugin
    id("im.agg.android12-system-jars") version "1.0.2"
}
```
