package application;
import java.awt.BorderLayout;

import javax.swing.JFrame;

import controleur.Controleur;
import modele.*;
import vue.Affichage;
import vue.Afficher;
import vue.DecorsVue;
import vue.HUD;
import vue.TerrainVue;
import vue.VoitureVue;


public class Main {

	public static void main(String[] args) {
		//initialisation des classes du modele
		Voiture voiture = new Voiture(50, 500, 2);
		Route route = new Route(voiture);
		Terrain terrain = new Terrain(route, voiture);
		voiture.setTerrain(terrain);
		CompteARebour timer = new CompteARebour();
		
		//initialisation des classes de la vue
		VoitureVue voitureVue = new VoitureVue(voiture);
		TerrainVue terrainVue = new TerrainVue(terrain, voitureVue);
		DecorsVue decor = new DecorsVue();
		Affichage affichage = new Affichage(terrainVue, decor);
		HUD hud = new HUD(voiture, terrain, timer);
		
		//intialisation du controleur et ecoute de l'affichage
		Controleur controleur = new Controleur(voiture);
		affichage.setFocusable(true);
		affichage.addKeyListener(controleur);
		
		//lancement des threads
		new Conduire(voiture).start();
		new Avancer(route).start();
		new Afficher(affichage, hud).start();
		
		//instanciation de la fenetre
		JFrame fenetre = new JFrame("MagneticRoad"); //instancie une fenetre avec un titre
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //permet de quitter le programme quand on clique sur la croix
		fenetre.setLayout(new BorderLayout()); //ajout d'un BorderLayout permettant de positionner l'affichage et L'HUD
		fenetre.add(affichage, BorderLayout.CENTER); //ajoute le component au centre du border layout
		fenetre.add(hud, BorderLayout.SOUTH); //ajoute le component en bas du border layout
		fenetre.pack();
		fenetre.setVisible(true); //rend la fenetre visible
	}

}
