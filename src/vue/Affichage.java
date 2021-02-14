package vue;

import modele.Terrain;

import java.awt.*;

import javax.swing.JPanel;

public class Affichage extends JPanel {
	private static final long serialVersionUID = 6883630088377101348L;

	/** Attribut */
	private VoitureVue voitureVue;
	private TerrainVue terrainVue;
	private DecorsVue decorsVue;
	
	/** constructeur */
	public Affichage(VoitureVue voitureVue, TerrainVue terrainVue, DecorsVue decorsVue) {
		this.voitureVue = voitureVue;
		this.terrainVue = terrainVue;
		this.decorsVue = decorsVue;
		this.setBackground(Color.gray);
		setPreferredSize(new Dimension(Terrain.LARGEUR_TERRAIN, Terrain.HAUTEUR_TERRAIN));
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g); //permet de nettoyer l'image
		this.terrainVue.afficherTerrain(g);
		this.voitureVue.afficherVoiture(g);
		this.decorsVue.afficherDecor(g);
	}

}
