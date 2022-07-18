package com.springboot.app.Path;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.*;


@RestController
public class Shopping_Cart extends BaseMethods{

    String getBookNameFromIsbn(String ISBN) { 

        String Queried_BookName = null;

        try {
            String Query = "SELECT * FROM BOOK WHERE ISBN = '" + ISBN + "';";
            ResultSet queryResult = SQLQuery(Query);
            while(queryResult.next()) { //CHANGE FROM WHILE INSTEAD OF IF, TEST TO SEE WHAT HAPPENS
                Queried_BookName = queryResult.getString("_name");
            }
            return Queried_BookName;

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return Queried_BookName;
    }
        
    @PostMapping("/Cart/Add")
    @ResponseBody
    String addToShoppingCart(@RequestParam String isbn, @RequestParam int quantity, @RequestParam int id) {
			String Book_Name = getBookNameFromIsbn(isbn);
        try  {
        	
            String duplicateCheckString = "SELECT * FROM shopping_cart WHERE ISBN = '" + isbn + "';";
            ResultSet duplicateCheck = SQLQuery(duplicateCheckString);
        	
        	if(duplicateCheck.next()) { //Checks the shopping_cart table against the book being added to see if a duplicate book is being added to the cart.
        		
        		int cartQuantity = duplicateCheck.getInt("quantity");
        		int newQuantity = cartQuantity + quantity;
        		
        		String addQuantity = "Update shopping_cart SET \"Quantity\" = " + newQuantity + " where \"isbn\" = '" + isbn + "';";
        		SQLUpdate(addQuantity);
        		
        		return ("Successfully Added " + cartQuantity + " copies of " + Book_Name + " to the cart!");
        	}
        	else { //If a duplicate book is not found, it adds the book to the shopping_cart table on its own.
        		

        		String Update = "INSERT INTO shopping_cart (ISBN, USER_ID, \"Quantity\", _name) VALUES ('" + isbn + "', " + id + ", " + quantity + ", '" + Book_Name + "');";
            	SQLUpdate(Update);
            	return ("Successfully added " + quantity + " copies of " + Book_Name + " to the shopping cart!");
        	}
        } catch (Exception e) {
            e.printStackTrace();
            return "Try Catch Error.";
        }
    } 
    
    @DeleteMapping("/Cart/Remove")
    @ResponseBody
    String removeFromShoppingCart(@RequestParam String id, @RequestParam String isbn) { 
        if (isTableEmpty("Shopping_Cart")) { //Checks if the table is empty before attempting to remove the item from the cart.
            return "The shopping cart is empty. Please add something to the shopping cart before attempting removal.";
        }
        String Query = "Delete from shopping_cart where ISBN = '" + isbn + "';";
        SQLQuery(Query);
        
		return "Item Successfully Removed!";
    }
    
    @GetMapping("/Cart")
    @ResponseBody
    ArrayList<String> viewShoppingCart(@RequestParam String id) {
    	
    	ArrayList<String> cart = new ArrayList<String>();
    	
    	cart.add("The shopping cart for this user is empty. Try adding something to the cart!"); //Holds an error string in the cart and prints it if the cart is empty,
    	System.out.println("Checking Shopping Cart if it's empty.");  							 //otherwise prints the shopping cart.
        
    	if (isTableEmpty("shopping_cart")) {
          return cart;
        }
        cart.remove(0);
    	
    	
    	try {
        	System.out.println("Querying cart.");

    		String Query = "SELECT * FROM SHOPPING_CART WHERE USER_ID = " + id + ";";
            ResultSet result = SQLQuery(Query);
            
            while (result.next()) { //Adds the ISBN, Quantity, and Name of each book to the ArrayList until ResultSet finishes adding the last row.
                
            	String Queried_ISBN = result.getString("isbn");
                int Queried_QUANTITY = result.getInt("Quantity");
                String Queried_Name = result.getString("_name");
                cart.add(" Name: " + Queried_Name + " || Quantity: " + Queried_QUANTITY + " || ISBN: " + Queried_ISBN);
            
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cart;
    }














































/*
@RestController
public class Shopping_Cart extends ConnectAndMenu{



    boolean isEmpty() {
        try (Connection connection = DriverManager.getConnection(getURL(), getUsername(), getDBPassword())) {

            String SQL = "SELECT * FROM SHOPPING_CART WHERE USER_ID = " + this.getUSER_ID() + ";";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(SQL);

            return !result.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    String getBookNameFromIsbn(String ISBN) {

        String Queried_BookName = null;

        try (Connection connection = DriverManager.getConnection(getURL(), getUsername(), getDBPassword())) {

            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM BOOK WHERE ISBN = '" + ISBN + "';";
            ResultSet result = statement.executeQuery(SQL);

            while (result.next()) {
                Queried_BookName = result.getString("_name");

            }

            return Queried_BookName;

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return Queried_BookName;
    }
    
    @PostMapping("/AddToShoppingCart")
    @ResponseBody
    void AddToShoppingCart(@RequestParam String ISBN, @RequestParam int QUANTITY) {

    	String Book_Name = getBookNameFromIsbn(ISBN); //----------------------------------------------------------------Find a way to retrieve the book name from the Book table to simplify adding items.

        try (Connection connection = DriverManager.getConnection(getURL(), getUsername(), getDBPassword())) {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO shopping_cart (ISBN, USER_ID, \"Quantity\", _name) VALUES ('" + ISBN + "', " + getUSER_ID() + ", " + QUANTITY + ", '" + Book_Name + "');";
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @DeleteMapping
    void RemoveFromShoppingCart(String USER_ID) { //Function Argument will be utilized when feature is developed.
        if (isEmpty()) {
            System.out.println("The shopping cart is empty. Please add something to the shopping cart before attempting removal.");
        }
    }
    
    @GetMapping("/shoppingCart")
    String ViewShoppingCart(@RequestParam String USER_ID) {
        //if (isEmpty()) {
          //  return "\nThe shopping cart is empty. Please add something to the shopping cart before attempting removal.";
        //}

        try (Connection connection = DriverManager.getConnection(url, username, DBPassword)) {

            Statement statement = connection.createStatement();
            String Query = "SELECT * FROM SHOPPING_CART WHERE USER_ID = " + USER_ID + ";";
            ResultSet result = statement.executeQuery(Query);

            System.out.println("User ID: " + USER_ID + "\n");

            while (result.next()) { //Continues printing the contents of database rows until ResultSet reaches the end.
                String Queried_ISBN = result.getString("isbn");
                int Queried_QUANTITY = result.getInt("Quantity");
                String Queried_Name = result.getString("_name");
                System.out.println("\tName: " + Queried_Name + "\n\tQuantity: " + Queried_QUANTITY + "\n\tISBN: " + Queried_ISBN + "\n\n");
            }

            System.out.println("Press Enter key to continue...");
            System.in.read(); //Waits for the user to press enter. ONLY WORKS ON ENTER KEY.

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "\n";
    }
*/

    //Menu Function

}