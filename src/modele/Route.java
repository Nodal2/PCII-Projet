package modele;

import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;

/** cette classe represente une route cote modele. Une route 
 * possede une ArrayList de courbes
 * peut initialiser cette liste
 * peut generer une nouvelle courbe
 * peut faire avancer chacune de ces courbes
 */

public class Route {

	/** constantes */
	public final static int BORNE_INF_X = 220; //position X min des points de la courbe de gauche
	public final static int LARGEUR = 150; //largeur de la route
	private final static int DISTANCE_Y = 70; //distance qui separe chaque point de chaque courbe
	public final static int BORNE_SUP_X = Terrain.LARGEUR_TERRAIN-BORNE_INF_X-LARGEUR; //position X max des points de la courbe de gauche
	private final static int PROBA_OBSTACLE = 6;

	/** attributs */
	private ArrayList<Courbe> courbes;
	private Voiture voiture;
	private PointControle pointControle;

	/** constructeur */
	public Route(Voiture voiture) {
		this.voiture = voiture;
		this.initCoteGauche();
		this.pointControle = new PointControle(this.voiture);
	}

	/** cette methode initialise la liste de courbe de bas en haut jusqu'a ce que le dernier point de la derniere courbe soit en dehors de l'ecran */
	private void initCoteGauche() {
		this.courbes = new ArrayList<>();
		QuadCurve2D premiereCourbe = new QuadCurve2D.Double();
		premiereCourbe.setCurve(new Point2D.Double(BORNE_INF_X,Terrain.HAUTEUR_TERRAIN), 
				new Point2D.Double(BORNE_INF_X, Terrain.HAUTEUR_TERRAIN-DISTANCE_Y), 
				new Point2D.Double(BORNE_INF_X+(BORNE_SUP_X-BORNE_INF_X)/2, Terrain.HAUTEUR_TERRAIN-2*DISTANCE_Y));
		this.courbes.add(new Courbe(premiereCourbe));
		while(this.courbes.get(this.courbes.size()-1).getCourbe().getP2().getY() > 0) {
			this.courbes.add(ajouterCourbeCoteGauche());
		}
	}

	/** cette methode permet d'ajouter une courbe a la liste */
	private Courbe ajouterCourbeCoteGauche() {
		int randX;
		Point2D dernierPointControle = this.courbes.get(this.courbes.size()-1).getCourbe().getCtrlPt(); //point de controle de la precedente courbe de la liste
		Point2D dernierPointFinal = this.courbes.get(this.courbes.size()-1).getCourbe().getP2(); //point final de la precedente courbe de la liste
		Point2D nouveauControle; // nouveau point de controle a calculer
		Point2D nouveauDernier; //nouveau point final a calculer
		
		//code qui permet de postionner aleatoirement le nouveauControle et nouveauFinal en evitant les cassures
		if(dernierPointControle.getX() != dernierPointFinal.getX()) {
			if(dernierPointControle.getX() < dernierPointFinal.getX()) {
				double min = (BORNE_SUP_X-dernierPointFinal.getX())/4+dernierPointFinal.getX(); //permet d'eviter d'avoir nouveau point de ctrl colle au dernier pf
				randX = (int) ((Math.random() * (BORNE_SUP_X - min)) + min);
				nouveauControle = Utils.calculerPointTangeant(dernierPointControle, dernierPointFinal,randX);
			}
			else {
				double max = (dernierPointFinal.getX()-BORNE_INF_X)/4+BORNE_INF_X; //permet d'eviter d'avoir nouveau point de ctrl colle au dernier pf
				randX = (int) ((Math.random() * (max - BORNE_INF_X)) + BORNE_INF_X);
				nouveauControle = Utils.calculerPointTangeant(dernierPointControle, dernierPointFinal,randX); 
			}			
			nouveauDernier = new Point2D.Double(nouveauControle.getX(),nouveauControle.getY()-(int) ((Math.random() * (DISTANCE_Y -DISTANCE_Y/2)) + DISTANCE_Y/2));
		}
		else {
			nouveauControle = new Point2D.Double(dernierPointFinal.getX(),dernierPointFinal.getY()-(int) ((Math.random() * (DISTANCE_Y -DISTANCE_Y/2)) + DISTANCE_Y/2)); 
			nouveauDernier = new Point2D.Double(BORNE_INF_X+(BORNE_SUP_X-BORNE_INF_X)/2, nouveauControle.getY()-(int) ((Math.random() * (DISTANCE_Y -DISTANCE_Y/2)) + DISTANCE_Y/2)); 
		}
		
		//creation de la nouvelle courbe avec P1 : dernier point final, Ctrl : nouveauControle et P2 : nouveauDernier
		QuadCurve2D nouvelleCourbe = new QuadCurve2D.Double();
		nouvelleCourbe.setCurve(dernierPointFinal, nouveauControle, nouveauDernier);
		Courbe courbe = new Courbe(nouvelleCourbe); //creation de l'objet courbe
		genererObstacleSurCourbe(courbe); //generation de l'eventuel obstacle sur cette nouvelle courbe
		return courbe;

	}
	
	/** cette methode permet d'attribut ou non un obstacle a une courbe */
	private void genererObstacleSurCourbe(Courbe courbe) {
		int randObstacle = (int) (Math.random() * PROBA_OBSTACLE);
		if(randObstacle == 0) {
			courbe.associerUnObstacle();
		}
	}

	/** cette procedure modifie la coordonnee Y de chaque point de chaque et met a jour la liste (suppression/ajout de courbe) dans certaines conditions */
	public void avancer() {
		this.avancerCourbes();
		this.pointControle.avancer();
	}
	
	/** cette methode permet de faire avancer les courbes et en ajoute ou en supprime*/
	private void avancerCourbes() {
		//on incremente les coordonnees Y de chaque points des courbes de la route
		this.courbes.forEach(c -> {
			QuadCurve2D courbe = c.getCourbe();
			Point2D premier = new Point2D.Double(courbe.getP1().getX(),courbe.getP1().getY()+this.voiture.getVitesse());
			Point2D controle = new Point2D.Double(courbe.getCtrlX(),courbe.getCtrlY()+this.voiture.getVitesse());
			Point2D dernier = new Point2D.Double(courbe.getP2().getX(),courbe.getP2().getY()+this.voiture.getVitesse());
			courbe.setCurve(premier, controle, dernier);
			c.setCourbe(courbe);
		});
		//partie qui ajoute et supprime des courbes au fur et a mesure de l'avancement
		if(this.courbes.get(this.courbes.size()-1).getCourbe().getP2().getY() > Terrain.HAUTEUR_HORIZON) {
			this.courbes.add(ajouterCourbeCoteGauche());
		}
		if(this.courbes.get(1).getCourbe().getCtrlY()>Terrain.HAUTEUR_TERRAIN) {
			this.courbes.remove(0);

		}
	}

	/** retourne la courbe la plus proche en Y de la voiture depuis la liste de courbes */
	public Courbe getCourbeCourante(int y) {
		for(Courbe courbe : this.courbes) {
			if(courbe.getCourbe().getP2().getY() <= y && courbe.getCourbe().getP1().getY() >= y) {
				return courbe;
			}
		}
		return null;
	}

	/** getter et setters */

	public PointControle getPointControle() {
		return this.pointControle;
	}


	public ArrayList<Courbe> getCourbes() {
		return this.courbes;
	}
}
