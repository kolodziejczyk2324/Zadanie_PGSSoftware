package com.rental.GUI.list.PressCell;

import com.rental.GUI.MainPanel;
import com.rental.MyDatabase.RentDatabase;
import com.rental.lists.listElement.IListElement;

public class OnlyDeleteCustomerPress extends OnlyDeletePress {

	@Override
	protected String getDialogText(IListElement elem) {
		return "Czy jestes pewny ze chcesz usunac klienta posiadajacego numer PESEL " + elem.getParam()[3] + "?";
	}
	
	@Override
	protected boolean doExecute(IListElement elem, MainPanel mainPanel) {
		if(!RentDatabase.getInstance().customerHaveSomething(elem.getParam()[3])) return true;
		else mainPanel.setInfoText("Nie mozesz usunac klienta ktory ma wypozyczony samochod");
		return false;
	}
}
