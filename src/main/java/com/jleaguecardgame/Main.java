package com.jleaguecardgame;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        WeightedActionDeck deck = new WeightedActionDeck(17L);
        List<PlayerProfile> players = JLeagueSampleData.demoPlayers();

        for (PlayerProfile player : players) {
            System.out.println("==================================================");
            System.out.println(deck.oddsReport(player));
            System.out.println("Starting hand:");
            List<ActionCard> hand = deck.drawHand(player, 5);
            for (ActionCard card : hand) {
                System.out.printf("  * %-20s (%s, impact %+d)%n", card.name(), card.type(), card.impact());
            }
            System.out.println();
        }
    }
}
