package main.java.DAO;

public class Category {
    private int Id;
    private  String Name;
    private String Description;
    private double Rating;
    public void setId(int _id){
        Id=_id;
    }
    public int getId(){
        return Id;
    }
    public void setName(String n){
        Name=n;
    }
    public String getName(){
        return Name;
    }
    public void setDescription(String d){
        Description=d;
    }
    public String getDescription(){
        return Description;
    }
    public void setRating(double r){
        Rating=r;
    }
    public double getRating(){
        return Rating;
    }
    public Category(String n, String d, double r ){
        Id=Integer.MIN_VALUE;Name=n;Description=d;Rating=r;
    }
    public Category(int id, String n, String d, double r){
        id=id;
        Name=n;
        Description=d;
        Rating=r;
    }
}
