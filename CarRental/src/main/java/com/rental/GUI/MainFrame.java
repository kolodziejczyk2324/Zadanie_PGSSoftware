package com.rental.GUI;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.rental.GUI.MyComponents.MyTabbedPane;
import com.rental.GUI.inputs.inputsCheckers.*;
import com.rental.GUI.list.PressCell.*;
import com.rental.MyDatabase.*;
import com.rental.actions.NameActionsInPanel;

public class MainFrame extends JFrame {

	private static MainFrame instance = null;
	
	public static MainFrame getInstance() {
		if(instance==null)
			instance = new MainFrame();
		return instance;
	}
	
	private MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(GUIParameters.frameWidth,GUIParameters.frameHeight);
		UIManager.put("TabbedPane.selected", GUIParameters.darkGrey);
		final MainPanel panels[] = {	new MainPanel(CarRentalDatabase.getInstance(), new OnlyDeleteCarPress(), new OnlyEmptyInputCheck(),
											new NameActionsInPanel("INSERTCAR", "DELETECAR")),
										new MainPanel(CustomerDatabase.getInstance(), new OnlyDeleteCustomerPress(), new CustomerCheck(),
											new NameActionsInPanel("INSERTCUSTOMER", "DELETECUSTOMER")),
										new MainPanel(RentDatabase.getInstance(), new RentBackPress(), new RentCheck(),
											new NameActionsInPanel("RENT", "RENTBACK"))	};
		String panelsName[] = {"Cars", "Customers", "Rent"};
		
		final MyTabbedPane tabbedPane = new MyTabbedPane(panelsName, panels);
		
		add(tabbedPane);
		setVisible(true);
	}
	
}
