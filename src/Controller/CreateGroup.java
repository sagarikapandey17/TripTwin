package Controller;
import java.sql.*;
public class CreateGroup 
{
    private Connection connection;

    public CreateGroup() 
    {
        try 
        {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "spandey", "Project@2024");
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    // Method to create a new group
    public void createGroup(String groupName) 
    {
        try {
            String insertQuery = "INSERT INTO final_project.Groups (group_name) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, groupName);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
