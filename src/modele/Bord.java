package modele;

import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;

public class Bord {
	private final static int ECART_POINT_MAX_Y = 300;
	
	private ArrayList<QuadCurve2D> courbes;
	private int borneInfX;
	private int borneSupX;
	
	
	public Bord(int borneInfX, int borneSupX) {
		this.borneInfX = borneInfX;
		this.borneSupX = borneSupX;
		this.initBord();
	}
	
	private void initBord() {
		this.courbes = new ArrayList<>();
		this.ajouterCourbe(new Point2D.Double(borneInfX,0), new Point2D.Double(borneInfX,0));
		while(this.courbes.get(this.courbes.size()-1).getP2().getY() < Terrain.HAUTEUR_TERRAIN) {
			ajouterCourbe(this.courbes.get(this.courbes.size()-1).getCtrlPt(),this.courbes.get(this.courbes.size()-1).getP2());
		}
	}
	
	public void ajouterCourbe(Point2D dernierPointControle, Point2D dernierPointFinal) {
		Point2D nouveauControle;
		Point2D nouveauDernier;
		if(dernierPointControle.getY() == dernierPointFinal.getY()) {
			if(dernierPointControle.getX() < dernierPointFinal.getX()) {
				nouveauControle = new Point2D.Double(this.borneSupX,dernierPointFinal.getY()); 
			}
			else {
				nouveauControle = new Point2D.Double(this.borneInfX, dernierPointFinal.getY()); 
				
			}
			int minY = ECART_POINT_MAX_Y/2 + (int)nouveauControle.getY();
			int maxY = ECART_POINT_MAX_Y + (int)nouveauControle.getY();
			nouveauDernier = new Point2D.Double(nouveauControle.getX(),(int)(Math.random() * (maxY - minY) + minY)); 
		}
		else {
			int minY = ECART_POINT_MAX_Y/2 + (int)dernierPointFinal.getY();
			int maxY = ECART_POINT_MAX_Y + (int)dernierPointFinal.getY();
			nouveauControle = new Point2D.Double(dernierPointFinal.getX(),(int)(Math.random() * (maxY - minY) + minY)); 
			nouveauDernier = new Point2D.Double(this.borneInfX+((this.borneSupX-this.borneInfX)/2), nouveauControle.getY());
		}
		QuadCurve2D nouvelleCourbe = new QuadCurve2D.Double();
		nouvelleCourbe.setCurve(dernierPointFinal, nouveauControle, nouveauDernier);
		this.courbes.add(nouvelleCourbe);
	}
	
	
	public ArrayList<QuadCurve2D> getCourbes() {
		return this.courbes;
	}

}
