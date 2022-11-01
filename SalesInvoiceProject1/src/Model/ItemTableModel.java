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
public class ItemTableModel extends AbstractTableModel{

    private String[] cols = {"No.", "Item Name", "Item Price", "Count", "Item Total"};
    private List<ItemData> items;

    public ItemTableModel(List<ItemData> items) {
        this.items = items;
    }

    @Override
    public int getRowCount() {
        return items.size(); 
    }
    
    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ItemData item = items.get(rowIndex);
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

    public List<ItemData> getItems() {
        return items;
    }
    
    @Override
    public String getColumnName(int columnIndex){
        return cols[columnIndex];
    }
          
}
