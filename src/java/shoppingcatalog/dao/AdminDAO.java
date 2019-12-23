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
import java.util.ArrayList;
import shoppingcatalog.dbutil.DBConnection;
import shoppingcatalog.dto.ItemDTO;
import shoppingcatalog.dto.UserDTO;
import shoppingcatalog.dto.ViewOrderDTO;

/**
 *
 * @author hp
 */
public class AdminDAO {
     private static  PreparedStatement ps1,ps2,ps3,ps4,ps5,ps6,ps7,ps8,ps9;
   // private static  Statement st;
    private static Connection conn;
    static 
    {
        conn =   DBConnection.getConnection();
        try 
        {
          ps1= conn.prepareStatement("Select count(*) as count from store_items");   
           ps2=conn.prepareStatement("insert into store_items values (?,?,?,?,?,?)");
           ps3=conn.prepareStatement("select order_master.order_id, order_master.cust_name,order_master.order_amount, order_master.order_date,order_details.item_name from order_master inner join order_details on order_master.order_id=order_details.order_id");
           ps4=conn.prepareStatement("select * from members ");
           ps5=conn.prepareStatement("delete from members where username=? and membertype=?");
           ps6=conn.prepareStatement("select id from store_items order by id");
           ps7 = conn.prepareStatement("select * from store_items where id=?" );
           ps8=conn.prepareStatement("update store_items set item_type=?,item_name=?,item_price=?,item_desc=?,item_image=? where id=?");  //,item_image=?
           ps9=conn.prepareStatement("delete from store_items where id=?");
        }
    catch(Exception ex)
    {
        System.out.println("exception in admin dao "+ex);
            ex.printStackTrace();
    }
    
    }
    
    public static boolean addItemIntoStore(ItemDTO item)throws SQLException
    {
    ResultSet rs=ps1.executeQuery();
    rs.next();
    int itemid=rs.getInt(1);
    int newitemid=(itemid+102);
    ps2.setInt(1,newitemid);
    ps2.setString(2,item.getItemType());
    ps2.setString(3,item.getItemName());
    ps2.setDouble(4,item.getItemPrice());
    ps2.setString(5,item.getItemDesc());
    ps2.setString(6,item.getItemImage());
    int ans=ps2.executeUpdate();
    if(ans!=0)
        return true;
    else
        return false;
    
    }
    
    public static ArrayList<ViewOrderDTO> viewOrderDetails()throws SQLException
    {
        ArrayList<ViewOrderDTO> order=new ArrayList<ViewOrderDTO>();
        ViewOrderDTO vorder=null;
      ResultSet rs=ps3.executeQuery();
      while(rs.next())
      {
      vorder=new ViewOrderDTO();
      vorder.setOrderId(rs.getString(1));
      vorder.setCustName(rs.getString(2));
      vorder.setOrderAmount(rs.getDouble(3));
      vorder.setOrderDate(rs.getDate(4));
      vorder.setItemName(rs.getString(5));
      order.add(vorder);

      }
        
      return order;
        
    }
   
     public static ArrayList<UserDTO> alterUserDetails()throws SQLException
    {
        ArrayList<UserDTO> order=new ArrayList<UserDTO>();
        UserDTO vorder=null;
      ResultSet rs=ps4.executeQuery();
      while(rs.next())
      {
      vorder=new UserDTO();
      vorder.setUsername(rs.getString(1));
      vorder.setPassword(rs.getString(2));
      vorder.setUsertype(rs.getString(3));
     
      order.add(vorder);

      }
        
      return order;
        
    }
    
     public static boolean removeUser(String username ,String usertype)throws SQLException
    {
   String uname=username;
 String utype=usertype;  
   ps5.setString(1, uname);
   ps5.setString(2, utype);
    int ans=ps5.executeUpdate();
    if(ans!=0)
        return true;
    else
        return false;
    
    }
    
    public static ArrayList<Integer> getIds()throws SQLException
    {
    ArrayList<Integer> id=new ArrayList<Integer>();
        ResultSet rs=ps6.executeQuery();
    while(rs.next())
    {
    
    id.add(rs.getInt(1));
    
    }
    
    return id;
    }



 public static ItemDTO getItemDetails(int itemid)throws SQLException
    {
        ItemDTO obj =  null;
        ps7.setInt(1, itemid);
       ResultSet rs =  ps7.executeQuery();
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


public static boolean updateItemIntoStore(ItemDTO item,int id)throws SQLException
    {
        System.out.println("inside admin dao update item into store");
    ps8.setString(1,item.getItemType());
    ps8.setString(2,item.getItemName());
    ps8.setDouble(3,item.getItemPrice());
    ps8.setString(4,item.getItemDesc());
     ps8.setString(5,item.getItemImage());
        System.out.println("type,name,price,desc and now image too exe with noprob");
  ps8.setInt(6,id);
        System.out.println("just below setting itemid from updateitem from adminDao");    
// s8.setString(5,item.getItemImage());
    int ans=ps8.executeUpdate();
    if(ans!=0)
        return true;
    else
        return false;
    
    }



 public static boolean removeItem(int id)throws SQLException
    {
     
   ps9.setInt(1, id);
    int ans=ps9.executeUpdate();
    if(ans!=0)
        return true;
    else
        return false;
    
    }
    
}
