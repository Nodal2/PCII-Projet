package vue;

import modele.Terrain;
import modele.Voiture;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Affichage extends JPanel {
	private static final long serialVersionUID = 6883630088377101348L;

	/** Attribut */
	private Voiture voiture;
	private Terrain terrain;
	
	/** constructeur */
	public Affichage(Voiture voiture, Terrain terrain) {
		this.voiture = voiture;
		this.terrain = terrain;
		setPreferredSize(new Dimension(Terrain.LARGEUR_TERRAIN, Terrain.HAUTEUR_TERRAIN));
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g); //permet de nettoyer l'image
		g.drawOval(this.voiture.getPosX(), this.voiture.getPosY(), Voiture.LARGEUR_VOITURE , Voiture.HAUTEUR_VOITURE); //Permet de dessiner un ovale

	}

}
