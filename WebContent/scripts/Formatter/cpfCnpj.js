/**
 * @author M�rcio d'�vila
 * @version 1.01, 2004
 *
 * Adiciona m�todo lpad() � classe String.
 * Preenche a String � esquerda com o caractere fornecido,
 * at� que ela atinja o tamanho especificado.
 */
String.prototype.lped = function(pSize, pCharPad)
{
	var str = this;
	var dif = pSize - str.length;
	var ch = String(pCharPad).charAt(0);
	for (; dif>0; dif--) str = ch + str;
	return (str);
}

/**
 * Formata a string fornecida como CNPJ ou CPF, adicionando zeros
 * � esquerda se necess�rio e caracteres separadores, conforme solicitado.
 * @param String pCpfCnpj
 *      String fornecida para ser formatada.
 * @param boolean pUseSepar
 *      Indica se devem ser usados caracteres separadores (. - /).
 * @param boolean pIsCnpj
 *      Indica se a string fornecida � um CNPJ.
 *      Caso contr�rio, � CPF. Default = false (CPF).
 * @return String de CPF ou CNPJ devidamente formatada.
 */
function formataCpfCnpj(field, pUseSepar, pIsCnpj) {

	if ( pIsCnpj == null ) 
		pIsCnpj = false;
		
	var fCpfCnpj = eval(window.document.getElementById(field));
	var vCpfCnpj = fCpfCnpj.value;
	
	if ( pUseSepar == null ) 
		pUseSepar = true;
		
	var maxDigitos = pIsCnpj? 14: 11;
	var numero = String(vCpfCnpj).replace(/\D/g, "").replace(/^0+/, "");

	numero = numero.lped(maxDigitos, '0');
	
	if (pUseSepar) {
			
		if (pIsCnpj) {
			reCnpj = /(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})$/;
			numero = numero.replace(reCnpj, "$1.$2.$3/$4-$5");
		} else {
			reCpf  = /(\d{3})(\d{3})(\d{3})(\d{2})$/;
			numero = numero.replace(reCpf, "$1.$2.$3-$4");
		}		
	
		fCpfCnpj.value = numero;
		
	}
	return;
}


/**
 * Testa se a String pCpfCnpj fornecida � um CPF ou CNPJ.
 * Se a String tiver uma quantidade de d�gitos igual ou inferior
 * a 11, ser� CPF. Se for maior que 11, ser� CNPJ.
 * Qualquer formata��o que n�o seja algarismos � desconsiderada.
 * @param String pCpfCnpj
 *      String fornecida para ser testada.
 * @return <code>true</code> se a String fornecida for um CNPJ ou 
 *		   <code>false</code> se a String fornecida for um CPF	 
 *
 * @author Edson Luiz Sumensari
 */
function isCpfORCnpj(pCpfCnpj) {

	var bValid     = false;	
	var numero	   = 0;	
			
	numero = pCpfCnpj.replace(/\D/g, "");
			
	if (numero.length > 11)
		bValid = true;
	else
		bValid = false;
	
	return bValid;
	
}
