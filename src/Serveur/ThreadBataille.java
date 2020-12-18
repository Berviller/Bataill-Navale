package Serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadBataille extends Thread{
int id;
BufferedReader in1;
PrintWriter out1;
BufferedReader in2;
PrintWriter out2;
static PrintWriter[] outs=new PrintWriter[100]; 

public ThreadBataille(int id,Socket client1, Socket client2) {
	try {
	this.id=id;
	//gestion des entrées sorties du joueur 1
	in1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));
	out1 = new PrintWriter(client1.getOutputStream(), true);

	
	//gestion des entrées sorties du joueur 2
	in2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));
	out2 = new PrintWriter(client2.getOutputStream(), true);
	
	
	}catch (Exception e) {}
}

public void run() {
	try {
	while (true) {
		// joueur 1
		out1.println("Id de la partie = "+id+"\n");
		out1.println("Vous etez le joueur 1\n");
		
		//Creation des plateaux pour le joueur 1
		Plateau plateau11 = new Plateau(); //Création du plateau du joueur 1
		plateau11.initialisation();
		plateau11.placement(3, 1, 4, 5); //ceci est un teste pour vérifier le bon fonctionnement
		plateau11.placement(2, 0, 7, 6); //ceci est un teste pour vérifier le bon fonctionnement
		out1.println("Votre plateau :\n");
		out1.println(plateau11.affichage());
		Plateau plateau12 = new Plateau();// Création du plateau du joueur 2 vu par le joueur 1
		plateau12.initialisation();
		out1.println("Plateau adverse :\n");
		out1.println(plateau12.affichage());
		plateau12.placement(2, 2, 4, 2);
		plateau12.placement(3, 3, 6, 5);
		plateau11.attaquer(3, 5);
		//plateau11.attaquer(4, 5);
		
		
		
		
		// joueur 2
		out2.println("Id de la partie = "+id+"\n");
		out2.println("Vous etez le joueur 2\n");
		
		//Creation des plateaux pour le joueur 2
		Plateau plateau22 = new Plateau();// Création du plateau du joueur 2
		plateau22.initialisation();
		//plateau22.placement(2, 1, 4, 5); //ceci est un teste pour vérifier le bon fonctionnement
		out2.println("Votre plateau :\n");
		out2.println(plateau22.affichage());
		Plateau plateau21 = new Plateau(); //Création du plateau du joueur 1 vu par le joueur 2
		plateau21.initialisation();
		out2.println("Plateau adverse :\n");
		out2.println(plateau21.affichage());
		
		//information des actions possibles
		out1.println("Veuillez choisir l'action que vous souhaitez réaliser :\n-1 : Placer un bateau\n-2 : Attaquer\n-3 : Envoyer un message");
		out2.println("Veuillez choisir l'action que vous souhaitez réaliser :\n-1 : Placer un bateau\n-2 : Attaquer\n-3 : Envoyer un message");
		
		
		
		
		
		int num_action_j1 = in1.read();
		if(num_action_j1 == 1) {
			out1.println("veuillez entrer la longueur du bateau (comprise entre 2 et 5) : ");
			int l1 = in1.read();
			while(l1!=2 || l1!=3 || l1!=4 || l1!=5) {
				out1.println("Valleur incorrecte, veuillez entrer la longueur du bateau (comprise entre 2 et 5) : ");
				l1 = in1.read();
			}
			out1.println("veuillez entrer le sense du bateau (0 = tête du pateau en haut, 1 = tête du bateau à gauche) : ");
			int s1 = in1.read();
			while (s1!=0 || s1!=1) {
				
			}
			out1.println("veuillez entrer colonne de placement de la tête du bateau (compris entre 1 et 10) : ");
			int colonne1 = in1.read(); 
			while(colonne1!=1 || colonne1!=2 || colonne1!=3 || colonne1!=4 || colonne1!=5 || colonne1!=6 || colonne1!=7 || colonne1!=8 || colonne1!=9 || colonne1!=10) {
				out1.println("Valleur incorrecte, veuillez entrer colonne de placement de la tête du bateau (compris entre 1 et 10) : ");
				colonne1 = in1.read();
			}
			colonne1-=1;
			out1.println("veuillez entrer ligne de placement de la tête du bateau (compris entre 1 et 10) : ");
			int ligne1 = in1.read();
			while(l1!=1 || l1!=2 || l1!=3 || l1!=4 || l1!=5 || l1!=6 || l1!=7 || l1!=8 || l1!=9 || l1!=10) {
				out1.println("Valleur incorrecte, veuillez entrer ligne de placement de la tête du bateau (compris entre 1 et 10) : ");
				ligne1 = in1.read();
			}
			ligne1-=1;
			plateau11.placement(l1, s1, colonne1, ligne1);
		}
		String message1=in1.readLine();
		message1="Partie "+id+", Joueur 1 : "+message1;
		System.out.println(message1);
		int num_action_j2 = in2.read();
		if(num_action_j2 == 1) {
			
		}
		String message2=in2.readLine();
		message2="Partie "+id+", Joueur 2 : "+message2;
		System.out.println(message2);
	}
	}catch (Exception e) {}
}
}