package modele;

public class Modele {
	
	public final static int LARGEUR_VOITURE = 150;
	public final static int HAUTEUR_VOITURE = 150;
	
	private int posX;
	private int posY;
	
	public Modele(int x, int y) {
		this.posX = x;
		this.posY = y;
	}
	
	public int getPosX() {
		return this.posX;
	}
	public int getPosY() {
		return this.posY;
	}
}