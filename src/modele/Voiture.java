package modele;

public class Voiture {

	/** constantes */
	public final static int LARGEUR_VOITURE = 100;
	public final static int HAUTEUR_VOITURE = 100;
	private final static int VITESSE_LATERALE = 1;

	/** attributs */
	private int posX;
	private int posY;
	private int velocite;
	private boolean gauche;
	private boolean droite;

	public Voiture(int x, int y) {
		this.posX = x;
		this.posY = y;
		this.velocite = 0;
		this.droite = false;
		this.gauche = false;
	}

	public void controler() {
		
		if(this.posX + this.velocite < 0) {
			this.velocite = -(posX-VITESSE_LATERALE);
		}
		else if(this.posX + LARGEUR_VOITURE + this.velocite > Terrain.LARGEUR_TERRAIN) {
			this.velocite = Terrain.LARGEUR_TERRAIN-(this.posX+LARGEUR_VOITURE+VITESSE_LATERALE);
		}
		else {
			if(this.gauche && !this.droite) {
				this.aGauche();
			}
			if(this.droite && !this.gauche) {
				this.aDroite();
			}
			if((!this.droite && !this.gauche) || (this.droite && this.gauche)) {
				this.freiner();
			}
		}
		
	}

	public void aGauche() {
		this.velocite -= VITESSE_LATERALE;
		this.posX += (int)velocite;	
	}

	public void aDroite() {
		this.velocite += VITESSE_LATERALE;
		this.posX += (int)velocite;
	}

	public void freiner() {
		if(this.velocite < 0) {
			this.aDroite();
		}
		else if(this.velocite > 0) {
			this.aGauche();
		}
	}

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

}

