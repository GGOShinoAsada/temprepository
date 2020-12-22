package main.java.DAO;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.print.Doc;
import java.security.spec.NamedParameterSpec;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MongoContext {
   private class Resourses{
      private String Host = "localhost";
      private String DbName = "computersrore";
      private int port;
      public void setHost(String h){
         this.Host=h;
      }
      public String getHost(){
         return Host;
      }
      public void setDbname(String dbname){
         DbName=dbname;
      }
      public String getDbName(){
         return DbName;
      }
      public int getPort(){
         return port;
      }
      public void setPort(int port){
         this.port=port;
      }

   }
   public boolean isConnecionSuccess(){
      boolean a = true;
      Resourses res = new Resourses();
      try{
         MongoClient client = new MongoClient(res.getHost(),res.getPort());
         MongoDatabase db = client.getDatabase("computerstore");
         MongoCollection categories = db.getCollection("categories");

      }
      catch (MongoException ex){
         //do something
         ex.printStackTrace();
         a=false;
      }

      return a;
   }
   public  void  addCollection(Category c){
      try{
         MongoClient client = new MongoClient("mongodb://localhost/computerstore");

         MongoDatabase db = client.getDatabase("computerstore");

         MongoCollection<Document> categoriescollection = db.getCollection("categories");
         Document category = new Document("_id ", new ObjectId());
         category.append("Name",c.getName()).append("Description",c.getRating()).append("Rating",c.getRating());
         categoriescollection.insertOne(category);
         client.close();
      }
      catch (MongoException ex){
         ex.printStackTrace();
      }
   }
   public List<Category> getAllCategories(){
      List<Category> cat = new CopyOnWriteArrayList();
      try{
         MongoClient client = new MongoClient("mongodb://localhost/computerstore");
         MongoDatabase db = client.getDatabase("computerstore");
         MongoCollection<Document> collection = db.getCollection("categories");
         collection.find();
         cat = (List<Category>) collection;
      }
      catch (MongoException ex){
         ex.printStackTrace();
      }
      return  cat;
   }

}
