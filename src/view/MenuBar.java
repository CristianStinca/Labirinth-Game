/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

/**
 *
 * @author Christ
 */
public class MenuBar extends JMenuBar {
    
    private Integer size = 3;
    
    public MenuBar(Action restartAction, Action startNewGameAction, Action toMainMenuAction) {
        
        JMenuItem restart = new JMenuItem(restartAction);
        restart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, Event.CTRL_MASK));
        restart.setText("Restart");
        restart.setMnemonic('R');

        JMenu newGameMenu = new JMenu("New game");
        
        JMenuItem startNewGame = new JMenuItem(startNewGameAction);
        startNewGame.setText("Start");
        
        newGameMenu.add(startNewGame);
        
        newGameMenu.addSeparator();
        
        ButtonGroup group = new ButtonGroup();
        
        JRadioButtonMenuItem small = new JRadioButtonMenuItem();
        small.setText("Easy");
        small.setSelected(true);
        small.setActionCommand("3");
        small.addActionListener(actionListener);
        group.add(small);
        
        JRadioButtonMenuItem medium = new JRadioButtonMenuItem();
        medium.setText("Medium");
        medium.setActionCommand("5");
        medium.addActionListener(actionListener);
        group.add(medium);
        
        JRadioButtonMenuItem large = new JRadioButtonMenuItem();
        large.setText("Hard");
        large.setActionCommand("7");
        large.addActionListener(actionListener);
        group.add(large);
        
        newGameMenu.add(small);
        newGameMenu.add(medium);
        newGameMenu.add(large);
        
        JMenuItem toMainMenuItem = new JMenuItem(toMainMenuAction);
        toMainMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, Event.CTRL_MASK));
        toMainMenuItem.setText("Main menu");
        toMainMenuItem.setMnemonic('B');
        
        add(restart);
        add(newGameMenu);
        add(toMainMenuItem);
    }
    
    private final ActionListener actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();
            size = Integer.valueOf(actionCommand);
        }
        
    };

    public int getBoardSize() {
        return size;
    }  
}
