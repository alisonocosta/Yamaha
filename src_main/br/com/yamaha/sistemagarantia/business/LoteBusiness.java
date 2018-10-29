/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......LoteBusiness.java
 *   .: Criação.....24 de abril, 13:01
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Objeto de negócios para a entidade "Lote".
 */
package br.com.yamaha.sistemagarantia.business;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.BusinessRuleException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.dao.LoteDao;
import br.com.yamaha.sistemagarantia.model.Concessionaria;
import br.com.yamaha.sistemagarantia.model.Cupom;
import br.com.yamaha.sistemagarantia.model.GarantiaHeader;
import br.com.yamaha.sistemagarantia.model.Lote;
import br.com.yamaha.sistemagarantia.model.Programa;
import br.com.yamaha.sistemagarantia.model.StatusGarantia;
import br.com.yamaha.sistemagarantia.model.StatusLote;
import br.com.yamaha.sistemagarantia.model.TipoLote;
import br.com.yamaha.sistemagarantia.view.LoteCompactVO;

/** Classe de negócios relacionada à entidade <b>Lote</b>.
 * 
 *  @author Thiago Uriel M. Garcia
 */
public class LoteBusiness extends BusinessObject {

	/** Objeto LoteDAO para utilizado para este objeto de negócios. 
     *  
     */
    private LoteDao loteDao;
    
    /** Objeto cupomBO para utilizado para este objeto de negócios. 
     *  
     */
    private CupomBusiness cupomBO;
    
    /** Objeto garantiaHeaderBO para utilizado para este objeto de negócios. 
     *  
     */
    private GarantiaHeaderBusiness garantiaHeaderBO;
    
