package modele;

public class PointControle {
	
	/** attributs */
	private Voiture voiture;
	private int numeroEtape;
	private int posY;
	
	/** constructeur */
	public PointControle(Voiture voiture) {
		this.voiture = voiture;
		this.numeroEtape = 0;
		//reculerPointControle();
	}
	
	/** cette procedure permet de faire reculer le point en fonction du nombre de fois ou il a deja recule */
	public void reculerPointControle() {
		this.numeroEtape++;
		this.posY = -Terrain.HAUTEUR_TERRAIN*this.numeroEtape;
	}
	
	/** cette methode permet de modifier la position Y du point de controle pour le faire avancer */
	public void avancer() {
		this.posY = this.posY+(int)this.voiture.getVitesse();
		this.crediterVoiture();
		if(Terrain.HAUTEUR_TERRAIN < this.posY) {
			this.reculerPointControle();
		}
	}
	
	public int getPosY() {
		return this.posY;
	}
	
	public void crediterVoiture() {
		if(this.voiture.getPosY() < this.posY) {
			//TODO
		}
	}

}
