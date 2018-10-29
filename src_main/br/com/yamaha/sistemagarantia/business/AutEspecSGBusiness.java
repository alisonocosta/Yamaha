/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......AutEspecSGBusiness.java
 *   .: Cria��o.....21 de maio, 12:09
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Objeto de neg�cios para a entidade "AutorizacaoEspecialSG".
 */
package br.com.yamaha.sistemagarantia.business;

import java.io.Serializable;
import java.util.List;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.dao.AutorizacaoEspecialSGDao;
import br.com.yamaha.sistemagarantia.model.AutorizacaoEspecialSG;

/** Classe de neg�cios relacionada � entidade <b>AutorizacaoEspecialSG</b>.
 * 
 *  @author Edson Luiz Sumensari
 */
public class AutEspecSGBusiness extends BusinessObject {

    /** Objeto DAO para utilizado para este objeto de neg�cios. 
     *  
     */
    private AutorizacaoEspecialSGDao autorizacaoEspecialSGDao;

    /** Recupera uma linha pelo id.
     *
     *  @param entityId
     *      Refer�ncia da entidade na camada de persist�ncia.
     *  @return
     *      Um objeto <code>AutorizacaoEspecialSG</code>.
     *      
     * @throws BusinessException
     *      Se houverem erros de execu��o ou nas camadas 
     *      abaixo dos servi�os, ser�o encapsulados neste
     *      tipo de <code>Exception</code>.
     */
    public AutorizacaoEspecialSG get(Serializable entityId) throws BusinessException {

        try {

            return (AutorizacaoEspecialSG)autorizacaoEspecialSGDao.get(AutorizacaoEspecialSG.class, entityId);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }
    
    /** Lista todos as AutorizacaoEspecialSG existentes no banco de dados.
     * 
     *  @return
     *      Um objeto <code>List</code> contendo todos os 
     *      clientes persistentes na aplica��o.
     *      
     * @throws BusinessException
     *      Se houverem erros de execu��o ou nas camadas 
     *      abaixo dos servi�os, ser�o encapsulados neste
     *      tipo de <code>Exception</code>.
     */
    public List list() throws BusinessException {

        try {

            return autorizacaoEspecialSGDao.listAll(AutorizacaoEspecialSG.class);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }
    
    /** Lista todos as AutorizacaoEspecialSG existentes no banco de dados
     *  pelo chassi informado e com as datas e usu�rios de cria��o e  atualiza��o iguais.
     *  
     *  @param chassi
     *  	<code>String</chassi>  N�mera��o do Chassi.
     *  
     *  @param numero
     *  	<code>String</chassi>  N�mero da Autoriza��o especial.
     * 
     *  @return
     *      Um objeto <code>AutorizacaoEspecialSG</code> contendo a autoriza��o especial.
     *      
     * @throws BusinessException
     *      Se houverem erros de execu��o ou nas camadas 
     *      abaixo dos servi�os, ser�o encapsulados neste
     *      tipo de <code>Exception</code>.
     */
    public AutorizacaoEspecialSG getByChassiAndNum(String chassi, String numero,Serializable concessionaria_id) throws BusinessException {

        try {

            return autorizacaoEspecialSGDao.getByChassiAndNum(chassi, numero, concessionaria_id);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }
    
    /** Salva ou atualiza o estado de uma entidade.
     *  <p>
     *  Se a entidade recebida n�o existir na camada de persist�ncia
     *  da aplica��o, ser� criada. Do contr�rio seus valores ser�o
     *  atualizados de acordo com as informa��es alteradas.
     *  
     *  @param autorizacaoEspecial
     *      <code>AutorizacaoEspecialSG</code> representando a entidade a ser
     *      criada/atualizada na camada de persist�ncia.
     *      
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma BusinessException.
     */    
    public void save(AutorizacaoEspecialSG autorizacaoEspecial) throws BusinessException {

        try {

        	autorizacaoEspecialSGDao.makePersistent(autorizacaoEspecial);

        } catch ( DaoException daoExp ) {

            throw new BusinessException( daoExp );

        }

    }

	/** M�todo getter para a propriedade autorizacaoEspecialSGDao
	 * 
	 *  @return AutorizacaoEspecialSGDao
	 *
	 */
	public AutorizacaoEspecialSGDao getAutorizacaoEspecialSGDao() {
		return autorizacaoEspecialSGDao;
	}

	/** M�todo setter para a propriedade autorizacaoEspecialSGDao
	 *
	 * @param autorizacaoEspecialSGDao 
	 *           <code>AutorizacaoEspecialSGDao</code> a ser designado para autorizacaoEspecialSGDao.
	 * 
	 */
	public void setAutorizacaoEspecialSGDao(
			AutorizacaoEspecialSGDao autorizacaoEspecialSGDao) {
		this.autorizacaoEspecialSGDao = autorizacaoEspecialSGDao;
	}
    
    
}
    