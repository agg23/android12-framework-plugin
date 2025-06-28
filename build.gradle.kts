plugins {
    `kotlin-dsl`
}

// Add resources to the plugin JAR
tasks.jar {
    from(fileTree("libs") {
        include("android.jar")
    })
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    compileOnly("com.android.tools.build:gradle:8.1.0")
    compileOnly(files("libs/android.jar"))
}

group = "com.github.agg23"
version = "1.0.2"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions.jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
}

gradlePlugin {
    plugins {
        create("systemJars") {
            id = "im.agg.android12-system-jars"
            implementationClass = "SystemJarsPlugin"
        }
    }
}
