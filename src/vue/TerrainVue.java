package vue;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;

import modele.Route;
import modele.Terrain;

public class TerrainVue {
	private Terrain terrain;

	public TerrainVue(Terrain terrain) {
		this.terrain = terrain;
	}
	
	public void afficherRoute(Graphics2D g) {
		afficherBordGauche(g);
		afficherBordDroite(g);
	}
	
	public void afficherBordGauche(Graphics2D g) {
		this.terrain.getRoute().getBordGauche().getCourbes().forEach(c -> g.draw(c));
	}
	
	public void afficherBordDroite(Graphics2D g) {
		this.terrain.getRoute().getBordGauche().getCourbes().forEach(c -> {
			Point2D nouveauPremier = new Point2D.Double(c.getP1().getX()+Terrain.LARGEUR_TERRAIN/2-Route.LIMITE_GAUCHE, c.getP1().getY());
			Point2D nouveauDernier = new Point2D.Double(c.getP2().getX()+Terrain.LARGEUR_TERRAIN/2-Route.LIMITE_GAUCHE, c.getP2().getY());
			Point2D nouveauControle = new Point2D.Double(c.getCtrlX()+Terrain.LARGEUR_TERRAIN/2-Route.LIMITE_GAUCHE, c.getCtrlY());
			QuadCurve2D nouvelleCourbe = new QuadCurve2D.Double();
			nouvelleCourbe.setCurve(nouveauPremier, nouveauControle, nouveauDernier);
			g.draw(nouvelleCourbe);
		});
	}

}
