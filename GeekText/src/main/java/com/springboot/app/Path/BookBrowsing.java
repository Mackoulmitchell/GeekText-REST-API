package com.springboot.app.Path;
import org.springframework.web.bind.annotation.*;
import java.sql.*;
import java.util.ArrayList;

@RestController 
public class BookBrowsing extends BaseMethods {//Class extends ConnectAndMenu for the connection features. Might be worth separating them

	@GetMapping("/books/genre") //Denotes the function as a GET function, with the URL path as a parameter.
    public ArrayList<String> booksByGenre() {   //Data retrieval from book table function
        
        ResultSet result = null;
        ArrayList<String> data = new ArrayList<String>();
        String Query = String.format("SELECT * FROM book WHERE genre IN ('Fiction')");
        result = SQLQuery(Query);
        
        try {
            
        	//Query to fetch record from book table based on genre field value
            while (result.next()) {
            	//Adds records for the book table to the 'data' ArrayList based on genre.
                data.add("ISBN: " + result.getString("isbn") + " | Genre: " + result.getString("genre") + " | Name: " + result.getString("_name") + " | Price: " + result.getString("price") + " | Author: " + result.getString("author") + " | Publisher: " + 
                		result.getString("publisher") + " | Year Published: " + result.getString("year_published") + " | Copies Sold: " + result.getString("copies_sold") + " | Book Description: " + result.getString("book_description"));
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return data;
    }
	
	
	
	
	
	@GetMapping("/books/topSold")
    public ArrayList<String> booksByTopSold() {   //Top 6 sold books data retrieval from book table function
        ResultSet Result = null;
        ArrayList<String> DataList = new ArrayList<String>(); //Declaration and initialization of array list
      
        try {
            //Query to fetch record of top 6 sold books from book table
            String Query = String.format("SELECT * from book group by isbn order by sum(copies_sold) desc limit 6");
            Result = SQLQuery(Query);

            while (Result.next()) {
            	DataList.add("Name: " + Result.getString("_name") + " | Copies Sold: " + Result.getString("copies_sold"));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return DataList;
    }
	
	@GetMapping("/books/rating")
	@ResponseBody
    public ArrayList<String> booksByRating(@RequestParam String rating){   //  Retrieves books from 'book_details' table for a particular rating.
    	
		ArrayList<String> DataList = new ArrayList<String>();
    	ResultSet Result=null;
        
        try {
        	//Query to fetch a record of books from the 'book_details' table for a particular rating.
        	String Query = String.format("SELECT * from book_details where rating >= '" + rating + "'");
        	Result=SQLQuery(Query);
            
        	while(Result.next()){
        		
                // Adds the fetched record of each book in the rating requirements onto the ArrayList 'DataList' 
        		DataList.add("Book Name: " + Result.getString("_name") + " | Author: " + Result.getString("author") + " | Rating: " + Result.getString("rating"));
            
        	}
        }
        catch (Exception e){
            System.out.println(e);
        }
        return DataList;
    }
    
	
	@GetMapping("/books/Quantity")
	@ResponseBody
	public ArrayList<String> quantityOfBooks(@RequestParam int quantity){   //Retrieves a number of books from the 'book' table where the quantity is passed as the parameter 'Quantity'.
	    
			ArrayList<String> DataList = new ArrayList<String>();
	        ResultSet Result = null;
	        
	        
	        try {

	            if (quantity < 0 || quantity == 0){
	                System.out.println("Please enter a valid integer greater then 0");
	            }
	            
	            //Database query to fetch the specified quantity of books.
	            
	            String Query = String.format("SELECT * from book group by isbn order by sum(copies_sold) desc limit\t" + quantity);
	            Result = SQLQuery(Query);
	            
	            while(Result.next()){
	                //Adds each book to an ArrayList to send to the end user.
	            	DataList.add(" Book Name: " + Result.getString("_name") + " | Author: " + Result.getString("author") + " | Genre: " + Result.getString("Genre"));
	            }
	        }
	        catch (Exception e){
	            System.out.println(e);
	        }
	        return DataList;
	    }
    
	
	
	
}