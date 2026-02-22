#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$ROOT_DIR"

OUT_DIR="out"

mkdir -p "$OUT_DIR"

javac -d "$OUT_DIR" $(find src/main/java -name "*.java")
java -cp "$OUT_DIR" com.jleaguecardgame.Main
