#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$ROOT_DIR"

OUT_DIR="out"
GUI_CLASS="com.jleaguecardgame.GameLauncher"
CLI_CLASS="com.jleaguecardgame.Main"

mkdir -p "$OUT_DIR"

echo "Compiling Java sources..."
javac -d "$OUT_DIR" $(find src/main/java -name "*.java")

if [[ -n "${DISPLAY:-}" || "${OSTYPE:-}" == darwin* || "${OS:-}" == "Windows_NT" ]]; then
  echo "Launching desktop game window..."
  java -cp "$OUT_DIR" "$GUI_CLASS"
else
  echo "No GUI display detected. Running console mode instead..."
  java -cp "$OUT_DIR" "$CLI_CLASS"
  if [[ -t 0 ]]; then
    echo
    read -r -p "Program finished. Press Enter to close..." _
  fi
fi
