package com.rental.GUI.list.PressCell;

import com.rental.GUI.MainPanel;
import com.rental.MyDatabase.CarRentalDatabase;
import com.rental.MyDatabase.CustomerDatabase;
import com.rental.MyDatabase.RentDatabase;
import com.rental.lists.listElement.IListElement;

public class RentBackPress  extends AbstractPress {

	@Override
	protected String[] getAnswers() {
		String tab[] = {"Tak", "Nie"};
		return tab;
	}

	@Override
	protected String getDialogText(IListElement elem) {
		return "Czy jestes pewny ze klient " + CustomerDatabase.getInstance().getNameAndSurname(elem.getParam()[1]) + " oddal samochod " + CarRentalDatabase.getInstance().getManufacturerAndModel(Integer.parseInt(elem.getParam()[2])) + "?";
	}

	@Override
	protected void manageDecision(IListElement elem, MainPanel mainPanel, int decision) {
		if(decision==0)
			mainPanel.deleteFromDBByID(-1, elem.getParam());
	}

	@Override
	protected boolean doExecute(IListElement elem, MainPanel mainPanel) {
		if(elem.getParam()[5].equals("1")) return true;
		mainPanel.setInfoText("To wypozyczenie zostalo juz zakonczone");
		return false;
	}
}
