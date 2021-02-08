package modele;

public class Route {
	
	public final static int LIMITE_GAUCHE = 200;
	private Bord bordGauche;

	public Route() {
		this.bordGauche = new Bord(LIMITE_GAUCHE, Terrain.LARGEUR_TERRAIN/2);
	}
	
	public Bord getBordGauche() {
		return this.bordGauche;
	}
	

}
