package BankingManagementSystem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.sql.Connection;

public class User {

    private Connection connection;
    private Scanner sc;

    public User(Connection connection, Scanner sc){
        this.connection = connection;
        this.sc = sc;
    }

    public void register(){

        sc.nextLine();
        System.out.println("Full Name: ");
        String full_name = sc.nextLine();
        System.out.println(("Email: "));
        String email = sc.nextLine();
        System.out.println("Password: ");
        String password = sc.nextLine();

        if(userExist(email)){
            System.out.println("User already exists for this email!");
            return;
        }

        String registerQuery = "INSERT INTO User(full_name, email, password) VALUE(?, ?, ?)";
        try{

            PreparedStatement preparedStatement = connection.prepareStatement(registerQuery);
            preparedStatement.setString(1, full_name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0 ){
                System.out.println("Registration Successful!l");
            } else {
                System.out.println("Registration Failed!");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public String login(){
        sc.nextLine();
        System.out.println(("Email: "));
        String email = sc.nextLine();
        System.out.println("Password: ");
        String password = sc.nextLine();
        String loginQuery = "SELECT * FROM User WHERE email = ? AND password = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(loginQuery);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return email;
            } else {
                return null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean userExist(String email){
        String query = "SELECT * FROM user WHERE email = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            } else {
                return false;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
