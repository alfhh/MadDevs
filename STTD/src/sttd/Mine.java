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

    //Metodo para modificar si esta puesta
    void setSet(boolean s) {
        set = s;
    }

    //Metodo para ver si esta puesta
    boolean getSet() {
        return set;
    }

    //Metodo para ver el daño
    int getDam() {
        return damage;
    }
    
    //Metodo para ver el tipo
    int getType() {
        return type;
    }

    //Metodo de acceso a explosion
    int getExp() {
        return explosion;
    }

    //Metodo de modificacion de explosion
    void setExp(int e){
        explosion = e;
    }
    
    //Metodo de actualizar la explosion
    void Exp() {
        explosion -= 2;
    }
}
