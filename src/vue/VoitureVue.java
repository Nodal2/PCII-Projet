package vue;

import java.awt.Color;
import java.awt.Graphics;

import modele.Voiture;

public class VoitureVue {
	
	private Voiture voiture;
	
	public VoitureVue(Voiture voiture) {
		this.voiture = voiture;
	}
	
	public void afficherVoiture(Graphics g) {
		g.setColor(Color.black);
		g.drawOval(this.voiture.getPosX(), this.voiture.getPosY(), Voiture.LARGEUR_VOITURE , Voiture.HAUTEUR_VOITURE); //Permet de dessiner un ovale
	}

}
