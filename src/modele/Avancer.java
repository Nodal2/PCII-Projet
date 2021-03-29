package modele;

import application.Jeu;

/** cette classe est un thread qui fait avancer la route toutes les FREQUENCE_RAFRAICHISSEMENT ms */

public class Avancer extends Thread {
	
	/** attributs */
	private volatile boolean running = true;
	private Route route;
	
	/** constructeur */
	public Avancer(Route route) {
		this.route = route;
	}
	
	@Override
	public void run() {
		while(running) {
			synchronized(this.route.getCourbes()) {
				this.route.avancer();
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
