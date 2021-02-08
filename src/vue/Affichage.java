package vue;

import modele.Terrain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Affichage extends JPanel {
	private static final long serialVersionUID = 6883630088377101348L;

	/** Attribut */
	private VoitureVue voitureVue;
	private TerrainVue terrainVue;
	
	/** constructeur */
	public Affichage(VoitureVue voitureVue, TerrainVue terrainVue) {
		this.voitureVue = voitureVue;
		this.terrainVue = terrainVue;
		this.setBackground(Color.gray);
		setPreferredSize(new Dimension(Terrain.LARGEUR_TERRAIN, Terrain.HAUTEUR_TERRAIN));
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g); //permet de nettoyer l'image
		this.terrainVue.afficherTerrain(g);
		this.voitureVue.afficherVoiture(g);
	}

}
