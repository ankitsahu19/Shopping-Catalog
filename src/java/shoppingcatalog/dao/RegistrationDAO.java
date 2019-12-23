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
import shoppingcatalog.dbutil.DBConnection;
import shoppingcatalog.dto.UserDTO;

/**
 *
 * @author hp
 */
public class RegistrationDAO {
     private static PreparedStatement ps1,ps2;
    static
    {
    try
    {
    ps1=DBConnection.getConnection().prepareStatement("select username from members where username=? and membertype='CUSTOMER'");

    ps2=DBConnection.getConnection().prepareStatement("insert into members values(?,?,?)");
    }
    catch(Exception ex)
    {
    
        System.out.println("Error in db connection "+ex);
    }
    
    }
    public static boolean searchUser(String username)throws SQLException
    {
    ps1.setString(1, username);
    ResultSet rs=ps1.executeQuery();
    return rs.next();
    
    
    
    }
    
    public static boolean registerUser(UserDTO user)throws SQLException
    {
        
        boolean done=false;
        ps2.setString(1,user.getUsername());
       ps2.setString(2,user.getPassword());
       ps2.setString(3,user.getUsertype());
       
       int ans=ps2.executeUpdate();
       if(ans!=0)
           done=true;
       return done;
        
        
    }
    
    
    
     
    public static boolean updatePwd(String uid,String oldpwd,String npwd)throws SQLException{
        Connection conn=DBConnection.getConnection();
        boolean passwordChanged=false;
        try
        {
            PreparedStatement ps=conn.prepareStatement("Update users set password=? where userid=? and password=?");
            ps.setString(1, npwd);
            ps.setString(2,uid);
            ps.setString(3,oldpwd);
           int count=ps.executeUpdate();
            if(count!=0){
                passwordChanged=true;
            }
        }
        finally{
            return passwordChanged;
        }
    }
    public static String validateUser(String uid,String pwd)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
       String username=null;
        try
        {
            PreparedStatement ps=conn.prepareStatement("select username from users where userid=? and password=?");
            ps.setString(1, uid);
            ps.setString(2,pwd);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                username=rs.getString(1);
            }
        }
        finally{
            return username;
        }
        
    }
    
}
