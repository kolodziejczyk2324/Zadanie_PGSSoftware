package com.rental.actions;

import java.sql.SQLException;

import com.rental.MyDatabase.MyDatabase;
import com.rental.MyDatabase.RentDatabase;

public class InsertCarAction extends InsertAction {

	public InsertCarAction(MyDatabase db, int id, String[] param) {
		super(db, id, param);
	}
	
	@Override
	public String executeOpposite() {
		if(id==-1) return null;
		if(RentDatabase.getInstance().someCustomerHaveThisCar(id))
			return "Ten samochod jest wypozyczony";
		try {
			db.deleteById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
