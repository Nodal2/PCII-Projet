package application;

import vue.MenuRejouer;

/** cette classe est un thread qui permet d'arreter un jeu si le joueur a perdu et de relancer une partie */

public class Perdre extends Thread {
	
	/** attributs */
	public boolean perdu = false;
	private Jeu manager;
	
	/** constructeur */
	public Perdre(Jeu manager) {
		this.manager = manager;
	}
	
	@Override
	public void run() {
		while(!perdu) {
			if(this.manager.getPartieCourante().getVoiture().getVitesse() == 0 || this.manager.getPartieCourante().getTerrain().getRoute().getPointControle().getCompteARebour().getTempsCourant() <= 0) {
				this.manager.stopperPartieCourante(); //arrete les threads
				new MenuRejouer(this.manager.getPartieCourante().getTerrain(), this.manager.getFenetre()); //ouvre la fenetre de fin de jeu
				this.perdu = true; //permet de sortir de la boucle
			}
			try {
				Thread.sleep(Jeu.FREQUENCE_RAFRAICHISSEMENT);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.manager.nouvellePartie(); //lance une nouvelle partie
	}

}
