# J.League Card Match Prototype (Java)

This prototype shows a **soccer-themed card system** where the action cards drawn are influenced by the currently controlled player.

## Core idea
- Each player has ability ratings per action type (`SHORT_PASS`, `THROUGH_PASS`, `SHOT`, etc.).
- Each player also has tactical/technical skills (`PLAYMAKER`, `CLUTCH_FINISHER`, etc.).
- A weighted deck computes draw probability based on:
  - base weight from ability rating
  - additive bonus from active skills

So a great passer with playmaker traits gets more pass cards, while a finisher gets more shot cards.

## Run
```bash
./run.sh
```

(Manual alternative)
```bash
javac -d out $(find src/main/java -name "*.java")
java -cp out com.jleaguecardgame.Main
```

## Extend next
- Attach stamina/form modifiers to change weights during a match.
- Add event context (counterattack, set piece, defensive third) as extra multipliers.
- Add card rarity tiers and combo effects.
