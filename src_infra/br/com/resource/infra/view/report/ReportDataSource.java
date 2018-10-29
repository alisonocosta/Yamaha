/* Resource Inform�tica
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......ReportDataSource.java
 *   .: Cria��o.....01 agosto, 12:41
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descri��o...Datasource para os relat�rios.
 */
package br.com.resource.infra.view.report;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/** Datasource padr�o para relat�rios <i>Jasper</i>.
 * 
 *  @author Thiago Uriel M. Garcia
 */
public class ReportDataSource implements JRDataSource {

    //----[ Constantes e atributos da classe ]--------------------------------------
    
    /** Lista de objetos contidos neste dataSource. */
    private List objectCollection = new ArrayList();

    /** Lista de dados de subreports, se houverem. */
    private Map subreportFields = null;
    
    /** Ponteiro que marca em qual posi��o da cole��o. */ 
    private int counter = -1; 

    //----[ Construtores ]----------------------------------------------------------
    
    /** Construtor padr�o
     *  <p>
     *  Cria uma inst�ncia da classe atual.
     */
    public ReportDataSource() {
        super();
    }    

    //----[ M�todos da classe ]-----------------------------------------------------
    
    /** Move o ponteiro para o pr�ximo item.
     * 
     *  @return 
     *      Indica se existe ou n�o uma pr�xima posi��o na collection.
     *      
     *  @throws JRException
     *      Se houver algum problema, ser� lan�ado como uma exception
     *      da API jasperReports.
     *      
     *  @see net.sf.jasperreports.engine.JRDataSource#next()
     */
    public boolean next() throws JRException {

        // Se o contador, j� incrementado for maior ou igual ao
        // tamanho da lista, estamos na �ltima posi��o, portanto
        // n�o temos pr�ximo registro.
        //
        // Caso contr�rio, indicamos que existe um pr�ximo registro.
        if ( ++counter >= objectCollection.size() )
            return false;

        else 
            return true;

    }

    /** Obt�m o valor de um campo do objeto-alvo do relat�rio. 
     * 
     *  @param jrField   
     *      Nome do campo a ser invocado.
     *      
     *  @return 
     *      Object. Resultado do campo invocado.
     *      
     *  @throws JRException
     *      Se houver algum problema, ser� lan�ado como uma exception
     *      da API jasperReports.
     *      
     *  @see net.sf.jasperreports.engine.JRDataSource#getFieldValue(net.sf.jasperreports.engine.JRField)
     */
    public Object getFieldValue(JRField jrField) throws JRException {

        try {

            // Utilizamos Reflection para obter o campo interno do relat�rio.
            //
            // Criamos uma inst�ncia chamada "fieldGetter" contendo o nome do campo
            // solicitado pela API de relat�rios. Esta inst�ncia ser� utilizada para
            // construir o nome do getter dentro das classes de resultado.
            String fieldGetter = jrField.getName();
            
            ReportDataSource ds = new ReportDataSource();
            
        	// Verificamos se o campo solicitado n�o diz respeito ao conte�do 
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
            
            // O campo solicitado n�o � um campo de subreport. Vamos procurar o
            // campo nas entidades internas normalmente, via Reflection.
            //
            // Alteramos o valor da vari�vel "fieldGetter" para deix�-lo no padr�o
            // de getter para um bean. Para isso, inserimos o prefixo "get", em seguida
            // a primeira letra do nome do campo em mai�scula e em seguida, o nome
            // restante do campo.
            // Ex.:
            //   Nome do campo original = "username"
            //   Nome getter resultante = "getUsername" 
            fieldGetter = "get" + fieldGetter.substring(0,1).toUpperCase() + fieldGetter.substring(1);
            
            // Agora obtemos o objeto atual da cole��o de registros, de acordo
            // com a posi��o indicada pelo ponteiro "counter".
            Object currentObject = this.objectCollection.get( this.counter );
            
            // Convertemos o objeto em sua pr�pria classe utilizando o m�todo
            // "getClass". Isso resulta na classe original.
            Class c = currentObject.getClass();
            
            // Agora buscamos pelo m�todo "getter" criado anteriormente. Passamos
            // null no segundo par�metro de "getDeclaredMethod" pois getters n�o
            // possuem valores de entrada que teriam seus TIPOS declarados por um
            // array neste par�metro.
            Method m = c.getDeclaredMethod( fieldGetter, null );
            
            // Finalmente obtemos o resultado, invocando o m�todo obtido acima,
            // apontado para o objeto original. Os valores de entrada do m�todo
            // seriam enviados no segundo atributo, mas getters n�o deveriam
            // possuir valores de entrada, portanto, enviamos null.
            
            Object value = m.invoke( currentObject, null );
            
            // Enfim, retornamos o valor obtido.
            return value;

        } catch (Exception exp) {
            throw new JRException( exp );

        }

    }

    //----[ M�todos Getter ]--------------------------------------------------------    
    
    /** M�todo getter para "objectCollection".
     *  @return
     *      <code>List</code>. O valor atual de objectCollection.
     */
    public List getObjectCollection() {
        return this.objectCollection;
    }

	/** M�todo getter para a propriedade "subreportFields".
	 *
	 *  @return O valor atual de subreportFields.
	 */
	public Map getSubreportFields() {
		return this.subreportFields;
	}    
    
    //----[ M�todos Setter ]--------------------------------------------------------    
    
    /** M�todo setter para "objectCollection".
     *  @param objectCollection
     *      <code>objectCollection</code> a ser designado para objectCollection.
     */
    public void setObjectCollection(List objectCollection) {
        this.objectCollection = objectCollection;
    }

	/** M�todo setter para a propriedade "subreportFields".
	 *
	 *  @param Novo valor para subreportFields.
	 */
	public void setSubreportFields(Map subreportFields) {
		this.subreportFields = subreportFields;
	}    

    
}