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

	public CompteARebour getCompteARebour(){
		return this.compteARebour;
	}

	/** methode permettant de reinitialiser le timer lorsqu'un cp est franchis la durée du timer est ajusté en fonction du cp franchis */
	public void crediterVoiture() {
		if(this.voiture.getPosY() < this.posY) {
			int n = numeroEtape;
			switch (n){
				case 0 : this.compteARebour.reset(5);
				break;
				case 1 : this.compteARebour.reset(10);
				break;
				case 2 : this.compteARebour.reset(15);
				break;
				case 3 : this.compteARebour.reset(20);
				break;
				case 4 : this.compteARebour.reset(25);
				break;
				case 5 : this.compteARebour.reset(30);
				break;
				case 6 : this.compteARebour.reset(35);
				break;
				case 7 : this.compteARebour.reset(40);
				break;
				case 8 : this.compteARebour.reset(45);
				break;
				case 9 : this.compteARebour.reset(50);
				break;
				case 10 : this.compteARebour.reset(55);
				break;
				case 11 : this.compteARebour.reset(60);
				break;
				case 12 : this.compteARebour.reset(65);
				break;
				case 13 : this.compteARebour.reset(70);
				break;
				case 14 : this.compteARebour.reset(75);
				break;
				case 15 : this.compteARebour.reset(80);
				break;

				default: this.compteARebour.reset(9999);
			}

		}
	}

}
