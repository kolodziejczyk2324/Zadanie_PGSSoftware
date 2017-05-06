package com.rental.actions;

import java.sql.SQLException;

import com.rental.MyDatabase.MyDatabase;
import com.rental.MyDatabase.RentDatabase;

public class InsertCustomerAction extends InsertAction{

	public InsertCustomerAction(MyDatabase db, int id, String[] param) {
		super(db, id, param);
	}

	@Override
	public String executeOpposite() {
		if(id==-1) return null;
		if(RentDatabase.getInstance().customerHaveSomething(param[2]))
			return "Ten klient ma wypozyczony samochod";
		try {
			db.deleteById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
