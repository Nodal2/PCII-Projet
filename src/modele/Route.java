package modele;

import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;

public class Route {
	
	public final static int BORNE_INF_X = 200;
	public final static int BORNE_SUP_X = Terrain.LARGEUR_TERRAIN/2;
	private final static int ECART_POINT_MAX_Y = 100;
	
	
	private ArrayList<QuadCurve2D> courbes;
	private int largeur;
	private Voiture voiture;
	
	public Route(Voiture voiture, int largeur) {
		this.voiture = voiture;
		this.largeur = largeur;
		this.initBord();
	}
	
	private void initBord() {
		this.courbes = new ArrayList<>();
		this.courbes.add(ajouterCourbe(new Point2D.Double(BORNE_INF_X,Terrain.HAUTEUR_TERRAIN), new Point2D.Double(BORNE_INF_X,Terrain.HAUTEUR_TERRAIN)));
		while(this.courbes.get(this.courbes.size()-1).getP2().getY() > 0) {
			this.courbes.add(ajouterCourbe(this.courbes.get(this.courbes.size()-1).getCtrlPt(),this.courbes.get(this.courbes.size()-1).getP2()));
		}
	}
	
	public QuadCurve2D ajouterCourbe(Point2D dernierPointControle, Point2D dernierPointFinal) {
		Point2D nouveauControle;
		Point2D nouveauDernier;
		if(dernierPointControle.getY() == dernierPointFinal.getY()+(BORNE_SUP_X-BORNE_INF_X)/2) {
			if(dernierPointControle.getX() < dernierPointFinal.getX()) {
				nouveauControle = new Point2D.Double(BORNE_SUP_X,dernierPointFinal.getY()-(BORNE_SUP_X-BORNE_INF_X)/2); 
			}
			else {
				nouveauControle = new Point2D.Double(BORNE_INF_X, dernierPointFinal.getY()-(BORNE_SUP_X-BORNE_INF_X)/2); 
				
			}
			int maxY = (int)nouveauControle.getY() - ECART_POINT_MAX_Y/2;
			int minY = (int)nouveauControle.getY() -  ECART_POINT_MAX_Y;
			nouveauDernier = new Point2D.Double(nouveauControle.getX(),(int)(Math.random() * (maxY - minY) + minY)); 
		}
		else {
			int maxY = (int)dernierPointFinal.getY() - ECART_POINT_MAX_Y/2;
			int minY = (int)dernierPointFinal.getY() - ECART_POINT_MAX_Y;
			nouveauControle = new Point2D.Double(dernierPointFinal.getX(),(int)(Math.random() * (maxY - minY) + minY)); 
			nouveauDernier = new Point2D.Double(BORNE_INF_X+((BORNE_SUP_X-BORNE_INF_X)/2), nouveauControle.getY()-(BORNE_SUP_X-BORNE_INF_X)/2); 
		}
		QuadCurve2D nouvelleCourbe = new QuadCurve2D.Double();
		nouvelleCourbe.setCurve(dernierPointFinal, nouveauControle, nouveauDernier);
		return nouvelleCourbe;
		
	}
	
	public void avancer() {
		this.courbes.forEach(c -> {
			Point2D premier = new Point2D.Double(c.getP1().getX(),c.getP1().getY()+this.voiture.getVitesse());
			Point2D controle = new Point2D.Double(c.getCtrlX(),c.getCtrlY()+this.voiture.getVitesse());
			Point2D dernier = new Point2D.Double(c.getP2().getX(),c.getP2().getY()+this.voiture.getVitesse());
			c.setCurve(premier, controle, dernier);
		});
		if(this.courbes.get(this.courbes.size()-1).getP2().getY() > 100) {
			this.courbes.add(ajouterCourbe(this.courbes.get(this.courbes.size()-1).getCtrlPt(),this.courbes.get(this.courbes.size()-1).getP2()));
		}
		if(this.courbes.get(1).getCtrlY()>Terrain.HAUTEUR_TERRAIN) {
			this.courbes.remove(0);
			
		}
	}
	
	public int getLargeurRoute() {
		return this.largeur;
	}
	
	public ArrayList<QuadCurve2D> getCourbes() {
		return this.courbes;
	}

	

}
