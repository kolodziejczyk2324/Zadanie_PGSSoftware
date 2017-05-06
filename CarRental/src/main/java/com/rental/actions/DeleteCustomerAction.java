package com.rental.actions;

import java.sql.SQLException;

import com.rental.MyDatabase.MyDatabase;
import com.rental.MyDatabase.RentDatabase;

public class DeleteCustomerAction extends DeleteAction {

	public DeleteCustomerAction(MyDatabase db, int id, String[] param) {
		super(db, id, param);
	}
	
	@Override
	public String execute() {
		try {
			if(RentDatabase.getInstance().customerHaveSomething(param[3]))
				return "Klient " + param[3] + " posiada wypo¿yczony samochód.";
			db.deleteById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
