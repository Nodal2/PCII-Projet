package vue;

import modele.Terrain;
import modele.Voiture;
import modele.DessinDecor;

import java.awt.*;

import javax.swing.JPanel;

public class Affichage extends JPanel {
	private static final long serialVersionUID = 6883630088377101348L;

	/** Attribut */
	private Voiture voiture;
	private Terrain terrain;
	private DessinDecor decor;
	
	/** constructeur */
	public Affichage(Voiture voiture, Terrain terrain, DessinDecor decor) {
		this.voiture = voiture;
		this.terrain = terrain;
		this.decor = decor;
		setPreferredSize(new Dimension(Terrain.LARGEUR_TERRAIN, Terrain.HAUTEUR_TERRAIN));
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g); //permet de nettoyer l'image
		g.drawOval(this.voiture.getPosX(), this.voiture.getPosY(), Voiture.LARGEUR_VOITURE , Voiture.HAUTEUR_VOITURE); //Permet de dessiner un ovale
		g.drawLine(decor.posxlignep1, decor.posyligne, decor.posxlignep2, decor.posyligne);
		for(int i = 0; i<this.decor.getMontagne().size()-1; i++){
			Point p1 = this.decor.getMontagne().get(i);
			Point p2 = this.decor.getMontagne().get(i+1);

			g.drawLine(p1.x, p1.y, p2.x, p2.y);
		}

	}

}
