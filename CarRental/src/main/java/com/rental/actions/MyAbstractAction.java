package com.rental.actions;

import com.rental.MyDatabase.MyDatabase;

public abstract class MyAbstractAction {

	protected MyDatabase db;
	protected String param[];
	protected int id;
	
	public abstract String execute();
	public abstract String executeOpposite();
	
	public MyAbstractAction(MyDatabase db, int id, String param[]) {
		this.db = db;
		this.param = param;
		this.id = id;
	}
	
}
