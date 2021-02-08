package vue;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;

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
		this.terrain.getRoute().getCourbes().forEach(c -> {
			g.draw(c);
			/*
			g.drawOval((int)c.getP1().getX(), (int)c.getP1().getY(), 5, 5);
			g.drawOval((int)c.getP2().getX(), (int)c.getP2().getY(), 5, 5);
			g.drawOval((int)c.getCtrlX(), (int)c.getCtrlY(), 10, 10);
			*/
		});
	}
	
	public void afficherBordDroite(Graphics2D g) {
		this.terrain.getRoute().getCourbes().forEach(c -> {
			Point2D nouveauPremier = new Point2D.Double(c.getP1().getX() + this.terrain.getRoute().getLargeurRoute(), c.getP1().getY());
			Point2D nouveauDernier = new Point2D.Double(c.getP2().getX() + this.terrain.getRoute().getLargeurRoute(), c.getP2().getY());
			Point2D nouveauControle = new Point2D.Double(c.getCtrlX() + this.terrain.getRoute().getLargeurRoute(), c.getCtrlY());
			QuadCurve2D nouvelleCourbe = new QuadCurve2D.Double();
			nouvelleCourbe.setCurve(nouveauPremier, nouveauControle, nouveauDernier);
			g.draw(nouvelleCourbe);
		});
	}

}
