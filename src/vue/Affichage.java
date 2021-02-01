package vue;

import modele.Voiture;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Affichage extends JPanel {
	private static final long serialVersionUID = 6883630088377101348L;

	/** Attribut */
	private Voiture modele;
	
	/** constantes */
	public static final int LARGEUR_FENETRE = 1000;
	public static final int HAUTEUR_FENETRE = 600;
	
	/** constructeur */
	public Affichage(Voiture e) {
		this.modele = e;
		setPreferredSize(new Dimension(LARGEUR_FENETRE, HAUTEUR_FENETRE));
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g); //permet de nettoyer l'image
		g.drawOval(this.modele.getPosX(), this.modele.getPosY(), Voiture.LARGEUR_VOITURE , Voiture.HAUTEUR_VOITURE); //Permet de dessiner un ovale

	}

}
