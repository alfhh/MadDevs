/**
 * @author Alfredo Hinojosa Huerta A01036053
 * @author Luis Juan Sanchez A01183634
 * @author Rodolfo Cantú Ortíz A01036042
 * @version 1.00 03/11/2014
 */
package sttd;

import java.awt.Image;
import java.util.ArrayList;

import java.awt.Image;
import java.util.ArrayList;

/**
	La clase Animacion maneja una serie de imágenes (cuadros)
	y la cantidad de tiempo que se muestra cada cuadro.
*/

public class Animacion{
	
	private ArrayList<cuadroDeAnimacion> cuadros;
	private int indiceCuadroActual;
        private boolean cutscene;
       
        private boolean activation = false;
	private long tiempoDeAnimacion;
	private long duracionTotal;
	
	/**
		Crea una nueva Animacion vacía
	*/
	public Animacion(){
		cuadros = new ArrayList<cuadroDeAnimacion>();
		duracionTotal = 0;
		iniciar();
	}
	
	/**
		Añade una cuadro a la animación con la duración
		indicada (tiempo que se muestra la imagen).
	*/	
	public synchronized void sumaCuadro(Image imagen, long duracion){
		duracionTotal += duracion;
		cuadros.add(new cuadroDeAnimacion(imagen, duracionTotal));
	}
	
	// Inicializa la animación desde el principio. 
	public synchronized void iniciar(){
		tiempoDeAnimacion = 0;
		indiceCuadroActual = 0;
	}
	
	/**
		Actualiza la imagen (cuadro) actual de la animación,
		si es necesario.
	*/
	public synchronized void actualiza(long tiempoTranscurrido){
		if (cuadros.size() > 1){
			tiempoDeAnimacion += tiempoTranscurrido;
			
			if (tiempoDeAnimacion >= duracionTotal){
                            if(cutscene)
                                {
                             activation = true; 
                            }
                               tiempoDeAnimacion = tiempoDeAnimacion % duracionTotal;
				indiceCuadroActual = 0;
                                
                                
				
			}
			
			while (tiempoDeAnimacion > getCuadro(indiceCuadroActual).tiempoFinal){
				indiceCuadroActual++;
			}
		}
	}
        
        public void cutsceneMode()
                {
                    this.cutscene = true;
                }
        
        public void stopCutsceneMode()
                {
                    this.cutscene = false;
                    this.activation = false;
                    
                }
        
        
        public boolean getActivation()
                {
                    return this.activation;
                }
	
         
        
	/**
		Captura la imagen actual de la animación. Regeresa null
		si la animación no tiene imágenes.
	*/
	public synchronized Image getImagen(){
		if (cuadros.size() == 0){
			return null;
		}
		else {
			return getCuadro(indiceCuadroActual).imagen;
		}
	}
	
	private cuadroDeAnimacion getCuadro(int i){
		return (cuadroDeAnimacion)cuadros.get(i);
	}
	
	public class cuadroDeAnimacion{
		
		Image imagen;
		long tiempoFinal;
		
		public cuadroDeAnimacion(){
			this.imagen = null;
			this.tiempoFinal = 0;
		}
		
		public cuadroDeAnimacion(Image imagen, long tiempoFinal){
			this.imagen = imagen;
			this.tiempoFinal = tiempoFinal;
		}
		
		public Image getImagen(){
			return imagen;
		}
		
		public long getTiempoFinal(){
			return tiempoFinal;
		}
		
		public void setImagen (Image imagen){
			this.imagen = imagen;
		}
		
		public void setTiempoFinal(long tiempoFinal){
			this.tiempoFinal = tiempoFinal;
		}
                
                
	}
		
}