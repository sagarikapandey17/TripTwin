package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class savePicture 
{
    public void saveProfilePicture(String username, String imagePath) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                File imageFile = new File(imagePath);
                FileInputStream fis = new FileInputStream(imageFile);

                // Establish connection to database
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "spandey", "Project@2024");

                // Prepare SQL statement
                String sql = "UPDATE final_project.users SET profilePicture = ? WHERE Username = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setBinaryStream(1, fis);
                pstmt.setString(2, username);

                // Execute SQL statement
                pstmt.executeUpdate();

                // Close resources
                pstmt.close();
                fis.close();
                conn.close();

                System.out.println("Profile picture saved successfully.");

            } catch (IOException | SQLException|ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }


