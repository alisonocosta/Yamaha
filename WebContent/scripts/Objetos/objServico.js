/* Resource Inform�tica LTDA.
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......objServico.js
 *   .: Cria��o.....15 de setembro de 2008, 10:08
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Objeto: Script de objeto para nota fiscal de servi�o.
 */
 
 /** Define um objeto de Servico */
function Servico(id, code, tipo, valor, valorFormatado, chassi, revisao) {
			
				this.id   			= id;
				this.code       	= code;
				this.tipo       	= tipo;				
				this.valor      	= valor;
				this.valorFormatado = valorFormatado;
				this.chassi     	= chassi;
				this.revisao    	= revisao;
				this.setId      	= setId;
				this.getId      	= getId;
				this.setCode    	= setCode;
				this.getCode    	= getCode;
				this.setTipo    	= setTipo;
				this.getTipo    	= getTipo;
				this.setValor   	= setValor;
				this.getValor  	 	= getValor;
				this.setValorFormatado   	= setValorFormatado;
				this.getValorFormatado  	= getValorFormatado;
				this.setChassi   	= setChassi;
				this.getChassi  	= getChassi;
				this.setRevisao   	= setRevisao;
				this.getRevisao	 	= getRevisao;
				this.isNota     	= false;
				this.numNota    	= null;
				this.getIsNota  	= getIsNota;
				this.setIsNota  	= setIsNota;	
				this.getNumNota 	= getNumNota;
				this.setNumNota 	= setNumNota;
				this.getIsSelected 	= getIsSelected;
				this.setIsSelected 	= setIsSelected;
}

/* M�todo Setter para a propriedade id */
function setId(id) {

	this.id = id;
	
}

/* M�todo Getter para a propriedade id */
function getId() {

	return this.id;
	
}

/* M�todo Setter para a propriedade code */
function setCode(code) {

	this.code = code;
	
}

/* M�todo Getter para a propriedade code */
function getCode() {

	return this.code;
	
}

/* M�todo Setter para a propriedade tipo */
function setTipo(tipo) {

	this.tipo = tipo;
	
}

/* M�todo Getter para a propriedade tipo */
function getTipo() {

	return this.tipo;
	
}

/* M�todo Setter para a propriedade valor */
function setValor(valor) {

	this.valor = valor;
	
}

/* M�todo Getter para a propriedade valor */
function getValor() {

	return this.valor;
	
}

/* M�todo Setter para a propriedade valorFormatado */
function setValorFormatado(valorFormatado) {

	this.valorFormatado = valorFormatado;
	
}

/* M�todo Getter para a propriedade valorFormatado */
function getValorFormatado() {

	return this.valorFormatado;
	
}

/* M�todo Setter para a propriedade chassi */
function setChassi(chassi) {

	this.chassi = chassi;
	
}

/* M�todo Getter para a propriedade chassi */
function getChassi() {

	return this.chassi;
	
}
/* M�todo Setter para a propriedade revisao */
function setRevisao(revisao) {

	this.revisao = revisao;
	
}

/* M�todo Getter para a propriedade revisao */
function getRevisao() {

	return this.revisao;
	
}

/* M�todo Setter para a propriedade isNota */
function setIsNota(isNota) {

	this.isNota = isNota;
	
}

/* M�todo Getter para a propriedade isNota */
function getIsNota() {

	return this.isNota;
	
}

/* M�todo Setter para a propriedade numNota */
function setNumNota(numNota) {

	this.numNota = numNota;
	
}

/* M�todo Getter para a propriedade numNota */
function getNumNota() {

	return this.numNota;
	
}

/* M�todo Setter para a propriedade isSelected */
function setIsSelected(isSelected) {

	this.isSelected = isSelected;
	
}

/* M�todo Getter para a propriedade isSelected */
function getIsSelected() {

	return this.isSelected;
	
}