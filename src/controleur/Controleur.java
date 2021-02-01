package controleur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import modele.Voiture;
import vue.Affichage;

public class Controleur implements KeyListener{
	
	private Affichage affichage;
	private Voiture voiture;
	
	public Controleur(Affichage affichage, Voiture voiture) {
		this.affichage = affichage;
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
		this.affichage.repaint();
	}

}
