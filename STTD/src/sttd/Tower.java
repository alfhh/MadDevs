/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sttd;

import java.awt.Toolkit;
import java.awt.Image;

/**
 *
 * @author LuisJuan
 */
public class Tower extends Base {

    double buffmultiplier = 1; //termina que tanto se va a multiplicar el daño y rof de la nave
    boolean set; // Boleano que representa se la torreta ya esta puesta
    double angle = 0; //angulo de la torre
    int speed = 3; // Velocidad de las balas. -1 convoca lazer instantaneo
    double range = 90; //Rango circular de la torre. 
    int damage = 5; //Daño de la torre
    int playerid = 1; //Dueño de la torre
    int rateoffire = 10; //Delay entre disparos
    int towerid; //Id de la torre (Utilizado para identificar la torre independientemente de su lugar en la linked list)
    int value; //Valor de venta de la torre
    int target = 0; // enemigo a apuntar
    int canshoot = 0; //Timer para poder disparar
    int exp; //Experiencia de la torre
    boolean girar = false; //Ver si deberia de girar

    public Tower(int posX, int posY, Animacion animacion, int t, int pl, int s, int dmg, int ra, int val, int rng, boolean m) {
        super(posX, posY, animacion);
        towerid = t;
        playerid = pl;
        speed = s;
        damage = dmg;
        rateoffire = ra;
        value = val;
        range = rng;
        set = false;
        girar = m;
        exp = 0;
    }

    /**
     * Regresa el angulo de la bala
     *
     * @return <code>angle</code> que es el angulo de la torre
     */
    double getAngle() {
        return angle;
    }

    /**
     * Modifica el angulo de la bala
     *
     * @param a de tipo <code>int</code> que es el angulo de la torre
     */
    void setAngle(double a) {
        angle = a;
    }

    /**
     * Metodo que accesa la velocidad de la bala
     *
     * @return <code>speed</code> que es la velocidad de la torre
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
     * Metodo que regresa el rengo de la torre
     *
     * @return range que es de tipo <code>double</code>
     */
    double getRange() {
        return range;
    }

    /**
     * Metodo que modifica el rengo de la torre
     *
     * @param r que es de tipo <code>double</code>
     */
    void setRange(int r) {
        range = r;
    }

    /**
     * Metodo que accesa el daño de la bala (
     *
     * @return damage de tipo <code>int</code> que es el daño de la torre
     */
    int getDamage() {
        return (int) (damage * buffmultiplier);
    }

    /**
     * Metodo que modifica el daño de la bala
     *
     * @param d de tipo <code>int</code> que es el daño de la torre
     */
    void setDamage(int d) {
        damage = d;
    }

    /**
     * Metodo que sirve para obtener los disparos por minuto
     *
     * @return los disparos por minuto en <code>int</code>
     */
    int getRate() {
        return (int) (rateoffire / buffmultiplier);
    }

    /**
     * Metodo que sirve para modificar los disparos por minuto
     *
     * @param ra los disparos por minuto en <code>int</code>
     */
    void setRate(int ra) {
        rateoffire = ra;
    }

    /**
     * Regrela el jugador dueño de la bala
     *
     * @return playerid que es de tipo <code>int</code> que es el jugador de la
     * torre
     */
    int getPlayer() {
        return playerid;
    }

