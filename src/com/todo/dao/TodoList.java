package com.todo.dao;

import java.util.*;

import com.todo.service.DateComparatorDesc;
import com.todo.service.TodoSortByDate;
import com.todo.service.TodoSortByName;

public class TodoList {
	private List<TodoItem> list;

	public TodoList() {
		this.list = new ArrayList<TodoItem>();
	}

	public void addItem(TodoItem t) {
		list.add(t);
	}

	public void deleteItem(TodoItem t) {
		list.remove(t);
	}

	void editItem(TodoItem t, TodoItem updated) {
		int index = list.indexOf(t);
		list.remove(index);
		list.add(updated);
	}

	public ArrayList<TodoItem> getList() {
		return new ArrayList<TodoItem>(list);
	}
	
	public int getCount() {
		return list.size();
	}
	
	public TodoItem getItem(int index) {
		return list.get(index);
	}
	
	public void sortByName() {
		Collections.sort(list, new TodoSortByName());
	}

	public void listAll() {
		System.out.print("[Item List, ");
		int num=list.size();
		System.out.println("a total of " + num + " items]");
		int cnt=0;
		for (TodoItem item : list) {
			cnt++;
			System.out.println(cnt +". " + item.toString());
		}
	}
	
	public void reverseList() {
		Collections.reverse(list);
	}

	public void sortByDate() {
		Collections.sort(list, new TodoSortByDate());
	}
	
	public void sortByDateDesc() {
		Collections.sort(list, new DateComparatorDesc());
	}

	public int indexOf(TodoItem t) {
		return list.indexOf(t);
	}

	public Boolean isDuplicate(String title) {
		for (TodoItem item : list) {
			if (title.equals(item.getTitle())) return true;
		}
		return false;
	}
}