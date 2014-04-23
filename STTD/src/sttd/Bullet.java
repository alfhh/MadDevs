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
    int angle; //angulo de la bala
    int speed = 3; // Velocidad de las bala. -1 convoca lazer instantaneo
    int distance = 45; // Distancia recorrida antes de desaparecer
    int playerid = 1; //Dueño de la bala

    
    public Bullet (int posX, int posY, Animacion animacion, int dmg, int sp, int ang, int range, int pl) {
        super(posX, posY, animacion);
        damage = dmg;
        speed = sp;
        angle = ang;
        distance = range * 2;
        playerid = pl;
    }
    //Metodo para obtener el angulo de la torre
    int getAngle()
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
}
