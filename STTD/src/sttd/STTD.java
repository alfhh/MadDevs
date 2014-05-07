/**
 * Star Wars Tower Defense Developed by Mad Devs "Stay on the right side, the
 * Mad side" Version: Alpha Date: 4/07/2014
 */
package sttd;

import java.awt.BasicStroke;
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
import java.awt.geom.Line2D;
import java.awt.geom.NoninvertibleTransformException;

public class STTD extends JFrame implements Runnable, KeyListener, MouseListener, MouseMotionListener {

    private Graphics dbg; // Graphic Object
    private Graphics dbgtower; // Graphic Object
    private Image dbImage; // Image
    private Image background; // Background Image
    private Image filter; //hipsters
    private Image virusimage; //Imagen del virus pa la nave
    private Image timerimage; //Imagen del timer pa la nave
    private Image imgmenu; // Menu Image
    private Image t; // Torreta Image
    private Font StarJedi; // Fuente de StarJedi

    private Animacion animIntro; // Animacion de intro

    private Animacion animNormal; // Animacion de la torre Normal
    private Animacion animDual; // Animacion de la torre Dual
    private Animacion animSniper; // Animacion de la torre Sniper
    private Animacion animFuerte; // Animacion de la torre DualFuerte
    private Animacion animQuad; // Animacion de la torre Quadrupeda
    private Animacion animLaser; // Animacion de la torre SniperLaser
    private Animacion animBala; // Animacion del enemigo
    private Animacion animBuff; // Animacion de la torre de buff

    private Animacion animAwing; // Animacion del Awing
    private Animacion animYwing; // Animacion del Ywing
    private Animacion animXwing; // Animacion del Xwing

    private LinkedList<Tower> tower; // Lista de las Torres
    private LinkedList<Point> levelstart; // Lista de los puntos de comienzo del mapa
    private LinkedList<Enemy> wrench; // Lista de los enemigos
    private LinkedList<Mine> mine; // Lista de las Minas
    private LinkedList<Bullet> bullet; // Lista de las balas
    private LinkedList<Animacion> towergraphics; // Lista de las imagenes de las torres
    private LinkedList<Laser> lasers; // Lista de las imagenes de las torres

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
    private double score1;

    private int wavecount; // numero de malos por oleada
    //numero de malos por tipo
    private LinkedList<Integer> wavecountarr; // Lista de las imagenes de las torres
    private int wavecountx; // numero de malos por oleada de xwing
    private int wavecounty; // numero de malos por oleada de ywing
    private int wavecounta; // numero de malos por oleada de awing
    private String wavetype; //Nombre de la wave

    private int wave; // numero de oleada
    private int wavebegin; // tiempo antes que empieze la oleada
    private int towerselect; // ID que marca el indice de la torreta seleccionada
    private int instrMouse; // Marca los pasos de las instrucciones
    private long tiempoActual;
    private long tiempoInicial;
    private int player1money = 400;
    private int player2money = 400;
    private int introtimer = 330; // variable que marca el intro del juego
    private int mouseover = 0; // variable que se usara para desplegar los datos de la torre

    private SoundClip intro; // cancion del juego
    private SoundClip lost; // cancion de perder
    private SoundClip instrsong; // cancion del tutorial
    private SoundClip gamesong; // cancion de Marcha imperial
    private SoundClip gamesong1; // cancion del Duel of Fates
    private SoundClip hit; // sonido de golpe
    private SoundClip minesound; // sonido de mina
    private SoundClip place; // sonido de poner la torre

    private AffineTransform identidad; // Variable tipo AffineTransform
    private Instruccion instruccion; // Objeto usado para las instrucciones

    private boolean main; // booleano que muestra la pantalla principal
    private boolean menu; // booleano que muestra el menu de niveles
    private boolean instr; // booleano que muestra las instrucciones
    private boolean game; // booleano que deja que el juego corra
    private boolean wavego; // booleano que inicia la oleada
    private boolean bmine; // booleano que muestra si seleccionaron una mina
    private boolean lose; // booleano que muestra si el jugador perdio
    private boolean pause; // booleano que pausa el juego
    private boolean music; // booleano que abilita la musica
    private boolean fx; // booleano que abilita los effectos especiales
    private boolean canput = false; // ver si se puede poner o no
    private boolean saveStates; // booleano que muestra los savestates
    private boolean loadStates; // booleano que muestra los loadstates

    //Checar si un punto esta dentro de un circulo
    public boolean inCircle(int circleX, int circleY, int clickX, int clickY, int radius) {
        return java.lang.Math.pow((circleX - clickX), 2) + java.lang.Math.pow((circleY - clickY), 2) < java.lang.Math.pow(radius, 2);
    }

    public void watulioCreation(int ex, int ey) {
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
        Image mine201 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine201.png"));
        Image mine202 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine202.png"));
        Image mine203 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine203.png"));
        Image mine204 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine204.png"));
        Image mine205 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine205.png"));
        Image mine206 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine206.png"));
        Image mine207 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine207.png"));
        Image mine208 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine208.png"));
        Image mine209 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine209.png"));
        Image mine210 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine210.png"));
        Image mine211 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine211.png"));
        Image mine212 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine212.png"));
        Image mine213 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine213.png"));
        Image mine214 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine214.png"));
        Image mine215 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine215.png"));
        Image mine216 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine216.png"));
        Image mine217 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine217.png"));
        Image mine218 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine218.png"));
        Image mine219 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine219.png"));
        Image mine220 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine220.png"));
        Image mine221 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine221.png"));
        Image mine222 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine222.png"));
        Image mine223 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine223.png"));
        Image mine224 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine224.png"));
        Image mine225 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine225.png"));
        Image mine226 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine226.png"));
        Image mine227 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine227.png"));
        Image mine228 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine228.png"));
        Image mine229 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine229.png"));
        Image mine230 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine230.png"));
        Image mine231 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine231.png"));
        Image mine232 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine232.png"));
        Image mine233 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine233.png"));
        Image mine234 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine234.png"));
        Image mine235 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/wat_mine/watmine235.png"));

        //Se crea la animación
        Animacion animWat = new Animacion();
        animWat.sumaCuadro(mine1, ((int) (Math.random() * (16000))) + 2000);
        animWat.sumaCuadro(mine2, 100);
        animrand = (int) (Math.random() * (5));
        if (animrand < 2) {
            //animacion 1: aburrimiento
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
            if (animrand < 4) {
                //animacion 2: refresco
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
            } else {
                //animacion 3: Killerpollo distrae al watulion
                animWat.sumaCuadro(mine1, ((int) (Math.random() * (16000))) + 2000);
                animWat.sumaCuadro(mine2, 100);
                animWat.sumaCuadro(mine1, ((int) (Math.random() * (16000))) + 2000);
                animWat.sumaCuadro(mine2, 100);
                animWat.sumaCuadro(mine1, ((int) (Math.random() * (16000))) + 2000);
                animWat.sumaCuadro(mine201, 500);
                animWat.sumaCuadro(mine202, 100);
                animWat.sumaCuadro(mine203, 400);
                animWat.sumaCuadro(mine204, 100);
                animWat.sumaCuadro(mine205, 75);
                animWat.sumaCuadro(mine206, 75);
                animWat.sumaCuadro(mine205, 75);
                animWat.sumaCuadro(mine206, 75);
                animWat.sumaCuadro(mine205, 75);
                animWat.sumaCuadro(mine206, 75);
                animWat.sumaCuadro(mine205, 75);
                animWat.sumaCuadro(mine206, 75);
                animWat.sumaCuadro(mine207, 100);
                animWat.sumaCuadro(mine208, 100);
                animWat.sumaCuadro(mine207, 100);
                animWat.sumaCuadro(mine208, 100);
                animWat.sumaCuadro(mine207, 100);
                animWat.sumaCuadro(mine208, 100);
                animWat.sumaCuadro(mine207, 100);
                animWat.sumaCuadro(mine208, 700);
                animWat.sumaCuadro(mine209, 100);
                animWat.sumaCuadro(mine210, 100);
                animWat.sumaCuadro(mine211, 2000);
                animWat.sumaCuadro(mine213, 125);
                animWat.sumaCuadro(mine214, 125);
                animWat.sumaCuadro(mine213, 125);
                animWat.sumaCuadro(mine214, 125);
                animWat.sumaCuadro(mine213, 125);
                animWat.sumaCuadro(mine214, 125);
                animWat.sumaCuadro(mine215, 125);
                animWat.sumaCuadro(mine216, 125);
                animWat.sumaCuadro(mine215, 125);
                animWat.sumaCuadro(mine216, 125);
                animWat.sumaCuadro(mine215, 125);
                animWat.sumaCuadro(mine216, 125);
                animWat.sumaCuadro(mine217, 100);
                animWat.sumaCuadro(mine218, 200);
                animWat.sumaCuadro(mine219, 200);
                animWat.sumaCuadro(mine220, 100);
                animWat.sumaCuadro(mine221, 100);
                animWat.sumaCuadro(mine220, 100);
                animWat.sumaCuadro(mine221, 100);
                animWat.sumaCuadro(mine220, 100);
                animWat.sumaCuadro(mine221, 100);
                animWat.sumaCuadro(mine220, 100);
                animWat.sumaCuadro(mine221, 100);
                animWat.sumaCuadro(mine222, 200);
                animWat.sumaCuadro(mine223, 1000);
                animWat.sumaCuadro(mine224, 200);
                animWat.sumaCuadro(mine225, 200);
                animWat.sumaCuadro(mine226, 2000);
                animWat.sumaCuadro(mine227, 200);
                animWat.sumaCuadro(mine228, 50);
                animWat.sumaCuadro(mine227, 50);
                animWat.sumaCuadro(mine228, 50);
                animWat.sumaCuadro(mine227, 50);
                animWat.sumaCuadro(mine228, 50);
                animWat.sumaCuadro(mine227, 50);
                animWat.sumaCuadro(mine228, 50);
                animWat.sumaCuadro(mine227, 100);
                animWat.sumaCuadro(mine228, 2000);
                animWat.sumaCuadro(mine229, 100);
                animWat.sumaCuadro(mine230, 100);
                animWat.sumaCuadro(mine231, 200);
                animWat.sumaCuadro(mine232, 2000);
                animWat.sumaCuadro(mine233, 50);
                animWat.sumaCuadro(mine232, 3000);
                animWat.sumaCuadro(mine234, 100);
                animWat.sumaCuadro(mine235, 1000);
                animWat.sumaCuadro(mine23, 50);
                animWat.sumaCuadro(mine24, 50);
                animWat.sumaCuadro(mine23, 50);
                animWat.sumaCuadro(mine24, 50);
                animWat.sumaCuadro(mine23, 50);
                animWat.sumaCuadro(mine24, 50);
                animWat.sumaCuadro(mine23, 50);
                animWat.sumaCuadro(mine24, 50);
                animWat.sumaCuadro(mine29, 200);
            }
        }
        towerid = 9;
        tower.add(new Tower(ex, ey, animWat, towerid, 1, 10, 600, 75, 4200, 60, true));
    }

