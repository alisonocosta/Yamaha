/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......AutorizacaoEspecialSGDao.java
 *   .: Criação.....15 de junho, 13:20
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Interface DAO para a entidade "AutorizacaoEspecialSG".
 */
package br.com.yamaha.sistemagarantia.dao;

import java.io.Serializable;

import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.model.AutorizacaoEspecialSG;

/** Interface contendo os métodos especializados para
 *  as entidades <b>AutorizacaoEspecialSG</b>.
 *  
 *  @author Edson Luiz Sumensari
 */
public interface AutorizacaoEspecialSGDao extends Dao {

    
    /** Pesquisa um AutorizacaoEspecialSG utilizando como critério, 
     *  datas e usuários de criação e  atualização iguais.
     * 
     *  @param chassi
     *      <code>String</code> contendo parte do nome a ser utilizado
     *      como critério de pesquisa.
     *      
     *  @param numero
     *      <code>String</code> contendo o número da Aut. Esp. a ser utilizado
     *      como critério de pesquisa.
     *  
     *  @return
     *      Uma entidade <code>AutorizacaoEspecialSG</code>, criada a
     *      partir do chassi  e núemro fornecidos.
     *  
     *  @throws DaoException
     *      Se houverem erros durante a execução estes serão lançados
     *      como uma Exception deste tipo.
     */
    public AutorizacaoEspecialSG getByChassiAndNum(String chassi, String numero, Serializable concessionaria_id) throws DaoException;    
    
    
}