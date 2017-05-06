package com.rental.lists.listElement;

import java.awt.Color;

import com.rental.GUI.GUIParameters;

public class Customer extends IListElement {
	
	public Customer(String param[], String name) {
		super(param, name);
		id = Integer.parseInt(param[0]);
	}

	@Override
	public Color getCellColor() {
		return GUIParameters.lightGrey;
	}
	
	@Override
	public Color getFontColor() {
		return Color.BLACK;
	}
	
}
