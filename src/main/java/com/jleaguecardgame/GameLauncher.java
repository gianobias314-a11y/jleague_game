package com.jleaguecardgame;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.time.LocalTime;
import java.util.List;

public class GameLauncher {
    private final WeightedActionDeck deck = new WeightedActionDeck(System.currentTimeMillis());
    private final List<PlayerProfile> players = JLeagueSampleData.demoPlayers();

    private JTextArea oddsArea;
    private JTextArea matchLog;
    private JComboBox<PlayerProfile> playerSelector;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameLauncher().show());
    }

    private void show() {
        JFrame frame = new JFrame("J.League Card Match");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("Controlled Player:"));

        playerSelector = new JComboBox<>(players.toArray(PlayerProfile[]::new));
        playerSelector.addActionListener(e -> refreshOdds());
        top.add(playerSelector);

        JButton drawButton = new JButton("Draw Action Card");
        drawButton.addActionListener(e -> drawCard());
        top.add(drawButton);

        JButton kickoffButton = new JButton("Kickoff (clear log)");
        kickoffButton.addActionListener(e -> matchLog.setText(""));
        top.add(kickoffButton);

        oddsArea = new JTextArea();
        oddsArea.setEditable(false);
        oddsArea.setBorder(BorderFactory.createTitledBorder("Current Draw Odds"));

        matchLog = new JTextArea();
        matchLog.setEditable(false);
        matchLog.setBorder(BorderFactory.createTitledBorder("Match Log"));

        JPanel center = new JPanel(new BorderLayout(12, 12));
        center.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        center.add(new JScrollPane(oddsArea), BorderLayout.WEST);
        center.add(new JScrollPane(matchLog), BorderLayout.CENTER);

        frame.add(top, BorderLayout.NORTH);
        frame.add(center, BorderLayout.CENTER);

        refreshOdds();
        frame.setVisible(true);
    }

    private void refreshOdds() {
        PlayerProfile player = selectedPlayer();
        oddsArea.setText(deck.oddsReport(player));
    }

    private void drawCard() {
        PlayerProfile player = selectedPlayer();
        ActionCard card = deck.draw(player);
        String line = String.format("[%s] %s played: %s (%s, impact %+d)%n",
            LocalTime.now().withNano(0),
            player.name(),
            card.name(),
            card.type(),
            card.impact());
        matchLog.append(line);
    }

    private PlayerProfile selectedPlayer() {
        Object selected = playerSelector.getSelectedItem();
        return (PlayerProfile) selected;
    }
}
