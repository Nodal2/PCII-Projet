package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.util.ConcurrentModificationException;

import modele.Route;
import modele.Terrain;

public class TerrainVue {

	private Terrain terrain;
	private Color couleurCiel;
	private Color couleurSol;

	public TerrainVue(Terrain terrain) {
		this.terrain = terrain;
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
		g.fillRect(0, this.terrain.getHorizon().getY(), Terrain.LARGEUR_TERRAIN, Terrain.HAUTEUR_TERRAIN);
		g.setColor(Color.black);
		try {
			afficherBordGauche(g);
			afficherBordDroite(g);
		}catch(ConcurrentModificationException e) {
			System.out.println("impossible d'afficher cette courbe : courbe supprimee !");
		}

	}

	private void afficherBordGauche(Graphics2D g) {
		
		this.terrain.getRoute().getCourbes().forEach(c -> {
			g.draw(c);
			/*
			g.drawLine((int)c.getP1().getX()+Route.LARGEUR/2, (int)c.getP1().getY(), (int)c.getP2().getX()+Route.LARGEUR/2, (int)c.getP2().getY());
			g.drawOval((int)c.getP1().getX(), (int)c.getP1().getY(), 5, 5);
			g.drawOval((int)c.getP2().getX(), (int)c.getP2().getY(), 5, 5);
			g.drawOval((int)c.getCtrlX(), (int)c.getCtrlY(), 10, 10);
			*/
		});
	}



	private void afficherLigneHorizon(Graphics g) {
		g.drawLine(0,this.terrain.getHorizon().getY(), Terrain.LARGEUR_TERRAIN, this.terrain.getHorizon().getY());
		g.setColor(this.couleurCiel);
		g.fillRect(0, 0, Terrain.LARGEUR_TERRAIN, this.terrain.getHorizon().getY());

	}
	
	private void afficherBordDroite(Graphics2D g) {
		this.terrain.getRoute().getCourbes().forEach(c -> {
			Point2D nouveauPremier = new Point2D.Double(c.getP1().getX() + calculLargeurPerspective((int)c.getP1().getY()), c.getP1().getY());
			Point2D nouveauDernier = new Point2D.Double(c.getP2().getX() +calculLargeurPerspective((int)c.getP2().getY()) , c.getP2().getY());
			Point2D nouveauControle = new Point2D.Double(c.getCtrlX() + calculLargeurPerspective((int)c.getCtrlY()), c.getCtrlY());
			QuadCurve2D nouvelleCourbe = new QuadCurve2D.Double();
			nouvelleCourbe.setCurve(nouveauPremier, nouveauControle, nouveauDernier);
			g.draw(nouvelleCourbe);
		});
	}
	
	/** cette fonction calcul la largeur entre les deux cotes de la route en prenant en compte la perspective */
	private int calculLargeurPerspective(int y) {
		return ((y-this.terrain.getHorizon().getY())*Route.LARGEUR)/(this.terrain.getVoiture().getPosY()-this.terrain.getHorizon().getY());
	}


	/*
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
