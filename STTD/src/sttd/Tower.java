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
    boolean set; // Boleano que representa se la torreta ya esta puesta
    double angle = ((double) (Math.random() * (360))); //angulo de la torre
    int speed = 3; // Velocidad de las balas. -1 convoca lazer instantaneo
    double range = 90; //Rango circular de la torre. 
    int damage = 5; //Daño de la torre
    int playerid = 1; //Dueño de la torre
    int rateoffire = 10; //Delay entre disparos
    int towerid; //Id de la torre (Utilizado para identificar la torre independientemente de su lugar en la linked list)
    int value; //Valor de venta de la torre
    int target = 0; // enemigo a apuntar
    int canshoot = 0; //Timer para poder disparar
    
    
    
    public Tower (int posX, int posY, Animacion animacion, int t, int pl) {
        super(posX, posY, animacion);
        type = t;
        playerid = pl;
        
    }
    //Metodo para obtener el angulo de la torre
    double getAngle()
    {
        return angle;
    }
    
    //Metodo para asignar el angulo de la torre
    void setAngle(double a)
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
    
    //Metodo para obtener el rango de la torre
    double getRange()
    {
        return range;
    }
    
    //Metodo para asignar el rango de la torre
    void setRange(int r)
    {
        range = r;
    }
    
    boolean canShoot()
    {
        if (canshoot == 0)
        {
            return true;
        }
        else
        {
            canshoot += -1;
            return false;
        }
        
    }
}
