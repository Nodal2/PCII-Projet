package vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
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
	
	private static final boolean DEBUG_MOD = false;
	
	private Route route;
	private TerrainVue terrainVue;
	private Color couleurRoute;
	private Color couleurLigneRoute;
	private BufferedImage imagePointDeControle;

	public RouteVue(Route route, TerrainVue terrainVue) {
		this.route = route;
		this.terrainVue = terrainVue;
		this.couleurRoute = new Color(71,71,71);
		this.couleurLigneRoute = new Color(189,152,65);
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
			if(!DEBUG_MOD) {
				afficherBordGaucheDroite(g);
			} 
			else {
				afficherBordGauche(g);
				afficherBordDroite(g);
			}
			
		}catch(ConcurrentModificationException | NullPointerException e) {
			System.out.println("impossible d'afficher cette courbe : courbe supprimee ! "+e); //car il est possible de vouloir afficher une courbe supprimee
		}
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
			/*
			g.drawOval((int)nouveauPremierDroite.getX(), (int)nouveauPremierDroite.getY(), 10, 10);
			g.drawOval((int)nouveauDernierDroite.getX(), (int)nouveauDernierDroite.getY(), 10, 10);
			g.drawOval((int)nouveauControleDroite.getX(), (int)nouveauControleDroite.getY(), 5, 5);
			*/
			//milieu route
			Point2D milieuBas = this.terrainVue.calculPointPerspective(c.getP1().getX()+Route.LARGEUR/2,c.getP1().getY());
			Point2D milieuHaut = this.terrainVue.calculPointPerspective(c.getP2().getX()+Route.LARGEUR/2,c.getP2().getY());
			Point2D milieuControle = this.terrainVue.calculPointPerspective(c.getCtrlX()+Route.LARGEUR/2,c.getCtrlY());
			
			//nouvelles courbes a partir des points calcules
			QuadCurve2D nouvelleCourbeGauche = new QuadCurve2D.Double();
			QuadCurve2D nouvelleCourbeDroite = new QuadCurve2D.Double();
			QuadCurve2D nouvelleCourbeMilieu = new QuadCurve2D.Double();
			nouvelleCourbeGauche.setCurve(nouveauPremierGauche, nouveauControleGauche, nouveauDernierGauche);
			nouvelleCourbeDroite.setCurve(nouveauPremierDroite, nouveauControleDroite, nouveauDernierDroite);
			nouvelleCourbeMilieu.setCurve(milieuBas, milieuControle, milieuHaut);
			
			
			
			GeneralPath closedCurve;
			closedCurve = new GeneralPath();
			
			closedCurve.append(nouvelleCourbeGauche, true);
			closedCurve.lineTo(milieuHaut.getX(), milieuHaut.getY());
			closedCurve.lineTo(milieuBas.getX(), milieuBas.getY());
			
			closedCurve.append(nouvelleCourbeDroite, true);
			closedCurve.lineTo(milieuHaut.getX(), milieuHaut.getY());
			closedCurve.lineTo(milieuBas.getX(), milieuBas.getY());
			
			
			g.setColor(this.couleurRoute);
			g.fill(closedCurve);
			g.setStroke(new BasicStroke(3.0f));
			g.setColor(this.couleurLigneRoute);
			g.draw(nouvelleCourbeMilieu);
			g.setStroke(new BasicStroke(1.0f));
			g.setColor(Color.black);
			
		});

	}
	
	/** cette methode permet d'afficher le point de controle sur la route*/
	public void afficherPointControle(Graphics g) {
		g.setColor(Color.black);
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
	
	
	private void afficherBordGauche(Graphics2D g) {
		this.route.getCourbes().forEach(c -> {
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
		this.route.getCourbes().forEach(c -> {
			Point2D nouveauPremier = new Point2D.Double(c.getP1().getX() + Route.LARGEUR, c.getP1().getY());
			Point2D nouveauDernier = new Point2D.Double(c.getP2().getX() + Route.LARGEUR, c.getP2().getY());
			Point2D nouveauControle = new Point2D.Double(c.getCtrlX() + Route.LARGEUR, c.getCtrlY());
			QuadCurve2D nouvelleCourbe = new QuadCurve2D.Double();
			nouvelleCourbe.setCurve(nouveauPremier, nouveauControle, nouveauDernier);
			g.draw(nouvelleCourbe);
		});
	}

}
