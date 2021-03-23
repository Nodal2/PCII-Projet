package modele;

import application.Jeu;

public class Avancer extends Thread {
	private volatile boolean running = true;
	private Route route;
	
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
	
	public void arreter() {
		this.running = false;
	}

}
