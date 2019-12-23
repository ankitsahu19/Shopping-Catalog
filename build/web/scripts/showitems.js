

var item;
function getitemNames(itemType)
{
    item = itemType;
    var para = document.getElementById(itemType);
    var span = para.getElementsByTagName("span")[0];// since total 3 span (span of mobile,shirt,books)are present.
    var spantext = span.innerHTML.trim();
    if( spantext.indexOf("+")!==-1)
    {
        span.innerHTML = "-"+ itemType;
    
    }
    else if (spantext.indexOf("-")!==-1)
    {
        span.innerHTML = "+" + itemType;
        $("#"+item+"").hide("slow");
        return;
    }
    var mydata = {item_type:itemType};
    var request = $.post("StoreControllerServlet",mydata,processResponse);
    request.error(handleError);
}
function processResponse(responseText)
{
    var para = document.getElementById(item);
    var resp = responseText;
    resp = resp.trim();
    var olddiv = para.getElementsByClassName("itemnames")[0];
    if(typeof olddiv!=='undefined')
    {
        para.removeChild(olddiv);
    }
    var newdiv = document.createElement("div");
    newdiv.setAttribute("class","itemnames");
    newdiv.setAttribute("id",item);
    newdiv.innerHTML = resp;
    para.appendChild(newdiv);
}
function handleError(xhr,textStatus){
        if(textStatus === 'error') 
      $("#newdiv").text('An error occurred during your request: ' +  xhr.status ).css("color","red");
}


