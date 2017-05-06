package com.rental.actions.actionList;

public abstract class AbstractList<T> {

	protected class Node {
		public Node next;
		public Node prev;
		public T value;
		
		public Node(T value, Node prev, Node next) {
			this.value = value;
			this.prev = prev;
			this.next = next;
		}
	}
	
	protected Node guard = new Node(null, null, null);
	protected Node cur = guard;
	
	public void insert(T value) {
		cur.next = new Node(value, cur, null);
		cur = cur.next;
	}
	
	public T moveNext() {
		if(!isNext()) return null;
		cur = cur.next;
		return cur.value;
	}
	
	public T movePrev() {
		if(cur == guard) return null;
		T retValue = cur.value;
		cur = cur.prev;
		return retValue;
	}
	
	public boolean isNext() {
		return cur.next != null;
	}
	
	public boolean isPrev() {
		return cur != guard;
	}
	
}
