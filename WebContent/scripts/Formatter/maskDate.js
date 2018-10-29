function mascara_data(fieldName,evt){ 

	var code = 0;
	if (clientNavigator == "IE"){
 		code = evt.keyCode;
 	}else{
 		code = evt.charCode;
 	}
	
	if ( code != 8 ) {
		var mydata = ''; 
		var data   = window.document.getElementById(fieldName);
		mydata = mydata + data.value; 
		if (mydata.length == 2){ 
		    mydata = mydata + '/'; 
		    data.value = mydata; 
		} 
		if (mydata.length == 5){ 
		    mydata = mydata + '/'; 
		    data.value = mydata; 
		}
	}
	
} 

function mascara_mes(fieldName,evt){ 
	
	var code = 0;
	if (clientNavigator == "IE"){
 		code = evt.keyCode;
 	}else{
 		code = evt.charCode;
 	}
	
	if ( code != 6 ) {
		var mydata = ''; 
		var data   = window.document.getElementById(fieldName);
		mydata = mydata + data.value; 
		if (mydata.length == 2){ 
		    mydata = mydata + '/'; 
		    data.value = mydata; 
		} 
	}
	
} 


