package com.rental.GUI.MyComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.rental.GUI.GUIParameters;
import com.rental.GUI.MainPanel;

public class MyTabbedPane extends JTabbedPane {

	private int curChecked = 0;
	
	public MyTabbedPane(String panelsName[], final MainPanel panels[]) {
		setFont(new Font("Helvetica", Font.ITALIC, GUIParameters.inputLabelFontSize));
		setBorder(BorderFactory.createEmptyBorder());
		Insets insets = UIManager.getInsets("TabbedPane.contentBorderInsets");
		insets.top = insets.bottom = insets.left = insets.right = -1;
		UIManager.put("TabbedPane.contentBorderInsets", insets);
		addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		    	panels[getSelectedIndex()].refreshList();;
		    }
		});
		for(int i=0 ; i<panels.length ; i++)
			add(panelsName[i], panels[i]);
	}	
}