var xmlClock = "";

function getClock() {

	var url = "/ym_sistema_garantia/Relogio.do";
	if (window.XMLHttpRequest) {
		xmlClock = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		xmlClock = new ActiveXObject("Microsoft.XMLHTTP");
	} else {
		alert("Your browser lacks the needed ability to use Ajax");
		return false;   
	}
	
	xmlClock.onreadystatechange = processClock;
	xmlClock.open("GET", url, true);
	xmlClock.send("");
	setTimeout('getClock()', 60*1000); // 60*1000 = 60 segundos = 1 minuto
	
}
 
function processClock() {

	if (xmlClock.readyState == 4) {
		if (xmlClock.status == 200) {
			div = document.getElementById("clockDiv");
			div.innerHTML = xmlClock.responseText;
		} else {
			alert("There was a problem retrieving the XML data:\n" + xmlClock.statusText);
		}
	}
	  
}