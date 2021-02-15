package vue;

import modele.Terrain;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class DecorsVue {

    /** Attributs */
    public int DECOR_LARG = 35; // largeur des decors
    public int DECOR_HAUT = 70; // hauteur des decors
    private ArrayList<Point> montagne; //liste contenant les point dessinant la montagne
    private ArrayList<Point> decoration; // liste contenant point pour les decors

    /** Constructeur */
    public DecorsVue(){
    	this.montagne = new ArrayList<>();
    	this.decoration = new ArrayList<>();
        for(int i=0; i<=Terrain.LARGEUR_TERRAIN+1; i++){
            if(i == 0){
                Random r2 = new Random();
                int pointy = 0 + r2.nextInt(Terrain.HAUTEUR_HORIZON - 0);
                Point point1 = new Point(0, pointy);
                this.montagne.add(point1);
            }
            else {
                Random r = new Random();
                Point point2 = new Point((this.montagne.get(i-1).x)+50, (0+r.nextInt(Terrain.HAUTEUR_HORIZON-0)));
                this.montagne.add(point2);
            }
        }

        for(int i=0; i<=14; i++){
            Random r1 = new Random();
            Random r2 = new Random();
            Point p = new Point(0 + r1.nextInt(Terrain.LARGEUR_TERRAIN-0), Terrain.HAUTEUR_HORIZON + r2.nextInt(Terrain.HAUTEUR_TERRAIN - Terrain.HAUTEUR_HORIZON));
            this.decoration.add(p);
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
		/*
		for(int i = 0; i<this.decoration.size()-1; i++){
			Point p1 = this.decoration.get(i);

			g.drawRect(p1.x, p1.y, DECOR_LARG, DECOR_HAUT);
		}*/
    }
}
