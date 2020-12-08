package main.java.DAO;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Context {
    private class ConnectionProperties {
        private  String DatabaseName = "ComputerStore";
        private  String UserName = "root";
        private  String Password = "0000";

        public String getDatabaseName(){
            return DatabaseName;
        }
        public void setDatabaseName(String dbname){
            this.DatabaseName=dbname;
        }
        public void setUsername(String un)
        {
            UserName = un;
        }
        public String getUserName(){
            return UserName;
        }
        public void setPassword(String p){
            Password=p;
        }
        public String getPassword(){
            return Password;
        }
        public String getConnectionString(){
            return "jdbc:mysql://localhost:3306/"+ DatabaseName +"?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        }
    }
    private ConnectionProperties connection;
    private Connection MyDbConnection;
    private Statement StaticStatement;
    private PreparedStatement DynamicStatement;
    private ResultSet result;
    private String query;
    /*
    add category into database
     */
    public  boolean addCategory(Category cat){
        boolean issuc = true;
        ConnectionProperties prop = new ConnectionProperties();
        try{
            MyDbConnection = DriverManager.getConnection(prop.getConnectionString(),prop.getUserName(),prop.getPassword());
            query = "insert into categories (name, description, rating) values (?,?,?)";
            DynamicStatement = MyDbConnection.prepareStatement(query);
            DynamicStatement.setString(1,cat.getName());
            DynamicStatement.setString(2,cat.getDescription());
            DynamicStatement.setDouble(3,cat.getRating());
            issuc = DynamicStatement.executeUpdate()>0;
            System.out.println(issuc);
        } catch (SQLException e) {
            try {
                MyDbConnection.close();
                DynamicStatement.close();
                issuc=false;
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        finally {
            try{
                DynamicStatement.close();
                MyDbConnection.close();

            }
            catch (SQLException ex1){
                issuc=false;
            }
            catch (Exception ex2){

            }
        }
        return issuc;
    }
    /*
    removing category
     */
    public boolean removeCategory(int id){
        boolean rez = true;
        query = "delete from categories where id=?";
        ConnectionProperties properties = new ConnectionProperties();
        //Category cat = new Category();
        try{
            MyDbConnection = DriverManager.getConnection(properties.getConnectionString(),properties.getUserName(),properties.getPassword());
            System.out.println(properties.getConnectionString());
            DynamicStatement = MyDbConnection.prepareStatement(query);
            DynamicStatement.setInt(1,id);

            rez = DynamicStatement.executeUpdate()>0;
        }
        catch (SQLException ex){
            try{
                DynamicStatement.close();
                MyDbConnection.close();
            }
            catch (SQLException ex1){

            }
        }
        finally {
            try{
                DynamicStatement.close();
                MyDbConnection.close();
            }
            catch (SQLException ex2){

            }
        }
        return rez;
    }
    /*
    searching category by id
     */
    public Category getcategorybyid(int id){

        Category catsearch = new Category();
        ConnectionProperties properties = new ConnectionProperties();
        try{
            MyDbConnection = DriverManager.getConnection(properties.getConnectionString(),properties.getUserName(),properties.getPassword());
            query = "select name, description, rating from categories where id=?";
            DynamicStatement = MyDbConnection.prepareStatement(query);
            DynamicStatement.setInt(1,id);
            result = DynamicStatement.executeQuery();

            while (result.next()){
                String name = result.getString(1);
                String des = result.getString(2);
                double rat = result.getDouble(3);
                catsearch = new Category(name,des,rat);
            }
            if (catsearch.getId()>0){
                System.out.println("Category find");
            }
            else {
                System.out.println("category was not found");
            }
        }
        catch (SQLException ex){
            try{
                MyDbConnection.close();
                result.close();
                DynamicStatement.close();
            }
            catch (SQLException ex1){
                //throw someone Ex
            }
        }
        finally {
            try{
                result.close();
                DynamicStatement.close();
                MyDbConnection.close();
            }
            catch (SQLException ex2){
                //throw someone ex
            }
        }
        return catsearch;
    }
    /*
    updating category
     */
    public boolean updatecategory(Category cat){
        boolean rez = false;
        ConnectionProperties properties = new ConnectionProperties();
        query = "update categories set name=?, description=?,rating=? where id=?";
        try{
            MyDbConnection = DriverManager.getConnection(properties.getConnectionString(),properties.getUserName(),properties.getPassword());
            DynamicStatement = MyDbConnection.prepareStatement(query);
            DynamicStatement.setString(1,cat.getName());
            DynamicStatement.setString(2,cat.getDescription());
            DynamicStatement.setDouble(3,cat.getRating());
            DynamicStatement.setInt(4,cat.getId());
            rez=DynamicStatement.executeUpdate()>0;
        }
        catch (SQLException e) {
            try {
                DynamicStatement.close();
                MyDbConnection.close();
            }
            catch (SQLException ex){

            }
        }
        finally {
            try{
                MyDbConnection.close();
                DynamicStatement.close();
            }
            catch (SQLException ex){
                //do something here
            }
        }
        return rez;
    }
    /*
    get all categories
     */
    public List<Category> getallcategories(){
        List<Category> Categories = new ArrayList<>();
        query = "select name, description, rating from categories";
        ConnectionProperties properties = new ConnectionProperties();
        try {
            MyDbConnection = DriverManager.getConnection(properties.getConnectionString(),properties.getUserName(),properties.getPassword());
            StaticStatement = MyDbConnection.createStatement();
            result= StaticStatement.executeQuery(query);
            while (result.next()){
                String name = result.getString(1);
                String description = result.getString(2);
                double rating = result.getDouble(3);
                Categories.add(new Category(name, description,rating));
            }
        }
        catch (SQLException ex){
            try {
                MyDbConnection.close();
                StaticStatement.close();
                result.close();
            }
            catch (SQLException ex2){
                //do something here
            }
        }
        return Categories;
    }
}
