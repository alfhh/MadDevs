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

    public Mine(int posX, int posY, Animacion animacion, int pi) {
        super(posX, posY, animacion);
        set = false;
        playerid = pi;

    }

}