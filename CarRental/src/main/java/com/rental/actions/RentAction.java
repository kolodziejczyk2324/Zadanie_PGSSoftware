package com.rental.actions;

import java.sql.SQLException;

import com.rental.MyDatabase.CarRentalDatabase;
import com.rental.MyDatabase.CustomerDatabase;
import com.rental.MyDatabase.MyDatabase;

public class RentAction extends MyAbstractAction {

	public RentAction(MyDatabase db, int id, String[] param) {
		super(db, id, param);
	}

	@Override
	public String execute() {
		try {
			if(!CustomerDatabase.getInstance().isCustomerInDB(param[0]))
				return "Uzytkownika o PESELU "+param[0]+" juz nie ma w bazie";
			if(!CarRentalDatabase.getInstance().isCarInDB(Integer.parseInt(param[1])))
				return "Nie ma takiego samochodu w bazie";
			if(id==-1)
				id = db.insert(param);
			else
				db.insert(id, param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		CarRentalDatabase.getInstance().setRentCar(Integer.parseInt(param[1]), 1);
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
		CarRentalDatabase.getInstance().setRentCar(Integer.parseInt(param[1]), 0);
		return null;
	}

}
