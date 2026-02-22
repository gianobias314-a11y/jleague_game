package com.jleaguecardgame;

import java.util.EnumMap;
import java.util.Map;

public enum Skill {
    PLAYMAKER(Map.of(
        ActionType.SHORT_PASS, 2.3,
        ActionType.THROUGH_PASS, 2.0,
        ActionType.CROSS, 1.2
    )),
    SPEEDSTER(Map.of(
        ActionType.DRIBBLE, 2.4,
        ActionType.PRESS, 0.8
    )),
    TARGET_MAN(Map.of(
        ActionType.SHOT, 2.3,
        ActionType.CROSS, 1.5
    )),
    BALL_WINNER(Map.of(
        ActionType.TACKLE, 2.4,
        ActionType.PRESS, 1.8
    )),
    CLUTCH_FINISHER(Map.of(
        ActionType.SHOT, 2.8,
        ActionType.DRIBBLE, 0.7
    ));

    private final Map<ActionType, Double> bonuses;

    Skill(Map<ActionType, Double> bonuses) {
        this.bonuses = new EnumMap<>(bonuses);
    }

    public double bonusFor(ActionType actionType) {
        return bonuses.getOrDefault(actionType, 0.0);
    }
}
