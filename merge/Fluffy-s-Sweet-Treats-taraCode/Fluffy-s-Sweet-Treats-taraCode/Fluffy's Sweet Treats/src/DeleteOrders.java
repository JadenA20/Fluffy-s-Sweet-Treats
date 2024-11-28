//Author: Tara-Lee Donald
//Last Modified: November 26, 2024

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;


public class DeleteOrders extends JFrame {
    private JTextField enterID;
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JButton save;
    private JButton cancel;
    private ArrayList<Current> orderList;
    OrderFile ofile = new OrderFile();

    int choice = 0;
    // Class for the Delete Orders GUI

    public DeleteOrders(){
        setTitle("Order Deletion");

        mainPanel = new JPanel();
        buttonPanel = new JPanel();

        mainPanel.add(new JLabel("Enter the ID number of entry to be deleted: "));
        enterID = new JTextField(5);
        mainPanel.add(enterID);

        save = new JButton("OK");
        cancel = new JButton("Cancel");

        mainPanel.setLayout(new GridLayout(1,2));

        buttonPanel.add(save);
        buttonPanel.add(cancel);

        save.addActionListener(new SaveButtonListener());
        cancel.addActionListener(new CloseButtonListener());

        orderList = ofile.load2();

        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }

    private class SaveButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try{

                Current target = new Current();

                for(Current o: orderList){
                    if(enterID.getText().equals(String.valueOf(o.getID()))){
                        target = o;

                    }
                }

                if(target != null){
                    choice = JOptionPane.showConfirmDialog(DeleteOrders.this, "Are you sure you want to delete this order? This action is permanent.", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

                    if(choice == JOptionPane.YES_OPTION){
                        //int id_num = Integer.parseInt(enterID.getText().trim());

                        ofile.deleteFromCurrentFile(orderList, target);
                        setVisible(false);
                    }
                    else{
                        setVisible(false);
                    }

                }
                

                
            }
            catch(NumberFormatException nfe){
                JOptionPane.showMessageDialog(DeleteOrders.this, "ID number invalid. Please ensure a valid ID number is entered.", "Invalid ID", JOptionPane.ERROR_MESSAGE);
            }
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
