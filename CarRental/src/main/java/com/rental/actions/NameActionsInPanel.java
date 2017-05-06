package com.rental.actions;

public class NameActionsInPanel {

	private String insertAction, deleteAction;
	
	public NameActionsInPanel(String insertAction, String deleteAction) {
		this.insertAction = insertAction;
		this.deleteAction = deleteAction;
	}
	
	public String getInsertAction() {
		return insertAction;
	}
	
	public String getDeleteAction() {
		return deleteAction;
	}
	
}
