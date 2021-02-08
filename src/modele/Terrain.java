package modele;

public class Terrain {
	public final static int LARGEUR_TERRAIN = 1000;
	public final static int HAUTEUR_TERRAIN = 600;
	
	private Route route;
	
	public Terrain(Route route) {
		this.route = route;
	}
	
	public Route getRoute() {
		return this.route;
	}

}
