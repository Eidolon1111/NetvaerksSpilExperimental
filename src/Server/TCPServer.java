package Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TCPServer {

	private static ArrayList<ServerThread> serverThreads = new ArrayList<>();
	private static ArrayList<String> joins = new ArrayList<>();
	private static ArrayList<String> readys = new ArrayList<>();
	private static String[] spawns = {"1 1", "18 1", "1 18", "18 18"};
	private static boolean launched = false;

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		ServerSocket welcomeSocket = new ServerSocket(6789);
		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			ServerThread serverThread = new ServerThread(connectionSocket);
			serverThreads.add(serverThread);
			serverThread.start();
		}
	}

	public static synchronized void updateClients(String outToClients){
		String command = outToClients.split(" ")[0];
		if(!launched){
			if(command.equals("join")){
				if (!joins.contains(outToClients)){
					joins.add(outToClients);
				}
				for (ServerThread serverThread : serverThreads){
					joins.forEach((jm) -> serverThread.writeToClients(jm));
				}
			}
			if(command.equals("ready")){
				if (!readys.contains(outToClients)){
					readys.add(outToClients);
				}
				for (ServerThread serverThread : serverThreads){
					readys.forEach((rm) -> serverThread.writeToClients(rm));
				}
			}
			if(readys.size() == serverThreads.size()){
				for (int i = 0; i < serverThreads.size(); i++) {
					serverThreads.get(i).writeToClients("launch " + spawns[i]);
				}

				for (ServerThread serverThread : serverThreads){
					serverThread.writeToClients("launch" + "\n");
				}
				launched = true;
			}
		}
		for (ServerThread serverThread : serverThreads){
				serverThread.writeToClients(outToClients);
		}


	}
}
