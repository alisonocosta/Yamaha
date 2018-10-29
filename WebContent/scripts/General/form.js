function apenasLetras(event){	    
    
    if (clientNavigator == "IE"){
    	tecla = event.keyCode;  
 	}else{ 		
 		tecla = event.charCode; 
 	}
    if (!(tecla >= 48 && tecla <= 57)){  
        return true;  
    }else{  
       return false;  
    } 
}

/** Monta Url para Dispatch Action.
 *
 * 
 *  @param form   Formulário que disparou esta função
 *
 *  @param task   método destino na action.
 *  @return  True ou False dependendo da confirmação do
 *           usuário.
 */
function enviar(form,task){
			var action = form.action + "?task=" + task;
			form.action = action;
			
			return true;
}

/** Monta Url para Dispatch Action e submete o form.
 *
 * 
 *  @param form   	Formulário que disparou esta função
 *
 *  @param task   	método destino na action.
 *  @param validate	Validação realizada antes true/false
 */
function submitFormTsk(form, task, validate) {

	if (validate) {
		var indexI = form.action.indexOf('?');
		var action = "";

		if (indexI > 0) {
			action = form.action.substring(0, indexI);
		
			form.action = action;
		}
		
		action = form.action + "?task=" + task;
		
		form.action = action;
		
		form.submit();
	}
	
	return false;
	
}


 //Verifica qual o browser do visitante e armazena na variável púbica clientNavigator,
 //Caso Internet Explorer(IE) outros (Other)
if (navigator.appName.indexOf('Microsoft') != -1){
 	clientNavigator = "IE";
}else{
 	clientNavigator = "Other";
}

/* Permite apenas a digitação de números */
function blockChar(evnt){

 	if (clientNavigator == "IE"){
 		if (evnt.keyCode < 48 || evnt.keyCode > 57){
 			return false
 		}
 	}else{
 		if ((evnt.charCode < 48 || evnt.charCode > 57) && evnt.keyCode == 0){
 			return false
 		}
 	}
}

//script que muda fundo da TD ou TR
// onmuoseover
function mOvr(src,clrOver) { 
 	if (!src.contains(event.fromElement)) { 
	 	src.style.cursor = 'hand';
		src.bgColor = clrOver;
	}
}
// onmouseout
function mOut(src,clrIn) { 
	if (!src.contains(event.toElement)) { 
	 	src.style.cursor = 'default';
	 	src.bgColor = clrIn;
	}
}
// onclick
function mClk(src) { 
	if(event.srcElement.tagName=='TD'){ 
		src.children.tags('A')[ 0].click();
	}
}

// Simula a função trim para o javascript
String.prototype.trim = function() {
	return this.replace(/^\s*/, "").replace(/\s*$/, "");
}


	 		
/* ////////////////////////////////////////////////////*/
/*                   Validação de Datas                */
/*/////////////////////////////////////////////////////*/
function comparaData( data1, data2 ) {

	var ano1 = data1.substring( 6 );
	var mes1 = data1.substring(3,5);
	var dia1 = data1.substring(0,2);
	var data1Str = ano1 + mes1 + dia1
	var ano2 = data2.substring( 6 );
	var mes2 = data2.substring(3,5);
	var dia2 = data2.substring(0,2);
	var data2Str = ano2 + mes2 + dia2;
	if( data1Str == data2Str ) {
		return 0;
	}
	if( data1Str > data2Str ) {
		/* Alert( 'data1 maior que data2' );*/
		return 1;
	}
	if( data1Str == data2Str ) {
		/* alert( 'data1 igual a data2' );*/
		return 0;
	}	
	if( data1Str < data2Str ) {
		/* alert( 'data1 menor que data2' ); */
		return -1;
	}	
}

function validaData(valor) {
	var date=valor;
	var ardt=new Array;
	var ExpReg=new RegExp("(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/[12][0-9]{3}");
	ardt=date.split("/");
	erro=false;
	if ( date.search(ExpReg)==-1){
		erro = true;
		}
	else if (((ardt[1]==4)||(ardt[1]==6)||(ardt[1]==9)||(ardt[1]==11))&&(ardt[0]>30))
		erro = true;
	else if ( ardt[1]==2) {
		if ((ardt[0]>28)&&((ardt[2]%4)!=0))
			erro = true;
		if ((ardt[0]>29)&&((ardt[2]%4)==0))
			erro = true;
	}
	if (erro) {
		return false;
	}
	return true;
}

