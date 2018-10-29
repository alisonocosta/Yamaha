/* Resource Informática LTDA.
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......objPeca.js
 *   .: Criação.....15 de setembro de 2008, 10:13
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Objeto: Script de objeto para nota fiscal de peça.
 */
 
 /** Define um objeto de Peça */
function CobraPeca(id, lineId, qtde, descricao, itemCode, valor, valorFormatado) {
			
				this.id 		= id;			// Id da SG
				this.lineId     = lineId		// Número da Linha da peça na Garantia (lineId)	
				this.qtde       = qtde;			// Quantidade do Item
				this.itemCode   = itemCode;     // Código da peça
				this.descricao  = descricao;    // Descrição da Peça
				this.valor      = valor;        // Valor total da(s) Peça(s)
				this.valorFormatado = valorFormatado;  // Valor total da(s) Peça(s) formatado para pt_BR
				this.isSelected = false;		// Identifica se a peça foi selecionada
				this.isNota     = false;        // Identifica se já foi relacionada com uma Nota Fiscal
				this.numNota    = null;         // Número da Nota Fiscal relacionada
				this.getIsNota  = getIsNota;
				this.setIsNota  = setIsNota;	
				this.getNumNota = getNumNota;
				this.setNumNota = setNumNota;
				this.getIsSelected = getIsSelected;
				this.setIsSelected = setIsSelected;
				
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