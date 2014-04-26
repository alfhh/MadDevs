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

    public Enemy(int posX, int posY, Animacion animacion, int t, int sp, int hp) {
        super(posX, posY, animacion);
        type = t;
        end = new Point(posX, posY);
        start = new Point(posX, posY);
        speed = sp;
        health = hp;
        
    }

    //Metodo para obtener el angulo de la enemigo
    double getAngle() {
        return angle;
    }

    //Metodo para asignar el angulo de la enemigo
    void setAngle(double a) {
        angle = a;
    }

    //Metodo para obtener el angulo de la enemigo
    int getSpeed() {
        return speed;
    }

    //Metodo para asignar el angulo de la enemigo
    void setSpeed(int s) {
        speed = s;
    }
    
    //Metodo para obtener el angulo de la enemigo
    int getHealth() {
        return health;
    }

    //Metodo para asignar el angulo de la enemigo
    void setHealth(int h) {
        health = h;
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
}
