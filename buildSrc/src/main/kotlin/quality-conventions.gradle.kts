plugins {
    id("java-conventions")
    id("testing-conventions")
    checkstyle
    pmd
    id("com.github.spotbugs")
    id("org.owasp.dependencycheck")
}

checkstyle {
    toolVersion = "10.12.7"
    configFile = rootProject.file("config/checkstyle/checkstyle.xml")
    isIgnoreFailures = false
}

pmd {
    toolVersion = "6.55.0"
    isConsoleOutput = true
    ruleSetFiles = files(rootProject.file("config/pmd/ruleset.xml"))
    ruleSets = emptyList()
}

spotbugs {
    toolVersion.set("4.8.6")
    excludeFilter.set(rootProject.file("config/spotbugs/exclude.xml"))
    ignoreFailures.set(false)
}

tasks.spotbugsMain {
    reports.create("html") {
        required.set(true)
    }
}

dependencyCheck {
    failBuildOnCVSS = 11f
    formats = listOf("HTML", "JSON")
}

tasks.named("check") {
    dependsOn("jacocoTestCoverageVerification")
}

tasks.register("staticDuJacocoComposite") {
    group = "verification"
    description = "Static-DU-JaCoCo-composite: UnusedAssignment ruleset + JaCoCo report"
    dependsOn("jacocoTestReport")
    doLast {
        val rules = rootProject.file("config/pmd/static-du-ruleset.xml")
        require(rules.exists()) { "Missing ${rules.path}" }
        logger.lifecycle("Static-DU-JaCoCo-composite ready for ${project.name}")
    }
}

val cpdConfiguration = configurations.create("cpdTool")
dependencies {
    "cpdTool"("net.sourceforge.pmd:pmd-dist:6.55.0")
}

tasks.register<JavaExec>("cpdCheck") {
    group = "verification"
    description = "CPD copy-paste detection via PMD CPD"
    classpath = cpdConfiguration
    mainClass.set("net.sourceforge.pmd.cpd.CPD")
    val mainSrc = layout.projectDirectory.dir("src/main/java").asFile
    args(
        "--minimum-tokens", "50",
        "--language", "java",
        "--files", mainSrc.absolutePath
    )
    isIgnoreExitValue = true
    onlyIf { mainSrc.exists() }
}

val pitestConfiguration = configurations.create("pitestTool")
dependencies {
    "pitestTool"("org.pitest:pitest-command-line:1.17.0")
    "pitestTool"("org.pitest:pitest-junit5-plugin:1.2.1")
}

tasks.register<JavaExec>("pitest") {
    group = "verification"
    description = "Run PIT mutation tests"
    dependsOn("testClasses")
    classpath = pitestConfiguration
    mainClass.set("org.pitest.mutationtest.commandline.MutationCoverageReport")
    isIgnoreExitValue = true
    doFirst {
        val reportDir = layout.buildDirectory.dir("reports/pitest").get().asFile
        reportDir.mkdirs()
        val cp = (
            configurations.getByName("testRuntimeClasspath") +
                sourceSets["main"].output +
                sourceSets["test"].output
            ).asPath
        args = listOf(
            "--reportDir", reportDir.absolutePath,
            "--targetClasses", "com.example.*",
            "--targetTests", "com.example.*",
            "--sourceDirs", file("src/main/java").absolutePath,
            "--classPath", cp
        )
    }
}
