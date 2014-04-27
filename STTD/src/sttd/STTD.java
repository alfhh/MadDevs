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
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.lang.ArrayIndexOutOfBoundsException;
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

    private Animacion animNormal; // Animacion de la torre Normal
    private Animacion animDual; // Animacion de la torre Dual
    private Animacion animSniper; // Animacion de la torre Sniper
    private Animacion animFuerte; // Animacion de la torre DualFuerte
    private Animacion animQuad; // Animacion de la torre Quadrupeda
    private Animacion animLaser; // Animacion de la torre SniperLaser
    private Animacion animEnemigo; // Animacion del enemigo

    private LinkedList tower; // Lista de las Torres
    private LinkedList levelstart; // Lista de los puntos de comienzo del mapa
    private LinkedList wrench; // Lista de los enemigos
    private LinkedList mine; // Lista de las Minas
    private LinkedList bullet; // Lista de las balas
    private LinkedList towergraphics; // Lista de las imagenes de las torres

    private double rotacion; // Rotacion que se le dara a las torres
    private int grid[][]; // Grid conceptual del mapa
    private int towerid; //Valor utilizado para identificar el id de la torre sin importar su posición en la linked list
    private double testingangle = 0; //solo de prueba
    private int mousex; //Posición en X del mouse
    private int mousey; //Posición en Y del mouse
    private int countx; // Contador del tiempo de enemigos
    private int basex; // marca la localizacion en x de la base
    private int basey; // marca la localizacion en y de la base
    private int animrand; //diferentes animaciones
    private int wavecount; // numero de malos por oleada
    private int wave; // numero de oleada
    private int wavebegin; // tiempo antes que empieze la oleada
    private long tiempoActual;
    private long tiempoInicial;
    private AffineTransform identidad; // Variable tipo AffineTransform

    private boolean main; // booleano que muestra la pantalla principal
    private boolean menu; // booleano que muestra el menu de niveles
    private boolean instr; // booleano que muestra las instrucciones
    private boolean game; // booleano que deja que el juego corra
    private boolean wavego; // booleano que inicia la oleada
    private boolean bmine; // booleano que muestra si seleccionaron una mina

    //Checar si un punto esta dentro de un circulo
    public boolean inCircle(int circleX, int circleY, int clickX, int clickY, int radius) {
        return java.lang.Math.pow((circleX - clickX), 2) + java.lang.Math.pow((circleY - clickY), 2) < java.lang.Math.pow(radius, 2);
    }

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
        towerid = 0;
        countx = 50;
        grid = new int[23][40];
        wavego = false;
        wavecount = 0;
        wave = 0;
        wavebegin = 750;
        bmine = false;

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

        // Imagen del enemigo
        Image e = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/xwing.jpg"));
        animEnemigo = new Animacion();
        animEnemigo.sumaCuadro(e, 100);

        tower = new LinkedList();
        levelstart = new LinkedList();
        wrench = new LinkedList();
        towergraphics = new LinkedList();
        mine = new LinkedList();

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
        //Guarda el tiempo actual del sistema
        tiempoActual = System.currentTimeMillis();

        while (true) {
            if (game) {
                checaColision();
                actualiza();
            }
            repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                System.out.println("Error en " + ex.toString());
            }

        }
    }

    /**
     * Este metodo checa colisiones con objetos
     */
    public void checaColision() {

    }

    /**
     * This method updates..
     */
    public void actualiza() {

        //para animar
        //Determina el tiempo que ha transcurrido desde que el Applet inicio su ejecución
        long tiempoTranscurrido
                = System.currentTimeMillis() - tiempoActual;

        //Guarda el tiempo actual
        tiempoActual += tiempoTranscurrido;

        //Actualiza la animación en base al tiempo transcurrido de Torre Watulio
        for (int i = 0; i < tower.size(); i++) {
            Tower t = (Tower) tower.get(i);
            t.getAnimacion().actualiza(tiempoTranscurrido);
        }

        //Actualiza la animación en base al tiempo transcurrido de los malos
        for (int i = 0; i < wrench.size(); i++) {
            Enemy t = (Enemy) wrench.get(i);
            t.getAnimacion().actualiza(tiempoTranscurrido);
        }

        //Actualizacion de las minas
        for (int i = 0; i < mine.size(); i++) {
            Mine m = (Mine) mine.get(i);
            m.getAnimacion().actualiza(tiempoTranscurrido);
        }

        if (towerid > 0) {// Si se esta cargando una torre
            PointerInfo a = MouseInfo.getPointerInfo(); // Obtencion del mouse para seguirlo
            Point b = a.getLocation();
            try { // Intenta tomar la ultima torre del arreglo
                Tower t = (Tower) tower.getLast();
                // si la torreta esta dentro de la grid
                if (b.getX() < 1208 && b.getY() > 30 && b.getY() < 716) {
                    if (grid[((int) b.getY() - 31) / 30][((int) b.getX() - 8) / 30] == 1) {
                        //Lo acomoda en la matriz
                        t.setPosX(((int) b.getX()) - ((int) b.getX() - 8) % 30);
                        t.setPosY(((int) b.getY()) - ((int) b.getY()) % 30);
                    }
                } else {
                    // se pone en medio del cursor
                    t.setPosX((int) b.getX() - t.getAncho() / 2);
                    t.setPosY((int) b.getY() - t.getAlto() / 2);
                }
            } catch (NoSuchElementException n) {
                towerid = 0;
            }
        }
        if (bmine) {
            PointerInfo a = MouseInfo.getPointerInfo(); // Obtencion del mouse para seguirlo
            Point b = a.getLocation();
            try { // Intenta tomar la ultima torre del arreglo
                Mine m = (Mine) mine.getLast();
                // si la torreta esta dentro de la grid
                if (b.getX() < 1208 && b.getY() > 30 && b.getY() < 716) {
                    if (grid[((int) b.getY() - 31) / 30][((int) b.getX() - 8) / 30] == 0) {
                        //Lo acomoda en la matriz
                        m.setPosX(((int) b.getX()) - ((int) b.getX() - 8) % 30);
                        m.setPosY(((int) b.getY()) - ((int) b.getY()) % 30);
                    }
                } else {
                    // se pone en medio del cursor
                    m.setPosX((int) b.getX() - m.getAncho() / 2);
                    m.setPosY((int) b.getY() - m.getAlto() / 2);
                }
            } catch (NoSuchElementException n) {
                bmine = false;
            }
        }
        if (wavego) {
            countx--;
            if (countx == 0 && wavecount > 0) { // Addicion de un enemigo nuevo
                Point p = (Point) levelstart.get((int) (Math.random() * levelstart.size()));
                wrench.add(new Enemy((int) p.getX(), (int) p.getY(), animEnemigo, 1, (wave - 1) / 5 + 1, 50));
                countx = 100;
                wavecount--;
            }
            for (int i = 0; i < wrench.size(); i++) {
                Enemy w = (Enemy) wrench.get(i);
                // Cuando se encuentra excactamente en la posicion del cuadrante
                if ((w.getPosX() - 8) % 30 == 0 && (w.getPosY() - 31) % 30 == 0) {
                    Point p = new Point(w.getPosX(), w.getPosY());
                    w.getStart().setLocation(w.getEnd().getLocation());
                    boolean ready = false;
                    char past = w.getMov();
                    try {
                        // Si el cuadrante de abajo es camino y no va hacia arriba
                        if (grid[((int) p.getY() - 31) / 30 + 1][((int) p.getX() - 8) / 30] == 0
                                && past != 'u') {
                            w.getEnd().setLocation(p.getX(), p.getY() + 30);
                            w.setMov('d');
                            ready = true;
                        }
                    } catch (ArrayIndexOutOfBoundsException a) {
                    }
                    try {
                        // Si el cuadrante de arriba es camino y no va hacia abajo
                        if (grid[((int) p.getY() - 31) / 30 - 1][((int) p.getX() - 8) / 30] == 0
                                && past != 'd' && !ready) {
                            w.getEnd().setLocation(p.getX(), p.getY() - 30);
                            w.setMov('u');
                            ready = true;
                        }
                    } catch (ArrayIndexOutOfBoundsException a) {
                    }
                    try {
                        // Si el cuadrante de izquierda es camino y no va hacia derecha
                        if (grid[((int) p.getY() - 31) / 30][((int) p.getX() - 8) / 30 - 1] == 0
                                && past != 'r' && !ready) {
                            w.getEnd().setLocation(p.getX() - 30, p.getY());
                            w.setMov('l');
                            ready = true;
                        }
                    } catch (ArrayIndexOutOfBoundsException a) {
                    }
                    try {
                        // Si el cuadrante de derecha es camino y no va hacia izquierda
                        if (grid[((int) p.getY() - 31) / 30][((int) p.getX() - 8) / 30 + 1] == 0
                                && past != 'l') {
                            w.getEnd().setLocation(p.getX() + 30, p.getY());
                            w.setMov('r');
                        }
                    } catch (ArrayIndexOutOfBoundsException a) {
                    }
                }
                switch (w.getMov()) {
                    case 'r':
                        w.setPosX(w.getPosX() + w.getSpeed()); // Va hacia la derecha
                        w.setAngle(0);
                        break;
                    case 'l':
                        w.setPosX(w.getPosX() - w.getSpeed()); // Va hacia la izquierda
                        w.setAngle(180);
                        break;
                    case 'd':
                        w.setPosY(w.getPosY() + w.getSpeed()); // Va hacia abajo
                        w.setAngle(90);
                        break;
                    case 'u':
                        w.setPosY(w.getPosY() - w.getSpeed()); // Va hacia arriba
                        w.setAngle(270);
                        break;
                    default:
                        break;
                }

                // Si el enemigo llega a la base
                if (grid[((int) w.getPosY() - 31) / 30][((int) w.getPosX() - 8) / 30] == 2) {
                    wrench.remove(i); // Desaparece
                }
            }

            //Disparar bala a la dirección deseada
            PointerInfo a = MouseInfo.getPointerInfo(); // Obtencion del mouse para seguirlo
            Point b = a.getLocation();
            int priority; //-1 = no apuntar a nada
            for (int i = 0; i < tower.size(); i++) {
                Tower t = (Tower) tower.get(i);
                priority = -1;
                for (int j = wrench.size() - 1; j >= 0; j--) {
                    Enemy w = (Enemy) wrench.get(j);
                    if (inCircle(t.getPosX() + t.getAncho() / 2, t.getPosY() + t.getAlto() / 2, w.getPosX() + w.getAncho() / 2, w.getPosY() + w.getAlto() / 2, (int) t.getRange())) {
                        priority = j;
                    }
                    if (priority != -1) {
                        if (!t.isMine()) //si es torre apuntar a ella 
                        {
                            Enemy g = (Enemy) wrench.get(priority);
                            double bullet_angle = Math.atan2((t.getPosX() + t.getAncho() / 2) - (g.getPosX() + g.getAncho() / 2), (t.getPosY() + t.getAlto() / 2) - (g.getPosY() + g.getAlto() / 2)) - Math.PI / 2;
                            t.setAngle(Math.toDegrees(-bullet_angle - Math.PI));
                        }
                        //Aqui iria la acción de disparar
                    }
                }
            }
            if (wavecount == 0 && wrench.isEmpty()) {
                wavego = false;
                wavebegin = 499;
            }
        } else {
            if (wavebegin == 0) {
                wavego = true;
                countx = 100;
                wave++;
                wavecount = 19 + wave;
            } else {
                wavebegin--;
            }
        }
        for (int i = 0; i < tower.size(); i++) {
            Tower t = (Tower) tower.get(i);

        }
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

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
        if (towerid > 0) {
            Tower t = (Tower) tower.getLast();
            if (t.getPosX() < 1180) {// se planta una torreta en la grid
                towerid = 0;
                t.setSet(true);
                grid[(t.getPosY() - 30) / 30][(t.getPosX() - 8) / 30] = t.getId();
            } else {                // Se remueve la torreta
                tower.removeLast();
                towerid = 0;
            }
        }
        if (bmine) {
            Mine m = (Mine) mine.getLast();
            if (m.getPosX() < 1180) {// se planta una torreta en la grid
                bmine = false;
                m.setSet(true);
            } else {                // Se remueve la torreta
                mine.removeLast();
                bmine = false;
            }
        }
        if (game) {
            if (e.getX() > 1200 && towerid == 0) { // si el mouse esta en el HUD
                // creacion de torretas
                if (new Rectangle(1268, 121, 30, 30).contains(e.getPoint())) {
                    //Torre normal
                    towerid = 3;
                    tower.add(new Tower(e.getX(), e.getY(), animNormal, towerid, 1, 3, 5, 50, 100, 90, false));
                }
                if (new Rectangle(1238, 181, 30, 30).contains(e.getPoint())) {
                    //Torre dual
                    towerid = 4;
                    tower.add(new Tower(e.getX(), e.getY(), animDual, towerid, 1, 4, 7, 25, 250, 96, false));
                }
                if (new Rectangle(1298, 181, 30, 30).contains(e.getPoint())) {
                    //Torre sniper
                    towerid = 5;
                    tower.add(new Tower(e.getX(), e.getY(), animSniper, towerid, 1, 15, 25, 75, 350, 130, false));
                }
                if (new Rectangle(1208, 231, 30, 30).contains(e.getPoint())) {
                    //Torre quad
                    towerid = 6;
                    tower.add(new Tower(e.getX(), e.getY(), animQuad, towerid, 1, 5, 10, 12, 550, 100, false));
                }
                if (new Rectangle(1268, 231, 30, 30).contains(e.getPoint())) {
                    //Torre dual fuerte
                    towerid = 7;
                    tower.add(new Tower(e.getX(), e.getY(), animFuerte, towerid, 1, 8, 12, 20, 780, 145, false));
                }
                if (new Rectangle(1328, 231, 30, 30).contains(e.getPoint())) {
                    //Torre laser
                    towerid = 8;
                    tower.add(new Tower(e.getX(), e.getY(), animLaser, towerid, 1, 25, 50, 80, 1050, 240, false));
                }

                if (new Rectangle(1238, 291, 30, 30).contains(e.getPoint())) {
                    //Torre wat

                    //Animación de watmine, que enverdad es una torre pero parece mina
                    Image mine1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine1.png"));
                    Image mine2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine2.png"));
                    Image mine3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine3.png"));
                    Image mine4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine4.png"));
                    Image mine5 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine5.png"));
                    Image mine6 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine6.png"));
                    Image mine7 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine7.png"));
                    Image mine8 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine8.png"));
                    Image mine9 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine9.png"));
                    Image mine10 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine10.png"));
                    Image mine11 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine11.png"));
                    Image mine12 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine12.png"));
                    Image mine13 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine13.png"));
                    Image mine14 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine14.png"));
                    Image mine15 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine15.png"));
                    Image mine16 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine16.png"));
                    Image mine17 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine17.png"));
                    Image mine18 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine18.png"));
                    Image mine19 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine19.png"));
                    Image mine20 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine20.png"));
                    Image mine21 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine21.png"));
                    Image mine22 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine22.png"));
                    Image mine23 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine23.png"));
                    Image mine24 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine24.png"));
                    Image mine25 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine25.png"));
                    Image mine26 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine26.png"));
                    Image mine27 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine27.png"));
                    Image mine28 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine28.png"));
                    Image mine29 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine29.png"));

                    //Se crea la animación
                    Animacion animWat = new Animacion();
                    animWat.sumaCuadro(mine1, ((int) (Math.random() * (16000))) + 2000);
                    animWat.sumaCuadro(mine2, 100);
                    animrand = ((int) Math.random() * (2));
                    if (animrand < 1) {
                        animWat.sumaCuadro(mine1, ((int) (Math.random() * (16000))) + 2000);
                        animWat.sumaCuadro(mine2, 100);
                        animWat.sumaCuadro(mine1, ((int) (Math.random() * (16000))) + 2000);
                        animWat.sumaCuadro(mine2, 100);
                        animWat.sumaCuadro(mine1, ((int) (Math.random() * (16000))) + 2000);
                        animWat.sumaCuadro(mine3, 200);
                        animWat.sumaCuadro(mine4, 50);
                        animWat.sumaCuadro(mine5, 100);
                        animWat.sumaCuadro(mine6, 100);
                        animWat.sumaCuadro(mine7, 400);
                        animWat.sumaCuadro(mine8, 100);
                        animWat.sumaCuadro(mine9, 300);
                        animWat.sumaCuadro(mine10, 2000);
                        animWat.sumaCuadro(mine11, 400);
                        animWat.sumaCuadro(mine12, 300);
                        animWat.sumaCuadro(mine13, 1000);
                        animWat.sumaCuadro(mine14, 200);
                        animWat.sumaCuadro(mine13, 400);
                        animWat.sumaCuadro(mine15, 1000);
                        animWat.sumaCuadro(mine16, 400);
                        animWat.sumaCuadro(mine17, 100);
                        animWat.sumaCuadro(mine18, 100);
                        animWat.sumaCuadro(mine19, 100);
                        animWat.sumaCuadro(mine20, 100);
                        animWat.sumaCuadro(mine21, 100);
                        animWat.sumaCuadro(mine22, 400);
                        animWat.sumaCuadro(mine22, 30);
                        animWat.sumaCuadro(mine23, 50);
                        animWat.sumaCuadro(mine24, 50);
                        animWat.sumaCuadro(mine25, 50);
                        animWat.sumaCuadro(mine26, 50);
                        animWat.sumaCuadro(mine27, 50);
                        animWat.sumaCuadro(mine28, 50);
                        animWat.sumaCuadro(mine29, 100);
                    }
                    towerid = 9;
                    tower.add(new Tower(e.getX(), e.getY(), animWat, towerid, 1, 10, 200, 200, 4200, 60, true));
                }
                if (new Rectangle(1298, 291, 30, 30).contains(e.getPoint())) {
                    //Mina
                    bmine = true;
                    Animacion animMine; // Animacion de la mina 
                    //Imagen de la mina animada
                    Image nmine1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Mine_animation/mine1.png"));
                    Image nmine2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Mine_animation/mine2.png"));
                    Image nmine3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Mine_animation/mine3.png"));
                    Image nmine4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Mine_animation/mine4.png"));
                    Image nmine5 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Mine_animation/mine5.png"));
                    Image nmine6 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Mine_animation/mine6.png"));

                    //Se crea la animación
                    animMine = new Animacion();
                    animMine.sumaCuadro(nmine1, 200);
                    animMine.sumaCuadro(nmine2, 200);
                    animMine.sumaCuadro(nmine3, 200);
                    animMine.sumaCuadro(nmine4, 200);
                    animMine.sumaCuadro(nmine5, 200);
                    animMine.sumaCuadro(nmine6, 200);
                    mine.add(new Mine(e.getX(), e.getY(), animMine, 1));
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
                //nivel 1
                game = true;
                menu = false;
                background = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Nivel1.png"));
                int b[][] = {
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2},
                    {1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 2, 2, 2},
                    {1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 2, 2, 2},
                    {1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1},
                    {1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1},
                    {1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1},
                    {1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1},
                    {1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1},
                    {1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},};
                grid = b;
                levelstart.add(new Point(8, 61));
                levelstart.add(new Point(8, 661));
                basex = 1154;
                basey = 357;
            }
            rect.setLocation(879, 232);
            if (rect.contains(e.getPoint())) {
                //Nivel 2
                game = true;
                menu = false;
                background = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Nivel2.png"));
                int b[][] = {
                    {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 2, 2, 2},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 2, 2, 2},
                    {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1},
                    {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 69, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},};
                grid = b;
                levelstart.add(new Point(158, 31));
                levelstart.add(new Point(8, 181));
                levelstart.add(new Point(158, 691));
                levelstart.add(new Point(8, 541));
                basex = 1154;
                basey = 357;
            }
            rect.setLocation(139, 496);
            if (rect.contains(e.getPoint())) {
                //Nivel 3
                game = true;
                menu = false;
                background = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Nivel3.png"));
                int b[][] = {
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1},
                    {1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1},
                    {1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1},
                    {1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1},
                    {1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                    {0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 2, 2, 2},
                    {1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2},
                    {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 2, 2, 2},
                    {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1},
                    {1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1},
                    {1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1},
                    {1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1},
                    {1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1},
                    {1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1},
                    {1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},};
                grid = b;
                levelstart.add(new Point(8, 331));
                levelstart.add(new Point(398, 31));
                levelstart.add(new Point(398, 691));
                basex = 1154;
                basey = 357;
            }
            rect.setLocation(879, 496);
            if (rect.contains(e.getPoint())) {
                //nivel 4
                game = true;
                menu = false;
                background = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Nivel4.png"));
                int b[][] = {
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1},
                    {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1},
                    {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1},
                    {1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1},
                    {1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1},
                    {1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1},
                    {1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1},
                    {0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0},
                    {1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1},
                    {1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1},
                    {1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1},
                    {1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1},
                    {1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
                    {1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
                    {1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},};
                grid = b;
                levelstart.add(new Point(8, 361));
                levelstart.add(new Point(578, 31));
                levelstart.add(new Point(1178, 361));
                levelstart.add(new Point(578, 691));
                basex = 585;
                basey = 357;

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
     * Metodo <I>mouseDragged</I> sobrescrito de la interface
     * <code>KeyListener</code>.<P>
     * En este metodo maneja el evento que se genera al presionar una boton del
     * mouse y arrastarlo.
     *
     * @param e es el <code>evento</code> que se genera en al presionar las
     * teclas.
     */
    public void mouseDragged(MouseEvent e) {

    }

    /**
     * Metodo <I>mouseMoved</I> sobrescrito de la interface
     * <code>MouseMotionListener</code>.<P>
     * En este metodo maneja el evento que se genera al arrastarlo sin presionar
     * un boton.
     *
     * @param e es el <code>evento</code> que se genera en al presionar las
     * teclas.
     */
    public void mouseMoved(MouseEvent e) {

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

        dbgtower.setColor(getForeground());//dibujar imagenes de torres
        dbgtower.setColor(getForeground());
        towerpaint1(dbgtower);
        g.drawImage(dbImage, 0, 0, this);
    }

    /**
     * Method used to paint images in the game
     *
     * @param g
     */
    public void paint1(Graphics g) {

        g.drawImage(background, 8, 31, this);
        if (game) {
            if (!wavego) {
                g.setFont(new Font("Consolas", Font.PLAIN, 50));
                g.drawString("Wave starts in: ", 400, 400);
                g.drawString("" + ((wavebegin * 20) / 1000 + 1), 570, 450);
            }
            g.setFont(new Font("Consolas", Font.PLAIN, 30));
            g.setColor(new Color(1346085));
            g.drawString("" + wave, 1312, 437);
            //Used for testing
            for (int i = 0; i < mine.size(); i++) {
                Mine m = (Mine) mine.get(i);
                g.drawImage(m.getAnimacion().getImagen(), m.getPosX(), m.getPosY(), this);
            }
            for (int i = 0; i < tower.size(); i++) {
                Tower t = (Tower) tower.get(i);
                g.setColor(Color.white);
                //Dibujar circulos del rango
                g.drawOval(t.getPosX() + t.getAncho() / 2 - (int) t.getRange(), t.getPosY() + t.getAlto() / 2 - (int) t.getRange(), (int) t.getRange() * 2, (int) t.getRange() * 2);
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

            for (int i = 0; i < wrench.size(); i++) {
                Enemy t = (Enemy) wrench.get(i);
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
