package com.rental.GUI.search;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.rental.GUI.GUIParameters;
import com.rental.GUI.ImageManager;
import com.rental.GUI.MainPanel;
import com.rental.GUI.MyComponents.MyLabel;
import com.rental.GUI.MyComponents.MyTextField;
import com.rental.GUI.list.ListPanel;

public class SearchPanel extends JPanel {
	
	private MyTextField textField;
	
	public SearchPanel(MainPanel parent) {
		this.setOpaque(true);
		this.setBackground(GUIParameters.darkGrey);
		
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		add(new MyLabel(ImageManager.getImage("src/main/resources/magnifier.png", GUIParameters.magnifierSize, GUIParameters.magnifierSize)));
		textField = createTextField(parent);
		add(textField);
	}
	
	private MyTextField createTextField(final MainPanel parent){
		final MyTextField textField = new MyTextField();
		textField.setPreferredSize(new Dimension((int)(0.3*GUIParameters.frameWidth),GUIParameters.textFieldHeight));
		textField.getDocument().addDocumentListener(new DocumentListener(){

			public void insertUpdate(DocumentEvent e) {
				findPattern(parent);
			}

			public void removeUpdate(DocumentEvent e) {
				findPattern(parent);
			}

			public void changedUpdate(DocumentEvent e) {}
		});
		
		return textField;
	}
	
	private void findPattern(MainPanel parent) {
		try {
			parent.updateList(parent.getDB().findPattern(textField.getText()));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void clearInput() {
		textField.setText("");
	}
	
}
