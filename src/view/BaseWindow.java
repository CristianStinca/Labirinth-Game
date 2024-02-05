/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

/**
 *
 * @author Christ
 */
public class BaseWindow extends JFrame {
    
    Border defaultEdge = BorderFactory.createEmptyBorder(10,10,0,10);
    Border lastEdge = BorderFactory.createEmptyBorder(10,10,10,10);
    
    public BaseWindow() {
        setSize(600, 500);
        setTitle("Hunter Game");
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
//                int x = JOptionPane.showConfirmDialog(BaseWindow.this, "Are you sure?");
//                if (x == 0) {
                    exit();
//                }
            }
        });
    }
    
    public void exit() {
        System.exit(0);
    }
}
