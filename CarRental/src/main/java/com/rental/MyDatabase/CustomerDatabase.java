package com.rental.MyDatabase;

import java.sql.SQLException;

public class CustomerDatabase extends RentalDatabase {

	private static CustomerDatabase instance = null;
	
	protected CustomerDatabase() {}
	
	public static CustomerDatabase getInstance() {
		if(instance == null)
			return new CustomerDatabase();
		return instance;
	}
	
	@Override
	protected String getTableSQL() {
		return "CREATE TABLE IF NOT EXISTS Customer ("
				+ "ID			INTEGER		PRIMARY KEY		AUTOINCREMENT,"
	            + "name 		TEXT		NOT NULL,"
	            + "surname 		TEXT		NOT NULL,"
	            + "PESEL		TEXT		NOT NULL)";
	}

	@Override
	protected String getTableName() {
		return "Customer";
	}

	@Override
	protected String getIdColumnName() {
		return "ID";
	}

	@Override
	protected String getElementListClassName() {
		return "Customer";
	}

	@Override
	public String[] getMainColumnName() {
		String tab[] = {"name", "surname", "PESEL"};
		return tab;
	}

	public String getNameAndSurname(String PESEL) {
		try {
			if(isInDBQuery("SELECT * FROM Customer WHERE PESEL='"+PESEL+"'")){
				String tab[] = getList("SELECT * FROM Customer WHERE PESEL='"+PESEL+"'").get(0).getParam();
				return tab[1] + " " + tab[2];
			}
		} catch (SQLException e){ 
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean isCustomerInDB(String PESEL) {
		try {
			return isInDBQuery("SELECT * FROM Customer WHERE PESEL='" + PESEL + "'");
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
