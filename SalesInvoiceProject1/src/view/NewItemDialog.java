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
public class NewItemDialog extends JDialog {
    private JTextField itemNameField;
    private JTextField itemCountField;
    private JTextField itemPriceField;
    private JLabel itemNameLab;
    private JLabel itemCountLab;
    private JLabel itemPriceLab;
    private JButton okBtn;
    private JButton cancelBtn;

    public NewItemDialog(InvFrame frame) {
        itemNameField = new JTextField(20);
        itemNameLab = new JLabel("Item Name:");
        
        itemCountField = new JTextField(20);
        itemCountLab = new JLabel("Item Count:");
        
        itemPriceField = new JTextField(20);
        itemPriceLab = new JLabel("ITem Price");
        
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        
        okBtn.setActionCommand("newItemOk");
        cancelBtn.setActionCommand("newItemCancel");
        
        okBtn.addActionListener(frame.getListener());
        cancelBtn.addActionListener(frame.getListener());
        
        // The dialog has 4 rows and 2 columns
        setLayout(new GridLayout(4,2));
        
        add(itemNameLab);
        add(itemNameField);
        add(itemCountLab);
        add(itemCountField);
        add(itemPriceLab);
        add(itemPriceField);
        add(okBtn);
        add(cancelBtn);
        
        setModal(true);
        //Resize the dialog to fit the components
        pack();
    }

    public JTextField getItemNameField() {
        return itemNameField;
    }

    public JTextField getItemCountField() {
        return itemCountField;
    }

    public JTextField getItemPriceField() {
        return itemPriceField;
    }
    
    
    
}
