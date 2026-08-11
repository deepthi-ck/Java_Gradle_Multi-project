plugins {
    id("spring-conventions")
}

dependencies {
    implementation(project(":common"))
}

tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    archiveBaseName.set("order-service")
}