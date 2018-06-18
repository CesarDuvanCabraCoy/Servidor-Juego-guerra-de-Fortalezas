package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import communications.Server;
import views.JFCreateServer;
import views.MainWindow;

public class MainController implements ActionListener{

	private Server server;
	private JFCreateServer jfCreateServer;
	private MainWindow mainWindow;
	
	public MainController() {
		mainWindow = new MainWindow();
		jfCreateServer = new JFCreateServer(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		switch (JBActions.valueOf(event.getActionCommand())) {
		case ACCEPT_INIT_SERVER:
			initServer();
			break;
		default:
			break;
		}
	}

	private void initServer() {
		try {
			server = new Server(jfCreateServer.getPort());
			jfCreateServer.setVisible(false);
			mainWindow.setVisible(true);
		} catch (IOException e) {
			Server.LOGGER.log(Level.SEVERE, e.getMessage());
		}
	}	
}