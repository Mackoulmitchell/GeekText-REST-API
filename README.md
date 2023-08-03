# GeekText API

GeekText is a REST API developed with a Java backend and Springboot framework. It uses a PostgreSQL database for data storage, allowing users to browse books and add them to a shopping cart.

## Features
Current features of the GeekText API include:

- Adding users and books to a database.
- Browsing books through various parameters, such as by genre, sales, or rating.
- Creating a shopping cart and adding books to it.

## API Endpoints
Users can access the following API methods through web URLs:

- `GET /books/details`: Takes a string parameter 'ISBN'. Retrieves the book that matches the ISBN.
- `GET /author/books`: Takes a string parameter 'Author'. Retrieves a list of all books that match the provided Author.
- `GET /books/genre`: Retrieves a list of books by genre.
- `GET /books/topSold`: Retrieves a list of the top 6 best selling books.
- `GET /books/rating`: Takes a string parameter 'query'. Retrieves a list of books above the rating specified (out of five).
- `GET /books/quantity`: Takes an integer parameter 'quantity'. Retrieves a list of 'quantity' books at a time from a given position in the overall recordset.
- `POST /addBook`: Takes eight parameters (string author, string title, int isbn, double cost, string description, string publisher, int year, int copiesSold) in a specific order. Adds a book to the database with the given attributes.
- `POST /cart/Add`: Takes two parameters (string isbn, int quantity). Adds a book to the shopping cart using the isbn parameter, of the quantity specified in the 'quantity' parameter. Merges duplicate database entries.
- `DELETE /cart/remove`: Deletes a book from the user's shopping cart.
- `GET /cart/`: Takes a string parameter 'id'. Returns the user's shopping cart as an ArrayList of Strings.
- `GET /user`: Takes a string parameter 'username'. Retrieves the details of the user (Name, Email, Password, and Home Address).
- `POST /User/Insert`: Takes strings 'name', 'email', 'password', 'homeAdd', 'card'. Adds a new user to the database.
