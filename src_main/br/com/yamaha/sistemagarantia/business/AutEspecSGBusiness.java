/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......AutEspecSGBusiness.java
 *   .: Criação.....21 de maio, 12:09
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Objeto de negócios para a entidade "AutorizacaoEspecialSG".
 */
package br.com.yamaha.sistemagarantia.business;

import java.io.Serializable;
import java.util.List;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.dao.AutorizacaoEspecialSGDao;
import br.com.yamaha.sistemagarantia.model.AutorizacaoEspecialSG;

/** Classe de negócios relacionada à entidade <b>AutorizacaoEspecialSG</b>.
 * 
 *  @author Edson Luiz Sumensari
 */
public class AutEspecSGBusiness extends BusinessObject {

    /** Objeto DAO para utilizado para este objeto de negócios. 
     *  
     */
    private AutorizacaoEspecialSGDao autorizacaoEspecialSGDao;

    /** Recupera uma linha pelo id.
     *
     *  @param entityId
     *      Referência da entidade na camada de persistência.
     *  @return
     *      Um objeto <code>AutorizacaoEspecialSG</code>.
     *      
     * @throws BusinessException
     *      Se houverem erros de execução ou nas camadas 
     *      abaixo dos serviços, serão encapsulados neste
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
     *      clientes persistentes na aplicação.
     *      
     * @throws BusinessException
     *      Se houverem erros de execução ou nas camadas 
     *      abaixo dos serviços, serão encapsulados neste
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
     *  pelo chassi informado e com as datas e usuários de criação e  atualização iguais.
     *  
     *  @param chassi
     *  	<code>String</chassi>  Númeração do Chassi.
     *  
     *  @param numero
     *  	<code>String</chassi>  Número da Autorização especial.
     * 
     *  @return
     *      Um objeto <code>AutorizacaoEspecialSG</code> contendo a autorização especial.
     *      
     * @throws BusinessException
     *      Se houverem erros de execução ou nas camadas 
     *      abaixo dos serviços, serão encapsulados neste
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
     *  Se a entidade recebida não existir na camada de persistência
     *  da aplicação, será criada. Do contrário seus valores serão
     *  atualizados de acordo com as informações alteradas.
     *  
     *  @param autorizacaoEspecial
     *      <code>AutorizacaoEspecialSG</code> representando a entidade a ser
     *      criada/atualizada na camada de persistência.
     *      
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma BusinessException.
     */    
    public void save(AutorizacaoEspecialSG autorizacaoEspecial) throws BusinessException {

        try {

        	autorizacaoEspecialSGDao.makePersistent(autorizacaoEspecial);

        } catch ( DaoException daoExp ) {

            throw new BusinessException( daoExp );

        }

    }

	/** Método getter para a propriedade autorizacaoEspecialSGDao
	 * 
	 *  @return AutorizacaoEspecialSGDao
	 *
	 */
	public AutorizacaoEspecialSGDao getAutorizacaoEspecialSGDao() {
		return autorizacaoEspecialSGDao;
	}

	/** Método setter para a propriedade autorizacaoEspecialSGDao
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
    