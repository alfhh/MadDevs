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

    
    public Bullet (int posX, int posY, Animacion animacion, int dmg, int sp, double ang, int range, int pl, int t) {
        super(posX, posY, animacion);
        damage = dmg;
        speed = sp;
        angle = ang;
        distance = range ;
        playerid = pl;
        towernum = t;
    }
    //Metodo para obtener el angulo de la torre
    double getAngle()
    {
        return angle;
    }
    
    //Metodo para asignar el angulo de la torre
    void setAngle(int a)
    {
        angle = a;
    }
    
    //Metodo para obtener el angulo de la torre
    int getSpeed()
    {
        return speed;
    }
    
    //Metodo para asignar el angulo de la torre
    void setSpeed(int s)
    {
        speed = s;
    }
    
    //Metodo para obtener el daño de la torre
    int getDamage() {
        return damage;
    }

    //Metodo para asignar el daño de la torre
    void setDamage(int d) {
        damage = d;
    }
    
    //Metodo para obtener el angulo de la torre
    int getDistance()
    {
        return speed;
    }
    
    //Metodo para asignar el angulo de la torre
    void destroy()
    {
        distance = 0;
    }
    
    //Metodo que mide el tiempo de vida de la bala y si es true en el main, borra el objeto
    boolean distanceTime()
    {
        distance -= speed;
        if (distance <= 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    //Metodo que regresa la torreta que la disparo
    int getTower(){
        return towernum;
    }
    
    //Metodo para confirmar que una bala es de watulio
    void setWatower()
    {
        watower = true;
    }
    
    //Metodo para saber si una bala es de watulio
    boolean getWatower()
    {
        return watower;
    }
    
    
}
