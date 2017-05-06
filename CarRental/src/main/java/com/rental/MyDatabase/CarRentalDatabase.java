package com.rental.MyDatabase;

import java.sql.SQLException;

import com.rental.lists.listElement.Car;
import com.rental.lists.listElement.IListElement;

public class CarRentalDatabase extends RentalDatabase {

	private static CarRentalDatabase instance = null;
	
	protected CarRentalDatabase() {}
	
	public static CarRentalDatabase getInstance() {
		if(instance == null)
			return new CarRentalDatabase();
		return instance;
	}
	

	@Override
	protected String getTableSQL() {
		return "CREATE TABLE IF NOT EXISTS Car ("
				+ "ID			INTEGER		PRIMARY KEY		AUTOINCREMENT,"
	            + "manufacturer TEXT		NOT NULL,"
	            + "model 		TEXT		NOT NULL,"
	            + "ISRENTED		INT2		DEFAULT 0)";
	}

	@Override
	protected String getTableName() {
		return "Car";
	}

	@Override
	protected String getElementListClassName() {
		return "Car";
	}
	
	@Override
	public String[] getMainColumnName() {
		String tab[] = {"manufacturer", "model"};
		return tab;
	}

	@Override
	protected String getIdColumnName() {
		return "ID";
	}
	
	public String getManufacturerAndModel(int id) {
		try {
			if(isInDBQuery("SELECT * FROM Car WHERE ID="+id)){
				String tab[] = getList("SELECT * FROM Car WHERE ID="+id).get(0).getParam();
				return tab[1] + " " + tab[2];
			}
		} catch (SQLException e){ 
			e.printStackTrace();
		}
		return null;
	}
	
	public void setRentCar(int id, int yes_or_no) {
		try {
			mySQLUpdate("UPDATE Car SET ISRENTED="+yes_or_no+" WHERE ID="+id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isCarInDB(int id) {
		try {
			return isInDBQuery("SELECT * FROM Car WHERE ID=" + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	protected boolean isDesc() {
		return false;
	}
}
