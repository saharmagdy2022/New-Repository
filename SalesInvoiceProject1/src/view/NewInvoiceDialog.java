/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Sahar.Magdy
 */
public class NewInvoiceDialog extends JDialog {

    
    private JTextField custNameField;
    private JTextField invDateField;
    private JLabel custNameLab;
    private JLabel invDateLab;
    private JButton okBtn;
    private JButton cancelBtn;
    
    public NewInvoiceDialog(SalesInvoiceFrame frame) {
        custNameLab = new JLabel("Customer Name:");
        custNameField = new JTextField(20);
        invDateLab = new JLabel("Invoice Date:");
        invDateField = new JTextField(20);
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        
        okBtn.setActionCommand("newInvoiceOk");
        cancelBtn.setActionCommand("newInvoiceCancel");
        
        okBtn.addActionListener(frame.getListener());
        cancelBtn.addActionListener(frame.getListener());
        
        // The dialog has 3 rows and 2 columns
        setLayout(new GridLayout(3,2));
        
        add(invDateLab);
        add(invDateField);
        add(custNameLab);
        add(custNameField);
        add(okBtn);
        add(cancelBtn);
        
        setModal(true);
        //Resize the dialog to fit the components
        pack();
    }

    public JTextField getCustNameField() {
        return custNameField;
    }

    public JTextField getInvDateField() {
        return invDateField;
    }
    
    
    
}
