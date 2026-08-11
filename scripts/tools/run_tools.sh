#!/usr/bin/env bash
set -euo pipefail
ROOT="$(cd "$(dirname "$0")/../.." && pwd)"
cd "$ROOT"
./gradlew --no-daemon clean test jacocoTestReport
./gradlew --no-daemon checkstyleMain pmdMain cpdCheck spotbugsMain || true
./gradlew --no-daemon pitest || true
./gradlew --no-daemon dependencyCheckAnalyze || true
./gradlew --no-daemon staticDuJacocoComposite || true
if command -v diff-cover >/dev/null 2>&1; then
  find . -path "*/build/reports/jacoco/test/jacocoTestReport.xml" -print0 | xargs -0 -I{} diff-cover {} --compare-branch origin/Java-25 || true
else
  echo "diff-cover CLI not installed; skip (pip install diff-cover)"
fi
python scripts/git/git_churn.py || true
bash scripts/ck/run_ck.sh || true
echo "Tool pipeline finished (Java 25 Gradle multi-project)."