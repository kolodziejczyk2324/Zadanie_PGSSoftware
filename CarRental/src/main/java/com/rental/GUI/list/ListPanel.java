package com.rental.GUI.list;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.rental.GUI.GUIParameters;
import com.rental.GUI.MainPanel;
import com.rental.GUI.list.PressCell.AbstractPress;
import com.rental.MyDatabase.MyDatabase;
import com.rental.lists.listElement.IListElement;

public class ListPanel extends JPanel {
	
	private JPanel panel = new JPanel();
	private JScrollPane scroll;
	private AbstractPress pressAction;
	
	public ListPanel(AbstractPress pressAction) {
		this.pressAction = pressAction;
		panel.setBackground(GUIParameters.middleGrey);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		scroll = new JScrollPane(panel);
		scroll.setBorder(BorderFactory.createEmptyBorder());
		this.setLayout(new BorderLayout());
		this.add(scroll,BorderLayout.CENTER);
	}
	
	public void refreshList(ArrayList<IListElement> list, MainPanel mainPanel) {
		panel.removeAll();
		for(int i=0 ; i<list.size() ; i++ ){
			panel.add(new CellPanel(list.get(i), mainPanel, pressAction));
			if(i!=list.size()-1)
				panel.add(Box.createRigidArea(new Dimension(0, GUIParameters.heightBetweenCells)));
		}
		scroll.revalidate();
		scroll.repaint();
	}
}
