package NetvaerksSpil;

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
				//outToClient.writeBytes(clientSentence + '\n' );

				for (ServerThread serverThread : TCPServer.getThreads()){
					serverThread.writeToClients(clientSentence);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// do the work here
	}

	public void writeToClients(String action){
		try {
			this.outToClient.writeBytes(action + '\n');
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
