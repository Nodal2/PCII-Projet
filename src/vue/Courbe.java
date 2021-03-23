package vue;

import java.awt.geom.QuadCurve2D;

import modele.Obstacle;

public class Courbe {
	
	 QuadCurve2D courbe;
	 Obstacle obstacle;
	
	public Courbe(QuadCurve2D courbe) {
		this.courbe = courbe;
		this.obstacle = null;
	}
	
	public void associerUnObstacle() {
		this.obstacle = new Obstacle((int)this.courbe.getP1().getX(), (int)this.courbe.getP1().getY());
	}
	
	public QuadCurve2D getCourbe() {
		return this.courbe;
	}
	
	public void setCourbe(QuadCurve2D nouvelleCourbe) {
		this.courbe = nouvelleCourbe;
		if(this.obstacle != null) {
			this.obstacle.setY((int)this.courbe.getP1().getY());
			this.obstacle.setX((int)this.courbe.getP1().getX());
		}
		
	}
	
	public Obstacle getObstacle() {
		return this.obstacle;
	}
	
	

}
