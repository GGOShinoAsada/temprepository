package main.java.DAO;

public class User {
    private  int Id;
    private String name;

    private int age;
    private void setId(int id){
        Id=id;
    }
    public int getId(){
        return Id;
    }
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
