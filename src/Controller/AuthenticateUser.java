package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.User;

public class AuthenticateUser 
{
    private boolean authStatus=false;
    private int userid;
    private String passString=null;
    private String storedPassword=null;
    private Connection conn;
    public  boolean authenticateUser(String username, char[] password) 
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Open a connection
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/final_project","spandey","Project@2024");
            passString=new String(password);
            String query = "SELECT Password,User_ID FROM final_project.users WHERE Username=?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
            {
                userid=resultSet.getInt("User_ID");
                storedPassword=resultSet.getString("Password");
            }
            
        
        }
        catch (SQLException|ClassNotFoundException e) 
    {
            e.printStackTrace();
}
return passString.equals(storedPassword);
}
public int getuserid()
{
   return userid;
}
}