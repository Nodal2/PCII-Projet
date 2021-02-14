package vue;

import modele.Terrain;
import modele.DessinDecor;

import java.awt.*;

import javax.swing.JPanel;

public class Affichage extends JPanel {
	private static final long serialVersionUID = 6883630088377101348L;

	/** Attribut */
	private VoitureVue voitureVue;
	private TerrainVue terrainVue;
	private DessinDecor decor;
	
	/** constructeur */
	public Affichage(VoitureVue voitureVue, TerrainVue terrainVue, DessinDecor decor) {
		this.voitureVue = voitureVue;
		this.terrainVue = terrainVue;
		this.decor = decor;
		this.setBackground(Color.gray);
		setPreferredSize(new Dimension(Terrain.LARGEUR_TERRAIN, Terrain.HAUTEUR_TERRAIN));
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g); //permet de nettoyer l'image
		this.terrainVue.afficherTerrain(g);
		this.voitureVue.afficherVoiture(g);

		/** dessin de la ligne brisée pour les montagnes */
		for(int i = 0; i<this.decor.getMontagne().size()-1; i++){
			Point p1 = this.decor.getMontagne().get(i);
			Point p2 = this.decor.getMontagne().get(i+1);

			g.drawLine(p1.x, p1.y, p2.x, p2.y);
		}

		/** dessin des décors autours de la route */
		for(int i = 0; i<this.decor.getDecoration().size()-1; i++){
			Point p1 = this.decor.getDecoration().get(i);

			g.drawRect(p1.x, p1.y, decor.DECOR_LARG, decor.DECOR_HAUT);
		}
	}

}
