/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sttd;

import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.NoSuchElementException;
import java.lang.ArrayIndexOutOfBoundsException;

/**
 *
 * @author LuisJuan
 */
public class Instruccion {

    int waittime = 100;

    Instruccion() {
    }

    //Checar si un punto esta dentro de un circulo
    public boolean inCircle(int circleX, int circleY, int clickX, int clickY, int radius) {
        return java.lang.Math.pow((circleX - clickX), 2) + java.lang.Math.pow((circleY - clickY), 2) < java.lang.Math.pow(radius, 2);
    }

    int Click(int i, int x, int y) {
        switch (i) {
            case 0:
                if (new Rectangle(1268, 121, 30, 30).contains(new Point(x, y))) {
                    i++;
                }
                break;
            case 2:
                if (new Rectangle(128, 301, 30, 30).contains(new Point(x, y))) {
                    i++;
                }
                break;
            case 5:
                i++;
                break;
            case 8:
                if (new Rectangle(1268, 361, 30, 30).contains(new Point(x, y))) {
                    i++;
                }
                break;
            case 10:
                if (new Rectangle(698, 391, 30, 30).contains(new Point(x, y))) {
                    i++;
                }
                break;
            case 14:
                i++;
                waittime = 300;
                break;
            case 15:
                i++;
                break;
            case 16:
                i++;
                break;
            case 17:
                i++;
                break;
            case 18:
                i++;
                break;
        }
        return i;
    }

