package Serveur;

public class Plateau {
	public int[][] plateau;

	void initialisation() {
		plateau = new int[10][10];
	}
	
	public void placement(int l, int s, int ligne, int colonne) {
		for(int i = 0;i < 4; i++) {
			if(s == 0 && plateau.length-ligne >= l) {
				for(int j = 0; j<l; j++) {
					plateau[colonne+j][ligne] = 1;
				}
			}
			else if(s == 1 && plateau[ligne].length-colonne <= l) {
				for (int j = 0; j<l; j++) {
					plateau[colonne][ligne-j] = 1;
				}
			}
			else if(s == 0 && plateau.length-ligne <= l) {
				for(int j = 0; j<l; j++) {
					plateau[colonne-j][ligne] = 1;
				}
			}
			else {
				for (int j = 0; j<l; j++) {
					plateau[colonne][ligne+j] = 1;
				}
			}
		}
	}
	
	int attaquer(int l, int c) {
		if(plateau[c][l] == 1) {
			plateau[c][l] = 2;
			return 1;
		}
		
		else if (plateau [c][l] == 2 || plateau[c][l] == 3) {
			return 2;
		}
		
		else {
			return 0;
		}
	}
	
	public String affichage () {
		String liste = "";
		  for (int i = 0;i < plateau.length; i++) {
			  for (int j = 0;j < plateau[i].length;j++) {
				  liste += "|" + plateau[i][j] + "|" + " ";
			  }
			  liste += "\n";
		  }
		            
		return liste;
	}
}