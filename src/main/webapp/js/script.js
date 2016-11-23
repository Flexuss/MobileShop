/**
 * Created by Dmitry on 22.11.2016.
 */
function addToCart(id){
    var Request = false;
    if (window.XMLHttpRequest) {
        Request = new XMLHttpRequest();
    }
    else if (window.ActiveXObject) {
        try {
            Request = new ActiveXObject("Microsoft.XMLHTTP");
        }
        catch (CatchException) {
            Request = new ActiveXObject("Msxml2.XMLHTTP");
        }
    }
    var param;
    if(!Request){
        return;
    }
    param="product="+id;
    Request.open("post","/addtocart",true);
    Request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    Request.send(param);
}

function removeFromCart(id){
    var Request = false;
    if (window.XMLHttpRequest) {
        Request = new XMLHttpRequest();
    }
    else if (window.ActiveXObject) {
        try {
            Request = new ActiveXObject("Microsoft.XMLHTTP");
        }
        catch (CatchException) {
            Request = new ActiveXObject("Msxml2.XMLHTTP");
        }
    }
    var param;
    if(!Request){
        return;
    }
    param="product="+id;
    Request.open("post","/remove_from_cart",true);
    Request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    Request.send(param);
}