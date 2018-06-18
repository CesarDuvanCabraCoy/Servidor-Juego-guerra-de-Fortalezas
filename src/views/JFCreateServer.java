package views;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import controllers.MainController;

public class JFCreateServer extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPCreateServer jpCreateServer;
	private MainController mainController;
	
	public JFCreateServer(MainController mainController) {
		this.mainController = mainController;
		this.setTitle(ConstantsGUI.T_JF_LOGIN);
		this.setIconImage(new ImageIcon(getClass().getResource((ConstantsGUI.URL_IMAGE_ICON))).getImage());
		this.setSize(ConstantsGUI.T_JF_WIDTH, ConstantsGUI.T_JF_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		init();
		this.setVisible(true);
	}
	
	private void init() {
		jpCreateServer = new JPCreateServer(mainController);
		this.add(jpCreateServer, BorderLayout.CENTER);
	}
	
	public int getPort() {
		return jpCreateServer.getPort();
	}
}
