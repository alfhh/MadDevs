/**
 * Star Wars Tower Defense Developed by Mad Devs "Stay on the right side, the
 * Mad side" Version: Alpha Date: 4/07/2014
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

    private Animacion animIntro; // Animacion de intro

    private Animacion animNormal; // Animacion de la torre Normal
    private Animacion animDual; // Animacion de la torre Dual
    private Animacion animSniper; // Animacion de la torre Sniper
    private Animacion animFuerte; // Animacion de la torre DualFuerte
    private Animacion animQuad; // Animacion de la torre Quadrupeda
    private Animacion animLaser; // Animacion de la torre SniperLaser
    private Animacion animEnemigo; // Animacion del enemigo
    private Animacion animBala; // Animacion del enemigo

    private LinkedList tower; // Lista de las Torres
    private LinkedList levelstart; // Lista de los puntos de comienzo del mapa
    private LinkedList wrench; // Lista de los enemigos
    private LinkedList mine; // Lista de las Minas
    private LinkedList bullet; // Lista de las balas
    private LinkedList towergraphics; // Lista de las imagenes de las torres

    private double rotacion; // Rotacion que se le dara a las torres
    private int grid[][]; // Grid conceptual del mapa
    private int life; // Variable que marca la vida de la base
    private int lifeini; // Variable que marca la vida de la base
    private int towerid; //Valor utilizado para identificar el id de la torre sin importar su posición en la linked list
    private double testingangle = 0; //solo de prueba
    private int mousex; //Posición en X del mouse
    private int mousey; //Posición en Y del mouse
    private int countx; // Contador del tiempo de enemigos
    private int basex; // marca la localizacion en x de la base
    private int basey; // marca la localizacion en y de la base
    private double animrand; //diferentes animaciones
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
        wavebegin = 150;
        bmine = false;
        lifeini = 20;
        life = lifeini;

        // Images
        Image in1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Intro/1.png"));
        Image in2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Intro/2.png"));
        Image in3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Intro/3.png"));
        Image in4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Intro/4.png"));
        Image in5 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Intro/5.png"));
        Image in6 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Intro/1.png"));
        Image in7 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Intro/6.png"));
        Image in8 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Intro/7.png"));
        Image in9 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Intro/8.png"));
        Image in10 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Intro/9.png"));
        Image in11 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Intro/1.png"));
        Image in12 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Intro/10.png"));
        Image in13 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Intro/11.png"));
        Image in14 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Intro/12.png"));
        Image in15 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Intro/13.png"));
        Image in16 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Intro/1.png"));
        Image in17 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Intro/14.png"));
        Image in18 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Intro/15.png"));
        Image in19 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Intro/16.png"));
        Image in20 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Intro/17.png"));
        Image in21 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Intro/18.png"));
        Image in22 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Intro/19.png"));

        animIntro = new Animacion();

        animIntro.sumaCuadro(in1,20);
        animIntro.sumaCuadro(in2,20);
        animIntro.sumaCuadro(in3,20);
        animIntro.sumaCuadro(in4,20);
        animIntro.sumaCuadro(in5,20);
        animIntro.sumaCuadro(in6,20);
        animIntro.sumaCuadro(in7,20);
        animIntro.sumaCuadro(in8,20);
        animIntro.sumaCuadro(in9,20);
        animIntro.sumaCuadro(in10,20);
        animIntro.sumaCuadro(in11,20);
        animIntro.sumaCuadro(in12,20);
        animIntro.sumaCuadro(in13,20);
        animIntro.sumaCuadro(in14,20);
        animIntro.sumaCuadro(in15,20);
        animIntro.sumaCuadro(in16,20);
        animIntro.sumaCuadro(in17,20);
        animIntro.sumaCuadro(in18,20);
        animIntro.sumaCuadro(in19,100);
        animIntro.sumaCuadro(in20,100);
        animIntro.sumaCuadro(in21,100);
        animIntro.sumaCuadro(in22,100);
        
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
        t = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/bala.png"));
        animBala = new Animacion();
        animBala.sumaCuadro(t, 100);

        // Imagen del enemigo
        Image e = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/xwing.jpg"));
        animEnemigo = new Animacion();
        animEnemigo.sumaCuadro(e, 100);

        tower = new LinkedList();
        levelstart = new LinkedList();
        wrench = new LinkedList();
        towergraphics = new LinkedList();
        mine = new LinkedList();
        bullet = new LinkedList();

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
        for (int i = 0; i < wrench.size(); i++) {
            Enemy e = (Enemy) wrench.get(i);
            for (int j = 0; j < mine.size(); j++) {
                Mine m = (Mine) mine.get(j);
                if (m.getSet() && e.getPerimetro().intersects(m.getPerimetro())) {
                    e.setHealth(e.getHealth() - m.getDam());
                    m.setExp(117);
                    m.setSet(false);
                    Image i1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Mine_animation/mineexp1.png"));
                    Image i2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Mine_animation/mineexp2.png"));
                    Image i3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Mine_animation/mineexp3.png"));
                    Image i4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Mine_animation/mineexp4.png"));
                    Image i5 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Mine_animation/mineexp5.png"));
                    Image i6 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Mine_animation/mineexp6.png"));
                    Image i7 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Mine_animation/mineexp7.png"));
                    Image i8 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Mine_animation/mineexp8.png"));
                    Image i9 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Mine_animation/mineexp9.png"));
                    Image i10 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Mine_animation/mineexp10.png"));
                    Image i11 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Mine_animation/mineexp11.png"));
                    Image i12 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Mine_animation/mineexp12.png"));

                    Animacion a = new Animacion();
                    a.sumaCuadro(i1, 100);
                    a.sumaCuadro(i2, 100);
                    a.sumaCuadro(i3, 100);
                    a.sumaCuadro(i4, 100);
                    a.sumaCuadro(i5, 100);
                    a.sumaCuadro(i6, 100);
                    a.sumaCuadro(i7, 100);
                    a.sumaCuadro(i8, 100);
                    a.sumaCuadro(i9, 100);
                    a.sumaCuadro(i10, 100);
                    a.sumaCuadro(i11, 100);
                    a.sumaCuadro(i12, 100);

                    m.setAnimacion(a);

                }
            }
            for (int j = 0; j < bullet.size(); j++) {
                Bullet bl = (Bullet) bullet.get(j);
                if (e.getPerimetro().intersects(bl.getPerimetro())) {
                    e.setHealth(e.getHealth() - bl.getDamage());
                    Tower t = (Tower) tower.get(bl.getTower());
                    t.Exp();
                    bl.destroy();
                    // Si el enemigo no tiene vida;
                if (e.getHealth() <= 0) {
                    wrench.remove(i); // Desaparece
                }
                break;
                }

            }
        }
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
        for (int i = 0; i < tower.size() - 1; i++) {
            Tower t = (Tower) tower.get(i);
            if (t.getPosX() > 1200) {
                tower.remove(i);
            } else {
                t.getAnimacion().actualiza(tiempoTranscurrido);
            }
        }

        //Actualiza la animación en base al tiempo transcurrido de los malos
        for (int i = 0; i < wrench.size(); i++) {
            Enemy t = (Enemy) wrench.get(i);
            t.getAnimacion().actualiza(tiempoTranscurrido);
        }

        //Actualizacion de las minas
        for (int i = 0; i < mine.size(); i++) {
            Mine m = (Mine) mine.get(i);
            if (m.getSet() && m.getPosX() > 1200 || m.getExp() == -1) {
                mine.remove(i);
            } else {
                m.getAnimacion().actualiza(tiempoTranscurrido);
            }
            if (m.getExp() > 0) {
                m.Exp();
            }
        }
        
        //Actualiza la animación del intro
        animIntro.actualiza(tiempoTranscurrido);
        

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
        
        //Movimiento de las balas
            for (int i = 0; i < bullet.size(); i++) {
                Bullet t = (Bullet) bullet.get(i);
                if (!t.distanceTime()) {
                    t.setPosX(t.getPosX() + (int) (t.getSpeed() * Math.cos(Math.toRadians(t.getAngle()))));
                    t.setPosY(t.getPosY() + (int) (t.getSpeed() * Math.sin(Math.toRadians(t.getAngle()))));
                } else {
                    bullet.remove(i);
                }
            }
            
        if (wavego) {
            countx--;
            if (countx == 0 && wavecount > 0) { // Addicion de un enemigo nuevo
                Point p = (Point) levelstart.get((int) (Math.random() * levelstart.size()));
                wrench.add(new Enemy((int) p.getX(), (int) p.getY(), animEnemigo, 1, 5, (50 + ((int) Math.pow(wave - 1, 2)))));
                countx = 100;
                wavecount--;
            }
            for (int i = 0; i < wrench.size(); i++) {
                Enemy w = (Enemy) wrench.get(i);
                w.addLifeTime();
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
                    life--;
                }
            }

            //Disparar bala a la dirección deseada
            PointerInfo a = MouseInfo.getPointerInfo(); // Obtencion del mouse para seguirlo
            Point b = a.getLocation();
            int priority; //-1 = no apuntar a nada
            int lifetimep; //ver quien es el que va más avanzado en el area
            for (int i = 0; i < tower.size(); i++) {
                Tower t = (Tower) tower.get(i);
                priority = -1;
                lifetimep = 0;
                for (int j = wrench.size() - 1; j >= 0; j--) {
                    Enemy w = (Enemy) wrench.get(j);
                    if (inCircle(t.getPosX() + t.getAncho() / 2, t.getPosY() + t.getAlto() / 2, w.getPosX() + w.getAncho() / 2, w.getPosY() + w.getAlto() / 2, (int) t.getRange())) {
                        if (w.getLifeTime() > lifetimep)
                        {
                            priority = j;
                        }
                    }
                    if (priority != -1) {
                        if (!t.isMine()) //si es torre apuntar a ella 
                        {
                            Enemy g = (Enemy) wrench.get(priority);
                            double bullet_angle = Math.atan2((t.getPosX() + t.getAncho() / 2) - (int) (g.getPosX() + g.getAncho() / 2 + ((g.getAncho() / 2) * Math.cos(Math.toRadians(g.getAngle())))),
                                    (t.getPosY() + t.getAlto() / 2) - (int) (g.getPosY() + g.getAlto() / 2 - 1 + ((g.getAlto() / 2 - 2) * Math.sin(Math.toRadians(g.getAngle()))))) - Math.PI / 2;
                            t.setAngle(Math.toDegrees(-bullet_angle - Math.PI));
                        }

                    }
                }

                //Aqui iria la acción de disparar
                if (t.canShoot() && priority != -1) {
                    int randomizer = (int) (Math.random() * (2));
                    switch (t.getId()) {
                        case 3: //Torre normal
                            Bullet b1 = new Bullet((int) (t.getPosX() + t.getAncho() / 2 + ((t.getAncho() / 2) * Math.cos(Math.toRadians(t.getAngle())))),
                                    (int) (t.getPosY() + t.getAlto() / 2 - 1 + ((t.getAlto() / 2 - 2) * Math.sin(Math.toRadians(t.getAngle())))), animBala, t.getDamage(), t.getSpeed(), t.getAngle(), (int) t.getRange(), t.getPlayer(), i);
                            bullet.add(b1);
                            t.shoot();
                            break;

                        case 4: //Torre dual
                            randomizer = (int) (Math.random() * (2));
                            if (randomizer < 1) {
                                Bullet b2 = new Bullet((int) (t.getPosX() + t.getAncho() / 2 + ((t.getAncho() / 2) * Math.cos(Math.toRadians(t.getAngle())))),
                                        (int) (t.getPosY() + t.getAlto() / 2 + 5 + ((t.getAlto() / 2) * Math.sin(Math.toRadians(t.getAngle())))), animBala, t.getDamage(), t.getSpeed(), t.getAngle(), (int) t.getRange(), t.getPlayer(), 1);
                                bullet.add(b2);
                            } else {
                                Bullet b2 = new Bullet((int) (t.getPosX() + t.getAncho() / 2 + ((t.getAncho() / 2) * Math.cos(Math.toRadians(t.getAngle())))),
                                        (int) (t.getPosY() + t.getAlto() / 2 - 5 + ((t.getAlto() / 2) * Math.sin(Math.toRadians(t.getAngle())))), animBala, t.getDamage(), t.getSpeed(), t.getAngle(), (int) t.getRange(), t.getPlayer(), 1);
                                bullet.add(b2);
                            }
                            t.shoot();
                            break;

                        case 5: //sniper

                            Bullet b3 = new Bullet((int) (t.getPosX() + t.getAncho() / 2 + ((t.getAncho() / 2 - 5) * Math.cos(Math.toRadians(t.getAngle())))),
                                    (int) (t.getPosY() + t.getAlto() / 2 - 1 + ((t.getAlto() / 2 - 2) * Math.sin(Math.toRadians(t.getAngle())))), animBala, t.getDamage(), t.getSpeed(), t.getAngle(), (int) t.getRange(), t.getPlayer(), 1);
                            bullet.add(b3);
                            t.shoot();
                            break;

                        case 6: //quad

                            randomizer = (int) (Math.random() * (5));
                            if (randomizer < 1) {
                                Bullet b2 = new Bullet((int) (t.getPosX() + t.getAncho() / 2 + ((t.getAncho() / 2) * Math.cos(Math.toRadians(t.getAngle())))),
                                        (int) (t.getPosY() + t.getAlto() / 2 + 5 + ((t.getAlto() / 2) * Math.sin(Math.toRadians(t.getAngle())))), animBala, t.getDamage(), t.getSpeed(), t.getAngle(), (int) t.getRange(), t.getPlayer(), 1);
                                bullet.add(b2);
                            } else {
                                if (randomizer < 2) {

                                    Bullet b2 = new Bullet((int) (t.getPosX() + t.getAncho() / 2 + ((t.getAncho() / 2) * Math.cos(Math.toRadians(t.getAngle())))),
                                            (int) (t.getPosY() + t.getAlto() / 2 - 5 + ((t.getAlto() / 2) * Math.sin(Math.toRadians(t.getAngle())))), animBala, t.getDamage(), t.getSpeed(), t.getAngle(), (int) t.getRange(), t.getPlayer(), 1);
                                    bullet.add(b2);
                                } else {
                                    if (randomizer < 3) {
                                        Bullet b2 = new Bullet((int) (t.getPosX() + t.getAncho() / 2 + ((t.getAncho() / 2) * Math.cos(Math.toRadians(t.getAngle())))),
                                                (int) (t.getPosY() + t.getAlto() / 2 - 10 + ((t.getAlto() / 2) * Math.sin(Math.toRadians(t.getAngle())))), animBala, t.getDamage(), t.getSpeed(), t.getAngle(), (int) t.getRange(), t.getPlayer(), 1);
                                        bullet.add(b2);
                                    } else {
                                        Bullet b2 = new Bullet((int) (t.getPosX() + t.getAncho() / 2 + ((t.getAncho() / 2) * Math.cos(Math.toRadians(t.getAngle())))),
                                                (int) (t.getPosY() + t.getAlto() / 2 + 10 + ((t.getAlto() / 2) * Math.sin(Math.toRadians(t.getAngle())))), animBala, t.getDamage(), t.getSpeed(), t.getAngle(), (int) t.getRange(), t.getPlayer(), 1);
                                        bullet.add(b2);
                                    }

                                }
                            }
                            t.shoot();
                            break;

                        case 7: //fuerte

                            randomizer = (int) (Math.random() * (2));
                            if (randomizer < 1) {
                                Bullet b2 = new Bullet((int) (t.getPosX() + t.getAncho() / 2 + ((t.getAncho() / 2) * Math.cos(Math.toRadians(t.getAngle())))),
                                        (int) (t.getPosY() + t.getAlto() / 2 + 5 + ((t.getAlto() / 2) * Math.sin(Math.toRadians(t.getAngle())))), animBala, t.getDamage(), t.getSpeed(), t.getAngle(), (int) t.getRange(), t.getPlayer(), 1);
                                bullet.add(b2);
                            } else {
                                Bullet b2 = new Bullet((int) (t.getPosX() + t.getAncho() / 2 + ((t.getAncho() / 2) * Math.cos(Math.toRadians(t.getAngle())))),
                                        (int) (t.getPosY() + t.getAlto() / 2 - 5 + ((t.getAlto() / 2) * Math.sin(Math.toRadians(t.getAngle())))), animBala, t.getDamage(), t.getSpeed(), t.getAngle(), (int) t.getRange(), t.getPlayer(), 1);
                                bullet.add(b2);
                            }
                            t.shoot();
                            break;

                        case 8: //laser

                            Bullet b4 = new Bullet((int) (t.getPosX() + t.getAncho() / 2 + ((t.getAncho() / 2 - 3) * Math.cos(Math.toRadians(t.getAngle())))),
                                    (int) (t.getPosY() + t.getAlto() / 2 - 1 + ((t.getAlto() / 2 - 2) * Math.sin(Math.toRadians(t.getAngle())))), animBala, t.getDamage(), t.getSpeed(), t.getAngle(), (int) t.getRange(), t.getPlayer(), 1);
                            bullet.add(b4);
                            t.shoot();
                            break;

                        case 9: //watulion
                            Bullet b5 = new Bullet((int) (t.getPosX() + t.getAncho() / 2 + ((t.getAncho() / 2) * Math.cos(Math.toRadians(0)))),
                                    (int) (t.getPosY() + t.getAlto() / 2 - 1 + ((t.getAlto() / 2 - 2) * Math.sin(Math.toRadians(0)))), animBala, t.getDamage(), t.getSpeed(), t.getAngle(), (int) t.getRange(), t.getPlayer(), 1);
                            bullet.add(b5);
                            for (int l = 45; l <= 360; l += 45) {
                                b5 = new Bullet((int) (t.getPosX() + t.getAncho() / 2 + ((t.getAncho() / 2) * Math.cos(Math.toRadians(l)))),
                                        (int) (t.getPosY() + t.getAlto() / 2 - 1 + ((t.getAlto() / 2 - 2) * Math.sin(Math.toRadians(l)))), animBala, t.getDamage(), t.getSpeed(), l, (int) t.getRange(), t.getPlayer(), 1);
                                bullet.add(b5);
                            }
                            t.shoot();
                            break;

                    }

                }

            }

            

            //Manejo de las waves
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
                    tower.add(new Tower(e.getX(), e.getY(), animNormal, towerid, 1, 15, 25, 50, 100, 90, false));
                }
                if (new Rectangle(1238, 181, 30, 30).contains(e.getPoint())) {
                    //Torre dual
                    towerid = 4;
                    tower.add(new Tower(e.getX(), e.getY(), animDual, towerid, 1, 20, 50, 25, 400, 100, false));
                }
                if (new Rectangle(1298, 181, 30, 30).contains(e.getPoint())) {
                    //Torre sniper
                    towerid = 5;
                    tower.add(new Tower(e.getX(), e.getY(), animSniper, towerid, 1, 30, 150, 75, 400, 180, false));
                }
                if (new Rectangle(1208, 231, 30, 30).contains(e.getPoint())) {
                    //Torre quad
                    towerid = 6;
                    tower.add(new Tower(e.getX(), e.getY(), animQuad, towerid, 1, 27, 100, 12, 1600, 120, false));
                }
                if (new Rectangle(1268, 231, 30, 30).contains(e.getPoint())) {
                    //Torre dual fuerte
                    towerid = 7;
                    tower.add(new Tower(e.getX(), e.getY(), animFuerte, towerid, 1, 24, 400, 50, 1600, 120, false));
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
                    Image mine101 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine101.png"));
                    Image mine102 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine102.png"));
                    Image mine103 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine103.png"));
                    Image mine104 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine104.png"));
                    Image mine105 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine105.png"));
                    Image mine106 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine106.png"));
                    Image mine107 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine107.png"));
                    Image mine108 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine108.png"));
                    Image mine109 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine109.png"));
                    Image mine110 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine110.png"));
                    Image mine111 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine111.png"));
                    Image mine112 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine112.png"));
                    Image mine113 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine113.png"));
                    Image mine114 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine114.png"));
                    Image mine115 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine115.png"));
                    Image mine116 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine116.png"));
                    Image mine117 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine117.png"));
                    Image mine118 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine118.png"));
                    Image mine119 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine119.png"));
                    Image mine120 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine120.png"));
                    Image mine121 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine121.png"));

                    //Se crea la animación
                    Animacion animWat = new Animacion();
                    animWat.sumaCuadro(mine1, ((int) (Math.random() * (16000))) + 2000);
                    animWat.sumaCuadro(mine2, 100);
                    animrand = (int) (Math.random() * (4));
                    if (animrand < 2) {
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
                    } else {
                        animWat.sumaCuadro(mine1, ((int) (Math.random() * (16000))) + 2000);
                        animWat.sumaCuadro(mine2, 100);
                        animWat.sumaCuadro(mine1, ((int) (Math.random() * (16000))) + 2000);
                        animWat.sumaCuadro(mine2, 100);
                        animWat.sumaCuadro(mine1, ((int) (Math.random() * (16000))) + 2000);
                        animWat.sumaCuadro(mine101, 600);
                        animWat.sumaCuadro(mine102, 75);
                        animWat.sumaCuadro(mine103, 75);
                        animWat.sumaCuadro(mine102, 75);
                        animWat.sumaCuadro(mine103, 75);
                        animWat.sumaCuadro(mine102, 75);
                        animWat.sumaCuadro(mine103, 75);
                        animWat.sumaCuadro(mine103, 75);
                        animWat.sumaCuadro(mine102, 75);
                        animWat.sumaCuadro(mine103, 75);
                        animWat.sumaCuadro(mine102, 75);
                        animWat.sumaCuadro(mine103, 75);
                        animWat.sumaCuadro(mine104, 100);
                        animWat.sumaCuadro(mine105, 100);
                        animWat.sumaCuadro(mine106, 100);
                        animWat.sumaCuadro(mine107, 100);
                        animWat.sumaCuadro(mine108, 300);
                        animWat.sumaCuadro(mine109, 100);
                        animWat.sumaCuadro(mine110, 100);
                        animWat.sumaCuadro(mine111, 100);
                        animWat.sumaCuadro(mine112, 100);
                        animWat.sumaCuadro(mine113, 100);
                        animWat.sumaCuadro(mine112, 100);
                        animWat.sumaCuadro(mine113, 100);
                        animWat.sumaCuadro(mine112, 100);
                        animWat.sumaCuadro(mine113, 100);
                        animWat.sumaCuadro(mine112, 100);
                        animWat.sumaCuadro(mine113, 100);
                        animWat.sumaCuadro(mine114, 200);
                        animWat.sumaCuadro(mine113, 100);
                        animWat.sumaCuadro(mine115, 100);
                        animWat.sumaCuadro(mine116, 100);
                        animWat.sumaCuadro(mine117, 100);
                        animWat.sumaCuadro(mine118, 200);
                        animWat.sumaCuadro(mine119, 1000);
                        animWat.sumaCuadro(mine120, 100);
                        animWat.sumaCuadro(mine121, 90);

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
                basex = 1118;
                basey = 331;
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
                basex = 1118;
                basey = 331;
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
                basex = 1118;
                basey = 331;
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
                basex = 540;
                basey = 331;

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
//        g.drawImage(animIntro.getImagen(), 8, 31, this);
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
            g.setColor(Color.red);
            g.fillRect(basex, basey - 3, 90, 3);
            g.setColor(Color.green);
            g.fillRect(basex, basey - 3, life * 90 / lifeini, 3);
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
                g2d.setColor(Color.white);
                g2d.fillRect(t.getPosX(), t.getPosY() - 1, t.getAncho(), 1);
                g2d.setColor(Color.green);
                g2d.fillRect(t.getPosX(), t.getPosY() - 1, t.getExp() % t.getAncho(), 1);
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
                g2d.setColor(Color.red);
                g2d.fillRect(t.getPosX(), t.getPosY() - 1, t.getAncho(), 1);
                g2d.setColor(Color.green);
                g2d.fillRect(t.getPosX(), t.getPosY() - 1, t.health * t.getAncho() / (50 + ((int) Math.pow(wave - 1, 2))), 1);
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

            for (int i = 0; i < bullet.size(); i++) {
                Bullet t = (Bullet) bullet.get(i);
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
