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
		
		String title, desc, category, date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n" + "[Create item]\n" + "Category > ");
		
		category=sc.nextLine();
		
		System.out.print("Title > ");
		
		title = sc.nextLine();
		if (list.isDuplicate(title)) {
			System.out.println("Duplicate titles not allowed!");
			return;
		}
		
		System.out.print("Description >");
		desc = sc.nextLine();
		
		System.out.print("Due Date >");
		date = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc, category,date);
		list.addItem(t);
	}
	
	public static void deleteItem(TodoList l) {
		int index;
		String des; //decision
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n" + "[Delete Item]\n" + "Number of the item to remove > ");
		
		index = sc.nextInt();
		
		if(index > l.getCount()) {
			System.out.println("Non-existing item!");
			return;
		}
		
		System.out.println(index + ". " + l.getItem(index-1).toString());
		
		System.out.print("Delete this item? (y/n)");
		des=sc.next();
		if(des.equals("y")) {
			l.deleteItem(l.getItem(index-1));
			System.out.println("The item is deleted!");
		}
		
	}
	
	public static void updateItem(TodoList l) {
		
		int num;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n" + "[Edit Item]\n" + "Number of the item to update > ");
		num = sc.nextInt();
		
		int cnt=1;
		for (TodoItem item : l.getList()) {
			if(cnt==num) {
				System.out.println(cnt + ". " + item.toString());
				sc.nextLine();
				System.out.print("Title of the new item > ");
				String new_title = sc.nextLine();
				if (l.isDuplicate(new_title)) {
					System.out.println("Existing title!");
					return;
				}
				System.out.print("Category of the new item > ");
				String new_category = sc.nextLine();
				System.out.print("Description of the new item > ");
				String new_desc = sc.nextLine();
				System.out.print("Due date of the new item > ");
				String new_due_date = sc.nextLine();
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_desc, new_category, new_due_date);
				l.addItem(t);
				System.out.println("The item is updated!");
				break;
			}
			cnt++;
		}
		
		if(cnt==l.getList().size()+1) {
			System.out.println("Non-existing item!");
		}
	}

	public static void listAll(TodoList l) {
		System.out.print("[Item List, ");
		int num=l.getList().size();
		System.out.println("a total of " + num + " items]");
		int cnt=0;
		for (TodoItem item : l.getList()) {
			cnt++;
			System.out.println(cnt +". " + item.toString());
		}
	}
	
	public static void findKeyword(TodoList l, String keyword) {
		int cnt=1;
		int num=0;
		for (TodoItem item : l.getList()) {
			if(item.getTitle().contains(keyword) || item.getDesc().contains(keyword)) {
				System.out.println(cnt + ". " + item.toString());
				num++;
			}
			cnt++;
		}
		System.out.println("Found a total of " + num + " items");
	}
	
	public static void findCateKeyword(TodoList l, String keyword) {
		int cnt=1;
		int num=0;
		for (TodoItem item : l.getList()) {
			if(item.getCategory().contains(keyword)) {
				System.out.println(cnt + ". " + item.toString());
				num++;
			}
			cnt++;
		}
		System.out.println("Found a total of " + num + " items");
	}
	
	public static void findCategories(TodoList l) {
		
		Set<String> clist = new HashSet<String>();
		
		for(TodoItem c : l.getList()) {
			clist.add(c.getCategory());
		}
		
		Iterator it = clist.iterator();
		while(it.hasNext()) {
			String s = (String)it.next();
			System.out.print(s);
			if(it.hasNext())
				System.out.print("/");
		}
		System.out.printf("\nA total of %d categories\n", clist.size());
	}
	
	public static void saveList(TodoList l, String filename) {
		System.out.println("Items saved!");
		try {
			Writer w = new FileWriter(filename);
			
			for(TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadList(TodoList l, String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("todolist.txt"));
			
			String oneline;
			int num=0;
			
			while((oneline=br.readLine()) != null) {
				num+=1;
				StringTokenizer st = new StringTokenizer(oneline, "##");
				String cat=st.nextToken();
				String title=st.nextToken();
				String desc=st.nextToken();
				String due_date=st.nextToken();
				String date=st.nextToken();
				
				TodoItem s = new TodoItem(title,desc,cat,due_date);
				s.setCurrent_date(date);
				l.addItem(s);
			}
			
			if( num==0 || num==1) {
				System.out.println("Read " + num + " item");
			}
			else {
				System.out.println("Read " + num + " items");
			}
			br.close();	
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}
