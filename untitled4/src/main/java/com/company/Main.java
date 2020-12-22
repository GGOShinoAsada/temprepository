package main.java.com.company;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        consoleInterfase();

    }
    static void consoleInterfase(){
        boolean a=true;
        Context context = new Context();
        Category category = null;
        while (a){
            System.out.println("Please select option:");
            System.out.println("I - add new category in database");
            System.out.println("A - get all categories from table categories, database \"computerstore\"");
            System.out.println("R - remove category from table \"categories\"");
            System.out.println("S - search category from table");
            System.out.println("U - update exists category in table \"categories\"");
            System.out.println("E - exit");
            Scanner scanner = new Scanner(System.in);

            String r = scanner.nextLine();
            switch (r){
                case "I":
                    System.out.println("trying to add new item into table categories");
                    System.out.println("Please enter name of category");
                    String n = scanner.nextLine();
                    System.out.println("Please enter description of category");
                    String d = scanner.nextLine();
                    System.out.println("Please enter rating from category");
                    double rat=Double.parseDouble(scanner.nextLine());
                    category=new Category(n,d,rat);
                    context.addCategory(category);
                    break;
                case "A":
                    List<Category> catlist = context.getAllCategories();
                    System.out.println("table \"castegories\" have "+catlist.size()+" elements");
                    for (Category c: catlist) {
                        System.out.println("Category id"+c.getId());
                        System.out.println("Category name"+c.getName());
                        System.out.println("Category description"+c.getDescription());
                        System.out.println("Rating of category: "+c.getRating());
                        System.out.println("Additional date: "+c.getDate());
                    }
                    break;
                case  "R":
                    System.out.println("trying to remove category");
                    System.out.println("please enter name of category");
                    n=scanner.nextLine();
                    System.out.println("Please enter description of category");
                    d=scanner.nextLine();
                    System.out.println("Please enter rating from category");
                    rat=Double.parseDouble(scanner.nextLine());
                    category=context.searchCategory(n,d,rat);
                    if (category!=null){
                        context.removeCategory(category);
                        System.out.println("removing successful");
                    }
                    else {
                        System.out.println("category not found, please try again");
                    }

                    break;
                case "S":
                    System.out.println("trying to search category");
                    System.out.println("please enter name of category");
                    n=scanner.nextLine();
                    System.out.println("please enter description of category");
                    d=scanner.nextLine();
                    System.out.println("Please enter rating of category");
                    rat=Double.parseDouble(scanner.nextLine());
                    category=context.searchCategory(n,d,rat);
                    if (category!=null){
                        System.out.println("category was found");
                        System.out.println("id: "+category.getId());
                        System.out.println("name: "+category.getName());
                        System.out.println("Description"+category.getDescription());
                        System.out.println("Rating "+category.getRating());
                        System.out.println("Date "+category.getDate());
                    }
                    else {
                        System.out.println("category was not found, please try again");
                    }
                    break;
                case "U":
                    System.out.println("trying to update category");
                    System.out.println("please enter name of category");
                    n=scanner.nextLine();
                    System.out.println("please enter description of category");
                    d=scanner.nextLine();
                    System.out.println("please enter rating of category");
                    rat=Double.parseDouble(scanner.nextLine());
                    category=context.searchCategory(n,d,rat);
                    if (category!=null){
                        System.out.println("Category was found");
                        System.out.println("please enter new name");
                        n=scanner.nextLine();
                        System.out.println("please enter new description");
                        d=scanner.nextLine();
                        System.out.println("please enter new rating");
                        rat=Double.parseDouble(scanner.nextLine());
                        category.setDescription(d);
                        category.setName(n);
                        category.setRating(rat);
                        context.updateCategory(category.getId(), category);
                    }
                    break;
                case "E":
                    a=false;
                    break;
            }
        }
    }
}
