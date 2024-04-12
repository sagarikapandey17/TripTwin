package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class rating_review {
        private int ratingsTotal = 0;
        private int ratingsCount = 0;
        private double rating;
        private String feedback;
        private String raterUsername; // Email ID as the identifier of the user being rated
        private String username;
        
        public rating_review(String raterUsername, String username) 
        {
            this.raterUsername = raterUsername;
            this.username = username;
        }

    // Defining class rating_review for defining getters and setters 
    
        public double getrating(){
            return rating;
        }    
    
        public void setrating(double rating){
            this.rating=rating;
        } 
    
        public String getfeedback(){
            return feedback;
        }    
    
        public void setfeedback(String feedback){
            this.feedback=feedback;
        } 
    
        // Method to rate a user
        public void rateUser(int rating) {
            ratingsTotal += rating;
            ratingsCount++;
        }
    
        // Method to calculate average rating
        public double calculateAverageRating() {
            if (ratingsCount > 0) {
                return (double) ratingsTotal / ratingsCount;
            } else {
                return -1.0; 
            }
        }
    
        // Method to calculate star rating
        public String calculateStarRating() {
            if (rating >= 4.5) {
                return "*****";
            } else if (rating >= 3.5) {
                return "****";
            } else if (rating >= 2.5) {
                return "***";
            } else if (rating >= 1.5) {
                return "**";
            } else if (rating >= 0.5) {
                return "*";
            } else {
                return "";
            }
        }
// Method to update the database with rating and feedback
    public void updateDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            String url = "jdbc:mysql://localhost/final_project?";
            String Username = "spandey";
            String dbPassword = "Project@2024";
            Connection connection = DriverManager.getConnection(url, Username, dbPassword);

            String sql = "INSERT INTO final_project.ratings (raterUsername, Username, rating, feedback) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, raterUsername); // Assuming you have the email ID of the rater
            statement.setString(2, username); // Assuming you have the email ID of the rated user
            statement.setDouble(3, rating);
            statement.setString(4, feedback);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Rating and feedback added to the database successfully.");
            } else {
                System.out.println("Failed to add rating and feedback to the database.");
            }

            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error: Database update failed.");
        }
    }
}
    