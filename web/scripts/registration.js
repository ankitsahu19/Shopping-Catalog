$(document).ready(function()
{
$("#registerbtn").click(function()
{
    connect();
});

  $("#username").focusin(function()
               {
                  
                   $("#regresult").html("");
               } );      
                 $("#password").focusin(function()
               {
                  
                   $("#regresult").html("");  
               } );       

      $("#username").keypress(function ()
                         {
                          
                            $("#regresult").text("");
                                    }
                         );
             $("#password").keypress(function ()
                             {
                                  $("#regresult").text("");
                                    }
                        );
            
});
function validate()
{
    username=$("#username").val();
    password=$("#password").val();
   
    var status=true;
    if(username==="")
    {
        
        $("#username").after("<span id='error1'>Username Reqd!</span>");
        $("#error1").css("color","red");
        status= false;
    }
    if(password==="")
    {
        
        $("#password").after("<span id='error2'>Password Reqd!</span>");
        $("#error2").css("color","red");
        status= false;
    }
    $("#error1").fadeOut(4000);
    $("#error2").fadeOut(4000);
    return status;
}
function connect()
{
    //alert("connect called");
    if(!validate())
    {
       
        return ;
    }
   
var mydata={username:username,password:password};
var request=$.post("RegistrationControllerServlet",mydata,processresponse);
request.error(handleError);
}
function processresponse(responseText)
{
    
    
        var resp=responseText;
       
        resp=resp.trim();
        if(resp==="uap")
        {
            $("#regresult").css("color","red");
            $("#regresult").html("Sorry! the username is <b>Already Present!</b>"); 
            
        }
        else if(resp==="failure")
        {
            $("#regresult").css("color","red");
            $("#regresult").html("<b>Registration Failed!!</b><br> Try again"); 
        
        }
        else if(resp==="success")
        {
             $("#regresult").css("color","yellowgreen");
            $("#regresult").html("<b>Registration Successfull!</b><br> You can now <a href='login.html'><font color='blue'> LOGIN </font></a>"); 
            
        }
        else
        {
            $("#regresult").css("color","red");
            $("#regresult").html("Some problem at server ! Try later"); 
            
            
        }
    
    }
    function handleError(xhr,textStatus)
    {
    
    if(textStatus==='error'){
        $("#registerbtn").html("Error is "+xhr.status);
    }
}

