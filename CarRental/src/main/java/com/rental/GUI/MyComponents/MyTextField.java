package com.rental.GUI.MyComponents;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import com.rental.GUI.GUIParameters;

public class MyTextField extends JTextField{
	
	public MyTextField(String text) {
		setText(text);
		setFont(new Font("Helvetica", Font.PLAIN, GUIParameters.inputLabelFontSize));
	}
	
	public MyTextField() {
		setFont(new Font("Helvetica", Font.PLAIN, GUIParameters.inputLabelFontSize));
	}

}
