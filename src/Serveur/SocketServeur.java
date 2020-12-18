package Serveur;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServeur {
public static void main(String[] args) {
	try {
		ServerSocket ecoute = new ServerSocket(1500);
		System.out.println("Serveur lancé!");
		int id=0;// id de la partie
		while(true) {
		// joueur 1
			Socket client1 = ecoute.accept();
		//joueur 2
			Socket client2 = ecoute.accept();
			new ThreadBataille(id, client1, client2).start();
			id++;
			Plateau plateau1 = new Plateau();
			plateau1.initialisation();
			plateau1.placement(3, 1, 4, 5);
			plateau1.placement(2, 0, 7, 6);
			//System.out.println(plateau1.affichage());
			Plateau plateau2 = new Plateau();
			plateau2.initialisation();
			plateau2.placement(2, 2, 4, 2);
			plateau2.placement(3, 3, 6, 5);
			plateau1.attaquer(3, 5);
			plateau1.attaquer(4, 5);
			System.out.println(plateau1.affichage());
			System.out.println(plateau2.affichage());
		
		}
		} catch(Exception e) {
		// Traitement d'erreur
		}

}
}