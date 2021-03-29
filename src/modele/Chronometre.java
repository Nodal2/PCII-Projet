package modele;

import java.util.Timer;
import java.util.TimerTask;

/** cette classe represente un chronometre. Un chronometre peut :
 * - lancer un timer qui incremente les secondes
 * - afficher le temps a partir du nombre de secondes ecoulees 
 */

public class Chronometre {
	
	/** attributs */
	private Timer timer;
	private int secondesEcoulees;
	
	/** constructeur */
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
	
	/** cette fonction permet d'afficher un temps au format MM:SS a partir des secondes ecoulees */
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
	
	/** getters et setters */
	
	public int getSecondesEcoulees() {
		return this.secondesEcoulees;
	}
	
	public Timer getTimer() {
    	return this.timer;
    }

}
