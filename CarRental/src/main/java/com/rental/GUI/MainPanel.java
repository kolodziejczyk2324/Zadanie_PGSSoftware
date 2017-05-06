package com.rental.GUI;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.rental.GUI.inputs.InputsPanel;
import com.rental.GUI.inputs.inputsCheckers.AbstractCheck;
import com.rental.GUI.list.ListPanel;
import com.rental.GUI.list.PressCell.AbstractPress;
import com.rental.GUI.search.SearchPanel;
import com.rental.GUI.textInfo.TextInfoPanel;
import com.rental.GUI.undoredo.UndoRedoPanel;
import com.rental.MyDatabase.MyDatabase;
import com.rental.actions.DeleteAction;
import com.rental.actions.InsertAction;
import com.rental.actions.MyAbstractAction;
import com.rental.actions.MyActionFactory;
import com.rental.actions.NameActionsInPanel;
import com.rental.actions.actionList.ListAction;
import com.rental.lists.listElement.IListElement;

public class MainPanel extends JPanel {

	private MyDatabase db;
	private ListPanel listPanel;
	private SearchPanel searchPanel;
	private TextInfoPanel textInfoPanel;
	private UndoRedoPanel undoRedoPanel;
	
	private ListAction listAction;
	
	private NameActionsInPanel nameActionsInPanel;
	
	public MainPanel(MyDatabase db, AbstractPress pressAction, AbstractCheck checkInput, NameActionsInPanel nameActionsInPanel) {
		this.db = db;
		this.nameActionsInPanel = nameActionsInPanel;
		setLayout(new BorderLayout());
		
		listAction = new ListAction();
		
		listPanel = new ListPanel(pressAction);
		searchPanel = new SearchPanel(this);
		textInfoPanel = new TextInfoPanel();
		undoRedoPanel = new UndoRedoPanel(this, listAction);
		
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.add(listPanel, BorderLayout.CENTER);
		panel1.add(new InputsPanel(db.getMainColumnName(), this, checkInput), BorderLayout.SOUTH);
		JPanel panel2 = new JPanel(new BorderLayout());
		panel2.add(textInfoPanel, BorderLayout.NORTH);
		panel2.add(undoRedoPanel, BorderLayout.SOUTH);
		add(searchPanel, BorderLayout.NORTH);
		add(panel1, BorderLayout.CENTER);
		add(panel2, BorderLayout.SOUTH);
		
		refreshList();
	}
	
	public void insertToDB(int id, String tab[]) {
		MyAbstractAction action = MyActionFactory.createAction(nameActionsInPanel.getInsertAction(), db, id, tab);
		listAction.insert(action);
		action.execute();
		refreshList();
		searchPanel.clearInput();
		setInfoText("Pomyslne dodanie elementu");
	}
	
	public void deleteFromDBByID(int id, String param[]) {
		MyAbstractAction action = MyActionFactory.createAction(nameActionsInPanel.getDeleteAction(), db, id, param);
		listAction.insert(action);
		action.execute();
		refreshList();
		setInfoText("Pomyslne wykonanie operacji");
	}
	
	public void refreshList() {
		try {
			listPanel.refreshList(db.getAllList(), this);
			searchPanel.clearInput();
			updateEnabledUndoReduButtons();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateList(ArrayList<IListElement> list) {
		listPanel.refreshList(list, this);
		updateEnabledUndoReduButtons();
	}
	
	public MyDatabase getDB() {
		return db;
	}
	
	public void setInfoText(String text) {
		textInfoPanel.setText(text);
	}
	
	public void updateEnabledUndoReduButtons() {
		undoRedoPanel.updateEnabledButtons();
	}
	
}

