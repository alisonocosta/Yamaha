/* Resource Informática
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......ReportDataSource.java
 *   .: Criação.....01 agosto, 12:41
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Datasource para os relatórios.
 */
package br.com.resource.infra.view.report;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/** Datasource padrão para relatórios <i>Jasper</i>.
 * 
 *  @author Thiago Uriel M. Garcia
 */
public class ReportDataSource implements JRDataSource {

    //----[ Constantes e atributos da classe ]--------------------------------------
    
    /** Lista de objetos contidos neste dataSource. */
    private List objectCollection = new ArrayList();

    /** Lista de dados de subreports, se houverem. */
    private Map subreportFields = null;
    
    /** Ponteiro que marca em qual posição da coleção. */ 
    private int counter = -1; 

    //----[ Construtores ]----------------------------------------------------------
    
    /** Construtor padrão
     *  <p>
     *  Cria uma instância da classe atual.
     */
    public ReportDataSource() {
        super();
    }    

    //----[ Métodos da classe ]-----------------------------------------------------
    
    /** Move o ponteiro para o próximo item.
     * 
     *  @return 
     *      Indica se existe ou não uma próxima posição na collection.
     *      
     *  @throws JRException
     *      Se houver algum problema, será lançado como uma exception
     *      da API jasperReports.
     *      
     *  @see net.sf.jasperreports.engine.JRDataSource#next()
     */
    public boolean next() throws JRException {

        // Se o contador, já incrementado for maior ou igual ao
        // tamanho da lista, estamos na última posição, portanto
        // não temos próximo registro.
        //
        // Caso contrário, indicamos que existe um próximo registro.
        if ( ++counter >= objectCollection.size() )
            return false;

        else 
            return true;

    }

    /** Obtém o valor de um campo do objeto-alvo do relatório. 
     * 
     *  @param jrField   
     *      Nome do campo a ser invocado.
     *      
     *  @return 
     *      Object. Resultado do campo invocado.
     *      
     *  @throws JRException
     *      Se houver algum problema, será lançado como uma exception
     *      da API jasperReports.
     *      
     *  @see net.sf.jasperreports.engine.JRDataSource#getFieldValue(net.sf.jasperreports.engine.JRField)
     */
    public Object getFieldValue(JRField jrField) throws JRException {

        try {

            // Utilizamos Reflection para obter o campo interno do relatório.
            //
            // Criamos uma instância chamada "fieldGetter" contendo o nome do campo
            // solicitado pela API de relatórios. Esta instância será utilizada para
            // construir o nome do getter dentro das classes de resultado.
            String fieldGetter = jrField.getName();
            
            ReportDataSource ds = new ReportDataSource();
            
        	// Verificamos se o campo solicitado não diz respeito ao conteúdo 
            // de um subreport.
            if ( this.getSubreportFields() != null ) {

            	// Possuimos um mapa. Verificamos se possui o campo.
                if ( this.getSubreportFields().containsKey( fieldGetter ) ) {
                
                // Possui. Neste caso, retornamos o valor convertido para
                // um objeto do tipo List.                
                ds.setObjectCollection( (List) this.getSubreportFields().get(fieldGetter) );
                return ds;

                }
            	
            }
            
            // O campo solicitado não é um campo de subreport. Vamos procurar o
            // campo nas entidades internas normalmente, via Reflection.
            //
            // Alteramos o valor da variável "fieldGetter" para deixá-lo no padrão
            // de getter para um bean. Para isso, inserimos o prefixo "get", em seguida
            // a primeira letra do nome do campo em maiúscula e em seguida, o nome
            // restante do campo.
            // Ex.:
            //   Nome do campo original = "username"
            //   Nome getter resultante = "getUsername" 
            fieldGetter = "get" + fieldGetter.substring(0,1).toUpperCase() + fieldGetter.substring(1);
            
            // Agora obtemos o objeto atual da coleção de registros, de acordo
            // com a posição indicada pelo ponteiro "counter".
            Object currentObject = this.objectCollection.get( this.counter );
            
            // Convertemos o objeto em sua própria classe utilizando o método
            // "getClass". Isso resulta na classe original.
            Class c = currentObject.getClass();
            
            // Agora buscamos pelo método "getter" criado anteriormente. Passamos
            // null no segundo parâmetro de "getDeclaredMethod" pois getters não
            // possuem valores de entrada que teriam seus TIPOS declarados por um
            // array neste parâmetro.
            Method m = c.getDeclaredMethod( fieldGetter, null );
            
            // Finalmente obtemos o resultado, invocando o método obtido acima,
            // apontado para o objeto original. Os valores de entrada do método
            // seriam enviados no segundo atributo, mas getters não deveriam
            // possuir valores de entrada, portanto, enviamos null.
            
            Object value = m.invoke( currentObject, null );
            
            // Enfim, retornamos o valor obtido.
            return value;

        } catch (Exception exp) {
            throw new JRException( exp );

        }

    }

    //----[ Métodos Getter ]--------------------------------------------------------    
    
    /** Método getter para "objectCollection".
     *  @return
     *      <code>List</code>. O valor atual de objectCollection.
     */
    public List getObjectCollection() {
        return this.objectCollection;
    }

	/** Método getter para a propriedade "subreportFields".
	 *
	 *  @return O valor atual de subreportFields.
	 */
	public Map getSubreportFields() {
		return this.subreportFields;
	}    
    
    //----[ Métodos Setter ]--------------------------------------------------------    
    
    /** Método setter para "objectCollection".
     *  @param objectCollection
     *      <code>objectCollection</code> a ser designado para objectCollection.
     */
    public void setObjectCollection(List objectCollection) {
        this.objectCollection = objectCollection;
    }

	/** Método setter para a propriedade "subreportFields".
	 *
	 *  @param Novo valor para subreportFields.
	 */
	public void setSubreportFields(Map subreportFields) {
		this.subreportFields = subreportFields;
	}    

    
}