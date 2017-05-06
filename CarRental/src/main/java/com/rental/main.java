package com.rental;

import java.sql.*;
import java.util.ArrayList;

import com.rental.GUI.MainFrame;
import com.rental.GUI.MainPanel;
import com.rental.MyDatabase.CarRentalDatabase;
import com.rental.lists.listElement.IListElement;

public class main {

	public static void main(String[] args) {
		MainFrame frame = MainFrame.getInstance();
		
		//CarRentalDatabase carDB = CarRentalDatabase.getInstance();
		//carDB.setRentCar(2, 1);
		/*try {
			//System.out.println(carDB.isCarInDatabase("Renault", "Sccenic") ? "TAK" : "NIE");
			//for(int i=0 ; i<10 ; i++)
			//carDB.deleteCar("Fiat", "Uno");
			//String tab[] = { "przemek", "kolodziejczyk" };
			//carDB.deleteLastByAtributes(tab);
			//ArrayList<IListElement> list = carDB.findPattern("sce gra");
			//for(IListElement l : list)
			//	l.printAtributes();
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
	}

}
