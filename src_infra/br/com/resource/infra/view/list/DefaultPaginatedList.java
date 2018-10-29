/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......DefaultPaginatedList.java
 *   .: Criação.....03 de maio, 11:53
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Objeto de lista paginada padrão.
 */
package br.com.resource.infra.view.list;

import java.util.List;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.properties.SortOrderEnum;

/** Objeto básico para uma listagem no formato DisplayTag.
 * 
 *  @author Thiago Uriel M. Garcia
 */
public class DefaultPaginatedList implements PaginatedList {

    /** Constante. Tamanho padrão das listagens. */
    public static final int OBJECTS_PER_PAGE = 20;
    
    /** Tamanho completo da lista. */
    private int fullListSize;
    
    /** Quantidade de objetos por página. Padrão. */
    private int objectsPerPage;
    
    /** Número da página atual. */
    private int pageNumber;
    
    /** Identificador da busca. */
    private String searchId;
    
    /** Critério de ordenação. */
    private String sortCriterion;

    /** Critério de direção de ordenação. */
    private SortOrderEnum sortDirection;
    
    /** Lista ou parte da lista a ser exibida. */
    private List list;

    /** Método getter para "fullListSize".
     *  @return
     *      <code>int</code>. O valor atual de fullListSize.
     */
    public int getFullListSize() {
        return this.fullListSize;
    }

    /** Método getter para "list".
     *  @return
     *      <code>List</code>. O valor atual de list.
     */
    public List getList() {
        return this.list;
    }

    /** Método getter para "objectsPerPage".
     *  @return
     *      <code>int</code>. O valor atual de objectsPerPage.
     */
    public int getObjectsPerPage() {
        return this.objectsPerPage;
    }

    /** Método getter para "pageNumber".
     *  @return
     *      <code>int</code>. O valor atual de pageNumber.
     */
    public int getPageNumber() {
        return this.pageNumber;
    }

    /** Método getter para "searchId".
     *  @return
     *      <code>String</code>. O valor atual de searchId.
     */
    public String getSearchId() {
        return this.searchId;
    }

    /** Método getter para "sortCriterion".
     *  @return
     *      <code>String</code>. O valor atual de sortCriterion.
     */
    public String getSortCriterion() {
        return this.sortCriterion;
    }

    /** Método getter para "sortDirection".
     *  @return
     *      <code>SortOrderEnum</code>. O valor atual de sortDirection.
     */
    public SortOrderEnum getSortDirection() {
        return this.sortDirection;
    }

    /** Método setter para "fullListSize".
     *  @param entityId
     *      <code>int</code> a ser designado para fullListSize.
     */
    public void setFullListSize(int fullListSize) {
        this.fullListSize = fullListSize;
    }

    /** Método setter para "list".
     *  @param entityId
     *      <code>List</code> a ser designado para list.
     */
    public void setList(List list) {
        this.list = list;
    }

    /** Método setter para "objectsPerPage".
     *  @param entityId
     *      <code>int</code> a ser designado para objectsPerPage.
     */
    public void setObjectsPerPage(int objectsPerPage) {
        this.objectsPerPage = objectsPerPage;
    }

    /** Método setter para "pageNumber".
     *  @param entityId
     *      <code>int</code> a ser designado para pageNumber.
     */
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    /** Método setter para "searchId".
     *  @param entityId
     *      <code>String</code> a ser designado para searchId.
     */
    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    /** Método setter para "sortCriterion".
     *  @param entityId
     *      <code>String</code> a ser designado para sortCriterion.
     */
    public void setSortCriterion(String sortCriterion) {
        this.sortCriterion = sortCriterion;
    }

    /** Método setter para "sortDirection".
     *  @param entityId
     *      <code>SortOrderEnum</code> a ser designado para sortDirection.
     */
    public void setSortDirection(SortOrderEnum sortDirection) {
        this.sortDirection = sortDirection;
    }
    
}