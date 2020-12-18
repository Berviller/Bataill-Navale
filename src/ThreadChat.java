import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadChat extends Thread{
BufferedReader in1;
PrintWriter out1;
BufferedReader in2;
PrintWriter out2;

public ThreadChat(Socket client1,Socket client2) {
	try {
	in1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));
	out1 = new PrintWriter(client1.getOutputStream(), true);
	in2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));
	out2 = new PrintWriter(client2.getOutputStream(), true);
	out1.println("Les deux clients sont connectés, Vous êtes le joueur 1 \n");
	out2.println("Les deux clients sont connectés, Vous êtes le joueur 2 \n");
	}catch (Exception e) {}
}

/*public void run() {
	try {
	while (true) {
		String message=in.readLine();
		message=id+":"+message;
		System.out.println(message);
		for (int i=0;i<nbid;i++) {
			if (i!=id)outs[i].println(message);
		}
	}
	}catch (Exception e) {}
}*/
}