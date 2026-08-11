plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(libs.springBoot.gradlePlugin)
    implementation(libs.spring.dependencyManagementPlugin)
    implementation(libs.spotbugs.gradlePlugin)
    implementation(libs.dependencyCheck.gradlePlugin)
}
