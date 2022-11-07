/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Sahar.Magdy
 */
public class LineTableModel extends AbstractTableModel{

    private String[] columns = {"No.", "Item Name", "Item Price", "Count", "Item Total"};
    private List<InvoiceLine> lines;

    public LineTableModel(List<InvoiceLine> items) {
        this.lines = items;
    }

    @Override
    public int getRowCount() {
        return lines.size(); 
    }
    
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceLine item = lines.get(rowIndex);
        switch (columnIndex){
            case 0:
                return item.getItemNum(); 
            case 1:
                return item.getItemName();
            case 2: 
                return item.getItemPrice();
            case 3: 
                return item.getItemCount();
            case 4: 
                return item.EachItemTotal();
        }
        return "";
    }

    public List<InvoiceLine> getItems() {
        return lines;
    }
    
    @Override
    public String getColumnName(int columnIndex){
        return columns[columnIndex];
    }
          
}
