package com.jleaguecardgame;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class WeightedActionDeck {
    private final Random random;
    private final Map<ActionType, List<ActionCard>> cardPool;

    public WeightedActionDeck(long seed) {
        this.random = new Random(seed);
        this.cardPool = Map.of(
            ActionType.SHORT_PASS, List.of(
                new ActionCard(ActionType.SHORT_PASS, "One-Touch Triangle", 2),
                new ActionCard(ActionType.SHORT_PASS, "Rhythm Setter", 3)
            ),
            ActionType.THROUGH_PASS, List.of(
                new ActionCard(ActionType.THROUGH_PASS, "Laser Through Ball", 4),
                new ActionCard(ActionType.THROUGH_PASS, "Split-the-Lines Pass", 5)
            ),
            ActionType.CROSS, List.of(
                new ActionCard(ActionType.CROSS, "Far-Post Cross", 3),
                new ActionCard(ActionType.CROSS, "Low Driven Cross", 4)
            ),
            ActionType.DRIBBLE, List.of(
                new ActionCard(ActionType.DRIBBLE, "Elusive Feint", 3),
                new ActionCard(ActionType.DRIBBLE, "Touchline Burst", 4)
            ),
            ActionType.SHOT, List.of(
                new ActionCard(ActionType.SHOT, "Composed Finish", 4),
                new ActionCard(ActionType.SHOT, "Top Corner Strike", 5)
            ),
            ActionType.PRESS, List.of(
                new ActionCard(ActionType.PRESS, "Coordinated Press", 3),
                new ActionCard(ActionType.PRESS, "Counter-Press Trap", 4)
            ),
            ActionType.TACKLE, List.of(
                new ActionCard(ActionType.TACKLE, "Clean Standing Tackle", 3),
                new ActionCard(ActionType.TACKLE, "Last-Ditch Block", 5)
            )
        );
    }

    public ActionCard draw(PlayerProfile controlledPlayer) {
        double totalWeight = 0.0;
        for (ActionType type : ActionType.values()) {
            totalWeight += controlledPlayer.weightFor(type);
        }

        double roll = random.nextDouble() * totalWeight;
        double cumulative = 0.0;
        ActionType selected = ActionType.SHORT_PASS;

        for (ActionType type : ActionType.values()) {
            cumulative += controlledPlayer.weightFor(type);
            if (roll <= cumulative) {
                selected = type;
                break;
            }
        }

        List<ActionCard> variants = cardPool.get(selected);
        return variants.get(random.nextInt(variants.size()));
    }

    public String oddsReport(PlayerProfile player) {
        double total = 0.0;
        for (ActionType type : ActionType.values()) {
            total += player.weightFor(type);
        }

        final double totalWeight = total;

        return "Draw Odds for " + player + "\n" +
            java.util.Arrays.stream(ActionType.values())
                .sorted(Comparator.comparingDouble(player::weightFor).reversed())
                .map(type -> {
                    double pct = (player.weightFor(type) / totalWeight) * 100;
                    return String.format("- %-12s : %5.1f%%", type, pct);
                })
                .collect(Collectors.joining("\n"));
    }

    public List<ActionCard> drawHand(PlayerProfile player, int handSize) {
        List<ActionCard> hand = new ArrayList<>();
        for (int i = 0; i < handSize; i++) {
            hand.add(draw(player));
        }
        return hand;
    }
}
