package modele;

public class Utils {

	public static int valeurAbsolue(int v) {
		return v < 0 ? -v : v;
	}
	
	public static int max(int v1, int v2) {
		return v1 >= v2 ? v1 : v2;
	}
	
	public static int min(int v1, int v2) {
		return v1 <= v2 ? v1 : v2;
	}

}
