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
import modele.CompteARebour;

public class HUD extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final int LARGEUR_HUD = Terrain.LARGEUR_TERRAIN;
	private static final int HAUTEUR_HUD = Terrain.HAUTEUR_TERRAIN/6;
	private static final int LARGEUR_LABELS = LARGEUR_HUD/8;
	private static final int HAUTEUR_LABELS = HAUTEUR_HUD-20;
	
	private JLabel labelVitesse;
	private JLabel labelTempsRestant;
	private JLabel labelScore;
	private Voiture voiture;
	private Terrain terrain;
	private Color couleurHUD;
	private Color couleurVitesse;
	private Color couleurTempsRestant;
	private Color couleurTemps;


	private CompteARebour timer;


	public HUD(Voiture voiture, Terrain terrain, CompteARebour timer) {
		this.setLayout(null);
		this.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		this.voiture = voiture;
		this.terrain = terrain;
		this.timer = timer;
		this.couleurHUD = new Color(106,140,140);
		this.couleurVitesse = new Color(100,100,100);
		this.couleurTempsRestant = new Color(160, 124,157);
		this.couleurTemps = new Color(0,150,150);
		setPreferredSize(new Dimension(LARGEUR_HUD, HAUTEUR_HUD));
		this.setBackground(this.couleurHUD);
		//creation des labels
		initialiserLabelVitesse();
		initialiserLabelTempsRestant();
		initialiserLabelTemps();
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g); //permet de nettoyer l'image
		updateLabelVitesse();
		updateLabelTempsRestant();
		updateLabelTemps();
	}
	
	/** cette methode permet de mettre a jour le texte du label vitesse avec la vitesse courante de la voiture du joueur */
	private void updateLabelVitesse() {
		this.labelVitesse.setText("Speed : "+this.voiture.vitesseEnKmH()+" Km/h");
		if(this.voiture.getVitesse() < Voiture.VITESSE_MAXIMALE/5) {
			this.labelVitesse.setForeground(Color.red);
		}
		else if (this.voiture.getVitesse() > Voiture.VITESSE_MAXIMALE-Voiture.VITESSE_MAXIMALE/5){
			this.labelVitesse.setForeground(Color.green);
		}
		else {
			this.labelVitesse.setForeground(Color.white);
		}
	}
	
	private void updateLabelTempsRestant() {

		this.labelTempsRestant.setText("Time left : " + timer.toString());
	}
	
	private void updateLabelTemps() {
		this.labelScore.setText(""+this.terrain.getChronometre());
	}
	
	private void initialiserLabelVitesse() {
		this.labelVitesse =  new JLabel("label vitesse", SwingConstants.CENTER);
		this.labelVitesse.setBounds(LARGEUR_LABELS, (HAUTEUR_HUD-HAUTEUR_LABELS)/2, LARGEUR_LABELS, HAUTEUR_LABELS);
		this.labelVitesse.setOpaque(true);
		this.labelVitesse.setBackground(this.couleurVitesse);		
		this.labelVitesse.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		this.add(labelVitesse);
	}
	
	private void initialiserLabelTempsRestant() {
		this.labelTempsRestant =  new JLabel("label temps", SwingConstants.CENTER);
		this.labelTempsRestant.setBounds(LARGEUR_LABELS*5, (HAUTEUR_HUD-HAUTEUR_LABELS)/2, LARGEUR_LABELS, HAUTEUR_LABELS);
		this.labelTempsRestant.setOpaque(true);
		this.labelTempsRestant.setBackground(this.couleurTempsRestant);	
		this.labelTempsRestant.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		this.add(labelTempsRestant);
	}
	
	private void initialiserLabelTemps() {
		this.labelScore =  new JLabel("label temps", SwingConstants.CENTER);
		this.labelScore.setBounds(LARGEUR_LABELS*6, (HAUTEUR_HUD-HAUTEUR_LABELS)/2, LARGEUR_LABELS, HAUTEUR_LABELS);
		this.labelScore.setOpaque(true);
		this.labelScore.setBackground(this.couleurTemps);	
		this.labelScore.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		this.add(labelScore);
	}

}
