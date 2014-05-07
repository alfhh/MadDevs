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
public class Laser {

    int PosX; //Posición X del origen
    int PosY; //Posición Y del origen
    int endX; //Posición X del enemigo
    int endY; //Posición Y del enemigo
    int damage = 5; //Daño de la bala
    int playerid = 1; //Dueño de la bala
    int towernum; // numero de indice de la torreta
    int life = 36; //tiempo de vida

    public Laser(int posX, int posY, int othX, int othY, int dmg, int pl, int t) {
        PosX = posX;
        PosY = posY;
        endX = othX;
        endY = othY;
        damage = dmg;
        playerid = pl;
        towernum = t;
    }

    /**
     * Metodo de acceso que regresa la posicion en x del objeto
     *
     * @return posX es la <code>posicion en x</code> del objeto.
     */
    int getPosX() {
        return PosX;
    }

    /**
     * Metodo de acceso que regresa la posicion en y del objeto
     *
     * @return posY es la <code>posicion en y</code> del objeto.
     */
    int getPosY() {
        return PosY;
    }

    /**
     * Metodo de acceso que regresa la posicion en x del final
     *
     * @return endX es la <code>posicion en x</code> del final.
     */
    int getEndX() {
        return endX;
    }

    /**
     * Metodo de acceso que regresa la posicion en y del fianl
     *
     * @return endY es la <code>posicion en y</code> del final.
     */
    int getEndY() {
        return endY;
    }

    /**
     * Metodo que accesa el daño de la bala (
     *
     * @return damage de tipo <code>int</code> que es el daño de la bala
     */
    int getDamage() {
        return damage;
    }

    /**
     * Metodo que modifica el daño de la bala
     *
     * @param d de tipo <code>int</code> que es el daño de la bala
     */
    void setDamage(int d) {
        damage = d;
    }

    /**
     * Metodo que regresa el tiempo de vida que le queda al laser
     *
     * @return true si sigue vivo
     */
    boolean deathTime() {
        if (life == 0) {
            return false;
        } else {
            life--;
            return true;
        }
    }

    /**
     * Metodo que regresa el tiempo de vida
     * 
     * @return life que es de tipo <code>int</code> que es la vida de la
     * bala
     */
    int getTime() {
        return life;
    }

    /**
     * Metodo que regresa el numero de indice de su torre
     *
     * @return towernum que es de tipo <code>int</code> que es la torre de la
     * bala
     */
    int getTower() {
        return towernum;
    }
}
