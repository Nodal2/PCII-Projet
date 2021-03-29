package vue;

import application.Jeu;

/** cette classe est un thread qui fait avancer les decors sur les cotes de la route toutes les FREQUENCE_RAFRAICHISSEMENT ms */


public class AvancerVue extends Thread {
	private volatile boolean running = true;
	private DecorationsVue decorations;
	
	public AvancerVue(DecorationsVue decorations) {
		this.decorations = decorations;
	}
	
	@Override
	public void run() {
		while(running) {
			synchronized(this.decorations.getDecorations()) {
				this.decorations.avancer();
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
