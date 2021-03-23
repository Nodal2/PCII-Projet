package vue;

import application.Jeu;

public class Afficher extends Thread{
	private volatile boolean running = true;
	private Affichage affichage;
	private HUD hud;

	public Afficher(Affichage affichage, HUD hud) {
		this.affichage = affichage;
		this.hud = hud;
	}
	
	@Override
	public void run() {
		while(running) {
			this.affichage.repaint();
			this.hud.repaint();
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
