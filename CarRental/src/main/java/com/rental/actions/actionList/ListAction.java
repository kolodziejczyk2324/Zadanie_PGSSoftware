package com.rental.actions.actionList;

import com.rental.actions.MyAbstractAction;

public class ListAction extends AbstractList<MyAbstractAction> {
	
	private void deleteAllNexts() {
		if(cur==null) return;
		Node ptr = cur;
		Node next = cur.next;
		ptr.next = null;
		while(next!=null) {
			ptr = next;
			next = ptr.next;
			ptr.next = null;
			ptr.prev = null;
		}
	}
	
	public void insert(MyAbstractAction value) {
		//deleteAllNexts();
		super.insert(value);
	}

}
