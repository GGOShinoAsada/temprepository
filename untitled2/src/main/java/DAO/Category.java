package main.java.DAO;

import org.bson.Document;


public class Category extends Document {

    private int id;
    private String name;
    private String description;
    private double rating;
    public void setId(int _id){
        id = _id;
    }
    public int getId(){
        return id;
    }
    public void setName(String _name){
        name=_name;
    }
    public String getName(){
        return name;
    }
    public void setDescription(String des){
        description=des;
    }
    public String getDescription(){
        return description;
    }
    public void setRating(double _rating){
        rating=_rating;
    }
    public double getRating(){
        return rating;
    }
    public Category(){
        id=Integer.MIN_VALUE;
        name=null;
        description=null;
        rating=0;
    }
    public Category(String _name, String des, double rat){
        name=_name;
        description=des;
        rating=rat;
    }
    public Category(int id, String _name, String des, double rat){
        id=id;
        name=_name;
        description=des;
        rating=rat;
    }
}
