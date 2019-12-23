var itemid;
function removeitem(itemId)
{
    location.reload();
    itemid = itemId;
      var mydata = {removeitemwithid:itemid};
    var request = $.post("StoreControllerServlet",mydata);
    request.error(handleError);
}
//function processResponse(responseText)
//{   // var space=document.getElementById(paste);  ,processResponse
 //  alert("response text recieved in removeitem js");
 //   var resp=responseText.trim();
 // var span=document.createElement("div");
 // span.innerHTML=resp;
  //  space.innerHTML=resp;
 // alert("inner html activated removeitem js"); 
   
//}
function handleError(xhr,textStatus){
        if(textStatus === 'error') 
      $("#newdiv").text('An error occurred during your request: ' +  xhr.status ).css("color","red");
}