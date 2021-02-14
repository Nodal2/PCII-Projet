package modele;

public class Terrain {
	public final static int LARGEUR_TERRAIN = 1500;
	public final static int HAUTEUR_TERRAIN = 600;
	public final static int HAUTEUR_HORIZON = 150;
	
	private Route route;
	private Voiture voiture;
	private Horizon horizon;
	
	public Terrain(Route route, Voiture voiture) {
		this.route = route;
		this.horizon = new Horizon(HAUTEUR_HORIZON);
		this.voiture = voiture;
	}
	
	public Horizon getHorizon() {
		return this.horizon;
	}
	
	public Route getRoute() {
		return this.route;
	}
	
	public Voiture getVoiture() {
		return this.voiture;
	}

}
