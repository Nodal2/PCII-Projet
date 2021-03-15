package vue;

import java.awt.Dimension;

import javax.swing.JPanel;

import modele.Terrain;

public class MenuRejouer extends JPanel{

	private static final long serialVersionUID = -1853653778276537077L;

	public MenuRejouer() {
		setPreferredSize(new Dimension(Terrain.LARGEUR_TERRAIN, Terrain.HAUTEUR_TERRAIN));
	}

}
