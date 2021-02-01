import javax.swing.JFrame;

import vue.Affichage;

public class Main {

	public static void main(String[] args) {
		Affichage affichage = new Affichage();
		JFrame fenetre = new JFrame("Projet"); //instancie une fenetre avec un titre
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //permet de quitter le programme quand on clique sur la croix
		fenetre.add(affichage); //ajoute le component a la fenetre
		fenetre.pack();
		fenetre.setVisible(true); //rend la fenetre visible
	}

}
