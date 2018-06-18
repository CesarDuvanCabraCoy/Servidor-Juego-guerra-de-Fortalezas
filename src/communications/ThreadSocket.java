package communications;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;

public class ThreadSocket extends Thread {

	private Socket connection;
	private DataInputStream input;
	private DataOutputStream output;
	private boolean stop;
	private Server server;

	public ThreadSocket(Socket socket, Server server) throws IOException {
		this.server = server;
		this.connection = socket;
		input = new DataInputStream(socket.getInputStream());
		output = new DataOutputStream(socket.getOutputStream());
		start();
	}

	@Override
	public void run() {
		while (!stop) {
			String request;
			try {
				request = input.readUTF();
				if (request != null) {
					manageRequest(request);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void manageRequest(String request) throws IOException {
		
	}
	
	public Socket getConnection() {
		return connection;
	}
}