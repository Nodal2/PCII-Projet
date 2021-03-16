package controleur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import modele.Voiture;

public class Controleur implements KeyListener{
	
	private Voiture voiture;
	
	public Controleur(Voiture voiture) {
		this.voiture = voiture;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int touche = e.getKeyCode();
		if(touche == KeyEvent.VK_RIGHT) {
			this.voiture.setDroite(true);
		}
		if(touche == KeyEvent.VK_LEFT) {
			this.voiture.setGauche(true);
		}
		if(touche == KeyEvent.VK_UP) {
			this.voiture.setSaute(true);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int touche = e.getKeyCode();
		if(touche == KeyEvent.VK_RIGHT) {
			this.voiture.setDroite(false);
		}
		if(touche == KeyEvent.VK_LEFT) {
			this.voiture.setGauche(false);
		}
	}
	
	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}

}
