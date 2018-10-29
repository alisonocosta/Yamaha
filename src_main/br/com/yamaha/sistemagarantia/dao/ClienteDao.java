/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......ClienteDao.java
 *   .: Criação.....03 de abril, 17:14
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Interface DAO para a entidade "Cliente".
 */
package br.com.yamaha.sistemagarantia.dao;

import java.util.List;

import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.model.Cliente;
import br.com.yamaha.sistemagarantia.model.Concessionaria;

/** Interface contendo os métodos especializados para
 *  as entidades <b>Cliente</b>.
 *  
 *  @author Thiago Uriel M. Garcia
 */
public interface ClienteDao extends Dao {

    /** Obtém um cliente a partir de seu CPF ou CNPJ.
     * 
     *  @param cpfCnpj
     *      <code>Long</code> representando o CPF ou CNPJ do cliente.
     *  
     *  @return
     *      Um cliente relacionado ao valor fornecido ou <code>null</code>
     *      caso nenhum cliente seja encontrado.
     *  
     *  @throws DaoException
     *      Se houverem erros durante a execução estes serão lançados
     *      como uma Exception deste tipo.
     */
    public Cliente getByCpfCnpj(Long cpfCnpj) throws DaoException;    
    
    /** Lista de clientes utilizando como critério, parte do nome.
     * 
     *  @param name
     *      <code>String</code> contendo parte do nome a ser utilizado
     *      como critério de pesquisa.
     *  
     *  
     *  @return
     *      Uma lista de entidades <code>Cliente</code>, criada a
     *      partir do nome fornecido.
     *  
     *  @throws DaoException
     *      Se houverem erros durante a execução estes serão lançados
     *      como uma Exception deste tipo.
     */
    public List listApproachedByName(String name) throws DaoException;
    
    /** Pesquisa um cliente utilizando como critério, parte do nome.
     * 
     *  @param name
     *      <code>String</code> contendo parte do nome a ser utilizado
     *      como critério de pesquisa.
     *      
     *  @param concessionaria
     *      <code>Concessionaria</code> Entidade concessionaria do usuário.
     *  
     *  @return
     *      Uma lista de entidades <code>Cliente</code>, criada a
     *      partir do nome fornecido.
     *  
     *  @throws DaoException
     *      Se houverem erros durante a execução estes serão lançados
     *      como uma Exception deste tipo.
     */
    public List searchByName(String name, Concessionaria concessionaria) throws DaoException;
    
    /** Pesquisa um cliente utilizando como critério, chassi e concessionária.
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
     *  @throws DaoException
     *      Se houverem erros durante a execução estes serão lançados
     *      como uma Exception deste tipo.
     */
    public Cliente getByChassi(String chassi, Concessionaria concessionaria) throws DaoException;
    
    
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
    public List listApproachedByCpfCnpj(String cpfCnpjPart) throws DaoException;
    
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
    public List listApproachedByCpfCnpjByConc(String cpfCnpjPart, Concessionaria concessionaria) throws DaoException;
    
    /** Obtém uma listagem de clientes que possuem cupom ou garantia em uma concessionária
     * 
     * @param concessionaria
     * 	<code>Conccessionaria</code> Entidade de Concessionária 
     * 
     * @return List
     * 	<code>List</code>  Uma lista de clientes
     * 
     * @throws DaoException
     */
    public List listByConcessionaria( Concessionaria concessionaria ) throws DaoException;
    
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
    public List listByChassiByConc(String chassi, Concessionaria concessionaria) throws DaoException;
    
    
    /** Valida um CEP
     * 
     * 
     * @param cep
     * 	<code>Long</code> CEP informado
     * 
     * @return boolean
     * 	<code>true</code> se for válido.
     * 
     * @throws BusinessException
     */
    public boolean isValidCep( Long cep ) throws DaoException ;
}