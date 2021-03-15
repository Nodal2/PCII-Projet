package application;

import modele.Avancer;
import modele.Conduire;
import modele.Terrain;
import modele.Voiture;

public class Partie {
	private Voiture voiture;
	private Terrain terrain;
	private Avancer avancer;
	private Conduire conduire;
	

	public Partie() {
		this.initPartie();
	}

	private void initPartie() {
		//initialisation des classes du modele
		this.voiture = new Voiture(50, 500, 2);
		this.terrain = new Terrain(voiture);
		voiture.setTerrain(terrain);

		//lancement des threads
		this.conduire =  new Conduire(voiture);
		this.avancer = new Avancer(this.terrain.getRoute());
		
		conduire.start();
		avancer.start();
	}


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
