package com.rental.actions;

import java.sql.SQLException;

import com.rental.MyDatabase.CarRentalDatabase;
import com.rental.MyDatabase.MyDatabase;
import com.rental.MyDatabase.RentDatabase;

public class InsertAction extends MyAbstractAction {
	
	public InsertAction(MyDatabase db, int id, String[] param) {
		super(db, id, param);
		id = -1;
	}

	@Override
	public String execute() {
		try {
			if(id==-1)
				id = db.insert(param);
			else
				db.insert(id, param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String executeOpposite() {
		if(id==-1) return null;
		try {
			db.deleteById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
}
