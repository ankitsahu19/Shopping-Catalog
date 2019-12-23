/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcatalog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import shoppingcatalog.dbutil.DBConnection;
import shoppingcatalog.dto.ItemDTO;
import shoppingcatalog.dto.ItemInfoDTO;
import shoppingcatalog.dto.OrderDTO;
import shoppingcatalog.dto.OrderInfoDTO;

/**
 *
 * @author hp
 */
public class StoreDAO {
    private static  PreparedStatement ps1,ps2,ps3,ps4,ps5,ps6,ps7;
    private static  Statement st;
    private static Connection conn;
    static 
    {
        conn =   DBConnection.getConnection();
        try 
        {
         ps1 = DBConnection.getConnection().prepareStatement("select id, item_name from store_items where item_type = ?");
         ps2 = conn.prepareStatement("select * from store_items where id=?" );
         ps3 = conn.prepareStatement("Insert into order_master values(?,?,?,?)");
         ps4 = conn.prepareStatement("Insert into order_details values (?,?,?)");
         ps5 = conn.prepareStatement("Select count(*) as count from order_master");
         ps6= conn.prepareStatement("select order_Id,order_amount,order_date from order_master where cust_name=?");
         ps7=conn.prepareStatement("select * from order_details where order_name=?");
        }
        catch(Exception ex)
        {
            System.out.println("exception in store dao "+ex);
            ex.printStackTrace();
        }
    }
    
    public static ArrayList<String> getItemTypes()throws SQLException
    {
     
      ArrayList<String> ar = new ArrayList<>();
     
      st = conn.createStatement();
      ResultSet rs = st.executeQuery("select distinct item_type from store_items");
      
      while(rs.next())
      {
         ar.add(rs.getString(1));
      }
      return ar;
    }
    public static ArrayList<ItemInfoDTO>getItemsByType(String item_type)throws SQLException 
    {
            ArrayList<ItemInfoDTO> itemdtos =  new ArrayList<ItemInfoDTO>();
            ps1.setString(1,item_type);
            ResultSet rs = ps1.executeQuery();
            while(rs.next())
              {
                  ItemInfoDTO i = new ItemInfoDTO();
                  i.setItemId(rs.getInt(1));
                  i.setItemName(rs.getString(2));
                  itemdtos.add(i);
              }
          return itemdtos;  
    }
    public static ItemDTO getItemDetails(int itemid)throws SQLException
    {
        ItemDTO obj =  null;
        ps2.setInt(1, itemid);
       ResultSet rs =  ps2.executeQuery();
        if(rs.next())
        {
            obj = new ItemDTO();
            obj.setItemDesc(rs.getString("item_desc"));
            obj.setItemId(rs.getInt("id"));
            obj.setItemImage(rs.getString("item_image"));
            obj.setItemName(rs.getString("item_name"));
            obj.setItemType(rs.getString("item_type"));
            obj.setItemPrice(rs.getDouble("item_price")); 
        }
        return obj;
    }
    public static boolean addOrder(String cust_name,ArrayList<ItemDTO>itemList,double totalAmount)throws SQLException
    {
       ResultSet rs =  ps5.executeQuery();
       rs.next();
       int lastId = rs.getInt(1);
       String nextId = "ORD-00"+(lastId+1);
       ps3.setString(1,nextId);
       ps3.setString(2, cust_name);
       ps3.setDouble(3,totalAmount );
       java.util.Date today  = new java.util.Date();
       long ms = today.getTime();
       java.sql.Date currDate = new java.sql.Date(ms);
       ps3.setDate(4,currDate);
       int ans1 = ps3.executeUpdate();
       int count=0;
       for(ItemDTO item:itemList)
       {
           ps4.setString(1, nextId);
           ps4.setString(2, item.getItemName());
           ps4.setDouble(3, item.getItemPrice());
           int ans2 = ps4.executeUpdate();
           if(ans2==1)
           {
               ++count;
           }
       }
       return (count==itemList.size()&&ans1==1);
    }

public static ArrayList<OrderDTO> getOrdersByCustomer(String custName)throws SQLException
{
ArrayList<OrderDTO> orderList=new ArrayList<OrderDTO>();
ps6.setString(1, custName);
ResultSet rs=ps6.executeQuery();
while(rs.next())
{
OrderDTO obj=new OrderDTO();
obj.setOrderId(rs.getString(1));
obj.setOrderAmount(rs.getDouble(2));
obj.setOrderDate(rs.getDate(3));
orderList.add(obj);




}

return orderList;
}


 public static ArrayList<OrderInfoDTO> getInfoByOrder(String order_name)throws SQLException 
    {
            
            ps7.setString(1,order_name);
           ArrayList<OrderInfoDTO> ord =  new ArrayList<OrderInfoDTO>();
            ResultSet rs = ps7.executeQuery();
            while(rs.next())
              {
                   OrderInfoDTO i =  new OrderInfoDTO();
                  i.setOrderName(order_name);
                  i.setItemName(rs.getString(2));
                  i.setItemPrice(rs.getDouble(3));
                  ord.add(i);
              }
          return ord;  
    }
    
}
