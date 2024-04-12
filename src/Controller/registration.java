package Controller;
// Defining class registration for defining getters and setters 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class registration 
{
      
 public void Registration(String firstName,String lastName,String emailId,String username,String password,int age,String gender,String address) throws ClassNotFoundException, SQLException
 {
    
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            String url = "jdbc:mysql://localhost:3306/final_project";
            String Username = "spandey";
            String dbPassword = "Project@2024";
            Connection connection = DriverManager.getConnection(url, Username, dbPassword);

            

            String sql = "INSERT INTO final_project.users (First_Name, Last_Name, Email, Password,Username, Age, Gender, Address) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, emailId);
            statement.setString(4, password);
            statement.setString(5, username);
            statement.setInt(6, age);
            statement.setString(7, gender);
            statement.setString(8, address);
            statement.executeUpdate();

            
            statement.close();
            connection.close();
        } 
           
        }
    
    
    
            
