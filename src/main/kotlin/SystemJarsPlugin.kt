import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
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
            
            // Add to compileOnly configuration
            project.dependencies.add("compileOnly", project.files(tempFile))
            project.logger.info("Added android.jar to compileOnly configuration from temporary file")
        } else {
            project.logger.error("android.jar not found in plugin resources. Plugin will not be applied.")
        }
    }
}
