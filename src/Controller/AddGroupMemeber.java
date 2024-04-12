package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddGroupMemeber 
{
    private Connection connection;

    public AddGroupMemeber() 
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
    
    public void addMember(String groupName, String memberName) {
        try {
            String insertQuery = "INSERT INTO final_project.GroupMembers (group_name, member_name) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, groupName);
            preparedStatement.setString(2, memberName);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
