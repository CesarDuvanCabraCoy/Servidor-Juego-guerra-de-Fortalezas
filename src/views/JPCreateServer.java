package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import controllers.JBActions;
import controllers.MainController;
import utils.Util;

public class JPCreateServer extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel jlInfo;
	private JLabel jlPort;
	private JSpinner jsPort;
	private JButton jbInitServer;
	private MainController mainController;
	
	public JPCreateServer(MainController mainController) {
		this.mainController = mainController;
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.WHITE);
		init();
	}

	private void init() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(20, 20, 10, 10);
		Util.generateBasicGrid(this, gbc);
		
		jlInfo = new JLabel(ConstantsGUI.JL_INFO_FIELDS);
		jlInfo.setFont(new Font(ConstantsGUI.TYPE_LETTER, Font.BOLD, 23));
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 8;
		this.add(jlInfo, gbc);
		
		jlPort = new JLabel(ConstantsGUI.JL_PORT);
		jlPort.setFont(new Font(ConstantsGUI.TYPE_LETTER, Font.BOLD, 18));
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridheight = 1;
		gbc.gridwidth = 4;
		this.add(jlPort, gbc);
		
		jsPort = new JSpinner();
		jsPort.setValue(8004);
		gbc.gridx = 5;
		gbc.gridy = 2;
		gbc.gridheight = 1;
		gbc.gridwidth = 5;
		this.add(jsPort, gbc);
		
		jbInitServer = new JButton(ConstantsGUI.JB_INIT_SERVER);
		jbInitServer.setActionCommand(JBActions.ACCEPT_INIT_SERVER.toString());
		jbInitServer.addActionListener(mainController);
		gbc.gridx = 4;
		gbc.gridy = 6;
		gbc.gridheight = 1;
		gbc.gridwidth = 3;
		this.add(jbInitServer, gbc);
	}
	
	public int getPort() {
		return (int) jsPort.getValue();
	}
}