/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......AutorizacaoEspecialSGDao.java
 *   .: Cria��o.....15 de junho, 13:20
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Interface DAO para a entidade "AutorizacaoEspecialSG".
 */
package br.com.yamaha.sistemagarantia.dao;

import java.io.Serializable;

import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.model.AutorizacaoEspecialSG;

/** Interface contendo os m�todos especializados para
 *  as entidades <b>AutorizacaoEspecialSG</b>.
 *  
 *  @author Edson Luiz Sumensari
 */
public interface AutorizacaoEspecialSGDao extends Dao {

    
    /** Pesquisa um AutorizacaoEspecialSG utilizando como crit�rio, 
     *  datas e usu�rios de cria��o e  atualiza��o iguais.
     * 
     *  @param chassi
     *      <code>String</code> contendo parte do nome a ser utilizado
     *      como crit�rio de pesquisa.
     *      
     *  @param numero
     *      <code>String</code> contendo o n�mero da Aut. Esp. a ser utilizado
     *      como crit�rio de pesquisa.
     *  
     *  @return
     *      Uma entidade <code>AutorizacaoEspecialSG</code>, criada a
     *      partir do chassi  e n�emro fornecidos.
     *  
     *  @throws DaoException
     *      Se houverem erros durante a execu��o estes ser�o lan�ados
     *      como uma Exception deste tipo.
     */
    public AutorizacaoEspecialSG getByChassiAndNum(String chassi, String numero, Serializable concessionaria_id) throws DaoException;    
    
    
}