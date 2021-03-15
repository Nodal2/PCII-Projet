package modele;

public class Avancer extends Thread {
	private volatile boolean running = true;
	private Route route;
	
	public Avancer(Route route) {
		this.route = route;
	}
	
	@Override
	public void run() {
		while(running) {
			synchronized(this.route.getCourbes()) {
				this.route.avancer();
			}
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void arreter() {
		this.running = false;
	}

}
