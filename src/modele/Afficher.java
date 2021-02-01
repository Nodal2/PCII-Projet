package modele;

import vue.Affichage;

public class Afficher extends Thread{
	
	private Affichage affichage;

	public Afficher(Affichage affichage) {
		this.affichage = affichage;
	}
	
	@Override
	public void run() {
		while(true) {
			this.affichage.repaint();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}

}
