package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		TodoUtil.loadList(l,"todolist.txt");
		Menu.displaymenu();
		
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "ls_name_asc":
				System.out.println("\n'List sorted by name'\n");
				l.sortByName();
				isList = true;
				break;

			case "ls_name_desc":
				System.out.println("\n'List sorted by name in reverse order'\n");
				l.sortByName();
				l.reverseList();
				isList = true;
				break;
				
			case "ls_date":
				System.out.println("\n'List sorted by date'\n");
				l.sortByDate();
				isList = true;
				break;
			
			case "help":
				Menu.displaymenu();
				break;
				
			case "exit":
				System.out.println("\n'Program ended!!'\n");
				quit = true;
				break;

			default:
				System.out.println("Choose one from the commands provided!");
				System.out.println("(Need help? -help command)");
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
		TodoUtil.saveList(l,"todolist.txt");
	}
}
