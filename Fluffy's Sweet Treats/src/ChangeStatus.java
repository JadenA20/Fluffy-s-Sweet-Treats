//Author:
//Last Modified:

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class ChangeStatus extends JFrame {
    private JTextField enterID;
    private JPanel mainPanel, buttonPanel;
    private JButton save,  cancel;
    private ViewCurrent viewCurrent;
    private User userAccount;
    private Admin adminAccount;


    public ChangeStatus(ViewCurrent viewCur, User userAcc, Admin adminAcc){
        this.viewCurrent = viewCur;
        this.userAccount = userAcc;
        this.adminAccount = adminAcc;

        setTitle("Change Order Status");

        mainPanel = new JPanel();
        buttonPanel = new JPanel();

        mainPanel.add(new JLabel("Enter the ID number of order to be converted: "));
        enterID = new JTextField(5);
        mainPanel.add(enterID);

        save = new JButton("OK");
        cancel = new JButton("Cancel");

        mainPanel.setLayout(new GridLayout(1,2));

        buttonPanel.add(save);
        buttonPanel.add(cancel);

        save.addActionListener(new SaveButtonListener());
        cancel.addActionListener(new CloseButtonListener());


        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
        setVisible(true);
        
    }

    private class SaveButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){

        }
    }

    private class CloseButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
        }
    }

}
