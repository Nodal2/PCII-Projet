package modele;

/** cette classe represente une partie cote modele. Une partie peut :
 * - initialiser tous ses attributs qui sont tous les objets du modele de la partie
 * - lancer les threads du modele
 */


public class Partie {
	
	/** attributs */
	private Voiture voiture;
	private Terrain terrain;
	private Avancer avancer;
	private Conduire conduire;
	
	/** constructeur */
	public Partie() {
		this.initPartie();
	}
	
	/** cette methode permet de initialiser les classes du modele et de lancer les threads du modele */
	private void initPartie() {
		//initialisation des classes du modele
		this.voiture = new Voiture(50, 450, 2);
		this.terrain = new Terrain(voiture);
		voiture.setTerrain(terrain);

		//lancement des threads
		this.conduire =  new Conduire(voiture);
		this.avancer = new Avancer(this.terrain.getRoute());
		conduire.start();
		avancer.start();
	}

	/** getters et setters */
	
	public Avancer getAvancer() {
		return avancer;
	}

	public Conduire getConduire() {
		return conduire;
	}

	public Voiture getVoiture() {
		return voiture;
	}

	public Terrain getTerrain() {
		return terrain;
	}




}