    /** Recupera um lote pelo id.
     *
     *  @param entityId
     *      Referência da entidade na camada de persistência.
     *  @return
     *      Um objeto <code>Lote</code>.
     *      
     * @throws BusinessException
     *      Se houverem erros de execução ou nas camadas 
     *      abaixo dos serviços, serão encapsulados neste
     *      tipo de <code>Exception</code>.
     */
    public Lote get(Serializable entityId) throws BusinessException {

        try {

            return (Lote)loteDao.get(Lote.class, entityId);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }
    
    /** Lista de Garantias de um lote para lançamento de NF.
     * 
     *  @param entityId
     *  	<code>Serializable</code> Id do Lote.
     *  
     *  @return
     *      <code>List</code> Uma lista de Garantias.
     *  
     *  @throws DaoException
     *      Se houverem erros durante a execução estes serão lançados
     *      como uma Exception deste tipo.
     */
 	public List listGaranatiasToNF(Serializable entityId) throws BusinessException {

        try {

            return loteDao.listGaranatiasToNF(entityId);
            
        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }
    
    /** Recupera um lote pelo id.
    *
    *  @param entityId
    *      Referência da entidade na camada de persistência.
    *      
    *  @param concessionaria
    *      Concessionária do usuário.
    *      
    *  @return
    *      Um objeto <code>Lote</code>.
    *      
    * @throws BusinessException
    *      Se houverem erros de execução ou nas camadas 
    *      abaixo dos serviços, serão encapsulados neste
    *      tipo de <code>Exception</code>.
    */
   public Lote getById(Serializable entityId, Concessionaria concessionaria) throws BusinessException {

       try {

           return (Lote)loteDao.getById(entityId, concessionaria);
           
       } catch (DaoException dExp) {

           throw new BusinessException(dExp);

       }

   }
   
   /** Recupera um lote pelo id.
   *
   *  @param entityId
   *      Referência da entidade na camada de persistência.
   *      
   *  @param concessionaria
   *      Concessionária do usuário.
   *      
   *  @return
   *      Um objeto <code>Lote</code>.
   *      
   * @throws BusinessException
   *      Se houverem erros de execução ou nas camadas 
   *      abaixo dos serviços, serão encapsulados neste
   *      tipo de <code>Exception</code>.
   */
  public LoteCompactVO getByIdList(Serializable entityId, Concessionaria concessionaria) throws BusinessException {

      try {

          return (LoteCompactVO)loteDao.getByIdList(entityId, concessionaria);
          
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
     *  @param lote
     *      <code>Lote</code> representando a entidade a ser
     *      criada/atualizada na camada de persistência.
     *      
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma BusinessException.
     */    
    public void save(Lote lote) throws BusinessException {

        try {

        	loteDao.makePersistent(lote);

        } catch ( DaoException daoExp ) {

            throw new BusinessException( daoExp );

        }

    }    
    
    /** Salva ou atualiza o estado de um Lote dos cupons e garantias quando for liberado.
     *  
     *  <p>
     *  Se a entidade recebida não existir na camada de persistência
     *  da aplicação, será retornado false. Do contrário seus valores serão
     *  atualizados de acordo com as informações alteradas.
     *  
     *  @param lote
     *      <code>Lote</code> representando a entidade a ser
     *     atualizada na camada de persistência.     
     *  
     *  @psrsm boolean hasAprovAut
     *  	<code>true</code> Atualização automática
     *  
     *  @return boolean 
     *  	<code>true</code> Atualizado com sucesso
     *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma BusinessException.
     */    
    public boolean alterStateLiberar(Lote lote) throws BusinessException, BusinessRuleException {

       boolean isAlterState = true;
    	
       try {
    	   
    	   if ( !lote.isNew() ) {
    		
    		  if ( !StatusLote.STATUS_EM_DIGITACAO.equals(lote.getStatusLote().getEntityId()) && !StatusLote.STATUS_MANUTENCAO.equals(lote.getStatusLote().getEntityId()) ) 
    			   throw new BusinessRuleException("O Lote " + lote.getEntityId() + " não pode ser liberado!");
    		   
    		  String msgException = "";
 			  boolean isException = false;
    		   
    		  // System.out.println("Linha do lote:" + lote.getLinha().getEntityId());
    		   StatusLote statusLote = new StatusLote();
    		   
    		   // De acordo com o tipo do lote devemos alterar o status_mov_id dos cupons e garantias
    		   if ( lote.getTipoLote().getEntityId().equals(TipoLote.TIPO_REVISAO)) {
    			   
    			   List cupons = getCupomBO().listByLote(lote); 
    			   
    			   // O status padrão para um cupom quando o lote é liberado é "STATUS_ANALISE"
    			   //Long statusMovId = StatusGarantia.STATUS_ANALISE;
    			   Long statusMovId = StatusGarantia.STATUS_AGUARDANDO_ENVIO_DOCUMENTOS;
    			   StatusGarantia statusGar = StatusGarantia.getInstance(statusMovId);
    			   
    			   if ( cupons == null || cupons.isEmpty() )
    				   throw new BusinessRuleException("O Lote " + lote.getEntityId() + " não tem cupom lançado!");
    			       			   
    			   Iterator itC = cupons.iterator();
    			   
    			   // A liberação deve ser feita após o lançamento das notas,
    			   // Portanto temos que verificar se todos os itens estão devidamente com as notas lançadas
    			   while (itC.hasNext()) {

						Cupom cupom = (Cupom) itC.next();  

						//System.out.println("Verificando o status do Cupom:" + cupom.getEntityId() + " - STATUS:" + cupom.getStatusMovId());
												
						// Se o status do item estiver com status diferente de "NF DIGITADA", "PAGO", "PERIÓDICA" e "RECUSADA"
						if ( 	!cupom.getStatusMovId().equals(StatusGarantia.STATUS_NF_DIGITADA)
							 && !cupom.getStatusMovId().equals(StatusGarantia.STATUS_PAGO)
							 && !cupom.getStatusMovId().equals(StatusGarantia.STATUS_PERIODICA)
							 && !cupom.getStatusMovId().equals(StatusGarantia.STATUS_RECUSADO)
							 && !cupom.getStatusMovId().equals(StatusGarantia.STATUS_AGUARDANDO_ENVIO_DOCUMENTOS)
							 && !cupom.getStatusMovId().equals(StatusGarantia.STATUS_AGUARDANDO_ADIANTAMENTO_DOCUMENTOS)
							 && !cupom.getStatusMovId().equals(StatusGarantia.STATUS_ADIANTAMENTO_PAGO_AGUARD_DOCUMENTOS)
						) {							
							
							//System.out.println("O Cupom " + cupom.getEntityId() + " não possui Nota Fiscal Lançada!");
							
							msgException += " - " + cupom.getCupomCode();
							
							isException = true;
							
						} 
					}
					
					// Se um cupom estiver sem nota o sistema deve lançar uma Exception
					if ( isException )
						throw new BusinessRuleException("Falta Nota Fiscal para o(s) Cupom(s): " + msgException);
				   
    			   ///////////////////////////////////////
					
					itC = cupons.iterator();
					while ( itC.hasNext() ) {
					   
					   Cupom cupom = (Cupom) itC.next();  
					   // Se o status do cupom "NF_DIGITADA
					   if ( StatusGarantia.STATUS_NF_DIGITADA.equals(cupom.getStatusMovId()) ) {
						   cupom.setStatusGarantia(statusGar);
						   cupom.setStatusMovId(statusMovId);    				   
						   getCupomBO().save(cupom);
					   }
					}
    			   
    			   // Após isso verifico se o lote só tem revisão como "PERIÓDICA"
    			   itC = cupons.iterator();
    			   boolean hasNoControl = false;
    			   
    			   while ( itC.hasNext() ) {
    				   
    				   Cupom cupom = (Cupom) itC.next();
    				  
    				   if ( !StatusGarantia.STATUS_PERIODICA.equals(cupom.getStatusMovId()) ) {
    					   hasNoControl = true;    					   
    				   }
    					   
    				}
    			   
    			   if ( hasNoControl )
	    			   // Mudamos o lote para status "EM ANÁLISE"
	    			   //statusLote.setEntityId(StatusLote.STATUS_ANALISE);
    				   statusLote.setEntityId(StatusLote.STATUS_AGUARDANDO_RECEB_DOC);
    			   else
    				   statusLote.setEntityId(StatusLote.STATUS_PERIODICA);
    				   
    				   
    			   lote.setStatusLote(statusLote);
    			   
    		   } else if ( lote.getTipoLote().getEntityId().equals(TipoLote.TIPO_GARANTIA)) {
    			   
    			  List garantias = getGarantiaHeaderBO().listByLote(lote);
    			  StatusGarantia statusGarantia = new StatusGarantia();
    			  
    			  // O status padrão para uma SG quando o lote é liberado é "Em Análise"
    			  statusGarantia.setEntityId(StatusGarantia.STATUS_ANALISE);
    			  Iterator itG = garantias.iterator();
    			  //System.out.println("Quantidade de garantias no lote:" + garantias.size());
    			  
    			  if ( garantias == null || garantias.isEmpty() )
   				   	throw new BusinessRuleException("O Lote " + lote.getEntityId() + " não tem Garantia lançada!");
    			 
    			  /******************************************************** Comentado em 01/01/2013  - Edson     			  
					// A liberação deve ser feita após o lançamento das notas,
					// Portanto temos que verificar se todos os itens estão devidamente com as notas lançadas
					while (itG.hasNext()) {

						GarantiaHeader garantiaHeader = (GarantiaHeader) itG.next();

						//System.out.println("Verificando o status da garantia:" + garantiaHeader.getEntityId() + " - STATUS:" + garantiaHeader.getStatusGarantia().getEntityId());
						
						
						// Se o status do item estiver com status diferente de "NF DIGITADA"  e diferente de "PAGO"
						if ( 	!garantiaHeader.getStatusGarantia().getEntityId().equals(StatusGarantia.STATUS_NF_DIGITADA)
							 && !garantiaHeader.getStatusGarantia().getEntityId().equals(StatusGarantia.STATUS_PAGO)
							 && !garantiaHeader.getStatusGarantia().getEntityId().equals(StatusGarantia.STATUS_PERIODICA)
							 && !garantiaHeader.getStatusGarantia().getEntityId().equals(StatusGarantia.STATUS_RECUSADO)
							 && !garantiaHeader.getStatusGarantia().getEntityId().equals(StatusGarantia.STATUS_AGUARDANDO_ENVIO_DOCUMENTOS)
							 && !garantiaHeader.getStatusGarantia().getEntityId().equals(StatusGarantia.STATUS_AGUARDANDO_ADIANTAMENTO_DOCUMENTOS)
							 && !garantiaHeader.getStatusGarantia().getEntityId().equals(StatusGarantia.STATUS_ADIANTAMENTO_PAGO_AGUARD_DOCUMENTOS)
						) {							
							
							//System.out.println("A Garantia " + garantiaHeader.getEntityId() + " não possui Nota Fiscal Lançada!");
							
							msgException += garantiaHeader.getEntityId()+ " - ";
							
							isException = true;
							
						} 
					}
					
					// Se uma garantia estiver sem nota o sistema deve lançar uma Exception
					if ( isException )
						throw new BusinessRuleException("Falta Nota Fiscal para SG(s): " + msgException);
					    			  
					itG = garantias.iterator();
					while( itG.hasNext() ) {
    				  
						//System.out.println("Alterando o status das garantias do lote:" + lote.getEntityId());
						GarantiaHeader garantiaHeader = (GarantiaHeader)itG.next();  
						
						// Só devemos alterar o status das garantias com status "NF DIGITADA"
						if ( garantiaHeader.getStatusGarantia().getEntityId().equals(StatusGarantia.STATUS_NF_DIGITADA) ) {
							
							statusGarantia.setEntityId(StatusGarantia.STATUS_ANALISE);
							
							garantiaHeader.setStatusGarantia(statusGarantia); 
	    				
							//System.out.println("Garantia: " + garantiaHeader.getEntityId() + " - Status:" + garantiaHeader.getStatusGarantia().getEntityId());
	    				
							getGarantiaHeaderBO().saveEntity(garantiaHeader);  
	    				
							//System.out.println("Status alterado com sucesso!");
							
						} 
    				  
					}
					
					 // Após isso verifico se o lote só tem garantia como "PERIÓDICA"
					itG = garantias.iterator();
					boolean hasNoControl = false;

					while (itG.hasNext()) {

						GarantiaHeader garantiaHeader = (GarantiaHeader)itG.next();  

						if (!StatusGarantia.STATUS_PERIODICA.equals(garantiaHeader.getStatusGarantia().getEntityId())) {
							hasNoControl = true;
						}

					}
					**********************************************************************************/
    			  
    			  	boolean hasNoControl = false;
    			  	itG = garantias.iterator();
					while( itG.hasNext() ) {
  				  
						System.out.println("Alterando o status das garantias do lote:" + lote.getEntityId());
						GarantiaHeader garantiaHeader = (GarantiaHeader)itG.next();  
						
						// Qunado status for períodico não alteramos o status
						if ( !garantiaHeader.getStatusGarantia().getEntityId().equals(StatusGarantia.STATUS_PERIODICA) ) {
							
							statusGarantia.setEntityId(StatusGarantia.STATUS_ANALISE);
							
							garantiaHeader.setStatusGarantia(statusGarantia); 
	    				
							//System.out.println("Garantia: " + garantiaHeader.getEntityId() + " - Status:" + garantiaHeader.getStatusGarantia().getEntityId());
	    				
							getGarantiaHeaderBO().saveEntity(garantiaHeader);  
	    				
							//System.out.println("Status alterado com sucesso!");
							
						} else
							hasNoControl = true;
  				  
					}    			  
    			  
					//System.out.println("Alterando o status do lote:" + lote.getEntityId());
	    			   
	    			if ( !hasNoControl )
	    				// Mudamos o lote para status "EM ANÁLISE"
	    				statusLote.setEntityId(StatusLote.STATUS_ANALISE);
	    			else
	    				statusLote.setEntityId(StatusLote.STATUS_PERIODICA);
	    			
					lote.setStatusLote(statusLote);
    			  
    		   }  
    		   
    		   lote.setDataLiberacao(new Date());
    		   
    		   loteDao.makePersistent(lote);
    		   
    	   } else
    		   isAlterState = false;
    	   
    	   return isAlterState;

        } catch ( DaoException daoExp ) {

            throw new BusinessException( daoExp );

        }
    }    
    
    /** Salva ou atualiza o estado de um Lote.
     *  
     *  <p>
     *  Se a entidade recebida não existir na camada de persistência
     *  da aplicação, será retornado false. Do contrário seus valores serão
     *  atualizados de acordo com as informações alteradas.
     *  
     *  @param lote
     *      <code>Lote</code> representando a entidade a ser
     *     atualizada na camada de persistência.
     *      
     *  @return boolean 
     *  	<code>true</code> Atualizado com sucesso
     *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma BusinessException.
     */    
    public boolean alterState(Lote lote) throws BusinessException {

       boolean isAlterState = true;
    	
       try {
    	   
    	   if ( !lote.isNew() ) {
    		   
    		   loteDao.makePersistent(lote);
    	   } else
    		   isAlterState = false;
    	   
    	   return isAlterState;

        } catch ( DaoException daoExp ) {

            throw new BusinessException( daoExp );

        }	   
    }

    /** Lista todos os lotes existentes no banco de dados.
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
     *      Um objeto <code>List</code> contendo todos os 
     *      lotes persistentes na aplicação.
     *      
     * @throws BusinessException
     *      Se houverem erros de execução ou nas camadas 
     *      abaixo dos serviços, serão encapsulados neste
     *      tipo de <code>Exception</code>.
     */    
    public List list() throws BusinessException {

        try {

            return loteDao.listAll(Lote.class);

        } catch ( DaoException daoExp ) {

            throw new BusinessException( daoExp );

        }

    }

    /** Lista os lotes existentes no banco de dados de acordo
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
     *      lotes persistentes na aplicação, de acordo com
     *      os critérios limitadores fornecidos.
     *      
     * @throws BusinessException
     *      Se houverem erros de execução ou nas camadas 
     *      abaixo dos serviços, serão encapsulados neste
     *      tipo de <code>Exception</code>.
     */
    public List list(int start, int limit, int orderType, String orderField) throws BusinessException {

        try {

            return loteDao.list(Lote.class, start, limit, orderType, orderField);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }    
    
    /** Lista os lotes existentes no banco de dados de acordo
     *  com a concessionária do usuário.
     *
     * @param usuarioId
     *      ID do usuário.
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
     *      lotes persistentes na aplicação, de acordo com
     *      os critérios limitadores fornecidos.
     *      
     * @throws BusinessException
     *      Se houverem erros de execução ou nas camadas 
     *      abaixo dos serviços, serão encapsulados neste
     *      tipo de <code>Exception</code>.
     */
    public List listByConcessionaria(Concessionaria concessionaria) throws BusinessException {
    	
    	try {
        	
        	return loteDao.listByParameter(Lote.class, "concessionaria", concessionaria, "entityId", "desc");

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    	
    }
    
    /** Lista os lotes existentes no banco de dados de acordo
     *  com a concessionária do usuário.
     *
     * @param usuarioId
     *      ID do usuário.
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
     *      lotes persistentes na aplicação, de acordo com
     *      os critérios limitadores fornecidos.
     *      
     * @throws BusinessException
     *      Se houverem erros de execução ou nas camadas 
     *      abaixo dos serviços, serão encapsulados neste
     *      tipo de <code>Exception</code>.
     */
    public List listByConcessionaria(Concessionaria concessionaria, int start, int limit, int orderType, String orderField) throws BusinessException {

        try {
        	
        	return loteDao.listByParameter(Lote.class, "concessionaria", concessionaria, start, limit, orderType, orderField);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }    
    
    /** Lista  os lotes de uma concessionária de acordo com o critério informado. 
     *  
     *  @param status
     *      Um <code>List</code>.
     *      
     *  @param concessionaria
     *  
     *  @return
     *      Um objeto <code>List</code> contendo os 
     *      lotes persistentes na aplicação, de acordo com
     *      os critérios limitadores fornecidos.
     *  
     * @throws BusinessException
     *      Se houverem erros de execução ou nas camadas 
     *      abaixo dos serviços, serão encapsulados neste
     *      tipo de <code>Exception</code>.  
     */
    public List listByStatus( List status, Concessionaria concessionaria ) throws BusinessException {
    	    	
    	try {

            return loteDao.listByStatus(status, concessionaria);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }
    	
    }
    
    /** Lista  os lotes de uma concessionária de acordo com o critério informado. 
     *  
     *  @param status
     *      Um <code>List</code>.
     *      
     *  @param concessionaria
     *  
     *  @return
     *      Um objeto <code>List</code> contendo os 
     *      lotes persistentes na aplicação, de acordo com
     *      os critérios limitadores fornecidos.
     *  
     * @throws BusinessException
     *      Se houverem erros de execução ou nas camadas 
     *      abaixo dos serviços, serão encapsulados neste
     *      tipo de <code>Exception</code>.  
     */
    public List listLotes( List status, Concessionaria concessionaria ) throws BusinessException {
    	    	
    	try {

            return loteDao.listlotes(status, concessionaria);

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
     *      Se houverem erros de execução ou nas camadas 
     *      abaixo dos serviços, serão encapsulados neste
     *      tipo de <code>Exception</code>.  
     */
    public int getTotals() throws BusinessException {

        try {

            return loteDao.getFullListSize(Lote.class);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }    
    
    /** Obtém o tamanho total de uma listagem de acordo com uma Concessionária. 
     *  
     *  @return
     *      Um <code>int</code> contendo o tamanho da listagem.
     *  
     * @throws BusinessException
     *      Se houverem erros de execução ou nas camadas 
     *      abaixo dos serviços, serão encapsulados neste
     *      tipo de <code>Exception</code>.  
     */
    public int getTotals(Concessionaria concessionaria) throws BusinessException {

        try {
        	
        	return loteDao.getListSizeByParameter(Lote.class, "concessionaria", concessionaria);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }
    
    /** Obtém uma entidade do Programa. 
     * 
     * @param nomePrograma
     *  <code>String</code> O nome do programa.
     *  
     *  @return quantidade de linhas
     *     <code>Integer</code>.
     *  
     * @throws BusinessException
     *      Se houverem erros de execução ou nas camadas 
     *      abaixo dos serviços, serão encapsulados neste
     *      tipo de <code>Exception</code>.  
     */
    public Integer getLinhasPagina(String nomePrograma) throws BusinessException {
    	
    	try {
    		
    		Programa programa = (Programa)this.getLoteDao().getByParameter(Programa.class, "codigoPrograma", nomePrograma);
    		
    		return programa.getLinhasPagina();
    	
    	} catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }
    	
    }
    
    /** Lista de Garantias da Concessionaria em Manutenção por mais de 2 dias
     * 
     *  @param concessionariaId
     *  	<code>Serializable</code> Id da Concessionaria.
     *  
     *  @return
     *      <code>boolean</code> true quando exisitir.
     *  
     *  @throws BusinessException
     *      Se houverem erros durante a execução estes serão lançados
     *      como uma Exception deste tipo.
     */
 	public boolean listGaranatiasInManut(Serializable concessionariaId) throws BusinessException {
 		try {
 			return loteDao.listGaranatiasInManut(concessionariaId);
 		} catch (DaoException dExp) {
            throw new BusinessException(dExp);
        }
 		
 	}

	/** Método getter para a propriedade loteDao
	 * 
	 *  @return LoteDao
	 *
	 */
	public LoteDao getLoteDao() {
		return loteDao;
	}

	/** Método setter para a propriedade loteDao
	 *
	 * @param loteDao 
	 *           <code>LoteDao</code> a ser designado para loteDao.
	 * 
	 */
	public void setLoteDao(LoteDao loteDao) {
		this.loteDao = loteDao;
	}

	/** Método getter para a propriedade cupomBO
	 * 
	 *  @return CupomBusiness
	 *
	 */
	public CupomBusiness getCupomBO() {
		return this.cupomBO;
	}

	/** Método setter para a propriedade CupomBO
	 *
	 * @param cupomBO 
	 *           <code>CupomBusiness</code> a ser designado para cupomBO.
	 * 
	 */
	public void setCupomBO(CupomBusiness cupomBO) {
		this.cupomBO = cupomBO;
	}

	/** Método getter para a propriedade garantiaHeaderBO
	 * 
	 *  @return GarantiaHeaderBusiness
	 *
	 */
	public GarantiaHeaderBusiness getGarantiaHeaderBO() {
		return garantiaHeaderBO;
	}

	/** Método setter para a propriedade garantiaHeaderBO
	 *
	 * @param garantiaHeaderBO 
	 *           <code>GarantiaHeaderBusiness</code> a ser designado para garantiaHeaderBO.
	 * 
	 */
	public void setGarantiaHeaderBO(GarantiaHeaderBusiness garantiaHeaderBO) {
		this.garantiaHeaderBO = garantiaHeaderBO;
	}
	
	
}