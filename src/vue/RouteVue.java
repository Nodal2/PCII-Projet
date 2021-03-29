package vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ConcurrentModificationException;

import javax.imageio.ImageIO;

import modele.Courbe;
import modele.Route;
import modele.Terrain;
import modele.Voiture;

public class RouteVue {
	
	/** constantes */
	private static final boolean DEBUG_MOD = false;
	
	/** attributs */
	private Route route;
	private TerrainVue terrainVue;
	private Color couleurRoute;
	private Color couleurLigneRoute;
	private BufferedImage imagePointDeControle;
	private BufferedImage imageObstacle;
	
	/** constructeur */
	public RouteVue(Route route, TerrainVue terrainVue) {
		this.route = route;
		this.terrainVue = terrainVue;
		this.couleurRoute = new Color(71,71,71);
		this.couleurLigneRoute = new Color(189,152,65);
		try {
			this.imagePointDeControle = ImageIO.read(getClass().getClassLoader().getResource("checkpoint.png"));
			this.imageObstacle = ImageIO.read(getClass().getClassLoader().getResource("obstacle.png"));
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
			QuadCurve2D courbe = c.getCourbe();
			
			//courbe cote gauche
			Point2D nouveauPremierGauche = this.terrainVue.calculPointPerspective(courbe.getP1().getX(),courbe.getP1().getY());
			Point2D nouveauDernierGauche = this.terrainVue.calculPointPerspective(courbe.getP2().getX(),courbe.getP2().getY());
			Point2D nouveauControleGauche = this.terrainVue.calculPointPerspective(courbe.getCtrlX(),courbe.getCtrlY());
			
			//courbe cote droit
			Point2D nouveauPremierDroite = this.terrainVue.calculPointPerspective(courbe.getP1().getX()+Route.LARGEUR,courbe.getP1().getY());
			Point2D nouveauDernierDroite = this.terrainVue.calculPointPerspective(courbe.getP2().getX()+Route.LARGEUR,courbe.getP2().getY());
			Point2D nouveauControleDroite = this.terrainVue.calculPointPerspective(courbe.getCtrlX()+Route.LARGEUR,courbe.getCtrlY());
			
			//milieu route
			Point2D milieuBas = this.terrainVue.calculPointPerspective(courbe.getP1().getX()+Route.LARGEUR/2,courbe.getP1().getY());
			Point2D milieuHaut = this.terrainVue.calculPointPerspective(courbe.getP2().getX()+Route.LARGEUR/2,courbe.getP2().getY());
			Point2D milieuControle = this.terrainVue.calculPointPerspective(courbe.getCtrlX()+Route.LARGEUR/2,courbe.getCtrlY());
			
			//nouvelles courbes a partir des points calcules
			QuadCurve2D nouvelleCourbeGauche = new QuadCurve2D.Double();
			QuadCurve2D nouvelleCourbeDroite = new QuadCurve2D.Double();
			QuadCurve2D nouvelleCourbeMilieu = new QuadCurve2D.Double();
			nouvelleCourbeGauche.setCurve(nouveauPremierGauche, nouveauControleGauche, nouveauDernierGauche);
			nouvelleCourbeDroite.setCurve(nouveauPremierDroite, nouveauControleDroite, nouveauDernierDroite);
			nouvelleCourbeMilieu.setCurve(milieuBas, milieuControle, milieuHaut);
			
			
			//cette partie du code permet de colorier la route
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
			
			//cette partie du code permet de recuperer et d'afficher l'obstacle de la courbe (s'il n'est pas null)
			if(c.getObstacle() != null) {
				Point2D point1 = this.terrainVue.calculPointPerspective(c.getObstacle().getX(), c.getObstacle().getY());
				Point2D point2 = this.terrainVue.calculPointPerspective(c.getObstacle().getX()+c.getObstacle().getLargeur(), c.getObstacle().getY());
				//affichage de l'image
				int largeurImage = (int)(point2.getX()-point1.getX());
				g.drawImage(this.imageObstacle, (int)point1.getX(), (int)point1.getY(), largeurImage, largeurImage/2, null);
			}
		});

	}
	
	/** cette methode permet d'afficher le point de controle sur la route*/
	public void afficherPointControle(Graphics g) {
		g.setColor(Color.black);
		Courbe courbePointControle = this.route.getCourbeCourante(this.route.getPointControle().getPosY());
		if(this.route.getPointControle().getPosY() >= Terrain.HAUTEUR_HORIZON && this.route.getPointControle().getPosY() <= Terrain.HAUTEUR_TERRAIN) { //on affiche le point de controle seulement si il est a l'ecran		
			Point2D point1 = this.terrainVue.calculPointPerspective(courbePointControle.getXMilieuRoute(this.route.getPointControle().getPosY())-Route.LARGEUR,this.route.getPointControle().getPosY());
			Point2D point2 = this.terrainVue.calculPointPerspective(courbePointControle.getXMilieuRoute(this.route.getPointControle().getPosY())+Route.LARGEUR,this.route.getPointControle().getPosY());
			//affichage de l'image
			int largeurImage = (int)(point2.getX()-point1.getX());
			g.drawImage(this.imagePointDeControle, (int)point1.getX(), (int)point1.getY()-largeurImage/2, largeurImage, largeurImage/2, null);

		}
	}
	
	/** cette methode permet d'afficher uniquement le cote gauche */
	private void afficherBordGauche(Graphics2D g) {
		this.route.getCourbes().forEach(c -> {
			QuadCurve2D courbe = c.getCourbe();
			Point2D nouveauPremier = new Point2D.Double(courbe.getP1().getX(), courbe.getP1().getY());
			Point2D nouveauDernier = new Point2D.Double(courbe.getP2().getX(), courbe.getP2().getY());
			Point2D nouveauControle = new Point2D.Double(courbe.getCtrlX(), courbe.getCtrlY());
			QuadCurve2D nouvelleCourbe = new QuadCurve2D.Double();
			nouvelleCourbe.setCurve(nouveauPremier, nouveauControle, nouveauDernier);
			g.draw(nouvelleCourbe);
			g.drawLine((int)courbe.getP1().getX()+Route.LARGEUR/2, (int)courbe.getP1().getY(), (int)courbe.getP2().getX()+Route.LARGEUR/2, (int)courbe.getP2().getY());
			if(c.getObstacle() != null) {
				g.drawRect(c.getObstacle().getX(), c.getObstacle().getY(), c.getObstacle().getLargeur(), c.getObstacle().getHauteur());
		
			}
			g.drawRect(this.terrainVue.getTerrain().getVoiture().getPosX(), this.terrainVue.getTerrain().getVoiture().getPosY(), Voiture.LARGEUR_VOITURE, Voiture.HAUTEUR_VOITURE/2);
		});
	}

	/** cette methode permet d'afficher uniquement le cote droit */
	private void afficherBordDroite(Graphics2D g) {
		this.route.getCourbes().forEach(c -> {
			QuadCurve2D courbe = c.getCourbe();
			Point2D nouveauPremier = new Point2D.Double(courbe.getP1().getX() + Route.LARGEUR, courbe.getP1().getY());
			Point2D nouveauDernier = new Point2D.Double(courbe.getP2().getX() + Route.LARGEUR, courbe.getP2().getY());
			Point2D nouveauControle = new Point2D.Double(courbe.getCtrlX() + Route.LARGEUR, courbe.getCtrlY());
			QuadCurve2D nouvelleCourbe = new QuadCurve2D.Double();
			nouvelleCourbe.setCurve(nouveauPremier, nouveauControle, nouveauDernier);
			
			g.drawOval((int)nouveauPremier.getX(), (int)nouveauPremier.getY(), 10, 10);
			g.drawOval((int)nouveauDernier.getX(), (int)nouveauDernier.getY(), 10, 10);
			g.drawOval((int)nouveauControle.getX(), (int)nouveauControle.getY(), 5, 5);
			
			g.draw(nouvelleCourbe);
		});
	}
	
	/** getters et setters */
	
	public void setRoute(Route route) {
		this.route = route;
	}

}
