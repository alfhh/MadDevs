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
public class Mine extends Base {

    boolean set; // Boleano que representa se la torreta ya esta puesta
    int damage = 25; //Daño de la torre
    int playerid; //Dueño de la torre
    int explosion = 0;
    int type;

    public Mine(int posX, int posY, Animacion animacion, int pi, int t) {
        super(posX, posY, animacion);
        set = false;
        playerid = pi;
        type = t;
    }

    /**
     * Metodo que modifica si esta puesta
     *
     * @param s que es de tipo <code>boolean</code>
     */
    void setSet(boolean s) {
        set = s;
    }

    /**
     * Metodo que accesa si esta puesta
     *
     * @return set que es de tipo <code>boolean</code>
     */
    boolean getSet() {
        return set;
    }

    /**
     * Metodo que accesa el daño
     *
     * @return damage que es de tipo <code>int</code>
     */
    int getDam() {
        return damage;
    }

    /**
     * Metodo que regresa que tipo es
     *
     * @return type que es de tipo <code>int</code>
     */
    int getType() {
        return type;
    }

    /**
     * Metodo que accesa la explosion
     *
     * @return explosion que es de tipo <code>int</code>
     */
    int getExp() {
        return explosion;
    }

    /**
     * Metodo que modifica la explosion
     *
     * @param e que es de tipo <code>int</code>
     */
    void setExp(int e) {
        explosion = e;
    }

    /**
     * Metodo que actualiza la explosion
     */
    void Exp() {
        explosion -= 2;
    }

    /**
     * Metodo que regresa el jugador dueño de la mina
     *
     * @return playerid que es de tipo <code>int</code>
     */
    int getPlayer() {
        return playerid;
    }
}
