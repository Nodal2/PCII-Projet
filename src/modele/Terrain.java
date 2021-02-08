package modele;

public class Terrain {
	public final static int LARGEUR_TERRAIN = 1000;
	public final static int HAUTEUR_TERRAIN = 600;
	public final static int HAUTEUR_HORIZON = 200;
	
	private Route route;
	private Horizon horizon;
	
	public Terrain(Route route) {
		this.route = route;
		this.horizon = new Horizon(HAUTEUR_HORIZON);
	}
	
	public Horizon getHorizon() {
		return this.horizon;
	}
	
	public Route getRoute() {
		return this.route;
	}

}
