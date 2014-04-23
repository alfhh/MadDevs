/**
 * Star Wars Tower Defense Developed by Mad Devs "Stay on the rigth side, the
 * Made side" Version: Alpha Date: 4/07/2014
 */
package sttd;

import java.awt.Color;
import javax.swing.JFrame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;

public class STTD extends JFrame implements Runnable, KeyListener, MouseListener, MouseMotionListener {

    private Graphics dbg; // Graphic Object
    private Graphics dbgtower; // Graphic Object
    private Image dbImage; // Image
    private Image background; // Background Image
    private Image imgmenu; // Menu Image
    private Image t; // Torreta Image
    
    
    private Animacion animTorre; // Animacion de la torre
    
    private LinkedList tower; // Lista de las Torres
    private LinkedList towergraphics; // Lista de las imagenes de las torres
    
    private double rotacion; // Rotacion que se le dara a las torres
    
    private int towerid; //Valor utilizado para identificar el id de la torre sin importar su posición en la linked list
    private int mousex; //Posición en X del mouse
    private int mousey; //Posición en Y del mouse
    
    private AffineTransform identidad; // Variable tipo AffineTransform
    
    private boolean main; // booleano que muestra la pantalla principal
    private boolean menu; // booleano que muestra el menu de niveles
    private boolean instr; // booleano que muestra las instrucciones
    private boolean game; // booleano que deja que el juego corra

    public STTD() {
        // Setup
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1368, 730);
        setTitle("Star Wars: Tower Defense");
        rotacion = Math.PI / 60;
        towerid = 0;
        main = true;
        instr = false;
        menu = false;
        

        // Images
        background = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/mainBackground.png"));
        t = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/torretadual.png"));
        animTorre = new Animacion();
        animTorre.sumaCuadro(t, 100);

        // Tower
        tower = new LinkedList();
        towergraphics = new LinkedList();
        tower.add(new Tower(1368/2, 730/2, animTorre, 1,1));
        tower.add(new Tower(1368/3, 730/3, animTorre, 1,1));
        tower.add(new Tower(2*1368/3, 2*730/3, animTorre, 1,1));
        
        

        Thread th = new Thread(this);
        th.start();

