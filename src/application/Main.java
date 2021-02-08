package application;
import javax.swing.JFrame;

import controleur.Controleur;
import modele.Conduire;
import modele.Route;
import modele.Terrain;
import modele.Voiture;
import vue.Affichage;
import vue.Afficher;
import vue.TerrainVue;


public class Main {

	public static void main(String[] args) {
		Voiture voiture = new Voiture(50, 400);
		Route route = new Route();
		Terrain terrain = new Terrain(route);
		TerrainVue terrainVue = new TerrainVue(terrain);
		
		Conduire conduire = new Conduire(voiture);
		conduire.start();
		
		Affichage affichage = new Affichage(voiture, terrainVue);
		Controleur controleur = new Controleur(voiture);
		affichage.setFocusable(true);
		affichage.addKeyListener(controleur);
		Afficher afficher = new Afficher(affichage);
		afficher.start();
		
		JFrame fenetre = new JFrame("MagneticRoad"); //instancie une fenetre avec un titre
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //permet de quitter le programme quand on clique sur la croix
		fenetre.add(affichage); //ajoute le component a la fenetre
		fenetre.pack();
		fenetre.setVisible(true); //rend la fenetre visible
	}

}
