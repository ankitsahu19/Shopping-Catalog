

function processupdateform( )     // for bringing and removing dynamic "update" option form
{
// location.reload();
if($("#frmup").is(":hidden"))
{
    $("#frmdel").fadeOut(750);
	$("#frm").fadeOut(750);
    $("#frmup").fadeIn(750,function()
    {
       
     var request = $.get("AdminControllerServlet?fruit=apple");
            request.error(handleError);
            
    });
    }	
else
{	$("#frmup").fadeOut(750);

}
}

   function handleError(xhr,textStatus)
    {
    
    if(textStatus==='error'){
        $("#updateconfirmmsg").html("Error is "+xhr.status);
    }
} 

function getIdDetails()
{
    id=document.getElementById("selectid").value; 
    var data={itemid:id};
    var request=$.post("UpdateForm",data,outputresponse);
    request.error(handleError);
   // alert("getID Details is called from updateform js");
}

function outputresponse(responseText)
{
 // alert("responseText of outputresponse");
    response= responseText;
    response=response.trim();
  //alert("update form values is been set with responseText(update form): "+resp)  ;
  collect_details=response.split("*");
    //alert("Array to display is: "+collect_details);
    var pname=collect_details[1];
    //alert("pname is:$ "+pname);
   // document.getElementById('#addconfirmmsg').value="text here";//collect_details[1];
    //alert( "Value to display in pname is: "+$("pname").val(collect_details[1]));
    $("#upname").val(pname);    
    var ptype=collect_details[2];
    $("#uptype").val(ptype);
    $("#upprice").val(collect_details[4]);
   // var desc=collect_details[3];
   // document.getElementById("#updesc").value=desc;
    $("#updesc").val(collect_details[3]);
  // alert("about to display image in update form DB: "+collect_details[5]);
 // $("#imagepos").val("collect_details[5]");
 // var position=document.getElementById("#frmup");
     img=collect_details[5];
      place=document.getElementById("location");
    // alert("Value of place: "+place);
       oldx = place.getElementsByClassName("itemnames")[0];
    //  alert("Value of oldx: "+oldx);
    if(typeof oldx!=='undefined')
    {
        place.removeChild(oldx);
    }
     x=document.createElement("IMG");
    x.setAttribute("src","images/"+img);
    x.setAttribute("class","itemnames");
   // x.setAttribute("id","dbimage");
    x.setAttribute("height",230);
    x.setAttribute("width",280);
    x.setAttribute("alt","the image from DB");
    //$("#updateconfirmmsg").after(x);
 //   alert("before appending");
     place.appendChild(x);
//alert("after appending");
}

function updateclearspan()
{
    //alert("ucnfmsg: "+document.getElementById("#updateconfirmmsg"));
     $("#updateconfirmmsg").html("");
      $("#location").html("");
     
     //place.removeChild(oldx);
   //  place.removeChild(x);

  //.style.display='none';
 //  rem_image.parentNode.removeChild(rem_image);
    
    //  $("#imagepos").html("");
     
     }

