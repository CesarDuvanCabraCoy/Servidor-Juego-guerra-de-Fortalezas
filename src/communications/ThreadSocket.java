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
	private Player player;
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
				Server.LOGGER.log(Level.INFO, "El cliente: " + connection.getInetAddress().getHostAddress() + " se desconectó");
				stop = true;
			}
		}
	}

	private void manageRequest(String petition) throws IOException {
		switch (Petitions.valueOf(petition)) {
		case C_CREATE_CLIENT:
			createClient();
			break;

		default:
			break;
		}
	}
	
	private void createClient() {
		try {
			int id = input.readInt();
			String name = input.readUTF();
			int x = input.readInt();
			int y = input.readInt();
			Server.LOGGER.log(Level.INFO, "El cliente: " + connection.getInetAddress().getHostAddress() + " se registro como jugador");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Socket getConnection() {
		return connection;
	}
}