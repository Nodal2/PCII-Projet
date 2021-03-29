package modele;

import java.awt.geom.QuadCurve2D;

/** cette classe represente une courbe de la route cote modele. Une courbe :
 * - possede une forme geometrique
 * - peut posseder un obstacle
 */

public class Courbe {
	
	/** attributs */
	 QuadCurve2D courbe;
	 Obstacle obstacle;
	
	/** constructeur */
	public Courbe(QuadCurve2D courbe) {
		this.courbe = courbe;
		this.obstacle = null;
	}
	
	/** methode permettant de generer un obstacle et d'affecter son attribut avec cet obstacle */
	public void associerUnObstacle() {
		this.obstacle = new Obstacle((int)this.courbe.getP1().getX(), (int)this.courbe.getP1().getY());
	}
	
	/** getters et setters */
	
	public QuadCurve2D getCourbe() {
		return this.courbe;
	}
	
	public void setCourbe(QuadCurve2D nouvelleCourbe) {
		this.courbe = nouvelleCourbe;
		if(this.obstacle != null) { //les coordonnees de la courbe et de l'obstacle sont liees
			this.obstacle.setY((int)this.courbe.getP1().getY());
			this.obstacle.setX((int)this.courbe.getP1().getX());
		}
		
	}
	
	/** retourne la coordonnee X du segment trace entre les points P1 et P2 de la courbe courante (+ largeur de route/2) */
	public float getXMilieuRoute(int y) {
		int p1x = (int)this.courbe.getP1().getX(); //definition ligne milieu de route
		int p1y = (int)this.courbe.getP1().getY();
		int p2x = (int)this.courbe.getP2().getX();
		int p2y = (int)this.courbe.getP2().getY();
		float pente = (p1y-p2y)/(float)(p1x-p2x); //calcul de la pente de cette ligne
		return -((p1y-y)/pente)+p1x+Route.LARGEUR/2; 
	}
	
	public Obstacle getObstacle() {
		return this.obstacle;
	}
	
	

}
