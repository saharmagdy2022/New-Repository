/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Sahar.Magdy
 */
public class ItemData {
    
    private int itemNum;
    private String itemName;
    private double itemPrice;
    private int itemCount;
    
    private InvoiceData invoices;

    public ItemData(int itemNum, String itemName, double itemPrice, int itemCount, InvoiceData invoices) {
        this.itemNum = itemNum;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
        this.invoices = invoices;
    }

    
    public int getItemNum() {
        return itemNum;
    }

    public void setItemNum(int itemNum) {
        this.itemNum = itemNum;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public InvoiceData getInvoices() {
        return invoices;
    }

    public void setInvoices(InvoiceData invoices) {
        this.invoices = invoices;
    }
    
    public double EachItemTotal()
    {
        double sum;  
        sum = itemCount * itemPrice;
        return sum;
    }
    
    public String toCSV(){
        return invoices.getInvNum() +","+ itemName +","+ itemPrice +","+ itemCount;
    }
}
