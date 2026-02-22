package com.jleaguecardgame;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

public class PlayerProfile {
    private final String name;
    private final String club;
    private final Map<ActionType, Integer> ability;
    private final Set<Skill> skills;

    public PlayerProfile(String name, String club, Map<ActionType, Integer> ability, Set<Skill> skills) {
        this.name = name;
        this.club = club;
        this.ability = new EnumMap<>(ability);
        this.skills = EnumSet.copyOf(skills);
    }

    public String name() {
        return name;
    }

    public String club() {
        return club;
    }

    public double weightFor(ActionType actionType) {
        int baseRating = ability.getOrDefault(actionType, 40);
        double baseWeight = Math.max(0.3, baseRating / 30.0);
        double skillBonus = skills.stream().mapToDouble(skill -> skill.bonusFor(actionType)).sum();
        return baseWeight + skillBonus;
    }

    @Override
    public String toString() {
        return name + " (" + club + ")";
    }
}
