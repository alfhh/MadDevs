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
public class Laser{
    int PosX; //Posición X del origen
    int PosY; //Posición Y del origen
    int endX; //Posición X del enemigo
    int endY; //Posición Y del enemigo
    int damage = 5; //Daño de la bala
    int playerid = 1; //Dueño de la bala
    int towernum; // numero de indice de la torreta
    int life = 36; //tiempo de vida

    
    public Laser (int posX, int posY,int othX, int othY, int dmg, int pl, int t) {
        PosX = posX;
        PosY = posY;
        endX = othX;
        endY = othY;
        damage = dmg;
        playerid = pl;
        towernum = t;
    }
    
    
    //Metodo para obtener la posicion x
    int getPosX() {
        return PosX;
    }
    
    //Metodo para obtener la posicion y
    int getPosY() {
        return PosY;
    }
    
    //Metodo para obtener la otra posicion x
    int getEndX() {
        return endX;
    }
    
    //Metodo para obtener la otra posicion y
    int getEndY() {
        return endY;
    }

    
    //Metodo para obtener el daño de la torre
    int getDamage() {
        return damage;
    }

    //Metodo para asignar el daño de la torre
    void setDamage(int d) {
        damage = d;
    }
    
    //Metodo que mide el tiempo de vida del laser y si es false en el main, borra el objeto
    boolean deathTime()
    {
        if (life == 0)
        {
            return false;
        }
        else
        {
            life--;
            return true;
        }
    }
    
    //Metodo que regresa el tiempodevida
    int getTime(){
        return life;
    }
    
    //Metodo que regresa la torreta que la disparo
    int getTower(){
        return towernum;
    }
}
