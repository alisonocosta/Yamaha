/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......LoteFormAction.java
 *   .: Criação.....24 de abril, 12:17
 *   .: Autor.......Edson Luiz Sumensari
 *                  Thiago Uriel M. Garcia
 *   .: Descrição...Action para o formulário de inclusão de Lote.
 */
package br.com.yamaha.sistemagarantia.controller.helper;

import java.util.Date;

import br.com.resource.infra.model.EntityObject;

/** Contém tarefas auxiliares para o controle da aplicação. 
 * 
 *  @author Edson Luiz Sumensari
 *  @author Thiago Uriel M. Garcia
 */
public final class ControllerHelper {

    /** Prepara uma entidade para que seja enviada ao objeto de negócios.
     *  <p>
     *  Seta seus dados básicos, compartilhados entre todas as entidades:
     *  <code>criadoPor</code>, <code>dataCriacao</code>, <code>atualizadoPor</code>,
     *  <code>dataAtualizacao</code>.
     *  
     *  @param entity
     *      <code>EntityObject</code> contendo a entidade a ser preparada
     *      para o envio ao <i>Business Object</i>.
     *  @param currentUserId
     *      <code>Long</code> do usuário logado atualmente.
     */
    public static void prepare(EntityObject entity, Long currentUserId) {
        
        if ( entity.isNew() ) {
            
            entity.setCriadoPor( currentUserId );
            entity.setDataCriacao( new Date() );
            
            entity.setAtualizadoPor( currentUserId );
            entity.setDataAtualizacao( new Date() );
            
            entity.setDataInicio( new Date() );
            
        } else {
            
            entity.setAtualizadoPor( currentUserId );
            entity.setDataAtualizacao( new Date() );
            
        }
        
    }
    
    /** Retorna uma String que representa o modelo a partir um chassi informado
     *  Extraimos os carecteres do início até a primeira ocorrência do caracter '-'
     * 
     * @param chassi
     * 	<code>String</code> Número do chassi
     * 
     * @return modelo
     * 	<code>String</code> Modelo
     */
    public static String getModeloByChassi(String chassi) {
    	
    	int index = chassi.indexOf('-');
    	
    	String modelo = "";
    	
    	if ( index > 0 ) {
    		
    		modelo = chassi.substring(0, index);
    		
    	}
    	
    	//System.out.println("---> getModeloByChassi -- Chassi:" + chassi + " - Modelo:" + modelo);
    	
    	return modelo.toUpperCase();    	
    	
    }
    
    /** Retorna uma String que representa o valor seqüêncial do chassi informado
     *  Extraimos os carecteres a partir da primeira ocorrência do caracter '-' até o final
     * 
     * @param chassi
     * 	<code>String</code> Número do chassi
     * 
     * @return sequency
     * 	<code>String</code> Seqüência do chassi
     */
    public static String getSequencyByChassi(String chassi) {
    	
    	int index = chassi.indexOf('-');
    	
    	String seq = "";
    	
    	if ( index > 0 ) {
    		
    		seq = chassi.substring(index + 1, chassi.length());
    		
    	}
    	
    	return seq.toUpperCase();    	
    	
    }
    
}