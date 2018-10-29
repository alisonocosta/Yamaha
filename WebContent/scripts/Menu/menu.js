// Declaração de constantes.
var openedId;

var bgImageNormalA = 'none';
var bgImageNormalB = 'none';
var bgImageNormalC = 'url(../images/menu_button_left_normal.gif)';
var bgImageNormalD = 'url(../images/menu_button_right_normal.gif)';
var bgImageNormalE = 'none';
var optionNormal   = 'menu_text';

var bgImageSelectedA = 'url(../images/menu_submenu_01_01.gif)';
var bgImageSelectedB = 'url(../images/menu_submenu_01_02.gif)';
var bgImageSelectedC = 'url(../images/menu_button_normal_selected.gif)';
var bgImageSelectedD = 'url(../images/menu_button_right_selected.gif)';
var bgImageSelectedE = 'url(../images/menu_submenu_02_02.gif)';
var optionSelected   = 'menu_text_selected';

// Fechar a janela e direcionar para o login
function close() {
	Windows.closeAll();
	window.location.href = "Home.do?task=close";
}


function closeMenu(id) {

	// Declara as variáveis a serem utilizadas para alterar
	// os estados do menu selecionado.
	var div = eval('document.getElementById(\'' + id + 'SubMenu\')');
	var bg01 = eval('document.getElementById(\'' + id + 'ItemA\')');
	var bg02 = eval('document.getElementById(\'' + id + 'ItemB\')');
	var bg03 = eval('document.getElementById(\'' + id + 'ItemC\')');
	var bg04 = eval('document.getElementById(\'' + id + 'ItemD\')');
	var bg05 = eval('document.getElementById(\'' + id + 'ItemE\')');
	var text = eval('document.getElementById(\'' + id + 'Option\')');
	
	div.style.display = 'none'; 
	bg01.style.backgroundImage = bgImageNormalA;
	bg02.style.backgroundImage = bgImageNormalB;
	bg03.style.backgroundImage = bgImageNormalC;
	bg04.style.backgroundImage = bgImageNormalD;
	bg05.style.backgroundImage = bgImageNormalE;
	text.setAttribute("class", optionNormal); //FireFox
	text.setAttribute("className", optionNormal); //IE				

}

function openMenu(id) {

	// Se houver outro menu aberto, devemos fechá-lo antes.
	if (openedId != null && id != openedId) {
		closeMenu(openedId);
	}

	// Declara as variáveis a serem utilizadas para alterar
	// os estados do menu selecionado.
	var div = eval('document.getElementById(\'' + id + 'SubMenu\')');
	var bg01 = eval('document.getElementById(\'' + id + 'ItemA\')');
	var bg02 = eval('document.getElementById(\'' + id + 'ItemB\')');
	var bg03 = eval('document.getElementById(\'' + id + 'ItemC\')');
	var bg04 = eval('document.getElementById(\'' + id + 'ItemD\')');
	var bg05 = eval('document.getElementById(\'' + id + 'ItemE\')');
	var text = eval('document.getElementById(\'' + id + 'Option\')');

	if (div.style.display == 'none') {
	
		div.style.display = 'block';
		bg01.style.backgroundImage = bgImageSelectedA;
		bg02.style.backgroundImage = bgImageSelectedB;
		bg03.style.backgroundImage = bgImageSelectedC;
		bg04.style.backgroundImage = bgImageSelectedD;
		bg05.style.backgroundImage = bgImageSelectedE;
		text.setAttribute("class", optionSelected); //FireFox
		text.setAttribute("className", optionSelected); //IE
		
	} else {
	
		closeMenu(id);
		
	}
	
	openedId = id;

}