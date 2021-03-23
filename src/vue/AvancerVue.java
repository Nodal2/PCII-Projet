package vue;

import application.Jeu;

public class AvancerVue extends Thread {
	private volatile boolean running = true;
	private DecorationVue decorations;
	
	public AvancerVue(DecorationVue decorations) {
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
