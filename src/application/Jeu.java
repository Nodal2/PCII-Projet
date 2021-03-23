package application;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import controleur.Controleur;
import modele.Partie;
import vue.Affichage;
import vue.Afficher;
import vue.AvancerVue;
import vue.DecorationVue;
import vue.HUD;
import vue.TerrainVue;
import vue.VoitureVue;

public class Jeu {
	
	public static final int FREQUENCE_RAFRAICHISSEMENT = 20;
	
	private JFrame fenetre;
	private Affichage affichage;
	private HUD hud;
	private Partie partieCourante;
	private Controleur controleur;
	private TerrainVue terrainVue;
	private VoitureVue voitureVue;
	private Afficher afficher;
	private AvancerVue avancerDecors;

	public Jeu() {
		initJeu();
		//instanciation de la fenetre
		this.fenetre = new JFrame("MagneticRoad"); //instancie une fenetre avec un titre
		this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //permet de quitter le programme quand on clique sur la croix
		this.fenetre.setLayout(new BorderLayout()); //ajout d'un BorderLayout permettant de positionner l'affichage et L'HUD
		this.fenetre.add(affichage, BorderLayout.CENTER); //ajoute le component au centre du border layout
		this.fenetre.add(hud, BorderLayout.SOUTH); //ajoute le component en bas du border layout
		this.fenetre.pack();
		this.fenetre.setVisible(true); //rend la fenetre visible

	}

	private void initJeu() {
		this.partieCourante = new Partie();

		//initialisation des classes de la vue
		this.voitureVue = new VoitureVue(this.partieCourante.getVoiture());
		this.terrainVue = new TerrainVue(this.partieCourante.getTerrain(), voitureVue);
		this.hud = new HUD(this.partieCourante.getTerrain());
		this.affichage = new Affichage(this.terrainVue);
		//intialisation du controleur et ecoute de l'affichage
		this.controleur = new Controleur(this.partieCourante.getVoiture());
		affichage.setFocusable(true);
		affichage.addKeyListener(this.controleur);
		
		this.avancerDecors = new AvancerVue(this.terrainVue.getDecorationVue());
		this.avancerDecors.start();
		
		this.afficher = new Afficher(affichage, hud);
		this.afficher.start();

		Perdre perdre = new Perdre(this);
		perdre.start();
	}

	public void nouvellePartie() {

		this.partieCourante = new Partie();
		//mise a jour du modele de la vue
		this.voitureVue.setVoiture(this.partieCourante.getVoiture());
		this.terrainVue.setTerrain(this.partieCourante.getTerrain());
		this.terrainVue.getRouteVue().setRoute(this.terrainVue.getTerrain().getRoute());
		this.hud.setTerrain(this.partieCourante.getTerrain());
		this.controleur.setVoiture(this.partieCourante.getVoiture());
		this.terrainVue.setDecorationVue(new DecorationVue(this.terrainVue));
		this.avancerDecors = new AvancerVue(this.terrainVue.getDecorationVue());
		this.avancerDecors.start();
		
		this.afficher = new Afficher(affichage, hud);
		this.afficher.start();


		Perdre perdre = new Perdre(this);
		perdre.start();
	}
	
	public void stopperPartieCourante() {
		this.partieCourante.getAvancer().arreter();
		this.partieCourante.getConduire().arreter();
		this.avancerDecors.arreter();
		this.afficher.arreter();
		this.partieCourante.getTerrain().getRoute().getPointControle().getCompteARebour().getTimer().cancel();
		this.partieCourante.getTerrain().getChronometre().getTimer().cancel();
	}

	public Afficher getAfficher() {
		return this.afficher;
	}

	public JFrame getFenetre() {
		return this.fenetre;
	}
	
	public Partie getPartieCourante() {
		return this.partieCourante;
	}
}
