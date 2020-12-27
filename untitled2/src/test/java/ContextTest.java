package test.java;

import main.java.DAO.Category;
import main.java.DAO.Context;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ContextTest {


    @Test
    public void isconnectionSuccessfull() {
        Context context = new Context();
        boolean t = context.isconnectionSuccessfull();
        Assert.assertEquals(t,true);
    }

    @Test
    public void getAllCategories() {

        Category c = new Category("MyCategory","description 1",9.4);
        Category c1 = new Category("Category2","description 2", 8.5);
        Category c2 = new Category("Category 3", "description", 9.5);
        Category c4 = new Category("Category 4", "mydescription", 45.5);
        List<Category> categories = new ArrayList<>();
        categories.add(c);
        categories.add(c2);
        categories.add(c1);
        categories.add(c4);
        Context context = new Context();
        List<Category> categories1 = context.getAllCategories();
        Assert.assertEquals(categories,categories1);
    }



    @Test
    public void updateCategory() {
        Context c = new Context(); int id = c.getAllCategories().size()-1;
        Category cat = c.getCategoryById(id);
        Category newcat = new Category("NewCat","New description",8.9);
        c.updateCategory(id,newcat);
        cat=c.getCategoryById(id);
        Assert.assertEquals(cat,newcat);
    }

    @Test
    public void removeCategory() {
        List<Category> categories = new ArrayList<>();
        List<Category> categories1 = new ArrayList<>();
        Context context = new Context();
        categories1=context.getAllCategories();
        categories=context.getAllCategories();
        Category t = new Category("Test", "Test description",5.8);
        //additional
        categories.add(t);
        context.addCategory(t);

        categories1=context.getAllCategories();
        //removing
        categories.remove(t);
        context.removeCategory(t);
        categories1=context.getAllCategories();

        Assert.assertEquals(categories,categories1);

    }

    @Test
    public void addCategory() {
        List<Category> categories = new ArrayList<>();
        List<Category> categories1 = new ArrayList<>();
        Context context = new Context();
        categories1=context.getAllCategories();
        categories=context.getAllCategories();
        Category t = new Category("Test", "Test description",5.8);
        //additional
        categories.add(t);
        context.addCategory(t);
        categories1=context.getAllCategories();
        Assert.assertEquals(categories,categories1);
    }

    @Test
    public void searchCategory() {
        Context c = new Context();

        Category cats=new Category("testing actegory", "this is testing category", 51.5);
        c.addCategory(cats);
       List<Category> categories=c.getAllCategories();

        Assert.assertEquals(c.searchCategory(cats.getName(),cats.getDescription(),cats.getRating()),cats);
    }
}