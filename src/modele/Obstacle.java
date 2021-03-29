package modele;

/** cette classe represente un obstacle sur la route. Un obstacle peut :
 * - initialiser ses attributs avec une largeur et hauteur aleatoire (bornees)
 */

public class Obstacle {
	
	/** constantes */
	private static final int LARGEUR_MIN = 50;
	
	/** attributs */
	private int x;
	private int y;
	private int largeur;
	private int hauteur;

	/** constructeur */
	public Obstacle(int x, int y) {
		this.largeur = (int) ((Math.random() * (Route.LARGEUR-LARGEUR_MIN))+LARGEUR_MIN);
		this.hauteur = this.largeur/2;
		this.x = x;
		this.y = y;
	}
	
	/** getters et setters */
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getLargeur() {
		return largeur;
	}

	public int getHauteur() {
		return hauteur;
	}
}
