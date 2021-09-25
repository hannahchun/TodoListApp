package com.todo.menu;

public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("<ToDoList>\n");
        System.out.println("^ User's guide ^\n");
        System.out.println("add - Add a new item");
        System.out.println("del - Delete an existing item");
        System.out.println("edit - Update an item");
        System.out.println("ls - List all items");
        System.out.println("ls_name_asc - sort the list in standard order by name");
        System.out.println("ls_name_desc - sort the list in reverse order by name");
        System.out.println("ls_date - sort the list by date");
        System.out.println("find <KEYWORD> - find all items that include KEYWORD in the title or the description");
        System.out.println("ls_date_desc - sort the list in reverse order by date");
        System.out.println("find_cate <KEYWORD> - find all items that include KEYWORD in the category");
        System.out.println("ls_cate - print non duplicate categories of the list");
        System.out.println("exit(Or escape key) - end the program");
    }
    
    public static void prompt() {
    	System.out.print("\nEnter your choice >");
    }
}