        addMouseListener(this);
        addKeyListener(this);
        addMouseMotionListener(this);
    }

    /**
     * Se ejectua el Thread
     */
    public void run() {
        while (true) {
            //checaColision();tr
            actualiza();
            repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                System.out.println("Error en " + ex.toString());
            }

        }
    }

    /**
     * This method updates..
     */
    public void actualiza() {
    for (int i = 0; i < tower.size(); i++) {
                Tower t = (Tower) tower.get(i);
                
        }
    }

    public void keyTyped(KeyEvent e) {
       
    }

    public void keyPressed(KeyEvent e) {
 
    if(e.getKeyCode() == KeyEvent.VK_LEFT)
    {
        for (int i = 0; i < tower.size(); i++) {
                Tower t = (Tower) tower.get(i);
                t.setAngle(t.getAngle()- 5);
        }
 
    }
    if(e.getKeyCode() == KeyEvent.VK_RIGHT)
    {
        for (int i = 0; i < tower.size(); i++) {
                Tower t = (Tower) tower.get(i);
                t.setAngle(t.getAngle()+ 5);
        }
    }
    }

    public void keyReleased(KeyEvent e) {
   
    }

    /**
     * Metodo mouseClicked
     *
     * sirve para que se detecten los clicks en los lugares deseados
     *
     * @param e
     */
    public void mouseClicked(MouseEvent e) {
        if (instr) {// si se esta en las instrucciones
            Rectangle rect = new Rectangle(45, 55, 94, 84);
            if (rect.contains(e.getPoint())) {
                instr = false;
                main = true;
                background = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/mainBackground.png"));
            }
        }
        if (main) { // si se esta en la pantalla de inicio
            Rectangle rect = new Rectangle(476, 430, 421, 44);
            // si se da click en el boton de SOLO
            if (rect.contains(e.getPoint())) {
                menu = true;
                main = false;
                background = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Menu.png"));
            }
            // si se da click en el boton de CO-OP
            rect.setLocation(476, 488);
            if (rect.contains(e.getPoint())) {
                menu = true;
                main = false;
                background = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Menu.png"));
            }
            // si se da click en el boton de INSTRUCTIONS
            rect.setLocation(476, 535);
            if (rect.contains(e.getPoint())) {
                instr = true;
                main = false;
                background = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Instrucciones.png"));
            }
        }
        if (menu) {// si se esta en la pantalla de menu
            Rectangle rect = new Rectangle(139, 232, 352, 164);
            if (rect.contains(e.getPoint())) {
                game = true;
                menu = false;
                background = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Nivel1.png"));
            }
            rect.setLocation(879, 232);
            if (rect.contains(e.getPoint())) {
                game = true;
                menu = false;
                background = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Nivel2.png"));
            }
            rect.setLocation(139, 496);
            if (rect.contains(e.getPoint())) {
                game = true;
                menu = false;
                background = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Nivel3.png"));
            }
            rect.setLocation(879, 496);
            if (rect.contains(e.getPoint())) {
                game = true;
                menu = false;
                background = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Nivel4.png"));
            }
        }
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
    
     /**
	 * Metodo <I>mouseDragged</I> sobrescrito de la interface <code>KeyListener</code>.<P>
	 * En este metodo maneja el evento que se genera al presionar una boton del mouse y arrastarlo.
	 * @param e es el <code>evento</code> que se genera en al presionar las teclas.
	 */
    public void mouseDragged(MouseEvent e){
       

        }
        
    
     /**
	 * Metodo <I>mouseMoved</I> sobrescrito de la interface <code>MouseMotionListener</code>.<P>
	 * En este metodo maneja el evento que se genera al arrastarlo sin presionar un boton.
	 * @param e es el <code>evento</code> que se genera en al presionar las teclas.
	 */
    public void mouseMoved(MouseEvent e){
    
       
    }
    

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
            dbgtower = dbImage.getGraphics();       
        }
        
        // Actualiza la imagen de fondo.
        dbg.setColor(getBackground());
        dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);
        dbgtower.setColor(getBackground());
        dbgtower.fillRect(0, 0, this.getSize().width, this.getSize().height);

        // Actualiza el Foreground.
        dbg.setColor(getForeground());
        paint1(dbg);
        
        //dibujar imagenes de torres
        dbgtower.setColor(getForeground());
        towerpaint1(dbgtower);
            
        // Dibuja la imagen actualizada
        g.drawImage(dbImage, 0, 0, this);
    }

    /**
     * Method used to paint images in the game
     *
     * @param g
     */
    public void paint1(Graphics g) {
        
        g.drawImage(background, 8, 31, this);
        if (game)
        {
        //Used for testing
        for (int i = 0; i < tower.size(); i++) {
                Tower t = (Tower) tower.get(i);
                g.setColor(Color.white);
                g.fillRect(t.getPosX(), t.getPosY(), t.getAncho(), -20);
                g.drawRect(t.getPosX(), t.getPosY(), t.getAncho(), -20);
                g.setColor(Color.black);         
                g.drawString("Angulo = " + (t.getAngle()-90), t.getPosX(), t.getPosY());
        }
        }
        
    }
    
    /**
     * Metodo para dibujar las torres que giran
     *
     * @param g
     * @param t
     */
    public void towerpaint1(Graphics g) {
        Graphics2D g2d = (Graphics2D) g; // Create a Java2D version of g.
        if (game) {
            for (int i = 0; i < tower.size(); i++) {
                Tower t = (Tower) tower.get(i);
                AffineTransform z = new AffineTransform();
                z.translate(t.getPosX(), t.getPosY());
                z.rotate(Math.toRadians(t.getAngle()), t.getAncho() / 2, t.getAlto() / 2);
                g2d.transform(z);
                g2d.drawImage(t.getAnimacion().getImagen(), 0, 0, this);
                try {
                    g2d.transform(z.createInverse());
                } catch (NoninvertibleTransformException e) {
                    //...
                }

            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        STTD defense = new STTD();
        defense.setVisible(true);
    }

}
