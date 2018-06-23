package utils;

import java.awt.Container;
import java.awt.GridBagConstraints;

import javax.swing.JLabel;

import views.ConstantsGUI;

public class Util {

	public static void generateBasicGrid(Container comp, GridBagConstraints gbc){
		gbc.weightx = 1;
		for (int i = 0; i < ConstantsGUI.COLUMNS_NUMBER; i++) {
			gbc.gridx = i;
			comp.add(new JLabel(""), gbc);
		}
	}
	
	public static int[] getPositionByNumPlayers(int numPlayers) {
		int[] pos = new int[2];
		switch (numPlayers) {
		case 0:
			pos[0] = 70;
			pos[1] = 50;
			break;
		case 1:
			pos[0] = 1500;
			pos[1] = 50;
			break;
		case 2:
			pos[0] = 70;
			pos[1] = 800;
			break;
		case 3:
			pos[0] = 1500;
			pos[1] = 800;
			break;
		}
		return pos;
	}
	
}