    public void towerCreate(MouseEvent e, boolean click) {
        if (game) {
            if (e.getX() > 1200) { // si el mouse esta en el HUD
                //Upgrade de las torres
                if (towerselect >= 0 && e.getY() > 458 && e.getY() < 532) {
                    Tower t = (Tower) tower.get(towerselect);
                    if (t.getExp() >= t.getMAXExp()) {
                        if (e.getX() > 1284) {
                            t.getUpgR();
                        } else {
                            t.getUpgL();
                        }
                    }
                }
                // creacion de torretas
                if (towerid == 0) {
                    if (!bmine) {
                        if (new Rectangle(1268, 121, 30, 30).contains(e.getPoint())) {
                            //Torre normal
                            if (player1money >= 100) {
                                towerid = 3;
                                tower.add(new Tower(e.getX(), e.getY(), animNormal, towerid, 1, 15, 25, 50, 100, 90, false));
                            }
                        }
                        if (new Rectangle(1238, 181, 30, 30).contains(e.getPoint())) {
                            //Torre dual
                            if (player1money >= 500) {
                                towerid = 4;
                                tower.add(new Tower(e.getX(), e.getY(), animDual, towerid, 1, 20, 50, 12, 500, 100, false));
                            }
                        }
                        if (new Rectangle(1298, 181, 30, 30).contains(e.getPoint())) {
                            //Torre sniper
                            if (player1money >= 420) {
                                towerid = 5;
                                tower.add(new Tower(e.getX(), e.getY(), animSniper, towerid, 1, 30, 150, 75, 420, 180, false));
                            }
                        }
                        if (new Rectangle(1208, 241, 30, 30).contains(e.getPoint())) {
                            //Torre quad
                            if (player1money >= 800) {
                                towerid = 6;
                                tower.add(new Tower(e.getX(), e.getY(), animQuad, towerid, 1, 27, 30, 3, 800, 120, false));
                            }
                        }
                        if (new Rectangle(1268, 241, 30, 30).contains(e.getPoint())) {
                            //Torre dual fuerte
                            if (player1money >= 900) {
                                towerid = 7;
                                tower.add(new Tower(e.getX(), e.getY(), animFuerte, towerid, 1, 36, 100, 12, 900, 160, false));
                            }
                        }
                        if (new Rectangle(1328, 241, 30, 30).contains(e.getPoint())) {
                            //Torre laser
                            if (player1money >= 1050) {
                                towerid = 8;
                                tower.add(new Tower(e.getX(), e.getY(), animLaser, towerid, 1, 36, 600, 75, 1050, 250, false));
                            }
                        }
                        if (new Rectangle(1238, 301, 30, 30).contains(e.getPoint())) {
                            //torre de watulion
                            if (player1money >= 2000) {
                                watulioCreation(e.getX(), e.getY());
                            }
                        }
                        if (new Rectangle(1298, 301, 30, 30).contains(e.getPoint())) {
                            //torre de buff
                            if (player1money >= 2000) {
                                towerid = 10;
                                tower.add(new Tower(e.getX(), e.getY(), animBuff, towerid, 1, 36, 0, 75, 1600, 150, true));
                            }
                        }
                    }
                    if (towerid == 0 && !bmine && player1money >= 410) {
                        if (new Rectangle(1268, 361, 30, 30).contains(e.getPoint())) {
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
                            mine.add(new Mine(e.getX(), e.getY(), animMine, 1, 1));
                        }

                        if (new Rectangle(1328, 361, 30, 30).contains(e.getPoint())) {
                            //Mina
                            bmine = true;
                            Animacion animMine; // Animacion de la mina 
                            //Imagen de la mina animada
                            Image nmine1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Poisonmine_animation/mine1.png"));
                            Image nmine2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Poisonmine_animation/mine2.png"));
                            Image nmine3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Poisonmine_animation/mine3.png"));
                            Image nmine4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Poisonmine_animation/mine4.png"));
                            Image nmine5 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Poisonmine_animation/mine5.png"));

                            //Se crea la animación
                            animMine = new Animacion();
                            animMine.sumaCuadro(nmine1, 100);
                            animMine.sumaCuadro(nmine2, 100);
                            animMine.sumaCuadro(nmine3, 100);
                            animMine.sumaCuadro(nmine4, 100);
                            animMine.sumaCuadro(nmine5, 100);
                            animMine.sumaCuadro(nmine4, 100);
                            animMine.sumaCuadro(nmine3, 100);
                            animMine.sumaCuadro(nmine2, 100);
                            mine.add(new Mine(e.getX(), e.getY(), animMine, 1, 3));
                        }

                        if (new Rectangle(1208, 361, 30, 30).contains(e.getPoint())) {
                            //Mina
                            bmine = true;
                            Animacion animMine; // Animacion de la mina 
                            //Imagen de la mina animada
                            Image nmine1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Timemine_animation/tmine1.png"));
                            Image nmine2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Timemine_animation/tmine2.png"));
                            Image nmine3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Timemine_animation/tmine3.png"));
                            Image nmine4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Timemine_animation/tmine4.png"));
                            Image nmine5 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Timemine_animation/tmine5.png"));
                            Image nmine6 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Timemine_animation/tmine6.png"));
                            Image nmine7 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Timemine_animation/tmine7.png"));
                            Image nmine8 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Timemine_animation/tmine8.png"));
                            Image nmine9 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Timemine_animation/tmine9.png"));
                            Image nmine10 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Timemine_animation/tmine10.png"));
                            Image nmine11 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Timemine_animation/tmine11.png"));
                            Image nmine12 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Timemine_animation/tmine12.png"));

                            //Se crea la animación
                            animMine = new Animacion();
                            animMine.sumaCuadro(nmine1, 200);
                            animMine.sumaCuadro(nmine2, 200);
                            animMine.sumaCuadro(nmine3, 200);
                            animMine.sumaCuadro(nmine4, 200);
                            animMine.sumaCuadro(nmine5, 200);
                            animMine.sumaCuadro(nmine6, 200);
                            animMine.sumaCuadro(nmine7, 200);
                            animMine.sumaCuadro(nmine8, 200);
                            animMine.sumaCuadro(nmine9, 200);
                            animMine.sumaCuadro(nmine10, 200);
                            animMine.sumaCuadro(nmine11, 200);
                            animMine.sumaCuadro(nmine12, 200);
                            mine.add(new Mine(e.getX(), e.getY(), animMine, 1, 2));
                        }
                    }
                }
            } else {
                if (click) {
                    Tower t;
                    for (int i = 0; i < tower.size(); i++) {
                        t = (Tower) tower.get(i);
                        if (t.getPerimetro().contains(e.getPoint())) {
                            towerselect = i;
                            break;
                        } else {
                            towerselect = -1;
                        }
                    }
                }
            }

        }
    }

