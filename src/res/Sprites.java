/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package res;

import java.awt.Color;
import java.awt.Graphics2D;
import model.LevelItem;
import static model.LevelItem.DESTINATION;
import static model.LevelItem.EMPTY;
import static model.LevelItem.WALL;

/**
 *
 * @author Christ
 */
public class Sprites {
    
    private final int x;
    private final int y;
    private final int tile_size;
    
    public Sprites(int x, int y, int tile_size) {
        this.x = x*tile_size;
        this.y = y*tile_size;
        this.tile_size = tile_size;
//        if (li == null) {
//            color = Color.YELLOW;
//        } else {
//            switch (li) {
//                case DESTINATION: color = Color.RED; break;
//                case WALL: color = Color.BLACK; break;
//                case EMPTY: color = Color.WHITE; break;
//            }
//        }
    }
    
    public void drawCell(Graphics2D g2, LevelItem li, int luminosity) {
        drawItem(g2, li);
        g2.setColor(new Color(0f, 0f, 0f, ((float) luminosity) / 100f));
        g2.fillRect(x, y, tile_size, tile_size);
    }
    
    private void drawItem(Graphics2D g2, LevelItem li) {
        switch (li) {
            case DESTINATION:
                drawDestination(g2);
                break;
            case WALL:
                drawWall(g2);
                break;
            case EMPTY:
                drawEmpty(g2);
                break;
        }
    }
    
    private void drawDestination(Graphics2D g2) {
        drawEmpty(g2);
    }
    
    private void drawWall(Graphics2D g2) {
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(x, y, tile_size, tile_size);
        g2.setColor(Color.BLACK);
        g2.fillRect(x + getBasedOn32(0), y + getBasedOn32(1), getBasedOn32(10), getBasedOn32(6));
        g2.fillRect(x + getBasedOn32(12), y + getBasedOn32(1), getBasedOn32(14), getBasedOn32(6));
        g2.fillRect(x + getBasedOn32(28), y + getBasedOn32(1), getBasedOn32(4), getBasedOn32(6));
        
        g2.fillRect(x + getBasedOn32(0), y + getBasedOn32(9), getBasedOn32(6), getBasedOn32(6));
        g2.fillRect(x + getBasedOn32(8), y + getBasedOn32(9), getBasedOn32(14), getBasedOn32(6));
        g2.fillRect(x + getBasedOn32(24), y + getBasedOn32(9), getBasedOn32(8), getBasedOn32(6));
        
        g2.fillRect(x + getBasedOn32(0), y + getBasedOn32(17), getBasedOn32(14), getBasedOn32(6));
        g2.fillRect(x + getBasedOn32(16), y + getBasedOn32(17), getBasedOn32(14), getBasedOn32(6));
        
        g2.fillRect(x + getBasedOn32(0), y + getBasedOn32(25), getBasedOn32(6), getBasedOn32(6));
        g2.fillRect(x + getBasedOn32(8), y + getBasedOn32(25), getBasedOn32(14), getBasedOn32(6));
        g2.fillRect(x + getBasedOn32(24), y + getBasedOn32(25), getBasedOn32(8), getBasedOn32(6));
    }
    
    private void drawEmpty(Graphics2D g2) {
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(x, y, tile_size, tile_size);
        g2.setColor(new Color(get255to100(148), get255to100(135), get255to100(107), 1f));
        g2.fillRect(x + getBasedOn32(3), y + getBasedOn32(3), getBasedOn32(5), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(12), y + getBasedOn32(18), getBasedOn32(5), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(17), y + getBasedOn32(8), getBasedOn32(5), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(23), y + getBasedOn32(26), getBasedOn32(5), getBasedOn32(2));
    }
    
