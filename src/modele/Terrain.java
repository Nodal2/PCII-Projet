package modele;

public class Terrain {
	public final static int LARGEUR_TERRAIN = 1000;
	public final static int HAUTEUR_TERRAIN = 600;
	public final static int HAUTEUR_HORIZON = 150;
	
	private Route route;
	private Voiture voiture;
	private Chronometre chronometre;
	
	
	public Terrain(Route route, Voiture voiture) {
		this.route = route;
		this.voiture = voiture;
		this.chronometre = new Chronometre();
		
	}
	
	public Route getRoute() {
		return this.route;
	}
	
	public Voiture getVoiture() {
		return this.voiture;
	}
	
	public Chronometre getChronometre() {
		return this.chronometre;
	}

}
