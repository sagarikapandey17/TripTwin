package Controller;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanionFinder 
{
    private Connection connection;

    

    

    // Method to match trips based on user preferences
    public ArrayList<String> findCompanion(String selectedTrip,String loggedInUser) 
    {
        ArrayList<String> companions = new ArrayList<>();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "spandey", "Project@2024");
            PreparedStatement stmt = connection.prepareStatement("SELECT Username, Age FROM final_project.users WHERE Username IN (SELECT Username FROM final_project.Trips WHERE destination=? and Username!=?)");
            stmt.setString(1, selectedTrip);
            stmt.setString(2, loggedInUser);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String username = rs.getString("Username");
                String age = rs.getString("Age");
                companions.add(username);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companions;
    }
    
}