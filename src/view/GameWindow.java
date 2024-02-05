/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.TimerTask;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import model.Direction;
import model.Game;
import model.GameID;
import model.GameLevel;
/**
 *
 * @author Christ
 */
public class GameWindow extends BaseWindow {
    //should load a level from a folder
    
    private final Game game;
    private Board board;
    private final JLabel gameStatLabel; 
    private final JLabel gameLevelName; 
    private boolean gameEnd = false;
//    private final int tile_size = 32;
    
    public GameWindow(Game _game) {
        game = _game;
        setTitle("Labyrinth");
        
        JMenuBar menuBar = new JMenuBar();
        JMenu menuGame = new JMenu("Game");
        JMenu menuGameLevel = new JMenu("Level");
        JMenu menuGameScale = new JMenu("Zoom");
        createGameLevelMenuItems(menuGameLevel);
        createScaleMenuItems(menuGameScale, 1.0, 2.0, 0.5);

        JMenuItem menuGameExit = new JMenuItem(new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menuGame.add(menuGameLevel);
        menuGame.add(menuGameScale);
        menuGame.addSeparator();
        menuGame.add(menuGameExit);
        menuBar.add(menuGame);
        setJMenuBar(menuBar);
        
        setLayout(new BorderLayout(0, 10));
        gameStatLabel = new JLabel("00:00");

        try { add(board = new Board(game), BorderLayout.CENTER); } catch (IOException ex) {}
        
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (game.hasTimerStopped()) {
                    return;
                }
                
                if (game.hasGameEnded()) {
                   game.stopTimer();
                }
                
                int s = game.modSeconds();
                gameStatLabel.setText("Time elapsed: " + String.valueOf(s / 60) + ":" + String.valueOf(s % 60));
            }
        };
        
        timer.scheduleAtFixedRate(task, 0, 1000);
        
        gameLevelName = new JLabel(game.getGameID().difficulty + " " + game.getGameID().level);
        
        JPanel stats = new JPanel();
        stats.setLayout(new BorderLayout());
        
        stats.add(gameStatLabel, BorderLayout.WEST);
        stats.add(gameLevelName, BorderLayout.EAST);
        
        add(stats, BorderLayout.NORTH);
        
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                super.keyPressed(ke); 
                if (!game.isLevelLoaded()) return;
                int kk = ke.getKeyCode();
                Direction d = null;
                switch (kk){
                    case KeyEvent.VK_A:  d = Direction.LEFT; break;
                    case KeyEvent.VK_D: d = Direction.RIGHT; break;
                    case KeyEvent.VK_W:    d = Direction.UP; break;
                    case KeyEvent.VK_S:  d = Direction.DOWN; break;
                    case KeyEvent.VK_ESCAPE: game.loadGame(game.getGameID());
                }
                if (game.hasGameEnded()) return;
                board.repaint();
                if (d != null && game.step(d)){
                    if (game.arePalyerDragonAdjacent()) {
                        //JOptionPane.showMessageDialog(GameWindow.this, "You lost!", "Oh no!", JOptionPane.INFORMATION_MESSAGE);
                        finishGame();
                    } else if (game.endCondition()) {
                        JOptionPane.showMessageDialog(GameWindow.this, "You won!", "Congratulation!", JOptionPane.INFORMATION_MESSAGE);
                        if (game.gameEnd(true, "")) {
                            GameWindow.this.finishGame();
                        }
                        board.refresh();
                        pack();
                        gameLevelName.setText(game.getGameID().difficulty + " " + String.valueOf(game.getGameID().level));
                    }
                } 
            }
        });
        
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            Runnable toRun = () -> {
                if (game.hasGameEnded() || gameEnd) return;
                try {
                    game.moveDragon();
                    board.repaint();
                    if (game.arePalyerDragonAdjacent()) {
                        finishGame();
                    }
                } catch (GameLevel.InvalidDragonPositionException e) {
                    System.out.println(e);
                }
        };
        scheduler.scheduleAtFixedRate(toRun, 1, 2, TimeUnit.SECONDS);
        
        setResizable(false);
        setLocationRelativeTo(null);
        board.refresh();
        pack();
        setVisible(true);
    }
    
    private void createGameLevelMenuItems(JMenu menu){
        for (String s : game.getDifficulties()){
            JMenu difficultyMenu = new JMenu(s);
            menu.add(difficultyMenu);
            for (Integer i : game.getLevelsOfDifficulty(s)){
                JMenuItem item = new JMenuItem(new AbstractAction("Level-" + i) {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        game.loadGame(new GameID(s, i));
                        board.refresh();
                        pack();
                    }
                });
                difficultyMenu.add(item);
            }
        }
    }
    
    private void createScaleMenuItems(JMenu menu, double from, double to, double by){
        while (from <= to){
            final double scale = from;
            JMenuItem item = new JMenuItem(new AbstractAction(from + "x") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (board.setScale(scale)) pack();
                }
            });
            menu.add(item);
            
            if (from == to) break;
            from += by;
            if (from > to) from = to;
        }
    }
    
    private void finishGame() {
        gameEnd = true;
        String name = null;
        do {
            name = JOptionPane.showInputDialog("What is your name?");
        } while (name == null || name.equals(""));
        
        game.gameEnd(false, name);
        
        MainWindow window = new MainWindow();
        window.setVisible(true);

        GameWindow.this.dispose();
        gameEnd = false;
    }
}


