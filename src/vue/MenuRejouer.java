package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import modele.Terrain;

public class MenuRejouer extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = -1853653778276537077L;
	private JLabel labelScore;
	private Terrain terrain;
	private  JFrame fenetrePrincipale;
	private JButton boutonQuitter;
	private Color couleurBouttonQuitter;
	private Color couleurTempsFinal;

	public MenuRejouer(Terrain terrain, JFrame fenetre) {
		this.terrain = terrain;
		this.fenetrePrincipale = fenetre;
		this.setLayout(new BorderLayout());
		this.couleurBouttonQuitter = new Color(150,150,150);
		this.couleurTempsFinal = new Color(190,255,150);
		initialiserLabelTempsFinal();
		initialiserBoutonQuitter();
		UIManager.put("OptionPane.okButtonText", "Play again");
		JOptionPane.showMessageDialog(this.fenetrePrincipale, this, "Game Over", JOptionPane.PLAIN_MESSAGE);
	}
	
	private void initialiserLabelTempsFinal() {
		this.labelScore = new JLabel("label temps final", SwingConstants.CENTER);
		this.labelScore.setText("Time survived : "+this.terrain.getChronometre().toString());
		this.labelScore.setBackground(couleurTempsFinal);
		this.labelScore.setOpaque(true);
		this.labelScore.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		this.add(labelScore, BorderLayout.CENTER);
	}
	
	private void initialiserBoutonQuitter() {
		this.boutonQuitter = new JButton("Quit");
		this.boutonQuitter.setBackground(couleurBouttonQuitter);
		this.boutonQuitter.addActionListener(this);
		this.add(this.boutonQuitter, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.boutonQuitter) {
			System.exit(0);
		}
		
	}

}
