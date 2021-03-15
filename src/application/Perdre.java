package application;

import vue.MenuRejouer;

public class Perdre extends Thread {
	public boolean perdu = false;
	private Partie partie;
	private PartieManager manager;
	
	public Perdre(Partie partie, PartieManager manager) {
		this.partie = partie;
		this.manager = manager;
	}
	
	@Override
	public void run() {
		while(!perdu) {
			if(this.partie.getVoiture().getVitesse() == 0 || this.partie.getTerrain().getRoute().getPointControle().getCompteARebour().getTempsCourant() <= 0) {
				this.stopperLesThreads();
				new MenuRejouer(this.partie.getTerrain(), this.manager.getFenetre());
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
	
	private void stopperLesThreads() {
		this.partie.getAvancer().arreter();
		this.partie.getConduire().arreter();
		this.manager.getAfficher().arreter();
		this.partie.getTerrain().getRoute().getPointControle().getCompteARebour().getTimer().cancel();
		this.partie.getTerrain().getChronometre().getTimer().cancel();
		
	
	}

}
