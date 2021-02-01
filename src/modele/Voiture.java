package modele;
	
public class Voiture {

	/** constantes */
	public final static int LARGEUR_VOITURE = 150;
	public final static int HAUTEUR_VOITURE = 150;

	/** attributs */
	private int posX;
	private int posY;
	
	public Voiture(int x, int y) {
		this.posX = x;
		this.posY = y;
	}

	/** accesseur */
	public int getPosX() {
		return this.posX;
	}
	public int getPosY() {
		return this.posY;
	}
}

