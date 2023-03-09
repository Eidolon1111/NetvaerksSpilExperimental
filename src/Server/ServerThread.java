package Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerThread extends Thread{

	private Socket connSocket;
	private DataOutputStream outToClient;
	
	public ServerThread(Socket connSocket) {
		this.connSocket = connSocket;
	}

	public void run() {
		try {
			while (true) {
				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connSocket.getInputStream()));
				outToClient = new DataOutputStream(connSocket.getOutputStream());
				String clientSentence = inFromClient.readLine();
				TCPServer.updateClients(clientSentence);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeToClients(String action){
		try {
			this.outToClient.writeBytes(action + '\n');
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
