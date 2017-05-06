package com.rental.GUI.MyComponents;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MyButton extends JButton {

	public MyButton(String text, int fontSize, ActionListener actionListener) {
		setText(text);
		setFont(new Font("Helvetica", Font.PLAIN, fontSize));
		addActionListener(actionListener);
	}
	
}
