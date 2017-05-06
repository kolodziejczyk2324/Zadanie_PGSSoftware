package com.rental.MyDatabase;

import java.sql.SQLException;

public class RentDatabase extends RentalDatabase {

	private static RentDatabase instance = null;
	
	protected RentDatabase() {}
	
	public static RentDatabase getInstance() {
		if(instance == null)
			return new RentDatabase();
		return instance;
	}
	
	@Override
	protected String getTableSQL() {
		return "CREATE TABLE IF NOT EXISTS Rent ("
				+ "ID			INTEGER		PRIMARY KEY		AUTOINCREMENT,"
	            + "PESEL 		TEXT		NOT NULL,"
	            + "car_ID 		TEXT		NOT NULL,"
	            + "HireDate 	DATE DEFAULT (datetime('now','localtime')),"
	            + "How_many_days	INTEGER	NOT NULL,"
	            + "ISRENTED		INT2		DEFAULT 1)";
	}

	@Override
	protected String getTableName() {
		return "Rent";
	}

	@Override
	protected String getIdColumnName() {
		return "ID";
	}

	@Override
	protected String getElementListClassName() {
		return "Rent";
	}

	@Override
	public String[] getMainColumnName() {
		String tab[] = {"PESEL", "car_ID", "How_many_days"};
		return tab;
	}
	
	public void setRent(int id, int yes_or_no) {
		try {
			mySQLUpdate("UPDATE Rent SET ISRENTED="+yes_or_no+" WHERE ID="+id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setRentByPESEL(int PESEL, int yes_or_no) {
		try {
			mySQLUpdate("UPDATE Rent SET ISRENTED="+yes_or_no+" WHERE PESEL="+PESEL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean customerHaveSomething(String PESEL){
		try {
			return isInDBQuery("SELECT * FROM Rent WHERE PESEL='"+PESEL+"' AND ISRENTED=1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean someCustomerHaveThisCar(int id) {
		try {
			return isInDBQuery("SELECT * FROM Rent WHERE car_ID=" + id + " AND ISRENTED=1");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	protected boolean isDesc() {
		return true;
	}
	
}
