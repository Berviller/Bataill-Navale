package Serveur;
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
public class ThreadBataille extends Thread{
int id;
BufferedReader in1;
PrintWriter out1;
BufferedReader in2;
PrintWriter out2;
static PrintWriter[] outs=new PrintWriter[100]; 
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
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
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
public void run() {
	try {
		//Creation des plateaux pour le joueur 1
		Plateau plateau11 = new Plateau(); //Création du plateau du joueur 1
		plateau11.initialisation();
		
		Plateau plateau12 = new Plateau();// Création du plateau du joueur 2 vu par le joueur 1
		plateau12.initialisation();
		
		//Creation des plateaux pour le joueur 2
		Plateau plateau22 = new Plateau();// Création du plateau du joueur 2
		plateau22.initialisation();
		
		Plateau plateau21 = new Plateau(); //Création du plateau du joueur 1 vu par le joueur 2
		plateau21.initialisation();
		
	while (true) {
		// joueur 1
		out1.println("Id de la partie = "+id+"\n");
		out1.println("Vous etez le joueur 1\n");
		plateau11.placement(3, 1, 4, 5); //ceci est un teste pour vérifier le bon fonctionnement
		plateau11.placement(2, 0, 7, 6); //ceci est un teste pour vérifier le bon fonctionnement
		out1.println("Votre plateau :\n");
		out1.println(plateau11.affichage());
		
		out1.println("Plateau adverse :\n");
		out1.println(plateau12.affichage());
		plateau12.placement(2, 2, 4, 2);
		plateau12.placement(3, 3, 6, 5);
		plateau11.attaquer(3, 5);
		//plateau11.attaquer(4, 5);
		
		
		
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
		// joueur 2
		out2.println("Id de la partie = "+id+"\n");
		out2.println("Vous etez le joueur 2\n");
		
		
		//plateau22.placement(2, 1, 4, 5); //ceci est un teste pour vérifier le bon fonctionnement
		out2.println("Votre plateau :\n");
		out2.println(plateau22.affichage());
		
		out2.println("Plateau adverse :\n");
		out2.println(plateau21.affichage());
		
		
		
		
		
		
		
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
		//information des actions possibles et traitement de ces actions chez le joueur 1
		out1.println("Veuillez choisir l'action que vous souhaitez réaliser :\n-1 : Placer un bateau\n-2 : Attaquer\n-3 : Envoyer un message");
		String num_action_j1 = in1.readLine();
		out1.println(num_action_j1);
		switch (num_action_j1) {
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
			case "1" :
				out1.println("veuillez entrer la longueur du bateau (comprise entre 2 et 5) : ");
				String strl1 = in1.readLine();
				int l1 = Integer.parseInt(strl1);
				out1.println("vous avez entrer : "+ l1);
				while(l1!=2 && l1!=3 && l1!=4 && l1!=5) {
					out1.println("Valleur incorrecte, veuillez entrer la longueur du bateau (comprise entre 2 et 5) : ");
					strl1 = in1.readLine();
					l1 = Integer.parseInt(strl1);
					out1.println("vous avez entrer : "+ l1);
				}
				
				out1.println("veuillez entrer le sens du bateau (0 = tête du pateau en haut, 1 = tête du bateau à gauche) : ");
				String strs1 = in1.readLine();
				int s1 = Integer.parseInt(strs1);
				while (s1!=0 && s1!=1) {
					out1.println("Valleur incorrecte, veuillez entrer le sense du bateau (0 = tête du pateau en haut, 1 = tête du bateau à gauche) : ");
					strs1 = in1.readLine();
					s1 = Integer.parseInt(strs1);
				}
				
				out1.println("veuillez entrer colonne de placement de la tête du bateau (compris entre 1 et 10) : ");
				String strcolonne1 = in1.readLine();
				int colonne1 = Integer.parseInt(strcolonne1);
				while(colonne1!=1 && colonne1!=2 && colonne1!=3 && colonne1!=4 && colonne1!=5 && colonne1!=6 && colonne1!=7 && colonne1!=8 && colonne1!=9 && colonne1!=10) {
					out1.println("Valleur incorrecte, veuillez entrer colonne de placement de la tête du bateau (compris entre 1 et 10) : ");
					strcolonne1 = in1.readLine();
					colonne1 = Integer.parseInt(strcolonne1);
				}
				colonne1-=1;
				
				out1.println("veuillez entrer ligne de placement de la tête du bateau (compris entre 1 et 10) : ");
				String strligne1 = in1.readLine();
				int ligne1 = Integer.parseInt(strligne1);
				while(ligne1!=1 && ligne1!=2 && ligne1!=3 && ligne1!=4 && ligne1!=5 && ligne1!=6 && ligne1!=7 && ligne1!=8 && ligne1!=9 && ligne1!=10) {
					out1.println("Valleur incorrecte, veuillez entrer ligne de placement de la tête du bateau (compris entre 1 et 10) : ");
					strligne1 = in1.readLine();
					ligne1 = Integer.parseInt(strligne1);
				}
				ligne1-=1;
				
				plateau11.placement(l1, s1, colonne1, ligne1);
				break;
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
			case "2":
				out1.println("veuillez indiquer le numéro colonne de la case que vous voulez attaquer :");
				String strc1 = in1.readLine();
				int c1 = Integer.parseInt(strc1);
				while(c1!=1 && c1!=2 && c1!=3 && c1!=4 && c1!=5 && c1!=6 && c1!=7 && c1!=8 && c1!=9 && c1!=10) {
					out1.println("Valleur incorrecte, veuillez indiquer le numéro colonne de la case que vous voulez attaquer (compris entre 1 et 10) : ");
					strc1 = in1.readLine();
					c1 = Integer.parseInt(strc1);
				}
				c1-=1;
				
				out1.println("veuillez indiquer le numéro ligne de la case que vous voulez attaquer (compris entre 1 et 10) : ");
				String strlig1 = in1.readLine();
				int lig1 = Integer.parseInt(strlig1);
				while(lig1!=1 && lig1!=2 && lig1!=3 && lig1!=4 && lig1!=5 && lig1!=6 && lig1!=7 && lig1!=8 && lig1!=9 && lig1!=10) {
					out1.println("Valleur incorrecte, veuillez indiquer le numéro ligne de la case que vous voulez attaquer (compris entre 1 et 10) : ");
					strlig1 = in1.readLine();
					lig1 = Integer.parseInt(strlig1);
				}
				lig1-=1;
				
				plateau12.attaquer(c1, lig1);
				plateau22.attaquer(c1, lig1);
				break;
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			case "3" :
				out1.println("veuillez écrire votre message : ");
				String messagej1 = in1.readLine();
				messagej1="Partie "+id+", Joueur 1 : "+messagej1;
				System.out.println(messagej1);
				out2.println(messagej1);
				break;
		}
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		out1.println("Votre plateau :\n");
		out1.println(plateau11.affichage());
		
		out1.println("Plateau adverse :\n");
		out1.println(plateau12.affichage());
		
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		
		//information des actions possibles et traitement de ces actions chez le joueur 2
		out2.println("Veuillez choisir l'action que vous souhaitez réaliser :\n-1 : Placer un bateau\n-2 : Attaquer\n-3 : Envoyer un message");
		String num_action_j2 = in2.readLine();
		switch(num_action_j2) {
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
			case "1" :
				out2.println("veuillez entrer la longueur du bateau (comprise entre 2 et 5) : ");
				String strl2 = in1.readLine();
				int l2 = Integer.parseInt(strl2);
				while(l2!=2 && l2!=3 && l2!=4 && l2!=5) {
					out2.println("Valleur incorrecte, veuillez entrer la longueur du bateau (comprise entre 2 et 5) : ");
					strl2 = in2.readLine();
					l2 = Integer.parseInt(strl2);
				}
				
				out2.println("veuillez entrer le sens du bateau (0 = tête du pateau en haut, 1 = tête du bateau à gauche) : ");
				String strs2 = in2.readLine();
				int s2 = Integer.parseInt(strs2);
				while (s2!=0 && s2!=1) {
					out2.println("Valleur incorrecte, veuillez entrer le sense du bateau (0 = tête du pateau en haut, 1 = tête du bateau à gauche) : ");
					strs2 = in2.readLine();
					s2 = Integer.parseInt(strs2);
				}
				
				out2.println("veuillez entrer colonne de placement de la tête du bateau (compris entre 1 et 10) : ");
				String strcolonne2 = in2.readLine();
				int colonne2 = Integer.parseInt(strcolonne2);
				while(colonne2!=1 && colonne2!=2 && colonne2!=3 && colonne2!=4 && colonne2!=5 && colonne2!=6 && colonne2!=7 && colonne2!=8 && colonne2!=9 && colonne2!=10) {
					out2.println("Valleur incorrecte, veuillez entrer colonne de placement de la tête du bateau (compris entre 1 et 10) : ");
					strcolonne2 = in2.readLine();
					colonne2 = Integer.parseInt(strcolonne2);
				}
				colonne2-=1;
				
				out2.println("veuillez entrer ligne de placement de la tête du bateau (compris entre 1 et 10) : ");
				String strligne2 = in2.readLine();
				int ligne2 = Integer.parseInt(strligne2);
				while(ligne2!=1 && ligne2!=2 && ligne2!=3 && ligne2!=4 && ligne2!=5 && ligne2!=6 && ligne2!=7 && ligne2!=8 && ligne2!=9 && ligne2!=10) {
					out2.println("Valleur incorrecte, veuillez entrer ligne de placement de la tête du bateau (compris entre 1 et 10) : ");
					strligne2 = in1.readLine();
					ligne2 = Integer.parseInt(strligne2);
				}
				ligne2-=1;
				
				plateau22.placement(l2, s2, colonne2, ligne2);
				break;
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
			case "2":
				out2.println("veuillez indiquer le numéro colonne de la case que vous voulez attaquer :");
				String strc2 = in2.readLine();
				int c2 = Integer.parseInt(strc2);
				while(c2!=1 && c2!=2 && c2!=3 && c2!=4 && c2!=5 && c2!=6 && c2!=7 && c2!=8 && c2!=9 && c2!=10) {
					out2.println("Valleur incorrecte, veuillez indiquer le numéro colonne de la case que vous voulez attaquer (compris entre 1 et 10) : ");
					strc2 = in2.readLine();
					c2 = Integer.parseInt(strc2);
				}
				c2-=1;
				
				out2.println("veuillez indiquer le numéro ligne de la case que vous voulez attaquer (compris entre 1 et 10) : ");
				String strlig2 = in2.readLine();
				int lig2 = Integer.parseInt(strlig2);
				while(lig2!=1 && lig2!=2 && lig2!=3 && lig2!=4 && lig2!=5 && lig2!=6 && lig2!=7 && lig2!=8 && lig2!=9 && lig2!=10) {
					out2.println("Valleur incorrecte, veuillez indiquer le numéro ligne de la case que vous voulez attaquer (compris entre 1 et 10) : ");
					strlig2 = in2.readLine();
					lig2 = Integer.parseInt(strlig2);
				}
				lig2-=1;
				
				plateau21.attaquer(c2, lig2);
				plateau11.attaquer(c2, lig2);
				break;
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			case "3" :
				out2.println("veuillez écrire votre message : ");
				String messagej2 = in2.readLine();
				messagej2="Partie "+id+", Joueur 2 : "+messagej2;
				System.out.println(messagej2);
				out1.println(messagej2);
				break;
		}
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		out2.println("Votre plateau :\n");
		out2.println(plateau11.affichage());
		
		out2.println("Plateau adverse :\n");
		out2.println(plateau12.affichage());
		
	}
	}catch (Exception e) {}
}
}