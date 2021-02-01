package modele;

public class Conduire extends Thread{
	
	private Voiture voiture;
	
	public Conduire(Voiture voiture) {
		this.voiture = voiture;
	}
	
	@Override
	public void run() {
		while(true) {
			this.voiture.controler();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}

}
