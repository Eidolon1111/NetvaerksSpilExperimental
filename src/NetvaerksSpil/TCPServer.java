package NetvaerksSpil;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TCPServer {

	//TODO: Lav et map med create beskeder!
	private static ArrayList<ServerThread> threads = new ArrayList<>();
	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		ServerSocket welcomeSocket = new ServerSocket(6789);
		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			ServerThread serverThread = new ServerThread(connectionSocket);
			threads.add(serverThread);
			serverThread.start();
		}
	}

	public static ArrayList<ServerThread> getThreads(){
		return new ArrayList<>(threads);
	}
}
