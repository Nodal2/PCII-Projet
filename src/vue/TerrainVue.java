package vue;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import modele.Terrain;

/** cette classe represente tout l'affichage princpal du jeu. */

public class TerrainVue {
	
	/** attributs */
	private Terrain terrain;
	private VoitureVue voitureVue;
	private RouteVue routeVue;
	private DecorationsVue decorationVue;
	private Color couleurCielHaut;
	private Color couleurCielBas;
	private Color couleurSolHaut;
	private Color couleurSolBas;
	private Point pointDeFuite;
	private BufferedImage imageHorizon;
	
	/** constructeur */
	public TerrainVue(Terrain terrain, VoitureVue voitureVue) {
		this.terrain = terrain;
		this.voitureVue = voitureVue;
		this.routeVue = new RouteVue(this.terrain.getRoute(), this);
		this.decorationVue = new DecorationsVue(this);
		this.pointDeFuite = new Point(Terrain.LARGEUR_TERRAIN/2, Terrain.HAUTEUR_HORIZON);
		this.couleurCielHaut = new Color(170,170,170);
		this.couleurCielBas = new Color(100,100,100);
		this.couleurSolBas = new Color(80,100,0);
		this.couleurSolHaut = new Color(130,150,20);
		try {
			this.imageHorizon = ImageIO.read(getClass().getClassLoader().getResource("background.png"));
		} catch (IOException e) {
			System.out.println("impossible d'afficher l'image ! : "+e);
		}
	}
	
	/** cette methode permet d'afficher tous les elements presents sur le train */
	public void afficherTerrain(Graphics g) {
		GradientPaint gradient = new GradientPaint(Terrain.HAUTEUR_HORIZON,Terrain.HAUTEUR_TERRAIN,this.couleurSolBas,0, 0,this.couleurSolHaut);
		Graphics2D g2 = (Graphics2D)g;
		g2.setPaint(gradient);
		g2.fill(new Rectangle(0, Terrain.HAUTEUR_HORIZON, Terrain.LARGEUR_TERRAIN, Terrain.HAUTEUR_TERRAIN));
		
		this.afficherHorizon(g);
		this.decorationVue.afficherDecors(g2);
		this.routeVue.afficherRoute(g2);
		this.voitureVue.afficherVoiture(g);
		this.routeVue.afficherPointControle(g);
	}
	
	/** cette methode permet de tracer une ligne horizontale delimitant l'horizon */
	private void afficherHorizon(Graphics g) {
		GradientPaint gradient = new GradientPaint(0,Terrain.HAUTEUR_HORIZON,this.couleurCielBas,0, 0,this.couleurCielHaut);
		Graphics2D g2 = (Graphics2D)g;
		g2.setPaint(gradient);
		g2.fill(new Rectangle(0, 0, Terrain.LARGEUR_TERRAIN, Terrain.HAUTEUR_HORIZON));
		g.drawImage(this.imageHorizon, 0, 0, Terrain.LARGEUR_TERRAIN, Terrain.HAUTEUR_HORIZON, null);
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
			double nouveauY = ((y-this.pointDeFuite.y)*(y-this.pointDeFuite.y))/(Terrain.HAUTEUR_SOL-this.pointDeFuite.y)+this.pointDeFuite.y;
			if(x<this.pointDeFuite.x) {
				nouveauX = this.pointDeFuite.x-((y-this.pointDeFuite.y)*(this.pointDeFuite.x-x))/(Terrain.HAUTEUR_SOL-this.pointDeFuite.y);
			}
			else {
				nouveauX = this.pointDeFuite.x+((y-this.pointDeFuite.y)*(x-this.pointDeFuite.x))/(Terrain.HAUTEUR_SOL-this.pointDeFuite.y);
			}
			return new Point2D.Double(nouveauX, nouveauY);
		}
	}
	
	/** getter et setters */
	
	public Terrain getTerrain() {
		return this.terrain;
	}
	
	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}
	
	public RouteVue getRouteVue() {
		return this.routeVue;
	}
	
	public void setDecorationVue(DecorationsVue decorationVue) {
		this.decorationVue = decorationVue;
	}
	
	public DecorationsVue getDecorationVue() {
		return this.decorationVue;
	}
}
