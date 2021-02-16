package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import modele.Terrain;

public class TerrainVue {
	private Terrain terrain;
	private RouteVue routeVue;
	private Color couleurCiel;
	private Color couleurSol;
	private Point pointDeFuite;

	public TerrainVue(Terrain terrain) {
		this.terrain = terrain;
		this.routeVue = new RouteVue(this.terrain.getRoute(), this);
		this.pointDeFuite = new Point(Terrain.LARGEUR_TERRAIN/2, Terrain.HAUTEUR_HORIZON);
		this.couleurCiel = new Color(0,149,224);
		this.couleurSol = new Color(114,174,0);
	}

	public void afficherTerrain(Graphics g) {
		g.setColor(this.couleurSol);
		g.fillRect(0, Terrain.HAUTEUR_HORIZON, Terrain.LARGEUR_TERRAIN, Terrain.HAUTEUR_TERRAIN);
		Graphics2D g2 = (Graphics2D)g;
		this.routeVue.afficherRoute(g2);
		this.afficherLigneHorizon(g);
	}

	

	private void afficherLigneHorizon(Graphics g) {
		g.drawLine(0,Terrain.HAUTEUR_HORIZON, Terrain.LARGEUR_TERRAIN, Terrain.HAUTEUR_HORIZON);
		g.setColor(this.couleurCiel);
		g.fillRect(0, 0, Terrain.LARGEUR_TERRAIN, Terrain.HAUTEUR_HORIZON);
	}

	public Point2D calculPointPerspective(double x, double y) {
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
	

}
