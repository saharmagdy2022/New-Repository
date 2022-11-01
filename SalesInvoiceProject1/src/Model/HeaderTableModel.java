/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import view.SalesInvoiceFrame;

/**
 *
 * @author Sahar.Magdy
 */
public class HeaderTableModel extends AbstractTableModel{
    
    private List<InvoiceData> invoices;

    //ArrayList<InvoiceData> invoices
    public HeaderTableModel(List<InvoiceData> invoices) {
        this.invoices = invoices;
    }
    
    @Override
    public int getRowCount(){
        return invoices.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex){
        switch (columnIndex){
            case 0:
                return "No.";
            case 1:
                return "Date";
            case 2:
                return "Customer";
            case 3:
                return "Total";
                    
        }
        return "";
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //get first item which will indeicate the row and then get the column using the switch
        InvoiceData inv = invoices.get(rowIndex);
        switch (columnIndex){
            case 0:
                return inv.getInvNum(); 
            case 1:
                return SalesInvoiceFrame.dateFormat.format(inv.getInvDate());
            case 2: 
                return inv.getInvCus();
            case 3:
                return inv.ItemsTotal();
        
        } 
        return "";
    }
}
