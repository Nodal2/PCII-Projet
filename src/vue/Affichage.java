package vue;

import modele.Terrain;
import modele.Voiture;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Affichage extends JPanel {
	private static final long serialVersionUID = 6883630088377101348L;

	/** Attribut */
	private Voiture voiture;
	private TerrainVue terrainVue;
	
	/** constructeur */
	public Affichage(Voiture voiture, TerrainVue terrainVue) {
		this.voiture = voiture;
		this.terrainVue = terrainVue;
		setPreferredSize(new Dimension(Terrain.LARGEUR_TERRAIN, Terrain.HAUTEUR_TERRAIN));
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g); //permet de nettoyer l'image
		Graphics2D g2 = (Graphics2D)g;
		this.terrainVue.afficherRoute(g2);
		g.drawOval(this.voiture.getPosX(), this.voiture.getPosY(), Voiture.LARGEUR_VOITURE , Voiture.HAUTEUR_VOITURE); //Permet de dessiner un ovale

	}

}
