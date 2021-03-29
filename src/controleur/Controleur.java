package controleur;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

import modele.Terrain;
import modele.Voiture;

/** cette classe permet de mettre a jour les donnees du modele
 * en fonction des actions du joueur 
 */

public class Controleur implements KeyListener{
	
	/** attributs */
	private Voiture voiture;
	
	/** constructeur */
	public Controleur(Voiture voiture) {
		this.voiture = voiture;
	}
	
	/** methode qui met a jour le modele lorsque le joueur enfonce une touche */
	@Override
	public void keyPressed(KeyEvent e) {
		int touche = e.getKeyCode();
		if(touche == KeyEvent.VK_RIGHT) {
			this.voiture.setDroite(true);
		}
		if(touche == KeyEvent.VK_LEFT) {
			this.voiture.setGauche(true);
		}
		if(touche == KeyEvent.VK_UP  && this.voiture.getPosY() == Terrain.HAUTEUR_SOL) {
			this.voiture.setSaute(true);
		}
	}
	
	/** methode qui met a jour le modele lorsque le joueur relache une touche */
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
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	/** getters et setters */
	
	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}

}
