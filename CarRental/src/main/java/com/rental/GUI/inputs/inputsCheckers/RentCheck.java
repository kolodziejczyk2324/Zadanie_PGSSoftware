package com.rental.GUI.inputs.inputsCheckers;

import java.sql.SQLException;

import javax.swing.JTextField;

import com.rental.GUI.MainPanel;
import com.rental.GUI.inputs.inputsCheckers.AbstractCheck.CheckCommunicat;

public class RentCheck extends CustomerCheck {

	@Override
	public CheckCommunicat check(JTextField[] textField, MainPanel parent) {
		if(isEmptyInput(textField)) return new CheckCommunicat(false, "Wypelnij wszystkie pola!");
		if(!isPESEL(textField[0].getText())) return new CheckCommunicat(false, "Wpisz poprawny numer PESEL!");
		if(!isCustomerWithThisPESEL(textField[0].getText(), parent))
			return new CheckCommunicat(false, "Uzytkownik z takim numerem PESEL nie istnieje!");
		if(!isNumber(textField[1].getText()))
			return new CheckCommunicat(false, "Wpisz poprawny identyfikator samochodu!");
		if(!isCarWithThisId(textField[1].getText(), parent))
			return new CheckCommunicat(false, "Nie istnieje taki samochod!");
		if(isCarRented(textField[1].getText(), parent))
			return new CheckCommunicat(false, "Ten samochod jest aktualnie wypozyczony");
		if(!isPositiveNumber(textField[2].getText()))
			return new CheckCommunicat(false, "Wstaw poprawna dlugosc wypozyczenia");
		return new CheckCommunicat(true, "Poprawne dodanie do bazy danych");
	}
	
	protected boolean isCarRented(String id, MainPanel parent){
		try {
			return parent.getDB().isInDBQuery("SELECT * FROM Car WHERE ID="+id+" AND ISRENTED=1");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	protected boolean isCarWithThisId(String id, MainPanel parent) {
		try {
			return parent.getDB().isInDBQuery("SELECT * FROM Car WHERE ID="+id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
