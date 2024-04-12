package View;
import Controller.AddGroupMemeber;
import Controller.ViewAllGroups;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.prefs.Preferences;

public class GroupDropdown extends JFrame {
    private JComboBox<String> groupDropdown;
    private JButton joinGroupButton;

    public GroupDropdown() 
    {
        setTitle("Group Dropdown");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        ViewAllGroups viewgroups = new ViewAllGroups();
         new  ViewAllGroups();
        ArrayList<String> groupList = viewgroups.getUserGroups();
        setVisible(true);

        groupDropdown = new JComboBox<>();
        groupDropdown.setModel(new DefaultComboBoxModel<>(groupList.toArray(new String[0])));
        add(groupDropdown, BorderLayout.CENTER);

        joinGroupButton=new JButton("Join");
        add(joinGroupButton,BorderLayout.PAGE_END);

        joinGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when an item is selected
                String selectedGroup = (String) groupDropdown.getSelectedItem();
                AddGroupMemeber memeber=new AddGroupMemeber();
                memeber.addMember(selectedGroup,retrieveUserID());
                JOptionPane.showMessageDialog(null,"You are now added to the group!");
            }
        });
        
        groupDropdown.setVisible(true);
        groupDropdown.setPopupVisible(true);
        
    }
     private String retrieveUserID() 
{
        // Retrieve the user ID from preferences (or any other storage mechanism)
        Preferences prefs = Preferences.userNodeForPackage(Login.class);
        return prefs.get("userID", ""); // Return the user ID
    }
    
}

