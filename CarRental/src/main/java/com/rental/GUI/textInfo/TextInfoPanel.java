package com.rental.GUI.textInfo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.rental.GUI.GUIParameters;
import com.rental.GUI.MyComponents.MyLabel;

public class TextInfoPanel extends JPanel {

	MyLabel infoLabel;
	
	public TextInfoPanel() {
		this.setOpaque(true);
		this.setBackground(GUIParameters.darkGrey);
		
		infoLabel = new MyLabel(" ", 30);
		add(infoLabel, BorderLayout.CENTER);
	}
	
	public void setText(String text) {
		infoLabel.setText(text);
	}
	
}
