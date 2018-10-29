/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......EstadoDddDao.java
 *   .: Cria��o.....26 de julho de 2009, 11:56
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Interface DAO para a entidade "EstadoDDD".
 */
package br.com.yamaha.sistemagarantia.dao;

import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.model.EstadoDdd;

/** Interface contendo os m�todos especializados para
 *  as entidades <b>EstadoDDD</b>.
 *  
 *  @author Edson Luiz Sumensari
 */
public interface EstadoDddDao extends Dao {

    /** Obt�m um ddd para valida��o.
     * 
     *  @param ddd
     *      <code>Long</code> representando ddd para um telefone.
     *  
     *  @return
     *      Um ddd relacionado ao valor fornecido ou <code>null</code>
     *      caso nenhum ddd seja encontrado.
     *  
     *  @throws DaoException
     *      Se houverem erros durante a execu��o estes ser�o lan�ados
     *      como uma Exception deste tipo.
     */
    public EstadoDdd getByDdd(Long ddd) throws DaoException; 
    
    /** Obt�m um ddd para valida��o.
     * 
     *  @param ddd
     *      <code>Long</code> representando ddd para um telefone.
     *      
     *  @param estadoId
     *      <code>Long</code> representando o id do estado.
     *  
     *  @return
     *      Um estadoDdd relacionado ao valor fornecido ou <code>null</code>
     *      caso nenhum ddd seja encontrado.
     *  
     *  @throws DaoException
     *      Se houverem erros durante a execu��o estes ser�o lan�ados
     *      como uma Exception deste tipo.
     */
    public EstadoDdd getByEstado(Long ddd, Long estadoId) throws DaoException;
    
}