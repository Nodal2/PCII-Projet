package modele;

import java.util.Timer;
import java.util.TimerTask;

public class Chronometre {
	
	private Timer timer;
	private int secondesEcoulees;
	
	public Chronometre() {
		this.secondesEcoulees = 0;
		this.timer = new Timer();
		this.timer.schedule(new TimerTask() {
			@Override
			public void run() {
				secondesEcoulees++;
			}
		}, 1000, 1000);
	}
	
	public String toString() {
		String affichage = "";
		int minutes = this.secondesEcoulees/60;
		int secondes = this.secondesEcoulees % 60;
		if(minutes < 10) {
			affichage+="0";
		}
		affichage+=minutes;
		affichage+=":";
		if(secondes < 10) {
			affichage+="0";
		}
		affichage+=secondes;
		return affichage;
	}
	
	public int getSecondesEcoulees() {
		return this.secondesEcoulees;
	}

}