    private void drawEmpty(Graphics2D g2, int luminosity) {
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(x, y, tile_size, tile_size);
        g2.setColor(new Color(get255to100(148), get255to100(135), get255to100(107), 1f));
        g2.fillRect(x + getBasedOn32(3), y + getBasedOn32(3), getBasedOn32(5), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(12), y + getBasedOn32(18), getBasedOn32(5), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(17), y + getBasedOn32(8), getBasedOn32(5), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(23), y + getBasedOn32(26), getBasedOn32(5), getBasedOn32(2));
        
        g2.setColor(new Color(0f, 0f, 0f, ((float) luminosity) / 100f));
        g2.fillRect(x, y, tile_size, tile_size);
    }
    
    public void drawPlayer(Graphics2D g2) {
        drawEmpty(g2);
        
        g2.setColor(new Color(get255to100(24), get255to100(20), get255to100(37), 1f));
        g2.fillRect(x + getBasedOn32(10), y + getBasedOn32(6), getBasedOn32(14), getBasedOn32(10));
        
        g2.setColor(new Color(get255to100(38), get255to100(43), get255to100(68), 1f));
        g2.fillRect(x + getBasedOn32(10), y + getBasedOn32(2), getBasedOn32(12), getBasedOn32(6));
        g2.fillRect(x + getBasedOn32(12), y + getBasedOn32(8), getBasedOn32(8), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(22), y + getBasedOn32(4), getBasedOn32(2), getBasedOn32(2));
        
        g2.setColor(new Color(get255to100(38), get255to100(43), get255to100(68), 0.3f));
        g2.fillRect(x + getBasedOn32(8), y + getBasedOn32(22), getBasedOn32(12), getBasedOn32(6));
        g2.fillRect(x + getBasedOn32(10), y + getBasedOn32(28), getBasedOn32(12), getBasedOn32(2));
       
        g2.setColor(new Color(get255to100(228), get255to100(166), get255to100(114), 1f));
        g2.fillRect(x + getBasedOn32(12), y + getBasedOn32(12), getBasedOn32(10), getBasedOn32(8));
        g2.fillRect(x + getBasedOn32(10), y + getBasedOn32(10), getBasedOn32(2), getBasedOn32(8));
        g2.fillRect(x + getBasedOn32(20), y + getBasedOn32(10), getBasedOn32(2), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(22), y + getBasedOn32(16), getBasedOn32(2), getBasedOn32(2));
        
        g2.setColor(new Color(get255to100(24), get255to100(20), get255to100(37), 1f));
        g2.fillRect(x + getBasedOn32(12), y + getBasedOn32(14), getBasedOn32(2), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(18), y + getBasedOn32(14), getBasedOn32(2), getBasedOn32(2));
        
        g2.setColor(Color.WHITE);
        g2.fillRect(x + getBasedOn32(10), y + getBasedOn32(20), getBasedOn32(8), getBasedOn32(2));
        
        g2.setColor(new Color(get255to100(0), get255to100(149), get255to100(233), 1f));
        g2.fillRect(x + getBasedOn32(12), y + getBasedOn32(22), getBasedOn32(6), getBasedOn32(2));
        
        //shield
//        g2.setColor(new Color(get255to100(228), get255to100(59), get255to100(68), 1f));
//        g2.fillRect(x + getBasedOn32(18), y + getBasedOn32(20), getBasedOn32(8), getBasedOn32(2));
//        
//        g2.setColor(new Color(get255to100(158), get255to100(40), get255to100(53), 1f));
//        g2.fillRect(x + getBasedOn32(18), y + getBasedOn32(22), getBasedOn32(8), getBasedOn32(4));
//        g2.fillRect(x + getBasedOn32(20), y + getBasedOn32(26), getBasedOn32(4), getBasedOn32(2));

//        lamp
        g2.setColor(new Color(get255to100(255), get255to100(240), get255to100(0), 1f));
        g2.fillRect(x + getBasedOn32(18), y + getBasedOn32(20), getBasedOn32(8), getBasedOn32(8));
        
        g2.setColor(new Color(get255to100(116), get255to100(84), get255to100(10), 1f));
        g2.fillRect(x + getBasedOn32(20), y + getBasedOn32(18), getBasedOn32(4), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(18), y + getBasedOn32(20), getBasedOn32(2), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(24), y + getBasedOn32(20), getBasedOn32(2), getBasedOn32(2));
//        g2.fillRect(x + getBasedOn32(18), y + getBasedOn32(26), getBasedOn32(8), getBasedOn32(2));
//        g2.fillRect(x + getBasedOn32(24), y + getBasedOn32(26), getBasedOn32(2), getBasedOn32(2));
        
        g2.setColor(new Color(get255to100(24), get255to100(20), get255to100(37), 1f));
        g2.fillRect(x + getBasedOn32(12), y + getBasedOn32(24), getBasedOn32(2), getBasedOn32(2));
        
        g2.setColor(new Color(get255to100(184), get255to100(111), get255to100(80), 1f));
        g2.fillRect(x + getBasedOn32(8), y + getBasedOn32(20), getBasedOn32(2), getBasedOn32(4));
        g2.fillRect(x + getBasedOn32(20), y + getBasedOn32(16), getBasedOn32(4), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(20), y + getBasedOn32(18), getBasedOn32(2), getBasedOn32(2));
        
        g2.setColor(new Color(get255to100(228), get255to100(166), get255to100(114), 1f));
        g2.fillRect(x + getBasedOn32(6), y + getBasedOn32(20), getBasedOn32(2), getBasedOn32(4));
        
        g2.setColor(new Color(get255to100(192), get255to100(203), get255to100(220), 1f));
        g2.fillRect(x + getBasedOn32(4), y + getBasedOn32(18), getBasedOn32(8), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(8), y + getBasedOn32(-2), getBasedOn32(2), getBasedOn32(18));
        
        g2.setColor(Color.WHITE);
        g2.fillRect(x + getBasedOn32(6), y + getBasedOn32(-4), getBasedOn32(2), getBasedOn32(22));
    }
    
