package vue;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import modele.Terrain;

/** cette classe represente l'ensemble des decoration au bord de la route. Cette classe peut :
 * - initialiser un tableau d'images
 * - initialiser une liste de decoration de maniere a les repartir sur le terrain aleatoirement avec une image aleatoire
 * - afficher ces decorations par rapport au point de fuite
 */

public class DecorationsVue {
	
	/** constantes */
	private final static int LARGEUR_ZONE = 300; //largeur de la zone d'apparition des decorations sur chaque cote de l'ecran
	private final static int NB_DECORATION_COTE = 50; //nombre de decoration totale presentes a l'ecran
	
	/** attributs */
	private TerrainVue terrainVue;
	private BufferedImage[] imagesDecoration;
	private ArrayList<Decoration> decorations;
	
	/** constructeur */
	public DecorationsVue(TerrainVue terrainVue) {
		this.terrainVue = terrainVue;
		this.decorations = new ArrayList<>();
		this.imagesDecoration = new BufferedImage[6];
		try {
			this.imagesDecoration[0] = ImageIO.read(new File("assets/tree_1.png"));
			this.imagesDecoration[1] = ImageIO.read(new File("assets/tree_2.png"));
			this.imagesDecoration[2] = ImageIO.read(new File("assets/tree_3.png"));
			this.imagesDecoration[3] = ImageIO.read(new File("assets/stone_1.png"));
			this.imagesDecoration[4] = ImageIO.read(new File("assets/stone_2.png"));
			this.imagesDecoration[5] = ImageIO.read(new File("assets/bush_1.png"));
		} catch (IOException e) {
			System.out.println("impossible d'afficher l'image ! : "+e);
		}
		this.initListeDecorations();
	}
	
	/** cette methode permet d'initialiser la liste des decorations */
	private void initListeDecorations() {
		this.decorations.add(new Decoration(0, Terrain.HAUTEUR_TERRAIN,0));
		for (int i=0; i<NB_DECORATION_COTE; i++) {
			genererDecoration();
		}
	}
	
	/** cette methode permet de generer aleatoirement une nouvelle decoration et de l'ajouter a la liste */
	private void genererDecoration() {
		int image = (int) (Math.random() * this.imagesDecoration.length); //attribution d'une image aleatoire a la decoration
		int gauche = (int) (Math.random() * 2); //determine si la decoration est a gauche ou a droite de l'ecran
		int x;
		int y = this.decorations.get(this.decorations.size()-1).getPoint().y - Terrain.LARGEUR_TERRAIN/NB_DECORATION_COTE; //la position de la nouvelle decoration depend de la precedante
		if(gauche == 1) {
			x = (int) (-(Math.random() * LARGEUR_ZONE)); //la decoration se trouve a gauche
		}
		else {
			x = (int) ((Math.random() * (Terrain.LARGEUR_TERRAIN+LARGEUR_ZONE - Terrain.LARGEUR_TERRAIN)) + Terrain.LARGEUR_TERRAIN); //la decoration se trouve a droite
		}
		this.decorations.add(new Decoration(x,y, image));
	}
	
	/** cette methode permet d'afficher toutes les decorations de la liste d'apres leur images attribuees */
	public void afficherDecors(Graphics g) {
		for(int i = this.decorations.size()-1; i>=0;i--) { //on parcourt la liste dans l'autre sens pour eviter les erreurs de superpositions lors de l'affichage
			Decoration d = this.decorations.get(i);
			if(d.getPoint().y > 0 && d.getPoint().getY() < Terrain.HAUTEUR_TERRAIN) { //on affiche la decoration que si elle est dans l'affichage
				Point2D point1 = this.terrainVue.calculPointPerspective(d.getPoint().getX(),d.getPoint().getY());
				Point2D point2 = this.terrainVue.calculPointPerspective(d.getPoint().getX()+Decoration.LARGEUR,d.getPoint().getY());
				int largeurImage = (int)(point2.getX()-point1.getX());
				g.drawImage(this.imagesDecoration[d.getImage()], (int)point1.getX()-(largeurImage/2), (int)point1.getY()-largeurImage, largeurImage, largeurImage, null);
			}
		}
	}
	
	/** cette methode permet de modifier les positions verticales des decorations pour les faire avancer */
	public void avancer() {
		this.decorations.forEach(d -> d.avancer((int)this.terrainVue.getTerrain().getVoiture().getVitesse()));
		//partie qui ajoute et supprime des decorations au fur et a mesure de l'avancement
		if(this.decorations.get(0).getPoint().y > Terrain.HAUTEUR_TERRAIN) {
			this.decorations.remove(0);
			this.genererDecoration();
		}
	}
	
	/** getters et setters */
	
	public ArrayList<Decoration> getDecorations() {
		return this.decorations;
	}
}
