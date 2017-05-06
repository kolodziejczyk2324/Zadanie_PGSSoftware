package com.rental.MyDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.rental.lists.listElement.IListElement;
import com.rental.lists.listElement.ListElementFactory;

public abstract class MyDatabase {
	
	protected abstract String getURL();
	protected abstract String getTableSQL();
	protected abstract String getTableName();
	protected abstract String getIdColumnName();
	protected abstract String getElementListClassName();
	protected abstract boolean isDesc();
	public abstract String[] getMainColumnName();
	
	private Connection con = null;
	
	private Statement getStatement() throws SQLException {
		if(con == null)
			con = DriverManager.getConnection(getURL());
		Statement stmt = con.createStatement();
		stmt.executeUpdate(getTableSQL());
		return stmt;
	}
	
	public int insert(String tab[]) throws SQLException{
		String query = "INSERT INTO " + getTableName() +" (";
		String column[] = getMainColumnName();
		for(int i=0 ; i<column.length ; i++)
			query += column[i] + (i!=column.length-1 ? ", " : "");
		query += ") VALUES (";
		for(int i=0 ; i<tab.length ; i++)
			query += "'" + tab[i] + "'" + (i!=tab.length-1 ? ", " : ")");
		mySQLUpdate(query);
		return getLastElementId(tab);
	}
	
	public void insert(int id, String tab[]) throws SQLException{
		String query = "INSERT INTO " + getTableName() +" (" + getIdColumnName() + ", ";
		String column[] = getMainColumnName();
		for(int i=0 ; i<column.length ; i++)
			query += column[i] + (i!=column.length-1 ? ", " : "");
		query += ") VALUES (" + id + ", ";
		for(int i=0 ; i<tab.length ; i++)
			query += "'" + tab[i] + "'" + (i!=tab.length-1 ? ", " : ")");
		mySQLUpdate(query);
	}
	
	public void insertWithAllParameters(String tab[]) throws SQLException {
		String query = "INSERT INTO " + getTableName() +" (";
		ArrayList<String> column = getColumnsName();
		for(int i=0 ; i<column.size() ; i++)
			query += column.get(i) + (i!=column.size()-1 ? ", " : "");
		query += ") VALUES (";
		for(int i=0 ; i<tab.length ; i++)
			query += "'" + tab[i] + "'" + (i!=tab.length-1 ? ", " : ")");
		mySQLUpdate(query);
	}
	
	public void deleteByAtributes(String tab[]) throws SQLException {
		String query = "DELETE FROM " + getTableName() + " WHERE ";
		String column[] = getMainColumnName();
		for(int i=0 ; i<tab.length ; i++)
			query += column[i] + "='" + tab[i] + "'" + (i!=tab.length-1 ? " AND " : "");
		mySQLUpdate(query);
	}
	
	public void deleteById(int id) throws SQLException {
		String query = "DELETE FROM " + getTableName() + " WHERE " + getIdColumnName() + "="+id;
		mySQLUpdate(query);
	}
	
	public boolean isInDatabase(String tab[]) throws SQLException {
		String query = "SELECT * FROM " + getTableName() + " WHERE ";
		String column[] = getMainColumnName();
		for(int i=0 ; i<tab.length ; i++)
			query += column[i] + "='" + tab[i] + "'" + (i!=tab.length-1 ? " AND " : "");
		return isInDBQuery(query);
	}
	
	public void mySQLUpdate(String query) throws SQLException {
		Statement stmt = getStatement();
		stmt.executeUpdate(query);
		stmt.close();
	}
	
	protected ArrayList<IListElement> getList(String query) throws SQLException {
		Statement stmt = getStatement();
		ArrayList<IListElement> list = new ArrayList<IListElement>();
		ResultSet rs = stmt.executeQuery(query);
		int numberOfColumns = rs.getMetaData().getColumnCount();
		while ( rs.next() ) {
			String param[] = new String[numberOfColumns];
			for(int i=1 ; i<=numberOfColumns ; i++)
				param[i-1] = rs.getString(i);
			list.add(ListElementFactory.getListElement(getElementListClassName(), param));
		}
		stmt.close();
		return list;
	}
	
	public IListElement getElementById(int id) throws SQLException{
		return getList("SELECT * FROM "+ getTableName() + " WHERE " + getIdColumnName() + "="+id).get(0);
	}
	
	public ArrayList<IListElement> getAllList() throws SQLException {
		return getList("SELECT * FROM " + getTableName() + " ORDER BY " + getIdColumnName() + (isDesc() ? " DESC" : ""));
	}
	
	public void printAllTable() throws SQLException {
		Statement stmt;
		stmt = getStatement();
		ResultSet resultSet = stmt.executeQuery("SELECT * FROM " + getTableName() + " ORDER BY " + getIdColumnName() + (isDesc() ? " DESC" : ""));
		int numberOfColumns = resultSet.getMetaData().getColumnCount();
		while ( resultSet.next() ) {
			for(int i=1 ; i<=numberOfColumns ; i++)
				System.out.print(resultSet.getString(i) + "\t");
			System.out.println();
		}
		stmt.close();
	}
	
	public boolean isInDBQuery(String query) throws SQLException {
		Statement stmt;
		stmt = getStatement();
		boolean ret = stmt.executeQuery(query).next();
		stmt.close();
		return ret;
	}
	
	public ArrayList<IListElement> findPattern(String pattern) throws SQLException {
		Statement stat = getStatement();
		String word[] = pattern.split(" ");
		String query = "SELECT * FROM " + getTableName() + " WHERE ";
		int visibleColumns[] = ListElementFactory.getVisibleColumns(getTableName());
		for(int i=0 ; i<word.length ; i++){
			ResultSet columns = stat.executeQuery("PRAGMA table_info(" + getTableName() + ")");
			query += "(";
			int curCol = 0; int iter = 0;
			while(columns.next() && iter!=visibleColumns.length) {
				if(curCol++ == visibleColumns[iter]) {
					iter++;
					query += columns.getString(2) + " LIKE '%" + word[i] + "%' OR ";
				}
			}
			query = query.substring(0, query.length() - 4);
			query += ")";
			if(i!=word.length-1) query += " AND ";
		}
		query += " ORDER BY " + getIdColumnName() + (isDesc() ? " DESC" : "");
		stat.close();
		return getList(query);
	}
	
	private int getLastElementId(String tab[]) throws SQLException{
		String query = 	"SELECT * FROM " + getTableName() + " WHERE " + getIdColumnName()
				+ "= (SELECT MAX(" + getIdColumnName() + ") FROM " + getTableName() + " WHERE ";
		String column[] = getMainColumnName();
		for(int i=0 ; i<tab.length ; i++)
			query += column[i] + "='" + tab[i] + "'" + (i!=tab.length-1 ? " AND " : ")");
		return getList(query).get(0).getId();
	}
	
	private ArrayList<String> getColumnsName() {
		ArrayList<String> list = new ArrayList<String>();
		try {
			Statement stat = getStatement();
			ResultSet columns = stat.executeQuery("PRAGMA table_info(" + getTableName() + ")");
			while(columns.next())
				list.add(columns.getString(2));
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}

