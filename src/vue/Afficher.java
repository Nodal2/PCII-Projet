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
<<<<<<< HEAD
				Thread.sleep(10);
=======
				Thread.sleep(20);
>>>>>>> PCII-controler_voiture
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
<<<<<<< HEAD
=======
		
>>>>>>> PCII-controler_voiture
	}

}
