package main.java.DAO;

import java.util.ArrayList;
import java.util.List;

public class Context {
    private final String connectionstring = "jdbc:mysql://localhost/computerstore?usernameroot";
    private List<Category> Categories = new ArrayList();

    public List<Category> getCategories() {
        return Categories;
    }
    public boolean InsertCategory(Category cat){
        boolean a = true;
        if (conn)
        return  a;
    }
}
