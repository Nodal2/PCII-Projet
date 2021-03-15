package modele;

import java.util.Timer;
import java.util.TimerTask;

public class CompteARebour {
	private static final int TEMPS_INITIAL = 60;
    private Timer timer;
    private int tempsCourant;
    
    public CompteARebour(){
    	this.tempsCourant = TEMPS_INITIAL;
        this.timer = new Timer();
        this.timer.schedule(new TimerTask(){
            @Override
            public void run(){
            	tempsCourant--;
            }
        }, 1000, 1000);
    }



    /** methode reinitialisant le timer avec une valeur indiqué en parametre soustrait au temps initial */
    public void reset(int n){
       this.tempsCourant += n;
    }

    public String toString(){
        String affichage = "";
        int minutes = this.tempsCourant/60;
        int secondes = this.tempsCourant%60;
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
    
    public Timer getTimer() {
    	return this.timer;
    }
    
    public int getTempsCourant() {
    	return this.tempsCourant;
    }



}