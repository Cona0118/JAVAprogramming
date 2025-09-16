package com.test.controller;

import com.test.controller.action.Action;
import com.test.controller.action.*;

public class ActionFactory {
	private static ActionFactory instance = new ActionFactory();
	
	private ActionFactory() { super(); }
	
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		System.out.println("ActionFactory : "+command);
		
		if(command.equals("emplList")) {
			action = new emplListAction();
		} else if(command.equals("emplView")) {
			action = new emplViewAction();
		} else if(command.equals("emplUpdateForm")) {
			action = new emplUpdateFormAction();
		} else if(command.equals("emplUpdate")) {
			action = new emplUpdateAction();
		} else if(command.equals("emplDelete")) {
			action = new emplDeleteAction();
		} else if(command.equals("emplInsert")) {
			action = new emplInsertAction();
		} else if(command.equals("checkPass")) {
			action = new checkPassAction();
		} else if(command.equals("checkPassForm")) {
			action = new checkPassFormAction();
		}
		return action;
	}
	
}
