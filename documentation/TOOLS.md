# Integrated quality tools (Java 8 Gradle multi-project)

Tools are wired via `buildSrc` conventions + `config/` + `scripts/` across all modules.

| Tool | Location | Command |
|------|----------|---------|
| Checkstyle | `config/checkstyle/` + Gradle plugin | `./gradlew checkstyleMain` |
| PMD | `config/pmd/ruleset.xml` + Gradle plugin | `./gradlew pmdMain` |
| CPD | custom `cpdCheck` task (PMD CPD) | `./gradlew cpdCheck` |
| SpotBugs | `config/spotbugs/` + Gradle plugin | `./gradlew spotbugsMain` |
| JaCoCo | Gradle plugin | `./gradlew test jacocoTestReport` |
| PIT | Gradle plugin | `./gradlew pitest` |
| OWASP-Dependency-Check | Gradle plugin | `./gradlew dependencyCheckAnalyze` |
| diff-cover | CLI over JaCoCo XML (`scripts/tools`) | `bash scripts/tools/run_tools.sh` |
| CK | `scripts/ck/run_ck.sh` | `bash scripts/ck/run_ck.sh` |
| Git | `scripts/git/git_churn.py` | `python scripts/git/git_churn.py` |
| Static-DU-JaCoCo-composite | `config/pmd/static-du-ruleset.xml` + task | `./gradlew staticDuJacocoComposite` |

Java toolchain: Gradle Java toolchain `languageVersion=8` / `options.release=8`.