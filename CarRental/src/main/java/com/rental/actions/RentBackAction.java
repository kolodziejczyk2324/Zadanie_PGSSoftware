package com.rental.actions;

import java.sql.SQLException;

import com.rental.MyDatabase.CarRentalDatabase;
import com.rental.MyDatabase.CustomerDatabase;
import com.rental.MyDatabase.MyDatabase;
import com.rental.MyDatabase.RentDatabase;

public class RentBackAction extends MyAbstractAction {

	public RentBackAction(MyDatabase db, int id, String[] param) {
		super(db, id, param);
	}

	@Override
	public String execute() {
		CarRentalDatabase.getInstance().setRentCar(Integer.parseInt(param[2]), 0);
		RentDatabase.getInstance().setRent(Integer.parseInt(param[0]), 0);
		return null;
	}

	@Override
	public String executeOpposite() {
		try {
			if(!CustomerDatabase.getInstance().isCustomerInDB(param[1]))
				return "Nie ma takiego uzytkownika";
			if(!CarRentalDatabase.getInstance().isCarInDB(Integer.parseInt(param[2])))
				return "Nie ma takiego samochodu";
			CarRentalDatabase.getInstance().setRentCar(Integer.parseInt(param[2]), 1);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		RentDatabase.getInstance().setRent(Integer.parseInt(param[0]), 1);
		return null;
	}
	
}
