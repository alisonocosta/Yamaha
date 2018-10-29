/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......Dao.java
 *   .: Criação.....03 de abril, 16:31
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Draft de um objeto de acesso a dados.
 */
package br.com.resource.infra.dao;

import java.io.Serializable;
import java.util.List;

import br.com.resource.infra.exception.DaoException;
import br.com.resource.infra.model.EntityObject;

/** Esta interface fornece os métodos a serem implementados por
 *  um objeto de acesso a dados (DAO - [J2ee]).
 *  
 *  @author Thiago Uriel M. Garcia
 */
public interface Dao {

    //----[ Métodos Fundamentais ]----------------------------------------------
    
    /** Obtém uma entidade do sistema a partir de uma classe
     *  base e um identificador.
     *  
     *  @param baseClass
     *      <code>Class</code>. Tipo de classe a ser obtida pelo método.
     *  
     *  @param entityId
     *      <code>Serializable<code>. Identificador pelo qual o objeto
     *      deverá ser pesquisado na camada de persistência da aplicação.
     *  
     *  @return
     *      Um <code>EntityObject</code> populado com os dados da 
     *      entidade específica, solicitada.
     *      
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */    
    public EntityObject get(Class baseClass, Serializable entityId) throws DaoException;
    
    /** Obtém uma entidade do sistema a partir de uma classe base e um parâmetro,
     *  utilizandao o framework <b>Hibernate</b>.
     *  
     *  @param baseClass
     *      <code>Class</code>. Tipo de classe a ser obtida pelo método.
     *  
     *  @param parameterName
     *      <code>Serializable<code>. Nome do parâmetro(Coluna) pelo qual o objeto
     *      deverá ser pesquisado na camada de persistência da aplicação.
     *      
     *  @param parameterValue
     *  	<code>Object<code>. Valor do parâmetro
     *  
     *  @return
     *      Um <code>EntityObject</code> populado com os dados da 
     *      entidade específica, solicitada, ou caso nenhuma entidade
     *      seja encontrada, retornará <code>null</code>.
     *      
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */     
    public EntityObject getByParameter(Class baseClass, String parameterName, Serializable parameterValue) throws DaoException;

    /** Lista todos os objetos do tipo fornecido pelo atributo baseClass.
     * 
     *  @param baseClass
     *      Tipo de entidade a ser lida da camada de persistência
     *      e listada para a aplicação.
     * 
     *  @return
     *      Uma lista de objetos recebida da camada de persistência,
     *      a partir da classe-base fornecida.
     *      
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */
    public List listAll(Class baseClass) throws DaoException;    

    /** Lista todos os objetos do tipo fornecido pelo atributo baseClass.
     * 
     *  @param baseClass
     *      Tipo de entidade a ser lida da camada de persistência
     *      e listada para a aplicação.
     * @param orderType
     *      Tipo de ordenação, sendo 1=ascendente, 2=descendente.
     * @param orderColumn
     *      Nome da coluna a ser utilizada para organizar a listagem. 
     * 
     *  @return
     *      Uma lista de objetos recebida da camada de persistência,
     *      a partir da classe-base fornecida.
     *      
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */
    public List orderedList(Class baseClass, int orderType, String orderColumn) throws DaoException;    
    
    /** Salva ou atualiza o estado de uma entidade.
     *  <p>
     *  Se a entidade recebida não existir na camada de persistência
     *  da aplicação, será criada. Do contrário seus valores serão
     *  atualizados de acordo com as informações alteradas.
     *  
     *  @param entity
     *      <code>EntityObject</code> representando a entidade a ser
     *      criada/atualizada na camada de persistência.
     *      
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */
    public void makePersistent(EntityObject entity) throws DaoException;    
    
    /** Remove um objeto da camada de persistência da aplicação.
     * 
     *  @param entity
     *      <code>EntityObject</code> representando a entidade a ser
     *      removida na camada de persistência.
     *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */
    public void makeTransient(EntityObject entity) throws DaoException;
    
    
    //----[ Métodos Complementares ]--------------------------------------------
    
    /** Obtém o tamanho completo de uma listagem de todos os registros.
     * 
     *  @param baseClass
     *      Tipo de entidade a ser lida da camada de persistência
     *      e listada para a aplicação.
     * 
     *  @return 
     *      Um inteiro determinando quantos registros serão retornados.
     *      
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */
    public int getFullListSize(Class baseClass) throws DaoException;
    
    /** Remove um objeto da camada de persistência da aplicação.
     * 
     *  @param baseClass
     *      <code>Class</code>. Tipo de classe a ser removida pelo método.
     *  
     *  @param entityId
     *      <code>Serializable<code>. Identificador pelo qual o objeto
     *      deverá ser referenciado na camada de persistência da aplicação.
     *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */    
    public void remove(EntityObject entity) throws DaoException;
    
