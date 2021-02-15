package vue;

public class Afficher extends Thread{
	
	private Affichage affichage;
	private HUD hud;

	public Afficher(Affichage affichage, HUD hud) {
		this.affichage = affichage;
		this.hud = hud;
	}
	
	@Override
	public void run() {
		while(true) {
			this.affichage.repaint();
			this.hud.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
