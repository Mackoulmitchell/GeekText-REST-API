package com.springboot.app.Path;
import org.springframework.web.bind.annotation.*;
import java.sql.*;
import java.util.ArrayList;

@RestController
public class BookDetails extends BaseMethods{

	/*
    public String Author;
    public int CopiesSold;
    public double Cost;
    public String Description;
    public String Genre;
    public int ISBN;
    public String Publisher;
    public String Title;
    public int Year;

    public BookDetails()
    {
       /*
    	String Author;
        int CopiesSold;
        double Cost;
        String Description;
        String Genre;
        int ISBN;
        String Publisher;
        String Title;
        int Year;
        
    }

    public BookDetails(String title,String author,int year,String publisher,double cost, int ISBN, String genre, String description, int copiesSold)
    {   this.Title=title;
        this.Author=author;
        this.Year=year;
        this.Publisher=publisher;
        this.Cost=cost;
        this.ISBN=ISBN;
        this.Genre=genre;
        this.Description=description;
        this.CopiesSold=copiesSold;
    }
    */
	
    @PostMapping("/addBook")
    @ResponseBody
    protected void AddBook(@RequestParam String author, @RequestParam String title, @RequestParam String isbn, @RequestParam double cost, @RequestParam String description, @RequestParam String publisher, @RequestParam int year, @RequestParam int copiesSold) {
        try  {
            SQLUpdate("INSERT INTO Book (ISBN, author, price, _name, book_description, publisher, year_published, copies_sold) VALUES (" + isbn + ", '" + author + "', " + cost + ", '" + title + "', '" + description + "', '" + publisher + "', " + year + ", " + copiesSold + ");");

        } catch (Exception e) {
            e.printStackTrace();
    }
   }
   @GetMapping("/Books/Details")
   @ResponseBody
   public ArrayList<String> GetBookDetails(@RequestParam String isbn) {
       ArrayList<String> data = new ArrayList<String>();
       String Queried_BookTitle;
       String genre;
       String publisher;
       double price;
       String author;
       String description;
        try {
           ResultSet result = SQLQuery("SELECT * FROM book where isbn = '" + isbn + "'");
           while (result.next()) {
               Queried_BookTitle = result.getString("_name");
               genre = result.getString("genre");
               publisher = result.getString("publisher");
               price = result.getDouble("price");
               author = result.getString("author");
               description = result.getString("book_description");
               data.add("Book Name: " + Queried_BookTitle + " | Author: " + author + " | Genre: " + genre + " | Publisher: " + publisher + " | Cost: " + price + " | Description: " + description);
           }
       } catch (SQLException e){
           e.printStackTrace();
       }
       return data; //("Book Name: " + Queried_BookTitle + " | Author: " + author + " | Genre: " + genre + " | Publisher: " + publisher + " | Cost: " + price + " | Description: " + description);
   }
   @GetMapping("/Author/Books")
    @ResponseBody
   String GetBooksBySameAuthor(@RequestParam String author) {
       ResultSet result;
       String Queried_BookTitle = null;
       String genre = null;
       String publisher = null;
       Double price = null;
       String description = null;
        try {
            result = SQLQuery("SELECT * FROM book where author = '" + author + "'");
            while (result.next()) {
                Queried_BookTitle = result.getString("_name");
                genre = result.getString("genre");
                publisher = result.getString("publisher");
                price = result.getDouble("price");
                description = result.getString("book_description");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return ("Book Name: " + Queried_BookTitle + " | Author: " + author + " | Genre: " + genre + " | Publisher: " + publisher + " | Cost: " + price + " | Description: " + description);
   }
  }


