package View;
import javax.swing.*;
import javax.swing.event.*;
import Controller.ChatService;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.prefs.Preferences;

public class ChatGUI extends JFrame 
{
    private JTextPane chatPane;
    private JTextField messageField;
    private JButton sendButton;
   private ChatService chatDatabase;
    private String username;

    public ChatGUI() 
    {
        
        setTitle("Chat");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
        loadUsernames();

        setLocationRelativeTo(null);
        setVisible(true);
    }
    private String retrieveUserID() 
    {
        // Retrieve the user ID from preferences (or any other storage mechanism)
        Preferences prefs = Preferences.userNodeForPackage(Login.class);
        return prefs.get("userID", ""); // Return the user ID
    }
    private void initComponents() 
    {
        JPanel mainPanel = new JPanel(new BorderLayout());
        chatPane = new JTextPane();
        chatPane.setContentType("text/html");
        chatPane.setEditable(false);
        chatPane.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) 
            {
                handleHyperlinkClick(e);
            }

            private void handleHyperlinkClick(MouseEvent e) 
            {
                loadMessagesForUser(username);
            }
         });

        JScrollPane scrollPane = new JScrollPane(chatPane);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel messagePanel = new JPanel(new BorderLayout());
        messageField = new JTextField();
        messagePanel.add(messageField, BorderLayout.CENTER);

        sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        messagePanel.add(sendButton, BorderLayout.EAST);

        mainPanel.add(messagePanel, BorderLayout.SOUTH);
       add(mainPanel);
    }
    private void loadMessagesForUser(String username2) 
    {
        StringBuilder messages = new StringBuilder();
        String messageDetails=null;
        chatDatabase=new ChatService();
        System.out.println(username);
        try 
        {
            messageDetails = chatDatabase.getMessageDetails(username2,retrieveUserID()) ;
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        if (messageDetails != null) 
            {
                messages.append(messageDetails);
                            
            }
            
            chatPane.setText(messages.toString());
        }   
        
    private void loadUsernames() 
    {
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<html><body>");
        chatDatabase=new ChatService();
        ResultSet resultSet = chatDatabase.getMessages(retrieveUserID());
        if (resultSet != null) 
        {
            try 
            {
                while (resultSet.next()) 
                {
                    String username = resultSet.getString("senderUsername");
                    
                    htmlContent.append("<a href=\"").append(username).append("\">").append(username).append("</a><br>");
                }
            } 
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
        }
        htmlContent.append("</body></html>");
        chatPane.setText(htmlContent.toString());
        chatPane.addHyperlinkListener(new HyperlinkListener() 
        {
        @Override
        public void hyperlinkUpdate(HyperlinkEvent e) 
        {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) 
            {
                // When a hyperlink is clicked
                try 
                {
                     username = e.getDescription(); // Get the username from the hyperlink
                    // Now you can use the 'username' as needed
                    System.out.println("Clicked username: " + username);
                    
                    // Example: Open a web browser with the link
                    Desktop.getDesktop().browse(new java.net.URI(username));
                }
                catch ( Exception ex) 
                {
                    ex.printStackTrace();
                }
            }
        }
    });
}
    private void sendMessage() 
    {
        String messageText = messageField.getText();
        if (!messageText.isEmpty()) 
        {
            chatDatabase.sendMessage(retrieveUserID(),username, messageText);
            loadMessagesForUser(username);
            messageField.setText("");
        }
    }
}
    

