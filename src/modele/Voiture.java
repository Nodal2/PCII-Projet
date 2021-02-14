package modele;

import java.awt.geom.QuadCurve2D;


public class Voiture {

	/** constantes */
	public final static int LARGEUR_VOITURE = 100;
	public final static int HAUTEUR_VOITURE = 30;
	private final static int VITESSE_MAXIMALE = 5; //vitesse verticale max
	private final static float ACCELERATION = 0.01f; //force de l'acceleration
	private final static float FREINAGE = 0.05f; //force du freinage
	private final static float VITESSE_LATERALE = 0.5f; //vitesse de deplacement laterale du vehicule

	/** attributs */
	private int posX;
	private int posY;
	private float velociteLaterale;
	private float vitesse;
	private boolean gauche;
	private boolean droite;
	private Terrain terrain;
	
	/** constructeur */
	public Voiture(int x, int y, int vitesse) {
		this.posX = x;
		this.posY = y;
		this.velociteLaterale = 0;
		this.vitesse = vitesse;
		this.droite = false;
		this.gauche = false;
	}
	
	/** cette procedure permet a la voiture de bouger si elle va a gauche ou a droite et de freiner sinon */
	public void controler() {
		if(this.gauche && !this.droite) {
			this.versLaGauche();
		}
		else if(this.droite && !this.gauche) {
			this.versLaDroite();
		}
		else{
			this.freinerLateralement();
		}
		this.posX += velociteLaterale; // mise a jour de la position
		this.resterDansTerrain(); //correction de la position si en dehors du terrain
	}

	
	/** cette procedure permet de corriger la position et d'annuler la velocite si la voiture est hors des bornes du terrain (et donc de l'ecran)*/
	private void resterDansTerrain() {
		if( this.posX < 0 ) {
			this.posX = 0;
			this.velociteLaterale = 0;
		}
		else if ( this.posX + LARGEUR_VOITURE > Terrain.LARGEUR_TERRAIN) { // il faut prendre en compte la largeur de la voiture
			this.posX = Terrain.LARGEUR_TERRAIN - LARGEUR_VOITURE;
			this.velociteLaterale = 0;
		}
	}
	
	/** cette procedure permet d'augmenter le poids de la vitesse vers la gauche */
	private void versLaGauche() {
		this.velociteLaterale -= VITESSE_LATERALE;	

	}
	
	/** cette procedure permet d'augmenter le poids de la vitesse vers la droite */
	private void versLaDroite() {
		this.velociteLaterale += VITESSE_LATERALE;
	}
	
	/** cette procedure permet d'inhiber la velocite vers la gauche ou la droite */
	public void freinerLateralement() {
		if(this.velociteLaterale < 0) {
			this.versLaDroite();
		}
		else if(this.velociteLaterale > 0) {
			this.versLaGauche();
		}
	}
	
	public void controleVitesse() {
		float x = getXMilieuRoute();
		if(posX+LARGEUR_VOITURE/2 < x-Route.LARGEUR/2 || posX+LARGEUR_VOITURE/2 > x + Route.LARGEUR/2) {
			this.freiner();
		}
		else {
			this.accelerer();
		}
	}
	
	public float getXMilieuRoute() {
		QuadCurve2D courbeCouranteGauche = getCourbeCourante();
		
		int p1x = (int)courbeCouranteGauche.getP1().getX(); //definition ligne milieu de route
		int p1y = (int)courbeCouranteGauche.getP1().getY();
		int p2x = (int)courbeCouranteGauche.getP2().getX();
		int p2y = (int)courbeCouranteGauche.getP2().getY();
		
		float pente = (p1y-p2y)/(float)(p1x-p2x); //calcul de la pente de cette ligne
		return -((p1y-posY)/pente)+p1x+Route.LARGEUR/2; 
	}
	
	private void accelerer() {
		if(vitesse+ACCELERATION < VITESSE_MAXIMALE) {
			this.vitesse += ACCELERATION;
		}
	}
	
	private void freiner() {
		if(this.vitesse - FREINAGE > 0) {
			this.vitesse -= FREINAGE;
		}
		
	}
	
	public QuadCurve2D getCourbeCourante() {
		for(QuadCurve2D courbe : this.terrain.getRoute().getCourbes()) {
			if(courbe.getP2().getY() <= this.posY && courbe.getP1().getY() >= this.posY) {
				return courbe;
			}
		}
		return null;
	}
	
	/** getters et setters */
	
	public int getPosX() {
		return this.posX;
	}
	public int getPosY() {
		return this.posY;
	}

	public void setGauche(boolean gauche) {
		this.gauche = gauche;
	}

	public void setDroite(boolean droite) {
		this.droite = droite;
	}
	
	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}
	
	public float getVitesse() {
		return this.vitesse;
	}
	
	public Terrain getTerrain() {
		return this.terrain;
	}

}

