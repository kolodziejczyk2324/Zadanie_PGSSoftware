package com.rental.GUI.MyComponents;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MyLabel extends JLabel {

	public MyLabel(String text, int fontSize) {
		setText(text);
		setFont(new Font("Helvetica", Font.PLAIN, fontSize));
	}
	
	public MyLabel(ImageIcon image) {
		setIcon(image);
	}
	
}
