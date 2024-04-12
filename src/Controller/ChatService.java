package Controller;
import java.sql.*;

public class ChatService {
    private Connection connection;
   
    private void connectToDatabase() {
        try {
            // Connect to your database
            String url = "jdbc:mysql://localhost:3306/final_project";
            String user = "spandey";
            String password = "Project@2024";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String loggedInUser,String receiver, String messageText) {
        try {
            connectToDatabase();
            String sql = "INSERT INTO final_project.messages (senderUsername, receiverUsername, message_content) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, loggedInUser);
            statement.setString(2, receiver);
            statement.setString(3, messageText);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getMessages(String loggedInUser) {
        try {
            connectToDatabase();
            String sql = "SELECT senderUsername,message_content FROM final_project.messages WHERE receiverUsername = ? OR senderUsername = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, loggedInUser);
            statement.setString(2, loggedInUser);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public String getMessageDetails(String Username, String loggedInUser) throws SQLException {
        ResultSet rs=null;
        String messageDetails=null;
        try {
            connectToDatabase();
            System.out.println("Connected");
            String sql = "SELECT senderUsername,message_content FROM final_project.messages WHERE receiverUsername = ? AND senderUsername = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, loggedInUser);
            statement.setString(2, Username);
            System.out.println(statement.executeQuery());
            rs=statement.executeQuery();
            System.out.println("executed");
            
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        while(rs.next())
        {
           String senderName=rs.getString("senderUsername");
           String message=rs.getString("message_content");
           System.out.println(senderName+message);
           messageDetails=(senderName+":"+"\n" +message+"/n");
           
        }
        System.out.println(messageDetails);
        return messageDetails;
    }
}