    int UpdateMouse(int i, LinkedList<Tower> t, LinkedList<Enemy> e, LinkedList<Mine> m, LinkedList<Bullet> b, int grid[][]) {
        PointerInfo pi = MouseInfo.getPointerInfo(); // Obtencion del mouse para seguirlo
        Point p = pi.getLocation();
        switch (i) {
            case 1:
                Image img = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/torretanormal.png"));
                Animacion ani = new Animacion();
                ani.sumaCuadro(img, 100);
                int towerid = 3;
                t.add(new Tower((int) p.getX(), (int) p.getY(), ani, towerid, 1, 15, 25, 50, 100, 90, false));
                i++;
                break;
            case 2:
                try { // Intenta tomar la ultima torre del arreglo
                    Tower tower = (Tower) t.getLast();
                    // si la torreta esta dentro de la grid
                    if (p.getX() < 1208 && p.getY() > 30 && p.getY() < 716) {
                        if (grid[((int) p.getY() - 30) / 30][((int) p.getX() - 8) / 30] == 1) {
                            //Lo acomoda en la matriz
                            tower.setPosX(((int) p.getX()) - ((int) p.getX() - 8) % 30);
                            tower.setPosY(((int) p.getY()) - ((int) p.getY()) % 30);
                        }
                    } else {
                        // se pone en medio del cursor
                        tower.setPosX((int) p.getX() - tower.getAncho() / 2);
                        tower.setPosY((int) p.getY() - tower.getAlto() / 2);
                    }
                } catch (NoSuchElementException n) {
                    towerid = 0;
                }
                break;
            case 3:
                Tower tower = (Tower) t.getLast();
                tower.setSet(true);
                Image imge = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/xwing.jpg"));
                Animacion anie = new Animacion();
                anie.sumaCuadro(imge, 100);
                e.add(new Enemy(8, 61, anie, 1, 6, 50));
                i++;
                break;
            case 4:
                Enemy wrench = (Enemy) e.getLast();
                wrench.addLifeTime();
                // Cuando se encuentra excactamente en la posicion del cuadrante
                if ((wrench.getPosX() - 8) % 30 == 0 && (wrench.getPosY() - 31) % 30 == 0) {
                    p = new Point(wrench.getPosX(), wrench.getPosY());
                    wrench.getStart().setLocation(wrench.getEnd().getLocation());
                    boolean ready = false;
                    char past = wrench.getMov();
                    try {
                        // Si el cuadrante de abajo es camino y no va hacia arriba
                        if (grid[((int) p.getY() - 31) / 30 + 1][((int) p.getX() - 8) / 30] == 0
                                && past != 'u') {
                            wrench.getEnd().setLocation(p.getX(), p.getY() + 30);
                            wrench.setMov('d');
                            ready = true;
                        }
                    } catch (ArrayIndexOutOfBoundsException a) {
                    }
                    try {
                        // Si el cuadrante de arriba es camino y no va hacia abajo
                        if (grid[((int) p.getY() - 31) / 30 - 1][((int) p.getX() - 8) / 30] == 0
                                && past != 'd' && !ready) {
                            wrench.getEnd().setLocation(p.getX(), p.getY() - 30);
                            wrench.setMov('u');
                            ready = true;
                        }
                    } catch (ArrayIndexOutOfBoundsException a) {
                    }
                    try {
                        // Si el cuadrante de izquierda es camino y no va hacia derecha
                        if (grid[((int) p.getY() - 31) / 30][((int) p.getX() - 8) / 30 - 1] == 0
                                && past != 'r' && !ready) {
                            wrench.getEnd().setLocation(p.getX() - 30, p.getY());
                            wrench.setMov('l');
                            ready = true;
                        }
                    } catch (ArrayIndexOutOfBoundsException a) {
                    }
                    try {
                        // Si el cuadrante de derecha es camino y no va hacia izquierda
                        if (grid[((int) p.getY() - 31) / 30][((int) p.getX() - 8) / 30 + 1] == 0
                                && past != 'l') {
                            wrench.getEnd().setLocation(p.getX() + 30, p.getY());
                            wrench.setMov('r');
                        }
                    } catch (ArrayIndexOutOfBoundsException a) {
                    }
                }
                switch (wrench.getMov()) {
                    case 'r':
                        wrench.setPosX(wrench.getPosX() + wrench.getSpeed()); // Va hacia la derecha
                        wrench.setAngle(0);
                        break;
                    case 'l':
                        wrench.setPosX(wrench.getPosX() - wrench.getSpeed()); // Va hacia la izquierda
                        wrench.setAngle(180);
                        break;
                    case 'd':
                        wrench.setPosY(wrench.getPosY() + wrench.getSpeed()); // Va hacia abajo
                        wrench.setAngle(90);
                        break;
                    case 'u':
                        wrench.setPosY(wrench.getPosY() - wrench.getSpeed()); // Va hacia arriba
                        wrench.setAngle(270);
                        break;
                    default:
                        break;
                }

                Tower tower1 = (Tower) t.getLast();
                Enemy wrench1 = (Enemy) e.getLast();
                if (inCircle(tower1.getPosX() + tower1.getAncho() / 2, tower1.getPosY() + tower1.getAlto() / 2,
                        wrench1.getPosX() + wrench1.getAncho() / 2, wrench1.getPosY() + wrench1.getAlto() / 2, (int) tower1.getRange())) {
                    i++;
                }
                break;
            case 6:
                wrench = (Enemy) e.getLast();
                wrench.addLifeTime();
                // Cuando se encuentra excactamente en la posicion del cuadrante
                if ((wrench.getPosX() - 8) % 30 == 0 && (wrench.getPosY() - 31) % 30 == 0) {
                    p = new Point(wrench.getPosX(), wrench.getPosY());
                    wrench.getStart().setLocation(wrench.getEnd().getLocation());
                    boolean ready = false;
                    char past = wrench.getMov();
                    try {
                        // Si el cuadrante de abajo es camino y no va hacia arriba
                        if (grid[((int) p.getY() - 31) / 30 + 1][((int) p.getX() - 8) / 30] == 0
                                && past != 'u') {
                            wrench.getEnd().setLocation(p.getX(), p.getY() + 30);
                            wrench.setMov('d');
                            ready = true;
                        }
                    } catch (ArrayIndexOutOfBoundsException a) {
                    }
                    try {
                        // Si el cuadrante de arriba es camino y no va hacia abajo
                        if (grid[((int) p.getY() - 31) / 30 - 1][((int) p.getX() - 8) / 30] == 0
                                && past != 'd' && !ready) {
                            wrench.getEnd().setLocation(p.getX(), p.getY() - 30);
                            wrench.setMov('u');
                            ready = true;
                        }
                    } catch (ArrayIndexOutOfBoundsException a) {
                    }
                    try {
                        // Si el cuadrante de izquierda es camino y no va hacia derecha
                        if (grid[((int) p.getY() - 31) / 30][((int) p.getX() - 8) / 30 - 1] == 0
                                && past != 'r' && !ready) {
                            wrench.getEnd().setLocation(p.getX() - 30, p.getY());
                            wrench.setMov('l');
                            ready = true;
                        }
                    } catch (ArrayIndexOutOfBoundsException a) {
                    }
                    try {
                        // Si el cuadrante de derecha es camino y no va hacia izquierda
                        if (grid[((int) p.getY() - 31) / 30][((int) p.getX() - 8) / 30 + 1] == 0
                                && past != 'l') {
                            wrench.getEnd().setLocation(p.getX() + 30, p.getY());
                            wrench.setMov('r');
                        }
                    } catch (ArrayIndexOutOfBoundsException a) {
                    }
                }
                switch (wrench.getMov()) {
                    case 'r':
                        wrench.setPosX(wrench.getPosX() + wrench.getSpeed()); // Va hacia la derecha
                        wrench.setAngle(0);
                        break;
                    case 'l':
                        wrench.setPosX(wrench.getPosX() - wrench.getSpeed()); // Va hacia la izquierda
                        wrench.setAngle(180);
                        break;
                    case 'd':
                        wrench.setPosY(wrench.getPosY() + wrench.getSpeed()); // Va hacia abajo
                        wrench.setAngle(90);
                        break;
                    case 'u':
                        wrench.setPosY(wrench.getPosY() - wrench.getSpeed()); // Va hacia arriba
                        wrench.setAngle(270);
                        break;
                    default:
                        break;
                }
                Tower tower2 = (Tower) t.getLast();
                Enemy g = (Enemy) e.getLast();
                if (!inCircle(tower2.getPosX() + tower2.getAncho() / 2, tower2.getPosY() + tower2.getAlto() / 2,
                        g.getPosX() + g.getAncho() / 2, g.getPosY() + g.getAlto() / 2, (int) tower2.getRange())) {
                    i++;
                } else {
                    double bullet_angle = Math.atan2((tower2.getPosX() + tower2.getAncho() / 2) - (int) (g.getPosX() + g.getAncho() / 2 + ((g.getAncho() / 2) * Math.cos(Math.toRadians(g.getAngle())))),
                            (tower2.getPosY() + tower2.getAlto() / 2) - (int) (g.getPosY() + g.getAlto() / 2 - 1 + ((g.getAlto() / 2 - 2) * Math.sin(Math.toRadians(g.getAngle()))))) - Math.PI / 2;
                    tower2.setAngle(Math.toDegrees(-bullet_angle - Math.PI));

                    if (tower2.canShoot()) {
                        Image bala = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Bala.png"));
                        Animacion anib = new Animacion();
                        anib.sumaCuadro(bala, 100);
                        Bullet b1 = new Bullet((int) (tower2.getPosX() + tower2.getAncho() / 2 - 3 + ((tower2.getAncho() / 2) * Math.cos(Math.toRadians(tower2.getAngle())))),
                                (int) (tower2.getPosY() + tower2.getAlto() / 2 + ((tower2.getAlto() / 2) * Math.sin(Math.toRadians(tower2.getAngle())))), anib,
                                tower2.getDamage(), tower2.getSpeed(), tower2.getAngle(), (int) tower2.getRange(), tower2.getPlayer(), i);
                        b.add(b1);
                        tower2.shoot();
                    }
                    try {
                        Bullet bull = (Bullet) b.getFirst();
                        if (!bull.distanceTime()) {
                            bull.setPosX(bull.getPosX() + (int) (bull.getSpeed() * Math.cos(Math.toRadians(bull.getAngle()))));
                            bull.setPosY(bull.getPosY() + (int) (bull.getSpeed() * Math.sin(Math.toRadians(bull.getAngle()))));
                        } else {
                            b.removeLast();
                        }
                        Bullet bl = (Bullet) b.getFirst();
                        if (wrench.getPerimetro().intersects(bl.getPerimetro())) {
                            wrench.setHealth(wrench.getHealth() - bl.getDamage());
                            tower2.Exp();
                            bl.destroy();
                        }
                    } catch (NoSuchElementException x) {
                        //...
                    }
                }
                break;
            case 7:
                wrench = (Enemy) e.getLast();
                wrench.addLifeTime();
                // Cuando se encuentra excactamente en la posicion del cuadrante
                if ((wrench.getPosX() - 8) % 30 == 0 && (wrench.getPosY() - 31) % 30 == 0) {
                    p = new Point(wrench.getPosX(), wrench.getPosY());
                    wrench.getStart().setLocation(wrench.getEnd().getLocation());
                    boolean ready = false;
                    char past = wrench.getMov();
                    try {
                        // Si el cuadrante de abajo es camino y no va hacia arriba
                        if (grid[((int) p.getY() - 31) / 30 + 1][((int) p.getX() - 8) / 30] == 0
                                && past != 'u') {
                            wrench.getEnd().setLocation(p.getX(), p.getY() + 30);
                            wrench.setMov('d');
                            ready = true;
                        }
                    } catch (ArrayIndexOutOfBoundsException a) {
                    }
                    try {
                        // Si el cuadrante de arriba es camino y no va hacia abajo
                        if (grid[((int) p.getY() - 31) / 30 - 1][((int) p.getX() - 8) / 30] == 0
                                && past != 'd' && !ready) {
                            wrench.getEnd().setLocation(p.getX(), p.getY() - 30);
                            wrench.setMov('u');
                            ready = true;
                        }
                    } catch (ArrayIndexOutOfBoundsException a) {
                    }
                    try {
                        // Si el cuadrante de izquierda es camino y no va hacia derecha
                        if (grid[((int) p.getY() - 31) / 30][((int) p.getX() - 8) / 30 - 1] == 0
                                && past != 'r' && !ready) {
                            wrench.getEnd().setLocation(p.getX() - 30, p.getY());
                            wrench.setMov('l');
                            ready = true;
                        }
                    } catch (ArrayIndexOutOfBoundsException a) {
                    }
                    try {
                        // Si el cuadrante de derecha es camino y no va hacia izquierda
                        if (grid[((int) p.getY() - 31) / 30][((int) p.getX() - 8) / 30 + 1] == 0
                                && past != 'l') {
                            wrench.getEnd().setLocation(p.getX() + 30, p.getY());
                            wrench.setMov('r');
                        }
                    } catch (ArrayIndexOutOfBoundsException a) {
                    }
                }
                switch (wrench.getMov()) {
                    case 'r':
                        wrench.setPosX(wrench.getPosX() + wrench.getSpeed()); // Va hacia la derecha
                        wrench.setAngle(0);
                        break;
                    case 'l':
                        wrench.setPosX(wrench.getPosX() - wrench.getSpeed()); // Va hacia la izquierda
                        wrench.setAngle(180);
                        break;
                    case 'd':
                        wrench.setPosY(wrench.getPosY() + wrench.getSpeed()); // Va hacia abajo
                        wrench.setAngle(90);
                        break;
                    case 'u':
                        wrench.setPosY(wrench.getPosY() - wrench.getSpeed()); // Va hacia arriba
                        wrench.setAngle(270);
                        break;
                    default:
                        break;
                }
                if (waittime == 0) {
                    i++;
                    waittime = 300;
                } else {
                    waittime--;
                }
                break;
            case 9:
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
                m.add(new Mine((int) p.getX(), (int) p.getY(), animMine, 1,1));

                i++;
                break;
            case 10:
                try { // Intenta tomar la ultima torre del arreglo
                    Mine mine = (Mine) m.getLast();
                    // si la torreta esta dentro de la grid
                    if (p.getX() < 1208 && p.getY() > 30 && p.getY() < 716) {
                        if (grid[((int) p.getY() - 30) / 30][((int) p.getX() - 8) / 30] == 0) {
                            mine.setPosX(((int) p.getX()) - ((int) p.getX() - 8) % 30);
                            mine.setPosY(((int) p.getY()) - ((int) p.getY()) % 30);
                        }
                    } else {
                        // se pone en medio del cursor
                        mine.setPosX((int) p.getX() - mine.getAncho() / 2);
                        mine.setPosY((int) p.getY() - mine.getAlto() / 2);
                    }
                } catch (NoSuchElementException n) {
                    //...
                }
                break;
            case 11:
                Mine mine = m.getLast();
                mine.setSet(true);
                i++;
                break;
            case 12:
                wrench = (Enemy) e.getLast();
                wrench.addLifeTime();
                // Cuando se encuentra excactamente en la posicion del cuadrante
                if ((wrench.getPosX() - 8) % 30 == 0 && (wrench.getPosY() - 31) % 30 == 0) {
                    p = new Point(wrench.getPosX(), wrench.getPosY());
                    wrench.getStart().setLocation(wrench.getEnd().getLocation());
                    boolean ready = false;
                    char past = wrench.getMov();
                    try {
                        // Si el cuadrante de abajo es camino y no va hacia arriba
                        if (grid[((int) p.getY() - 31) / 30 + 1][((int) p.getX() - 8) / 30] == 0
                                && past != 'u') {
                            wrench.getEnd().setLocation(p.getX(), p.getY() + 30);
                            wrench.setMov('d');
                            ready = true;
                        }
                    } catch (ArrayIndexOutOfBoundsException a) {
                    }
                    try {
                        // Si el cuadrante de arriba es camino y no va hacia abajo
                        if (grid[((int) p.getY() - 31) / 30 - 1][((int) p.getX() - 8) / 30] == 0
                                && past != 'd' && !ready) {
                            wrench.getEnd().setLocation(p.getX(), p.getY() - 30);
                            wrench.setMov('u');
                            ready = true;
                        }
                    } catch (ArrayIndexOutOfBoundsException a) {
                    }
                    try {
                        // Si el cuadrante de izquierda es camino y no va hacia derecha
                        if (grid[((int) p.getY() - 31) / 30][((int) p.getX() - 8) / 30 - 1] == 0
                                && past != 'r' && !ready) {
                            wrench.getEnd().setLocation(p.getX() - 30, p.getY());
                            wrench.setMov('l');
                            ready = true;
                        }
                    } catch (ArrayIndexOutOfBoundsException a) {
                    }
                    try {
                        // Si el cuadrante de derecha es camino y no va hacia izquierda
                        if (grid[((int) p.getY() - 31) / 30][((int) p.getX() - 8) / 30 + 1] == 0
                                && past != 'l') {
                            wrench.getEnd().setLocation(p.getX() + 30, p.getY());
                            wrench.setMov('r');
                        }
                    } catch (ArrayIndexOutOfBoundsException a) {
                    }
                }
                switch (wrench.getMov()) {
                    case 'r':
                        wrench.setPosX(wrench.getPosX() + wrench.getSpeed()); // Va hacia la derecha
                        wrench.setAngle(0);
                        break;
                    case 'l':
                        wrench.setPosX(wrench.getPosX() - wrench.getSpeed()); // Va hacia la izquierda
                        wrench.setAngle(180);
                        break;
                    case 'd':
                        wrench.setPosY(wrench.getPosY() + wrench.getSpeed()); // Va hacia abajo
                        wrench.setAngle(90);
                        break;
                    case 'u':
                        wrench.setPosY(wrench.getPosY() - wrench.getSpeed()); // Va hacia arriba
                        wrench.setAngle(270);
                        break;
                    default:
                        break;
                }
                mine = m.getLast();
                if (mine.getSet() && wrench.getPerimetro().intersects(mine.getPerimetro())) {
                    wrench.setHealth(wrench.getHealth() - mine.getDam());
                    mine.setExp(117);
                    mine.setSet(false);
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

                    mine.setAnimacion(a);
                }
                if (wrench.getHealth() <= 0) {
                    e.removeLast(); // Desaparece
                    i++;
                }
                break;
            case 13:
                mine = (Mine) m.getFirst();
                if (mine.getExp() == -1) {
                    m.removeFirst();
                } else {
                    mine.getAnimacion().actualiza(20);
                }
                if (mine.getExp() > 0) {
                    mine.Exp();
                }
                if (m.isEmpty()) {
                    i++;
                }
                break;
            case 14:
                tower = t.getLast();
                if (waittime == 0) {
                    waittime = 300;
                } else {
                    if (waittime % 10 == 0) {
                        tower.Exp();
                    }
                    waittime--;
                }
                break;
            case 15:
                tower = t.getLast();
                if (waittime % 10 == 0) {
                    tower.Exp();
                    waittime += 10;
                }
                waittime--;
                break;

            case 16:
                tower = t.getLast();
                if (waittime == 0) {
                    waittime = 300;
                } else {
                    if (waittime % 10 == 0) {
                        tower.Exp();
                    }
                    waittime--;
                }
                break;
            case 17:
                tower = t.getLast();
                if (waittime == 0) {
                    waittime = 300;
                } else {
                    if (waittime % 10 == 0) {
                        tower.Exp();
                    }
                    waittime--;
                }
            case 18:
                tower = t.getLast();
                if (waittime == 0) {
                    waittime = 300;
                } else {
                    if (waittime % 10 == 0) {
                        tower.Exp();
                    }
                    waittime--;
                }
                break;
            case 19:
                t.clear();
                e.clear();
                m.clear();
                b.clear();
                i++;
                break;
        }
        return i;

    }

