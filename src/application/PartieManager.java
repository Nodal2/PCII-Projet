package application;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import controleur.Controleur;
import vue.Affichage;
import vue.Afficher;
import vue.DecorsVue;
import vue.HUD;
import vue.MenuRejouer;
import vue.TerrainVue;
import vue.VoitureVue;

public class PartieManager {
	private JFrame fenetre;
	private Affichage affichage;
	private HUD hud;
	private Partie partieCourante;
	private Controleur controleur;
	private TerrainVue terrainVue;
	private VoitureVue voitureVue;
	private DecorsVue decorVue;
	private Afficher afficher;

	public PartieManager() {
		nouvellePartie();


		//instanciation de la fenetre
		this.fenetre = new JFrame("MagneticRoad"); //instancie une fenetre avec un titre
		this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //permet de quitter le programme quand on clique sur la croix
		this.fenetre.setLayout(new BorderLayout()); //ajout d'un BorderLayout permettant de positionner l'affichage et L'HUD
		this.fenetre.add(affichage, BorderLayout.CENTER); //ajoute le component au centre du border layout
		this.fenetre.add(hud, BorderLayout.SOUTH); //ajoute le component en bas du border layout
		this.fenetre.pack();
		this.fenetre.setVisible(true); //rend la fenetre visible

	}

	public void nouvellePartie() {
		if(this.partieCourante == null) {
			this.partieCourante = new Partie();

			//initialisation des classes de la vue
			this.voitureVue = new VoitureVue(this.partieCourante.getVoiture());
			this.terrainVue = new TerrainVue(this.partieCourante.getTerrain(), voitureVue);
			this.hud = new HUD(this.partieCourante.getTerrain());
			this.decorVue = new DecorsVue();
			this.affichage = new Affichage(this.terrainVue, this.decorVue);

			//intialisation du controleur et ecoute de l'affichage
			this.controleur = new Controleur(this.partieCourante.getVoiture());
			affichage.setFocusable(true);
			affichage.addKeyListener(this.controleur);
		} 
		else {
			this.partieCourante = new Partie();
			//mise a jour du modele de la vue
			this.voitureVue.setVoiture(this.partieCourante.getVoiture());
			this.terrainVue.setTerrain(this.partieCourante.getTerrain());
			this.terrainVue.getRouteVue().setRoute(this.terrainVue.getTerrain().getRoute());
			this.hud.setTerrain(this.partieCourante.getTerrain());
			this.controleur.setVoiture(this.partieCourante.getVoiture());
		}
		this.afficher = new Afficher(affichage, hud);
		afficher.start();


		Perdre perdre = new Perdre(this.partieCourante, this);
		perdre.start();
	}
	
	public Afficher getAfficher() {
		return this.afficher;
	}
}
