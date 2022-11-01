/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.HeaderTableModel;
import Model.InvoiceData;
import Model.ItemData;
import Model.ItemTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import view.NewInvoiceDialog;
import view.NewItemDialog;
import view.SalesInvoiceFrame;

/**
 *
 * @author Sahar.Magdy
 */
public class InvoiceListener implements ActionListener, ListSelectionListener{

    private SalesInvoiceFrame frame;
    private NewInvoiceDialog  invDialog;
    private NewItemDialog itemDialog;
    
    public InvoiceListener(SalesInvoiceFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println("btn");
        String actionCommand = e.getActionCommand();
        switch(actionCommand){
            case "New Invoice":
                newInvoice();
                break;
            case "Delete Invoice":
                delInvoice();
                break;
            case "New Item":
                newItem();
                break;
            case "Delete Item":
                delItem();
                break;
            case "Load File":
                loadFile(null,null);
                break;
            case "Save Data":
                saveData();
                break;
            case "newInvoiceOk":
                newInvoiceOk();
                break;
            case "newInvoiceCancel":
                newInvoiceCancel();
                break;
            case "newItemOk":
                newItemOk();
                break;
            case "newItemCancel":
                newItemCancel();
                break;
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
    
        int row = frame.getInvoicesTable().getSelectedRow();

        if(row>-1 && row<frame.getInvoices().size())
        {
            InvoiceData inv = frame.getInvoices().get(row);
            List<ItemData> items = inv.getItems();
            
            //Display the items of the selected invoice in item table
            frame.getItemsTable().setModel(new ItemTableModel(items));

            //Display the detials in the label section for the selected invoice from the header table
            frame.getInvNumLab().setText(""+inv.getInvNum());
            frame.getInvCusLab().setText(inv.getInvCus());
            frame.getInvDateLab().setText(""+SalesInvoiceFrame.dateFormat.format(inv.getInvDate()));
            frame.getInvTotalLab().setText(""+inv.ItemsTotal());
        } else {
            
            //remove data of the lables 
            frame.getInvNumLab().setText("");
            frame.getInvCusLab().setText("");
            frame.getInvDateLab().setText("");
            frame.getInvTotalLab().setText("");
            //remove data in item table
            frame.getItemsTable().setModel(new ItemTableModel(new ArrayList<ItemData> ()));
        }
    }
    private void newInvoice() {
        invDialog = new NewInvoiceDialog(frame);
        invDialog.setVisible(true);
     }

    private void delInvoice() {
        int row = frame.getInvoicesTable().getSelectedRow();
        if (row != -1){
            frame.getInvoices().remove(row);
            ((HeaderTableModel)frame.getInvoicesTable().getModel()).fireTableDataChanged();
    
        }
    }

    private void newItem() {
        int hRow = frame.getInvoicesTable().getSelectedRow();
        System.out.println(hRow);
        
        if(hRow > -1){
            itemDialog = new NewItemDialog(frame);
            itemDialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(frame,"Please select invoice first to be able to add item to it","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void delItem() {
        int row = frame.getItemsTable().getSelectedRow();
        if (row != -1){
            int hRow = frame.getInvoicesTable().getSelectedRow();
            ItemTableModel itemTableModel = ((ItemTableModel)frame.getItemsTable().getModel()); 
            itemTableModel.getItems().remove(row);
            itemTableModel.fireTableDataChanged();
            ((HeaderTableModel)frame.getInvoicesTable().getModel()).fireTableDataChanged();
            frame.getInvoicesTable().setRowSelectionInterval(hRow, hRow);
        }
         
    }

    public void loadFile(String headerPath, String itemPath) {
        
     
        File headerFile = null;
        File itemFile = null;
        if (headerPath == null)
        {
            JFileChooser fileCh = new JFileChooser();
            int result = fileCh.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION){
                headerFile = fileCh.getSelectedFile();
                result = fileCh.showOpenDialog(frame);
                if(result == JFileChooser.APPROVE_OPTION){
                    itemFile = fileCh.getSelectedFile();
                }
            }
        }
        else
        {
        headerFile = new File(headerPath);
        itemFile = new File(itemPath);    
        }
        
        //if there is no path for the invoice header file regardless the item file cause no use of the item file if there is no invoice file
        if(headerFile != null)
        {
            try{
                /*read and add invoice records in headerLine
                and add items records in itemlines*/
                List<String> headerRecords = Files.lines(Paths.get(headerFile.getAbsolutePath())).collect(Collectors.toList());
                List<String> itemRecords = Files.lines(Paths.get(itemFile.getAbsolutePath())).collect(Collectors.toList());
  
                //split invoice records and add in a variable
                for (String headerRecord: headerRecords)
                {
                  //split each invoice record according to the comma and add them in array of string
                  String[] invCell = headerRecord.split(",");
                  
                  //add each invoice cell to a variable
                  int invNum = Integer.parseInt(invCell[0]);
                  Date invDate = SalesInvoiceFrame.dateFormat.parse(invCell[1]);
                  String invNameS = invCell[2];
                  
                  //add each invoice line to array of invoices
                  InvoiceData inv = new InvoiceData(invNum, invDate, invNameS);
                  frame.getInvoices().add(inv);
                }
                
                //split item records and add in a variable
                for (String itemRecord: itemRecords)
                {
                    //split each invoice record according to the comma and add them in array of string
                  String[] itemCell = itemRecord.split(",");
                  //add each item cell to a variable
                  int itemNum = Integer.parseInt(itemCell[0]);
                  String itemName = itemCell[1];
                  double itemPrice = Integer.parseInt(itemCell[2]);
                  int itemCount = Integer.parseInt(itemCell[3]);
                  
                  InvoiceData inv = getInvoiceByNum(itemNum);
                  ItemData item = new ItemData(itemNum,itemName,itemPrice,itemCount,inv);
                  inv.getItems().add(item);
                }
                //frame.getInvoices())
                 frame.getInvoicesTable().setModel(new HeaderTableModel(frame.getInvoices()));
                }catch (Exception ex)
                {
                  ex.printStackTrace();
                }
           
        }
    }

    private void saveData() 
    {
        String invoicesData = "";
        String itemsData = "";
        
        for(InvoiceData invoice: frame.getInvoices()){
            invoicesData += invoice.toCSV();
            invoicesData += "\n";
            for(ItemData item:invoice.getItems()){
                itemsData += item.toCSV();
                itemsData += "\n";   
            }
        }
        JFileChooser fs = new JFileChooser();
        int result = fs.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION){
            File headerFile = fs.getSelectedFile();
            result = fs.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION){
                File itemFile = fs.getSelectedFile();
                try{
                    //write in header file
                    FileWriter headerFW = new FileWriter(headerFile);
                    headerFW.write(invoicesData);
                    headerFW.flush();
                    headerFW.close();

                    //write item file
                    FileWriter itemFW = new FileWriter(itemFile);
                    itemFW.write(itemsData);
                    itemFW.flush();
                    itemFW.close();
                }catch (IOException ioex) {
                    JOptionPane.showMessageDialog(frame,"Error happen while saving","Error",JOptionPane.ERROR_MESSAGE);        
                    
                }
                
            }
        }
    }

