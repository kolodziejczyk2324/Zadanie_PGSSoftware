package com.rental.GUI.undoredo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.rental.GUI.GUIParameters;
import com.rental.GUI.ImageManager;
import com.rental.GUI.MainPanel;
import com.rental.actions.MyAbstractAction;
import com.rental.actions.actionList.ListAction;

public class UndoRedoPanel extends JPanel {

	private ListAction listAction;
	private JButton undoButton, redoButton;
	
	public UndoRedoPanel(final MainPanel parent, final ListAction listAction) {
		setBackground(GUIParameters.lightGrey);
		
		this.listAction = listAction;
		
		JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		rowPanel.setOpaque(false);
		undoButton = createButton("undo.png", new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				MyAbstractAction action = listAction.movePrev();
				
				if(action != null) {
					String errorText = action.executeOpposite();
					if(errorText != null){
						parent.setInfoText(errorText);
						listAction.moveNext();
					}
					else {
						parent.refreshList();
						parent.setInfoText("Akcja cofnieta");
					}
				}
			}
		});
		redoButton = createButton("redo.png", new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				MyAbstractAction action = listAction.moveNext();
				if(action != null){
					String errorText = action.execute();
					if(errorText != null) {
						parent.setInfoText(errorText);
						listAction.movePrev();
					} else {
						parent.refreshList();
						parent.setInfoText("Wrociles do poprzedniego stanu");
					}
				}
			}
		});
		rowPanel.add(undoButton);
		rowPanel.add(redoButton);
		add(rowPanel);
	}
	
	public void updateEnabledButtons() {
		redoButton.setEnabled(listAction.isNext());
		undoButton.setEnabled(listAction.isPrev());
	}
	
	private JButton createButton(String imageName, ActionListener actionListener) {
		JButton newButton = new JButton(ImageManager.getImage("src/main/resources/"+imageName, 60, 60));
		newButton.setBackground(Color.WHITE);
		newButton.addActionListener(actionListener);
		newButton.setOpaque(false);
		newButton.setContentAreaFilled(false);
		newButton.setBorderPainted(false);
		return newButton;
	}
}
