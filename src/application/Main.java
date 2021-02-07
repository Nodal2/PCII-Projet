package application;
import javax.swing.JFrame;

import controleur.Controleur;
import modele.Conduire;
import modele.Terrain;
import modele.Voiture;
import vue.Affichage;
import vue.Afficher;


public class Main {

	public static void main(String[] args) {
		Voiture voiture = new Voiture(50, 400);
		Terrain terrain = new Terrain();
		
		Conduire conduire = new Conduire(voiture);
		conduire.start();
		
		Affichage affichage = new Affichage(voiture, terrain);
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
