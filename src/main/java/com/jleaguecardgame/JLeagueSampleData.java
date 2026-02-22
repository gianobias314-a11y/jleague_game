package com.jleaguecardgame;

import java.util.List;
import java.util.Map;
import java.util.Set;

public final class JLeagueSampleData {
    private JLeagueSampleData() {
    }

    public static List<PlayerProfile> demoPlayers() {
        return List.of(
            new PlayerProfile(
                "Takuya Yasui",
                "Vissel Kobe",
                Map.of(
                    ActionType.SHORT_PASS, 86,
                    ActionType.THROUGH_PASS, 82,
                    ActionType.CROSS, 64,
                    ActionType.DRIBBLE, 71,
                    ActionType.SHOT, 58,
                    ActionType.PRESS, 68,
                    ActionType.TACKLE, 66
                ),
                Set.of(Skill.PLAYMAKER)
            ),
            new PlayerProfile(
                "Leo Ceara",
                "Cerezo Osaka",
                Map.of(
                    ActionType.SHORT_PASS, 67,
                    ActionType.THROUGH_PASS, 63,
                    ActionType.CROSS, 58,
                    ActionType.DRIBBLE, 74,
                    ActionType.SHOT, 88,
                    ActionType.PRESS, 61,
                    ActionType.TACKLE, 49
                ),
                Set.of(Skill.TARGET_MAN, Skill.CLUTCH_FINISHER)
            ),
            new PlayerProfile(
                "Kota Watanabe",
                "Yokohama F. Marinos",
                Map.of(
                    ActionType.SHORT_PASS, 69,
                    ActionType.THROUGH_PASS, 56,
                    ActionType.CROSS, 52,
                    ActionType.DRIBBLE, 68,
                    ActionType.SHOT, 54,
                    ActionType.PRESS, 83,
                    ActionType.TACKLE, 86
                ),
                Set.of(Skill.BALL_WINNER)
            )
        );
    }
}
