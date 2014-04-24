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
import java.awt.PointerInfo;
import java.awt.Point;
import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

public class STTD extends JFrame implements Runnable, KeyListener, MouseListener {

    private Graphics dbg; // Graphic Object
    private Graphics dbgtower; // Graphic Object
    private Image dbImage; // Image
    private Image background; // Background Image
    private Image imgmenu; // Menu Image
    private Image t; // Torreta Image

    private Animacion animNormal; // Animacion de la torre
    private Animacion animDual; // Animacion de la torre
    private Animacion animSniper; // Animacion de la torre
    private Animacion animFuerte; // Animacion de la torre
    private Animacion animQuad; // Animacion de la torre
    private Animacion animLaser; // Animacion de la torre
    private Animacion animWat; // Animacion de la torre

    private char tipo;

    private LinkedList tower; // Lista de las Torres
    private LinkedList towergraphics; // Lista de las imagenes de las torres

    private double rotacion; // Rotacion que se le dara a las torres
    private int grid[][]; // Grid conceptual del mapa
    private int towerid; //Valor utilizado para identificar el id de la torre sin importar su posición en la linked list
    private double testingangle = 0; //solo de prueba

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
        tipo = 0;
        grid = new int[40][23];

        // Images
        background = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/mainBackground.png"));
        t = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/torretanormal.png"));
        animNormal = new Animacion();
        animNormal.sumaCuadro(t, 100);
        t = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/torretadual.png"));
        animDual = new Animacion();
        animDual.sumaCuadro(t, 100);
        t = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/torretasniper.png"));
        animSniper = new Animacion();
        animSniper.sumaCuadro(t, 100);
        t = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/torretadualfuerte.png"));
        animFuerte = new Animacion();
        animFuerte.sumaCuadro(t, 100);
        t = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/torretaquadruple.png"));
        animQuad = new Animacion();
        animQuad.sumaCuadro(t, 100);
        t = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/torretasniperlaser.png"));
        animLaser = new Animacion();
        animLaser.sumaCuadro(t, 100);
        t = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/torretawatulio.png"));
        animWat = new Animacion();
        animWat.sumaCuadro(t, 100);

        // Tower
        tower = new LinkedList();
        towergraphics = new LinkedList();

        Thread th = new Thread(this);
        th.start();

        addMouseListener(this);
        addKeyListener(this);
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
        if (tipo > 0) {
            PointerInfo a = MouseInfo.getPointerInfo();
            Point b = a.getLocation();
            Tower t = (Tower) tower.getLast();
            if (b.getX() < 1208) {
                t.setPosX(((int) b.getX() + 30) - ((int) b.getX() - 8) % 30);
                t.setPosY(((int) b.getY() + 31) - ((int) b.getY()) % 30);
            } else {
                t.setPosX((int) b.getX() + t.getAncho() / 2);
                t.setPosY((int) b.getY() + t.getAlto() / 2);

            }

        }

    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            for (int i = 0; i < tower.size(); i++) {
                Tower t = (Tower) tower.get(i);
                t.setAngle(t.getAngle() + 0.2);
            }

        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            for (int i = 0; i < tower.size(); i++) {
                Tower t = (Tower) tower.get(i);
                t.setAngle(t.getAngle() - 0.2);
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
        if (tipo > 0) {
            if (e.getX() < 1208) {
                tipo = 0;
            } else {
                tower.removeLast();
                tipo = 0;
            }
        }
        if (game) {
            if (e.getX() > 1200 && tipo == 0) {
                if (new Rectangle(1268, 121, 30, 30).contains(e.getPoint())) {
                    tipo = 1;
                    tower.add(new Tower(e.getX(), e.getY(), animNormal, 0, 0));
                }
                if (new Rectangle(1238, 181, 30, 30).contains(e.getPoint())) {
                    tipo = 2;
                    tower.add(new Tower(e.getX(), e.getY(), animDual, 0, 0));
                }
                if (new Rectangle(1298, 181, 30, 30).contains(e.getPoint())) {
                    tipo = 3;
                    tower.add(new Tower(e.getX(), e.getY(), animSniper, 0, 0));
                }
                if (new Rectangle(1208, 231, 30, 30).contains(e.getPoint())) {
                    tipo = 4;
                    tower.add(new Tower(e.getX(), e.getY(), animQuad, 0, 0));
                }
                if (new Rectangle(1268, 231, 30, 30).contains(e.getPoint())) {
                    tipo = 5;
                    tower.add(new Tower(e.getX(), e.getY(), animFuerte, 0, 0));
                }
                if (new Rectangle(1328, 231, 30, 30).contains(e.getPoint())) {
                    tipo = 6;
                    tower.add(new Tower(e.getX(), e.getY(), animLaser, 0, 0));
                }
                if (new Rectangle(1268, 291, 30, 30).contains(e.getPoint())) {
                    tipo = 7;
                    tower.add(new Tower(e.getX(), e.getY(), animWat, 0, 0));
                }
            }
        }
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

        dbgtower.setColor(getForeground());
        //dibujar imagenes de torres
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
                z.rotate(t.getAngle(), -t.getAncho() / 2, -t.getAlto() / 2);
                g2d.transform(z);
                g2d.drawImage(t.getAnimacion().getImagen(), (int) -(t.getAncho()), (int) -(t.getAlto()), this);
                try {
                    g2d.transform(z.createInverse());
                } catch (NoninvertibleTransformException e) {
                    //...
                }

//         g2d.translate(1368/2, 730/2); // Translate the center of our coordinates.
//         g2d.rotate(testingangle, t.getPosX()+ t.getAncho()/2, t.getPosY()+ t.getAlto()/2);
//         g2d.drawImage(t.getAnimacion().getImagen(), t.getPosX(), t.getPosY(), this);
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
