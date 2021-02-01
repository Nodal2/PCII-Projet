package controleur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import vue.Affichage;

public class Controleur implements KeyListener{
	
	private Affichage affichage;
	
	public Controleur(Affichage affichage) {
		this.affichage = affichage;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int touche = e.getKeyCode();
		if(touche == KeyEvent.VK_RIGHT) {
			//dire a la moto d'aller a droite
		}
		if(touche == KeyEvent.VK_LEFT) {
			//dire a la moto d'aller a gauche
		}
		this.affichage.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int touche = e.getKeyCode();
		if(touche == KeyEvent.VK_RIGHT) {
			//dire a la moto d'arreter d'aller a droite
		}
		if(touche == KeyEvent.VK_LEFT) {
			//dire a la moto d'arreter d'aller a gauche
		}
		this.affichage.repaint();
	}

}
