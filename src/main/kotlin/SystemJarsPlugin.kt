import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File
import java.io.InputStream

class SystemJarsPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.logger.info("Applying SystemJarsPlugin")

        // This isn't as elegant as it could be, but we want an easy to deploy option
        // Get android.jar resource stream
        val inputStream: InputStream? = this::class.java.classLoader.getResourceAsStream("android.jar")
        if (inputStream != null) {
            // Create temporary directory
            val tempDir = File(project.buildDir, "system-jars")
            tempDir.mkdirs()
            
            // Create temporary file
            val tempFile = File(tempDir, "android.jar")
            tempFile.outputStream().use { out ->
                inputStream.copyTo(out)
            }
            
            project.afterEvaluate {
                project.tasks.withType(org.gradle.api.tasks.compile.JavaCompile::class.java).configureEach {
                    options.compilerArgs.add("-Xbootclasspath/p:${tempFile.absolutePath}")
                }
                project.logger.info("Prepended custom android.jar to JavaCompile bootclasspath")
            }
        } else {
            project.logger.error("android.jar not found in plugin resources. Plugin will not be applied.")
        }
    }
}
