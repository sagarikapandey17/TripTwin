package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;

public class getProfilePicture 
{
   private ImageIcon profileImageIcon = null;
   public ImageIcon setProfilePicture(String Username) 
   {
    
    try {
        // Establish connection to database
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "spandey", "Project@2024");

        // Prepare SQL statement
        String sql = "SELECT profilePicture FROM final_project.users WHERE Username= ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, Username);

        // Execute query to retrieve profile picture
        ResultSet rs = pstmt.executeQuery();

        // Check if result set contains data
        if (rs.next()) {
            // Get the profile picture bytes from the result set
            byte[] imageBytes = rs.getBytes("profilePicture");

            // Convert image bytes to ImageIcon
            if (imageBytes != null) {
                profileImageIcon = new ImageIcon(imageBytes);
            }
        }

        // Close resources
        rs.close();
        pstmt.close();
        conn.close();

    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return profileImageIcon;
}
}
