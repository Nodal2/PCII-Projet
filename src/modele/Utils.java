package modele;

import java.awt.geom.Point2D;

/** cette classe contient toutes les fonctions statiques du programme */

public class Utils {
	
	/** cette fonction calcul un point dans l'alignement de deux precedents points a la distance souhaitee */
	public static Point2D calculerPointTangeant(Point2D p1, Point2D p2, double p3X) {
		double pente = (p1.getY() - p2.getY()) / (p1.getX() - p2.getX());
		double p3Y = -pente*(p2.getX() - p3X) + p2.getY();
		return new Point2D.Double(p3X, p3Y);
	}
}
