/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......ClienteBusiness.java
 *   .: Cria��o.....02 de abril, 17:12
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descri��o...Objeto de neg�cios para a entidade "Cliente".
 */
package br.com.yamaha.sistemagarantia.business;

import java.io.Serializable;
import java.util.List;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.BusinessRuleException;
import br.com.resource.infra.exception.DaoException;
import br.com.resource.infra.utils.StringUtils;
import br.com.yamaha.sistemagarantia.dao.ClienteDao;
import br.com.yamaha.sistemagarantia.model.Cliente;
import br.com.yamaha.sistemagarantia.model.Concessionaria;

/** Classe de neg�cios relacionada � entidade <b>Cliente</b>.
 * 
 *  @author Thiago Uriel M. Garcia
 */
public class ClienteBusiness extends BusinessObject {

    /** Objeto DAO para <i>clientes</i> utilizado para este 
     *  objeto de neg�cios.
     */
    private ClienteDao clienteDao;    


    /** Recupera um cliente pelo id.
     *
     *  @param entityId
     *      Refer�ncia da entidade na camada de persist�ncia.
     *  @return
     *      Um objeto <code>Cliente</code>.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
     */
    public Cliente get(Serializable entityId) throws BusinessException {

        try {

            return (Cliente)clienteDao.get(Cliente.class, entityId);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }

    /** Obt�m um cliente a partir de seu CPF ou CNPJ.
     * 
     *  @param cpfCnpj
     *      Par�metro de pesquisa a ser utilizado na busca pelo
     *      cliente na camada de persist�ncia.
     *  
     *  @return
     *      Um cliente relacionado ao CPF/CNPJ ou <code>null</code>
     *      caso nenhum cliente seja encontrado.
     *  
     *  @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
     */
    public Cliente getByCpfCnpj(Long cpfCnpj) throws BusinessException {

        try {
            
            return clienteDao.getByCpfCnpj(cpfCnpj);
            
        } catch (DaoException dExp) {
            
            throw new BusinessException(dExp);
            
        }
        
    }
    
    /** Obt�m um cliente a partir de um chassi e concession�ria.
     * 
      *  @param chassi
     *      <code>String</code> contendo o chassi.
     *      
     *  @param concessionaria
     *      <code>Concessionaria</code> Entidade concessionaria do usu�rio.
     *  
     *  @return
     *      Uma entidade <code>Cliente</code>.
     *  
     *  @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
     */
    public Cliente getByChassi(String chassi, Concessionaria concessionaria) throws BusinessException {

        try {
            
            return clienteDao.getByChassi(chassi, concessionaria);
            
        } catch (DaoException dExp) {
            
            throw new BusinessException(dExp);
            
        }
        
    }
    
    /** Lista todos os clientes existentes no banco de dados.
     *  @return
     *      Um objeto <code>List</code> contendo todos os 
     *      clientes persistentes na aplica��o.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
     */
    public List list() throws BusinessException {

        try {

            return clienteDao.listAll(Cliente.class);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    } 
    
    /** Lista todos os clientes que possuam cupons de uma concession�ria.
     * 
     *  @param concessionaria
     *  	<code>Concessionaria</code> Entidade da Concession�ria.
     *    
     * 
     *  @return
     *      Um objeto <code>List</code> contendo todos os 
     *      clientes persistentes na aplica��o.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
     */
    public List listByConcessionaria(Concessionaria concessionaria) throws BusinessException {
    	
    	try {

            return clienteDao.listByConcessionaria(concessionaria);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }
    	
    }
    
    
    /** Valida um CEP
     * 
     * 
     * @param cep
     * 	<code>Long</code> CEP informado
     * 
     * @return boolean
     * 	
     * 
     * @throws BusinessException
     */
    public boolean isValidCep( Long cep ) throws BusinessException {
    	
    	try {

            return clienteDao.isValidCep( cep );

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }
    	
    }
    

    /** Lista os clientes existentes no banco de dados de acordo
     *  com par�metros limitadores fornecidos.
     *
     *  @param start
     *      Indica por onde a lista dever� ser iniciada.
     *  @param limit
     *      Limite de resultados a serem apresentados na lista.
     *  @param orderType
     *      Tipo de ordem utilizada, sendo 1=asc, 2=desc.
     *  @param orderField
     *      Campo da entidade a ser utilizado como ordem.
     * 
     *  @return
     *      Um objeto <code>List</code> contendo os 
     *      clientes persistentes na aplica��o, de acordo com
     *      os crit�rios limitadores fornecidos.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
     */
    public List list(int start, int limit, int orderType, String orderField) throws BusinessException {

        try {

            return clienteDao.list(Cliente.class, start, limit, orderType, orderField);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }


    /** Obt�m o tamanho total de uma listagem. 
     *  
     *  @return
     *      Um <code>int</code> contendo o tamanho da listagem.
     *  
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
     */
    public int getTotals() throws BusinessException {

        try { 

            return clienteDao.getFullListSize(Cliente.class);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }    


    /** Salva ou atualiza o estado de uma entidade.
     *  <p>
     *  Se a entidade recebida n�o existir na camada de persist�ncia
     *  da aplica��o, ser� criada. Do contr�rio seus valores ser�o
     *  atualizados de acordo com as informa��es alteradas.
     *  <p>
     *  No entanto, antes de proceder com o processo de persist�ncia
     *  algumas valida��es ir�o ocorrer. Se houverem problemas, ser�o
     *  lan�ados como <code>BusinessRuleExceptions</code>.
     *  
     *  @param cliente
     *      <code>cliente</code> representando a entidade a ser
     *      criada/atualizada na camada de persist�ncia.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
     */    
    public void save(Cliente cliente) throws BusinessException {

        try {

            // � um cliente novo. Devemos proceder com valida��es
            // de integridade antes de salv�-lo na base.
            if ( cliente.isNew() ) {
            
                // Valida��o de CPF/CNPJ j� existentes.
                if ( this.getByCpfCnpj( cliente.getCnpj() ) != null )
                    throw new BusinessRuleException("customer.msg.cpfCnpjTaken");
                
            }           
            
            //System.out.println("Cliente ID:" + cliente.getEntityId() + " - Nome:" + cliente.getNome() + " - CPF:" + cliente.getCnpj());
            // Se chegamos aqui, n�o houveram erros. Podemos
            // salvar o cliente na base de dados.
            clienteDao.makePersistent(cliente);

        } catch ( DaoException daoExp ) {

            throw new BusinessException( daoExp );

        }

    }

    /** Verifica se o cliente possui informa��o de contato (telefones e email).
     * 
     *  @param cliente
     *      Entidade de cliente a ser verificada.
     *  
     *  @return
     *      Um valor booleano indicando se o cliente possui ou n�o informa��es
     *      de contato.
     */
    public boolean hasContactInformation(Cliente cliente) {
        
        // Verificamos se o cliente possui algum telefone dispon�vel.
        // Caso n�o possua, iremos verificar em seguida se existe algum email cadastrado.
        //
        // Se n�o houver nenhum email cadastrado o cliente n�o possui informa��es
        // de contado cadastradas, ent�o retornaremos "false".
        if ( cliente.getTelefoneDisponivel() == null || cliente.getTelefoneDisponivel().equals("N�o informado.") ) {
            
            if ( cliente.getEmail() == null || cliente.getEmail().equals("") ) {
                
                return false;
                
            }
            
        }
        
        // Caso contr�rio, retornaremos "true".
        return true;
        
    }

    /** Retira a m�scara de um CEP/CPF/CNPJ de um cliente.
     * 
     *  @param maskedCpfCnpj
     *      <code>String</code> CEP, CPF ou CNPj com m�scara.
     *  
     *  @return
     *      Um <code>Long</code> contendo os n�meros sem m�scara.
     */
    public Long unmaskCepCpfCnpj(String maskedCepCpfCnpj) {
    
        String unmaskedCepCpfCnpj = maskedCepCpfCnpj;
        
        unmaskedCepCpfCnpj = StringUtils.replace(unmaskedCepCpfCnpj, ".", "");
        unmaskedCepCpfCnpj = StringUtils.replace(unmaskedCepCpfCnpj, "/", "");
        unmaskedCepCpfCnpj = StringUtils.replace(unmaskedCepCpfCnpj, "-", "");
        
        return new Long( unmaskedCepCpfCnpj );
        
    }
    
    /** Obt�m uma listagem de CPF/CNPJ e nomes de cliente, aproxiamda limitada de acordo
     *  com a parte inicial do CPF/CNPJ passado.
     *     
     * @param cpfCnpjPart
     *      <code>String<code> parte co CPF/CNPJ para pesquisa aproximada.
     *      
     * @param concessionaria
     * 		<code>Concessionaria</code> Entidade concessionaria do usu�rio. 
     * 
     * @return
     *      Uma listagem de elementos, organizada e restrita aos 
     *      delimitadores fornecidos.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
     */
    public List listApproachedByCpfCnpjByConc(String cpfCnpjPart, Concessionaria concessionaria) throws BusinessException {

    	try {

            return clienteDao.listApproachedByCpfCnpjByConc(cpfCnpjPart, concessionaria);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }
    
    /** Obt�m uma listagem de CPF/CNPJ e nomes de cliente, aproxiamda limitada de acordo
     *  com a parte inicial do CPF/CNPJ passado.
     *     
     * @param cpfCnpjPart
     *      <code>String<code> parte co CPF/CNPJ para pesquisa aproximada.
     *
     * 
     * @return
     *      Uma listagem de elementos, organizada e restrita aos 
     *      delimitadores fornecidos.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
     */
    public List listApproachedByCpfCnpj(String cpfCnpjPart) throws BusinessException {

    	try {

            return clienteDao.listApproachedByCpfCnpj(cpfCnpjPart);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }
    
    /** Obt�m uma listagem de nomes de cliente, limitada de acordo
     *  com a  Chassi passado.
     *     
     * @param chassi
     *      <code>String<code> Chassi.
     *      
     * @param concessionaria
     * 		<code>Concessionaria</code> Entidade concessionaria do usu�rio. 
     * 
     * @return
     *      Uma listagem de elementos, organizada e restrita aos 
     *      delimitadores fornecidos.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
     */
    public List listByChassiByConc(String chassi, Concessionaria concessionaria) throws BusinessException {

    	try {

            return clienteDao.listByChassiByConc(chassi, concessionaria);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }
    
    /** Obt�m uma listagem por parte dos nomes dos clientes.
     * 
     *  @param namePart
     *  @param concessionaria
     *  
     *  @return List
     *  @throws BusinessException
     */
    public List listByNamePart(String namePart, Concessionaria concessionaria) throws BusinessException {
    	
    	try {

            return clienteDao.searchByName(namePart, concessionaria);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }    	
    	
    }
    
    /** Obt�m uma listagem por parte dos nomes dos clientes.
     * 
     *  @param namePart
     *  @param concessionaria
     *  
     *  @return List
     *  @throws BusinessException
     */
    public List listByNamePart(String namePart) throws BusinessException {
    	
    	try {

            return clienteDao.listApproachedByName(namePart);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }    	
    	
    }
    
    /** M�todo getter para "clienteDao".
     *  @return
     *      <code>ClienteDao</code>. O valor atual de clienteDao.
     */
    public ClienteDao getClienteDao() {
        return this.clienteDao;
    }

    /** M�todo setter para "clienteDao".
     *  @param entityId
     *      <code>ClienteDao</code> a ser designado para clienteDao.
     */
    public void setClienteDao(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

}