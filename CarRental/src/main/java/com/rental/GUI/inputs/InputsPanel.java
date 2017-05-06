package com.rental.GUI.inputs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.JTextField;

import com.rental.GUI.GUIParameters;
import com.rental.GUI.MainPanel;
import com.rental.GUI.MyComponents.MyButton;
import com.rental.GUI.MyComponents.MyLabel;
import com.rental.GUI.MyComponents.MyTextField;
import com.rental.GUI.inputs.inputsCheckers.AbstractCheck;
import com.rental.GUI.list.ListPanel;

public class InputsPanel extends JPanel {
	
	MyTextField textField[];
	
	public InputsPanel(String titles[], MainPanel parent, AbstractCheck checkInput) {
		setBackground(GUIParameters.middleGrey);
		setLayout(new BorderLayout());
		setRows(titles);
		setButton(parent, checkInput);
	}
	
	private void setRows(String titles[]){
		textField = new MyTextField[titles.length];
		JPanel inpPanel = new JPanel();
		inpPanel.setOpaque(false);
		inpPanel.setLayout(new GridLayout(titles.length,2, GUIParameters.inputsMargin, GUIParameters.inputsMargin));
		for(int i=0 ; i<titles.length ; i++)
			inpPanel.add(createRow(titles[i], i));
		add(inpPanel, BorderLayout.CENTER);
	}
	
	private void setButton(final MainPanel parent, final AbstractCheck checkInput){
		JPanel butPanel = new JPanel();
		butPanel.setOpaque(false);
		MyButton button = new MyButton("OK", GUIParameters.inputLabelFontSize, 
				new ActionListener() { 
			  		public void actionPerformed(ActionEvent e) {
			  			AbstractCheck.CheckCommunicat checkCommunicat = checkInput.check(textField, parent);
			  			if(checkCommunicat.isCorrect) {
				  			String tab[] = new String[textField.length];
				  			for(int i=0 ; i<textField.length ; i++)
				  				tab[i] = textField[i].getText();
				  			parent.insertToDB(-1, tab);
							clearTextFields();
			  			} else
			  				parent.setInfoText(checkCommunicat.infoText);
			  		}
				});
		button.setBackground(GUIParameters.lightGrey);
		butPanel.add(button);
		add(butPanel, BorderLayout.SOUTH);
	}
	
	private void clearTextFields(){
		for(JTextField t : textField)
			t.setText("");
	}
	
	private JPanel createRow(String title, int i) {
		title = title.replace('_', ' ');
		JPanel rowPanel = new  JPanel(new FlowLayout(FlowLayout.RIGHT));
		rowPanel.setOpaque(false);
		textField[i] = new MyTextField();
		textField[i].setPreferredSize(new Dimension((int)(0.5*GUIParameters.frameWidth),GUIParameters.textFieldHeight));
		rowPanel.add(new MyLabel(title, GUIParameters.inputLabelFontSize));
		rowPanel.add(textField[i]);
		return rowPanel;
	}

}
