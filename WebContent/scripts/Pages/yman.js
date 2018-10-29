function yman(form) {
	
	var action = 'https://yman-link.ymcapps.net/yman/yman/login/DealerRefLogin.do';
		
		//'https://s11.yamaha-motor.co.jp/yman/yman/login/DealerRefLogin.do';
	form.action = action;
	
	//window.alert(form.flag1.value + " - " + form.flag2.value + " - " + form.flag3.value + " - " + form.flag4.value);
	//window.alert("Referrer: " + document.referrer);
	
	form.submit();
}