package main.java.com.company.DAO;

import java.security.spec.NamedParameterSpec;
import java.util.Date;

public class Category {
    private int Id;
    private String Name;
    private String Description;
    private double Rating;
    public Date date;
    public void setRating(double r){
        Rating=r;
    }
    public void setDate(Date d){
        date=d;
    }
    public Date getDate() {
        date = new Date();
        return date;
    }

    public double getRating(){
        return Rating;
    }
    public void setId(int _id){
        Id=_id;
    }
    public void setName(String n){
        Name=n;
    }
    public void setDescription(String d){
        Description=d;
    }
    public int getId(){
        return Id;
    }
    public String getName()
    {
        return Name;
    }
    public String getDescription(){
        return Description;
    }
    public  Category(int _id, String n, String d, double r){
        Id=_id;
        Name=n;
        Description=d;
        Rating=r;
        date=new Date();
    }
    public Category(String n, String d, double r){
        Name=n;
        Description=d;
        Rating=r;
        date=new Date();
    }
    public Category(){}
}
