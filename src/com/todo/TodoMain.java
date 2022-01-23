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
		TodoUtil.loadList(l, "todolist.txt");
		Menu.displaymenu();
		
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			String keyword= sc.nextLine().trim();
			
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
				System.out.println("'List sorted by title'\n");
				l.sortByName();
				isList = true;
				break;
			
			case "ls_name_desc":
				System.out.println("'List sorted by title in reverse order'\n");
				l.sortByName();
				l.reverseList();
				isList = true;
				break;
				
			case "ls_date":
				System.out.println("'List sorted by date'\n");
				l.sortByDate();
				isList = true;
				break;
			
			case "ls_date_desc":
				System.out.println("'List sorted by date in reverse order'\n");
				//l.sortByDate();
				//l.reverseList();
				l.sortByDateDesc();
				isList = true;
				break;
				
			case "find":
				System.out.println("'Find items that include the above keyword'\n");
				TodoUtil.findKeyword(l,keyword);
				break;
			
			case "find_cate":
				System.out.println("'Find items whose category include the above keyword'\n");
				TodoUtil.findCateKeyword(l,keyword);
				break;
			
			case "ls_cate":
				System.out.println("'Find items whose category include the above keyword'\n");
				TodoUtil.findCategories(l);
				break;
				
			case "help":
				Menu.displaymenu();
				break;
				
			case "exit":
				quit = true;
				TodoUtil.saveList(l, "todolist.txt");
				break;

			default:
				System.out.println("Choose one from the commands provided!");
				System.out.println("(Need help? -help command)");
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
	}
}
