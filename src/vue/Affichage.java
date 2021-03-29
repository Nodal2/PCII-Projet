package vue;

import modele.Terrain;

import java.awt.*;

import javax.swing.JPanel;

/** cette classe est un JPanel qui contient l'affichage principal du jeu (voiture, decors etc) */

public class Affichage extends JPanel {
	
	/** constantes */
	private static final long serialVersionUID = 6883630088377101348L;

	/** attributs */
	private TerrainVue terrainVue;
	
	/** constructeur */
	public Affichage(TerrainVue terrainVue) {
		this.terrainVue = terrainVue;
		setPreferredSize(new Dimension(Terrain.LARGEUR_TERRAIN, Terrain.HAUTEUR_TERRAIN));
	}
	
	/** redefinition de la methode paint */
	@Override
	public void paint(Graphics g) { //l'objet Graphics est une classe abstraite qui permet a l'application de dessiner sur un component, ici le JPanel
		super.paint(g); //permet de nettoyer l'image
		this.terrainVue.afficherTerrain(g);
	}

	public TerrainVue getTerrainVue() {
		return terrainVue;
	}

}
