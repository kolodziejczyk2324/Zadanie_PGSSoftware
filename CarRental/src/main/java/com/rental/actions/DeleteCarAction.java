package com.rental.actions;

import java.sql.SQLException;

import com.rental.MyDatabase.MyDatabase;
import com.rental.MyDatabase.RentDatabase;

public class DeleteCarAction extends DeleteAction {

	public DeleteCarAction(MyDatabase db, int id, String[] param) {
		super(db, id, param);
	}
	
	@Override
	public String execute() {
		try {
			if(RentDatabase.getInstance().someCustomerHaveThisCar(id))
				return "Samochod o indeksie " + id + " jest aktualnie wypo¿yczony.";
			db.deleteById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
