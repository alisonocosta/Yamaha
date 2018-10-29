function tokenizer(source, separator) {

	var tokens = new Array();
    tokenIndex = 0;
    
    sourceLength = source.length;
    pos = 0;
    
    for ( i=0; i<sourceLength; i++){
    
		if(source.charAt(i) == separator) {
		
	    	tokens[tokenIndex] = source.substring(pos,i);
	     	pos = i + 1;
	     	tokenIndex++;
	     	
	 	}
   	}
   	
    return tokens;

}