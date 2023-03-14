package Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TCPServer {

	private static ArrayList<ServerThread> serverThreads = new ArrayList<>();
	private static ArrayList<String> joins = new ArrayList<>();
	private static ArrayList<String> readys = new ArrayList<>();
	private static int maxSpillere = 2;

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

//	public static synchronized void updateClients1(String outToClients){
//		String temp = outToClients.split(" ")[0].equals("create") ? outToClients : null;
//		if(temp != null){
//			creates.add(temp);
//			if (creates.size() == maxSpillere) {
//				for (ServerThread serverThread : serverThreads){
//					serverThread.writeToClients(creates.get(serverThreads.indexOf(serverThread)));
//					for (String s : creates){
//						if(serverThreads.indexOf(serverThread) != creates.indexOf(s)){
//							serverThread.writeToClients(s);
//						}
//					}
//				}
//			}
//		} else {
//			for (ServerThread serverThread : serverThreads){
//				serverThread.writeToClients(outToClients);
//			}
//		}
//	}

	//TODO
	public static synchronized void updateClients(String outToClients){
		String command = outToClients.split(" ")[0];
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

	}
}
