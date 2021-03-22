package vue;

import java.awt.Point;

public class Decoration {
	public static final int HAUTEUR = 300;
	public static final int LARGEUR = 300;
	
	private Point point;
	private int image;
	
	public Decoration(int x, int y, int image) {
		this.point = new Point(x,y);
		this.image = image;
	}
	
	public void avancer(int avancement) {
		this.point.y += avancement;
	}
	
	public Point getPoint() {
		return this.point;
	}
	
	public int getImage() {
		return this.image;
	}

}
