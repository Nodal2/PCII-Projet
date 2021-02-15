package modele;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

public class CompteARebour {

    private Timer timer;
    private int tempsRestant;
    private int temps;
    private int tempsini = 40;

    public int getTempsini(){
        return this.tempsini;
    }
    
    private void setTemps(int n) {
        this.temps = n;
    }

    private void setTempsRestant(int n) {
        this.tempsRestant = n;
    }
    
    public CompteARebour(){
        this.timer = new Timer();
        this.timer.schedule(new TimerTask(){
            @Override
            public void run(){
                tempsini--;
            }
        }, 1000, 1000);
    }


}
