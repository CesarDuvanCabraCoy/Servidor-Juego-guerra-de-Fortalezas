package communications;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import models.Player;
import perssistence.FileManager;

public class Server extends Thread {

	private ServerSocket server;
	private int port;
	private boolean stop;
	public final static Logger LOGGER = Logger.getGlobal();
	private FileManager fileManager;
	private ArrayList<ThreadSocket> connections;
	private ArrayList<Player> players;

	public Server(int port) throws IOException {
		fileManager = new FileManager();
		connections = new ArrayList<>();
		this.port = port;
		server = new ServerSocket(port);
		start();
		players = new ArrayList<Player>();
		LOGGER.log(Level.INFO, "Servidor inicado en puerto: " + this.port);
		JOptionPane.showMessageDialog(null, "Servidor corriendo");
	}

	@Override
	public void run() {
		while (!stop) {
			Socket connection;
			try {
				connection = server.accept();
				connections.add(new ThreadSocket(connection, this));
				LOGGER.log(Level.INFO, "Conexion aceptada: " + connection.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addPlayer(int id, String name, int avatar, int fort, int x, int y) throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		players.add(new Player(id, name, avatar, fort, x, y));
		writeGame();
	}
	
	public void writeGame() throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		if (players.size() == 4) {
			fileManager.writeGame(players);
		}
	}
	
	public void sendPlayersToAll() {
		if (players.size() == 4) {
			for (int i = 0; i < players.size(); i++) {
				connections.get(i).sendPlayers(players);
			}			
		}
	}
	
	public int getPlayersSize() {
		return players.size();
	}
}