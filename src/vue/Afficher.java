package vue;

public class Afficher extends Thread{
	
	private Affichage affichage;

	public Afficher(Affichage affichage) {
		this.affichage = affichage;
	}
	
	@Override
	public void run() {
		while(true) {
			this.affichage.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
