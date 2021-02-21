package vue;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import modele.Terrain;

public class TerrainVue {
	
	/** attributs */
	private Terrain terrain;
	private VoitureVue voitureVue;
	private RouteVue routeVue;
	private Color couleurCiel;
	private Color couleurSol;
	private Point pointDeFuite;

	public TerrainVue(Terrain terrain, VoitureVue voitureVue) {
		this.terrain = terrain;
		this.voitureVue = voitureVue;
		this.routeVue = new RouteVue(this.terrain.getRoute(), this);
		this.pointDeFuite = new Point(Terrain.LARGEUR_TERRAIN/2, Terrain.HAUTEUR_HORIZON);
		this.couleurCiel = new Color(0,149,255);
		this.couleurSol = new Color(114,174,0);
	}
	
	/** cette methode permet d'afficher tous les elements presents sur le train */
	public void afficherTerrain(Graphics g) {
		g.setColor(this.couleurSol);
		g.fillRect(0, Terrain.HAUTEUR_HORIZON, Terrain.LARGEUR_TERRAIN, Terrain.HAUTEUR_TERRAIN);
		Graphics2D g2 = (Graphics2D)g;
		this.routeVue.afficherRoute(g2);
		this.afficherLigneHorizon(g);
		this.voitureVue.afficherVoiture(g);
		this.routeVue.afficherPointControle(g);
	}
	
	/** cette methode permet de tracer une ligne horizontale delimitant l'horizon */
	private void afficherLigneHorizon(Graphics g) {
		g.drawLine(0,Terrain.HAUTEUR_HORIZON, Terrain.LARGEUR_TERRAIN, Terrain.HAUTEUR_HORIZON);
		GradientPaint grandient = new GradientPaint(0,Terrain.HAUTEUR_HORIZON,Color.orange,0, 0,this.couleurCiel);
		Graphics2D g2 = (Graphics2D)g;
		g2.setPaint(grandient);
		g2.fill(new Rectangle(0, 0, Terrain.LARGEUR_TERRAIN, Terrain.HAUTEUR_HORIZON));
	}
	
	/** cette fonction positionne un nouveau point a partir de deux coordonnees en fonction de la position du point de fuite */
	public Point2D calculPointPerspective(double x, double y) {
		//si le point est au dessus de la ligne d'horizon, le nouveau point s'ecrase a la position du point de fuite
		if(y<this.pointDeFuite.y) {
			return new Point2D.Double(this.pointDeFuite.x, this.pointDeFuite.y);
		}
		else {
			double nouveauX;
			//nouveau Y qui s'ecrase plus il est proche du point de fuite
			double nouveauY = ((y-this.pointDeFuite.y)*(y-this.pointDeFuite.y))/(this.terrain.getVoiture().getPosY()-this.pointDeFuite.y)+this.pointDeFuite.y;
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
