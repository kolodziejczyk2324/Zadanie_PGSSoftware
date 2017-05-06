package com.rental.lists.listElement;

public class ListElementFactory {

	public static IListElement getListElement(String criteria, String param[]) {
		if(criteria == "Car") {
			return new Car(param, criteria);
		} else if(criteria == "Customer") {
			return new Customer(param, criteria);
		} else if(criteria == "Rent") {
			return new Rent(param, criteria);
		}
		return null;
	}
	
	public static int[] getVisibleColumns(String criteria) {
		if(criteria == "Car") {
			int tab[] = {0, 1, 2};
			return tab;
		} else if(criteria == "Customer") {
			int tab[] = {1, 2, 3};
			return tab;
		} else if(criteria == "Rent") {
			int tab[] = {1, 2, 3, 4};
			return tab;
		}
		return null;
	}
	
}
