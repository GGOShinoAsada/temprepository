package main.java.com.company.DAO;


import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Context {

    private class ConnectInfo{
        private String dbname="computerstore";
        private  String username = "root";
        private  String password="0000";
        public String getConnectionString(){
            //jdbc:mysql://localhost:3306/computerstore
            return "jdbc:mysql://localhost:3306/"+dbname+"?serverTimezone=UTC&useSSL=false";
        }
        public String getUsername(){
            return  username;
        }
        public String getPassword(){
            return  password;
        }

    }
    private Connection connection;
    private Statement statement;
    private PreparedStatement dynamicstatement;
    private ResultSet result;
    String query;
    public boolean isconnectionSuccessfull(){
        boolean a = true;
        ConnectInfo info = new ConnectInfo();
        connection = null;
        try{
            //Class.forName("com.mysql.jdbc.Driver");
             connection = DriverManager.getConnection(info.getConnectionString(),info.getUsername(),info.getPassword());
        }
        catch ( SQLException e) {
            a=false;
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return a;
    }

    public List<Category> getAllCategories(){
        ConnectInfo info = new ConnectInfo();
        List<Category> categories = new ArrayList();
        try{

            connection = DriverManager.getConnection(info.getConnectionString(),info.getUsername(),info.getPassword());
            query = "select * from categories";
            statement=connection.createStatement();
            result=statement.executeQuery(query);
            while (result.next()){
                int id =result.getInt("id");
                String name = result.getString("name");
                String description = result.getString("Description");
                double rat = result.getDouble("rating");
                Date d = result.getDate("additionaldate");
                Category cat = new Category(id,name,description,rat);
                categories.add(cat);
            }
        }
        catch (SQLException ex){
            try{
                result.close();
                statement.close();
                connection.close();
                ex.printStackTrace();
            }
            catch (SQLException ex1){
                ex1.printStackTrace();
            }
        }
        finally {
            try{
                result.close();
                statement.close();
                connection.close();
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return categories;
    }
    public  Category getCategoryById(int id){
        query="select name, description, rating, additionaldate from categories where id=%d";
        Category cat=null;
        ConnectInfo info = new ConnectInfo();
        try{
            connection=DriverManager.getConnection(info.getConnectionString(),info.getUsername(),info.getPassword());
            statement=connection.createStatement();
            result=statement.executeQuery(String.format(query,id));
            while (result.next()){
                String n = result.getString("name");
                String d=result.getString("description");
                double r = result.getDouble("rating");
                Date date = result.getDate("additionaldate");
                cat=new Category(id, n,d,r);
            }
        }
        catch (SQLException ex){
            try {
                System.out.println("trying ro get data from database is failed..");
                connection.close();
                statement.close();
                result.close();
            }
            catch (SQLException ex1){
                ex1.printStackTrace();
            }
        }
        finally {
            try{
                connection.close();
                result.close();
                statement.close();
            }
            catch (SQLException ex1){
                ex1.printStackTrace();
            }
        }
        return  cat;
    }
    public boolean updateCategory(int id, Category cat){
        boolean f = true;
        try{
            query="update categories set name=?, description=?, rating=?, additionaldate=? where id=?";
            ConnectInfo info = new ConnectInfo();
            connection=DriverManager.getConnection(info.getConnectionString(),info.getUsername(),info.getPassword());
            dynamicstatement=connection.prepareStatement(query);
            dynamicstatement.setString(1,cat.getName());
            dynamicstatement.setString(2,cat.getDescription());
            dynamicstatement.setDouble(3,cat.getRating());
            dynamicstatement.setDate(4,convertToSqlDate(cat.getDate()));
            dynamicstatement.setInt(5,id);
            if (dynamicstatement.executeUpdate()>0){
               System.out.println("updating successful");
            }
            else {
                System.out.println("updating failed");
                f=false;
            }
        }
        catch (SQLException ex){
            try {
                ex.printStackTrace();
                statement.close();
                connection.close();
            }
            catch (SQLException ex1){
                ex1.printStackTrace();
            }
        }
        finally {
            try {
                statement.close();
                connection.close();
            }
            catch (SQLException ex1){
                ex1.printStackTrace();
            }
        }
        return f;
    }
    public  boolean removeCategory(Category cat){
        boolean f = true;
        try {


            query="delete from categories where id=%d";

            ConnectInfo info = new ConnectInfo();
            connection = DriverManager.getConnection(info.getConnectionString(),info.getUsername(),info.getPassword());
            statement=connection.createStatement();
            if (statement.executeUpdate(String.format(query,cat.getId()))>0){
                System.out.println("removing successful");
            }
            else {
                f=false;
                System.out.println("removing failed");
            }
        }
        catch (SQLException ex){
            try {
                connection.close();
                statement.close();
                ex.printStackTrace();
                f=false;
            }
            catch (SQLException ex1){
                ex1.printStackTrace();
            }
        }
        finally {
            try {
                connection.close();
                statement.close();


            }
            catch (SQLException ex1){
                ex1.printStackTrace();
            }
        }
        return f;
    }
    public boolean addCategory(Category t){
        boolean f=true;
        try{
            ConnectInfo info = new ConnectInfo();

            connection=DriverManager.getConnection(info.getConnectionString(), info.getUsername(),info.getPassword());
            //query="insert into categories (name, description, rating, additionaldate) values (%s, %s, %f, %T)";
            query="insert into categories ( name, description, rating, additionaldate) values (?,?,?,?)";

            dynamicstatement=connection.prepareStatement(query);
            dynamicstatement.setString(1,t.getName());
            dynamicstatement.setString(2,t.getDescription());
            dynamicstatement.setDouble(3,t.getRating());
            dynamicstatement.setDate(4,convertToSqlDate(t.getDate()));

           System.out.println(query);
            if (dynamicstatement.executeUpdate()>0){

                System.out.println("adding successful");
            }
            else {
                System.out.println("adding failed");
            }
        }
        catch (SQLException ex){
            try{
                connection.close();
                dynamicstatement.close();
                f=false;
                ex.printStackTrace();
            }
            catch (SQLException ex1){
                ex1.printStackTrace();
            }
            finally {
                try{
                    connection.close();
                    dynamicstatement.close();
                }
                catch (SQLException ex1){
                    ex1.printStackTrace();
                }

            }

        }
        return f;
    }
    public Category searchCategory(String name, String desc, double rat){
        List<Category> categories = getAllCategories();
        //System.out.println(categories.size());
        Category category = new Category();
        for (Category c: categories){
            if (c.getName().equalsIgnoreCase(name) && c.getDescription().equalsIgnoreCase(desc) && c.getRating()==rat){
              // System.out.println(c.getId());
                category.setId(c.getId());
                category.setName(c.getName());
                category.setRating(c.getRating());
                category.setDate(c.getDate());
                category.setDescription(c.getDescription());
            }
        }
        return category;
    }
    public java.sql.Date convertToSqlDate(java.util.Date d){
        java.sql.Date date=new java.sql.Date(d.getTime());
        return  date;
    }
}
