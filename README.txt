GeekText is a REST API that is being developed using a Java backend and Springboot framework. It uses a PostgreSQL database to allow users to 
store their data, browse books, and add them to a shopping cart. 

-------------------
Users can: 

-Add users and books to a database.

-Browse books through various parameters, such as by genre, sales, or rating.

-Create a shopping cart and add books to it.

-------------------

Features in development:

-Retrieving user details by their username.

-Updating user details.

-Adding credit card payment methods.

-------------------

Users can access the following API methods through web URLs:

'/books/details': Takes a String 'ISBN' as a parameter. Retrieves the book that matches the ISBN. 

'/author/books': Takes a String 'Author' as a parameter. Retrieves a list of all books that match the provided Author.

'/books/genre': Retrieves a list of books by genre.

'/books/topSold': Retrieves a list of the top 6 best selling books.

'/books/rating' : Takes a String 'query' as a parameter. Retrieves a list of books above the rating specified (out of five).

'/books/quantity': Takes an integer 'quantity' as a parameter. Retrieves a list of 'quantity' books at a time where the parameter is an integer from a given position in the overall recordset. (Example: Passing 5 would yield 5 books from the 5th position in the record set onward.)

'/addBook': Takes eight parameters (String author, String title, int isbn, double cost, String description, String publisher, int year, int copiesSold) in a specific order. Adds a book to the database with the given attributes. 

'/cart/Add': Takes two parameters (String isbn, int quantity). Adds a book to the shopping cart using the isbn parameter, of the quantity specified in the 'quantity' parameter. Will merge duplicate database entries.

'/cart/remove': Deletes a book from the user's shopping cart.

'/cart/': Takes a String 'id' as a parameter. Returns the user's shopping cart as an ArrayList of Strings.

'/user': Takes a String 'username' as a parameter. Retrieves the details of the user (Name, Email, Password, and Home Address).

'/User/Insert': Takes Strings 'name', 'email', 'password', 'homeAdd', 'card'. Adds a new user to the database.
