package Controller;
import java.sql.*;
import java.util.ArrayList;

public class ViewTrip 
{
    public  ArrayList<String> fetchTrips(String username) 
    {
         String url = "jdbc:mysql://localhost:3306/final_project";
         String user = "spandey";
         String password = "Project@2024"; // Replace with your database URL
        String query = "SELECT destination FROM final_project.Trips where Username=?"; // 
        ArrayList<String> trips = new ArrayList<>();
        

        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url,user,password);
            PreparedStatement stmt = conn.prepareStatement(query);

            // Set the user ID parameter
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) 
            {
                String tripName = rs.getString("destination");
                trips.add(tripName);
            }
   } catch (SQLException|ClassNotFoundException e) {
       e.printStackTrace();
   }
   return trips;
}
}
