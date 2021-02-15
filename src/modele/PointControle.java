package modele;

public class PointControle {
	
	private Voiture voiture;
	private int numeroEtape;
	private int posY;
	
	public PointControle(Voiture voiture) {
		this.voiture = voiture;
		this.numeroEtape = 0;
		reculerPointControle();
	}
	
	public void reculerPointControle() {
		this.numeroEtape++;
		this.posY = -100*this.numeroEtape;
	}
	
	public void setPosY(int y) {
		this.posY = y;
	}
	
	public int getPosY() {
		return this.posY;
	}
	
	public void avancer() {
		this.posY = this.posY+(int)this.voiture.getVitesse();
		this.crediterVoiture();
		if(Terrain.HAUTEUR_TERRAIN < this.posY) {
			this.reculerPointControle();
		}
	}
	
	public void crediterVoiture() {
		if(this.voiture.getPosY() < this.posY) {
			//TODO
		}
	}

}
