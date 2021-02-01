package vue;

import Modele.Modele;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Affichage extends JPanel {
	private static final long serialVersionUID = 6883630088377101348L;

	/** Attribut */
	private Modele modele;
	
	/** constantes */
	public static final int LARGEUR_FENETRE = 1000;
	public static final int HAUTEUR_FENETRE = 600;
	
	/** constructeur */
	public Affichage(Modele e) {
		this.modele = e;
		setPreferredSize(new Dimension(LARGEUR_FENETRE, HAUTEUR_FENETRE));
	}
	
	@Override
	public void paint(Graphics g) {
		//g.clearRect(0, 0, LARGEUR_FENETRE, HAUTEUR_FENETRE);
		super.paint(g); //permet de nettoyer l'image
		super.revalidate();
		//super.repaint();

		g.drawOval(this.modele.getPos_x(), this.modele.getPos_y(), this.modele.getLargVoit() , this.modele.getLongVoit()); //Permet de dessiner un ovale

	}

}
