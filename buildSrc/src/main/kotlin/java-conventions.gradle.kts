plugins {
    java
}

val jdk25Home: File = run {
    val fromEnv = sequenceOf("JAVA_25_HOME", "JAVA_HOME_25_X64")
        .mapNotNull { System.getenv(it) }
        .map { file(it) }
        .firstOrNull { it.exists() }
    val candidates = listOf(
        file("C:/Program Files/Microsoft/jdk-25.0.4.7-hotspot"),
        file("C:/Users/Deepthi/tools/jdk-25")
    )
    fromEnv ?: candidates.firstOrNull { it.exists() }
        ?: error("JDK 25 required: set JAVA_25_HOME (Gradle itself should run on JDK 21)")
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.release.set(25)
    options.isFork = true
    options.forkOptions.javaHome = jdk25Home
}

repositories {
    mavenCentral()
}

tasks.test {
    useJUnitPlatform()
    systemProperty("net.bytebuddy.experimental", "true")
    val javaBin = if (System.getProperty("os.name").lowercase().contains("windows")) "java.exe" else "java"
    executable = jdk25Home.resolve("bin/$javaBin").absolutePath
}