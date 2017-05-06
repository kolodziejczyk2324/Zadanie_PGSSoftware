package com.rental.lists.listElement;

import java.awt.Color;

public abstract class IListElement {
	
	protected String param[];
	protected int id;
	protected String name;
	
	public abstract Color getCellColor();
	public abstract Color getFontColor();
	
	public IListElement(String param[], String name) {
		this.param = param;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public String[] getParam() {
		return param;
	}
	
	public String[] getAtributesStringTable() {
		int visibleColumns[] = ListElementFactory.getVisibleColumns(name);
		String atributes[] = new String[visibleColumns.length];
		for(int i=0 ; i<visibleColumns.length ; i++)
			atributes[i] = param[visibleColumns[i]];
		return atributes;
	}
	
}
