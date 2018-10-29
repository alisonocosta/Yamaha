//Variáveis globaisvar _loadTimer	= setInterval(__loadAnima,18);
var _loadPos	= 0;
var _loadDir	= 2;
var _loadLen	= 0;// Exibe o carregador e reseta os valores globais function __showAnima() {	_loadTimer	= setInterval(__loadAnima,18);	_loadPos	= 0;	_loadDir	= 2;	_loadLen	= 0;	var objLoader				= document.getElementById("carregador_pai");	objLoader.style.display		="block";	objLoader.style.visibility	="visible";	__loadAnima();}//Anima a barra de progresso
function __loadAnima(){
	var elem = document.getElementById("barra_progresso");
	if(elem != null){
		if (_loadPos==0) _loadLen += _loadDir;
		if (_loadLen>32 || _loadPos>79) _loadPos += _loadDir;
		if (_loadPos>79) _loadLen -= _loadDir;
		if (_loadPos>79 && _loadLen==0) _loadPos=0;
		elem.style.left		= _loadPos;
		elem.style.width	= _loadLen;
	}
}//Esconde o carregador
function __loadEsconde(){
	this.clearInterval(_loadTimer);
	var objLoader				= document.getElementById("carregador_pai");
	objLoader.style.display		="none";
	objLoader.style.visibility	="hidden";
}
