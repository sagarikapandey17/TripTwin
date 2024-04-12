package Controller;
import java.sql.*;

import Model.User;


public class AddTripLogic 
{
    


    String url = "jdbc:mysql://localhost:3306/final_project";
    String user = "spandey";
    String password = "Project@2024";
    private Connection conn;

    public AddTripLogic(String username,String destination, String startDate, String endDate, double budget, String description) 
    {
      
       
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,this.user,password);
            String query = "INSERT INTO final_project.Trips (Username,Destination, StartDate, EndDate, Budget, Description) VALUES (?,?,?,?,?,?)";
            try (PreparedStatement statement = conn.prepareStatement(query)) 
            {
                statement.setString(1, username);
                statement.setString(2, destination);
                statement.setString(3, startDate);
                statement.setString(4, endDate);
                statement.setDouble(5, budget);
                statement.setString(6, description);
                statement.executeUpdate();
                System.out.println("Trip added successfully.");
            }
        } 
        catch (SQLException|ClassNotFoundException e) 
        {
            e.printStackTrace();
        }
    }
    
}




