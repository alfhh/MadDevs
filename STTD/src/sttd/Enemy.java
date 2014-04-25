/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sttd;

import java.awt.Point;

/**
 *
 * @author LuisJuan
 */
public class Enemy extends Base {
    int type = 0; //tipo de enemigo.
    Point start;
    Point end;
    char movment;
    double angle = ((double) (Math.random() * (16))); //angulo de la enemigo
    int speed = 3; // Velocidad de las balas. -1 convoca lazer instantaneo
    int range = 30; //Rango circular de la enemigo. 
    int damage = 5; //Daño de la enemigo
    int playerid = 1; //Dueño de la enemigo
    int rateoffire = 10; //Delay entre disparos
    int towerid; //Id de la enemigo (Utilizado para identificar la enemigo independientemente de su lugar en la linked list)
    int value; //Valor de venta de la enemigo
    int target = 0; // enemigo a apuntar
    
    
    
    public Enemy (int posX, int posY, Animacion animacion, int t) {
        super(posX, posY, animacion);
        type = t;
        Point p = new Point(posX,posY);
        end=p;
        start = end;    
    }
    //Metodo para obtener el angulo de la enemigo
    double getAngle()
    {
        return angle;
    }
    
    //Metodo para asignar el angulo de la enemigo
    void setAngle(double a)
    {
        angle = a;
    }
    
    //Metodo para obtener el angulo de la enemigo
    int getSpeed()
    {
        return speed;
    }
    
    //Metodo para asignar el angulo de la enemigo
    void setSpeed(int s)
    {
        speed = s;
    }
}
