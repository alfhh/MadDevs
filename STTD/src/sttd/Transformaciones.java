/**
 *	JFrame Transformaciones
 *	Implementa las transformaciones de traslación, rotación y escala para generar
 *	un diseño creativo con polígonos
 *
 *	Tutorial Transformaciones - Jugando con JAVA
 *
 *	@autor Jugando con JAVA
 *	@version 1.0 30/08/2010
 */
 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.util.Random;
import javax.swing.JFrame;
 
public class Transformaciones extends JFrame {
 	
 	//Declaración de variables
 	
	//Coordenandas en x de un polígono
	private int puntosX[] = {55, 67, 109, 73, 83, 55, 27, 37, 1, 43};
 	//Coordenadas en y de un polígono
 	private int puntosY[] = {0, 36, 36, 54, 96, 72, 96, 54, 36, 36};
 	//Polígono de la clase Polygon
 	private Polygon poligono;
 	//Generador de números aleatorios
 	private Random rnd;
 	//Objeto gráfico de la clase Graphics2D
 	private Graphics2D g2d;
 	//Matriz identidad de la clase AffineTransform
 	private AffineTransform identidad;
 	//Ancho y Alto del JFrame
 	private int ancho, alto;
 	 	
 	/**
	 * Método constructor de la clase Transformaciones.
	 * Define la apariencia y funcionalidad del JFrame.
	 * Crea e inicializa las variables.
	 */
 	public Transformaciones() {
 		
 		//Polígono de 10 lados
 		poligono = new Polygon(puntosX, puntosY, 10);
 		//Nueva instancia de la clase Random
 		rnd = new Random();
 		//Nueva instancia de la clase AffineTransform
 		identidad = new AffineTransform();
 		//Título del JFrame
		setTitle("Transformaciones (Graphics2D)");
 		//Operación de salida del JFrame
 		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		//Tamaño del JFrame
 		setSize(400, 300);
 		//Fija el tamaño del JFrame, no redimensionable
 		setResizable(false);
 		//Hace visible el JFrame en la pantalla
 		setVisible(true);
 	}
 	
 	/**
	 * Metodo paint sobrescrito de la clase Applet,
	 * heredado de la clase Container.
	 * En este metodo se dibujan 400 polígonos
	 * de manera aleatoria.
	 * g es el objeto grafico usado para dibujar.
	 */
 	public void paint(Graphics g) {
 		
 		//Se crea el objeto gráfico Graphics2D
 		g2d = (Graphics2D) g;
 		//Guarda el ancho del JFrame
 		ancho = getWidth();
 		//Guarda el alto del JFrame
 		alto = getHeight();
 		
 		//Color Negro 		
 		g2d.setColor(Color.BLACK);
 		//Rellena el fondo de color Negro
 		g2d.fillRect(0, 0, ancho, alto);
 		
 		//Dibuja 400 polígonos
 		for (int i = 0; i < 400; i++) {
 		
 			//Se carga la matriz identidad
 			g2d.setTransform(identidad);
 			//Traslada el polígono de manera aleatoria
 			g2d.translate(rnd.nextInt() % ancho, rnd.nextInt() % alto);
 			//Rota el polígono de manera aleatoria
 			g2d.rotate(Math.toRadians(360 * rnd.nextDouble()));
 			//Escala el polígono de manera aleatoria
 			g2d.scale(2 * rnd.nextDouble(), 2 * rnd.nextDouble());
 			//Establece el color del polígono de manera aleatoria
 			g2d.setColor(new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
 			//Pinta el polígono
 			g2d.fillPolygon(poligono);
 		}
 	}
 	
 	public static void main(String args []) {
 		
 		//Crea una nueva instancia de la clase Transformaciones
 		Transformaciones transforma = new Transformaciones();
	}
}