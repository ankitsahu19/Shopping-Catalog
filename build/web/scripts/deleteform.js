/*$(document).ready(function()
{
$('#dfrefresh').click(function()
{
   $('#dfselectid').load(refresh()); 
    
    
});
 
               });

function refresh()
{
     var request = $.get("AdminControllerServlet?fruit=apple");
            request.error(handleError);
}
*/

function processdeleteform( )   // for bringing and removing dynamic "delete" option form  
{
if($("#frmdel").is(":hidden"))
{
	$("#frmdel").fadeIn(750);
	$("#frm").fadeOut(750);
	$("#frmup").fadeOut(750);
}	
else
{
	$("#frmdel").fadeOut(750);
}
}


function dfGetIdDetails()
{
    id=document.getElementById("dfselectid").value; 
    var data={itemid:id};
    var request=$.post("UpdateForm",data,outputresp);
    request.error(handleError);
   // alert("getID Details is called from updateform js");
}

function outputresp(responseText)
{
 // alert("responseText of outputresponse");
   var resp= responseText;
    resp=resp.trim();
  //alert("update form values is been set with responseText(update form): "+resp)  ;
  var collect_details=resp.split("*");
    //alert("Array to display is: "+collect_details);
    var pname=collect_details[1];
    //alert("pname is:$ "+pname);
   // document.getElementById('#addconfirmmsg').value="text here";//collect_details[1];
    //alert( "Value to display in pname is: "+$("pname").val(collect_details[1]));
    $("#dpname").val(pname);    
    var ptype=collect_details[2];
    $("#dptype").val(ptype);
    $("#dpprice").val(collect_details[4]);
   // var desc=collect_details[3];
   // document.getElementById("#updesc").value=desc;
    $("#dpdesc").val(collect_details[3]);
  //  $("#upimage").val(collect_details[5]).files[0].name;
        
}


function deletevalidate()     // to validate entries of add form
{      dpid=$("#dfselectid").val();
    //alert("uid is : "+upid);
    dpname=$("#dpname").val();
    dptype=$("#dptype").val();
    dpprice=$("#dpprice").val();
    dpdesc=$("#dpdesc").val();
   if(dpname==="" || dpprice===""|| dpdesc==="" ||dptype==="")
    {
        alert("Please Select An Item Id First!!");
      return;
   }
else if(isNaN(dpprice))
{

 alert("Plz input valid Price");
 return;
}

else if(!(isNaN(dpname) && isNaN(dpdesc) && isNaN(dptype)))
{
  alert("Please input valid data");
return;
}
    var status=true;
    if(dpname==="")
    {
        
        $("#dpname").after("<span id='dpnameerror'>P_Name Reqd!</span>");
        $("#dpnameerror").css("color","red");
        status= false;
    }
    if(dptype==="")
    {
        
        $("#dptype").after("<span id='dptypeerror'>P_Type Reqd!</span>");
        $("#dptypeerror").css("color","red");
        status= false;
    }
     if(dpprice==="")
    {
        
        $("#dpprice").after("<span id='dppriceerror'>P_Price Reqd!</span>");
        $("#dppriceerror").css("color","red");
        status= false;
    }
    if(dpdesc==="")
    {
        
        $("#dpdesc").after("<span id='dpdescerror'>P_Desc Reqd!</span>");
        $("#dpdescerror").css("color","red");
        status= false;
    }
    /*  if(upimage==="")
    {
        
        $("#upimage").after("<span id='upimageerror'>P_Image Reqd!</span>");
        $("#upimageerror").css("color","red");
        status= false;
    }*/
    $("#dpnameerror").fadeOut(2000);
    $("#dptypeerror").fadeOut(2000);
      $("#dppriceerror").fadeOut(2000);
    $("#dpdescerror").fadeOut(2000);
    //  $("#upimageerror").fadeOut(2000);
   
    return status;
}
function ondeletesubmit()  // called on submitting the update form
{
    if(!deletevalidate())
    {
       // alert("<b>Please Fill All The Fields</b>");
     return;   
    }
    var mydata={dpid:dpid}; 
var request=$.post("DeleteForm",mydata,processrespon);
request.error(handleError);
}
function processrespon(responseText)
{
  //  alert("responseText of processrespon");
 // alert("in process respon of delete form js with responsetext:  "+responseText);
       var resp=responseText;
       
        resp=resp.trim();
        if(resp==="Success")
        {
            $("#deleteconfirmmsg").css("color","yellowgreen");
            $("#deleteconfirmmsg").html("<b>Successfully Deleted Product from the DataBase</b>"); 
            
        }
        else if(resp==="Failure")
        {
            $("#deleteconfirmmsg").css("color","red");
            $("#deleteconfirmmsg").html("<b>Error Occured While Deleting the  Product from the DataBase</b>"); 
        
        }
      
     
        else if(resp.indexOf("div")===-1)
        {
            $("#deleteconfirmmsg").css("color","red");
            $("#deleteconfirmmsg").html("Some problem at server from delete form ! Try later"); 
            
            
        }
        else
        {
            $("#deleteconfirmmsg").css("color","yellowgreen");
            $("#deleteconfirmmsg").html("<b>Successfully Deleted Product from the DataBase:)</b>"); 
            
        }
       
    
    
     // var request = $.get("AdminControllerServlet?fruit=apple");
    //        request.error(handleError);
    // call to refresh function
    
    }

function handleError(xhr,textStatus)
    {
    
    if(textStatus==='error'){
        //$("#deleteconfirmmsg").html("Error(from handleError of delete form js) is "+xhr.status);
   $("#updateconfirmmsg").html("Please refresh the web page to get the correct response");
   
    }
    }
   
function deleteclearspan()
{
     
        $("#deleteconfirmmsg").html("");
        refresh();
    
    
}

function refresh()
{
     var request = $.get("DeleteForm",refreshcall);
            request.error(handleError);
    
}
function refreshcall(responseText)
{
    var respon=responseText.trim();
   // alert("In alert of refresh with responsetext: "+respon) ;
    $("#dfdivrefresh").html(respon);
    
}