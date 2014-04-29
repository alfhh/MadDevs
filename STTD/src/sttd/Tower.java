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

    //Metodo para obtener el angulo de la torre
    double getAngle() {
        return angle;
    }

    //Metodo para asignar el angulo de la torre
    void setAngle(double a) {
        angle = a;
    }

    //Metodo para obtener el angulo de la torre
    int getSpeed() {
        return speed;
    }

    //Metodo para asignar el angulo de la torre
    void setSpeed(int s) {
        speed = s;
    }

    //Metodo para obtener el rango de la torre
    double getRange() {
        return range;
    }

    //Metodo para asignar el rango de la torre
    void setRange(int r) {
        range = r;
    }

    //Metodo para obtener el daño de la torre
    int getDamage() {
        return damage;
    }

    //Metodo para asignar el daño de la torre
    void setDamage(int d) {
        damage = d;
    }

    //Metodo para obtener el daño de la torre
    int getRate() {
        return rateoffire;
    }

    //Metodo para asignar el daño de la torre
    void setRate(int ra) {
        rateoffire = ra;
    }

    //Metodo para obtener el numero del jugador de la torre
    int getPlayer() {
        return playerid;
    }

    //Metodo para ver si puede disparar
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
//Dispara!

    void shoot() {
        canshoot = rateoffire;
    }

    int getAble() {
        return canshoot;
    }

    //Metodo para ver si es una mina o no
    boolean isMine() {
        return girar;
    }

    //Metodo para modificar si esta puesta
    void setSet(boolean s) {
        set = s;
    }

    //Metodo para ver si esta puesta
    boolean getSet() {
        return set;
    }

    //Metodo para obtener el tipo de la torre
    int getId() {
        return towerid;
    }

    // Metodo que suma experiencia
    void Exp() {
        if (exp < this.getMAXExp()) {
            exp++;
        }
    }

    // Metodo que access la experiencia
    int getExp() {
        return exp;
    }

    // Metodo que modifica la experiencia
    void setExp(int e) {
        exp = e;
    }

    // Metodo que access la experiencia Maxima
    int getMAXExp() {
        if (towerid >= 6) {
            return -1;
        } else {
            return (towerid / 4 * 300 + 100);
        }
    }

    // Metodo que regresa la towerid de el ugrade izquierdo
    void getUpgL() {
        towerid += towerid / 2;
        Animacion a;
        switch (towerid) {
            case 4:
                speed = 20;
                damage = 50;
                rateoffire = 25;
                range = 100;
                exp = 0;
                a = new Animacion();
                a.sumaCuadro(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/torretadual.png")), 100);
                animacion = a;
                break;
            case 6:
                speed = 27;
                damage = 100;
                rateoffire = 12;
                range = 120;
                exp = 0;
                a = new Animacion();
                a.sumaCuadro(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/torretaquadruple.png")), 100);
                animacion = a;
                break;
            case 7:
                speed = 24;
                damage = 400;
                rateoffire = 50;
                range = 120;
                exp = 0;
                a = new Animacion();
                a.sumaCuadro(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/torretadualfuerte.png")), 100);
                animacion = a;
                break;
        }
    }

    // Metodo que regresa la towerid de el ugrade izquierdo
    void getUpgR() {
        towerid += towerid / 2 + 1;
        Animacion a;
        switch (towerid) {
            case 5:
                speed = 30;
                damage = 150;
                rateoffire = 75;
                range = 180;
                exp = 0;
                a = new Animacion();
                a.sumaCuadro(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/torretasniper.png")), 100);
                animacion = a;
                break;
            case 7:
                speed = 24;
                damage = 400;
                rateoffire = 50;
                range = 120;
                exp = 0;
                a = new Animacion();
                a.sumaCuadro(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/torretadualfuerte.png")), 100);
                animacion = a;
                break;
            case 8:
                speed = 30;
                damage = 600;
                rateoffire = 75;
                range = 250;
                exp = 0;
                a = new Animacion();
                a.sumaCuadro(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/torretasniperlaser.png")), 100);
                animacion = a;
                break;
        }
    }
}
