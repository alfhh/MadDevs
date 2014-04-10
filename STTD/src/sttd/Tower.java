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
public class Tower extends Base {
    int type;
    public Tower (int posX, int posY, Animacion animacion, int t) {
        super(posX, posY, animacion);
        type = t;
    }
}
