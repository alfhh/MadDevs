/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sttd;

import java.awt.Point;

/**
 *
 * @author LuisJuan
 */
public class Enemy extends Base {

    int type = 0; //tipo de enemigo.
    Point start;
    Point end;
    char movment = '0';
    double angle = ((double) (Math.random() * (16))); //angulo de la enemigo
    int speed = ((int) (Math.random() * (5 - 2))) + 2; // Velocidad del enemigo
    int range = 30; //Rango circular de la enemigo. 
    int damage = 5; //Daño de la enemigo
    int value; //Valor de venta de la enemigo
    int target = 0; // enemigo a apuntar
    int health = 100; //Vida
    int basehealth; //Vida inicial
    int lifetime = 0; //tiempo de vida
    int slowtimer = 0; //timer del tiempo que se alentara
    int infectiontimer = 0; //timer del virus

    public Enemy(int posX, int posY, Animacion animacion, int t, int sp, int hp) {
        super(posX, posY, animacion);
        type = t;
        end = new Point(posX, posY);
        start = new Point(posX, posY);
        speed = sp;
        health = hp;
        basehealth = hp;

    }

    /**
     * Metodo que regresa el tipo de enemigo que es
     *
     * @return type que es de tipo <code>int</code>
     */
    int getType() {
        return type;
    }

    /**
     * Regresa el angulo de la bala
     *
     * @return <code>angle</code> que es el angulo del enemigo
     */
    double getAngle() {
        return angle;
    }

    /**
     * Modifica el angulo de la bala
     *
     * @param a de tipo <code>int</code> que es el angulo del enemigo
     */
    void setAngle(double a) {
        angle = a;
    }

    /**
     * Metodo que accesa la velocidad de la bala
     *
     * @return <code>speed</code> que es la velocidad del enemigo
     */
    int getSpeed() {
        if (slowtimer > 0) {
            if (slowtimer % 2 == 0) {
                return speed;
            } else {
                return 0;
            }
        } else {
            return speed;
        }
    }

    /**
     * Metodo que modifica la velocidad de la bala
     *
     * @param s de tipo <code>int</code> que es la velocidad del enemigo
     */
    void setSpeed(int s) {
        speed = s;
    }

    /**
     * Metodo que regresa la vida base del enemigo
     *
     * @return basehealth de tipo <code>int</code> que es la vida del enemigo
     */
    int getBaseHealth() {
        return basehealth;
    }

    /**
     * Metodo que modifica la vida base del enemigo
     *
     * @param h de tipo <code>int</code> que es la vida del enemigo
     */
    void setBaseHealth(int h) {
        basehealth = h;
    }

    /**
     * Metodo que regresa la vida base del enemigo
     *
     * @return health de tipo <code>int</code> que es la vida del enemigo
     */
    int getHealth() {
        return health;
    }

    /**
     * Metodo que modifica la vida base del enemigo
     *
     * @param h de tipo <code>int</code> que es la vida del enemigo
     */
    void setHealth(int h) {
        health = h;
    }

    /**
     * Metodo que sirve para saber si el enemigo fue alentado
     *
     * @return true si fue alentado
     */
    boolean getSlow() {
        if (slowtimer > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que define el tiempo de alentamiento
     */
    void setSlow() {
        slowtimer = 255;
    }

    /**
     * Metodo que actualiza el tiempo de alentamiento
     */
    void slowTimer() {
        if (slowtimer > 0) {
            slowtimer--;
        }
    }

    /**
     * Metodo que sirve para saber si el enemigo fue infectadi
     *
     * @return true si fue infectado
     */
    boolean getVirus() {
        if (infectiontimer > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que define el tiempo de infeccion
     */
    void setVirus() {
        infectiontimer = 250;
    }

    /**
     * Metodo que actualiza el tiempo de infeccion
     */
    void virusTimer() {
        if (infectiontimer > 0) {
            infectiontimer--;
            if (infectiontimer % 50 == 0) {
                if ((int) health * 0.05 > 1) {
                    health -= health * 0.05;
                } else {
                    health--;
                }

            }
        }
    }

    /**
     * Metodo que modifica el movimiento del enemigo
     *
     * @param m que es de tipo <code>char</code>
     */
    void setMov(char m) {
        movment = m;
    }

    /**
     * Metodo que regresa el tipo de movimiento
     *
     * @return movment que es de tipo <code>char</code>
     */
    char getMov() {
        return movment;
    }

    /**
     * Metodo que modifica el punto de comienzo del enemigo
     *
     * @param p que es de tipo <code>Point</code>
     */
    void setStart(Point p) {
        start.setLocation(p.getLocation());
    }

    /**
     * Metodo que accesa el punto de comienzo del enemigo
     *
     * @return start que es de tipo <code>Point</code>
     */
    Point getStart() {
        return start;
    }

    /**
     * Metodo que modifica el punto de fin del enemigo
     *
     * @param p que es de tipo <code>Point</code>
     */
    void setEnd(Point p) {
        end.setLocation(p.getLocation());
    }

    /**
     * Metodo que accesa el punto de fin del enemigo
     *
     * @return end que es de tipo <code>Point</code>
     */
    Point getEnd() {
        return end;
    }

    /**
     * Metodo que actualiza el tiempo de vida del enemigo
     */
    void addLifeTime() {
        lifetime += speed;
    }

    /**
     * Metodo que regresa el tiempo de vida del enemigo
     *
     * @return lifetime que es de tipo <code>int</code>
     */
    int getLifeTime() {
        return lifetime;
    }
}
