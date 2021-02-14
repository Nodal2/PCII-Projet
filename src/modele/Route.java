package modele;

import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;

public class Route {
	
	public final static int BORNE_INF_X = 300;
	public final static int LARGEUR = 150;
	public final static int BORNE_SUP_X = Terrain.LARGEUR_TERRAIN-BORNE_INF_X-LARGEUR;	
	
	private ArrayList<QuadCurve2D> courbes;
	private Voiture voiture;
	
	public Route(Voiture voiture) {
		this.voiture = voiture;
		this.initCoteGauche();
	}
	
	private void initCoteGauche() {
		this.courbes = new ArrayList<>();
		QuadCurve2D premiereCourbe = new QuadCurve2D.Double();
		premiereCourbe.setCurve(new Point2D.Double(BORNE_INF_X,Terrain.HAUTEUR_TERRAIN), new Point2D.Double(BORNE_INF_X, Terrain.HAUTEUR_TERRAIN-LARGEUR), new Point2D.Double(BORNE_INF_X+(BORNE_SUP_X-BORNE_INF_X)/2, Terrain.HAUTEUR_TERRAIN-2*LARGEUR));
		this.courbes.add(premiereCourbe);
		while(this.courbes.get(this.courbes.size()-1).getP2().getY() > 0) {
			this.courbes.add(ajouterCourbeCoteGauche());
		}
	}
	
	public QuadCurve2D ajouterCourbeCoteGauche() {
		Point2D dernierPointControle = this.courbes.get(this.courbes.size()-1).getCtrlPt();
		Point2D dernierPointFinal = this.courbes.get(this.courbes.size()-1).getP2();
		Point2D nouveauControle;
		Point2D nouveauDernier;
		if(dernierPointControle.getX() != dernierPointFinal.getX()) {
			if(dernierPointControle.getX() < dernierPointFinal.getX()) {
				nouveauControle = new Point2D.Double(BORNE_SUP_X,dernierPointFinal.getY()-LARGEUR); 
			}
			else {
				nouveauControle = new Point2D.Double(BORNE_INF_X, dernierPointFinal.getY()-LARGEUR); 
				
			}

			nouveauDernier = new Point2D.Double(nouveauControle.getX(),nouveauControle.getY()-LARGEUR); 
		}
		else {
			nouveauControle = new Point2D.Double(dernierPointFinal.getX(),dernierPointFinal.getY()-LARGEUR); 
			nouveauDernier = new Point2D.Double(BORNE_INF_X+(BORNE_SUP_X-BORNE_INF_X)/2, nouveauControle.getY()-LARGEUR); 
		}
		QuadCurve2D nouvelleCourbe = new QuadCurve2D.Double();
		nouvelleCourbe.setCurve(dernierPointFinal, nouveauControle, nouveauDernier);
		return nouvelleCourbe;
		
	}
	
	public void avancer() {
		//on incremente les coordonnees Y de chaque points des courbes de la route
		this.courbes.forEach(c -> {
			Point2D premier = new Point2D.Double(c.getP1().getX(),c.getP1().getY()+this.voiture.getVitesse());
			Point2D controle = new Point2D.Double(c.getCtrlX(),c.getCtrlY()+this.voiture.getVitesse());
			Point2D dernier = new Point2D.Double(c.getP2().getX(),c.getP2().getY()+this.voiture.getVitesse());
			c.setCurve(premier, controle, dernier);
		});
		//partie qui ajoute et supprime des courbes au fur et a mesure de l'avancement
		if(this.courbes.get(this.courbes.size()-1).getP2().getY() > Terrain.HAUTEUR_HORIZON) {
			this.courbes.add(ajouterCourbeCoteGauche());
		}
		if(this.courbes.get(1).getCtrlY()>Terrain.HAUTEUR_TERRAIN) {
			this.courbes.remove(0);
			
		}
	}

	
	public ArrayList<QuadCurve2D> getCourbes() {
		return this.courbes;
	}

	

}
