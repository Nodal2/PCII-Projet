package modele;

import application.Jeu;

/** cette classe est un thread qui fait bouger la voiture lateralement toutes les FREQUENCE_RAFRAICHISSEMENT ms */

public class Conduire extends Thread{
	
	/** attributs */
	private volatile boolean running = true;
	private Voiture voiture;
	
	/** constructeur */
	public Conduire(Voiture voiture) {
		this.voiture = voiture;
	}
	
	@Override
	public void run() {
		while(running) {
			this.voiture.controler();
			synchronized(this.voiture.getTerrain().getRoute().getCourbes()) {
				this.voiture.controleVitesse();
			}
			try {
				Thread.sleep(Jeu.FREQUENCE_RAFRAICHISSEMENT);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/** cette methode permet de sortir de la boucle de run() */
	public void arreter() {
		this.running = false;
	}

}
