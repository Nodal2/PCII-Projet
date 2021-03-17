package application;

import vue.MenuRejouer;

public class Perdre extends Thread {
	public boolean perdu = false;
	private Jeu manager;
	
	public Perdre(Jeu manager) {
		this.manager = manager;
	}
	
	@Override
	public void run() {
		while(!perdu) {
			if(this.manager.getPartieCourante().getVoiture().getVitesse() == 0 || this.manager.getPartieCourante().getTerrain().getRoute().getPointControle().getCompteARebour().getTempsCourant() <= 0) {
				this.manager.stopperPartieCourante();
				new MenuRejouer(this.manager.getPartieCourante().getTerrain(), this.manager.getFenetre());
				this.perdu = true;
			}
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.manager.nouvellePartie();
	}

}
