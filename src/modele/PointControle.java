package modele;

/** cette classe represente un point de controle cote modele. Un point de controle peut :
 * - reculer si il atteint le bas du terrain
 * - avancer
 * - crediter le compte a rebours en temps supplementaire
 */

public class PointControle {
	
	/** attributs */
	private Voiture voiture;
	private CompteARebour compteARebour;
	private int numeroEtape;
	private int posY;
	
	/** constructeur */
	public PointControle(Voiture voiture) {
		this.voiture = voiture;
		this.numeroEtape = 0;
		this.compteARebour = new CompteARebour();
	}
	
	/** cette procedure permet de faire reculer le point en fonction du nombre de fois ou il a recule */
	public void reculerPointControle() {
		this.numeroEtape++;
		this.crediterEnTemps();
		this.posY = -Terrain.HAUTEUR_TERRAIN*this.numeroEtape;
	}
	
	/** cette methode permet de modifier la position Y du point de controle pour le faire avancer */
	public void avancer() {
		this.posY = this.posY+(int)this.voiture.getVitesse();
		if(Terrain.HAUTEUR_TERRAIN < this.posY) {
			this.reculerPointControle();
		}
	}

	/** cette methode permet de reinitialiser le timer lorsqu'un cp est franchis la duree du timer est ajustee en fonction du cp franchis */
	public void crediterEnTemps() {
		this.compteARebour.reset(this.numeroEtape * Voiture.VITESSE_MAXIMALE);
	}
	
	/** getters et setters */
	
	public CompteARebour getCompteARebour() {
		return this.compteARebour;
	}
	
	public int getPosY() {
		return this.posY;
	}

}
