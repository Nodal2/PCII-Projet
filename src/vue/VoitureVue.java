package vue;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import modele.Voiture;

/** cette classe represente la vue de la voiture du joueur. Cette classe :
 * - possede un tableau d'images necessaires a l'affichage de la voiture et l'initialise
 * - peut afficher chacune de ces images en fontion de l'etat de la voiture
 */

public class VoitureVue {
	
	/** constantes */
	public final static int LARGEUR_VOITURE_VUE = 100;
	
	/** attributs */
	private Voiture voiture;
	private BufferedImage[] imagesVoiture;
	
	/** constructeur */
	public VoitureVue(Voiture voiture) {
		this.voiture = voiture;
		this.imagesVoiture = new BufferedImage[4];
		try {
			this.imagesVoiture[0] = ImageIO.read(new File("assets/car_gauche.png"));
			this.imagesVoiture[1] = ImageIO.read(new File("assets/car.png"));
			this.imagesVoiture[2] = ImageIO.read(new File("assets/car_droite.png"));
			this.imagesVoiture[3] = ImageIO.read(new File("assets/car_saut.png"));
		} catch (IOException e) {
			System.out.println("impossible d'afficher l'image ! : "+e);
		}
	}
	
	/** cette methode permet de dessiner une image differente en fonction de l'etat de la voiture */
	public void afficherVoiture(Graphics g) {
		if(!this.voiture.isSaute()) {
			if(this.voiture.isGauche()) { //si on va vers la gauche
				g.drawImage(this.imagesVoiture[0], this.voiture.getPosX()-(LARGEUR_VOITURE_VUE-Voiture.LARGEUR_VOITURE)/2, this.voiture.getPosY(), LARGEUR_VOITURE_VUE, Voiture.HAUTEUR_VOITURE, null);
			}
			else if(this.voiture.isDroite()) { //si on va vers la droite
				g.drawImage(this.imagesVoiture[2], this.voiture.getPosX()-(LARGEUR_VOITURE_VUE-Voiture.LARGEUR_VOITURE)/2, this.voiture.getPosY(), LARGEUR_VOITURE_VUE, Voiture.HAUTEUR_VOITURE, null);
			}
			else { //si on va tout droit
				g.drawImage(this.imagesVoiture[1], this.voiture.getPosX()-(LARGEUR_VOITURE_VUE-Voiture.LARGEUR_VOITURE)/2, this.voiture.getPosY(), LARGEUR_VOITURE_VUE, Voiture.HAUTEUR_VOITURE, null);
			}
		}
		else { //si on saute
			g.drawImage(this.imagesVoiture[3], this.voiture.getPosX()-(LARGEUR_VOITURE_VUE-Voiture.LARGEUR_VOITURE)/2, this.voiture.getPosY(), LARGEUR_VOITURE_VUE, Voiture.HAUTEUR_VOITURE, null);
		}
		
		
	}
	
	/** getters et setters */
	
	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}
}
