package View;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Controller.AddGroupMemeber;
import Controller.SearchMember;
import Controller.UserProfileLogic;
import Model.User;

public class SearchPanel extends JFrame {

    private JTextField searchTextField;
    private JButton searchButton;
    private List<String> memberList;

    public SearchPanel() {
        setLayout(new FlowLayout());

        searchTextField = new JTextField(20);
        searchButton = new JButton("Search");

        add(new JLabel("Search Member:"));
        add(searchTextField);
        add(searchButton);

        // Add action listener to search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchName = searchTextField.getText();
                if (searchName != null && !searchName.isEmpty()) {
                    SearchMember members = new SearchMember();
                    setMemberList(members.searchMembers(searchName));
                    displaySearchResults();
                } else 
                {
                    JOptionPane.showMessageDialog(SearchPanel.this, "Please enter a member name to search.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true);
    }

    // Method to set the member list
    public void setMemberList(List<String> members) {
        this.memberList = members;
    }

    // Method to display search results with hyperlinks
    private void displaySearchResults() {
        if (memberList != null && !memberList.isEmpty()) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            JLabel label = new JLabel("Matching member(s) found:");
            panel.add(label);

            for (String member : memberList) {
               JPanel memberPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    
    JLabel memberLabel = new JLabel(member);
    memberPanel.add(memberLabel);
    
    JButton addMemberButton = new JButton("Add Member");
    addMemberButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            AddGroupMemeber addMember=new AddGroupMemeber();
            addMember.addMember(retrieveGroupname(), member);
        }
    });
    memberPanel.add(addMemberButton);
    
    panel.add(memberPanel);
               
            }
            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setPreferredSize(new Dimension(300, 200));
            JOptionPane.showMessageDialog(SearchPanel.this, scrollPane, "Members Found",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(SearchPanel.this, "No matching member found.", "Member Not Found",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
    private String retrieveGroupname() 
{
        // Retrieve the user ID from preferences (or any other storage mechanism)
        Preferences prefs = Preferences.userNodeForPackage(GroupPanel.class);
        return prefs.get("groupname", ""); // Return the user ID
    }
}
