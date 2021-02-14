package vue;

import modele.Terrain;
import modele.Route;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class DecorsVue {

    public int posyligne = Terrain.HAUTEUR_TERRAIN - 450; //position y de la ligne d'horizon
    public int DECOR_LARG = 35; // largeur des decors
    public int DECOR_HAUT = 70; // hauteur des decors
    private ArrayList<Point> montagne = new ArrayList<>(); //liste contenant les point dessinant la montagne
    private ArrayList<Point> decoration = new ArrayList<>(); // liste contenant point pour les decors
    private static final int nbDeco = 15;
    private static final int nbMontagne = 25;

    /** Constructeur */
    public DecorsVue(){
        /*
        for(int i=0; i<=nbMontagne-1; i++){
            if(i == 0){
                Random r2 = new Random();
                int pointy = 0 + r2.nextInt(posyligne - 0);
                Point point1 = new Point(0, pointy);
                this.montagne.add(point1);
            }
            else {
                Random r = new Random();
                Point point2 = new Point((this.montagne.get(i-1).x)+50, (0+r.nextInt(posyligne-0)));
                this.montagne.add(point2);
            }
        }*/

        /** boucle pour crèer les points faisant la montagne */
        int cpt = 0;

        Random r5 = new Random();
        int pointy = 0 + r5.nextInt(posyligne - 0);
        Point point1 = new Point(0, pointy);
        this.montagne.add(point1);

        while(this.montagne.get(cpt).x < Terrain.LARGEUR_TERRAIN){
            if(cpt == 0){
                cpt++;
                Random r = new Random();
                Point point2 = new Point((this.montagne.get(cpt-1).x)+50, (0+r.nextInt(posyligne-0)));
                this.montagne.add(point2);
            }
            else {
                cpt++;
                Random r = new Random();
                Point point2 = new Point((this.montagne.get(cpt-1).x)+50, (0+r.nextInt(posyligne-0)));
                this.montagne.add(point2);
            }
        }

        /** boucle pour crée les points pour les décorations */
        for(int i=0; i<=(nbDeco-1)/2; i++){
            Random r1 = new Random();
            Random r2 = new Random();
            Random r3 = new Random();
            Random r4 = new Random();
            Point p = new Point(0 + r1.nextInt(Route.BORNE_INF_X-0), posyligne + r2.nextInt(Terrain.HAUTEUR_TERRAIN - posyligne));
            Point p2 = new Point (Route.BORNE_SUP_X + r3.nextInt(Terrain.LARGEUR_TERRAIN - Route.BORNE_SUP_X),posyligne + r4.nextInt(Terrain.HAUTEUR_TERRAIN - posyligne));
            this.decoration.add(p);
            this.decoration.add(p2);
        }
    }
    
    public void afficherDecor(Graphics g) {
    	/** dessin de la ligne brisée pour les montagnes */
		for(int i = 0; i<this.montagne.size()-1; i++){
			Point p1 = this.montagne.get(i);
			Point p2 = this.montagne.get(i+1);

			g.drawLine(p1.x, p1.y, p2.x, p2.y);
		}
		
		/** dessin des décors autours de la route */
		
		for(int i = 0; i<this.decoration.size()-1; i++){
			Point p1 = this.decoration.get(i);

			g.drawRect(p1.x, p1.y, DECOR_LARG, DECOR_HAUT);
		}
    }
}
