package com.rental.actions;

import java.sql.SQLException;

import com.rental.MyDatabase.MyDatabase;

public class DeleteAction extends MyAbstractAction  {

	

	public DeleteAction(MyDatabase db, int id, String[] param) {
		super(db, id, param);
	}

	@Override
	public String execute() {
		try {
			db.deleteById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String executeOpposite() {
		if(param==null) return null;
		try {
			db.insertWithAllParameters(param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
