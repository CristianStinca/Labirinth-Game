/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import database.HighScore;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import model.Game;
import model.GameID;

/**
 *
 * @author Christ
 */
public class MainWindow extends BaseWindow {
    private final Game game;
//    private Board board;
    
    public MainWindow() {
        game = new Game();
        
        setTitle("Labyrinth");
        setSize(300, 500);
        
        JLabel titleLabel = new JLabel();
        titleLabel.setBorder(defaultEdge);
        titleLabel.setText("Choose level difficulty:");
        
        JLabel title2Label = new JLabel();
        title2Label.setBorder(defaultEdge);
        title2Label.setText("Choose level:");
        
        JLabel infoLabel = new JLabel();
        infoLabel.setBorder(defaultEdge);
        infoLabel.setText("Additional info:");
        
        JPanel difficultiesPanel = generateDifficultyButtons();
        
        JPanel levelsPanel = new JPanel();
        levelsPanel.setBorder(defaultEdge);
        levelsPanel.setLayout(new GridLayout(3, 1));
        levelsPanel.setMaximumSize(new Dimension(300, 150));
        levelsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JPanel addPanel = new JPanel();
        addPanel.setLayout(new GridLayout(1, 1));
        addPanel.setBorder(defaultEdge);
        addPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        addPanel.setMaximumSize(new Dimension(300, 50));
        
        JButton highscore = new JButton();
        highscore.setText("Highscores");
        highscore.addActionListener(showAddInfo(game.getHighScores()));
        
        addPanel.add(highscore);
        
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().add(infoLabel);
        getContentPane().add(addPanel);
        getContentPane().add(titleLabel);
        getContentPane().add(difficultiesPanel);
        getContentPane().add(title2Label);
        getContentPane().add(levelsPanel, 5);
    }
    
    private JPanel generateDifficultyButtons() {
        JPanel panel = new JPanel();
        
        for (String difficulty : game.getDifficulties()) {
            JButton button = new JButton();
            button.setText(difficulty);
            button.addActionListener(showLevelsOfDifficultyButtons(difficulty));
            panel.add(button);
        }
        
        panel.setBorder(defaultEdge);
        panel.setLayout(new GridLayout(3, 1));
        panel.setMaximumSize(new Dimension(300, 150));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        return panel;
    }
    
    private ActionListener showLevelsOfDifficultyButtons(String difficulty) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = new JPanel();
                for (Integer level : game.getLevelsOfDifficulty(difficulty)) {
                    JButton button = new JButton();
                    button.setText(difficulty + ": " + level);
                    button.addActionListener(runGameLevel(new GameID(difficulty, level)));
                    panel.add(button);
                }
                
                panel.setBorder(defaultEdge);
                panel.setLayout(new GridLayout(3, 1));
                panel.setMaximumSize(new Dimension(300, 150));
                panel.setAlignmentX(Component.LEFT_ALIGNMENT);
                
                getContentPane().remove(5);
                getContentPane().validate();
                getContentPane().add(panel);
                getContentPane().validate();
            }
        };
    }
    
    private ActionListener runGameLevel(GameID gameID) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.loadGame(gameID);
                GameWindow window = new GameWindow(game);
                window.setVisible(true);

                MainWindow.this.dispose();
            }
        };
    }
    
    private ActionListener showAddInfo (ArrayList<HighScore> hs) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HighScoresWindow window = new HighScoresWindow(hs);
                window.setVisible(true);

                MainWindow.this.dispose();
            }
        };
    }
}
