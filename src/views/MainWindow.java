package views;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MainWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPMain jpMain;
	
	public MainWindow() {
		this.setTitle(ConstantsGUI.TITLE_JF_MAIN);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setIconImage(new ImageIcon(getClass().getResource(ConstantsGUI.URL_IMAGE_ICON)).getImage());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		init();
	}

	private void init() {
		jpMain = new JPMain();
		this.add(jpMain, BorderLayout.CENTER);
	}
}