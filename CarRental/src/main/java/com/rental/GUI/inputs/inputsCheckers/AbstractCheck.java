package com.rental.GUI.inputs.inputsCheckers;

import java.sql.SQLException;

import javax.swing.JTextField;

import com.rental.GUI.MainPanel;

public abstract class AbstractCheck {

	public class CheckCommunicat{
		public boolean isCorrect;
		public String infoText;
		public CheckCommunicat(boolean isCorrect, String infoText) {
			this.isCorrect = isCorrect;
			this.infoText = infoText;
		}
	}
	
	public abstract CheckCommunicat check(JTextField textField[], MainPanel parent);
	
	protected boolean isEmptyInput(JTextField textField[]) {
		for(JTextField tf : textField)
			if(tf.getText().isEmpty())
				return true;
		return false;
	}
	
	protected boolean isNumberInTextField(JTextField textField){
		boolean ret = true;
		try{
			Long.parseLong(textField.getText());
		} catch(NumberFormatException ex) {
			ret = false;
		}
		return ret;
	}
	
	protected boolean isNumber(String str){
		boolean ret = true;
		try{
			Long.parseLong(str);
		} catch(NumberFormatException ex) {
			ret = false;
		}
		return ret;
	}
	
	protected boolean isPositiveNumber(String str){
		try{
			long x = Long.parseLong(str);
			return x > 0;
		} catch(NumberFormatException ex) {
			return false;
		}
	}
	
	protected boolean isCarWithThisId(int id, MainPanel parent) {
		try {
			return parent.getDB().isInDBQuery("SELECT * FROM Car WHERE ID="+id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
