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
		
		String title, desc, cat, due;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n" + "[Create item]\n" + "Category > ");
		
		cat = sc.next();
		
		System.out.print("Title >");
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.println("Duplicate titles not allowed!");
			return;
		}
		
		System.out.print("Description >");
		sc.nextLine();
		desc = sc.nextLine();
		
		System.out.print("Due date >");
		due = sc.next();
		
		TodoItem t = new TodoItem(cat,title, desc, due);
		list.addItem(t);
		System.out.println("Item added~");
	}

	public static void deleteItem(TodoList l) {
		int num=l.getList().size(); //기존 리스트 item 개수
		int no; //번호 입력
		String yn; //yes or no 
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n" + "[Delete Item]\n" + "The item number to remove > ");
		
		no = sc.nextInt();
		
		if (1<=no && no<=num) {
			System.out.println(no
					+ ". [" + l.getList().get(no-1).getCategory()  
					+"] " + l.getList().get(no-1).getTitle() 
					+ " - " + l.getList().get(no-1).getDesc()
					+ " - " + l.getList().get(no-1).getDue_date()
					+ " - " + l.getList().get(no-1).getCurrent_date());
			
			System.out.print("Delete this item?(y/n) > ");
			yn=sc.next();
			if(yn.equals("y")) {
				l.deleteItem(l.getList().get(no-1));
				System.out.println("Item Deleted~");
			}
		}
		
		else {
			System.out.println("Invalid number!");
		}
		return;	
	}

	public static void updateItem(TodoList l) {
		int num=l.getList().size(); //기존 리스트 item 개수 
		int no; //번호 입력
		String title, cat, desc, due; //새 카테고리, 설명, 마감일자 
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n" + "[Edit Item]\n" + "The item number to update > ");
		
		no = sc.nextInt();
		
		if (1<=no && no<=num) {
			System.out.println(no
					+ ". [" + l.getList().get(no-1).getCategory()  
					+"] " + l.getList().get(no-1).getTitle() 
					+ " - " + l.getList().get(no-1).getDesc()
					+ " - " + l.getList().get(no-1).getDue_date()
					+ " - " + l.getList().get(no-1).getCurrent_date());
		
			System.out.print("Title of the new item > ");
			title = sc.next().trim();
			if (l.isDuplicate(title)) {
				System.out.println("Existing title!");
				return;
			}
		
			System.out.print("New Category > ");
			cat = sc.next();
		
			System.out.print("New Description > ");
			sc.nextLine();
			desc = sc.nextLine().trim();
		
			System.out.print("New Due date >");
			due = sc.next();
			
			l.deleteItem(l.getList().get(no-1));
			TodoItem t = new TodoItem(cat, title, desc, due);
			l.addItem(t);
			System.out.println("Item updated~");
		}
		
		else {
			System.out.println("Invalid number!");
		}
		return;
	}

	public static void listAll(TodoList l) {
		int num=l.getList().size();
		System.out.println("\n[Item List] , " + num + " items\n");
		for (int i=0; i<num ; i++) {
			System.out.println(i+1
								+ ". [" + l.getList().get(i).getCategory()  
								+"] " + l.getList().get(i).getTitle() 
								+ " - " + l.getList().get(i).getDesc()
								+ " - " + l.getList().get(i).getDue_date()
								+ " - " + l.getList().get(i).getCurrent_date());
		}
	}
	
	public static void KeyWordFind(TodoList l, String kw) {
		int num=l.getList().size();
		int cnt=0;
		for (int i=0; i<num ; i++) {
			if (l.getList().get(i).getTitle().contains(kw) || l.getList().get(i).getDesc().contains(kw)) {
				cnt++;
				System.out.println(i+1
									+ ". [" + l.getList().get(i).getCategory()  
									+"] " + l.getList().get(i).getTitle() 
									+ " - " + l.getList().get(i).getDesc()
									+ " - " + l.getList().get(i).getDue_date()
									+ " - " + l.getList().get(i).getCurrent_date());
			}
		}
		System.out.println("Found " + cnt + " items");
	}
	
	public static void KeyWordFindCate(TodoList l, String kw) {
		int num=l.getList().size();
		int cnt=0;
		for (int i=0; i<num ; i++) {
			if (l.getList().get(i).getCategory().contains(kw)) {
				cnt++;
				System.out.println(i+1
									+ ". [" + l.getList().get(i).getCategory()  
									+"] " + l.getList().get(i).getTitle() 
									+ " - " + l.getList().get(i).getDesc()
									+ " - " + l.getList().get(i).getDue_date()
									+ " - " + l.getList().get(i).getCurrent_date());
			}
		}
		System.out.println("Found " + cnt + " items");
	}
	
	public static void PrintCat(TodoList l) {
		int num=l.getList().size();
		
		List<String> catList = new ArrayList<String>();
		HashSet<String> catHashSet = new HashSet<>();
		
		
		for (int i=0; i<num ; i++) {
			String cat = l.getList().get(i).getCategory();
			catList.add(cat);
		}
		
		for(String c : catList) {
			catHashSet.add(c);
		}
		
		int numofcat=catHashSet.size(); 
		
		Iterator<String> itr = catHashSet.iterator(); //반복자 획득 
		
		while(itr.hasNext()) {
			System.out.print(itr.next()); //catHashSet에 있는 첫번째 값 출력
			itr.remove(); //첫번째 값 삭제
			break;
		}
			
		itr = catHashSet.iterator(); //반복자 재획득
		while(itr.hasNext()) {
			System.out.print(" / " + itr.next()); //catHashSet에 있는 첫번째 값 출력
		}
		System.out.println();
		
		System.out.println("A total of " + numofcat + " categories");
	}
	
	public static void loadList(TodoList l, String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			int num=0;
			String oneline;
			
			while((oneline=br.readLine()) != null) {
				num+=1;
				StringTokenizer dl = new StringTokenizer(oneline, "##");
				String cat=dl.nextToken();
				String title=dl.nextToken();
				String desc=dl.nextToken();
				String due=dl.nextToken();
				String date=dl.nextToken();
				
				TodoItem s = new TodoItem(cat, title, desc, due);
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
