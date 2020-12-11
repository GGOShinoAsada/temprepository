package main.java.DAO;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoDatabase;

import java.security.spec.NamedParameterSpec;

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
      }
      catch (MongoException ex){
         //do something
         ex.printStackTrace();
         a=false;
      }

      return a;
   }

}
