import org.gradle.api.Plugin
import org.gradle.api.Project
import java.net.URL

class SystemJarsPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.logger.info("Applying SystemJarsPlugin")

        // Get android.jar resource URL from plugin classloader
        val androidJarUrl: URL? = this::class.java.classLoader.getResource("android.jar")
        
        if (androidJarUrl != null) {
            // Create file reference directly from the resource URL
            val androidJarRef = project.files(androidJarUrl.toURI())
            
            // Add to compileOnly configuration
            project.dependencies.add("compileOnly", androidJarRef)
            project.logger.info("Added android.jar to compileOnly configuration from plugin resources")
        } else {
            project.logger.error("android.jar not found in plugin resources. Plugin will not be applied.")
        }
    }
}
