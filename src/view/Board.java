package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.swing.JPanel;
import model.Game;
import model.LevelItem;
import model.Position;
import res.Sprites;

public class Board extends JPanel {
    private Game game;
    private double scale;
    private int scaled_size;
    private final int tile_size = 32;
    
    public Board(Game g) throws IOException{
        game = g;
        scale = 1.0;
        scaled_size = (int)(scale * tile_size);
    }
    
    public boolean setScale(double scale){
        this.scale = scale;
        scaled_size = (int)(scale * tile_size);
        return refresh();
    }
    
    public boolean refresh(){
        if (!game.isLevelLoaded()) return false;
        Dimension dim = new Dimension(game.getLevelCols() * scaled_size, game.getLevelRows() * scaled_size);
        setPreferredSize(dim);
        setMaximumSize(dim);
        setSize(dim);
        repaint();
        return true;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (!game.isLevelLoaded()) return;
        Graphics2D gr = (Graphics2D)g;
        int w = game.getLevelCols();
        int h = game.getLevelRows();
        Position p = game.getPlayerPos();
        Position d = game.getDragonPos();
        for (int y = 0; y < h; y++){
            for (int x = 0; x < w; x++){
                Sprites sprite = new Sprites(x, y, scaled_size);
                LevelItem li = game.getItem(y, x);
                sprite.drawCell(gr, li, game.getCellLuminosity(y, x));
            }
            
            Sprites spriteP = new Sprites(p.x, p.y, scaled_size);
            spriteP.drawPlayer(gr);
            if (!game.isCellOutOfVision(d.y, d.x)) {
                Sprites spriteD = new Sprites(d.x, d.y, scaled_size);
                spriteD.drawDragon(gr, game.getCellLuminosity(d.y, d.x));
            }
        }
    }
}
