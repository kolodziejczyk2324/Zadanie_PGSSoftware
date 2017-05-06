package com.rental.GUI.list.PressCell;

import com.rental.GUI.MainPanel;
import com.rental.lists.listElement.IListElement;

public class OnlyDeletePress extends AbstractPress {

	@Override
	protected String[] getAnswers() {
		String tab[] = {"Tak", "Nie"};
		return tab;
	}

	@Override
	protected String getDialogText(IListElement elem) {
		return "Czy jestes pewny ze chcesz usunac element o indeksie " + elem.getId() + "?";
	}

	@Override
	protected void manageDecision(IListElement elem, MainPanel mainPanel, int decision) {
		if(decision==0)
			mainPanel.deleteFromDBByID(elem.getId(), elem.getParam());
	}

	@Override
	protected boolean doExecute(IListElement elem, MainPanel mainPanel) {
		return true;
	}
	
}
