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
    int type = 0; //tipo de torre.
    int angle = ((int) (Math.random() * (360))); //angulo de la torre
    int speed = 3; // Velocidad de las balas. -1 convoca lazer instantaneo
    int range = 30; //Rango circular de la torre
    int damage = 5; //Daño de la torre
    
    
    
    
    public Tower (int posX, int posY, Animacion animacion, int t) {
        super(posX, posY, animacion);
        type = t;
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
}
