# Java Gradle Multi-project

Spring Boot **Gradle multi-project** for **Java 17 only**.

Layout inspired by [mrclrchtr/gradle-kotlin-spring](https://github.com/mrclrchtr/gradle-kotlin-spring)
(`buildSrc`, version catalog, multi-module) with connected service modules in the spirit of
[nihadamirov/spring-boot-microservices](https://github.com/nihadamirov/spring-boot-microservices) —
implemented as **Java** + Spring Boot **3.2** (not a copy of those repositories).

## Requirements

- JDK **17** (see `.sdkmanrc`)
- Gradle Wrapper (`./gradlew` / `gradlew.bat`)

## Project structure

```
.
├── buildSrc/                 Shared Gradle conventions (like reference)
├── gradle/libs.versions.toml Version catalog
├── common/                   Shared Java library
├── product-service/          Spring Boot REST API
├── order-service/            Spring Boot REST API
├── config/                   Checkstyle / PMD / SpotBugs
├── documentation/
├── scripts/ck|git|tools/
├── settings.gradle.kts
└── gradlew / gradlew.bat
```

## Building from source

```bash
./gradlew clean test
./gradlew :product-service:bootRun
./gradlew :order-service:bootRun
```

Windows:

```bat
gradlew.bat clean test
```

## Quality tools

See `documentation/TOOLS.md`. All of: CK, CPD, Checkstyle, Git, JaCoCo,
OWASP-Dependency-Check, PIT, PMD, SpotBugs, Static-DU-JaCoCo-composite, diff-cover.

```bash
bash scripts/tools/run_tools.sh
```

## License

Apache License 2.0 — see `LICENSE.txt`.