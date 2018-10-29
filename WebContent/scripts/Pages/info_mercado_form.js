/* Resource Informática
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......info_mercado_form.js
 *   .: Criação.....01 de julho, 11:05
 *   .: Autor.......Edson Luiz Sumensari
 *                  Thiago Uriel M. Garcia
 *   .: Descrição...Conteúdo JavaScript para a tela "info_mercado_form"
 */

//----[ Função: backup() ]----------------------------------------------------------
function backup() {

	var sourceField1 = document.getElementById("condicaoProblema");
	var sourceField2 = document.getElementById("causaProblema");
	var sourceField3 = document.getElementById("diagnostico");
	var sourceField4 = document.getElementById("solucao");

	var backupField1 = document.getElementById("condicaoProblema_backup");
	var backupField2 = document.getElementById("causaProblema_backup");
	var backupField3 = document.getElementById("diagnostico_backup");
	var backupField4 = document.getElementById("solucao_backup");
	
	backupField1.value = sourceField1.value;
	backupField2.value = sourceField2.value;
	backupField3.value = sourceField3.value;
	backupField4.value = sourceField4.value;

}

//----[ Função: reset() ]----------------------------------------------------------
function resetInfoMercadoForm() {

	var sourceField1 = document.getElementById("condicaoProblema");
	var sourceField2 = document.getElementById("causaProblema");
	var sourceField3 = document.getElementById("diagnostico");
	var sourceField4 = document.getElementById("solucao");

	var backupField1 = document.getElementById("condicaoProblema_backup");
	var backupField2 = document.getElementById("causaProblema_backup");
	var backupField3 = document.getElementById("diagnostico_backup");
	var backupField4 = document.getElementById("solucao_backup");

	var oldValue1 = backupField1.value;
	var oldValue2 = backupField2.value;
	var oldValue3 = backupField3.value;
	var oldValue4 = backupField4.value;
	 
	document.forms[0].reset();
	
	sourceField1.value = oldValue1;
	sourceField2.value = oldValue2;
	sourceField3.value = oldValue3;
	sourceField4.value = oldValue4;
	
	backup();

}

//----[ Função: storeChassi() ]-----------------------------------------------------
function storeChassi() {
		
	var chassiFieldSource   = window.document.infoMercadoForm.chassi;
	var chassiFieldTarget   = window.document.infoMercadoForm.escapedChassi;			
	chassiFieldTarget.value = escape(chassiFieldSource.value);
		
}

//----[ Função: showHide() ]--------------------------------------------------------
function showHide(wobj) {
	document.getElementById(wobj).style.display = (document.getElementById(wobj).style.display == 'none') ? '' : 'none';
}

//----[ Função: showHideInput() ]---------------------------------------------------
function showHideInput(name) {

	document.getElementById(name +  'NewText').value = "";
	showHide(name + '_inputLine');
	showHide(name + '_addLine');

}

//----[ Função: getCurrentDate() ]--------------------------------------------------
function getCurrentDate() {

	var currentDateTime = new Date();
	
	var currYear  = currentDateTime.getFullYear();
	var currMonth = currentDateTime.getMonth() + 1;
	var currDay   = currentDateTime.getDate();
	var currHour  = currentDateTime.getHours();
	var currMin   = currentDateTime.getMinutes();

	return currDay + "/" + currMonth + "/" + currYear + " " + currHour + ":" + currMin;			

}

//----[ Função: addText() ]---------------------------------------------------------
function addText(name) {

	window.alert(name);
	
	var targetField     = document.getElementById(name);
	var sourceField     = document.getElementById(name + 'NewText');
	var hiddenField     = document.getElementById(name + '_newItems');
	var finalDate       = getCurrentDate();
	var currentContent  = targetField.value;
	var newContent      = sourceField.value;
	
	if ( newContent == "" ) {
	
		window.alert("Por favor forneca uma informação válida.");
		
	} else {
	
		if ( hiddenField.value != "blank" )
			hiddenField.value += "_";
	
		hiddenField.value += "[" + finalDate + "] " + newContent;
	
		if ( currentContent == "" )
			var finalContent = "[" + finalDate + "] " + newContent;
		else 
			var finalContent = currentContent + "\n" + "[" + finalDate + "] " + newContent;
		
		targetField.value = finalContent;

		showHide(name + '_inputLine');
		showHide(name + '_addLine');
	
	}

}

//----[ Função: initProgress() ]----------------------------------------------------
function initProgress() {
	Element.hide('msg');
	Element.show('indicator');	
}

//----[ Função: finalProgress() ]---------------------------------------------------
function finalProgress() {

	Element.hide('indicator');				
	if (window.document.infoMercadoForm.itemId.options.length == 0 ) {
		window.document.infoMercadoForm.itemId.disabled = true;					
	} else {
		window.document.infoMercadoForm.itemId.disabled = false;
	}
				
}

//----[ Função: reportError() ]-----------------------------------------------------
function reportError() {

	if (window.document.infoMercadoForm.itemId.options.length == 0) {
		window.document.getElementById("msg").innerHTML = "Erro - Valores não Encontrados!";
	}
	Element.show('msg');
	setTimeout("Element.hide('msg')", 2500);
	 
}



