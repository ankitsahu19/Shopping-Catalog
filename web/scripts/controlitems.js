$(document).ready(function()
{

  $("#pname").focusin(function()
               {
                   $("#pname").html("");
                   $("#addconfirmmsg").html("");
               } );      
                 $("#ptype").focusin(function()
               {
                   $("#ptype").html("");
                  $("#addconfirmmsg").html("");
               } );    
                $("#pdesc").focusin(function()
               {
                   $("#pdesc").html("");
                   $("#addconfirmmsg").html("");
               } );      
                 $("#pprice").focusin(function()
               {
                   $("#pprice").html("");
                  $("#addconfirmmsg").html(""); 
               } );
                 $("#pimage").focusin(function()
               {
                   $("#pimage").html("");
                   $("#addconfirmmsg").html("");
               } );
               });
               



function process1( ) // for Manage Products
{
if($("#div1").is(":hidden"))
	$("#div1").slideDown("slow",function()
	{
		$("#a1").text("- Products");
	});
else
	$("#div1").slideUp("slow",function()
	{
		$("#a1").text("+ Products");
	});
}

function process2( )    //  for Manage Users
{
if($("#div2").is(":hidden"))
	$("#div2").slideDown("slow",function()
	{
		$("#a2").text("- Users");
	});
else
	$("#div2").slideUp("slow",function()
	{
		$("#a2").text("+ Users");
	});
}

function process3( )    // for View Orders
{
if($("#div3").is(":hidden"))
	$("#div3").slideDown("slow",function()
	{
		$("#a3").text("- View ");
	});
else
	$("#div3").slideUp("slow",function()
	{
		$("#a3").text("+ View ");
	});
}

function processaddform( )     // for bringing and removing dynamic "add" option form
{
if($("#frm").is(":hidden"))
{
    $("#frmdel").fadeOut(750);
	$("#frmup").fadeOut(750);
        $("#frm").fadeIn(750);
    }	
else
{
    $("#frm").fadeOut(750);
}
}

function addvalidate()     // to validate entries of add form
{
    pname=$("#pname").val();
    ptype=$("#ptype").val();
    pprice=$("#pprice").val();
    pdesc=$("#pdesc").val();
   if(pname==="" || pprice===""|| pdesc==="" ||ptype==="")
    {
        alert("Please Fill All The Fields of Add Form");
      return;
   }
else if(isNaN(pprice))
{

 alert("Plz input valid Price");
 return;
}

else if(!(isNaN(pname) && isNaN(pdesc) && isNaN(ptype)))
{
  alert("Please input valid data");
return;
}
     pimage1=document.getElementById("pimage").value;
        
    if(pimage1==="")
    {
        alert("Image is not Selected");
      return;
   }
 pimage=document.getElementById("pimage").files[0].name;    
    var status=true;
    if(pname==="")
    {
        
        $("#pname").after("<span id='pnameerror'>P_Name Reqd!</span>");
        $("#pnameerror").css("color","red");
        status= false;
    }
    if(ptype==="")
    {
        
        $("#ptype").after("<span id='ptypeerror'>P_Type Reqd!</span>");
        $("#ptypeerror").css("color","red");
        status= false;
    }
     if(pprice==="")
    {
        
        $("#pprice").after("<span id='ppriceerror'>P_Price Reqd!</span>");
        $("#ppriceerror").css("color","red");
        status= false;
    }
    if(pdesc==="")
    {
        
        $("#pdesc").after("<span id='pdescerror'>P_Desc Reqd!</span>");
        $("#pdescerror").css("color","red");
        status= false;
    }
      if(pimage==="")
    {
        
        $("#pimage").after("<span id='pimageerror'>P_Image Reqd!</span>");
        $("#pimageerror").css("color","red");
        status= false;
    }
    $("#pnameerror").fadeOut(2000);
    $("#ptypeerror").fadeOut(2000);
      $("#ppriceerror").fadeOut(2000);
    $("#pdescerror").fadeOut(2000);
      $("#pimageerror").fadeOut(2000);
   
    return status;
}
function onsubmitform()  // called on submitting the form
{
    if(!addvalidate())
    {
       // alert("<b>Please Fill All The Fields</b>");
     return;   
    }
    var mydata={pname:pname,ptype:ptype,pprice:pprice,pdesc:pdesc,pimage:pimage};
var request=$.post("AdminControllerServlet",mydata,processresponse);
request.error(handleError);
    }
function processresponse(responseText)
{
    
    
       var resp=responseText;
       
        resp=resp.trim();
        if(resp==="Success")
        {
            $("#addconfirmmsg").css("color","green");
            $("#addconfirmmsg").html("<b>Successfully Added Product into the DataBase</b>"); 
            
        }
        else if(resp==="Failure")
        {
            $("#addconfirmmsg").css("color","red");
            $("#addconfirmmsg").html("<b>Error While Storing Product into the DataBase</b>"); 
        
        }
      
     
        else
        {
            $("#addconfirmmsg").css("color","red");
            $("#addconfirmmsg").html("Some problem at server ! Try later"); 
            
            
        }
    
    }
    function handleError(xhr,textStatus)
    {
    
    if(textStatus==='error'){
        $("#addconfirmmsg").html("Error is(from handel error of control items): "+xhr.status);
    }
}

function addclearspan()
{
     $("#addconfirmmsg").html("");
     location.reload();
    
    
    
    
}

/////////////////////////////////////////////////////////////////////

/* function processupdateform( )     // for bringing and removing dynamic "update" option form
{

if($("#frmup").is(":hidden"))
	$("#frmup").fadeIn(750,function()
    {
      var request = $.get("AdminControllerServlet?fruit=apple");
    request.error(handleError);
        
       
    });
	
else
	$("#frmup").fadeOut(750);

}

 
function getIdDetails()
{
   var id=document.getElementById("selectid").value; 
    var data={itemid:id};
    var request=$.post("AdminControllerServlet",data,outputresponse);
    request.error(handleError);
    alert("getID Details is called from control items js");
}

function outputresponse(responseText)
{
   var resp= responseText;
    resp=resp.trim();
    var collect_details=resp.split(",");
     $("pname").val(collect_details[1]);
    $("ptype").val(collect_details[2]);
    $("pprice").val(collect_details[4]);
    $("pdesc").val(collect_details[3]);
    $("pimage").val(collect_details[5]);
    
    alert("update form values is been set with responseText: "+resp)  ;
}
*/



 function removeUser(username,usertype )     // for removing user details,     
 {
     location.reload();
     uname=username;
    utype=usertype;
     var mydata={memberusername:username ,memberusertype:usertype};
var request=$.post("AdminControllerServlet",mydata);
request.error(handleError);  
     
     
     
 } 