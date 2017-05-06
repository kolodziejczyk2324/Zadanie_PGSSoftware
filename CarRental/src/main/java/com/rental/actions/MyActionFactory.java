package com.rental.actions;

import com.rental.MyDatabase.MyDatabase;

public class MyActionFactory {

	public static MyAbstractAction createAction(String criteria, MyDatabase db, int id, String param[]) {
		if(criteria=="INSERT")
			return new InsertAction(db, id, param);
		if(criteria=="DELETE")
			return new DeleteAction(db, id, param);
		if(criteria=="RENT")
			return new RentAction(db, id, param);
		if(criteria=="RENTBACK")
			return new RentBackAction(db, id, param);
		if(criteria=="INSERTCUSTOMER")
			return new InsertCustomerAction(db, id, param);
		if(criteria=="INSERTCAR")
			return new InsertCarAction(db, id, param);
		if(criteria=="DELETECUSTOMER")
			return new DeleteCustomerAction(db, id, param);
		if(criteria=="DELETECAR")
			return new DeleteCarAction(db, id, param);
		return null;
	}
	
}
