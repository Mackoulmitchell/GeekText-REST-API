package com.springboot.app.Path;
import org.springframework.web.bind.annotation.*;

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

            String SQL = "INSERT INTO Book (ISBN, author, price, _name, book_description, publisher, year_published, copies_sold) VALUES (" + isbn + ", '" + author + "', " + cost + ", '" + title + "', '" + description + "', '" + publisher + "', " + year + ", " + copiesSold + ");";
            SQLUpdate(SQL);

        } catch (Exception e) {

    }
   }
  }

