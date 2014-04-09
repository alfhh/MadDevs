/**
 * Star Wars Tower Defense
 * Developed by Mad Devs
 * "Stay on the rigth side, the Made side"
 * Version: Alpha       Date: 4/07/2014
 */
package sttd;

import java.awt.Color;
import javax.swing.JFrame;
<<<<<<< HEAD
import javax.swing.JButton;
=======
>>>>>>> 4dc2c10e8ad3840a7cb5afbb2911d75dc4e9ef31
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.awt.Graphics;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

public class STTD extends JFrame implements Runnable, KeyListener, MouseListener {

<<<<<<< HEAD
    private Graphics dbg; // Graphic Object
    private Image dbImage; // Image
    private Image background; // Background Image
    private JButton bt_solo, bt_2p, bt_inst; // Main menu buttons
    
    public STTD(){
        // Setup
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1368,768);
        setTitle("Star Wars: Tower Defense");
        Thread th = new Thread(this);
        th.start();
    }
    
    /**
     * Se ejectua el Thread
     */
    public void run() {
        while(true) {
            //checaColision();tr
            actualiza();
            repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                System.out.println("Error en " + ex.toString());
            }
            
        }
    }
    /**
     * This method updates..
     */
    public void actualiza() {
        
=======
    public STTD(){
        
    }
    
    public void run() {

>>>>>>> 4dc2c10e8ad3840a7cb5afbb2911d75dc4e9ef31
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
<<<<<<< HEAD
    
     /**
     * Metodo que actuliza las animaciones
     *
     * @param g es la <code>imagen</code> del objeto.
     */
    public void paint(Graphics g) {
        // Inicializa el DoubleBuffer
        if (dbImage == null) {
            dbImage = createImage(this.getSize().width, this.getSize().height);
            dbg = dbImage.getGraphics();
        }

        // Actualiza la imagen de fondo.
        dbg.setColor(getBackground());
        dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);

        // Actualiza el Foreground.
        dbg.setColor(getForeground());
        paint1(dbg);

        // Dibuja la imagen actualizada
        g.drawImage(dbImage, 0, 0, this);
    }
    /**
     * Method used to paint images in the game
     * @param g 
     */
    public void paint1(Graphics g){
        
    }
=======
>>>>>>> 4dc2c10e8ad3840a7cb5afbb2911d75dc4e9ef31

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        STTD defense = new STTD();
        defense.setVisible(true);
    }
<<<<<<< HEAD
=======

>>>>>>> 4dc2c10e8ad3840a7cb5afbb2911d75dc4e9ef31
}
