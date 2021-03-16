package vue;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import modele.Voiture;

public class VoitureVue {
	
	private Voiture voiture;
	private BufferedImage[] imagesVoiture;

	
	
	public VoitureVue(Voiture voiture) {
		this.voiture = voiture;
		this.imagesVoiture = new BufferedImage[3];
		try {
			this.imagesVoiture[0] = ImageIO.read(new File("assets/car_gauche.png"));
			this.imagesVoiture[1] = ImageIO.read(new File("assets/car.png"));
			this.imagesVoiture[2] = ImageIO.read(new File("assets/car_droite.png"));
		} catch (IOException e) {
			System.out.println("impossible d'afficher l'image ! : "+e);
		}
	}
	
	public void afficherVoiture(Graphics g) {
		if(this.voiture.isGauche()) {
			g.drawImage(this.imagesVoiture[0], this.voiture.getPosX(), this.voiture.getPosY(), Voiture.LARGEUR_VOITURE, Voiture.HAUTEUR_VOITURE, null);
		}
		else if(this.voiture.isDroite()) {
			g.drawImage(this.imagesVoiture[2], this.voiture.getPosX(), this.voiture.getPosY(), Voiture.LARGEUR_VOITURE, Voiture.HAUTEUR_VOITURE, null);
		}
		else {
			g.drawImage(this.imagesVoiture[1], this.voiture.getPosX(), this.voiture.getPosY(), Voiture.LARGEUR_VOITURE, Voiture.HAUTEUR_VOITURE, null);
		}
		
	}
	
	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}
}
