/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sttd;

/**
 *
 * @author LuisJuan
 */
public class Bullet extends Base {

    int damage = 5; //Daño de la bala
    double angle; //angulo de la bala
    int speed = 3; // Velocidad de las bala. -1 convoca lazer instantaneo
    int distance = 45; // Distancia recorrida antes de desaparecer
    int playerid = 1; //Dueño de la bala
    int towernum; // numero de indice de la torreta
    boolean watower; //balas penetradoras

    public Bullet(int posX, int posY, Animacion animacion, int dmg, int sp, double ang, int range, int pl, int t) {
        super(posX, posY, animacion);
        damage = dmg;
        speed = sp;
        angle = ang;
        distance = range;
        playerid = pl;
        towernum = t;
    }

    /**
     * Regresa el angulo de la bala
     *
     * @return <code>angle</code> que es el angulo de la bala
     */
    double getAngle() {
        return angle;
    }

    /**
     * Modifica el angulo de la bala
     *
     * @param a de tipo <code>int</code> que es el angulo de la bala
     */
    void setAngle(int a) {
        angle = a;
    }

    /**
     * Metodo que accesa la velocidad de la bala
     *
     * @return <code>speed</code> que es la velocidad de la bala
     */
    int getSpeed() {
        return speed;
    }

    /**
     * Metodo que modifica la velocidad de la bala
     *
     * @param s de tipo <code>int</code> que es la velocidad de la bala
     */
    void setSpeed(int s) {
        speed = s;
    }

    /**
     * Metodo que accesa el daño de la bala (
     *
     * @return damage de tipo <code>int</code> que es el daño de la bala
     */
    int getDamage() {
        return damage;
    }

    /**
     * Metodo que modifica el daño de la bala
     *
     * @param d de tipo <code>int</code> que es el daño de la bala
     */
    void setDamage(int d) {
        damage = d;
    }

    /**
     * Metodo que accesa la distancia que recorre la bala
     *
     * @return speed de tipo <code>int</code> que es la velocidad de la bala
     */
    int getDistance() {
        return speed;
    }

    /**
     * Destruye indirectamente la bala
     */
    void destroy() {
        distance = 0;
    }

    /**
     * Metodo que regresa si la bala se debe seguir moviendo
     *
     * @return true si se debe mover
     */
    boolean distanceTime() {
        distance -= speed;
        if (distance <= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que regresa el numero de indice de su torre
     *
     * @return towernum que es de tipo <code>int</code> que es la torre de la
     * bala
     */
    int getTower() {
        return towernum;
    }

    /**
     * Metodo que dice si la bala es de la torre Watulio
     */
    void setWatower() {
        watower = true;
    }

    /**
     * Regresa la torre Watulio de la bala
     *
     * @return watower que es de tipo <code>int</code> que es la torre Watulio
     * de la balla
     */
    boolean getWatower() {
        return watower;
    }

    /**
     * Regrela el jugador dueño de la bala
     *
     * @return playerid que es de tipo <code>int</code> que es el jugador de la
     * bala
     */
    int getPlayer() {
        return playerid;
    }

}
