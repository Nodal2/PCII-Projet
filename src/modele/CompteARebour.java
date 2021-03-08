package modele;

import java.util.Timer;
import java.util.TimerTask;

public class CompteARebour {
	private static final int TEMPS_INITIAL = 40;
    private Timer timer;
    private int tempsini;

    public int getTempsini(){
        return this.tempsini;
    }
    
    public CompteARebour(){
    	this.tempsini = TEMPS_INITIAL;
        this.timer = new Timer();
        this.timer.schedule(new TimerTask(){
            @Override
            public void run(){
                tempsini--;
            }
        }, 1000, 1000);
    }

    public String toString(){
        String affichage = "";
        int minutes = this.tempsini/60;
        int secondes = this.tempsini%60;
        if (minutes<10){
            affichage+="0";
        }
        affichage+=minutes;
        affichage+=":";
        if(secondes<10){
            affichage+="0";
        }
        affichage+=secondes;
        return affichage;
    }



}
