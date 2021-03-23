package vue;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import modele.Terrain;

public class DecorationVue {
	private final static int LARGEUR_ZONE = 300;
	private final static int NB_DECORATION_COTE = 10;
	private final static int DISTANCE_ARBRE_Y = 40;
	
	private AvancerVue avancerDecors;
	private TerrainVue terrainVue;
	private BufferedImage[] imagesDecoration;
	private ArrayList<Decoration> decorations;
	
	public DecorationVue(TerrainVue terrainVue) {
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
	
	private void initListeDecorations() {
		this.decorations.add(new Decoration(0, Terrain.HAUTEUR_TERRAIN,0));
		for (int i=0; i<NB_DECORATION_COTE; i++) {
			genererDecoration();
		}
	}
	
	private void genererDecoration() {
		int image = (int) (Math.random() * this.imagesDecoration.length);
		int gauche = (int) (Math.random() * 2);
		int x;
		int y = this.decorations.get(this.decorations.size()-1).getPoint().y - DISTANCE_ARBRE_Y;
		if(gauche == 1) {
			x = (int) (-(Math.random() * LARGEUR_ZONE));
		}
		else {
			x = (int) ((Math.random() * (Terrain.LARGEUR_TERRAIN+LARGEUR_ZONE - Terrain.LARGEUR_TERRAIN)) + Terrain.LARGEUR_TERRAIN);
		}
		this.decorations.add(new Decoration(x,y, image));
	}
	
	public void afficherDecors(Graphics g) {
		for(int i = this.decorations.size()-1; i>=0;i--) { //on parcourt la liste dans l'autre sens pour eviter les superpositions lors de l'affichage
			Decoration d = this.decorations.get(i);
			if(d.getPoint().y > 0 && d.getPoint().getY() < Terrain.HAUTEUR_TERRAIN) {
				Point2D point1 = this.terrainVue.calculPointPerspective(d.getPoint().getX(),d.getPoint().getY());
				Point2D point2 = this.terrainVue.calculPointPerspective(d.getPoint().getX()+Decoration.LARGEUR,d.getPoint().getY());
				int largeurImage = (int)(point2.getX()-point1.getX());
				g.drawImage(this.imagesDecoration[d.getImage()], (int)point1.getX()-(largeurImage/2), (int)point1.getY()-largeurImage, largeurImage, largeurImage, null);
			}
		}
	}
	
	public void avancer() {
		for(Decoration d : this.decorations) {
			d.avancer((int)this.terrainVue.getTerrain().getVoiture().getVitesse());
		}
		if(this.decorations.get(0).getPoint().y > Terrain.HAUTEUR_TERRAIN) {
			this.decorations.remove(0);
			this.genererDecoration();
		}
		
	}
	
	public ArrayList<Decoration> getDecorations() {
		return this.decorations;
	}
	
	public AvancerVue getAvancerDecorations() {
		return this.avancerDecors;
	}
}
