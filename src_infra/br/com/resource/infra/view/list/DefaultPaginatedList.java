/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......DefaultPaginatedList.java
 *   .: Cria��o.....03 de maio, 11:53
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descri��o...Objeto de lista paginada padr�o.
 */
package br.com.resource.infra.view.list;

import java.util.List;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.properties.SortOrderEnum;

/** Objeto b�sico para uma listagem no formato DisplayTag.
 * 
 *  @author Thiago Uriel M. Garcia
 */
public class DefaultPaginatedList implements PaginatedList {

    /** Constante. Tamanho padr�o das listagens. */
    public static final int OBJECTS_PER_PAGE = 20;
    
    /** Tamanho completo da lista. */
    private int fullListSize;
    
    /** Quantidade de objetos por p�gina. Padr�o. */
    private int objectsPerPage;
    
    /** N�mero da p�gina atual. */
    private int pageNumber;
    
    /** Identificador da busca. */
    private String searchId;
    
    /** Crit�rio de ordena��o. */
    private String sortCriterion;

    /** Crit�rio de dire��o de ordena��o. */
    private SortOrderEnum sortDirection;
    
    /** Lista ou parte da lista a ser exibida. */
    private List list;

    /** M�todo getter para "fullListSize".
     *  @return
     *      <code>int</code>. O valor atual de fullListSize.
     */
    public int getFullListSize() {
        return this.fullListSize;
    }

    /** M�todo getter para "list".
     *  @return
     *      <code>List</code>. O valor atual de list.
     */
    public List getList() {
        return this.list;
    }

    /** M�todo getter para "objectsPerPage".
     *  @return
     *      <code>int</code>. O valor atual de objectsPerPage.
     */
    public int getObjectsPerPage() {
        return this.objectsPerPage;
    }

    /** M�todo getter para "pageNumber".
     *  @return
     *      <code>int</code>. O valor atual de pageNumber.
     */
    public int getPageNumber() {
        return this.pageNumber;
    }

    /** M�todo getter para "searchId".
     *  @return
     *      <code>String</code>. O valor atual de searchId.
     */
    public String getSearchId() {
        return this.searchId;
    }

    /** M�todo getter para "sortCriterion".
     *  @return
     *      <code>String</code>. O valor atual de sortCriterion.
     */
    public String getSortCriterion() {
        return this.sortCriterion;
    }

    /** M�todo getter para "sortDirection".
     *  @return
     *      <code>SortOrderEnum</code>. O valor atual de sortDirection.
     */
    public SortOrderEnum getSortDirection() {
        return this.sortDirection;
    }

    /** M�todo setter para "fullListSize".
     *  @param entityId
     *      <code>int</code> a ser designado para fullListSize.
     */
    public void setFullListSize(int fullListSize) {
        this.fullListSize = fullListSize;
    }

    /** M�todo setter para "list".
     *  @param entityId
     *      <code>List</code> a ser designado para list.
     */
    public void setList(List list) {
        this.list = list;
    }

    /** M�todo setter para "objectsPerPage".
     *  @param entityId
     *      <code>int</code> a ser designado para objectsPerPage.
     */
    public void setObjectsPerPage(int objectsPerPage) {
        this.objectsPerPage = objectsPerPage;
    }

    /** M�todo setter para "pageNumber".
     *  @param entityId
     *      <code>int</code> a ser designado para pageNumber.
     */
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    /** M�todo setter para "searchId".
     *  @param entityId
     *      <code>String</code> a ser designado para searchId.
     */
    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    /** M�todo setter para "sortCriterion".
     *  @param entityId
     *      <code>String</code> a ser designado para sortCriterion.
     */
    public void setSortCriterion(String sortCriterion) {
        this.sortCriterion = sortCriterion;
    }

    /** M�todo setter para "sortDirection".
     *  @param entityId
     *      <code>SortOrderEnum</code> a ser designado para sortDirection.
     */
    public void setSortDirection(SortOrderEnum sortDirection) {
        this.sortDirection = sortDirection;
    }
    
}