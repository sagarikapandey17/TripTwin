package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddPost 
{
    private Connection connection;

    public AddPost() 
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
    public void addGroupPost(String groupName, String post) {
        String sql = "INSERT INTO final_project.GroupPosts (group_name, post_text) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql))
         {
            statement.setString(1, groupName);
            statement.setString(2, post);
            statement.executeUpdate();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
}
}
