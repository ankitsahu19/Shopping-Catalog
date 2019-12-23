<%-- 
    Document   : seeoption
    Created on : 16 Apr, 2019, 2:26:59 AM
    Author     : hp
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <title>See option Page</title>
         <link rel="stylesheet" type="text/css" href="styles/stylesheet.css">
        <script src="scripts/jquery.js"></script>
        <script src="scripts/controlitems.js"></script>
       <script src="scripts/updateform.js"></script>
       <script src="scripts/deleteform.js"></script>
    </head>
    <body>
         <%@include  file="logo.html" %>
        
        <% 
           
        StringBuffer displayBlock= null ;
           String username  = (String)session.getAttribute("username");
             System.out.println("username in the seeoption page: "+ username);
            if(username==null)
            {
                session.invalidate();
               response.sendRedirect("accessdenied.html");
            }
            else
            {               
            displayBlock = new StringBuffer("<h1>Admin Options</h1><p>Select a category to take an action :</p>");
 /// products  
            displayBlock.append("<p><strong><a id='a1' onclick='process1()' href='#'>+ Products</a></strong></p>");
          displayBlock.append("<div id='div1' style='display:none'><ul>");
           displayBlock.append("<li><a href='#' onclick = processaddform()>Add</a></li>"); // Add Section 
         displayBlock.append("<li><a href='#' onclick = processupdateform()>Update</a></li>"); // Update Section
         displayBlock.append("<li><a href='#' onclick = processdeleteform()>Delete</a></li>");  // Delete Section
           
           displayBlock.append("</ul></div>");
     
//// users
     
            displayBlock.append("<p><strong><a id='a2' onclick='process2()' href='#'>+ Users</a></strong></p>");
          displayBlock.append("<div id='div2' style='display:none'><ul>");
           displayBlock.append("<li><a href='alteruserdetails.jsp'>Remove</a></li>");
           displayBlock.append("</ul></div>");
 
//// View orders         
                //String str="vieworder";
            displayBlock.append("<p><strong><a id='a3' onclick='process3()' href='#'>+ View </a></strong></p>");
            displayBlock.append("<div id='div3' style='display:none'><ul>");
           displayBlock.append("<li><a href='vieworderdetails.jsp' >Orders</a></li>");
          displayBlock.append("</ul></div>");
          
         ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
 // making add form
          displayBlock.append("<div id='frm'  style='display:none' align='center' >");
          displayBlock.append("<form>");
          displayBlock.append("<fieldset style='display:inline-block' >");
          displayBlock.append("<legend>Fill the product details:</legend>");
          displayBlock.append("<table border='1'>");
          displayBlock.append("<tr><td>Product_Name:</td><td><input type='text' name='pname' id='pname'></td></tr>");
          displayBlock.append("<tr><td>Product_Type:</td><td><input type='text' name='ptype' id='ptype'></td></tr>");
          displayBlock.append("<tr><td>Product_Price:</td><td><input type='text' name='pprice' id='pprice'></td></tr>");
          displayBlock.append("<tr><td>Product_Desc:</td><td><input type='text' name='pdesc' id='pdesc'></td></tr>");
          displayBlock.append("<tr><td colspan='2'><input type='file' accept='images/jpg,images/png,images/jpeg' name='pimage' id='pimage'></td></tr>");
          displayBlock.append("<tr><td align='center'><input type='button' value='Add Product' onclick=onsubmitform()></td><td align='center'><input type='reset' onclick=addclearspan() value='Clear'></td></tr>");
          displayBlock.append("</table>");
          displayBlock.append("</fieldset>");
          displayBlock.append("</form>");
          displayBlock.append("<span id='addconfirmmsg'> </span>");
          displayBlock.append("</div>");
  //end of add form
         
          //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 // making update form
           displayBlock.append("<div id='frmup'  style='display:none' align='center'>");
          displayBlock.append("<form>");
          displayBlock.append("<fieldset style='display:inline-block' >");
          displayBlock.append("<legend>Update the product details:</legend>");
      //displayBlock.append("<div id=updivrefresh>");
          displayBlock.append("Choose the Item ID : <select id='selectid' onchange='getIdDetails()'>");
          ArrayList<Integer> idm=(ArrayList<Integer>)request.getAttribute("ids");
        //  if(idm.size()!=0)
       //   {System.out.println("list is not empty");
       //   }
       //   else
       //   {
        //      System.out.println("List is Empty ");
       //   return;
        //  }
         for(Integer id : idm)
         {
           displayBlock.append("<option>"+id+"</option>");
          }
           displayBlock.append("</select>");
        //   displayBlock.append("</div>");
     //      displayBlock.append("getIdDetails()");
  //       ItemDTO item=(ItemDTO)request.getAttribute("itemdetails");
//String collected=item.getItemId()+","+item.getItemName()+","+item.getItemType()+","+item.getItemDesc()+","+item.getItemPrice()+","+item.getItemImage();

  //        displayBlock.append(collected);
    
             displayBlock.append("<table border='1'>");
          displayBlock.append("<tr><td>Product_Name:</td><td><input type='text' name='pname' id='upname'></td></tr>");
          displayBlock.append("<tr><td>Product_Type:</td><td><input type='text' name='ptype' id='uptype'></td></tr>");
          displayBlock.append("<tr><td>Product_Price:</td><td><input type='text' name='pprice' id='upprice'></td></tr>");
          displayBlock.append("<tr><td>Product_Desc:</td><td><input type='text' name='pdesc' id='updesc'></td></tr>");
         displayBlock.append("<tr><td colspan='2'><input type='file' accept='images/jpg,images/png,images/jpeg' name='upimage' id='upimage' ></td></tr>");
          displayBlock.append("<tr><td align='center'><input type='button' value='Update Product'  onclick=onupdatesubmit()></td><td align='center'><input type='reset' onclick=updateclearspan() value='Clear'></td></tr>");
        
          displayBlock.append("</table>");
          displayBlock.append("</fieldset>");
          displayBlock.append("</form>");
         //  displayBlock.append("<input type='button' id='refresh' value='Refresh'>");
          displayBlock.append("<span id='updateconfirmmsg'> </span>");
       displayBlock.append("<span id='imageposition'></span>");
       // displayBlock.append("<img src='images/"+item.getItemImage()+"'>");
          displayBlock.append("<span style='width:60px' id='location'></span>");
       displayBlock.append("</div>");
          
          
 // end of update form
           ///////////////////////////////////////////////////////////////////////////////////////
 // making of delete form  
           
           displayBlock.append("<div id='frmdel'  style='display:none' align='center' >");
           displayBlock.append("<form>");
           displayBlock.append("<fieldset style='display:inline-block' >");
           displayBlock.append("<legend>Delete product details:</legend>");
           displayBlock.append("<div id=dfdivrefresh>"); // to refresh the list on clear button 
           displayBlock.append("Choose the Item ID : <select id='dfselectid' onchange='dfGetIdDetails()' >");
          for(Integer id : idm)
          {
           displayBlock.append("<option>"+id+"</option>");
          }
           displayBlock.append("</select>");
     displayBlock.append("</div>");
          
          displayBlock.append("<table border='1'>");
          displayBlock.append("<tr><td>Product_Name:</td><td><input type='text' name='pname' id='dpname'></td></tr>");
          displayBlock.append("<tr><td>Product_Type:</td><td><input type='text' name='ptype' id='dptype'></td></tr>");
          displayBlock.append("<tr><td>Product_Price:</td><td><input type='text' name='pprice' id='dpprice'></td></tr>");
          displayBlock.append("<tr><td>Product_Desc:</td><td><input type='text' name='pdesc' id='dpdesc'></td></tr>");
        // displayBlock.append("<tr><td colspan='2'><input type='file' accept='images/jpg,images/png,images/jpeg' name='pimage' id='pimage'></td></tr>");
          displayBlock.append("<tr><td align='center'><input type='button' value='Delete Product' onclick=ondeletesubmit()></td><td align='center'><input type='reset' onclick=deleteclearspan() value='Clear'></td></tr>");
          displayBlock.append("</table>");
          displayBlock.append("</fieldset>");
     //        displayBlock.append("<input type='button' id='dfrefresh' value='Refresh'>");
          displayBlock.append("</form>");
          displayBlock.append("<span id='deleteconfirmmsg'> </span>");
          displayBlock.append("</div>");
 // ending of delete form
           
           displayBlock.append("<h2 id='logout'><a href='LoginControllerServlet?logout=logout'>Logout</a></h2>");
            
           out.println(displayBlock);
            
            }
        
        
        %>
        

    </body>
</html>
