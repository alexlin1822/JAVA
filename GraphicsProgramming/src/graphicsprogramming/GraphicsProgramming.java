/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicsprogramming;

import java.awt.*;
import static java.lang.Thread.sleep;
import javax.swing.JFrame;
import java.util.Random;

/**
 *
 * @author Alex
 */
public class GraphicsProgramming {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Create a Canvas
        MyCanvas canvas1 = new MyCanvas();
        JFrame f= new JFrame();  
        f.setSize(800, 800); 
        f.setLocation(50, 50); 
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        // add the canvas to the frame as a content panel 
        f.getContentPane().add(canvas1); 
        f.setVisible(true);  
    }
}

class MyCanvas extends Canvas  
{  
    public MyCanvas() {  
        setBackground (Color.black);  
        setSize(800, 800);  
    }  
  
    @Override
    public void paint(Graphics graphic){  
        
        //drawLine 
        for (int i=1;i<50;i++){
            int R = (int)(Math.random()*256);
            int G = (int)(Math.random()*256);
            int B= (int)(Math.random()*256);
            Color col = new Color(R, G, B); //random color, but can be bright or dull
            graphic.setColor(col);  
            graphic.drawLine((i*10),(i*20), (800-i*10), (800-i*20));
            try {
                Thread.sleep(100);    
            } catch (Exception e) {
            }
        }
        
        //drawLine 
        for (int i=1;i<50;i++){
            int R = (int)(Math.random()*256);
            int G = (int)(Math.random()*256);
            int B= (int)(Math.random()*256);
            Color col = new Color(R, G, B); //random color, but can be bright or dull
            graphic.setColor(col);  
            graphic.drawLine((i*20),(i*10), (800-i*20), (800-i*10));
            try {
                Thread.sleep(100);    
            } catch (Exception e) {
            }
        }

    }  
}   