    public STTD() {
        // Setup
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1368, 730);
        setTitle("Star Wars: Tower Defense");
        rotacion = Math.PI / 60;
        player1money = 400;
        player2money = 400;
        towerid = 0;
        main = false;
        score1 = 0;
        instr = false;
        menu = false;
        countx = 50;
        grid = new int[23][40];
        wavego = false;
        wavecount = 0;
        wave = 0;
        wavebegin = 50;
        bmine = false;
        lifeini = 20;
        life = lifeini;
        towerselect = -1;
        instrMouse = -1;
        instruccion = new Instruccion();
        intro = new SoundClip("sounds/introMedley.wav");
        lost = new SoundClip("sounds/lose.wav");
        instrsong = new SoundClip("sounds/tutorial.wav");
        gamesong = new SoundClip("sounds/duel.wav");
        gamesong1 = new SoundClip("sounds/march.wav");
        hit = new SoundClip("sounds/hit.wav"); // sonido de golpe
        minesound = new SoundClip("sounds/mine.wav"); // sonido de mina
        place = new SoundClip("sounds/towerplace.wav");
        music = true;
        fx = true;
        saveStates = false;
        loadStates = false;

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

        animIntro.sumaCuadro(in1, 20);
        animIntro.sumaCuadro(in2, 20);
        animIntro.sumaCuadro(in3, 20);
        animIntro.sumaCuadro(in4, 20);
        animIntro.sumaCuadro(in5, 20);
        animIntro.sumaCuadro(in6, 20);
        animIntro.sumaCuadro(in7, 20);
        animIntro.sumaCuadro(in8, 20);
        animIntro.sumaCuadro(in9, 20);
        animIntro.sumaCuadro(in10, 20);
        animIntro.sumaCuadro(in11, 20);
        animIntro.sumaCuadro(in12, 20);
        animIntro.sumaCuadro(in13, 20);
        animIntro.sumaCuadro(in14, 20);
        animIntro.sumaCuadro(in15, 20);
        animIntro.sumaCuadro(in16, 20);
        animIntro.sumaCuadro(in17, 20);
        animIntro.sumaCuadro(in18, 20);
        animIntro.sumaCuadro(in19, 100);
        animIntro.sumaCuadro(in20, 100);
        animIntro.sumaCuadro(in21, 100);
        animIntro.sumaCuadro(in22, 100);

        background = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Intro/intro.gif"));
        virusimage = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Status/virus.png"));
        timerimage = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Status/timer.png"));
        filter = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/filtro.png"));
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
        t = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/torretabuff.png"));
        animBuff = new Animacion();

        animBuff.sumaCuadro(t, 100);
        t = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Bala.png"));
        animBala = new Animacion();

        animBala.sumaCuadro(t, 100);

        // Imagen de los enemigos
        Image e = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/ywing.png"));
        animYwing = new Animacion();

        animYwing.sumaCuadro(e, 100);
        e = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/xwing.jpg"));
        animXwing = new Animacion();

        animXwing.sumaCuadro(e, 100);
        e = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/awing.png"));
        animAwing = new Animacion();

        animAwing.sumaCuadro(e, 100);

        tower = new LinkedList<Tower>();
        levelstart = new LinkedList<Point>();
        wrench = new LinkedList<Enemy>();
        towergraphics = new LinkedList<Animacion>();
        mine = new LinkedList<Mine>();
        bullet = new LinkedList<Bullet>();
        lasers = new LinkedList<Laser>();
        wavecountarr = new LinkedList<Integer>();

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
        intro.play();
        while (true) {
//            if (intro.getClip().getLongFramePosition() == intro.getClip().getFrameLength() - 1) {
//
//            }
            if (introtimer > 0) {
                introtimer--;
            } else if (introtimer == 0) {
                main = true;
                background = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/mainBackground.png"));
                introtimer--;
            }
            if (game) {
                checaColision();
                actualiza();
            }
            if (instr) {
                instrMouse = instruccion.UpdateMouse(instrMouse, tower, wrench, mine, bullet, grid);
                if (instrMouse == 20) {
                    instrMouse = -1;
                    instr = false;
                    main = true;
                    instrsong.stop();
                    if (music) {
                        intro.play();
                    }
                    background = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/mainBackground.png"));
                }
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
            for (int j = 0; j < wrench.size(); j++) {
                Enemy f = (Enemy) wrench.get(j);
                if (e.getPerimetro().intersects(f.getPerimetro())) {
                    if (e.getVirus() && !f.getVirus()) {
                        f.setVirus();
                    }
                }
            }
            e.slowTimer();
            e.virusTimer();
            for (int j = 0; j < mine.size(); j++) {
                Mine m = (Mine) mine.get(j);
                if (m.getSet() && e.getPerimetro().intersects(m.getPerimetro())) {
                    m.setSet(false);
                    if (m.getType() == 1) {
                        m.setExp(117);
                        if (fx) {
                            minesound.play();
                        }
                        if (e.getType() < 3) {
                            player1money += 20;
                            wrench.remove(i);
                        } else {
                            e.setHealth(e.getHealth() - e.getBaseHealth() / (3 + (int) (wave / 10)));
                        }
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
                        a.sumaCuadro(i1, 75);
                        a.sumaCuadro(i2, 75);
                        a.sumaCuadro(i3, 75);
                        a.sumaCuadro(i4, 75);
                        a.sumaCuadro(i5, 150);
                        a.sumaCuadro(i6, 150);
                        a.sumaCuadro(i7, 150);
                        a.sumaCuadro(i8, 100);
                        a.sumaCuadro(i9, 100);
                        a.sumaCuadro(i10, 100);
                        a.sumaCuadro(i11, 100);
                        a.sumaCuadro(i12, 100);

                        m.setAnimacion(a);
                    }

                    if (m.getType() == 2) {
                        m.setExp(71);
                        e.setSlow();
                        e.setHealth(e.getHealth() - m.getDam());
                        Image i1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Timemine_animation/mineexp1.png"));
                        Image i2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Timemine_animation/mineexp2.png"));
                        Image i3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Timemine_animation/mineexp3.png"));
                        Image i4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Timemine_animation/mineexp4.png"));
                        Image i5 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Timemine_animation/mineexp5.png"));
                        Image i6 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Timemine_animation/mineexp6.png"));

                        Animacion a = new Animacion();
                        a.sumaCuadro(i1, 100);
                        a.sumaCuadro(i2, 100);
                        a.sumaCuadro(i3, 400);
                        a.sumaCuadro(i4, 100);
                        a.sumaCuadro(i5, 100);
                        a.sumaCuadro(i6, 100);

                        m.setAnimacion(a);
                    }

                    if (m.getType() == 3) {
                        m.setExp(117);
                        e.setVirus();
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
                        a.sumaCuadro(i4, 75);
                        a.sumaCuadro(i5, 150);
                        a.sumaCuadro(i6, 150);
                        a.sumaCuadro(i7, 150);
                        a.sumaCuadro(i8, 100);
                        a.sumaCuadro(i9, 100);
                        a.sumaCuadro(i10, 100);
                        a.sumaCuadro(i11, 100);
                        a.sumaCuadro(i12, 100);

                        m.setAnimacion(a);
                    }

                }
            }
            for (int j = 0; j < bullet.size(); j++) {
                Bullet bl = (Bullet) bullet.get(j);
                if (e.getPerimetro().intersects(bl.getPerimetro())) {
                    e.setHealth(e.getHealth() - bl.getDamage());
                    Tower t = (Tower) tower.get(bl.getTower());
                    t.Exp();
                    if (t.getPlayer() == 1) {
                        score1 += (double) bl.getDamage() / 100.0;
                    }
                    bl.destroy();
                }

            }
        }
    }

