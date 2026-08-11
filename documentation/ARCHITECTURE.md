# Architecture

Java 25 Gradle multi-project Spring Boot application, inspired by the modular layout of
[mrclrchtr/gradle-kotlin-spring](https://github.com/mrclrchtr/gradle-kotlin-spring)
(`buildSrc` conventions, version catalog, multi-module) and connected service modules similar to
[nihadamirov/spring-boot-microservices](https://github.com/nihadamirov/spring-boot-microservices) —
implemented in **Java** for JDK 25 (not a copy of either repo).

## Modules (connected)

| Module | Role | Port |
|--------|------|------|
| `common` | Shared library (`Money`, `SkuUtils`) used by services | — |
| `product-service` | Spring Boot product REST API (depends on `common`) | 8082 |
| `order-service` | Spring Boot order REST API (depends on `common`) | 8083 |

Build logic lives in `buildSrc` (`java-conventions`, `spring-conventions`, `testing-conventions`, `quality-conventions`).