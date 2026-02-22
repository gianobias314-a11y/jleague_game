#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$ROOT_DIR"

OUT_DIR="out"
MAIN_CLASS="com.jleaguecardgame.Main"

mkdir -p "$OUT_DIR"

echo "Compiling Java sources..."
javac -d "$OUT_DIR" $(find src/main/java -name "*.java")

echo "Running ${MAIN_CLASS}..."
java -cp "$OUT_DIR" "$MAIN_CLASS"

# Keep terminal open when launched interactively (e.g., double-click / manual shell use).
if [[ -t 0 ]]; then
  echo
  read -r -p "Program finished. Press Enter to close..." _
fi
