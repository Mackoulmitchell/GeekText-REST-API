package com.springboot.app.Path;

import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
public class User extends BaseMethods {
    private String name;
    private String email;
    private String password;
    private String homeAddress;

    private String creditCards;
    public User() {

    }

    public User(String name, String email, String password, String homeAddress){
        this.name = name;
        this.email = email;
        this.password = password;
        this.homeAddress = homeAddress;
        this.creditCards= "";
    }
    public String getName(String email) {
        String queryResult = null;
        try {

            ResultSet result = SQLQuery("SELECT * FROM profile WHERE email = " + email + ";");

            while(result.next()){

                queryResult = result.getString("_name");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return queryResult;
    }
    public String getEmail() {
    	return email;
    }
    public String getPassword(String email) {
        String queryResult = null;
        try {

            ResultSet result = SQLQuery("SELECT * FROM profile WHERE email = " + email + ";");

            while(result.next()){

                queryResult = result.getString("_password");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return queryResult;
    }
    public String getAddress(String email) {
        String queryResult = null;
        try {

            ResultSet result = SQLQuery("SELECT * FROM profile WHERE email = " + email + ";");

            while(result.next()){

                queryResult = result.getString("homeAdd");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return queryResult;
    }
    public String getCreditCards(String email) {
        String queryResult = null;
        try {

            ResultSet result = SQLQuery("SELECT * FROM profile WHERE email = '" + email + "'");

            while(result.next()){

                queryResult = result.getString("card");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return queryResult;}

    public void printUser(){
        this.printName();
        this.printEmail();
        this.printPassword();
        this.printHomeAdd();
        this.printCreditCards();
    }

    public void printName(){
        System.out.println("Name : " + name);
    }

    public void printEmail(){
        System.out.println("Email : " + email);
    }

    public void printPassword(){
        System.out.println("Password : " + password);
    }

    public void printHomeAdd(){
        System.out.println("Home Address : " + homeAddress);
    }

    public void printCreditCards(){
        System.out.println("Credit Cards : " + creditCards);
    }

    @PostMapping("/User/Insert")
    @ResponseBody
    public String insertToProfile(@RequestParam String name, @RequestParam String email, @RequestParam String password, @RequestParam String homeAdd, @RequestParam String card){
        if(getUser(email)==null)
        {
            SQLUpdate("INSERT INTO profile (_name, email, _password, homeAdd, card) VALUES ('"+ name +"', '"+ email +"', '"+ password +"', '"+ homeAdd +"', '"+ card +"')");
            return("Profile has been inserted into database.");
        }else{
            return ("A User with that Email already exists.");
        }
    }

    @GetMapping("/User")
    @ResponseBody
    public String getUser(@RequestParam String username){

    	String queryResult = null;

        try {

        	ResultSet result = SQLQuery("SELECT * FROM profile WHERE email = '"+ username + "'");

        	while(result.next()) {
                queryResult = (" Name: "+ result.getString("_name") + " | Email: "+ result.getString("email") + " | Password: " + result.getString("_password") + " | Home Address: "+ result.getString("homeAdd") + " | Credit Cards: "+ result.getString("card") + " ||| ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return queryResult;
    }
    @PatchMapping("/User/UpdateName")
    @ResponseBody
    public String updateName(@RequestParam String name, @RequestParam String email)
    {
        SQLUpdate("UPDATE profile SET _name = '" + name + "' WHERE email = '" + email + "'");
        return("Successfully updated name to:" + name);
    }
    @PatchMapping("/User/UpdatePassword")
    @ResponseBody
    public String updatePassword(@RequestParam String password, @RequestParam String email)
    {
        SQLUpdate("UPDATE profile SET _password = '" + password + "' WHERE email = '" + email + "'");
        return("Successfully updated password to:" + password);
    }
    @PatchMapping("/User/UpdateAddress")
    @ResponseBody
    public String updateHomeAddress(@RequestParam String homeAdd, @RequestParam String email)
    {
        SQLUpdate("UPDATE profile SET homeAdd = '" + homeAdd + "' WHERE email = '" + email + "'");
        return("Successfully updated home Address to:" + homeAdd);
    }
    @PatchMapping("/User/UpdateCard")
    @ResponseBody
    public String addCard(@RequestParam String card, @RequestParam String email)
    {
        String newCard = getCreditCards(email) + ", " + card ;
        SQLUpdate("UPDATE profile SET card = '" +  newCard + "' WHERE email = '" + email + "'");
        return("Successfully added card:" + card);
    }

}
