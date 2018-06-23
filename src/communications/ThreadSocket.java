package communications;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import models.Player;
import utils.Util;

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
				Server.LOGGER.log(Level.INFO, "El cliente: " + connection.getInetAddress().getHostAddress() + " se desconectó");
				stop = true;
			}
		}
	}

	private void manageRequest(String petition) throws IOException {
		switch (Request.valueOf(petition)) {
		case C_CREATE_CLIENT:
			createClient();
			break;

		default:
			break;
		}
	}
	
	private void createClient() {
		try {
			int id = server.getPlayersSize();
			String name = input.readUTF();
			int avatar = input.readInt();
			int fort = input.readInt();
			int[] pos = Util.getPositionByNumPlayers(server.getPlayersSize());
			int x = pos[0];
			int y = pos[1];
			sendPositions(id, x, y);
			server.addPlayer(id, name, avatar, fort, x, y);
			server.sendPlayersToAll();
			Server.LOGGER.log(Level.INFO, "El cliente: " + connection.getInetAddress().getHostAddress() + " se registro como jugador");
		} catch (IOException e) {
			Server.LOGGER.log(Level.INFO, e.getMessage());
		} catch (ParserConfigurationException e) {
			Server.LOGGER.log(Level.INFO, e.getMessage());
		} catch (TransformerFactoryConfigurationError e) {
			Server.LOGGER.log(Level.INFO, e.getMessage());
		} catch (TransformerException e) {
			Server.LOGGER.log(Level.INFO, e.getMessage());
		}
	}

	private void sendPositions(int id, int x, int y) throws IOException {
		output.writeUTF(Response.S_SEND_POS_PLAYER.name());
		output.writeInt(id);
		output.writeInt(x);
		output.writeInt(y);
	}

	public void sendPlayers(ArrayList<Player> players) {
		try {
			if (players.size() == 4) {
				output.writeUTF(Response.S_SEND_PLAYERS.name());
				sendFile();
			}
		} catch (IOException e) {
			Server.LOGGER.log(Level.INFO, e.getMessage());
		}
	}
	
	private void sendFile() {
		try {
			File file = new File("src" + System.getProperty("file.separator")+ "perssistence" + System.getProperty("file.separator") +
					"game.xml");
			FileInputStream ficheroStream = new FileInputStream(file);
			byte content[] = new byte[(int)file.length()];
			ficheroStream.read(content);
			output.writeInt(content.length);
			output.flush();
			output.write(content);
			output.flush();
			System.out.println("Se envio el archivo");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Socket getConnection() {
		return connection;
	}
}