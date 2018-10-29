/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......LoteFormAction.java
 *   .: Cria��o.....24 de abril, 12:17
 *   .: Autor.......Edson Luiz Sumensari
 *                  Thiago Uriel M. Garcia
 *   .: Descri��o...Action para o formul�rio de inclus�o de Lote.
 */
package br.com.yamaha.sistemagarantia.controller.helper;

import java.util.Date;

import br.com.resource.infra.model.EntityObject;

/** Cont�m tarefas auxiliares para o controle da aplica��o. 
 * 
 *  @author Edson Luiz Sumensari
 *  @author Thiago Uriel M. Garcia
 */
public final class ControllerHelper {

    /** Prepara uma entidade para que seja enviada ao objeto de neg�cios.
     *  <p>
     *  Seta seus dados b�sicos, compartilhados entre todas as entidades:
     *  <code>criadoPor</code>, <code>dataCriacao</code>, <code>atualizadoPor</code>,
     *  <code>dataAtualizacao</code>.
     *  
     *  @param entity
     *      <code>EntityObject</code> contendo a entidade a ser preparada
     *      para o envio ao <i>Business Object</i>.
     *  @param currentUserId
     *      <code>Long</code> do usu�rio logado atualmente.
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
     *  Extraimos os carecteres do in�cio at� a primeira ocorr�ncia do caracter '-'
     * 
     * @param chassi
     * 	<code>String</code> N�mero do chassi
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
    
    /** Retorna uma String que representa o valor seq��ncial do chassi informado
     *  Extraimos os carecteres a partir da primeira ocorr�ncia do caracter '-' at� o final
     * 
     * @param chassi
     * 	<code>String</code> N�mero do chassi
     * 
     * @return sequency
     * 	<code>String</code> Seq��ncia do chassi
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