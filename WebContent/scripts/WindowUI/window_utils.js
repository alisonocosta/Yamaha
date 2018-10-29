/**====================================================================
 *  Resource Informática LTDA.
 *  Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Criação.....19 de abril de 2007, 15:51
 *   .: Autor.......Thiago Uriel M. Garcia
 * 	 .:				Edson Luiz Sumensari
 *   .: Descrição...Script para ser incluído nas páginas jsp para
 *					tratamento das janelas do sistema
 *====================================================================*/

var incrementTop  = 23;
var incrementLeft = 23;

var firstTop  = 98;
var nextTop   = 98;
var firstLeft = 170;
var nextLeft  = 170;

var windowCount = 0;
var maxWindows  = 2;

/** Criamos o protótipo de observer para traçar ações ocorridas nas 
  * janelas. Desta forma poderemos identificar quantas janelas 
  * existem, definir melhor suas posições, etc.
  *
  */
function WindowObserver() { }
WindowObserver.prototype = {

	// Responde para: onDestroy, onStartResize, onStartMove, onResize, 
	//                onMove, onEndResize, onEndMove, onFocus, onBlur, 
	//                onBeforeShow, onShow, onHide, onMinimize, onMaximize,
	//                onClose. ATENÇÃO: Não esqueça do "," ao fim de cada
	//                "}" encerrando uma função!!!

	onClose : function() {
	
		// Uma janela foi fechada. Devemos decrementar a quantidade de 
		// janelas existentes na página. Se o resultado for 0, devemos
		// definir a próxima posição como o início da contagem.
		windowCount--;
		if (windowCount == 0) {
			
			nextTop  = firstTop;
			nextLeft = firstLeft;
			
		} else  if ( windowCount % 4 == 0 ) {
	
			nextTop   = firstTop;
			nextLeft -= incrementLeft;
	
		} else {

			nextTop  += incrementTop;	
			nextLeft -= incrementLeft;
		
		}
	
	}
	
};

var observer = new WindowObserver;

/** Função chamada para montar a janela.
 *  Parâmetros:
 *    myId    - Identificação da janela
 *    myTitle - Título para a janela
 *    MyUrl   - URL chamada
 */	
function openWindow(myId, myTitle, myUrl) {
	
	if ( windowCount > maxWindows) {
	
		window.alert("O número de janelas abertas foi excedido!");
		return;
	
	}
	
	myTitle = "<span class='text'>" + myTitle + "</span>";	
	
	Windows.addObserver(observer);
	
	if ( windowCount == 0 ) {
	
		win = new Window( { className: "alphacube", 
							title: myTitle, 
							top:nextTop,
							left:nextLeft,
							width:800, 
							height:460, 
							resizable: true, 
							url: myUrl,
							minimizable: false,
							draggable: false,
							//showEffect: Element.show,
							//hideEffect: Element.hide,
							wiredDrag: false } );
							
		windowCount++;
		
	} else { 
		
		win.setURL(myUrl);
		newTitle(myTitle);
		
	}
					
	//win.setStatusBar(myTitle);
	win.show();
	
	win.setDestroyOnClose();
	
	if ( windowCount % 4 == 0 ) {
	
		nextTop   = firstTop;
		nextLeft += incrementLeft;
	
	} else {

		nextTop  += incrementTop;	
		nextLeft += incrementLeft;
		
	}

}

/** Função chamada para trocar o título da janela */
function newTitle( title ) {
	
	title = "<span class='text'>" + title + "</span>";	
	win.setTitle(title);
	//win.setStatusBar(title);
	
}