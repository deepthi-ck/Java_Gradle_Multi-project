# Contributing

This branch targets **Java 8** only (release=8, bytecode major 52).

1. Use JDK 8 (see .sdkmanrc), or a newer JDK that can emit Java 8 bytecode.
2. Run ./gradlew clean test before opening a PR.
3. Keep quality tool configs under config/ and scripts/ in sync with documentation/TOOLS.md.