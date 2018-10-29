/** Resource Informática LTDA.
 *  Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......ceo.js
 *   .: Criação.....04 de maio de 2007, 18:11
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Validação e Formatação de CEP
 */


/*
 Padawan's JavaScript-Mega-Validator 3000+
 Todos os direitos reservados para Diego Pires Plentz
 Você pode usar esse código nas suas páginas desde que mantenha os créditos ;-)
*/
 
 //Verifica qual o browser do visitante e armazena na variável púbica clientNavigator,
 //Caso Internet Explorer(IE) outros (Other)
if (navigator.appName.indexOf('Microsoft') != -1){
 	clientNavigator = "IE";
}else{
 	clientNavigator = "Other";
}

/** Verifica se é um CEP válido */
function isCep(cep, obrigatorio){
 //Se o parâmetro obrigatório for igual à zero, significa que elepode estar vazio, caso contrário, não
 	var cep    = document.getElementById(cep);
 	var strcep = cep.value;
 	if((obrigatorio == 1) || (obrigatorio == 0 && strcep != "")){
 		if (strcep.length != 9){
 			alert("CEP informado inválido.");
 			cep.focus();
 			return false
 		}else{
 			if (strcep.indexOf("-") != 5){
 				alert("Formato de CEP informado inválido.");
 				cep.focus();
 				return false
 			}else{
 				if (isNaN(strcep.replace("-","0"))){
 					alert("CEP informado inválido.");
 					cep.focus();
 					return false
 				}
 			}
 		}
 	}	  
}

/* Adiciona uma máscar de CEP */
function formatCep(input, evnt){
 //Ajusta máscara de CEP e só permite digitação de números
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



 
 