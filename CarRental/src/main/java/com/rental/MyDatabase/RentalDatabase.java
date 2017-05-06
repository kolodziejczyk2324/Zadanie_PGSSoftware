package com.rental.MyDatabase;

public abstract class RentalDatabase extends MyDatabase {

	@Override
	protected String getURL() {
		return "jdbc:sqlite:" + System.getProperty("user.dir") + "/" + "database.db";
	}

	@Override
	protected abstract String getTableSQL();

	@Override
	protected abstract String getTableName();
	
}
