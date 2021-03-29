package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


import modele.Terrain;
import modele.Voiture;

/** cette classe est un JPanel qui contient la zone d'informations du jeu en bas de l'ecran */

public class HUD extends JPanel {

	/** constantes */
	private static final long serialVersionUID = 1L;
	private static final int LARGEUR_HUD = Terrain.LARGEUR_TERRAIN; //largeur du bandeau
	private static final int HAUTEUR_HUD = Terrain.HAUTEUR_TERRAIN/6; //hauteur du bandeau
	private static final int LARGEUR_LABELS = LARGEUR_HUD/8; //largeur des zones de texte
	private static final int HAUTEUR_LABELS = HAUTEUR_HUD-20; //hauteur des zones de texte

	/** attributs */
	private Terrain terrain;

	private JLabel labelVitesse;
	private JLabel labelTempsRestant;
	private JLabel labelScore;

	private Color couleurHUD;
	private Color couleurVitesse;
	private Color couleurTempsRestant;
	private Color couleurTemps;

	/** constructeur */
	public HUD(Terrain terrain) {
		this.terrain = terrain;

		//initialisation des couleurs
		this.couleurHUD = new Color(106,140,140);
		this.couleurVitesse = new Color(100,100,100);
		this.couleurTempsRestant = new Color(160, 124,157);
		this.couleurTemps = new Color(0,150,150);

		//creation des labels
		initialiserLabelVitesse();
		initialiserLabelTempsRestant();
		initialiserLabelTemps();

		this.setLayout(null); //permet de placer librement les elements sur l'HUD
		this.setBorder(BorderFactory.createRaisedSoftBevelBorder()); //bordure de l'HUD
		setPreferredSize(new Dimension(LARGEUR_HUD, HAUTEUR_HUD)); //taille de l'HUD
		this.setBackground(this.couleurHUD); //couleur de l'HUD
	}

	/** redefinition de la methode paint */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		updateLabelVitesse();
		updateLabelTempsRestant();
		updateLabelTemps();
	}

	/** cette methode permet de mettre a jour le texte du label vitesse*/
	private void updateLabelVitesse() {
		this.labelVitesse.setText("Speed : "+this.terrain.getVoiture().vitesseEnKmH()+" Km/h");
		if(this.terrain.getVoiture().getVitesse() < Voiture.VITESSE_MAXIMALE/5) {
			this.labelVitesse.setForeground(Color.red);
		}
		else if (this.terrain.getVoiture().getVitesse() > Voiture.VITESSE_MAXIMALE-Voiture.VITESSE_MAXIMALE/5){
			this.labelVitesse.setForeground(Color.green);
		}
		else {
			this.labelVitesse.setForeground(Color.white);
		}
	}

	/** cette methode permet de mettre a jour le texte du label temps restant*/
	private void updateLabelTempsRestant() {
		this.labelTempsRestant.setText("Time left : " + this.terrain.getRoute().getPointControle().getCompteARebour().toString());
	}

	/** cette methode permet de mettre a jour le texte du label temps ecoule*/
	private void updateLabelTemps() {
		this.labelScore.setText(""+this.terrain.getChronometre());
	}

	/** initialisation du label vitesse */
	private void initialiserLabelVitesse() {
		this.labelVitesse =  new JLabel("label vitesse", SwingConstants.CENTER);
		this.labelVitesse.setBounds(LARGEUR_LABELS, (HAUTEUR_HUD-HAUTEUR_LABELS)/2, LARGEUR_LABELS, HAUTEUR_LABELS);
		this.labelVitesse.setOpaque(true);
		this.labelVitesse.setBackground(this.couleurVitesse);		
		this.labelVitesse.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		this.add(labelVitesse);
	}

	/** initialisation du label temps restant */
	private void initialiserLabelTempsRestant() {
		this.labelTempsRestant =  new JLabel("label temps", SwingConstants.CENTER);
		this.labelTempsRestant.setBounds(LARGEUR_LABELS*5, (HAUTEUR_HUD-HAUTEUR_LABELS)/2, LARGEUR_LABELS, HAUTEUR_LABELS);
		this.labelTempsRestant.setOpaque(true);
		this.labelTempsRestant.setBackground(this.couleurTempsRestant);	
		this.labelTempsRestant.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		this.add(labelTempsRestant);
	}

	/** initialisation du label temps ecoule */
	private void initialiserLabelTemps() {
		this.labelScore =  new JLabel("label temps", SwingConstants.CENTER);
		this.labelScore.setBounds(LARGEUR_LABELS*6, (HAUTEUR_HUD-HAUTEUR_LABELS)/2, LARGEUR_LABELS, HAUTEUR_LABELS);
		this.labelScore.setOpaque(true);
		this.labelScore.setBackground(this.couleurTemps);	
		this.labelScore.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		this.add(labelScore);
	}

	/** getters et setters */

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}

}