///////////////////////////////////////////////////////////////////////////////////////////////////
function updatevalidate()     // to validate entries of add form
{      upid=$("#selectid").val();
    //alert("uid is : "+upid);
    upname=$("#upname").val();
    uptype=$("#uptype").val();
    upprice=$("#upprice").val();
    updesc=$("#updesc").val();
 
   if(upname==="" || upprice===""|| updesc==="" ||uptype==="")
    {
        alert("Please Fill All The Fields of Update Form");
      return;
   }
else if(isNaN(upprice))
{

 alert("Plz input valid Price");
 return;
}

else if(!(isNaN(upname) && isNaN(updesc) && isNaN(uptype)))
{
  alert("Please input valid data");
return;
}

   upimage1=document.getElementById("upimage").value;
   //alert("pimage1 is : "+pimage1);     
    if(upimage1==="")
    {
       var ans= confirm("New Image is NOT selected for the Product. Press 'OK' to continue and 'CANCEL' to select an image");
 // alert("confirm replied:  "+ans);
 if(ans)
 {
     upimage=img;
   // alert("image going to  the DB is: "+upimage);
 }
 else
 {
     
     return;
 }
    }
  else
  {
      $("#location").html("");
   upimage=document.getElementById("upimage").files[0].name; 
  // alert("name of the new image going to the Db is : "+upimage );

    } 
 
    var status=true;
    if(upname==="")
    {
        
        $("#upname").after("<span id='upnameerror'>P_Name Reqd!</span>");
        $("#upnameerror").css("color","red");
        status= false;
    }
    if(uptype==="")
    {
        
        $("#uptype").after("<span id='uptypeerror'>P_Type Reqd!</span>");
        $("#uptypeerror").css("color","red");
        status= false;
    }
     if(upprice==="")
    {
        
        $("#upprice").after("<span id='uppriceerror'>P_Price Reqd!</span>");
        $("#uppriceerror").css("color","red");
        status= false;
    }
    if(updesc==="")
    {
        
        $("#updesc").after("<span id='updescerror'>P_Desc Reqd!</span>");
        $("#updescerror").css("color","red");
        status= false;
    }
     if(upimage==="")
    {
        
        $("#upimage").after("<span id='upimageerror'>P_Image Reqd!</span>");
        $("#upimageerror").css("color","red");
        status= false;
    }
    $("#upnameerror").fadeOut(2000);
    $("#uptypeerror").fadeOut(2000);
      $("#uppriceerror").fadeOut(2000);
    $("#updescerror").fadeOut(2000);
     $("#upimageerror").fadeOut(2000);
   
    return status;
}
function onupdatesubmit()  // called on submitting the update form
{
    if(!updatevalidate())
    {
    // alert("<b>Problem in validation</b>");
     return;   
    }
    var mydata={upid:upid,upname:upname,uptype:uptype,upprice:upprice,updesc:updesc,upimage:upimage};
var request=$.post("UpdateForm",mydata,processresp);
request.error(handleError);
}
function processresp(responseText)
{
   // alert("responseText of processresp");
   // alert("in process resp of update form js with responsetext:  "+responseText);
       var resp=responseText;
       
        resp=resp.trim();
        if(resp==="Success")
        {
            $("#updateconfirmmsg").css("color","yellowgreen");
            $("#updateconfirmmsg").html("<b>Successfully Updated Product into the DataBase</b>"); 
            
        }
        else if(resp==="Failure")
        {
            $("#updateconfirmmsg").css("color","red");
            $("#updateconfirmmsg").html("<b>Error Occured While Updating Product into the DataBase</b>"); 
        
        }
      
     
      //  else if(resp.indexOf("div")===-1)
     //   {
     //       $("#updateconfirmmsg").css("color","red");
     //       $("#updateconfirmmsg").html("Some problem at server from update form js! Try later"); 
            
            
     //   }
        else
        {
             $("#updateconfirmmsg").css("color","yellowgreen");
            $("#updateconfirmmsg").html("<b>Successfully Updated Product into the DataBase :)</b>"); 
            
            
        }
        
  //    var req = $.get("AdminControllerServlet?fruit=apple");
   //         req.error(handleError);  
    
    // call to refresh function 
    
    }
    function handleError(xhr,textStatus)
    {
    
    if(textStatus==='error'){
        $("#updateconfirmmsg").html("Error is(from handel error of updateform js) "+xhr.status);
    }
}
/*    uprefresh();
    
}
function uprefresh()
{
     var request = $.get("DeleteForm?v1=1",uprefreshcall);
            request.error(handleError);
    
}
function uprefreshcall(responseText)
{
    var respon=responseText.trim();
  alert("In alert of uprefresh with responsetext: "+respon) ;
    $("#updivrefresh").html(respon);
    
}
*/