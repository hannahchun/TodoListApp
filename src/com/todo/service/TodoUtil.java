package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n" + "[Create item]\n" + "Title > ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.println("Duplicate titles not allowed!");
			return;
		}
		
		System.out.print("Description >");
		sc.nextLine();
		desc = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("Item added~");
	}

	public static void deleteItem(TodoList l) {
		
		String title;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n" + "[Delete Item]\n" + "Title of the item to remove > ");
		
		title = sc.next();
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("Item deleted~");
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n" + "[Edit Item]\n" + "Title of the item to update > ");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("Non-existing title!");
			return;
		}

		System.out.print("Title of the new item > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("Existing title!");
			return;
		}
		
		System.out.print("Description > ");
		sc.nextLine();
		String new_description = sc.nextLine().trim();
		
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("Item updated~");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("\n[Item List] \n");
		for (TodoItem item : l.getList()) {
			System.out.println("(" + item.getTitle() + ") -> " + item.getDesc() + " - " + item.getCurrent_date());
		}
	}

	public static void loadList(TodoList l, String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			int num=0;
			String oneline;
			
			while((oneline=br.readLine()) != null) {
				num+=1;
				StringTokenizer dl = new StringTokenizer(oneline, "##");
				String title=dl.nextToken();
				String desc=dl.nextToken();
				String date=dl.nextToken();
				
				TodoItem s = new TodoItem(title,desc);
				s.setCurrent_date(date);
				l.addItem(s);
			}
			
			if(num==1) {
				System.out.println("Read " + num + " item");
			}
			else {
				System.out.println("Read " + num + " items");
			}
			br.close();
		}
		catch(FileNotFoundException e) {
			System.out.println(filename + " does not exist");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void saveList(TodoList l, String filename) {
		System.out.println("Items saved!");
		
		try {
			Writer w = new FileWriter(filename);
			
			for(TodoItem m : l.getList()) {
				w.write(m.toSaveString());
			}
			w.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
