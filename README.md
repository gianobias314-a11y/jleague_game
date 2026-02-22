# J.League Card Match Prototype (Java)

This is now a **one-click playable desktop prototype** for a J.League-themed soccer card game.

## What you can do
- Pick the currently controlled player.
- See live weighted draw odds for that player.
- Draw action cards into a match log.
- Start a fresh kickoff (clear log) and play again.

## Run (one command)
```bash
./run.sh
```

`run.sh` compiles everything and launches the desktop game window.
If no GUI display is available, it automatically falls back to console mode.

## Manual alternative
```bash
javac -d out $(find src/main/java -name "*.java")
java -cp out com.jleaguecardgame.GameLauncher
```

## Core mechanic
- Each player has ability ratings per action type (`SHORT_PASS`, `THROUGH_PASS`, `SHOT`, etc.).
- Players can also have skills (`PLAYMAKER`, `CLUTCH_FINISHER`, etc.) that add action-specific bonuses.
- Card draw probability is weighted from ability + skill bonuses, so better passers/finishers naturally draw more related cards.
