/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function checkform()
{
	var statys=true;
    if(!document.getElementById("login").value.match(/^[a-z0-9]+$/i))
    {	$("#textLogin").html('Логин введен неверно!'); 		
        statys=false;
    }else{
		 $("#textLogin").html(''); 
	}
    if(!document.getElementById("password").value.match(/^[a-z0-9]+$/i))
    {
        $("#textPass").html('Пароль введен неверно!'); 
        statys=false; 
    }else{
		 $("#textPass").html(''); 
	}
    return statys;
}