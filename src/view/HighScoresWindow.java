/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.GridLayout;
import java.util.ArrayList;
import database.HighScore;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Christ
 */
public class HighScoresWindow extends BaseWindow {
    public HighScoresWindow(ArrayList<HighScore> hs) {
        setSize(300, 400);
        setLayout(new GridLayout(12, 1));
        
        JLabel nameL = new JLabel("Name:");
        JLabel scoreL = new JLabel("Score:");
        JLabel timeL = new JLabel("Time:");
        
        JPanel instructions = new JPanel(new GridLayout(1, 3));
        
        instructions.add(nameL);
        instructions.add(scoreL);
        instructions.add(timeL);
        
        add(instructions);
        
        for (HighScore score : hs) {
            JPanel panel = new JPanel(new GridLayout(1, 3));
            
            JLabel labelN = new JLabel(score.getName());
            JLabel labelS = new JLabel(String.valueOf(score.getScore()));
            JLabel labelT = new JLabel(String.valueOf(score.getTotalTime()));
            
            panel.add(labelN);
            panel.add(labelS);
            panel.add(labelT);
            
            add(panel);
        }
        
        JButton backButton = new JButton("Back");
        backButton.addActionListener(backAction());
        add(backButton);
    }
    
    private ActionListener backAction () {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow window = new MainWindow();
                window.setVisible(true);

                HighScoresWindow.this.dispose();
            }
        };
    }
}
