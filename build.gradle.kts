plugins {
    idea
}

idea {
    module.isDownloadJavadoc = true
    module.isDownloadSources = true
}

tasks.wrapper {
    gradleVersion = "8.14.3"
    distributionType = Wrapper.DistributionType.BIN
}

tasks.register("qualityAll") {
    group = "verification"
    description = "Runs tests and verification across all modules"
    dependsOn(subprojects.map { it.path + ":check" })
}