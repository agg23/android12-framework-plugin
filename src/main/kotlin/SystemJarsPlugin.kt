import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler

class SystemJarsPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.logger.info("Applying SystemJarsPlugin")

        val libsDir = project.rootProject.file("libs")
        val androidJar = libsDir.resolve("android.jar")

        if (androidJar.exists()) {
            project.dependencies.add("compileOnly", project.files(androidJar))
            project.logger.info("Added android.jar to compileOnly configuration")
        } else {
            project.logger.warn("android.jar not found in libs directory. Plugin will not be applied.")
        }
    }
}
