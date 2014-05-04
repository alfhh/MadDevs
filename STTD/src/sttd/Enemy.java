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
    char movment = '0';
    double angle = ((double) (Math.random() * (16))); //angulo de la enemigo
    int speed = ((int) (Math.random() * (5-2)))+ 2; // Velocidad del enemigo
    int range = 30; //Rango circular de la enemigo. 
    int damage = 5; //Daño de la enemigo
    int value; //Valor de venta de la enemigo
    int target = 0; // enemigo a apuntar
    int health = 100; //Vida
    int basehealth; //Vida inicial
    int lifetime = 0; //tiempo de vida
    int slowtimer = 0; //timer del tiempo que se alentara
    int infectiontimer = 0; //timer del virus
    

    public Enemy(int posX, int posY, Animacion animacion, int t, int sp, int hp) {
        super(posX, posY, animacion);
        type = t;
        end = new Point(posX, posY);
        start = new Point(posX, posY);
        speed = sp;
        health = hp;
        basehealth = hp;
        
    }
    
    //Metodo para obtener el tipo del enemigo
    int getType() {
        return type;
    }


    //Metodo para obtener el angulo del enemigo
    double getAngle() {
        return angle;
    }

    //Metodo para asignar el angulo del enemigo
    void setAngle(double a) {
        angle = a;
    }

    //Metodo para obtener la velocidad del enemigo
    int getSpeed() {
        if (slowtimer > 0)
        {
         if (slowtimer % 2 == 0)
        {
        return speed;
        }
        else
        {
        return 0;   
        }
        }
        else
        {
        return speed;
        }
    }

    //Metodo para asignar la velocidad del enemigo
    void setSpeed(int s) {
        speed = s;
    }
    
    //Metodo para obtener la vida base del enemigo
    int getBaseHealth() {
        return basehealth;
    }

    //Metodo para asignar la vida base del enemigo
    void setBaseHealth(int h) {
        basehealth = h;
    }
    
    //Metodo para obtener la vida del enemigo
    int getHealth() {
        return health;
    }

    //Metodo para asignar la vida del enemigo
    void setHealth(int h) {
        health = h;
    }
    
    //Saber si lo alentaron
    boolean getSlow() {
        if (slowtimer > 0)
        {
        return true;
        }
        else
        {
            return false;
        }
    }

    //Metodo para asignar el timer de alentarse
    void setSlow() {
        slowtimer = 255;
    }
    
        //Metodo para asignar el timer de alentarse
    void slowTimer() {
        if(slowtimer > 0)
        {
        slowtimer--;
        }
    }
    
    //Saber si tiene virus
    boolean getVirus() {
        if (infectiontimer > 0)
        {
        return true;
        }
        else
        {
            return false;
        }
    }

    
    //Metodo para asignar el timer del virus
    void setVirus() {
        infectiontimer = 250;
    }
    
        //Metodo para asignar el timer del virus
    void virusTimer() {
        if(infectiontimer > 0)
        {
        infectiontimer--;
        if(infectiontimer % 50 == 0)
        {
            if((int)health*0.05> 1)
            {
             health -= health*0.05;   
            }
            else
            {
             health--;
            }
           
        }
        }
    }
    

    //Metodo para asignar el movimiento
    void setMov(char m) {
        movment = m;
    }
    
    

    //Metodo para obtener el movimiento
    char getMov() {
        return movment;
    }

    //Metodo para asignar el bloque anterior
    void setStart(Point p) {
        start.setLocation(p.getLocation());
    }

    //Metodo para obtener el bloque anterior
    Point getStart() {
        return start;
    }

    //Metodo para asignar el bloque siguiente
    void setEnd(Point p) {
        end.setLocation(p.getLocation());
    }

    //Metodo para obtener el bloque siguiente
    Point getEnd() {
        return end;
    }
    
    //Metodo que actualize su tiempo de vida
    void addLifeTime() {
         lifetime += speed;
    }
    
    //Metodo que regresa su tiempo de vida
    int getLifeTime() {
         return lifetime;
    }
}
