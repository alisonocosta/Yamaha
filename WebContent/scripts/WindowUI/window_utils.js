/**====================================================================
 *  Resource Inform�tica LTDA.
 *  Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Cria��o.....19 de abril de 2007, 15:51
 *   .: Autor.......Thiago Uriel M. Garcia
 * 	 .:				Edson Luiz Sumensari
 *   .: Descri��o...Script para ser inclu�do nas p�ginas jsp para
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

/** Criamos o prot�tipo de observer para tra�ar a��es ocorridas nas 
  * janelas. Desta forma poderemos identificar quantas janelas 
  * existem, definir melhor suas posi��es, etc.
  *
  */
function WindowObserver() { }
WindowObserver.prototype = {

	// Responde para: onDestroy, onStartResize, onStartMove, onResize, 
	//                onMove, onEndResize, onEndMove, onFocus, onBlur, 
	//                onBeforeShow, onShow, onHide, onMinimize, onMaximize,
	//                onClose. ATEN��O: N�o esque�a do "," ao fim de cada
	//                "}" encerrando uma fun��o!!!

	onClose : function() {
	
		// Uma janela foi fechada. Devemos decrementar a quantidade de 
		// janelas existentes na p�gina. Se o resultado for 0, devemos
		// definir a pr�xima posi��o como o in�cio da contagem.
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

/** Fun��o chamada para montar a janela.
 *  Par�metros:
 *    myId    - Identifica��o da janela
 *    myTitle - T�tulo para a janela
 *    MyUrl   - URL chamada
 */	
function openWindow(myId, myTitle, myUrl) {
	
	if ( windowCount > maxWindows) {
	
		window.alert("O n�mero de janelas abertas foi excedido!");
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

/** Fun��o chamada para trocar o t�tulo da janela */
function newTitle( title ) {
	
	title = "<span class='text'>" + title + "</span>";	
	win.setTitle(title);
	//win.setStatusBar(title);
	
}