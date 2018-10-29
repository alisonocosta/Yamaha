/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......Dao.java
 *   .: Cria��o.....03 de abril, 16:31
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descri��o...Draft de um objeto de acesso a dados.
 */
package br.com.resource.infra.dao;

import java.io.Serializable;
import java.util.List;

import br.com.resource.infra.exception.DaoException;
import br.com.resource.infra.model.EntityObject;

/** Esta interface fornece os m�todos a serem implementados por
 *  um objeto de acesso a dados (DAO - [J2ee]).
 *  
 *  @author Thiago Uriel M. Garcia
 */
public interface Dao {

    //----[ M�todos Fundamentais ]----------------------------------------------
    
    /** Obt�m uma entidade do sistema a partir de uma classe
     *  base e um identificador.
     *  
     *  @param baseClass
     *      <code>Class</code>. Tipo de classe a ser obtida pelo m�todo.
     *  
     *  @param entityId
     *      <code>Serializable<code>. Identificador pelo qual o objeto
     *      dever� ser pesquisado na camada de persist�ncia da aplica��o.
     *  
     *  @return
     *      Um <code>EntityObject</code> populado com os dados da 
     *      entidade espec�fica, solicitada.
     *      
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
     */    
    public EntityObject get(Class baseClass, Serializable entityId) throws DaoException;
    
    /** Obt�m uma entidade do sistema a partir de uma classe base e um par�metro,
     *  utilizandao o framework <b>Hibernate</b>.
     *  
     *  @param baseClass
     *      <code>Class</code>. Tipo de classe a ser obtida pelo m�todo.
     *  
     *  @param parameterName
     *      <code>Serializable<code>. Nome do par�metro(Coluna) pelo qual o objeto
     *      dever� ser pesquisado na camada de persist�ncia da aplica��o.
     *      
     *  @param parameterValue
     *  	<code>Object<code>. Valor do par�metro
     *  
     *  @return
     *      Um <code>EntityObject</code> populado com os dados da 
     *      entidade espec�fica, solicitada, ou caso nenhuma entidade
     *      seja encontrada, retornar� <code>null</code>.
     *      
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
     */     
    public EntityObject getByParameter(Class baseClass, String parameterName, Serializable parameterValue) throws DaoException;

    /** Lista todos os objetos do tipo fornecido pelo atributo baseClass.
     * 
     *  @param baseClass
     *      Tipo de entidade a ser lida da camada de persist�ncia
     *      e listada para a aplica��o.
     * 
     *  @return
     *      Uma lista de objetos recebida da camada de persist�ncia,
     *      a partir da classe-base fornecida.
     *      
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
     */
    public List listAll(Class baseClass) throws DaoException;    

    /** Lista todos os objetos do tipo fornecido pelo atributo baseClass.
     * 
     *  @param baseClass
     *      Tipo de entidade a ser lida da camada de persist�ncia
     *      e listada para a aplica��o.
     * @param orderType
     *      Tipo de ordena��o, sendo 1=ascendente, 2=descendente.
     * @param orderColumn
     *      Nome da coluna a ser utilizada para organizar a listagem. 
     * 
     *  @return
     *      Uma lista de objetos recebida da camada de persist�ncia,
     *      a partir da classe-base fornecida.
     *      
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
     */
    public List orderedList(Class baseClass, int orderType, String orderColumn) throws DaoException;    
    
    /** Salva ou atualiza o estado de uma entidade.
     *  <p>
     *  Se a entidade recebida n�o existir na camada de persist�ncia
     *  da aplica��o, ser� criada. Do contr�rio seus valores ser�o
     *  atualizados de acordo com as informa��es alteradas.
     *  
     *  @param entity
     *      <code>EntityObject</code> representando a entidade a ser
     *      criada/atualizada na camada de persist�ncia.
     *      
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
     */
    public void makePersistent(EntityObject entity) throws DaoException;    
    
    /** Remove um objeto da camada de persist�ncia da aplica��o.
     * 
     *  @param entity
     *      <code>EntityObject</code> representando a entidade a ser
     *      removida na camada de persist�ncia.
     *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
     */
    public void makeTransient(EntityObject entity) throws DaoException;
    
    
    //----[ M�todos Complementares ]--------------------------------------------
    
    /** Obt�m o tamanho completo de uma listagem de todos os registros.
     * 
     *  @param baseClass
     *      Tipo de entidade a ser lida da camada de persist�ncia
     *      e listada para a aplica��o.
     * 
     *  @return 
     *      Um inteiro determinando quantos registros ser�o retornados.
     *      
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
     */
    public int getFullListSize(Class baseClass) throws DaoException;
    
