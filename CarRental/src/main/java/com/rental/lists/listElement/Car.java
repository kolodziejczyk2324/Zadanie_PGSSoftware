package com.rental.lists.listElement;

import java.awt.Color;

import com.rental.GUI.GUIParameters;

public class Car extends IListElement {

	public Car(String param[], String name) {
		super(param, name);
		id = Integer.parseInt(param[0]);
	}

	@Override
	public Color getCellColor() {
		if(param[3].equals("1"))
			return Color.BLACK;
		return GUIParameters.lightGrey;
	}

	@Override
	public Color getFontColor() {
		if(param[3].equals("1"))
			return Color.WHITE;
		return Color.BLACK;
	}
}
