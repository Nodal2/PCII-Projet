package modele;

import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;

public class Route {
	
	public final static int BORNE_INF_X = 300;
	public final static int BORNE_SUP_X = Terrain.LARGEUR_TERRAIN/2;
	private final static int ECART_POINT_MAX_Y = 200;
	
	
	private ArrayList<QuadCurve2D> courbes;
	private Voiture voiture;
	
	public Route(Voiture voiture) {
		this.voiture = voiture;
		this.initBord();
	}
	
	private void initBord() {
		this.courbes = new ArrayList<>();
		this.ajouterCourbe(new Point2D.Double(BORNE_INF_X,0), new Point2D.Double(BORNE_INF_X,0));
		while(this.courbes.get(this.courbes.size()-1).getP2().getY() < Terrain.HAUTEUR_TERRAIN) {
			ajouterCourbe(this.courbes.get(this.courbes.size()-1).getCtrlPt(),this.courbes.get(this.courbes.size()-1).getP2());
		}
	}
	
	public void ajouterCourbe(Point2D dernierPointControle, Point2D dernierPointFinal) {
		Point2D nouveauControle;
		Point2D nouveauDernier;
		if(dernierPointControle.getY() == dernierPointFinal.getY()-(BORNE_SUP_X-BORNE_INF_X)/2) {
			if(dernierPointControle.getX() < dernierPointFinal.getX()) {
				nouveauControle = new Point2D.Double(BORNE_SUP_X,dernierPointFinal.getY()+(BORNE_SUP_X-BORNE_INF_X)/2); 
			}
			else {
				nouveauControle = new Point2D.Double(BORNE_INF_X, dernierPointFinal.getY()+(BORNE_SUP_X-BORNE_INF_X)/2); 
				
			}
			int minY = ECART_POINT_MAX_Y/2 + (int)nouveauControle.getY();
			int maxY = ECART_POINT_MAX_Y + (int)nouveauControle.getY();
			nouveauDernier = new Point2D.Double(nouveauControle.getX(),(int)(Math.random() * (maxY - minY) + minY)); 
		}
		else {
			int minY = ECART_POINT_MAX_Y/2 + (int)dernierPointFinal.getY();
			int maxY = ECART_POINT_MAX_Y + (int)dernierPointFinal.getY();
			nouveauControle = new Point2D.Double(dernierPointFinal.getX(),(int)(Math.random() * (maxY - minY) + minY)); 
			nouveauDernier = new Point2D.Double(BORNE_INF_X+((BORNE_SUP_X-BORNE_INF_X)/2), nouveauControle.getY()+(BORNE_SUP_X-BORNE_INF_X)/2); 
		}
		QuadCurve2D nouvelleCourbe = new QuadCurve2D.Double();
		nouvelleCourbe.setCurve(dernierPointFinal, nouveauControle, nouveauDernier);
		this.courbes.add(nouvelleCourbe);
	}
	
	public void avancer() {
		this.courbes.forEach(c -> {
			Point2D premier = new Point2D.Double(c.getP1().getX(),c.getP1().getY()+this.voiture.getVitesse());
			Point2D controle = new Point2D.Double(c.getCtrlX(),c.getCtrlY()+this.voiture.getVitesse());
			Point2D dernier = new Point2D.Double(c.getP2().getX(),c.getP2().getY()+this.voiture.getVitesse());
			c.setCurve(premier, controle, dernier);
		});
	}
	
	public int getLargeurRoute() {
		return BORNE_SUP_X-Route.BORNE_INF_X;
	}
	
	public ArrayList<QuadCurve2D> getCourbes() {
		return this.courbes;
	}

	

}
