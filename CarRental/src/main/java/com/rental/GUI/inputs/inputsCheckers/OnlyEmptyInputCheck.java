package com.rental.GUI.inputs.inputsCheckers;

import javax.swing.JTextField;

import com.rental.GUI.MainPanel;

public class OnlyEmptyInputCheck extends AbstractCheck {

	@Override
	public CheckCommunicat check(JTextField[] textField, MainPanel parent) {
		if(isEmptyInput(textField)) return new CheckCommunicat(false, "Wypelnij wszystkie pola!");
		return new CheckCommunicat(true, "Poprawne dodanie do bazy danych");
	}

}
