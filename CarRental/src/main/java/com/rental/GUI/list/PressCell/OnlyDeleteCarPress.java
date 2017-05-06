package com.rental.GUI.list.PressCell;

import com.rental.GUI.MainPanel;
import com.rental.lists.listElement.IListElement;

public class OnlyDeleteCarPress extends OnlyDeletePress {

	@Override
	protected boolean doExecute(IListElement elem, MainPanel mainPanel) {
		if(elem.getParam()[3].equals("0")) return true;
		else mainPanel.setInfoText("Nie mozesz usunac wypozyczonego samochodu");
		return false;
	}
}
