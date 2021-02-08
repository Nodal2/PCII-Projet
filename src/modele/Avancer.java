package modele;

public class Avancer extends Thread {
	
	private Route route;
	
	public Avancer(Route route) {
		this.route = route;
	}
	
	@Override
	public void run() {
		while(true) {
			this.route.avancer();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}

}
