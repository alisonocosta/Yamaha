/* Resource Inform�tica LTDA.
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......objPeca.js
 *   .: Cria��o.....15 de setembro de 2008, 10:13
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Objeto: Script de objeto para nota fiscal de pe�a.
 */
 
 /** Define um objeto de Pe�a */
function CobraPeca(id, lineId, qtde, descricao, itemCode, valor, valorFormatado) {
			
				this.id 		= id;			// Id da SG
				this.lineId     = lineId		// N�mero da Linha da pe�a na Garantia (lineId)	
				this.qtde       = qtde;			// Quantidade do Item
				this.itemCode   = itemCode;     // C�digo da pe�a
				this.descricao  = descricao;    // Descri��o da Pe�a
				this.valor      = valor;        // Valor total da(s) Pe�a(s)
				this.valorFormatado = valorFormatado;  // Valor total da(s) Pe�a(s) formatado para pt_BR
				this.isSelected = false;		// Identifica se a pe�a foi selecionada
				this.isNota     = false;        // Identifica se j� foi relacionada com uma Nota Fiscal
				this.numNota    = null;         // N�mero da Nota Fiscal relacionada
				this.getIsNota  = getIsNota;
				this.setIsNota  = setIsNota;	
				this.getNumNota = getNumNota;
				this.setNumNota = setNumNota;
				this.getIsSelected = getIsSelected;
				this.setIsSelected = setIsSelected;
				
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