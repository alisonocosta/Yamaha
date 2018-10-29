/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......ClienteBusiness.java
 *   .: Criação.....02 de abril, 17:12
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Objeto de negócios para a entidade "Cliente".
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

/** Classe de negócios relacionada à entidade <b>Cliente</b>.
 * 
 *  @author Thiago Uriel M. Garcia
 */
public class ClienteBusiness extends BusinessObject {

    /** Objeto DAO para <i>clientes</i> utilizado para este 
     *  objeto de negócios.
     */
    private ClienteDao clienteDao;    


    /** Recupera um cliente pelo id.
     *
     *  @param entityId
     *      Referência da entidade na camada de persistência.
     *  @return
     *      Um objeto <code>Cliente</code>.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      será lançada. Caso hajam problemas de regras de negócio, uma
     *      BusinessRuleException (subclasse de BusinessException) será 
     *      lançada.
     */
    public Cliente get(Serializable entityId) throws BusinessException {

        try {

            return (Cliente)clienteDao.get(Cliente.class, entityId);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }

    /** Obtém um cliente a partir de seu CPF ou CNPJ.
     * 
     *  @param cpfCnpj
     *      Parâmetro de pesquisa a ser utilizado na busca pelo
     *      cliente na camada de persistência.
     *  
     *  @return
     *      Um cliente relacionado ao CPF/CNPJ ou <code>null</code>
     *      caso nenhum cliente seja encontrado.
     *  
     *  @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      será lançada. Caso hajam problemas de regras de negócio, uma
     *      BusinessRuleException (subclasse de BusinessException) será 
     *      lançada.
     */
    public Cliente getByCpfCnpj(Long cpfCnpj) throws BusinessException {

        try {
            
            return clienteDao.getByCpfCnpj(cpfCnpj);
            
        } catch (DaoException dExp) {
            
            throw new BusinessException(dExp);
            
        }
        
    }
    
    /** Obtém um cliente a partir de um chassi e concessionária.
     * 
      *  @param chassi
     *      <code>String</code> contendo o chassi.
     *      
     *  @param concessionaria
     *      <code>Concessionaria</code> Entidade concessionaria do usuário.
     *  
     *  @return
     *      Uma entidade <code>Cliente</code>.
     *  
     *  @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      será lançada. Caso hajam problemas de regras de negócio, uma
     *      BusinessRuleException (subclasse de BusinessException) será 
     *      lançada.
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
     *      clientes persistentes na aplicação.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      será lançada. Caso hajam problemas de regras de negócio, uma
     *      BusinessRuleException (subclasse de BusinessException) será 
     *      lançada.
     */
    public List list() throws BusinessException {

        try {

            return clienteDao.listAll(Cliente.class);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    } 
    
    /** Lista todos os clientes que possuam cupons de uma concessionária.
     * 
     *  @param concessionaria
     *  	<code>Concessionaria</code> Entidade da Concessionária.
     *    
     * 
     *  @return
     *      Um objeto <code>List</code> contendo todos os 
     *      clientes persistentes na aplicação.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      será lançada. Caso hajam problemas de regras de negócio, uma
     *      BusinessRuleException (subclasse de BusinessException) será 
     *      lançada.
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
     *  com parâmetros limitadores fornecidos.
     *
     *  @param start
     *      Indica por onde a lista deverá ser iniciada.
     *  @param limit
     *      Limite de resultados a serem apresentados na lista.
     *  @param orderType
     *      Tipo de ordem utilizada, sendo 1=asc, 2=desc.
     *  @param orderField
     *      Campo da entidade a ser utilizado como ordem.
     * 
     *  @return
     *      Um objeto <code>List</code> contendo os 
     *      clientes persistentes na aplicação, de acordo com
     *      os critérios limitadores fornecidos.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      será lançada. Caso hajam problemas de regras de negócio, uma
     *      BusinessRuleException (subclasse de BusinessException) será 
     *      lançada.
     */
    public List list(int start, int limit, int orderType, String orderField) throws BusinessException {

        try {

            return clienteDao.list(Cliente.class, start, limit, orderType, orderField);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }


    /** Obtém o tamanho total de uma listagem. 
     *  
     *  @return
     *      Um <code>int</code> contendo o tamanho da listagem.
     *  
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      será lançada. Caso hajam problemas de regras de negócio, uma
     *      BusinessRuleException (subclasse de BusinessException) será 
     *      lançada.
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
     *  Se a entidade recebida não existir na camada de persistência
     *  da aplicação, será criada. Do contrário seus valores serão
     *  atualizados de acordo com as informações alteradas.
     *  <p>
     *  No entanto, antes de proceder com o processo de persistência
     *  algumas validações irão ocorrer. Se houverem problemas, serão
     *  lançados como <code>BusinessRuleExceptions</code>.
     *  
     *  @param cliente
     *      <code>cliente</code> representando a entidade a ser
     *      criada/atualizada na camada de persistência.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      será lançada. Caso hajam problemas de regras de negócio, uma
     *      BusinessRuleException (subclasse de BusinessException) será 
     *      lançada.
     */    
    public void save(Cliente cliente) throws BusinessException {

        try {

            // É um cliente novo. Devemos proceder com validações
            // de integridade antes de salvá-lo na base.
            if ( cliente.isNew() ) {
            
                // Validação de CPF/CNPJ já existentes.
                if ( this.getByCpfCnpj( cliente.getCnpj() ) != null )
                    throw new BusinessRuleException("customer.msg.cpfCnpjTaken");
                
            }           
            
            //System.out.println("Cliente ID:" + cliente.getEntityId() + " - Nome:" + cliente.getNome() + " - CPF:" + cliente.getCnpj());
            // Se chegamos aqui, não houveram erros. Podemos
            // salvar o cliente na base de dados.
            clienteDao.makePersistent(cliente);

        } catch ( DaoException daoExp ) {

            throw new BusinessException( daoExp );

        }

    }

    /** Verifica se o cliente possui informação de contato (telefones e email).
     * 
     *  @param cliente
     *      Entidade de cliente a ser verificada.
     *  
     *  @return
     *      Um valor booleano indicando se o cliente possui ou não informações
     *      de contato.
     */
    public boolean hasContactInformation(Cliente cliente) {
        
        // Verificamos se o cliente possui algum telefone disponível.
        // Caso não possua, iremos verificar em seguida se existe algum email cadastrado.
        //
        // Se não houver nenhum email cadastrado o cliente não possui informações
        // de contado cadastradas, então retornaremos "false".
        if ( cliente.getTelefoneDisponivel() == null || cliente.getTelefoneDisponivel().equals("Não informado.") ) {
            
            if ( cliente.getEmail() == null || cliente.getEmail().equals("") ) {
                
                return false;
                
            }
            
        }
        
        // Caso contrário, retornaremos "true".
        return true;
        
    }

    /** Retira a máscara de um CEP/CPF/CNPJ de um cliente.
     * 
     *  @param maskedCpfCnpj
     *      <code>String</code> CEP, CPF ou CNPj com máscara.
     *  
     *  @return
     *      Um <code>Long</code> contendo os números sem máscara.
     */
    public Long unmaskCepCpfCnpj(String maskedCepCpfCnpj) {
    
        String unmaskedCepCpfCnpj = maskedCepCpfCnpj;
        
        unmaskedCepCpfCnpj = StringUtils.replace(unmaskedCepCpfCnpj, ".", "");
        unmaskedCepCpfCnpj = StringUtils.replace(unmaskedCepCpfCnpj, "/", "");
        unmaskedCepCpfCnpj = StringUtils.replace(unmaskedCepCpfCnpj, "-", "");
        
        return new Long( unmaskedCepCpfCnpj );
        
    }
    
    /** Obtém uma listagem de CPF/CNPJ e nomes de cliente, aproxiamda limitada de acordo
     *  com a parte inicial do CPF/CNPJ passado.
     *     
     * @param cpfCnpjPart
     *      <code>String<code> parte co CPF/CNPJ para pesquisa aproximada.
     *      
     * @param concessionaria
     * 		<code>Concessionaria</code> Entidade concessionaria do usuário. 
     * 
     * @return
     *      Uma listagem de elementos, organizada e restrita aos 
     *      delimitadores fornecidos.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */
    public List listApproachedByCpfCnpjByConc(String cpfCnpjPart, Concessionaria concessionaria) throws BusinessException {

    	try {

            return clienteDao.listApproachedByCpfCnpjByConc(cpfCnpjPart, concessionaria);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }
    
    /** Obtém uma listagem de CPF/CNPJ e nomes de cliente, aproxiamda limitada de acordo
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
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */
    public List listApproachedByCpfCnpj(String cpfCnpjPart) throws BusinessException {

    	try {

            return clienteDao.listApproachedByCpfCnpj(cpfCnpjPart);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }
    
    /** Obtém uma listagem de nomes de cliente, limitada de acordo
     *  com a  Chassi passado.
     *     
     * @param chassi
     *      <code>String<code> Chassi.
     *      
     * @param concessionaria
     * 		<code>Concessionaria</code> Entidade concessionaria do usuário. 
     * 
     * @return
     *      Uma listagem de elementos, organizada e restrita aos 
     *      delimitadores fornecidos.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */
    public List listByChassiByConc(String chassi, Concessionaria concessionaria) throws BusinessException {

    	try {

            return clienteDao.listByChassiByConc(chassi, concessionaria);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }
    
    /** Obtém uma listagem por parte dos nomes dos clientes.
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
    
    /** Obtém uma listagem por parte dos nomes dos clientes.
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
    
    /** Método getter para "clienteDao".
     *  @return
     *      <code>ClienteDao</code>. O valor atual de clienteDao.
     */
    public ClienteDao getClienteDao() {
        return this.clienteDao;
    }

    /** Método setter para "clienteDao".
     *  @param entityId
     *      <code>ClienteDao</code> a ser designado para clienteDao.
     */
    public void setClienteDao(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

}