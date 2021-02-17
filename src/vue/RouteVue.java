package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ConcurrentModificationException;

import javax.imageio.ImageIO;

import modele.Route;
import modele.Terrain;

public class RouteVue {
	
	private Route route;
	private TerrainVue terrainVue;
	private BufferedImage imagePointDeControle;

	public RouteVue(Route route, TerrainVue terrainVue) {
		this.route = route;
		this.terrainVue = terrainVue;
		try {
			this.imagePointDeControle = ImageIO.read(new File("assets/checkpoint.png"));
		} catch (IOException e) {
			System.out.println("impossible d'afficher l'image ! : "+e);
		}
	}
	
	/** cette methode permet d'afficher la route */
	public void afficherRoute(Graphics2D g) {
		g.setColor(Color.black);
		try {
			afficherBordGaucheDroite(g);
		}catch(ConcurrentModificationException | NullPointerException e) {
			System.out.println("impossible d'afficher cette courbe : courbe supprimee ! "+e); //car il est possible de vouloir afficher une courbe supprimee
		}
		afficherPointControle(g);
	}
	
	/** cette methode permet d'afficher les courbes du cote droit et gauche de la route */
	private void afficherBordGaucheDroite(Graphics2D g) {
		this.route.getCourbes().forEach(c -> {
			//courbe cote gauche
			Point2D nouveauPremierGauche = this.terrainVue.calculPointPerspective(c.getP1().getX(),c.getP1().getY());
			Point2D nouveauDernierGauche = this.terrainVue.calculPointPerspective(c.getP2().getX(),c.getP2().getY());
			Point2D nouveauControleGauche = this.terrainVue.calculPointPerspective(c.getCtrlX(),c.getCtrlY());
			
			//courbe cote droit
			Point2D nouveauPremierDroite = this.terrainVue.calculPointPerspective(c.getP1().getX()+Route.LARGEUR,c.getP1().getY());
			Point2D nouveauDernierDroite = this.terrainVue.calculPointPerspective(c.getP2().getX()+Route.LARGEUR,c.getP2().getY());
			Point2D nouveauControleDroite = this.terrainVue.calculPointPerspective(c.getCtrlX()+Route.LARGEUR,c.getCtrlY());
			
			//nouvelles courbes a partir des points calcules
			QuadCurve2D nouvelleCourbeGauche = new QuadCurve2D.Double();
			QuadCurve2D nouvelleCourbeDroite = new QuadCurve2D.Double();
			nouvelleCourbeGauche.setCurve(nouveauPremierGauche, nouveauControleGauche, nouveauDernierGauche);
			nouvelleCourbeDroite.setCurve(nouveauPremierDroite, nouveauControleDroite, nouveauDernierDroite);
			
			//dessin des courbes
			g.draw(nouvelleCourbeGauche);
			g.draw(nouvelleCourbeDroite);
		});

	}
	
	/** cette methode permet d'afficher le point de controle */
	private void afficherPointControle(Graphics g) {
		if(this.route.getPointControle().getPosY() >= Terrain.HAUTEUR_HORIZON && this.route.getPointControle().getPosY() <= Terrain.HAUTEUR_TERRAIN) { //on affiche le point de controle seulement si il est a l'ecran		
			Point2D point1 = this.terrainVue.calculPointPerspective(this.route.getXMilieuRoute(this.route.getPointControle().getPosY())-Route.LARGEUR,this.route.getPointControle().getPosY());
			Point2D point2 = this.terrainVue.calculPointPerspective(this.route.getXMilieuRoute(this.route.getPointControle().getPosY())+Route.LARGEUR,this.route.getPointControle().getPosY());
			g.drawLine((int)point1.getX(), (int)point1.getY(), (int)point2.getX(), (int)point2.getY());
			//affichage de l'image
			int largeurImage = (int)(point2.getX()-point1.getX());
			int hauteurImage = (int)(point2.getX()-point1.getX())/2;
			g.drawImage(this.imagePointDeControle, (int)point1.getX(), (int)point1.getY()-hauteurImage, largeurImage, hauteurImage, null);

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