    private InvoiceData getInvoiceByNum(int itemNum) {
        for(InvoiceData inv: frame.getInvoices())
        {
            if(itemNum == inv.getInvNum())
            {
                return inv;
            }
                     
        }
        return null;
    }

    private void newInvoiceOk() {
        String Customer = invDialog.getCustNameField().getText();
        String date = invDialog.getInvDateField().getText();
        invDialog.setVisible(false);
        invDialog.dispose();
        int num = getNextInvoiceNo();
        try{
            Date invDate = frame.dateFormat.parse(date); 
            InvoiceData inv = new InvoiceData(num, invDate, Customer);
            frame.getInvoices().add(inv);
            ((HeaderTableModel)frame.getInvoicesTable().getModel()).fireTableDataChanged();
        } catch(ParseException pe){
            JOptionPane.showMessageDialog(frame,"Date formate is not correct.","Error",JOptionPane.ERROR_MESSAGE);        
        }
    }

    private int getNextInvoiceNo(){
        int num = 0;
        for (InvoiceData inv : frame.getInvoices()){
            if (inv.getInvNum() > num){
                num = inv.getInvNum();
            }
        }
        return num+1;
    }
    private void newInvoiceCancel() {
        invDialog.setVisible(false);
        invDialog.dispose();
    }

    private void newItemOk() {
        //get data entered in the dialog
        String itemCustomer = itemDialog.getItemNameField().getText();
        try {
            int itemCount =  Integer.parseInt(itemDialog.getItemCountField().getText());
            double itemPrice =  Integer.parseInt(itemDialog.getItemPriceField().getText());
            //remove the dialog
            itemDialog.setVisible(false);
            itemDialog.dispose();

            //get selected invoice row
            int hRow = frame.getInvoicesTable().getSelectedRow();
            InvoiceData inv = frame.getInvoices().get(hRow);
            int no = inv.getInvNum();
            ItemData item = new ItemData(no, itemCustomer, itemPrice, itemCount, inv);
            inv.getItems().add(item);

            //Update item table
            ItemTableModel itemTableModel = ((ItemTableModel)frame.getItemsTable().getModel());
            itemTableModel.fireTableDataChanged();
            //Update Invoice table and labels to update the totals
            ((HeaderTableModel)frame.getInvoicesTable().getModel()).fireTableDataChanged();
            frame.getInvoicesTable().setRowSelectionInterval(hRow, hRow);        
        }catch (NumberFormatException nex){
            JOptionPane.showMessageDialog(frame,"Item Count and/or Item Price are/is not correct.","Error",JOptionPane.ERROR_MESSAGE);   
        }       
    }

    private void newItemCancel() {
        itemDialog.setVisible(false);
        itemDialog.dispose();
    }
    
}
