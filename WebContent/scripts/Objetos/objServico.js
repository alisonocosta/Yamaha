/* Resource Informática LTDA.
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......objServico.js
 *   .: Criação.....15 de setembro de 2008, 10:08
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Objeto: Script de objeto para nota fiscal de serviço.
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

/* Método Setter para a propriedade id */
function setId(id) {

	this.id = id;
	
}

/* Método Getter para a propriedade id */
function getId() {

	return this.id;
	
}

/* Método Setter para a propriedade code */
function setCode(code) {

	this.code = code;
	
}

/* Método Getter para a propriedade code */
function getCode() {

	return this.code;
	
}

/* Método Setter para a propriedade tipo */
function setTipo(tipo) {

	this.tipo = tipo;
	
}

/* Método Getter para a propriedade tipo */
function getTipo() {

	return this.tipo;
	
}

/* Método Setter para a propriedade valor */
function setValor(valor) {

	this.valor = valor;
	
}

/* Método Getter para a propriedade valor */
function getValor() {

	return this.valor;
	
}

/* Método Setter para a propriedade valorFormatado */
function setValorFormatado(valorFormatado) {

	this.valorFormatado = valorFormatado;
	
}

/* Método Getter para a propriedade valorFormatado */
function getValorFormatado() {

	return this.valorFormatado;
	
}

/* Método Setter para a propriedade chassi */
function setChassi(chassi) {

	this.chassi = chassi;
	
}

/* Método Getter para a propriedade chassi */
function getChassi() {

	return this.chassi;
	
}
/* Método Setter para a propriedade revisao */
function setRevisao(revisao) {

	this.revisao = revisao;
	
}

/* Método Getter para a propriedade revisao */
function getRevisao() {

	return this.revisao;
	
}

/* Método Setter para a propriedade isNota */
function setIsNota(isNota) {

	this.isNota = isNota;
	
}

/* Método Getter para a propriedade isNota */
function getIsNota() {

	return this.isNota;
	
}

/* Método Setter para a propriedade numNota */
function setNumNota(numNota) {

	this.numNota = numNota;
	
}

/* Método Getter para a propriedade numNota */
function getNumNota() {

	return this.numNota;
	
}

/* Método Setter para a propriedade isSelected */
function setIsSelected(isSelected) {

	this.isSelected = isSelected;
	
}

/* Método Getter para a propriedade isSelected */
function getIsSelected() {

	return this.isSelected;
	
}