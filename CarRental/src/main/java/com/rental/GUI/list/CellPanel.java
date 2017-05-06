package com.rental.GUI.list;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.rental.GUI.GUIParameters;
import com.rental.GUI.MainFrame;
import com.rental.GUI.MainPanel;
import com.rental.GUI.MyComponents.MyLabel;
import com.rental.GUI.list.PressCell.AbstractPress;
import com.rental.lists.listElement.IListElement;

public class CellPanel extends JPanel {

	public CellPanel(final IListElement elem, final MainPanel mainPanel, final AbstractPress pressAction) {
		String elemStr[] = elem.getAtributesStringTable();
		setLayout(new GridLayout(1,elemStr.length));
		setBackground(elem.getCellColor());
		for(String e : elemStr) {
			MyLabel newLabel = new MyLabel(e, GUIParameters.cellFontSize);
			newLabel.setForeground(elem.getFontColor());
			newLabel.setHorizontalAlignment(MyLabel.CENTER);
			add(newLabel);
		}
		
		addMouseListener(new MouseAdapter(){
			@Override
            public void mousePressed(MouseEvent e) {
				pressAction.execute(elem, mainPanel);
            }
		});
	
	}
}