    /** Remove um objeto da camada de persist�ncia da aplica��o.
     * 
     *  @param baseClass
     *      <code>Class</code>. Tipo de classe a ser removida pelo m�todo.
     *  
     *  @param entityId
     *      <code>Serializable<code>. Identificador pelo qual o objeto
     *      dever� ser referenciado na camada de persist�ncia da aplica��o.
     *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
     */    
    public void remove(EntityObject entity) throws DaoException;
    
    /** Obt�m o tamanho  de uma listagem dos registros de acordo com uma par�metro informado.
     * 
     *  @param baseClass
     *      Tipo de entidade a ser lida da camada de persist�ncia
     *      e listada para a aplica��o.
     *      
     *  @param paramName
     *      Nome do par�metro para filtro.
     *   
     *  @param objectParam
     *      Objeto que servir� como par�metro de filtro.
     * 
     *  @return 
     *      Um inteiro determinando quantos registros ser�o retornados.
     *      
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
     */
    public int getListSizeByParameter(Class baseClass, String paramName, Object objectParameter) throws DaoException;
    
    /** Obt�m uma listagem de registros limitada de acordo com os dados
     *  delimitadores fornecidos e organizada.
     * 
     *  @param baseClass
     *      Tipo de entidade a ser lida da camada de persist�ncia
     *      e listada para a aplica��o.
     * @param start
     *      Registro pelo qual a listagem dever� ser iniciada.
     * @param limit
     *      Limite de registros obtidos a partir do registro de in�cio.
     * @param orderType
     *      Tipo de ordena��o, sendo 1=ascendente, 2=descendente.
     * @param orderColumn
     *      Nome da coluna a ser utilizada para organizar a listagem.
     * 
     * @return
     *      Uma listagem de elementos, organizada e restrita aos 
     *      delimitadores fornecidos.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
     */
    public List list(Class baseClass, int start, int limit, int orderType, String orderColumn) throws DaoException;
    
    /** Obt�m uma listagem de registros limitada de acordo com os dados
     *  delimitadores fornecidos e organizada.
     * 
     *  @param baseClass
     *      Tipo de entidade a ser lida da camada de persist�ncia
     *      e listada para a aplica��o.
     * @param paramName
     *      Nome do par�metro do filtro.
     * @param objectParam
     *      Objeto que servir� como par�metro de filtro.
     * @param start
     *      Registro pelo qual a listagem dever� ser iniciada.
     * @param limit
     *      Limite de registros obtidos a partir do registro de in�cio.
     * @param orderType
     *      Tipo de ordena��o, sendo 1=ascendente, 2=descendente.
     * @param orderColumn
     *      Nome da coluna a ser utilizada para organizar a listagem.
     * 
     * @return
     *      Uma listagem de elementos, organizada e restrita aos 
     *      delimitadores fornecidos.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
     */
    public List listByParameter(Class baseClass, String paramName, Object objectParameter, int start, int limit, int orderType, String orderColumn) throws DaoException;
    
    /** Obt�m uma listagem de registros limitada de acordo o par�metro passado.
     * 
     *  @param baseClass
     *      Tipo de entidade a ser lida da camada de persist�ncia
     *      e listada para a aplica��o.
     * @param paramName
     *      Nome do par�metro do filtro.
     * @param objectParam
     *      Objeto que servir� como par�metro de filtro.
     * 
     * @return
     *      Uma listagem de elementos, organizada e restrita aos 
     *      delimitadores fornecidos.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
     */
    public List listByParameter(Class baseClass, String parameterName, Object objectParameter) throws DaoException;
    
    /** Obt�m uma listagem de registros limitada de acordo o par�metro passado.
     * 
     *  @param baseClass
     *      Tipo de entidade a ser lida da camada de persist�ncia
     *      e listada para a aplica��o.
     * @param paramName
     *      Nome do par�metro do filtro.
     * @param objectParam
     *      Objeto que servir� como par�metro de filtro.
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
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
     */
    public List listByParameter(Class baseClass, String parameterName, Object objectParameter, String orderField , String order) throws DaoException;
    
    
    /** Obt�m uma listagem de registros aproxiamda limitada de acordo o par�metro passado.
     * 
     *  @param baseClass
     *      Tipo de entidade a ser lida da camada de persist�ncia
     *      e listada para a aplica��o.
     * @param parameterName
     *      Nome do par�metro do filtro.
     *      
     * @param objectParameter
     *      Objeto que servir� como par�metro de filtro.
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
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
     */
    public List listApproachedByParameter(Class baseClass, String parameterName, Object objectParameter, String fieldName, String valuePart) throws DaoException;
}