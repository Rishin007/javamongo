package com.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;


public class Main {
    private static final String connectionString = "mongodb://localhost:27017";
    private static final String dbName="springbootdb";
    private static final String collectionName = "books";
    public static void main(String[] args) {
        try(MongoClient mongoClient =  MongoClients.create(connectionString)){
            MongoDatabase database = mongoClient.getDatabase(dbName);
            System.out.println("Connected to database..... "+dbName);
            MongoCollection<Document> collection = database.getCollection(collectionName);
            System.out.println("Collection name is ..... "+collectionName);
            
           Document book1=new Document("title","Spring Boot")
                    .append("author","Rishin Dutta")
                    .append("price",1200.99)
                    .append("isbn","798-470-7890")
                    .append("publisher","Prantik Publications")
                    .append("published",true);
            collection.insertOne(book1);
            Document book2=new Document("title","Oracle JDBC")
                    .append("author","Oindrila Sarkar")
                    .append("price",800.66)
                    .append("isbn","123-456-77970")
                    .append("publisher","AC Sir Publications")
                    .append("published",true);
            collection.insertOne(book2);

            System.out.println("Inserted Book Successfully..... "+book1.toJson());
            System.out.println("Inserted Book Successfully..... "+book2.toJson());


            //Fetch a Book
            Document di=collection.find(eq("title","Spring Boot")).first();
            if(di!=null){
                System.out.println("Book has been found.......... "+di.toJson());
            }
            else
                System.out.println("Book has been NOT found........ ");

            //Update the BOOK
            collection.updateOne(eq("title","Spring Boot"),new Document("$set",new Document("author","Hasbullah Marwari")));
            collection.updateOne(eq("title","Spring Boot"),new Document("$set",new Document("publisher","Boudi Publications")));

            //Delete the BOOK

            collection.deleteOne(eq("title","Oracle JDBC"));
            System.out.println("Deleted Book Successfully......... ");
        }
        catch (Exception e){
            System.out.println("Exception "+e);
        }
    }
}
