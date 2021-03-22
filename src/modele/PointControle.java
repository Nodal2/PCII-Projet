package modele;

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
	
	/** cette procedure permet de faire reculer le point en fonction du nombre de fois ou il a deja recule */
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


	/** methode permettant de reinitialiser le timer lorsqu'un cp est franchis la duree du timer est ajustee en fonction du cp franchis */
	public void crediterEnTemps() {
		this.compteARebour.reset(this.numeroEtape * Voiture.VITESSE_MAXIMALE);
	}
	
	public CompteARebour getCompteARebour() {
		return this.compteARebour;
	}
	
	public int getPosY() {
		return this.posY;
	}

}
