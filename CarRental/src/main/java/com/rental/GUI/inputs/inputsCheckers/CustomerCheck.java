package com.rental.GUI.inputs.inputsCheckers;

import java.sql.SQLException;

import javax.swing.JTextField;

import com.rental.GUI.MainPanel;
import com.rental.GUI.inputs.inputsCheckers.AbstractCheck.CheckCommunicat;

public class CustomerCheck extends AbstractCheck {

	@Override
	public CheckCommunicat check(JTextField[] textField, MainPanel parent) {
		if(isEmptyInput(textField)) return new CheckCommunicat(false, "Wypelnij wszystkie pola!");
		if(!isPESEL(textField[2].getText())) return new CheckCommunicat(false, "Wpisz poprawny numer PESEL!");
		if(isCustomerWithThisPESEL(textField[2].getText(), parent))
			return new CheckCommunicat(false, "Uzytkownik z takim numerem PESEL juz istnieje!");
		return new CheckCommunicat(true, "Poprawne dodanie do bazy danych");
	}
	
	protected boolean isCustomerWithThisPESEL(String PESEL, MainPanel parent) {
		try {
			return parent.getDB().isInDBQuery("SELECT * FROM Customer WHERE PESEL='"+PESEL+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	protected boolean isPESEL(String str) {
		return str.length()==11 && isPositiveNumber(str);
	}

}