    public void drawDragon(Graphics2D g2, int luminosity) {
        drawEmpty(g2, luminosity);
        
        g2.setColor(new Color(get255to100(38), get255to100(43), get255to100(68), 0.3f));
        g2.fillRect(x + getBasedOn32(4), y + getBasedOn32(22), getBasedOn32(12), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(2), y + getBasedOn32(24), getBasedOn32(24), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(0), y + getBasedOn32(26), getBasedOn32(32), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(2), y + getBasedOn32(28), getBasedOn32(30), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(4), y + getBasedOn32(30), getBasedOn32(22), getBasedOn32(2));
        
        g2.setColor(new Color(get255to100(252), get255to100(225), get255to100(170), 1f));
        g2.fillRect(x + getBasedOn32(8), y + getBasedOn32(0), getBasedOn32(2), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(10), y + getBasedOn32(2), getBasedOn32(2), getBasedOn32(4));
        g2.fillRect(x + getBasedOn32(8), y + getBasedOn32(6), getBasedOn32(6), getBasedOn32(16));
        g2.fillRect(x + getBasedOn32(6), y + getBasedOn32(14), getBasedOn32(2), getBasedOn32(6));
        
        g2.setColor(new Color(get255to100(237), get255to100(193), get255to100(118), 1f));
        g2.fillRect(x + getBasedOn32(6), y + getBasedOn32(20), getBasedOn32(4), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(8), y + getBasedOn32(22), getBasedOn32(6), getBasedOn32(2));
        
        g2.setColor(new Color(get255to100(121), get255to100(11), get255to100(12), 1f));
        g2.fillRect(x + getBasedOn32(14), y + getBasedOn32(-6), getBasedOn32(6), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(16), y + getBasedOn32(-4), getBasedOn32(6), getBasedOn32(6));
        g2.fillRect(x + getBasedOn32(22), y + getBasedOn32(6), getBasedOn32(4), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(24), y + getBasedOn32(8), getBasedOn32(6), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(26), y + getBasedOn32(10), getBasedOn32(6), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(30), y + getBasedOn32(12), getBasedOn32(2), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(28), y + getBasedOn32(14), getBasedOn32(2), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(8), y + getBasedOn32(24), getBasedOn32(4), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(6), y + getBasedOn32(26), getBasedOn32(6), getBasedOn32(2));
        
        g2.setColor(new Color(get255to100(157), get255to100(39), get255to100(39), 1f));
        g2.fillRect(x + getBasedOn32(2), y + getBasedOn32(-8), getBasedOn32(2), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(6), y + getBasedOn32(-8), getBasedOn32(2), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(20), y + getBasedOn32(-8), getBasedOn32(4), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(2), y + getBasedOn32(-6), getBasedOn32(10), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(22), y + getBasedOn32(-6), getBasedOn32(4), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(-2), y + getBasedOn32(-4), getBasedOn32(16), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(22), y + getBasedOn32(-4), getBasedOn32(8), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(-2), y + getBasedOn32(-2), getBasedOn32(18), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(22), y + getBasedOn32(-2), getBasedOn32(10), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(-4), y + getBasedOn32(0), getBasedOn32(10), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(10), y + getBasedOn32(0), getBasedOn32(8), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(22), y + getBasedOn32(0), getBasedOn32(12), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(-4), y + getBasedOn32(2), getBasedOn32(8), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(12), y + getBasedOn32(2), getBasedOn32(22), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(12), y + getBasedOn32(4), getBasedOn32(24), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(12), y + getBasedOn32(6), getBasedOn32(10), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(26), y + getBasedOn32(6), getBasedOn32(10), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(12), y + getBasedOn32(8), getBasedOn32(8), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(30), y + getBasedOn32(8), getBasedOn32(8), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(6), y + getBasedOn32(10), getBasedOn32(2), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(10), y + getBasedOn32(10), getBasedOn32(12), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(34), y + getBasedOn32(10), getBasedOn32(4), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(12), y + getBasedOn32(12), getBasedOn32(10), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(36), y + getBasedOn32(12), getBasedOn32(2), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(14), y + getBasedOn32(14), getBasedOn32(10), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(34), y + getBasedOn32(14), getBasedOn32(2), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(14), y + getBasedOn32(16), getBasedOn32(10), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(14), y + getBasedOn32(18), getBasedOn32(12), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(36), y + getBasedOn32(18), getBasedOn32(2), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(14), y + getBasedOn32(20), getBasedOn32(14), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(34), y + getBasedOn32(20), getBasedOn32(4), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(14), y + getBasedOn32(22), getBasedOn32(22), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(26), y + getBasedOn32(24), getBasedOn32(8), getBasedOn32(2));
        
        g2.fillRect(x + getBasedOn32(18), y + getBasedOn32(24), getBasedOn32(4), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(16), y + getBasedOn32(26), getBasedOn32(6), getBasedOn32(2));
        
        g2.setColor(new Color(get255to100(137), get255to100(11), get255to100(12), 1f));
        g2.fillRect(x + getBasedOn32(14), y + getBasedOn32(22), getBasedOn32(12), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(26), y + getBasedOn32(24), getBasedOn32(8), getBasedOn32(2));
        
        g2.setColor(Color.BLACK);
        g2.fillRect(x + getBasedOn32(2), y + getBasedOn32(-2), getBasedOn32(2), getBasedOn32(2));
        
        
        
        
        g2.setColor(new Color(0f, 0f, 0f, ((float) luminosity) / 100f));
        g2.fillRect(x + getBasedOn32(8), y + getBasedOn32(0), getBasedOn32(2), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(10), y + getBasedOn32(2), getBasedOn32(2), getBasedOn32(4));
        g2.fillRect(x + getBasedOn32(8), y + getBasedOn32(6), getBasedOn32(6), getBasedOn32(16));
        g2.fillRect(x + getBasedOn32(6), y + getBasedOn32(14), getBasedOn32(2), getBasedOn32(6));
        g2.fillRect(x + getBasedOn32(6), y + getBasedOn32(20), getBasedOn32(4), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(8), y + getBasedOn32(22), getBasedOn32(6), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(14), y + getBasedOn32(-6), getBasedOn32(6), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(16), y + getBasedOn32(-4), getBasedOn32(6), getBasedOn32(6));
        g2.fillRect(x + getBasedOn32(22), y + getBasedOn32(6), getBasedOn32(4), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(24), y + getBasedOn32(8), getBasedOn32(6), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(26), y + getBasedOn32(10), getBasedOn32(6), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(30), y + getBasedOn32(12), getBasedOn32(2), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(28), y + getBasedOn32(14), getBasedOn32(2), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(8), y + getBasedOn32(24), getBasedOn32(4), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(6), y + getBasedOn32(26), getBasedOn32(6), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(2), y + getBasedOn32(-8), getBasedOn32(2), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(6), y + getBasedOn32(-8), getBasedOn32(2), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(20), y + getBasedOn32(-8), getBasedOn32(4), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(2), y + getBasedOn32(-6), getBasedOn32(10), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(22), y + getBasedOn32(-6), getBasedOn32(4), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(-2), y + getBasedOn32(-4), getBasedOn32(16), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(22), y + getBasedOn32(-4), getBasedOn32(8), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(-2), y + getBasedOn32(-2), getBasedOn32(18), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(22), y + getBasedOn32(-2), getBasedOn32(10), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(-4), y + getBasedOn32(0), getBasedOn32(10), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(10), y + getBasedOn32(0), getBasedOn32(8), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(22), y + getBasedOn32(0), getBasedOn32(12), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(-4), y + getBasedOn32(2), getBasedOn32(8), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(12), y + getBasedOn32(2), getBasedOn32(22), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(12), y + getBasedOn32(4), getBasedOn32(24), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(12), y + getBasedOn32(6), getBasedOn32(10), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(26), y + getBasedOn32(6), getBasedOn32(10), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(12), y + getBasedOn32(8), getBasedOn32(8), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(30), y + getBasedOn32(8), getBasedOn32(8), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(6), y + getBasedOn32(10), getBasedOn32(2), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(10), y + getBasedOn32(10), getBasedOn32(12), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(34), y + getBasedOn32(10), getBasedOn32(4), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(12), y + getBasedOn32(12), getBasedOn32(10), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(36), y + getBasedOn32(12), getBasedOn32(2), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(14), y + getBasedOn32(14), getBasedOn32(10), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(34), y + getBasedOn32(14), getBasedOn32(2), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(14), y + getBasedOn32(16), getBasedOn32(10), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(14), y + getBasedOn32(18), getBasedOn32(12), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(36), y + getBasedOn32(18), getBasedOn32(2), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(14), y + getBasedOn32(20), getBasedOn32(14), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(34), y + getBasedOn32(20), getBasedOn32(4), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(14), y + getBasedOn32(22), getBasedOn32(22), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(26), y + getBasedOn32(24), getBasedOn32(8), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(18), y + getBasedOn32(24), getBasedOn32(4), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(16), y + getBasedOn32(26), getBasedOn32(6), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(14), y + getBasedOn32(22), getBasedOn32(12), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(26), y + getBasedOn32(24), getBasedOn32(8), getBasedOn32(2));
        g2.fillRect(x + getBasedOn32(2), y + getBasedOn32(-2), getBasedOn32(2), getBasedOn32(2));
    }
    
    private int getBasedOn32(int n) {
        return (n*tile_size) / 32;
    } 
    
    private float get255to100(int n) {
        if (n > 255) return 255;
        return ((float)n) / 255f;
    } 
}