    void PaintMouse(Graphics g, int i
    ) {
        switch (i) {
            case 0:
                g.setFont(new Font("Consolas", Font.PLAIN, 50));
                g.setColor(new Color(16769536));
                g.drawString("Welcome to the tutorial", 350, 100);
                g.drawString("Lets start by clicking this tower", 270, 150);
                g.setColor(Color.red);
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(8));
                g.drawRect(1268, 121, 30, 30);
                g2.setStroke(new BasicStroke(1));
                break;
            case 2:
                g.setFont(new Font("Consolas", Font.PLAIN, 50));
                g.setColor(new Color(16769536));
                g.drawString("All right!!", 450, 100);
                g.drawString("Now, let's put it here to protect", 250, 150);
                g.drawString("ourselves for incomming badies", 250, 200);
                g.setColor(Color.red);
                g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(8));
                g.drawRect(128, 301, 30, 30);
                g2.setStroke(new BasicStroke(1));
                break;
            case 5:
                g.setFont(new Font("Consolas", Font.PLAIN, 50));
                g.setColor(new Color(16769536));
                g.drawString("OK, Looks like the enemy is", 270, 150);
                g.drawString("within the range of our turret", 270, 200);
                g.drawString("Just sit back and", 270, 250);
                g.drawString("let the turret handle business", 270, 300);
                g.drawString("Click anywhere to continue", 500, 700);
                break;
            case 8:
                g.setFont(new Font("Consolas", Font.PLAIN, 50));
                g.setColor(new Color(16769536));
                g.drawString("Hmmm, looks like our turret is", 270, 150);
                g.drawString("not that strong after all", 270, 200);
                g.drawString("Lets put a mine here so it won't", 270, 250);
                g.drawString("go any further", 270, 300);
                g.setColor(Color.red);
                g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(8));
                g.drawRect(1268, 361, 30, 30);
                g2.setStroke(new BasicStroke(1));
                break;
            case 10:
                g.setFont(new Font("Consolas", Font.PLAIN, 50));
                g.setColor(new Color(16769536));
                g.drawString("Hmmm, looks like our turret is", 270, 150);
                g.drawString("not that strong after all", 270, 200);
                g.drawString("Lets put a mine here so it won't", 270, 250);
                g.drawString("go any further", 270, 300);
                g.setColor(Color.red);
                g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(8));
                g.drawRect(698, 391, 30, 30);
                g2.setStroke(new BasicStroke(1));
                break;
            case 14:
                g.setFont(new Font("Consolas", Font.PLAIN, 50));
                g.setColor(new Color(16769536));
                g.drawString("Sweet, now you know how to", 270, 150);
                g.drawString("place a turret and a mine", 270, 200);
                g.drawString("And looks like your turret", 270, 250);
                g.drawString("got some XP from that shot", 270, 300);
                g.drawString("Click anywhere to continue", 500, 700);
                break;
            case 15:
                g.setFont(new Font("Consolas", Font.PLAIN, 50));
                g.setColor(new Color(16769536));
                g.drawString("XP = experience", 270, 150);
                g.drawString("Note: its that green bar that's", 270, 200);
                g.drawString("filling up right now above", 270, 250);
                g.drawString("the turret", 270, 300);
                g.drawString("Click anywhere to continue", 500, 700);
                break;
            case 16:
                g.setFont(new Font("Consolas", Font.PLAIN, 50));
                g.setColor(new Color(16769536));
                g.drawString("*Side Note*", 270, 150);
                g.drawString("The bar on the side of the tower", 270, 200);
                g.drawString("is its cooldown meter, meaning", 270, 250);
                g.drawString("it won't shoot until its empty", 270, 300);
                g.drawString("Click anywhere to continue", 500, 700);
                break;
            case 17:
                g.setFont(new Font("Consolas", Font.PLAIN, 50));
                g.setColor(new Color(16769536));
                g.drawString("When the turret reaches its full", 250, 150);
                g.drawString("XP, an upgrade is available", 250, 200);
                g.drawString("The left upgrade focuses on Shots/m", 250, 250);
                g.drawString("and the right focuses on power", 250, 300);
                g.drawString("Click anywhere to continue", 500, 700);
                g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(8));
                g.drawRect(1208, 458, 150, 74);
                g2.setStroke(new BasicStroke(1));
                break;
            case 18:
                g.setFont(new Font("Consolas", Font.PLAIN, 50));
                g.setColor(new Color(16769536));
                g.drawString("Now you know all you need to", 270, 150);
                g.drawString("know, so its time for you to", 270, 200);
                g.drawString("go ahead a defend your base", 270, 250);
                g.drawString("(click anywhere to end tutorial)", 250, 300);
                break;

        }
    }
}
