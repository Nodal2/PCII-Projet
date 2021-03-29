package modele;

/** cette classe represente un terrain cote modele. Un terrain 
 * possede des attributs des principales classes du modele
 * contient des constantes qui definissent le cadre du jeu
 */

public class Terrain {
	
	/** constantes */
	public final static int LARGEUR_TERRAIN = 1000;
	public final static int HAUTEUR_TERRAIN = 600;
	public final static int HAUTEUR_HORIZON = 150;
	public final static int HAUTEUR_SOL = 450;
	
	/** attributs */
	private Route route;
	private Voiture voiture;
	private Chronometre chronometre;
	
	/** constructeur */
	public Terrain(Voiture voiture) {
		this.route = new Route(voiture);
		this.voiture = voiture;
		this.chronometre = new Chronometre();

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
}
