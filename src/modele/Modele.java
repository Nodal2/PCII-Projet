package modele;

public class Modele {

    /**Attributs */
    public int Pos_x = 500; // Position x de la voiture
    public int Pos_y = 50; // Position y de la voiture

    /** Constantes */
    private static int LARG_VOIT = 150; // Largeur de la voiture
    private static int LONG_VOIT = 530; // Longueur de la voiture

    /** Accesseurs */
    public int getPos_x(){
        return this.Pos_x;
    }

    public int getPos_y(){
        return this.Pos_y;
    }

    public void setPos_X(int pos_X) {
        this.Pos_x = pos_X;
    }

    public void setPos_y(int pos_y) {
        this.Pos_y = pos_y;
    }

    public static int getLargVoit() {
        return LARG_VOIT;
    }

    public static int getLongVoit() {
        return LONG_VOIT;
    }

    /** Constructeur */
    public Modele() {

    }

    //pour teest
}
