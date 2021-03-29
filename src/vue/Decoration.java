package vue;

import java.awt.Point;

/** cette classe represente une decoration au bord de la route. Une decoration :
 * - possede des dimensions et une position representee par un point
 * - est lie a une image representee par un int
 */

public class Decoration {
	
	/** constantes */
	public static final int HAUTEUR = 300; 
	public static final int LARGEUR = 300;
	
	/** attributs */
	private Point point;
	private int image;
	
	/** constructeur */
	public Decoration(int x, int y, int image) {
		this.point = new Point(x,y);
		this.image = image;
	}
	
	/** cette methode permet de modifier la position verticale de la decoration pour la faire avancer */
	public void avancer(int avancement) {
		this.point.y += avancement;
	}
	
	/** getters et setters */
	
	public Point getPoint() {
		return this.point;
	}
	
	public int getImage() {
		return this.image;
	}

}