    /**
     * This method updates..
     */
    public void actualiza() {
        if (music) {
            if (gamesong1.getClip().getFrameLength() - 1 <= gamesong1.getClip().getFramePosition()
                    || gamesong.getClip().getFrameLength() - 1 <= gamesong.getClip().getFramePosition()) {
                if (Math.random() * 2 > 1) {
                    gamesong.getClip().setFramePosition(0);
                    gamesong.stop();
                    gamesong1.play();
                } else {
                    gamesong1.getClip().setFramePosition(0);
                    gamesong1.stop();
                    gamesong.play();

                }
            }
        }
        // para actualizar la vida del laser
        for (int i = 0; i < lasers.size(); i++) {
            Laser l = (Laser) lasers.get(i);
            if (!l.deathTime()) {
                lasers.remove(l);
            }
        }

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
            if ((Mine) mine.getLast() != m && m.getPosX() > 1200 || m.getExp() == -1) {
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
        PointerInfo a = MouseInfo.getPointerInfo(); // Obtencion del mouse para seguirlo
        Point b = a.getLocation();
        mouseover = 0;
        if (towerid > 0) {// Si se esta cargando una torre
            try { // Intenta tomar la ultima torre del arreglo
                Tower t = (Tower) tower.getLast();
                // si la torreta esta dentro de la grid
                if (b.getX() < 1208 && b.getY() > 30 && b.getY() < 716) {
                    if (grid[((int) b.getY() - 30) / 30][((int) b.getX() - 8) / 30] == 1) {
                        canput = true;
                        //Lo acomoda en la matriz
                        t.setPosX(((int) b.getX()) - ((int) b.getX() - 8) % 30);
                        t.setPosY(((int) b.getY()) - ((int) b.getY()) % 30);
                    } else {
                        canput = false;
                        // se pone en medio del cursor
                        t.setPosX((int) b.getX() - t.getAncho() / 2);
                        t.setPosY((int) b.getY() - t.getAlto() / 2);
                    }
                } else {
                    canput = false;
                    // se pone en medio del cursor
                    t.setPosX((int) b.getX() - t.getAncho() / 2);
                    t.setPosY((int) b.getY() - t.getAlto() / 2);
                }
            } catch (NoSuchElementException n) {
                towerid = 0;
            }
        } else if (bmine) {
            try { // Intenta tomar la ultima torre del arreglo
                Mine m = (Mine) mine.getLast();
                // si la torreta esta dentro de la grid
                if (b.getX() < 1208 && b.getY() > 30 && b.getY() < 716) {
                    if (grid[((int) b.getY() - 30) / 30][((int) b.getX() - 8) / 30] == 0) {
                        //Checa que no haya otra mina
                        boolean over = false;
                        for (int i = 0; i < mine.size(); i++) {
                            Mine n = (Mine) mine.get(i);
                            //Lo acomoda en la matriz
                            if (n != m && n.getPosX() == (((int) b.getX()) - ((int) b.getX() - 8) % 30)
                                    && n.getPosY() == (((int) b.getY()) - ((int) b.getY()) % 30)) {
                                over = true;
                            }
                        }
                        if (!over) {
                            canput = true;
                            m.setPosX(((int) b.getX()) - ((int) b.getX() - 8) % 30);
                            m.setPosY(((int) b.getY()) - ((int) b.getY()) % 30);
                        } else {
                            canput = false;
                            // se pone en medio del cursor
                            m.setPosX((int) b.getX() - m.getAncho() / 2);
                            m.setPosY((int) b.getY() - m.getAlto() / 2);
                        }
                    } else {
                        canput = false;
                        // se pone en medio del cursor
                        m.setPosX((int) b.getX() - m.getAncho() / 2);
                        m.setPosY((int) b.getY() - m.getAlto() / 2);
                    }
                } else {
                    canput = false;
                    // se pone en medio del cursor
                    m.setPosX((int) b.getX() - m.getAncho() / 2);
                    m.setPosY((int) b.getY() - m.getAlto() / 2);
                }
            } catch (NoSuchElementException n) {
                bmine = false;
            }
        } else {
            if (new Rectangle(1268, 121, 30, 30).contains(b.getLocation())) {
                //Torre normal
                mouseover = 3;
            }
            if (new Rectangle(1238, 181, 30, 30).contains(b.getLocation())) {
                //Torre dual
                mouseover = 4;
            }
            if (new Rectangle(1298, 181, 30, 30).contains(b.getLocation())) {
                //Torre sniper
                mouseover = 5;
            }
            if (new Rectangle(1208, 241, 30, 30).contains(b.getLocation())) {
                //Torre quad
                mouseover = 6;
            }
            if (new Rectangle(1268, 241, 30, 30).contains(b.getLocation())) {
                //Torre dual fuerte
                mouseover = 7;
            }
            if (new Rectangle(1328, 241, 30, 30).contains(b.getLocation())) {
                //Torre laser
                mouseover = 8;
            }
            if (new Rectangle(1238, 301, 30, 30).contains(b.getLocation())) {
                //torre de watulion
                mouseover = 9;
            }
            if (new Rectangle(1298, 301, 30, 30).contains(b.getLocation())) {
                //torre de buff
                mouseover = 10;
            }
            if (new Rectangle(1268, 361, 30, 30).contains(b.getLocation())) {
                //Mina
                mouseover = 11;
            }
            if (new Rectangle(1328, 361, 30, 30).contains(b.getLocation())) {
                //Mina
                mouseover = 12;
            }
            if (new Rectangle(1208, 361, 30, 30).contains(b.getLocation())) {
                //Mina
                mouseover = 13;
            }
        }

        //bufeadora
        for (int i = 0; i < tower.size(); i++) {
            Tower t = (Tower) tower.get(i);
            if (t.getSet()) {
                if (t.getId() == 10) {
                    for (int m = 0; m < tower.size(); m++) {
                        Tower t2 = (Tower) tower.get(m);
                        if (t2.getId() != 10 && t2.getSet()) {
                            if (inCircle(t.getPosX() + t.getAncho() / 2, t.getPosY() + t.getAlto() / 2, t2.getPosX() + t2.getAncho() / 2, t2.getPosY() + t2.getAlto() / 2, (int) t.getRange())) {
                                t2.setMul(1.1);
                            }

                        }
                    }
                }
            }

        }

        if (wavego) {
            countx--;
            if (countx == 0 && wavecount > 0) { // Addicion de un enemigo nuevo
                Point p = (Point) levelstart.get((int) (Math.random() * levelstart.size()));
                if (wave < 0) {
                    wrench.add(new Enemy((int) p.getX(), (int) p.getY(), animXwing, 1, 3, (50 + 3 * ((int) Math.pow(wave - 1, 2)))));
                } else {
                    //Cual enemigo va a salir?
                    int randome = (int) (Math.random() * 3);
                    if (wavecounta + wavecountx > 0) {
                        switch (randome) {
                            case 0:
                                if (wavecounta > 0) {
                                    wrench.add(new Enemy((int) p.getX(), (int) p.getY(), animAwing, 2, 6, (40 + 2 * ((int) Math.pow(wave - 1, 2)))));
                                    wavecounta--;
                                } else {
                                    if (wavecountx > 0) {
                                        wavecountx--;
                                        wrench.add(new Enemy((int) p.getX(), (int) p.getY(), animXwing, 1, 3, (50 + 3 * ((int) Math.pow(wave - 1, 2)))));
                                    } else {
                                        wavecounty--;
                                        wrench.add(new Enemy((int) p.getX(), (int) p.getY(), animYwing, 3, 1, (200 + 4 * ((int) Math.pow(wave + 5, 2)))));
                                    }
                                }
                                break;
                            case 1:
                                if (wavecounty > 0) {
                                    wavecounty--;
                                    wrench.add(new Enemy((int) p.getX(), (int) p.getY(), animYwing, 3, 1, (200 + 4 * ((int) Math.pow(wave + 5, 2)))));

                                } else {
                                    if (wavecountx > 0) {
                                        wavecountx--;
                                        wrench.add(new Enemy((int) p.getX(), (int) p.getY(), animXwing, 1, 3, (50 + 3 * ((int) Math.pow(wave - 1, 2)))));
                                    } else {
                                        wavecounta--;
                                        wrench.add(new Enemy((int) p.getX(), (int) p.getY(), animAwing, 2, 6, (40 + 2 * ((int) Math.pow(wave - 1, 2)))));
                                    }
                                }
                                break;
                            default:
                                if (wavecountx > 0) {
                                    wrench.add(new Enemy((int) p.getX(), (int) p.getY(), animXwing, 1, 3, (50 + 3 * ((int) Math.pow(wave - 1, 2)))));
                                    wavecountx--;
                                } else {
                                    if (wavecounta > 0) {
                                        wavecounta--;
                                        wrench.add(new Enemy((int) p.getX(), (int) p.getY(), animAwing, 2, 6, (40 + 2 * ((int) Math.pow(wave - 1, 2)))));
                                    } else {
                                        wavecounty--;
                                        wrench.add(new Enemy((int) p.getX(), (int) p.getY(), animYwing, 3, 1, (200 + 4 * ((int) Math.pow(wave + 5, 2)))));
                                    }
                                }
                                break;
                        }
                    }

                }
                countx = 75;
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
                    } catch (ArrayIndexOutOfBoundsException ai) {
                    }
                    try {
                        // Si el cuadrante de arriba es camino y no va hacia abajo
                        if (grid[((int) p.getY() - 31) / 30 - 1][((int) p.getX() - 8) / 30] == 0
                                && past != 'd' && !ready) {
                            w.getEnd().setLocation(p.getX(), p.getY() - 30);
                            w.setMov('u');
                            ready = true;
                        }
                    } catch (ArrayIndexOutOfBoundsException ai) {
                    }
                    try {
                        // Si el cuadrante de izquierda es camino y no va hacia derecha
                        if (grid[((int) p.getY() - 31) / 30][((int) p.getX() - 8) / 30 - 1] == 0
                                && past != 'r' && !ready) {
                            w.getEnd().setLocation(p.getX() - 30, p.getY());
                            w.setMov('l');
                            ready = true;
                        }
                    } catch (ArrayIndexOutOfBoundsException ai) {
                    }
                    try {
                        // Si el cuadrante de derecha es camino y no va hacia izquierda
                        if (grid[((int) p.getY() - 31) / 30][((int) p.getX() - 8) / 30 + 1] == 0
                                && past != 'l') {
                            w.getEnd().setLocation(p.getX() + 30, p.getY());
                            w.setMov('r');
                        }
                    } catch (ArrayIndexOutOfBoundsException ai) {
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
                // Si el enemigo no tiene vida;
                if (w.getHealth() <= 0) {
                    player1money += wrench.get(i).getBaseHealth() / (40 + (Math.pow(wave - 1, 2)));
                    wrench.remove(i); // Desaparece
                }
                // Si el enemigo llega a la base
                if (grid[((int) w.getPosY() - 31) / 30][((int) w.getPosX() - 8) / 30] == 2) {
                    if (fx) {
                        hit.play();
                    }
                    wrench.remove(i); // Desaparece
                    life--;
                }
                // Si la base no tiene vida
                if (life == 0) {
                    game = false;
                    lose = true;
                    gamesong.stop();
                    gamesong1.stop();
                    if (music) {
                        lost.play();
                    }
                }
            }

            //Disparar bala a la dirección deseada
            int priority; //-1 = no apuntar a nada
            int lifetimep; //ver quien es el que va más avanzado en el area
            for (int i = 0; i < tower.size(); i++) {
                Tower t = (Tower) tower.get(i);
                priority = -1;
                lifetimep = 0;
                for (int j = wrench.size() - 1; j >= 0; j--) {
                    Enemy w = (Enemy) wrench.get(j);
                    if (inCircle(t.getPosX() + t.getAncho() / 2, t.getPosY() + t.getAlto() / 2, w.getPosX() + w.getAncho() / 2, w.getPosY() + w.getAlto() / 2, (int) t.getRange())) {
                        if (w.getLifeTime() > lifetimep) {
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
                //Aqui va la acción de disparar
                if (t.canShoot() && priority != -1) {
                    int randomizer = (int) (Math.random() * (2));
                    switch (t.getId()) {
                        case 3: //Torre normal
                            if (fx) {
                                SoundClip bulletsound = new SoundClip("sounds/lasernormal.wav"); // sonido de disparo
                                bulletsound.play();
                            }
                            Bullet b1 = new Bullet((int) (t.getPosX() + t.getAncho() / 2 - 3 + ((t.getAncho() / 2) * Math.cos(Math.toRadians(t.getAngle())))),
                                    (int) (t.getPosY() + t.getAlto() / 2 + ((t.getAlto() / 2) * Math.sin(Math.toRadians(t.getAngle())))), animBala, t.getDamage(), t.getSpeed(), t.getAngle(), (int) t.getRange(), t.getPlayer(), i);
                            bullet.add(b1);
                            t.shoot();
                            break;
                        case 4: //Torre dual
                            if (fx) {
                                SoundClip bulletsound = new SoundClip("sounds/lasernormal.wav"); // sonido de disparo
                                bulletsound.play();
                            }
                            randomizer = (int) (Math.random() * (2));
                            if (randomizer < 1) {
                                Bullet b2 = new Bullet((int) (t.getPosX() + t.getAncho() / 2 - 3 + ((t.getAncho() / 2) * Math.cos(Math.toRadians(t.getAngle())))),
                                        (int) (t.getPosY() + t.getAlto() / 2 + 5 + ((t.getAlto() / 2) * Math.sin(Math.toRadians(t.getAngle())))), animBala, t.getDamage(), t.getSpeed(), t.getAngle(), (int) t.getRange(), t.getPlayer(), i);
                                bullet.add(b2);
                            } else {
                                Bullet b2 = new Bullet((int) (t.getPosX() + t.getAncho() / 2 - 3 + ((t.getAncho() / 2) * Math.cos(Math.toRadians(t.getAngle())))),
                                        (int) (t.getPosY() + t.getAlto() / 2 - 5 + ((t.getAlto() / 2) * Math.sin(Math.toRadians(t.getAngle())))), animBala, t.getDamage(), t.getSpeed(), t.getAngle(), (int) t.getRange(), t.getPlayer(), i);
                                bullet.add(b2);
                            }
                            t.shoot();
                            break;

                        case 5: //sniper
                            if (fx) {
                    SoundClip snipersound = new SoundClip("sounds/lasersniper.wav"); // sonido de sniper
                                snipersound.play();
                            }
                            Bullet b3 = new Bullet((int) (t.getPosX() + t.getAncho() / 2 - 3 + ((t.getAncho() / 2 - 5) * Math.cos(Math.toRadians(t.getAngle())))),
                                    (int) (t.getPosY() + t.getAlto() / 2 + ((t.getAlto() / 2 - 2) * Math.sin(Math.toRadians(t.getAngle())))), animBala, t.getDamage(), t.getSpeed(), t.getAngle(), (int) t.getRange(), t.getPlayer(), i);
                            bullet.add(b3);
                            t.shoot();
                            break;

                        case 6: //quad
                            if (fx) {
                                SoundClip bulletsound = new SoundClip("sounds/lasernormal.wav"); // sonido de disparo
                                bulletsound.play();
                            }
                            randomizer = (int) (Math.random() * (5));
                            if (randomizer < 1) {
                                Bullet b2 = new Bullet((int) (t.getPosX() + t.getAncho() / 2 - 3 + ((t.getAncho() / 2) * Math.cos(Math.toRadians(t.getAngle())))),
                                        (int) (t.getPosY() + t.getAlto() / 2 + 5 + ((t.getAlto() / 2) * Math.sin(Math.toRadians(t.getAngle())))), animBala, t.getDamage(), t.getSpeed(), t.getAngle(), (int) t.getRange(), t.getPlayer(), i);
                                bullet.add(b2);
                            } else {
                                if (randomizer < 2) {

                                    Bullet b2 = new Bullet((int) (t.getPosX() + t.getAncho() / 2 - 3 + ((t.getAncho() / 2) * Math.cos(Math.toRadians(t.getAngle())))),
                                            (int) (t.getPosY() + t.getAlto() / 2 - 5 + ((t.getAlto() / 2) * Math.sin(Math.toRadians(t.getAngle())))), animBala, t.getDamage(), t.getSpeed(), t.getAngle(), (int) t.getRange(), t.getPlayer(), i);
                                    bullet.add(b2);
                                } else {
                                    if (randomizer < 3) {
                                        Bullet b2 = new Bullet((int) (t.getPosX() + t.getAncho() / 2 - 3 + ((t.getAncho() / 2) * Math.cos(Math.toRadians(t.getAngle())))),
                                                (int) (t.getPosY() + t.getAlto() / 2 - 10 + ((t.getAlto() / 2) * Math.sin(Math.toRadians(t.getAngle())))), animBala, t.getDamage(), t.getSpeed(), t.getAngle(), (int) t.getRange(), t.getPlayer(), i);
                                        bullet.add(b2);
                                    } else {
                                        Bullet b2 = new Bullet((int) (t.getPosX() + t.getAncho() / 2 - 3 + ((t.getAncho() / 2) * Math.cos(Math.toRadians(t.getAngle())))),
                                                (int) (t.getPosY() + t.getAlto() / 2 + 10 + ((t.getAlto() / 2) * Math.sin(Math.toRadians(t.getAngle())))), animBala, t.getDamage(), t.getSpeed(), t.getAngle(), (int) t.getRange(), t.getPlayer(), i);
                                        bullet.add(b2);
                                    }

                                }
                            }
                            t.shoot();
                            break;

                        case 7: //fuerte
                            if (fx) {
                                SoundClip bulletsound = new SoundClip("sounds/lasernormal.wav"); // sonido de disparo
                                bulletsound.play();
                            }
                            randomizer = (int) (Math.random() * (2));
                            if (randomizer < 1) {
                                Bullet b2 = new Bullet((int) (t.getPosX() + t.getAncho() / 2 - 3 + ((t.getAncho() / 2) * Math.cos(Math.toRadians(t.getAngle())))),
                                        (int) (t.getPosY() + t.getAlto() / 2 + 5 + ((t.getAlto() / 2) * Math.sin(Math.toRadians(t.getAngle())))), animBala, t.getDamage(), t.getSpeed(), t.getAngle(), (int) t.getRange(), t.getPlayer(), i);
                                bullet.add(b2);
                            } else {
                                Bullet b2 = new Bullet((int) (t.getPosX() + t.getAncho() / 2 - 3 + ((t.getAncho() / 2) * Math.cos(Math.toRadians(t.getAngle())))),
                                        (int) (t.getPosY() + t.getAlto() / 2 - 5 + ((t.getAlto() / 2) * Math.sin(Math.toRadians(t.getAngle())))), animBala, t.getDamage(), t.getSpeed(), t.getAngle(), (int) t.getRange(), t.getPlayer(), i);
                                bullet.add(b2);
                            }
                            t.shoot();
                            break;

                        case 8: //laser
                            if (fx) {        
                                SoundClip lasersound = new SoundClip("sounds/laserv1.WAV"); // sonido de laser
                                lasersound.play();
                            }
                            Enemy g = (Enemy) wrench.get(priority);
                            Laser l1 = new Laser((int) (t.getPosX() + t.getAncho() / 2 - 3 + ((t.getAncho() / 2 - 5) * Math.cos(Math.toRadians(t.getAngle())))),
                                    (int) (t.getPosY() + t.getAlto() / 2 + ((t.getAlto() / 2 - 2) * Math.sin(Math.toRadians(t.getAngle())))), g.getPosX() + g.getAncho() / 2, g.getPosY() + g.getAlto() / 2, t.getDamage(), t.getPlayer(), i);
                            lasers.add(l1);
                            g.setHealth(g.getHealth() - t.getDamage());
                            t.shoot();
                            if (t.getPlayer() == 1) {
                                score1 += t.getDamage() / 100.0;
                            }
                            break;

                        case 9: //watulion
                            if (fx) {
                                SoundClip bulletsound = new SoundClip("sounds/lasernormal.wav"); // sonido de disparo
                                bulletsound.play();
                            }
                            Bullet b5 = new Bullet((int) (t.getPosX() + t.getAncho() / 2 + ((t.getAncho() / 2) * Math.cos(Math.toRadians(0)))),
                                    (int) (t.getPosY() + t.getAlto() / 2 - 1 + ((t.getAlto() / 2 - 2) * Math.sin(Math.toRadians(0)))), animBala,
                                    t.getDamage(), t.getSpeed(), t.getAngle(), (int) t.getRange(), t.getPlayer(), i);
                            b5.setWatower();
                            bullet.add(b5);
                            for (int l = 45; l <= 360; l += 45) {
                                b5 = new Bullet((int) (t.getPosX() + t.getAncho() / 2 + ((t.getAncho() / 2) * Math.cos(Math.toRadians(l)))),
                                        (int) (t.getPosY() + t.getAlto() / 2 - 1 + ((t.getAlto() / 2 - 2) * Math.sin(Math.toRadians(l)))), animBala,
                                        t.getDamage(), t.getSpeed(), l, (int) t.getRange(), t.getPlayer(), i);
                                b5.setWatower();
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
                //manejo d
            }
        } else {
            if (wavebegin == 0) {
                wavego = true;
                countx = 100;
                wave++;
                //Decir cuantos enemigos van a salir
                wavecount = 19 + wave;
                if (wave < 0) {
                    wavecountx = wavecount;
                } else {
                    wavecounty = (int) (Math.random() * wavecount / 5) + 1;
                    wavecounta = (int) (Math.random() * wavecount / 5) + 1;
                    wavecountx = wavecount - wavecounty - wavecountx;

                }

            } else {
                if (life != 0) {
                    wavebegin--;
                }
            }
        }
        for (int i = 0; i < tower.size(); i++) {
            Tower t = (Tower) tower.get(i);

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
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_P) {
            if (game) {
                game = false;
                pause = true;
            } else if (pause) {
                game = true;
                pause = false;
                saveStates = false;
                loadStates = false;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_C) {
            if (saveStates) {
                saveStates = false;
            }

            if (loadStates) {
                loadStates = false;
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
        if (introtimer > 0) {
            background = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/mainBackground.png"));
            introtimer = -1;
            main = true;
        }
        if (towerid > 0) {
            Tower t = (Tower) tower.getLast();
            if (t.getPosX() < 1180) {// se planta una torreta en la grid
                if (canput) {
                    player1money -= t.getValue();
                    towerid = 0;
                    if (fx) {
                        place.play();
                    }
                    t.setSet(true);
                    grid[(t.getPosY() - 30) / 30][(t.getPosX() - 8) / 30] = t.getId();
                }
            } else {                // Se remueve la torreta
                tower.removeLast();
                towerid = 0;
            }
        }
        if (bmine) {
            Mine m = (Mine) mine.getLast();
            if (m.getPosX() < 1180) {// se planta una torreta en la grid
                if (canput) {
                    player1money -= 410;
                    bmine = false;
                    if (fx) {
                        place.play();
                    }
                    m.setSet(true);
                }
            } else {                // Se remueve la torreta
                mine.removeLast();
                bmine = false;
            }
        }
        if (game) {
            towerCreate(e, true);
        }
        if (instr) {// si se esta en las instrucciones
            if (new Rectangle(45, 55, 94, 84).contains(e.getPoint())) {
                instr = false;
                main = true;
                background = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/mainBackground.png"));
            }
            if (new Rectangle(291, 422, 186, 52).contains(e.getPoint()) && instrMouse == -1) {
                background = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Nivel1.png"));
                intro.stop();
                if (music) {
                    instrsong.setLooping(true);
                    instrsong.play();
                }
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
                instrMouse = 0;
            }
            instrMouse = instruccion.Click(instrMouse, e.getX(), e.getY());
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
            if (new Rectangle(45, 55, 94, 84).contains(e.getPoint())) {
                menu = false;
                main = true;
                background = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/mainBackground.png"));
            }
            Rectangle rect = new Rectangle(139, 232, 352, 164);
            if (rect.contains(e.getPoint())) {
                intro.stop();
                if (music) {
                    gamesong1.play();
                }
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
                intro.stop();
                if (music) {
                    gamesong1.play();
                }
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
                intro.stop();
                if (music) {
                    gamesong1.play();
                }
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
                intro.stop();
                if (music) {
                    gamesong1.play();
                }
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
        if (lose) {
            lost.stop();
            if (music) {
                intro.play();
            }
            score1 = 0;
            lasers.clear();
            lose = false;
            tower.clear();
            mine.clear();
            player1money = 400;
            player2money = 400;
            wrench.clear();
            rotacion = Math.PI / 60;
            towerid = 0;
            main = true;
            instr = false;
            menu = false;
            countx = 50;
            wavego = false;
            wavecount = 0;
            wave = 0;
            wavebegin = 750;
            bmine = false;
            life = lifeini;
            towerselect = -1;
            levelstart.clear();
            background = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/mainBackground.png"));
        }
        // Si esta en pausa
        if (pause) {
            if (new Rectangle(600, 190, 52, 49).contains(e.getPoint()) && !saveStates && !loadStates) {
                music = !music;
                if (music) {
                    if (gamesong.getClip().getFramePosition() > gamesong1.getClip().getFramePosition()) {
                        gamesong.play();
                    } else {
                        gamesong1.play();
                    }
                } else {
                    gamesong.stop();
                    gamesong1.stop();
                }
            }
            if (new Rectangle(600, 260, 52, 49).contains(e.getPoint()) && !saveStates && !loadStates) {
                fx = !fx;
            }
            if (new Rectangle(478, 461, 180, 66).contains(e.getPoint()) && !saveStates && !loadStates) {
                //Save State
                saveStates = true;
            }

            // SAVESTATES ****** Interaciones de SaveStates
            if (saveStates) {
                // Slot 1
                if (new Rectangle(468, 193, 123, 123).contains(e.getPoint())) {
                    JOptionPane.showMessageDialog(null, " Saving Slot 1!");
                }
                // Slot 2
                if (new Rectangle(623, 193, 123, 123).contains(e.getPoint())) {
                    JOptionPane.showMessageDialog(null, " Saving Slot 2!");
                }
                // Slot 3
                if (new Rectangle(779, 193, 123, 123).contains(e.getPoint())) {
                    JOptionPane.showMessageDialog(null, " Saving Slot 3!");
                }
            }

            if (new Rectangle(696, 461, 180, 66).contains(e.getPoint()) && !saveStates && !loadStates) {
                //Load State
                loadStates = true;
            }

            // LOADSTATES ****** Interaciones de LoadStates
            if (loadStates) {
                // Slot 1
                if (new Rectangle(468, 193, 123, 123).contains(e.getPoint())) {
                    JOptionPane.showMessageDialog(null, " Loading Slot 1!");
                }
                // Slot 2
                if (new Rectangle(623, 193, 123, 123).contains(e.getPoint())) {
                    JOptionPane.showMessageDialog(null, " Loading Slot 2!");
                }
                // Slot 3
                if (new Rectangle(779, 193, 123, 123).contains(e.getPoint())) {
                    JOptionPane.showMessageDialog(null, " Loading Slot 3!");
                }
            }

            if (new Rectangle(478, 560, 180, 66).contains(e.getPoint()) && !saveStates && !loadStates) {
                // Quita la pausa
                pause = false;
                game = true;
            }
            if (new Rectangle(696, 560, 180, 66).contains(e.getPoint()) && !saveStates && !loadStates) {
                // Sale del juego
                gamesong.stop();
                gamesong1.stop();
                if (music) {
                    intro.play();
                }
                lasers.clear();
                pause = false;
                tower.clear();
                mine.clear();
                wrench.clear();
                player1money = 400;
                player2money = 400;
                rotacion = Math.PI / 60;
                towerid = 0;
                main = true;
                instr = false;
                menu = false;
                countx = 50;
                wavego = false;
                wavecount = 0;
                wave = 0;
                score1 = 0;
                wavebegin = 750;
                bmine = false;
                life = lifeini;
                towerselect = -1;
                levelstart.clear();
                background = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/mainBackground.png"));
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
        if (game) {
            towerCreate(e, false);
        }
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
        if (instr) {
            if (instrMouse >= 0) {
                instruccion.PaintMouse(g, instrMouse);
                if (instrMouse >= 17) {
                    g.setFont(new Font("Consolas", Font.BOLD, 16));
                    g.setColor(Color.black);
                    g.drawString("UPGRADE!!!", 1240, 470);
                    for (int k = 0; k < 2; k++) {
                        if (k == 0) {
                            g.drawImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/torretadual.png")),
                                    1231 + k * 76, 480, this);
                        } else {
                            g.drawImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/torretasniper.png")),
                                    1231 + k * 76, 480, this);
                        }
                    }
                }
            }
            for (int i = 0; i < tower.size(); i++) {
                Tower t = (Tower) tower.get(i);
                g.setColor(Color.white);
                //Dibujar circulos del rango
                g.drawOval(t.getPosX() + t.getAncho() / 2 - (int) t.getRange(), t.getPosY() + t.getAlto() / 2 - (int) t.getRange(), (int) t.getRange() * 2, (int) t.getRange() * 2);
                if (towerselect == i && t.getExp() != t.getMAXExp()) {
                    g.setFont(new Font("Consolas", Font.BOLD, 12));
                    g.setColor(Color.black);
                    if (t.getId() == 10) {
                        g.drawString("Damage: None", 1252, 485);
                        g.drawString("Shots/m: None", 1245, 515);
                    } else {
                        g.drawString("Damage: " + t.getDamage(), 1252, 485);
                        g.drawString("Shots/m: " + (int) ((double) 60 / ((double) t.getRate() * 20 / 1000)), 1245, 515);
                    }
                    g.drawString("Range: " + (int) t.getRange(), 1259, 500);
                }
            }
            for (int i = 0; i < mine.size(); i++) {
                Mine m = (Mine) mine.get(i);
                g.drawImage(m.getAnimacion().getImagen(), m.getPosX(), m.getPosY(), this);
            }
        }
        if (lose) {
            //mostrar dinero y dinero
            g.setFont(new Font("Consolas", Font.PLAIN, 22));
            g.setColor(new Color(1346085));
            g.drawString("" + (int) score1, 1296, 62);
            g.drawString("" + (int) player1money, 1288 + 8, 54 + 30);
            g.setColor(Color.red);
            //mostrar numero de la wave
            g.setFont(new Font("Consolas", Font.PLAIN, 30));
            g.setColor(new Color(1346085));
            g.drawString("" + wave, 1312, 437);
            //Decirle al jugador que perdio
            g.setFont(new Font("Consolas", Font.PLAIN, 100));
            g.setColor(Color.red);
            g.drawString("YOU LOST", 400, 400);
            g.setFont(new Font("Consolas", Font.PLAIN, 50));
            g.drawString("Click anywhere to return to the main menu", 100, 450);
        }
        if (game || pause) {
            for (int i = 0; i < lasers.size(); i++) {
                Laser l = (Laser) lasers.get(i);
                if (l.deathTime()) {
                    Graphics2D g2 = (Graphics2D) g;
                    if (l.getTime() > 24) {
                        g.setColor(Color.RED);
                        g2.setStroke(new BasicStroke(3));
                        g2.draw(new Line2D.Float(l.getPosX(), l.getPosY(), l.getEndX(), l.getEndY()));
                    }
                    if (l.getTime() > 12) {
                        g.setColor(Color.orange);
                        g2.setStroke(new BasicStroke(2));
                        g2.draw(new Line2D.Float(l.getPosX(), l.getPosY(), l.getEndX(), l.getEndY()));
                    }
                    g.setColor(Color.yellow);
                    g2.setStroke(new BasicStroke(1));
                    g2.draw(new Line2D.Float(l.getPosX(), l.getPosY(), l.getEndX(), l.getEndY()));
                }
            }
            //mostrar dinero y score
            g.setFont(new Font("Consolas", Font.PLAIN, 22));
            g.setColor(new Color(1346085));
            g.drawString("" + (int) score1, 1296, 62);
            g.drawString("$" + (int) player1money, 1288 + 8, 54 + 30);
            //mostrar numero de la wave
            g.setFont(new Font("Consolas", Font.PLAIN, 30));
            g.setColor(new Color(1346085));
            g.drawString("" + wave, 1312, 437);
            for (int i = 0; i < mine.size(); i++) {
                Mine m = (Mine) mine.get(i);
                g.drawImage(m.getAnimacion().getImagen(), m.getPosX(), m.getPosY(), this);
                if (!canput) {
                    Mine t2 = (Mine) mine.getLast();
                    if (!t2.getSet()) {
                        Graphics2D g2d = (Graphics2D) g; // Create a Java2D version of g.
                        g2d.setColor(Color.red);
                        g2d.setStroke(new BasicStroke(3));
                        g2d.draw(new Line2D.Float(t2.getPosX(), t2.getPosY(), t2.getPosX() + t2.getAncho(), t2.getPosY() + t2.getAlto()));
                        g2d.draw(new Line2D.Float(t2.getPosX() + t2.getAncho(), t2.getPosY(), t2.getPosX(), t2.getPosY() + t2.getAlto()));
                        g2d.setStroke(new BasicStroke(1));
                    }
                }
            }

            for (int i = 0; i < tower.size(); i++) {
                Tower t = (Tower) tower.get(i);
                if (towerselect == i || towerid > 0 && i == tower.size() - 1) {
                    g.setColor(Color.white);
                    //Dibujar circulos del rango
                    g.drawOval(t.getPosX() + t.getAncho() / 2 - (int) t.getRange(), t.getPosY() + t.getAlto() / 2 - (int) t.getRange(), (int) t.getRange() * 2, (int) t.getRange() * 2);
                }
                //dibujar atributos de la torre seleccionada
                if (towerselect == i && (t.getExp() < t.getMAXExp() || t.getId() > 5)) {
                    g.setFont(new Font("Consolas", Font.BOLD, 12));
                    g.setColor(Color.black);
                    if (t.getId() == 10) {
                        g.drawString("Damage: None", 1252, 485);
                        g.drawString("Shots/m: None", 1245, 515);
                    } else {
                        g.drawString("Damage: " + t.getDamage(), 1252, 485);
                        g.drawString("Shots/m: " + (int) ((double) 60 / ((double) t.getRate() * 20 / 1000)), 1245, 515);
                    }
                    g.drawString("Range: " + (int) t.getRange(), 1259, 500);
                }
            }
            g.setColor(Color.red);
            g.fillRect(basex, basey - 3, 90, 3);
            g.setColor(Color.green);
            g.fillRect(basex, basey - 3, life * 90 / lifeini, 3);
        }
        g.drawImage(filter, 8, 31, this);
    }

    /**
     * Metodo para dibujar las torres que giran
     *
     * @param g
     * @param t
     */
    public void towerpaint1(Graphics g) {
        Graphics2D g2d = (Graphics2D) g; // Create a Java2D version of g.
        if (game || instr || pause) {
            for (int i = 0; i < tower.size(); i++) {
                Tower t = (Tower) tower.get(i);
                //XP de la torre
                if (t.getId() < 6) {
                    g2d.setColor(Color.white);
                    g2d.fillRect(t.getPosX(), t.getPosY() - 1, t.getAncho(), 1);
                    g2d.setColor(Color.green);
                    g2d.fillRect(t.getPosX(), t.getPosY() - 1, t.getExp() * t.getAncho() / t.getMAXExp(), 1);
                    if (t.getExp() >= t.getMAXExp()) {
                        g2d.setStroke(new BasicStroke(3));
                        g.setColor(new Color(1346085));
                        g2d.drawOval(t.getPosX(), t.getPosY(), t.getAncho(), t.getAlto());
                    }
                }
                if (towerselect == i) {
                    if (t.getExp() >= t.getMAXExp() && t.getId() < 6) {
                        g2d.setFont(new Font("Consolas", Font.BOLD, 16));
                        g2d.setColor(Color.black);
                        g2d.drawString("UPGRADE!!!", 1240, 470);
                        for (int k = 0; k < 2; k++) {
                            g2d.setFont(new Font("Consolas", Font.BOLD, 12));
                            g2d.setColor(Color.black);
                            if (k == 1) {
                                g2d.drawString("Range", 1231 + k * 76, 520);
                            } else {
                                g2d.drawString("Speed", 1231 + k * 76, 520);
                            }
                            AffineTransform z = new AffineTransform();
                            z.translate(1231 + k * 76, 480);
                            z.rotate(Math.toRadians(t.getAngle()), t.getAncho() / 2, t.getAlto() / 2);
                            g2d.transform(z);
                            switch (t.getId()) {
                                case 3:
                                    if (k == 0) {
                                        g2d.drawImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/torretadual.png")), 0, 0, this);
                                    } else {
                                        g2d.drawImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/torretasniper.png")), 0, 0, this);
                                    }
                                    break;
                                case 4:
                                    if (k == 0) {
                                        g2d.drawImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/torretaquadruple.png")), 0, 0, this);
                                    } else {
                                        g2d.drawImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/torretadualfuerte.png")), 0, 0, this);
                                    }
                                    break;
                                case 5:
                                    if (k == 0) {
                                        g2d.drawImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/torretadualfuerte.png")), 0, 0, this);
                                    } else {
                                        g2d.drawImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/torretasniperlaser.png")), 0, 0, this);
                                    }
                                    break;
                            }
                            try {
                                g2d.transform(z.createInverse());
                            } catch (NoninvertibleTransformException e) {
                                //...
                            }
                        }
                    } else {
                        AffineTransform z = new AffineTransform();
                        z.translate(1215, 480);
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
                //Rate of fire de la torre vertical
                if (t.getId() != 10) {
                    g2d.setColor(Color.white);
                    g2d.fillRect(t.getPosX() - 1, t.getPosY(), 1, t.getAlto());
                    if (t.isBuffed()) {
                        g2d.setColor(new Color(255, 0, 255));
                    } else {
                        g2d.setColor(Color.red);
                    }
                    g2d.fillRect(t.getPosX() - 1, t.getPosY(), 1, t.getAble() * t.getAlto() / t.getRate());
                }
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

                if (!canput) {
                    Tower t2 = (Tower) tower.getLast();
                    if (!t2.getSet()) {
                        g2d.setColor(Color.red);
                        g2d.setStroke(new BasicStroke(3));
                        g2d.draw(new Line2D.Float(t2.getPosX(), t2.getPosY(), t2.getPosX() + t2.getAncho(), t2.getPosY() + t2.getAlto()));
                        g2d.draw(new Line2D.Float(t2.getPosX() + t2.getAncho(), t2.getPosY(), t2.getPosX(), t2.getPosY() + t2.getAlto()));
                    }
                }

            }
            //dibujar vida de los enemigos y los enemigos
            for (int i = 0; i < wrench.size(); i++) {
                Enemy t = (Enemy) wrench.get(i);
                g2d.setColor(Color.red);
                g2d.fillRect(t.getPosX(), t.getPosY() - 1, t.getAncho(), 1);
                if (t.getType() == 1) {
                    g2d.setColor(Color.green);
                }
                if (t.getType() == 2) {
                    g2d.setColor(Color.yellow);
                }
                if (t.getType() == 3) {
                    g2d.setColor(Color.blue);
                }
                g2d.fillRect(t.getPosX(), t.getPosY() - 1, t.health * t.getAncho() / t.getBaseHealth(), 1);
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
                if (t.getSlow()) {
                    g.drawImage(timerimage, t.getPosX(), t.getPosY(), this);
                }
                if (t.getVirus()) {
                    g.drawImage(virusimage, t.getPosX(), t.getPosY(), this);
                }
            }
            for (int i = 0; i < bullet.size(); i++) {
                Bullet t = (Bullet) bullet.get(i);
                AffineTransform z = new AffineTransform();
                z.translate(t.getPosX(), t.getPosY());
                z.rotate(Math.toRadians(t.getAngle()), 3, 1);
                g2d.transform(z);
                g2d.drawImage(t.getAnimacion().getImagen(), 0, 0, this);
                try {
                    g2d.transform(z.createInverse());
                } catch (NoninvertibleTransformException e) {
                    //...
                }
            }
            if (!wavego && game) {
                g.setColor(Color.black);
                g.setFont(new Font("Consolas", Font.PLAIN, 50));
                g.drawString("Wave starts in: ", 400, 400);
                g.drawString("" + ((wavebegin * 20) / 1000 + 1), 570, 450);
            }
            if (pause) {
                g.drawImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/pausa.jpg")), 400, 31, this);
                if (music) {
                    g.drawImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/check.png")), 600, 190, this);
                } else {
                    g.drawImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/uncheck.png")), 600, 190, this);
                }
                if (fx) {
                    g.drawImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/check.png")), 600, 260, this);

                } else {
                    g.drawImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/uncheck.png")), 600, 260, this);
                }
                // Se pintan los SaveStates
                if (saveStates || loadStates) {
                    g.drawImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/SaveStates.png")), 400, 31, this);
                }
            }
            if (game) {
                switch (mouseover) {
                    case 3:
                        g.setColor(Color.black);
                        g.fillRect(1228, 120, 40, 45);
                        g.setColor(new Color(1346085));
                        g.setFont(new Font("Consolas", Font.PLAIN, 10));
                        g.drawString("$100", 1230, 130);
                        g.drawString("D:25", 1230, 140);
                        g.drawString("R:90", 1230, 150);
                        g.drawString("S:60", 1230, 160);
                        break;
                    case 4:
                        g.setColor(Color.black);
                        g.fillRect(1198, 180, 40, 45);
                        g.setColor(new Color(1346085));
                        g.setFont(new Font("Consolas", Font.PLAIN, 10));
                        g.drawString("$500", 1200, 190);
                        g.drawString("D:50", 1200, 200);
                        g.drawString("R:100", 1200, 210);
                        g.drawString("S:250", 1200, 220);
                        break;
                    case 5:
                        g.setColor(Color.black);
                        g.fillRect(1258, 180, 40, 45);
                        g.setColor(new Color(1346085));
                        g.setFont(new Font("Consolas", Font.PLAIN, 10));
                        g.drawString("$420", 1260, 190);
                        g.drawString("D:150", 1260, 200);
                        g.drawString("R:180", 1260, 210);
                        g.drawString("S:40", 1260, 220);
                        break;
                    case 6:
                        g.setColor(Color.black);
                        g.fillRect(1168, 230, 40, 45);
                        g.setColor(new Color(1346085));
                        g.setFont(new Font("Consolas", Font.PLAIN, 10));
                        g.drawString("$800", 1170, 240);
                        g.drawString("D:30", 1170, 250);
                        g.drawString("R:120", 1170, 260);
                        g.drawString("S:1000", 1170, 270);
                        break;
                    case 7:
                        g.setColor(Color.black);
                        g.fillRect(1228, 230, 40, 45);
                        g.setColor(new Color(1346085));
                        g.setFont(new Font("Consolas", Font.PLAIN, 10));
                        g.drawString("$900", 1230, 240);
                        g.drawString("D:100", 1230, 250);
                        g.drawString("R:160", 1230, 260);
                        g.drawString("S:250", 1230, 270);
                        break;
                    case 8:
                        g.setColor(Color.black);
                        g.fillRect(1288, 230, 40, 45);
                        g.setColor(new Color(1346085));
                        g.setFont(new Font("Consolas", Font.PLAIN, 10));
                        g.drawString("$1050", 1290, 240);
                        g.drawString("D:600", 1290, 250);
                        g.drawString("R:250", 1290, 260);
                        g.drawString("S:40", 1290, 270);
                        break;
                    case 9:
                        g.setColor(Color.black);
                        g.fillRect(1198, 300, 40, 45);
                        g.setColor(new Color(1346085));
                        g.setFont(new Font("Consolas", Font.PLAIN, 10));
                        g.drawString("$2000", 1200, 310);
                        g.drawString("D:600", 1200, 320);
                        g.drawString("R:60", 1200, 330);
                        g.drawString("S:40", 1200, 340);
                        break;
                    case 10:
                        g.setColor(Color.black);
                        g.fillRect(1260, 300, 40, 45);
                        g.setColor(new Color(1346085));
                        g.setFont(new Font("Consolas", Font.PLAIN, 10));
                        g.drawString("$1000", 1260, 310);
                        g.drawString("BUFF", 1260, 320);
                        g.drawString("R:60", 1260, 330);
                        g.drawString("2*XP", 1260, 340);
                        break;
                    case 11:
                        g.setColor(Color.black);
                        g.fillRect(1228, 360, 40, 45);
                        g.setColor(new Color(1346085));
                        g.setFont(new Font("Consolas", Font.PLAIN, 10));
                        g.drawString("$410", 1230, 370);
                        g.drawString("Damage", 1230, 380);
                        break;
                    case 12:
                        g.setColor(Color.black);
                        g.fillRect(1288, 360, 40, 45);
                        g.setColor(new Color(1346085));
                        g.setFont(new Font("Consolas", Font.PLAIN, 10));
                        g.drawString("$410", 1290, 370);
                        g.drawString("Infect", 1290, 380);
                        break;
                    case 13:
                        g.setColor(Color.black);
                        g.fillRect(1170, 360, 38, 40);
                        g.setColor(new Color(1346085));
                        g.setFont(new Font("Consolas", Font.PLAIN, 10));
                        g.drawString("$410", 1170, 370);
                        g.drawString("Slow", 1170, 380);
                        break;
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
