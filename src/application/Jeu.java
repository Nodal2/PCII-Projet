package application;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import controleur.Controleur;
import modele.Partie;
import vue.Affichage;
import vue.Afficher;
import vue.AvancerVue;
import vue.DecorationsVue;
import vue.HUD;
import vue.TerrainVue;
import vue.VoitureVue;

/** cette classe represente un jeu dans son ensemble. Un jeu peut :
 * - creer une fenetre
 * - initialiser une partie cote modele, initialiser la vue et lancer les threads de la vue
 * - creer une nouvelle partie en liant la vue a un nouveau modele de partie
 * - arreter les threads du modele et de la vue de la partie courante
 */

public class Jeu {
	/** constantes */
	public static final int FREQUENCE_RAFRAICHISSEMENT = 20; //taux de rafraichissement des threads du jeu

	/** attributs */
	private JFrame fenetre;
	private Affichage affichage;
	private HUD hud;
	private Partie partieCourante;
	private Controleur controleur;
	private TerrainVue terrainVue;
	private VoitureVue voitureVue;
	private Afficher afficher;
	private AvancerVue avancerDecors;

	/** constructeur */
	public Jeu() {
		this.initJeu();
		this.instancierFenetre();
	}
	
	/** cette methode permet de initialiser une partie cote modele, initialiser les classes meres de la vue et lancer les threads de la vue */
	private void initJeu() {
		//creation et lancement du modele de la partie
		this.partieCourante = new Partie(); 
		
		//initialisation des classes de la vue
		this.voitureVue = new VoitureVue(this.partieCourante.getVoiture());
		this.terrainVue = new TerrainVue(this.partieCourante.getTerrain(), voitureVue);
		this.hud = new HUD(this.partieCourante.getTerrain());
		this.affichage = new Affichage(this.terrainVue);
		
		//intialisation du controleur et ecoute de l'affichage
		this.controleur = new Controleur(this.partieCourante.getVoiture());
		this.affichage.setFocusable(true);
		this.affichage.addKeyListener(this.controleur);
		
		//creation et lancement des threads de la vue
		this.avancerDecors = new AvancerVue(this.terrainVue.getDecorationVue());
		this.avancerDecors.start();
		this.afficher = new Afficher(affichage, hud);
		this.afficher.start();
		
		Perdre perdre = new Perdre(this);
		perdre.start();
	}
	
	/** cette methode permet de creer une nouvelle partie et de lier la vue a cette nouvelle partie */
	public void nouvellePartie() {
		//creation et lancement du modele de la nouvelle partie
		this.partieCourante = new Partie();
		
		//mise a jour des attributs de la vue avec le nouveau modele
		this.voitureVue.setVoiture(this.partieCourante.getVoiture());
		this.terrainVue.setTerrain(this.partieCourante.getTerrain());
		this.terrainVue.getRouteVue().setRoute(this.terrainVue.getTerrain().getRoute());
		this.hud.setTerrain(this.partieCourante.getTerrain());
		this.controleur.setVoiture(this.partieCourante.getVoiture());
		this.terrainVue.setDecorationVue(new DecorationsVue(this.terrainVue));
		
		//relancement des threads de la vue
		this.avancerDecors = new AvancerVue(this.terrainVue.getDecorationVue());
		this.avancerDecors.start();
		this.afficher = new Afficher(affichage, hud);
		this.afficher.start();

		Perdre perdre = new Perdre(this);
		perdre.start();
	}
	
	/** cette methode permet d'arreter tous les threads du modele et de la vue de la partie courante */
	public void stopperPartieCourante() {
		this.partieCourante.getAvancer().arreter();
		this.partieCourante.getConduire().arreter();
		this.avancerDecors.arreter();
		this.afficher.arreter();
		this.partieCourante.getTerrain().getRoute().getPointControle().getCompteARebour().getTimer().cancel();
		this.partieCourante.getTerrain().getChronometre().getTimer().cancel();
	}
	
	/** cette methode permet d'initialiser la fenetre principale du jeu */
	private void instancierFenetre() {
		this.fenetre = new JFrame("OnTheRoad"); //instancie une fenetre avec un titre
		this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //permet de quitter le programme quand on clique sur la croix
		this.fenetre.setLayout(new BorderLayout()); //ajout d'un BorderLayout permettant de positionner l'affichage et L'HUD
		this.fenetre.add(affichage, BorderLayout.CENTER); //ajoute le component au centre du border layout
		this.fenetre.add(hud, BorderLayout.SOUTH); //ajoute le component en bas du border layout
		this.fenetre.pack();
		this.fenetre.setVisible(true); //rend la fenetre visible
	}
	
	/** getters et setters */
	
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
