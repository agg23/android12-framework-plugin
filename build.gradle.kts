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
}

group = "com.github.agg23"
version = "1.0.4"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions.jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8)
}

gradlePlugin {
    plugins {
        create("systemJars") {
            id = "im.agg.android12-system-jars"
            implementationClass = "SystemJarsPlugin"
        }
    }
}
