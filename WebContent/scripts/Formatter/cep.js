/** Resource Inform�tica LTDA.
 *  Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......ceo.js
 *   .: Cria��o.....04 de maio de 2007, 18:11
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Valida��o e Formata��o de CEP
 */


/*
 Padawan's JavaScript-Mega-Validator 3000+
 Todos os direitos reservados para Diego Pires Plentz
 Voc� pode usar esse c�digo nas suas p�ginas desde que mantenha os cr�ditos ;-)
*/
 
 //Verifica qual o browser do visitante e armazena na vari�vel p�bica clientNavigator,
 //Caso Internet Explorer(IE) outros (Other)
if (navigator.appName.indexOf('Microsoft') != -1){
 	clientNavigator = "IE";
}else{
 	clientNavigator = "Other";
}

/** Verifica se � um CEP v�lido */
function isCep(cep, obrigatorio){
 //Se o par�metro obrigat�rio for igual � zero, significa que elepode estar vazio, caso contr�rio, n�o
 	var cep    = document.getElementById(cep);
 	var strcep = cep.value;
 	if((obrigatorio == 1) || (obrigatorio == 0 && strcep != "")){
 		if (strcep.length != 9){
 			alert("CEP informado inv�lido.");
 			cep.focus();
 			return false
 		}else{
 			if (strcep.indexOf("-") != 5){
 				alert("Formato de CEP informado inv�lido.");
 				cep.focus();
 				return false
 			}else{
 				if (isNaN(strcep.replace("-","0"))){
 					alert("CEP informado inv�lido.");
 					cep.focus();
 					return false
 				}
 			}
 		}
 	}	  
}

/* Adiciona uma m�scar de CEP */
function formatCep(input, evnt){
 //Ajusta m�scara de CEP e s� permite digita��o de n�meros
 	if (input.value.length == 5){
 		if(clientNavigator == "IE"){
 			input.value += "-";
 		}else{
 			if(evnt.keyCode == 0){
 				input.value += "-";
 			}
 		}
 	}
}

function formatarCEP(src, mask) {
	
		var i = src.value.length;
		var saida = mask.substring(0,1);
		var texto = mask.substring(i);
		
		if ( texto.substring(0,1) != saida ) {
			src.value += texto.substring(0,1);
		}
		
}



 
 