package modele;

public class Terrain {
	
	/** constantes */
	public final static int LARGEUR_TERRAIN = 1000;
	public final static int HAUTEUR_TERRAIN = 600;
	public final static int HAUTEUR_HORIZON = 150;
	
	/** attributs */
	private Route route;
	private Voiture voiture;
	private Chronometre chronometre;
	//private CompteARebour compteARebour;
	
	/** constructeur */
	public Terrain(Route route, Voiture voiture) {
		this.route = route;
		this.voiture = voiture;
		this.chronometre = new Chronometre();
		//this.compteARebour = new CompteARebour();
	}
	
	/** getters et setters */
	
	public Route getRoute() {
		return this.route;
	}
	
	public Voiture getVoiture() {
		return this.voiture;
	}
	
	public Chronometre getChronometre() {
		return this.chronometre;
	}

	//public CompteARebour getCompteARebour(){ return this.compteARebour;}

}
