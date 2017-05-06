package com.rental.GUI.list.PressCell;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.rental.GUI.MainFrame;
import com.rental.GUI.MainPanel;
import com.rental.MyDatabase.MyDatabase;
import com.rental.lists.listElement.IListElement;

public abstract class AbstractPress {
	
	protected abstract boolean doExecute(IListElement elem, MainPanel mainPanel);
	protected abstract String[] getAnswers();
	protected abstract String getDialogText(IListElement elem);
	protected abstract void manageDecision(IListElement elem, MainPanel mainPanel, int decision);
	
	public void execute(IListElement elem, MainPanel mainPanel){
		if(!doExecute(elem, mainPanel)) return;
		Object[] options = getAnswers();
		int decision = JOptionPane.showOptionDialog(MainFrame.getInstance(),
				getDialogText(elem),
			    "Question",
			    JOptionPane.YES_NO_CANCEL_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    null,
			    options,
			    null);
		manageDecision(elem, mainPanel, decision);
	}
}
