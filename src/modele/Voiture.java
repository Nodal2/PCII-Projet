package modele;

public class Voiture {

	/** constantes */
	public final static int LARGEUR_VOITURE = 100;
	public final static int HAUTEUR_VOITURE = 50;
	private final static int VITESSE_LATERALE = 1;

	/** attributs */
	private int posX;
	private int posY;
	private int velociteLaterale;
	private int vitesse;
	private boolean gauche;
	private boolean droite;
	
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
			this.freiner();
		}
		this.posX += velociteLaterale; // mise a jour de la position
		this.resterDansTerrain(); //correction de la position si en dehors du terrain
	}

	
	/** cette procedure permet de corriger la position et d'annuler la velocite si la voiture est hors des bornes du terrain */
	private void resterDansTerrain() {
		if( this.posX < 0 ) {
			this.posX = 0;
			this.velociteLaterale = 0;
		}
		else if ( this.posX + LARGEUR_VOITURE > Terrain.LARGEUR_TERRAIN -1 ) { // il faut prendre en compte la largeur de la voiture
			this.posX = Terrain.LARGEUR_TERRAIN - LARGEUR_VOITURE -1;
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
	public void freiner() {
		if(this.velociteLaterale < 0) {
			this.versLaDroite();
		}
		else if(this.velociteLaterale > 0) {
			this.versLaGauche();
		}
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
	
	public int getVitesse() {
		return this.vitesse;
	}

}

