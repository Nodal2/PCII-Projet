package Modele;

public class Modele {

    /**Attributs */
    private int Pos_X = 50; // Position x de la voiture
    private int Pos_y = 50; // Position y de la voiture

    /** Constantes */
    private static int LARG_VOIT = 50; // Largeur de la voiture
    private static int LONG_VOIT = 50; // Longueur de la voiture

    /** Accesseurs */
    public int getPos_X(){
        return this.Pos_X;
    }

    public int getPos_y(){
        return this.Pos_y;
    }

    public void setPos_X(int pos_X) {
        this.Pos_X = pos_X;
    }

    public void setPos_y(int pos_y) {
        this.Pos_y = pos_y;
    }

    /** Constructeur */
    public Modele() {

    }
}