    /**
     * Metodo que te dice si puede disparar
     *
     * @return true si puede disparar
     */
    boolean canShoot() {
        if (set) {
            if (canshoot == 0) {
                return true;
            } else {
                canshoot += -1;
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Metodo que dispara
     */
    void shoot() {
        canshoot = rateoffire;
    }

    /**
     * Metodo que regresa si es puede disparar
     *
     * @return canshoot que es de tipo <code>int</code>
     */
    int getAble() {
        return canshoot;
    }

    /**
     * Metodo que regresa si puede girar
     *
     * @return girar que es de tipo <code>boolean</code>
     */
    boolean isMine() {
        return girar;
    }

    /**
     * Metodo modifica si esta puesta
     *
     * @param s que es de tipo <code>int</code>
     */
    void setSet(boolean s) {
        set = s;
    }

    /**
     * Metodo que modifica si puede girar
     *
     * @param m que es de tipo <code>int</code>
     */
    void setGira(boolean m) {
        girar = m;
    }

    /**
     * Metodo que regresa si esta puesta
     *
     * @return set que es de tipo <code>int</code>
     */
    boolean getSet() {
        return set;
    }

    /**
     * Metodo que modifica el valor de la torre
     *
     * @param s que es de tipo <code>int</code>
     */
    void setValue(int s) {
        value = s;
    }

    /**
     * Metodo que regresa el valor de la torre
     *
     * @return value que es de tipo <code>int</code>
     */
    int getValue() {
        return value;
    }

    /**
     * Metodo que regresa si la torre esta biffeada
     *
     * @return true si esta buffeada
     */
    boolean isBuffed() {
        if (buffmultiplier > 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que regresa el Id de la torre
     *
     * @return towerid que es de tipo <code>int</code>
     */
    int getId() {
        return towerid;
    }

    /**
     * Metodo que actualiza la experiencia de la torre
     */
    void Exp() {
        if (exp < this.getMAXExp()) {
            if (towerid > 3) {
                if (isBuffed()) {
                    exp += damage / 10;
                }
                exp += damage / 10;
            } else {
                if (isBuffed()) {
                    exp++;
                }
                exp++;
            }
        }
    }

    /**
     * Metodo que regresa la experiencia de la torre
     *
     * @return exp que es de tipo <code>int</code>
     */
    int getExp() {
        return exp;
    }

    /**
     * Metodo que modifica la experiencia de la torre
     *
     * @param e que es de tipo <code>int</code>
     */
    void setExp(int e) {
        exp = e;
    }

    /**
     * Metodo que regresa el buff
     *
     * @return buffmultiplier que es de tipo <code>double</code>
     */
    double getMul() {
        return buffmultiplier;
    }

    /**
     * Metodo que modifica el buff
     *
     * @param e que es de tipo <code>double</code>
     */
    void setMul(double e) {
        buffmultiplier = e;
    }

    /**
     * Metodo que regresa la experiencia maxima
     *
     * @return un valor entero de la experiencia maxima
     */
    int getMAXExp() {
        if (towerid >= 6) {
            return -1;
        } else {
            if (towerid > 3) {
                return 5000;
            } else {
                return 100;
            }
        }
    }

    /**
     * Metodo que sube de nivel de la torre en rpm
     */
    void getUpgL() {
        towerid += towerid / 2;
        Animacion a;
        switch (towerid) {
            case 4:
                speed = 20;
                damage = 50;
                rateoffire = 12;
                range = 100;
                value = 500;
                exp = 0;
                a = new Animacion();
                a.sumaCuadro(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/torretadual.png")), 100);
                animacion = a;
                break;
            case 6:
                speed = 27;
                damage = 30;
                rateoffire = 3;
                range = 120;
                value = 800;
                exp = 0;
                a = new Animacion();
                a.sumaCuadro(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/torretaquadruple.png")), 100);
                animacion = a;
                break;
            case 7:
                speed = 36;
                damage = 100;
                rateoffire = 12;
                range = 160;
                value = 900;
                exp = 0;
                a = new Animacion();
                a.sumaCuadro(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/torretadualfuerte.png")), 100);
                animacion = a;
                break;
        }
    }

    /**
     * Metodo que sube de nivel a la torre en poder
     */
    void getUpgR() {
        towerid += towerid / 2 + 1;
        Animacion a;
        switch (towerid) {
            case 5:
                speed = 30;
                damage = 150;
                rateoffire = 75;
                value = 420;
                range = 180;
                exp = 0;
                a = new Animacion();
                a.sumaCuadro(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/torretasniper.png")), 100);
                animacion = a;
                break;
            case 7:
                speed = 36;
                damage = 100;
                rateoffire = 12;
                range = 160;
                value = 900;
                exp = 0;
                a = new Animacion();
                a.sumaCuadro(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/torretadualfuerte.png")), 100);
                animacion = a;
                break;
            case 8:
                speed = -1;
                damage = 600;
                rateoffire = 75;
                range = 250;
                value = 1050;
                exp = 0;
                a = new Animacion();
                a.sumaCuadro(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/torretasniperlaser.png")), 100);
                animacion = a;
                break;
        }
    }
}
