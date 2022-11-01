/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;
import java.util.ArrayList;
import view.SalesInvoiceFrame;

/**
 *
 * @author Sahar.Magdy
 */
public class InvoiceData {
    
    private int invNum;
    private String invCus;
    private Date invDate;
    private ArrayList<ItemData> items; 

    public int getInvNum() {
        return invNum;
    }

    public void setInvNum(int invNum) {
        this.invNum = invNum;
    }

    public String getInvCus() {
        return invCus;
    }

    public void setInvCus(String invCus) {
        this.invCus = invCus;
    }

    public Date getInvDate() {
        return invDate;
    }

    public void setInvDate(Date invDate) {
        this.invDate = invDate;
    }

    public ArrayList<ItemData> getItems() {
        if (items == null)
        {
            items = new ArrayList<>();
        }
        return items;
    }

    public InvoiceData(int invNum, Date invDate, String invCus) {
        this.invNum = invNum;
        this.invDate = invDate;
        this.invCus = invCus;
    }

    public void setItems(ArrayList<ItemData> items) {
        this.items = items;
    }
    
    public double ItemsTotal()
    {
        if (items != null){
            
        
        double sum = 0;
        for (int i =0; i<items.size(); i++)
        {
            sum += items.get(i).EachItemTotal();
        }
       return sum;
        }
        return 0;
    }
    
    public String toCSV(){
        return invNum +","+ SalesInvoiceFrame.dateFormat.format(invDate) +","+ invCus;
    }
}