    /** Obtém o tamanho  de uma listagem dos registros de acordo com uma parâmetro informado.
     * 
     *  @param baseClass
     *      Tipo de entidade a ser lida da camada de persistência
     *      e listada para a aplicação.
     *      
     *  @param paramName
     *      Nome do parâmetro para filtro.
     *   
     *  @param objectParam
     *      Objeto que servirá como parâmetro de filtro.
     * 
     *  @return 
     *      Um inteiro determinando quantos registros serão retornados.
     *      
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */
    public int getListSizeByParameter(Class baseClass, String paramName, Object objectParameter) throws DaoException;
    
    /** Obtém uma listagem de registros limitada de acordo com os dados
     *  delimitadores fornecidos e organizada.
     * 
     *  @param baseClass
     *      Tipo de entidade a ser lida da camada de persistência
     *      e listada para a aplicação.
     * @param start
     *      Registro pelo qual a listagem deverá ser iniciada.
     * @param limit
     *      Limite de registros obtidos a partir do registro de início.
     * @param orderType
     *      Tipo de ordenação, sendo 1=ascendente, 2=descendente.
     * @param orderColumn
     *      Nome da coluna a ser utilizada para organizar a listagem.
     * 
     * @return
     *      Uma listagem de elementos, organizada e restrita aos 
     *      delimitadores fornecidos.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */
    public List list(Class baseClass, int start, int limit, int orderType, String orderColumn) throws DaoException;
    
    /** Obtém uma listagem de registros limitada de acordo com os dados
     *  delimitadores fornecidos e organizada.
     * 
     *  @param baseClass
     *      Tipo de entidade a ser lida da camada de persistência
     *      e listada para a aplicação.
     * @param paramName
     *      Nome do parâmetro do filtro.
     * @param objectParam
     *      Objeto que servirá como parâmetro de filtro.
     * @param start
     *      Registro pelo qual a listagem deverá ser iniciada.
     * @param limit
     *      Limite de registros obtidos a partir do registro de início.
     * @param orderType
     *      Tipo de ordenação, sendo 1=ascendente, 2=descendente.
     * @param orderColumn
     *      Nome da coluna a ser utilizada para organizar a listagem.
     * 
     * @return
     *      Uma listagem de elementos, organizada e restrita aos 
     *      delimitadores fornecidos.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */
    public List listByParameter(Class baseClass, String paramName, Object objectParameter, int start, int limit, int orderType, String orderColumn) throws DaoException;
    
    /** Obtém uma listagem de registros limitada de acordo o parâmetro passado.
     * 
     *  @param baseClass
     *      Tipo de entidade a ser lida da camada de persistência
     *      e listada para a aplicação.
     * @param paramName
     *      Nome do parâmetro do filtro.
     * @param objectParam
     *      Objeto que servirá como parâmetro de filtro.
     * 
     * @return
     *      Uma listagem de elementos, organizada e restrita aos 
     *      delimitadores fornecidos.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */
    public List listByParameter(Class baseClass, String parameterName, Object objectParameter) throws DaoException;
    
    /** Obtém uma listagem de registros limitada de acordo o parâmetro passado.
     * 
     *  @param baseClass
     *      Tipo de entidade a ser lida da camada de persistência
     *      e listada para a aplicação.
     * @param paramName
     *      Nome do parâmetro do filtro.
     * @param objectParam
     *      Objeto que servirá como parâmetro de filtro.
     * @param orderField
     *      Nome do campo para ordenar.
     * @param order
     *      ordem  ASC/DESC.
     * 
     * @return
     *      Uma listagem de elementos, organizada e restrita aos 
     *      delimitadores fornecidos.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */
    public List listByParameter(Class baseClass, String parameterName, Object objectParameter, String orderField , String order) throws DaoException;
    
    
    /** Obtém uma listagem de registros aproxiamda limitada de acordo o parâmetro passado.
     * 
     *  @param baseClass
     *      Tipo de entidade a ser lida da camada de persistência
     *      e listada para a aplicação.
     * @param parameterName
     *      Nome do parâmetro do filtro.
     *      
     * @param objectParameter
     *      Objeto que servirá como parâmetro de filtro.
     *      
     * @param fieldName
     * 		Nome do campo para busca aproximada
     * 
     * @param valuePart
     * 		Valor para busca aproximada
     * 
     * @return
     *      Uma listagem de elementos, organizada e restrita aos 
     *      delimitadores fornecidos.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */
    public List listApproachedByParameter(Class baseClass, String parameterName, Object objectParameter, String fieldName, String valuePart) throws DaoException;
}