/** Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RequiredWhen.js
 *   .: Cria��o.....16 de maio, 11:19
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Realiza a valida��o de um campo conforme um crit�rio.
 */
 
 
 /**
 * Realiza a valida��o de um campo conforme um crit�rio.
 * Recebe um par�metro <code>test<code> true/false.
 * Se verdadeiro realiza��o trat como requerido
 * 
 * @param Form      
 * @return <code>true</code> se o campo estiver preenchido.
 */
function validateRequiredWhen(form) {
		
	var bValid     = true;
	var formName   = form.getAttributeNode("name");
    var focusField = null;
	var i 		   = 0;	
	var fields     = new Array();
	
	var oField = eval('new ' + formName.value + '_requiredWhen()');
	
	for (x in oField) {
			
		var field = form[oField[x][0]];
		
		if (!jcv_isFieldPresent(field)) {
             continue;
        }
                		
		var test  = eval(oField[x][2]("test"));
		
		//alert("vari�vel test:" + test + " -Field:" + field.name);
		
		if ( test == true ) {
		
			if ((field.type == 'hidden' ||
	                field.type == 'text' ||
	                field.type == 'textarea' ||
	                field.type == 'select-one' ||
	                field.type == 'radio')) {
	                
	                var value = '';
	                // get field's value
	                if (field.type == "select-one") {
	                    var si = field.selectedIndex;
	                    if (si >= 0) {
	                        value = field.options[si].value;
	                    }
	                } else {
	                    value = field.value;
	                }
	                if ( (value.length == 0) || ( value == null ) ) {
	                	bValid = false;
	                }
	         }
	         
	        if ( !bValid ) {	     
    			if (i == 0) {
            		focusField = field;
            	}
				fields[i++] = oField[x][1];
			}
		 }
	     
	}        
    
    if (fields.length > 0) {
          jcv_handleErrors(fields, focusField);
    } 
   	//alert("Resultado da Valida��o:" + bValid);
    return bValid;
}
 
 