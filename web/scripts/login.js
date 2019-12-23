/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function()
{
$("#loginbtn").click(function()
{
    connect();
});

  $("#username").focusin(function()
               {
                   $("#requname").html("");
                   $("#loginresult").html("");
               } );      
                 $("#password").focusin(function()
               {
                   $("#reqpwd").html("");
                   $("#loginresult").html("");  
               } );       

      $("#username").keypress(function ()
                         {
                           $("#requname").text("");
                            $("#loginresult").text("");
                                    }
                         );
             $("#password").keypress(function ()
                             {
                                 $("#reqpwd").text("");
                                 $("#loginresult").text("");
                                    }
                        );
            
});

function dovalidate()
{
    
    
    $("#reqpwd").css("display","inline");
      $("#requname").css("display","inline");
   uname=$("#username").val();
   pwd=$("#password").val();
  
  if(uname==="" && pwd==="")
  { status= false;
      $("#requname").html("UserName Required!!").css("color","red");
        $("#requname").fadeOut(3000);
       
         $("#reqpwd").html("Password Required!!").css("color","red");
        $("#reqpwd").fadeOut(3000);
  }
  else if(uname==="")
    { status= false;
        $("#requname").html("UserName Required!!").css("color","red");
        $("#requname").fadeOut(3000);
        
    }
    else if(pwd==="")
    {
        status= false;
         $("#reqpwd").html("Password Required!!").css("color","red");
        $("#reqpwd").fadeOut(3000);
        
    }
    else
    {
        status=true;
    }
  return status;
}
function direct(p)
{

     if(p.charCode===13)
         {
             connect();
         }
}

function connect()
{
    
  var ans= dovalidate();
  if(ans===false)
  {
      return;
      //alert("ans has become false on 2 clearing  ");
  }
 
utype=$("#usertype").val();
//alert(utype);
var data={usertype:utype,username:uname,password:pwd};
//alert(data.username);
var request=$.post("LoginControllerServlet",data,processResponse);
request.error(handleError);
}
function processResponse(responseText,textStatus)
{
   
  //  alert("content of responseText : "+responseText);
    
    function destination()
    {
       window.location=responseText;
    }
    if(textStatus==='success')
    {
         if(responseText.trim()==="incorrectdata")
    {
        $("#loginresult").text("Login Failed!! Try Again ").css("color","red");
       // return;
    }
        if(utype==="CUSTOMER" && responseText.trim()!=="incorrectdata")
        {
$("#loginresult").text("Login Accepted! Redirecting to the Store Page!! ").css("color","yellowgreen");
 setTimeout(destination,3000);

        }
        
        if(utype==="ADMIN" && responseText.trim()!=="incorrectdata")
        {
$("#loginresult").text("Login Accepted! Redirecting to the Options Page!! ").css("color","yellowgreen");;
setTimeout++
(destination,3000);
        }
        
        
    }
   else
   {
        if(textStatus==='error')
    {
        
$("#loginresult").text("Login Failed !!").css("color","red");
    }
   }
    
}


function handleError(xhr,textStatus)

{
    if(textStatus==='error')
    {
        
$("#loginresult").text("Login Failed , An error occured during your request "+xhr.status);
    }
    
}



    
    
    
