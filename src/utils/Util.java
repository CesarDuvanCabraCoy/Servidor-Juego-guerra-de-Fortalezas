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
	
}