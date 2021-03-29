package vue;

import application.Jeu;

/** cette classe est un thread qui met a jour l'affichage du jeu toutes les FREQUENCE_RAFRAICHISSEMENT ms */

public class Afficher extends Thread{
	
	/** attributs */
	private volatile boolean running = true;
	private Affichage affichage;
	private HUD hud;
	
	/** constructeur */
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
	
	/** cette methode permet de sortir de la boucle de run() */
	public void arreter() {
		this.running = false;
	}

}
