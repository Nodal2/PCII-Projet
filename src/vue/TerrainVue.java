package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.util.ConcurrentModificationException;

import modele.Route;
import modele.Terrain;

public class TerrainVue {
	private Terrain terrain;
	private Color couleurCiel;
	private Color couleurSol;
	private Point pointDeFuite;

	public TerrainVue(Terrain terrain) {
		this.terrain = terrain;
		this.pointDeFuite = new Point(Terrain.LARGEUR_TERRAIN/2, Terrain.HAUTEUR_HORIZON);
		this.couleurCiel = new Color(0,149,224);
		this.couleurSol = new Color(114,174,0 );
	}

	public void afficherTerrain(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		this.afficherRoute(g2);
		this.afficherLigneHorizon(g);
	}

	private void afficherRoute(Graphics2D g) {
		g.setColor(this.couleurSol);
		g.fillRect(0, Terrain.HAUTEUR_HORIZON, Terrain.LARGEUR_TERRAIN, Terrain.HAUTEUR_TERRAIN);
		g.setColor(Color.black);
		try {
			afficherBordGauche(g);
			afficherBordDroite(g);
		}catch(ConcurrentModificationException | NullPointerException e) {
			System.out.println("impossible d'afficher cette courbe : courbe supprimee !"+e);
		}
		afficherPointControle(g);

	}

	private void afficherPointControle(Graphics g) {
		if(this.terrain.getRoute().getPointControle().getPosY() >= Terrain.HAUTEUR_HORIZON && 
				this.terrain.getRoute().getPointControle().getPosY() <= Terrain.HAUTEUR_TERRAIN) {
			Point2D point1 = this.calculPointPerspective(this.terrain.getRoute().getXMilieuRoute(this.terrain.getRoute().getPointControle().getPosY())-Route.LARGEUR,this.terrain.getRoute().getPointControle().getPosY());
			Point2D point2 = this.calculPointPerspective(this.terrain.getRoute().getXMilieuRoute(this.terrain.getRoute().getPointControle().getPosY())+Route.LARGEUR,this.terrain.getRoute().getPointControle().getPosY());
			g.drawLine((int)point1.getX(), (int)point1.getY(), (int)point2.getX(), (int)point2.getY());
		}
	}

	private void afficherLigneHorizon(Graphics g) {
		g.drawLine(0,Terrain.HAUTEUR_HORIZON, Terrain.LARGEUR_TERRAIN, Terrain.HAUTEUR_HORIZON);
		g.setColor(this.couleurCiel);
		g.fillRect(0, 0, Terrain.LARGEUR_TERRAIN, Terrain.HAUTEUR_HORIZON);

	}


	private void afficherBordGauche(Graphics2D g) {
		this.terrain.getRoute().getCourbes().forEach(c -> {
			Point2D nouveauPremier = calculPointPerspective(c.getP1().getX(),c.getP1().getY());
			Point2D nouveauDernier = calculPointPerspective(c.getP2().getX(),c.getP2().getY());
			Point2D nouveauControle = calculPointPerspective(c.getCtrlX(),c.getCtrlY());
			QuadCurve2D nouvelleCourbe = new QuadCurve2D.Double();
			nouvelleCourbe.setCurve(nouveauPremier, nouveauControle, nouveauDernier);
			g.draw(nouvelleCourbe);
		});

	}


	private void afficherBordDroite(Graphics2D g) {
		this.terrain.getRoute().getCourbes().forEach(c -> {
			Point2D nouveauPremier = calculPointPerspective(c.getP1().getX()+Route.LARGEUR,c.getP1().getY());
			Point2D nouveauDernier = calculPointPerspective(c.getP2().getX()+Route.LARGEUR,c.getP2().getY());
			Point2D nouveauControle = calculPointPerspective(c.getCtrlX()+Route.LARGEUR,c.getCtrlY());
			QuadCurve2D nouvelleCourbe = new QuadCurve2D.Double();
			nouvelleCourbe.setCurve(nouveauPremier, nouveauControle, nouveauDernier);
			g.draw(nouvelleCourbe);
		});
	}

	private Point2D calculPointPerspective(double x, double y) {
		double nouveauX;
		if(y<this.pointDeFuite.y) {
			return new Point2D.Double(this.pointDeFuite.x, this.pointDeFuite.y);
		}
		else {
			double nouveauY = (y-this.pointDeFuite.y)*((y-this.pointDeFuite.y)/(this.terrain.getVoiture().getPosY()-this.pointDeFuite.y))+this.pointDeFuite.y;
			if(x<this.pointDeFuite.x) {
				nouveauX = this.pointDeFuite.x-((y-this.pointDeFuite.y)*(this.pointDeFuite.x-x))/(this.terrain.getVoiture().getPosY()-this.pointDeFuite.y);
			}
			else {
				nouveauX = this.pointDeFuite.x+((y-this.pointDeFuite.y)*(x-this.pointDeFuite.x))/(this.terrain.getVoiture().getPosY()-this.pointDeFuite.y);
			}
			return new Point2D.Double(nouveauX, nouveauY);
		}
	}
	/*
	private void afficherBordGauche(Graphics2D g) {
		this.terrain.getRoute().getCourbes().forEach(c -> {
			Point2D nouveauPremier = new Point2D.Double(c.getP1().getX(), c.getP1().getY());
			Point2D nouveauDernier = new Point2D.Double(c.getP2().getX(), c.getP2().getY());
			Point2D nouveauControle = new Point2D.Double(c.getCtrlX(), c.getCtrlY());
			QuadCurve2D nouvelleCourbe = new QuadCurve2D.Double();
			nouvelleCourbe.setCurve(nouveauPremier, nouveauControle, nouveauDernier);
			g.draw(nouvelleCourbe);
			g.drawLine((int)c.getP1().getX()+Route.LARGEUR/2, (int)c.getP1().getY(), (int)c.getP2().getX()+Route.LARGEUR/2, (int)c.getP2().getY());
		});
	}


	private void afficherBordDroite(Graphics2D g) {
		this.terrain.getRoute().getCourbes().forEach(c -> {
			Point2D nouveauPremier = new Point2D.Double(c.getP1().getX() + Route.LARGEUR, c.getP1().getY());
			Point2D nouveauDernier = new Point2D.Double(c.getP2().getX() + Route.LARGEUR, c.getP2().getY());
			Point2D nouveauControle = new Point2D.Double(c.getCtrlX() + Route.LARGEUR, c.getCtrlY());
			QuadCurve2D nouvelleCourbe = new QuadCurve2D.Double();
			nouvelleCourbe.setCurve(nouveauPremier, nouveauControle, nouveauDernier);
			g.draw(nouvelleCourbe);
		});
	}*/

}
