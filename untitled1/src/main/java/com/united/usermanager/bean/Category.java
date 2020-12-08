package com.united.usermanager.bean;

public class Category {
    private int Id;
    private String Name;
    private  String Description;
    private boolean Status;
    public void setId(int _id){
        Id=_id;
    }
    public int getId(){
        return Id;
    }
    public void setName(String name){
        Name=name;
    }
    public String getName(){
        return Name;
    }
    public void setDescription(String des){
        Description=des;
    }
    public String getDescription(){
        return Description;
    }
    public void setStatus(boolean status){
        Status=status;
    }
    public boolean getStatus(){
        return Status;
    }
    public Category(String name, String des, boolean st){
        Name=name;
        Description=des;
        Status=st;
    }
    public Category(int id, String name, String des, boolean st){
        Id=id;
        Name=name;
        Description=des;
        Status=st;
    }
}
