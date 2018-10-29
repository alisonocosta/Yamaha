/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......CupomBusiness.java
 *   .: Cria��o.....10 de maio, 10:54
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Objeto de neg�cios para a entidade "Cupom".
 */
package br.com.yamaha.sistemagarantia.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.BusinessRuleException;
import br.com.resource.infra.exception.DaoException;
import br.com.resource.infra.utils.DateUtils;
import br.com.resource.infra.utils.NumberUtils;
import br.com.yamaha.sistemagarantia.controller.helper.ControllerHelper;
import br.com.yamaha.sistemagarantia.dao.CupomDao;
import br.com.yamaha.sistemagarantia.dao.ModeloDao;
import br.com.yamaha.sistemagarantia.model.AlertCupom;
import br.com.yamaha.sistemagarantia.model.AlertGarantia;
import br.com.yamaha.sistemagarantia.model.Concessionaria;
import br.com.yamaha.sistemagarantia.model.Cupom;
import br.com.yamaha.sistemagarantia.model.Faturamento;
import br.com.yamaha.sistemagarantia.model.Item;
import br.com.yamaha.sistemagarantia.model.Linha;
import br.com.yamaha.sistemagarantia.model.Lote;
import br.com.yamaha.sistemagarantia.model.Modelo;
import br.com.yamaha.sistemagarantia.model.ParametroSistema;
import br.com.yamaha.sistemagarantia.model.Revisao;
import br.com.yamaha.sistemagarantia.model.StatusGarantia;
import br.com.yamaha.sistemagarantia.model.StatusLote;
import br.com.yamaha.sistemagarantia.model.Usuario;
import br.com.yamaha.sistemagarantia.model.ValorServico;
import br.com.yamaha.sistemagarantia.model.id.AlertCupomId;
 
/** Classe de neg�cios relacionada � entidade <b>Cupom</b>.
 * 
 *  @author Edson Luiz Sumensari
 */
public class CupomBusiness extends BusinessObject {

	
	/** Objeto CupomDao para utilizado para este objeto de neg�cios. 
     *  
     */
    private CupomDao cupomDao;
    
    /** Objeto RevisaoBusiness para utilizado para este objeto de neg�cios. 
     *  
     */
    private RevisaoBusiness revisaoBO;
    
    /** Objeto FeriadoDataBusiness para utilizado para este objeto de neg�cios. 
     *  
     */
    private FeriadoDataBusiness feriadoDataBO;
    
    /** Objeto loteBusiness para utilizado para este objeto de neg�cios. 
     *  
     */
    private LoteBusiness loteBO;
    
    /** Objeto itemBusiness para utilizado para este objeto de neg�cios. 
	 *  
	 */
	private ItemBusiness itemBO;
    
    /** Recupera uma Cupom pelo id.
    *
    *  @param entityId
    *      Refer�ncia da entidade na camada de persist�ncia.
    *  @return
    *      Um objeto <code>Cupom</code>.
    *      
    * @throws BusinessException
    *      Se houverem erros de execu��o ou nas camadas 
    *      abaixo dos servi�os, ser�o encapsulados neste
    *      tipo de <code>Exception</code>.
    */
   public Cupom get(Serializable entityId) throws BusinessException {

       try {

           return (Cupom)cupomDao.get(Cupom.class, entityId);

       } catch (DaoException dExp) {

           throw new BusinessException(dExp);

       }

   }
   
   /** Retorna uma entidade de faturamento de acordo com um chassi e linha do produto
    * 
    * @param chassi
    * 	<code>String</code> Chassi informado
    * 
    * @param linha
    * 	<code>Linha</code> Linha do produto
    * 
    * @return boolean
    * 	<code>Faturamento</code> uma entidade de Faturamento ou null se n�o encontrar
    * 
    * @throws DaoException
    */
   public Faturamento getFaturamentoByLinha( String chassi,Linha linha ) throws BusinessException {
	   
	   try {
	       	
    	   return (Faturamento) cupomDao.getFaturamentoByLinha(chassi, linha);

       } catch (DaoException dExp) {

           throw new BusinessException(dExp);

       } 
       
   }   
   
   /** Retorna uma entidade de cupom de acordo com um chassi e revisao
    * 
    * @param chassi
    * 	<code>String</code> Chassi informado
    * 
    * @param linha
    * 	<code>Linha</code> Linha do produto
    * 
    * @return boolean
    * 	<code>Faturamento</code> uma entidade de Faturamento ou null se n�o encontrar
    * 
    * @throws DaoException
    */
   public Cupom getCupomByChassiAndRevisao( String chassi,Revisao revisao ) throws BusinessException {
	   
	   try {
	       	
    	   return (Cupom) cupomDao.getCupomByChassiAndRevisao(chassi, revisao);

       } catch (DaoException dExp) {

           throw new BusinessException(dExp);

       } 
       
   }   
   
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
   public Cupom getByParameter(String parameterName, Serializable parameterValue)throws BusinessException {
	   
	   try {
	       	
    	   return (Cupom) cupomDao.getByParameter(Cupom.class, parameterName, parameterValue);

       } catch (DaoException dExp) {

           throw new BusinessException(dExp);

       } 
   }
   
   /** Lista os cupons existentes no banco de dados de acordo
    *  com lote informado.
    *
    * @param lote
    *      lote para filtro.
    * 
    *  @return
    *      Um objeto <code>List</code> contendo os 
    *      lotes persistentes na aplica��o, de acordo com
    *      os crit�rios limitadores fornecidos.
    *      
    * @throws BusinessException
    *      Se houverem erros de execu��o ou nas camadas 
    *      abaixo dos servi�os, ser�o encapsulados neste
    *      tipo de <code>Exception</code>.
    */
   public List listByLote(Lote lote) throws BusinessException {

       try {
       	
    	   return cupomDao.listByParameter(Cupom.class, "lote", lote, "dataRevisao", "DESC");

       } catch (DaoException dExp) {

           throw new BusinessException(dExp);

       }

   }    
   
   
   /** Lista os cupons existentes no banco de dados de acordo
    *  com lote informado.
    *
    * @param loteId
    *      id do lote para filtro.
    * 
    *  @return
    *      Um objeto <code>List</code> contendo os 
    *      lotes persistentes na aplica��o, de acordo com
    *      os crit�rios limitadores fornecidos.
    *      
    * @throws BusinessException
    *      Se houverem erros de execu��o ou nas camadas 
    *      abaixo dos servi�os, ser�o encapsulados neste
    *      tipo de <code>Exception</code>.
    */
   public List listCuponsByLote(Serializable loteId) throws BusinessException {
	   
	   try {
	       	
    	   return cupomDao.listCuponsByLote(loteId);

       } catch (DaoException dExp) {

           throw new BusinessException(dExp);

       }

   }    
   
   
   /** Obt�m uma lista de chassi de uma concession�ria e lote.
    * 
    * @param chassiPart
    *  <code>String</code> Chassi ou parte.
    *
    *  @param concessionaria
    *  	<code>Concessionaria</code> entidade concession�ria
    *  
    *  @param loteId
    *  	<code>Long</code> Id do lote.
    *    
    *  @throws DaoException
    *      Se houverem erros durante a execu��o estes ser�o lan�ados
    *      como uma Exception deste tipo.
    */
   public List listApproachChassiByLote(String chassiPart, Concessionaria concessionaria, Long loteId) throws BusinessException {

       try {
       	
    	   return cupomDao.listApproachChassiByLote(chassiPart, concessionaria, loteId);

       } catch (DaoException dExp) {

           throw new BusinessException(dExp);

       }

   }
   
   /** Retorna uma lista de enitdades de faturamento de acordo com um chassi e linha do produto
    * 
    * @param chassiPart
    * 	<code>String</code> Chassi informado ou parte do valor do chassi
    * 
    * @param concessionaria
    * 	<code>Concessionaria</code> Entidade de Concessionaria 
    * 
    * @param linhaId
    * 	<code>Long</code> entityId da Linha do produto
    * 
    * @return 
    * 	<code>List</code> uma lista de entidades de Faturamento
    * 
    * @throws DaoException
    */
   public List listApproachFaturamentoByLinha( String chassiPart, Concessionaria concessionaria, Long linhaId ) throws BusinessException {

       try {
       	
    	   return cupomDao.listApproachFaturamentoByLinha(chassiPart, concessionaria, linhaId);

       } catch (DaoException dExp) {

           throw new BusinessException(dExp);

       }

   }

   /** Retorna uma lista de enitdades de faturamento de acordo com um chassi e linha do produto
    * 
    * @param chassiPart
    * 	<code>String</code> Chassi informado ou parte do valor do chassi
    * 
    * @param concessionaria
    * 	<code>Concessionaria</code> Entidade de Concessionaria 
    * 
    * @param linhaId
    * 	<code>Long</code> entityId da Linha do produto
    * 
    * @return 
    * 	<code>List</code> uma lista de entidades de Faturamento
    * 
    * @throws DaoException
    */
   public List listFaturamentoByLinhaAndChassi( String chassiPart, Long linhaId ) throws BusinessException {

       try {
       	
    	   return cupomDao.listFaturamentoByLinhaAndChassi(chassiPart, linhaId);

       } catch (DaoException dExp) {

           throw new BusinessException(dExp);

       }

   }   
   
   /** Lista os cupons existentes no banco de dados de acordo
    *  com o chassi.
    *
    * @param chassi
    *      chassi para filtro.
    * 
    *  @return
    *      Um objeto <code>List</code> contendo os 
    *      lotes persistentes na aplica��o, de acordo com
    *      os crit�rios limitadores fornecidos.
    *      
    * @throws BusinessException
    *      Se houverem erros de execu��o ou nas camadas 
    *      abaixo dos servi�os, ser�o encapsulados neste
    *      tipo de <code>Exception</code>.
    */
   public List listByChassi(String chassi) throws BusinessException {

       try {
       	
    	   return cupomDao.listByParameter(Cupom.class, "chassi", chassi, "entityId", "DESC");

       } catch (DaoException dExp) {

           throw new BusinessException(dExp);

       }

   }    
   
   /** Lista os cupons existentes no banco de dados de acordo
    *  com o chassi e em ordem crescente.
    *
    * @param chassi
    *      chassi para filtro.
    * 
    *  @return
    *      Um objeto <code>List</code> contendo os 
    *      lotes persistentes na aplica��o, de acordo com
    *      os crit�rios limitadores fornecidos.
    *      
    * @throws BusinessException
    *      Se houverem erros de execu��o ou nas camadas 
    *      abaixo dos servi�os, ser�o encapsulados neste
    *      tipo de <code>Exception</code>.
    */
   public List listByChassiAsc(String chassi) throws BusinessException {

       try {
       	
    	   return cupomDao.listByParameter(Cupom.class, "chassi", chassi, "entityId", "ASC");

       } catch (DaoException dExp) {

           throw new BusinessException(dExp);

       }

   }    
   
   
   /** Obt�m o tamanho total de acordo com um Lote. 
    *  
    *  @return
    *      Um <code>int</code> contendo o tamanho da listagem.
    *  
    * @throws BusinessException
    *      Se houverem erros de execu��o ou nas camadas 
    *      abaixo dos servi�os, ser�o encapsulados neste
    *      tipo de <code>Exception</code>.  
    */
   public int getTotals(Lote lote) throws BusinessException {

       try {
       	
       	return cupomDao.getListSizeByParameter(Cupom.class, "lote", lote);

       } catch (DaoException dExp) {

           throw new BusinessException(dExp);

       }

   }   
   
   /** Salva ou atualiza o estado de uma entidade.
    *  <p>
    *  Se a entidade recebida n�o existir na camada de persist�ncia
    *  da aplica��o, ser� criada. Do contr�rio seus valores ser�o
    *  atualizados de acordo com as informa��es alteradas.
    *  
    *  @param cupom
    *      <code>Cupom</code> representando a entidade a ser
    *      criada/atualizada na camada de persist�ncia.
    *      
    *  @throws DaoException
    *      Se houverem problemas nas camadas inferiores, estes ser�o 
    *      convertidos para uma BusinessException.
    */    
   public void save(Cupom cupom) throws BusinessException {

       try {

    	   cupomDao.makePersistent(cupom);

       } catch ( DaoException daoExp ) {

           throw new BusinessException( daoExp );

       }

   }    
   
   /** Salva ou atualiza o estado de uma entidade.
    *  <p>
    *  Se a entidade recebida n�o existir na camada de persist�ncia
    *  da aplica��o, ser� criada. Do contr�rio seus valores ser�o
    *  atualizados de acordo com as informa��es alteradas.
    *  
    *  @param cupom
    *      <code>Cupom</code> representando a entidade a ser
    *      criada/atualizada na camada de persist�ncia.
    *      
    *  @param alertas
	*      <code>List</code> Lista de alertas enviados das valida��es.	
    *      
    *  @throws DaoException
    *      Se houverem problemas nas camadas inferiores, estes ser�o 
    *      convertidos para uma BusinessException.
    */    
   public void save(Cupom cupom, List alertas) throws BusinessException {

       try {

    	   cupomDao.makePersistent(cupom);
    	   
    	   if ( !alertas.isEmpty() && alertas != null ) {

				Iterator it = alertas.iterator();
				while ( it.hasNext() ) {

					AlertCupom alertCupom = (AlertCupom) it.next();
					if ( alertCupom.getAlertCupomKey().indexOf("warning") != -1 ) {
						
						AlertCupomId compositeEntityId = alertCupom.getCompositeEntityId();						
						compositeEntityId.setCupomId(cupom.getEntityId());						
						alertCupom.setCompositeEntityId(compositeEntityId);						
						getCupomDao().makePersistent(alertCupom);					
						
					}
				}
			}

       } catch ( DaoException daoExp ) {

           throw new BusinessException( daoExp );

       }

   }    
   
   /** Remove uma entidade do banco de dados.
    * 
    *  @param entityId
    *  @throws BusinessException
    */
   public void remove(Serializable entityId) throws BusinessException {
	   
	   try {
	   
		   Cupom cupom = get( entityId );
	   
		   if ( cupom.getLote().getStatusLote().getEntityId() == new Long(1) ) {
		   
			   // Quando o ID do statusLote for 1, � permitida
			   // a remo��o f�sica dos dados.
			   this.getCupomDao().makeTransient( cupom );
	   
		   } else {
	   
			   // Quando o ID do statusLote n�o for 1, a exclus�o
			   // de dados dever� ser l�gica: Setar enddate para data
			   // atual.
			   cupom.setEndDate( new Date() );
			   this.save( cupom );
			   
		   }
		   
	   } catch (DaoException dExp) {
		   throw new BusinessException( dExp );
	   }
	   
   }   
   
   /** Remo��o L�gica de um Cupom do banco de dados.
	 * 
	 *  @param garatnia
	 *  @throws BusinessException
	 */
	public void remove(Cupom cupom) throws BusinessException {

		try {		

			this.getCupomDao().remove(cupom);


		} catch (DaoException dExp) {
			throw new BusinessException( dExp );
		}

	}  
   
   /** M�todo que realiza a valida��o de intervalo de tempo ou quilomentragem para revis�o
    *  Antes de salvar o cupom
    * 
    * @param cupom
    * 		<code>Cupom</code> Entidade populado com o valores do formul�rio
    * 
    * @param linha
    * 		<code>Linha</code> Entidade com a linha de produtos da Concession�ria
    * 
    * @param isConcessionaria
    * 		<code>boolean</code> True se o chassi foi faturado pela Concession�ria
    * 							 False para chassi faturarado por outra Concession�ria
    * 
    * @return alertCupom
    * 		<code>AlertCupom</code> Mensagem retornada pela valida��o Warning/Error/Success    
    * 
    * @throws BusinessException
    * 
    * @throws BusinessRuleException
    * 		Se ocorrer algum erro recuperando algum objeto lan�amos esta exception
    *       com uma chave de properties da mensagem de erro.
    */
   public AlertCupom validateKmCondiz(Cupom cupom, Linha linha, boolean isConcessionaria) throws BusinessException,BusinessRuleException {
	   
	   AlertCupom alertCupom = null;
	   
	   try {
		   
		   /*	 Valida��o KM Condiz
		    *    Comparamos pelo id da Linha 
		    *    Id 1 ou  - Linha de Motociclieta
		    *    Id 2,3 ou 4 - Linha de N�utica, Produtos de For�a e Quadriciclos 
		    */
		   if ( linha.getEntityId().equals(new Long(1)) ){
			   
			   //System.out.println("Modelo: " + cupom.getRevisao().getModelo());
			   
			   // Linha motoclicleta e quadriciclos
			   double toleranciaMotoPerc = 0;			   
			   
			   Faturamento 	faturamento = this.getFaturamentoByLinha(cupom.getChassi(), linha);
			   Modelo 		modelo 		= null; 
				   
			   if ( faturamento.getModelo() != null )
				   modelo = (Modelo)this.cupomDao.get(Modelo.class, new Long(faturamento.getModelo()));
			   else {
				   
				   alertCupom = new AlertCupom();
				   alertCupom.setAlertCupomText("N�o foi localizado o modelo no faturamento do produto!");
				   //System.out.println("CupomBussines - linha 558 - N�o foi localizado o modelo para o chassi:" + cupom.getChassi() + " !");
				   alertCupom.setCompositeEntityId(new Long(1), (Long)cupom.getEntityId()); // ist_edson 01/07/2013
				   alertCupom.setAlertCupomSeveridade(AlertCupom.SEVERIDADE_ERROR);	
				   alertCupom.setAlertCupomKey("cupom.msg.error.modeloNotFound");
				   
				   return alertCupom;
				   
			   }
			   
			   //System.out.println("Modelo: " + modelo.getModelo() + " - Veloc�metro:" + modelo.getVelocimetro());
			   
			   
			   long tempoKM 			  = 0;
			   
			   try {
				   
				  if ( Modelo.VELOCIMETRO_NOT.equals(modelo.getVelocimetro()) ) {
					   
					   toleranciaMotoPerc = cupom.getRevisao().getToleranciaMotoMes().doubleValue();
					   
				  } else 
					   toleranciaMotoPerc  = cupom.getRevisao().getToleranciaMotoPerc().doubleValue();
				   
				  tempoKM  = cupom.getRevisao().getTempoKm().longValue();			  
			   
			   } catch (NullPointerException npExp) {
					   
				   alertCupom = new AlertCupom();
				   alertCupom.setAlertCupomText("Os dados da revis�o est�o incompletos!");
				   alertCupom.setCompositeEntityId(new Long(1), (Long)cupom.getEntityId());//ist_edson  01/07/2013
				   alertCupom.setAlertCupomKey("cupom.msg.error.revisionData");
				   alertCupom.setAlertCupomSeveridade(AlertCupom.SEVERIDADE_ERROR);	
				   
				   return alertCupom;
			   }
			   
			   double toleranciaInicial = NumberUtils.round((( 1 - toleranciaMotoPerc / 100 ) *  tempoKM),0);
			   double toleranciaFinal   = NumberUtils.round((( 1 + toleranciaMotoPerc / 100 ) *  tempoKM),0);
			   /*
			   System.out.println("Quilometragem: " + cupom.getQuilometragem().longValue());
			   System.out.println("Toler�ncia Percentual: " + toleranciaMotoPerc);
			   System.out.println("Toler�ncia Inicial: " + toleranciaInicial);
			   System.out.println("Toler�ncia Final: "   + toleranciaFinal);
			   */
			   // Verificamos o intervalo da quilometragem 
			   if ( !( cupom.getQuilometragem().longValue() >= toleranciaInicial && 
					   cupom.getQuilometragem().longValue() <= toleranciaFinal ) ) {
				   
				   
				   alertCupom = new AlertCupom();
				   alertCupom.setAlertCupomText("Quilometragem inv�lida para a revis�o!");					   	
				   
				  // Verificamos se o chassi foi faturado para a Concession�ria do Usu�rio
				  // if ( !isConcessionaria ) {
					   
					   alertCupom.setCompositeEntityId(new Long(1), (Long)cupom.getEntityId());//ist_edson  01/07/2013
					   alertCupom.setAlertCupomKey("cupom.msg.error.kilometrage");
					   alertCupom.setAlertCupomSeveridade(AlertCupom.SEVERIDADE_ERROR);				   			   
					   
				   /*} else {
					   
					   alertCupom.setCompositeEntityId(new Long(2), (Long)cupom.getCupomCode());
					   alertCupom.setAlertCupomKey("cupom.msg.warning.kilometrage");
					   alertCupom.setAlertCupomSeveridade(AlertCupom.SEVERIDADE_WARNING);
					   
				   }*/			   
			   } 
			   
		   } else if ( linha.getEntityId().equals(Linha.TIPO_NAUTICA) || linha.getEntityId().equals(new Long(3)) || linha.getEntityId().equals(new Long(4)) ) {
			   /* Altera��o em 23/10/2008 
			   // Recuperamos a revis�o anterior			   
			   long numeroRevisaoAnt = ( cupom.getRevisao().getNumeroRevisao().longValue() - 1 );
			   */
			   
			   // Recuperamos a revis�o de Entega
			   Revisao revisaoEntrega = this.revisaoBO.getByModel( ControllerHelper.getModeloByChassi(cupom.getChassi()), new Long(1) );
			   
			   if ( revisaoEntrega == null ){
				  
				   alertCupom = new AlertCupom();
				   alertCupom.setAlertCupomText("A Revis�o de Entrega n�o foi localizada!");
				   //System.out.println("CupomBussines - Revis�o solicitada n�o localizada para o chassi:" + cupom.getChassi() + " !");
				   alertCupom.setCompositeEntityId(new Long(1), (Long)cupom.getEntityId());//ist_edson  01/07/2013
				   alertCupom.setAlertCupomSeveridade(AlertCupom.SEVERIDADE_ERROR);	
				   alertCupom.setAlertCupomKey("cupom.msg.error.revisionNotFound");
				   
				   return alertCupom;
			   } 
			   /* Altera��o realizada em 23/10/2008 */			   
			   //Cupom cupomLast =  this.cupomDao.getCupomByChassiAndRevisao(cupom.getChassi(), revisao);
			   
			   Cupom cupomEntrega =  this.cupomDao.getCupomByChassiAndRevisao(cupom.getChassi(), revisaoEntrega);
			   
			   if ( cupomEntrega == null ) {
				  
				   alertCupom = new AlertCupom();
				   alertCupom.setAlertCupomText("Revis�o de entrega n�o foi localizada!");
				   //System.out.println("CupomBussines -  Revis�o de Entrega n�o localizada para o chassi:" + cupom.getChassi() + " !");
				   alertCupom.setCompositeEntityId(new Long(1), (Long)cupom.getEntityId());//ist_edson  01/07/2013
				   alertCupom.setAlertCupomSeveridade(AlertCupom.SEVERIDADE_ERROR);	
				   alertCupom.setAlertCupomKey("cupom.msg.error.previewRevision");
				   
				   return alertCupom;
			   } 
			   
			   //System.out.println("CupomBusiness - validateKmCondiz  602 - cupom anterior:" + cupomLast.getEntityId());
			   
			   // Calculamos o intervalo de dias entre a revis�o anterior e a solicitada
			   long innerDays = DateUtils.getDays(cupomEntrega.getDataEntrega(), cupom.getDataRevisao());			   
			   
			   //System.out.println("CupomBusiness - validateKmCondiz - innerDays:" + innerDays + " - Hora Uso:" + cupom.getHorasUso().longValue());
			   
			   // Valida��o de intervalo de tempo para linha n�utica e produtos de for�a			   
			   long   tempoDias 			 = cupom.getRevisao().getTempoDias() != null ? cupom.getRevisao().getTempoDias().longValue(): 0 ;
			   double toleranciaNauticaDias  = cupom.getRevisao().getToleranciaNauticaDias() != null ? cupom.getRevisao().getToleranciaNauticaDias().doubleValue() : 0 ;
			   long   tempoHoras			 = cupom.getRevisao().getTempoHoras() != null ? cupom.getRevisao().getTempoHoras().longValue() : 0 ;
			   double toleranciaNauticaHoras = cupom.getRevisao().getToleranciaNauticaHoras() != null ? cupom.getRevisao().getToleranciaNauticaHoras().doubleValue() : 0 ;		   
			   /*
			   double toleranciaNauticasDiasInicial = NumberUtils.round((( 1 - toleranciaNauticaDias / 100 ) *  tempoDias),0);
			   double toleranciaNauticasDiasFinal	= NumberUtils.round((( 1 + toleranciaNauticaDias / 100 ) *  tempoDias),0);
			   double toleranciaNauticaHorasInicial = NumberUtils.round((( 1 - toleranciaNauticaHoras / 100 ) *  tempoHoras),0);
			   double toleranciaNauticaHorasFinal	= NumberUtils.round((( 1 + toleranciaNauticaHoras / 100 ) *  tempoHoras),0);
			   */   
			   // Valores para as toler�ncias inteiras
			   double toleranciaNauticasDiasInicial = NumberUtils.round((tempoDias  - toleranciaNauticaDias),0);
			   double toleranciaNauticasDiasFinal	= NumberUtils.round((tempoDias  + toleranciaNauticaDias),0);
			   double toleranciaNauticaHorasInicial = NumberUtils.round((tempoHoras - toleranciaNauticaHoras),0);
			   double toleranciaNauticaHorasFinal	= NumberUtils.round((tempoHoras + toleranciaNauticaHoras),0);
			   
			   /*
			   System.out.println("CupomBusiness - validateKmCondiz - tempoDias: " 				  + tempoDias);
			   System.out.println("CupomBusiness - validateKmCondiz - toleranciaNauticaDias: "    + toleranciaNauticaDias); 
			   System.out.println("CupomBusiness - validateKmCondiz - tempoHoras: " 			  + tempoHoras); 
			   System.out.println("CupomBusiness - validateKmCondiz - toleranciaNauticaHoras:"    + toleranciaNauticaHoras );
			   
			   System.out.println("CupomBusiness - validateKmCondiz - toleranciaNauticasDiasInicial: " + toleranciaNauticasDiasInicial);
			   System.out.println("CupomBusiness - validateKmCondiz - toleranciaNauticasDiasFinal: "   + toleranciaNauticasDiasFinal); 
			   System.out.println("CupomBusiness - validateKmCondiz - toleranciaNauticaHorasInicial: " + toleranciaNauticaHorasInicial); 
			   System.out.println("CupomBusiness - validateKmCondiz - toleranciaNauticaHorasFinal:"    + toleranciaNauticaHorasFinal );
			   */
			   if ( innerDays < toleranciaNauticasDiasInicial ) {
				   
				   if ( cupom.getHorasUso().longValue() < toleranciaNauticaHorasInicial 
					   || cupom.getHorasUso().longValue() > toleranciaNauticaHorasFinal ) {
				   
					   alertCupom = new AlertCupom();
					   alertCupom.setAlertCupomText("Quantidade de Horas informada e inv�lida para a revis�o!");
					   //System.out.println("Quantidade de Horas informada e inv�lida para a revis�o!");
					   alertCupom.setCompositeEntityId(new Long(1), (Long)cupom.getEntityId());//ist_edson  01/07/2013
					   alertCupom.setAlertCupomSeveridade(AlertCupom.SEVERIDADE_ERROR);	
					   alertCupom.setAlertCupomKey("cupom.msg.error.hours");
					   
				   }
				   
			   } else if ( innerDays > toleranciaNauticasDiasFinal ) {
				   
				   alertCupom = new AlertCupom();
				   alertCupom.setAlertCupomText("Per�odo de Dias inv�lido para a revis�o!");
				   //System.out.println("Per�odo de Dias inv�lido para a revis�o!");
				   alertCupom.setCompositeEntityId(new Long(1), (Long)cupom.getEntityId());//ist_edson  01/07/2013
				   alertCupom.setAlertCupomSeveridade(AlertCupom.SEVERIDADE_ERROR);	
				   alertCupom.setAlertCupomKey("cupom.msg.error.days");
				   
			   } else if ( cupom.getHorasUso().longValue() < toleranciaNauticaHorasInicial 
					   || cupom.getHorasUso().longValue() > toleranciaNauticaHorasFinal ) {
				   
				   alertCupom = new AlertCupom();
				   alertCupom.setAlertCupomText("Quantidade de Horas informada e inv�lida para a revis�o!");
				   //System.out.println("Quantidade de Horas informada e inv�lida para a revis�o!");
				   alertCupom.setCompositeEntityId(new Long(1), (Long)cupom.getEntityId());//ist_edson  01/07/2013
				   alertCupom.setAlertCupomSeveridade(AlertCupom.SEVERIDADE_ERROR);	
				   alertCupom.setAlertCupomKey("cupom.msg.error.hours");
				   
			   }
		   } 
		   //System.out.println("T�rmino da Valida��o validateKmCondiz:" + (alertCupom == null));
		   
		   return alertCupom;
		
		   
	   } catch (DaoException dExp) {

           throw new BusinessException(dExp);

       } 
   }
   
   /** M�todo que realiza a valida��o da data de envio da revis�o
    *  Antes de salvar o cupom
    * 
    * @param cupom
    * 		<code>Cupom</code> Entidade populado com o valores do formul�rio
    * 
    * @return alertCupom
    * 		<code>AlertCupom</code> Mensagem retornada pela valida��o Warning/Error/Success    
    * 
    * @throws BusinessException
    * 
    * @throws BusinessRuleException
    * 		Se ocorrer algum erro recuperando algum objeto lan�amos esta exception
    *       com uma chave de properties da mensagem de erro.
    */
   public AlertCupom validatePrazoEnvio(Cupom newCupom) throws BusinessException,BusinessRuleException {
	   
	   AlertCupom alertCupom = null;
	   
	   Date startDate = newCupom.getDataRevisao();
	   Date finalDate = new Date();
	   
	   int daysUtils = feriadoDataBO.getDaysUtils(startDate, finalDate); 
	   
	   // Recuperamos o par�metro que para envio do cupom - dias corridos 
	   ParametroSistema parametroSistema = null;
	   //parametroSistema = getByParameterSystem(ParametroSistema.PRAZO_ENVIO_GARANTIA_DIAS);
	   
	   if ( Linha.TIPO_MOTOCICLETA.equals(newCupom.getLote().getLinha().getEntityId()) || 
			Linha.TIPO_QUADRICICLO.equals(newCupom.getLote().getLinha().getEntityId()) )
	   
		   parametroSistema = getByParameterSystem(ParametroSistema.PRAZO_ENVIO_MOTO_DIAS);
	   
	   else if ( Linha.TIPO_NAUTICA.equals(newCupom.getLote().getLinha().getEntityId()) || 
				Linha.TIPO_FORCA.equals(newCupom.getLote().getLinha().getEntityId()) )
		   
		   parametroSistema = getByParameterSystem(ParametroSistema.PRAZO_ENVIO_NAUTICA_DIAS);
	   
	   else {
		   
		   throw new BusinessException("Linha do produto n�o  identificada!");
		   
	   }
		
		if ( parametroSistema == null ) {
			
			throw new BusinessException("N�o foi encontrado o par�metro com o prazo de envio da revis�o!");			
			
		}
		int daysPrazo = Integer.parseInt(parametroSistema.getValorParametro());
		
		//System.out.println("Dias Utils - In�cio:" + startDate + " Final:" + finalDate + " Dias:" + daysUtils + " Prazo:" + daysPrazo);
		
	   if ( daysUtils > daysPrazo ) {
		   
		   alertCupom = new AlertCupom();
		   alertCupom.setAlertCupomText("A Data da Revis�o est� fora do Prazo!");
		   alertCupom.setCriadoPor(new Long(-1));
		   alertCupom.setDataCriacao(new Date());
		   alertCupom.setCompositeEntityId( new Long(10), (Long)newCupom.getEntityId());//ist_edson  01/07/2013
		   
		   // Altera��o realizada em 06/12/2007 - Edson
		   // Quando estiver fora do prazo, sempre lan�ar error 
		   alertCupom.setAlertCupomSeveridade(AlertCupom.SEVERIDADE_ERROR);
		   alertCupom.setAlertCupomKey("cupom.msg.error.dateOutside");
		   
	   }	   
	   
	   //System.out.println("T�rmino da Valida��o validatePrazoEnvio:" + (alertCupom == null));
	   
	   return alertCupom;
	   
   }
	   
   
   /** M�todo que realiza a verifica��o de a revis�o anterior foi realizada
    *  nos casos de revis�o Zero KM
    *  Antes de salvar o cupom
    * 
    * @param cupom
    * 		<code>Cupom</code> Entidade populado com o valores do formul�rio
    * 
    * @param linha
    * 		<code>Linha</code> Entidade com a linha de produtos da Concession�ria
    * 
    * @param isConcessionaria
    * 		<code>boolean</code> True se o chassi foi faturado pela Concession�ria
    * 							 False para chassi faturarado por outra Concession�ria
    * @param isConcessionaria
    * 		<code>boolean</code> True se o chassi foi faturado pela Concession�ria
    * 							 False para chassi faturarado por outra Concession�ria
    * 
    * @return alertCupom
    * 		<code>AlertCupom</code> Mensagem retornada pela valida��o Warning/Error/Success   
    * 
    * @throws BusinessException
    * 
    * @throws BusinessRuleException
    * 		Se ocorrer algum erro recuperando algum objeto lan�amos esta exception
    *       com uma chave de properties da mensagem de erro.
    */
   public AlertCupom validateRevisaoAnterior(Cupom newCupom, Linha linha, boolean isConcessionaria, boolean isEdit) throws BusinessException,BusinessRuleException {
	   
	   AlertCupom alertCupom = null;
	   
	   // Recuperamos uma lista de revis�es para o chassi informado
	   // OBS: O n�mero do cupom informado precisa ser validado para seq��ncia
	   List 	listCupom = this.listByChassi(newCupom.getChassi().toUpperCase());
	   Iterator iter  	  = null;
	   Cupom    cupom 	  = null;	   
	   
	   // Flag de lista com ou sem valores - True com vazia
	   boolean isListEmpty = listCupom == null || listCupom.isEmpty();
	   
	   // Flag de Revis�o de Zero Km - True para Revis�o Zero Km
	   boolean isRevisionZero = newCupom.getRevisao().getNumeroRevisao().equals(Revisao.REVISAO_ZERO);
	   
	   //System.out.println("isRevisionZero:" + isRevisionZero + " isListEmpty:" + isListEmpty);
	   // Verificamos se a revis�o solicitada � de Zero Km
	   if ( isRevisionZero ){
	   
		   // Verificamos se a lista est� vazia
		   if ( !isListEmpty && !isEdit ) {		   
			   	
			   alertCupom = new AlertCupom();
			   alertCupom.setAlertCupomText("A Revis�o solicitada j� foi realizada!");
			   alertCupom.setCompositeEntityId( new Long(3), (Long)newCupom.getEntityId());//ist_edson  01/07/2013
			   alertCupom.setAlertCupomSeveridade(AlertCupom.SEVERIDADE_ERROR);
			   alertCupom.setAlertCupomKey("cupom.msg.error.revisionYet");
			   
		   }// Fim da verifica��o de lista vazia 		   
		   
	   } else {
		   
		   // Agora sabemos que a Revis�o n�o � de Zero Km
		   // Verificamos se a lista n�o est� vazia
		   if ( !isListEmpty ) {	
		   
			   // Chegamos aqui com: Lista n�o est� vazia e Revis�o diferente de Zero Km
			   // Verificamos se o cupom passado faz parte da lista recuperada
			   
			   // Flag de revis�o anterior - True para Revis�o existe
			   boolean isExistPreviewRevision = false;	
			   			   
			   iter = listCupom.iterator();
		   
			   // ********************************************** //
			   // * ITERA��O DOS CUPONS DA LISTA				 //
			   // ********************************************** //
			   while ( iter.hasNext() ) {
				   
				   cupom = (Cupom)iter.next();
				  
				   //Comentado -- Retirado do c�digo de cupom CupomCode - ist_edson 01/07/2013
				   /*
				   if(!Linha.TIPO_MOTOCICLETA.equals(linha.getEntityId())) {
					   // Verificamos se o n�mero do cupom � diferente
					   if ( !(newCupom.getCupomCode().equals(cupom.getCupomCode())) ) {
						   
						   boolean 		 hasAlert 		= false;
						   ManualRevisao manualRevisao 	= null;
						   // Tentamos recuperar uma entidade do Manual de Revisao
						   // Para verificar se houve altera��o no n�mero do cupom
						   try {
							   
							   manualRevisao = (ManualRevisao)cupomDao.getByParameter(ManualRevisao.class, "manualSubst", String.valueOf(cupom.getCupomCode().longValue()));
							   
						   } catch (DaoException dExp) {
	
					           throw new BusinessException(dExp);
	
					       }
						   
						   //System.out.println("Tem Manual:" + (manualRevisao != null));
						   // Se existir manual de revis�o, devemos conferir 
						   if ( manualRevisao != null ) {
							   
							   //System.out.println("Cupom Inserido:" + newCupom.getCupomCode() + " - Cupom Alterado:" + Long.valueOf(manualRevisao.getNumeroManual()));
							   if ( !(newCupom.getCupomCode().equals(Long.valueOf(manualRevisao.getNumeroManual()))) ) {
								   hasAlert = true;
							   }
							   
						   } else {
							   
							   //System.out.println("N�o foi encontrado Manual de Revis�o!");
							   hasAlert = true;
						   }
						   
						   if ( hasAlert ) {
							   
							   //System.out.println("Erro no n�mero do cupom!");
							   //Quando n�mero do cupom informado for diferente de qualquer cupom da lista, emitir alerta
							   alertCupom = new AlertCupom();
							   alertCupom.setAlertCupomText("Erro no n�mero do cupom! <b>" + newCupom.getCupomCode() + "</b>");
							   alertCupom.setCompositeEntityId( new Long(5), (Long)newCupom.getCupomCode());
							   alertCupom.setAlertCupomSeveridade(AlertCupom.SEVERIDADE_ERROR);
							   alertCupom.setAlertCupomKey("cupom.msg.error.numberCupom");
							   
							   // Retornamos para a Action com o alerta
							   return alertCupom;
							   
						   }
						   
					   }
				   }
					*/  
				   // Verificamos se o n�mero da revis�o j� existe - Se existir e n�o for Edi��o - emitir Alerta 
				   if ( cupom.getRevisao().getNumeroRevisao().equals(newCupom.getRevisao().getNumeroRevisao()) ) {
					   
					   // Verificamos se � uma opera��o de edi��o para validar
					   if ( !isEdit ) {
						   
						   // Quando j� existir a revis�o, verificamos se foi faturado para mesma concession�ria
						   if ( isConcessionaria ) {
							   
							   alertCupom = new AlertCupom();
							   alertCupom.setAlertCupomText("Revis�o j� foi paga para esta Concession�ria!");
							   alertCupom.setCompositeEntityId( new Long(7), (Long)newCupom.getEntityId());//ist_edson  01/07/2013
							   alertCupom.setAlertCupomSeveridade(AlertCupom.SEVERIDADE_ERROR);
							   alertCupom.setAlertCupomKey("cupom.msg.error.thisPayRevision");
							   
							   // Retornamos para a Action com o alerta
							   return alertCupom;
							   
						   } else {
							   
							   alertCupom = new AlertCupom();
							   alertCupom.setAlertCupomText("Revis�o j� foi paga para outra Concession�ria!");
							   alertCupom.setCompositeEntityId( new Long(8), (Long)newCupom.getEntityId());//ist_edson  01/07/2013
							   alertCupom.setAlertCupomSeveridade(AlertCupom.SEVERIDADE_ERROR);
							   alertCupom.setAlertCupomKey("cupom.msg.error.otherPayRevision");
							   
							   // Retornamos para a Action com o alerta
							   return alertCupom;
							   
						   }// isConcessionaria - Fim da verifica��o do faturamento para a concession�ria
						   
					   } else {
						   
						   // Quando for uma edi��o, verificamos se o objeto recuperado 
						   // � mesmo que est� sendo editado
						   if ( !( cupom.getEntityId().equals(newCupom.getEntityId()) ) ) {
							   
							   alertCupom = new AlertCupom();
							   alertCupom.setAlertCupomText("A Revis�o solicitada j� foi realizada!");
							   alertCupom.setCompositeEntityId( new Long(3), (Long)newCupom.getEntityId());//ist_edson  01/07/2013
							   alertCupom.setAlertCupomSeveridade(AlertCupom.SEVERIDADE_ERROR);
							   alertCupom.setAlertCupomKey("cupom.msg.error.revisionYet");	
							   
							   // Retornamos para a Action com o alerta
							   return alertCupom;
							   
						   }
						   
					   }
				   
				   } else {
					   
					   // Verificamos se � uma revis�o anterior
					   if ( cupom.getRevisao().getNumeroRevisao().longValue() == ( newCupom.getRevisao().getNumeroRevisao().longValue() - 1) ) {
						   
						   // Agora verificamos se esta revis�o anterior tem a data anterior a da revis�o solicitada
						   if ( DateUtils.compareDate(cupom.getDataRevisao(), newCupom.getDataRevisao()) > 0 ){
							   
							   alertCupom = new AlertCupom();
							   alertCupom.setAlertCupomText("A Data desta Revis�o � menor que a Data da Revis�o Anterior!");
							   alertCupom.setCompositeEntityId( new Long(4), (Long)newCupom.getEntityId());//ist_edson  01/07/2013
							   alertCupom.setAlertCupomSeveridade(AlertCupom.SEVERIDADE_ERROR);
							   alertCupom.setAlertCupomKey("cupom.msg.error.greaterDateRevision");
							   
							   // Retornamos para a Action com o alerta
							   return alertCupom;								   
							   
							   // Verificamos se a data � igual
						   } else if ( DateUtils.compareDate(cupom.getDataRevisao(), newCupom.getDataRevisao()) == 0 ){
							   
							   alertCupom = new AlertCupom();
							   alertCupom.setAlertCupomText("A Data desta Revis�o � igual a Data da Revis�o Anterior!");
							   alertCupom.setCompositeEntityId( new Long(4), (Long)newCupom.getEntityId());//ist_edson  01/07/2013
							   alertCupom.setAlertCupomSeveridade(AlertCupom.SEVERIDADE_ERROR);
							   alertCupom.setAlertCupomKey("cupom.msg.error.equalDateRevision");
							   
							   // Retornamos para a Action com o alerta
							   return alertCupom;
							   
							    
						   } else {
							   
							   // Ent�o a revis�o anterior est� OK
							   isExistPreviewRevision = true;
							   alertCupom = null;
						   
						   }// Fim da valida��o da Data
						   
					   }// Fim
				   }
				   
			   }// Fim da itera��o da lista
			   
			   // ************************************************* //
			   // FIM DA ITERA��O, VAMOS VERIFICAR AGORA SE EXISTE  // 
			   // REVIS�O ANTERIOR 									//
			   // ************************************************* //			   
			   
			   if ( !isExistPreviewRevision ) {
				   
				   /* Altera��o realizada em 15/08/2007, conforme solicita��o Yamaha.
				    * 1. Quando n�o existir uma revis�o anterior e o produto n�o foi faturado
				    * para a concession�ria do usu�rio, podemos permitir a inclus�o da 
				    * revis�o, mas com a confirma��o da opera��o emitindo uma mnesagem de alerta.
				    * 2. Quando n�o existir uma revis�o anterior e o produto foi faturado
				    * para a concession�ria do usu�rio, n�o podemos permitir a inclus�o da revis�o 
				    * e emitir uma mensagem de erro.
				    */ 
				   if ( isConcessionaria ) {
					   
					   alertCupom = new AlertCupom();
					   alertCupom.setAlertCupomText("A Revis�o Anterior n�o foi realizada!");
					   alertCupom.setCompositeEntityId( new Long(3), (Long)newCupom.getEntityId());//ist_edson  01/07/2013
					   alertCupom.setAlertCupomSeveridade(AlertCupom.SEVERIDADE_ERROR);
					   alertCupom.setAlertCupomKey("cupom.msg.error.previewRevision");					   
					   
				   } else {
				   
					   alertCupom = new AlertCupom();
					   alertCupom.setAlertCupomText("A Revis�o Anterior n�o foi realizada!");
					   alertCupom.setCompositeEntityId( new Long(3), (Long)newCupom.getEntityId());//ist_edson  01/07/2013
					   alertCupom.setAlertCupomSeveridade(AlertCupom.SEVERIDADE_WARNING);
					   alertCupom.setAlertCupomKey("cupom.msg.warning.previewRevision");
					   
				  } 
				   
			   } // isExistPreviewRevision - Fim da verifica��o se existe revis�o anterior
			   
		   } else {
			   
			   // Chegamos aqui com revis�o diferente de Zero KM e a lista vazia
			   			   
			   /* Altera��o realizada em 15/08/2007, conforme solicita��o Yamaha.
			    * 1. Quando n�o existir uma revis�o anterior e o produto n�o foi faturado
			    * para a concession�ria do usu�rio, podemos permitir a inclus�o da 
			    * revis�o, mas com a confirma��o da opera��o emitindo uma mnesagem de alerta.
			    * 2. Quando n�o existir uma revis�o anterior e o produto foi faturado
			    * para a concession�ria do usu�rio, n�o podemos permitir a inclus�o da revis�o 
			    * e emitir uma mensagem de erro.
			    */ 
			   if ( isConcessionaria ) {
				   
				   alertCupom = new AlertCupom();
				   alertCupom.setAlertCupomText("A Revis�o Anterior n�o foi realizada!");
				   alertCupom.setCompositeEntityId( new Long(3), (Long)newCupom.getEntityId());//ist_edson  01/07/2013
				   alertCupom.setAlertCupomSeveridade(AlertCupom.SEVERIDADE_ERROR);
				   alertCupom.setAlertCupomKey("cupom.msg.error.previewRevision");					   
				   
			   } else {
			   
				   alertCupom = new AlertCupom();
				   alertCupom.setAlertCupomText("A Revis�o Anterior n�o foi realizada!");
				   alertCupom.setCompositeEntityId( new Long(3), (Long)newCupom.getEntityId());//ist_edson  01/07/2013
				   alertCupom.setAlertCupomSeveridade(AlertCupom.SEVERIDADE_WARNING);
				   alertCupom.setAlertCupomKey("cupom.msg.warning.previewRevision");
				   
			  } 
			   
			   
		   }// Fim da verifica��o de lista vazia
		   
		   
		   
	   }// isRevisionZero - Fim da verifica��o de revis�o zero	   
	   
	   return alertCupom;
   }
   
   /** Obt�m uma lista de cupons para relacionar com notas fiscais.
    *  
    *  @throws DaoException
    *      Se houverem erros durante a execu��o estes ser�o lan�ados
    *      como uma Exception deste tipo.
    */
   public List listCupons( Concessionaria concessionaria, StatusLote statusLote )throws BusinessException {
	   
	   try {

           return cupomDao.listCupons(concessionaria, statusLote);

       } catch (DaoException dExp) {

           throw new BusinessException(dExp);

       }
	   
   }
   
   /**
	 * Recebe a lista de cupons que foram lan�adas as notas e altera seu status,
	 * 
	 * 
	 * @param cupons
	 *            <code>Collection</code> Lista de cupons para verifica��o.
	 *            
	 * @param usuario
	 * 			  <code>Usuario</code> Entidade do usu�rio logado.
	 * 
	 * @throws BusinessException
	 *             Se houverem erros de execu��o ou nas camadas abaixo dos
	 *             servi�os, ser�o encapsulados neste tipo de
	 *             <code>Exception</code>.
	 */
  public void updateStatus(Collection cupons, Usuario usuario) throws BusinessException {
	   	   
      try {
   	   
    	  	// Status para ser atribu�do ao Lote quando n�o existir mais cupons;
			StatusLote statusLote = (StatusLote) cupomDao.get(StatusLote.class,	StatusLote.STATUS_EM_DIGITACAO);
			Lote lote = null;
			Iterator iter = cupons.iterator();
			while (iter.hasNext()) {

				Cupom cupom = (Cupom) iter.next();

				lote = (Lote) cupom.getLote();
								
				if (	StatusGarantia.STATUS_AGUARDANDO_DIGITACAO_NOTA.equals(cupom.getStatusMovId()) 
					|| 	StatusGarantia.STATUS_NF_DIVERGENTE.equals(cupom.getStatusMovId()) ) {
					
					cupom.setStatusMovId( StatusGarantia.STATUS_NF_DIGITADA );
	
					//System.out.println("Alterando o statusMovId do cupom "
						//	+ cupom.getEntityId() + " para 13 - "
							//+ cupom.getStatusMovId());
	
					this.save(cupom);
				}

			}
			if (lote != null) {
				
				if ( !StatusLote.STATUS_EM_DIGITACAO.equals(lote.getStatusLote().getEntityId()) ) {
					// Se o Status do Lote for diferente de "EM DIGITA��O",
					// setamos para esse status
					if ( !StatusLote.STATUS_EM_DIGITACAO.equals(lote.getStatusLote().getEntityId()) ) {
						lote.setStatusLote(statusLote);
						lote.setDataAtualizacao(new Date());
						lote.setAtualizadoPor((Long)usuario.getEntityId());    			   
						loteBO.alterState(lote);
					}
					
				}
			}	   	   

      } catch (DaoException dExp) {

          throw new BusinessException(dExp);

      }

  }    
  
  
  	/** Recupera o valor do Servi�o para um cupom.
  	 *
  	 *  @param revisao
  	 *  		<code>Revisao</code> Uma entidade de revisao.
  	 *  
  	 *  @param modelo
  	 *      <code>String</code> valor do modelo do produto.
  	 *      
  	 *  @param codigoRegiao
  	 *  	   <code>Long</code> C�digo da regis�o da concession�ria.
  	 *      
  	 *  @return
  	 *      Um objeto <code>ValorServico</code>.
  	 *      
  	 * @throws BusinessException
  	 *      Se houverem erros de execu��o ou nas camadas 
  	 *      abaixo dos servi�os, ser�o encapsulados neste
  	 *      tipo de <code>Exception</code>.
  	 */
  	public ValorServico getValorServico(Revisao revisao, String modelo, Long codigoRegiao ) throws BusinessException {

    	try {

    		return (ValorServico)cupomDao.getValorServico(revisao, modelo, codigoRegiao);

    	} catch (DaoException dExp) {

         	throw new BusinessException(dExp);

    	}

  	}
  	
  	/** Obt�m uma entidade de parametro do sistema a partir de um nome de par�metro passado.
	 * 
	 *  @param parameterName
	 *      <code>Serializable<code>. Nome do par�metro(Coluna) pelo qual o objeto
	 *      dever� ser pesquisado na camada de persist�ncia da aplica��o.

	 *  @return
	 *      Um <code>ParametroSistema</code> populado com os dados da 
	 *      entidade espec�fica, solicitada, ou caso nenhuma entidade
	 *      seja encontrada, retornar� <code>null</code>.
	 *      
	 *  @throws DaoException
	 *      Se houverem problemas nas camadas inferiores, estes ser�o 
	 *      convertidos para uma DaoException.
	 */     
	public ParametroSistema getByParameterSystem(String parameterName) throws BusinessException {

		try {

			return (ParametroSistema) cupomDao.getByParameter(ParametroSistema.class, "nomeParametro", parameterName);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}    	

	}
	
	/** Retorna a �ltima kilometragem registrada 
	* 
	* @param chassi
	* @return Long - �ltima km cadastrada para o produto
	* @throws DaoException
	*/
	public Long getLastRevision(String chassi)throws BusinessException {
				
		try {

			return cupomDao.getLastRevision(chassi);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}  
		
	}
	
	/** Retorna a �ltima kilometragem registrada, com exce��o da garantia enviada
	*  Para os casos de altera��o da quilometragem da Garantia
	* 
	* @param chassi
	* @param garantiaId
	* @return Long - �ltima km cadastrada para o produto
	* @throws DaoException
	*/
	public Long getLastRevision(String chassi, Long garantiaId)throws BusinessException {
				
		try {

			return cupomDao.getLastRevision(chassi, garantiaId);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}  
		
	}
	
	
	/** Verificar se est� dentro do prazo de garantia.	
	 *  Valida��o realizada com base na data de entrega do produto,
	 *  Data da Revis�o de Entrega      
	 * 
	 * @param cupom
	 * 	<code>Cupom</code> Entidade de Cupom
	 * 
	 * @param faturamento
	 * 	<code>Faturamento</code> Entidade do Faturamento do produto.
	 * 
	 * @return List
	 * 	<code>List</code> lista de AlertCupom com os alertas da valida��o.
	 * 
	 * @throws BusinessException
	 * @throws BusinessRuleException
	 */
	public List validatePrazo( Cupom cupom, Faturamento faturamento) throws BusinessException,BusinessRuleException {
		
		List alertasPrazo     = new ArrayList();
		
		AlertCupom alertCupom = null;
		
		try {
			
			// Buscamos a revis�o zero 
			Cupom  cupomEntrega	  = getCupomDao().getCupomByRevisao(cupom.getChassi(), new Long(1));
			Item   item  		  = (Item) itemBO.getItem(faturamento.getItemId());
			Date   dataEntrega    = null;
			boolean isRevisaoZero = true; 
			boolean isLaser       = true;
			
			// Se n�o foi realizada a data de revis�o zero, pegamos a data da nota fiscal
			if ( cupomEntrega != null ) {

				if ( cupomEntrega.getDataEntrega() != null )
					dataEntrega = cupomEntrega.getDataEntrega();
				else
					dataEntrega = cupomEntrega.getDataRevisao();
				
				// Se existir uma associa��o com um Tipo de Uso, verificamos se � laser ou comercial
				if ( cupomEntrega.getTipoUsoBarco() != null ) {
					
					isLaser = cupomEntrega.getTipoUsoBarco().getEntityId().equals(new Long(1)) ? true : false;
					
				}
				
			} else {
				
				isRevisaoZero = false;
				dataEntrega = faturamento.getDataNotaFiscal();
			}
			
			// ********************************************************************************** //
			// *  Verificamos a linha do produto e recuperamos o par�metro para saber o prazo   * //
			// *  de garantia.																	* //
			// ********************************************************************************** //			
			String paramName = new String();
			
			if ( Linha.TIPO_MOTOCICLETA.equals(cupom.getLote().getLinha().getEntityId()) ) { 
			
				paramName = ParametroSistema.GARANTIA_MOTO_DIAS;
			
			} else {				
				// Quando a for da linha N�utica e de uso para laser, verificamos a origem
				if ( isLaser ) {
					
					if ( Item.ORIGEM_NAC.equals(item.getOrigem()) ) {
						
						paramName = ParametroSistema.GARANTIA_NAUTICA_LASER_CKD_DIAS;
						
					} else {
						
						paramName = ParametroSistema.GARANTIA_NAUTICA_LASER_CBU_DIAS;
					} 
				
				// Quando o uso for comercial, verificamos se existe revis�o Zero
				} else if ( isRevisaoZero ) {
					
					// Se o tipo de ve�culo n�o foi informado, lan�amos um error
					if ( item.getTipoVeiculo() != null && !"".equals(item.getTipoVeiculo())) {
					
						// Verificamos o tipo de ve�culo
						if ( Item.TIPO_VEICULO_VA.equals(item.getTipoVeiculo()) ) {
							
							paramName = ParametroSistema.GARANTIA_NAUTICA_COMERCIAL_JET_DIAS;						
							
						} else if ( Item.TIPO_VEICULO_MP.equals(item.getTipoVeiculo()) ) {
							
							paramName = ParametroSistema.GARANTIA_NAUTICA_COMERCIAL_MP_DIAS;						
							
						}
						
					} else {
						
						alertCupom = new AlertCupom();
						alertCupom.setAlertCupomText("Tipo de ve�culo n�o encontrado!");
						alertCupom.setAlertCupomSeveridade(AlertGarantia.SEVERIDADE_ERROR);
						alertCupom.setAlertCupomKey("garantia.msg.error.prazo.tipoVeiculo");	
						alertasPrazo.add(alertCupom);
						
					}
				
				// Ve�culo de uso comercial e quando a revis�o de zero km n�o foi realizada,
				// Usamos o par�metro do MP
				} else {					
					
					paramName = ParametroSistema.GARANTIA_NAUTICA_COMERCIAL_MP_DIAS;
					
					alertCupom = new AlertCupom();
					alertCupom.setAlertCupomText("Revis�o de entrega e tipo de uso n�o encontrados!");
					alertCupom.setAlertCupomSeveridade(AlertCupom.SEVERIDADE_WARNING);
					alertCupom.setAlertCupomKey("garantia.msg.warning.prazo.tipoUso");
					alertasPrazo.add(alertCupom);
					
				}
				
			}
			
			ParametroSistema parametro = this.getByParameterSystem(paramName);			
			int paramDias = Integer.parseInt(parametro.getValorParametro());
			
			// Joviano 30-01-2016 INI #01
			String paramNameGarantiaDif = ParametroSistema.GARANTIA_DIFERENCIADA;
			ParametroSistema parametroGarantiaDif = this.getByParameterSystem(paramNameGarantiaDif);
			int paramDiasGarantiaDif = Integer.parseInt(parametroGarantiaDif.getValorParametro());
			Modelo modeloGarantia = (Modelo)this.cupomDao.get(Modelo.class, new Long(faturamento.getModelo()));
			Date dtHoje = new Date();
			
			System.out.println("CupomBusiness paramDiasGarantiaDif=" + paramDiasGarantiaDif);
			System.out.println("CupomBusiness Linha=" + Linha.TIPO_MOTOCICLETA);
			System.out.println("CupomBusiness modeloGarantia=" + modeloGarantia.getGarantiaDif());
			try {
			System.out.println("CupomBusiness valor Servico getValorMaoObra =" + cupom.getValorServico().getValorMaoObra());
			} catch (NullPointerException e) {}
			try {
			System.out.println("CupomBusiness valor Servico getValorRevisao =" + cupom.getValorServico().getValorRevisao());
			} catch (NullPointerException e) {}
			
			if ("S".equals(modeloGarantia.getGarantiaDif()) ) {
				
				if ( DateUtils.dataDiff(dataEntrega, cupom.getDataRevisao()) > paramDiasGarantiaDif ) {
					
					alertCupom = new AlertCupom();
					alertCupom.setAlertCupomText("Chassi com garantia estendida fora do prazo de Garantia!");
					//alertCupom.setAlertCupomSeveridade(AlertCupom.SEVERIDADE_WARNING);
					//alertCupom.setAlertCupomKey("garantia.msg.error.prazo.garantia.dif");
					alertCupom.setAlertCupomSeveridade(AlertCupom.SEVERIDADE_WARNING);
					alertCupom.setAlertCupomKey("garantia.msg.warning.prazo.garantia.dif");	 
					
					alertasPrazo.add(alertCupom);
					//ValorServico valorServico = cupom.getValorServico();
					//valorServico.setValor
					//cupom.setValorServico(valorServico)
					
				}
				
			} 
			else 
			if ( DateUtils.dataDiff(dataEntrega, cupom.getDataRevisao()) > paramDias ) {
				
					alertCupom = new AlertCupom();
					alertCupom.setAlertCupomText("Chassi fora do prazo de Garantia!");
					//alertCupom.setAlertCupomSeveridade(AlertCupom.SEVERIDADE_ERROR);
					//alertCupom.setAlertCupomKey("garantia.msg.error.prazo.garantia");	 
					alertCupom.setAlertCupomSeveridade(AlertCupom.SEVERIDADE_WARNING);
					alertCupom.setAlertCupomKey("garantia.msg.warning.prazo.garantia");	 
					alertasPrazo.add(alertCupom);
				
			}
      // Joviano 30-01-2016 FIM #01

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}
		
		//System.out.println("Valida��o do prazo de Garantia conclu�do! Alertas:" + alertasPrazo.size());
		return alertasPrazo;

	}
   
   
   /** M�todo getter para "cupomDao".
    *  @return
    *      <code>CupomDao</code>. O valor atual de CupomDao.
    */
   public CupomDao getCupomDao() {
       return this.cupomDao;
   }

   /** M�todo setter para "cupomDao".
    *  @param entityId
    *      <code>CupomDao</code> a ser designado para cupomDao.
    */
   public void setCupomDao(CupomDao cupomDao) {
       this.cupomDao = cupomDao;
   }

	/** M�todo getter para a propriedade feriadoDataBO
	 *
	 *  @return FeriadoDataBusiness de feriadoDataBO
	 */
	public FeriadoDataBusiness getFeriadoDataBO() {
		return feriadoDataBO;
	}
	
	/** M�todo setter para a propriedade feriadoDataBO
	 *
	 * @param feriadoDataBO FeriadoDataBusiness
	 */
	public void setFeriadoDataBO(FeriadoDataBusiness feriadoDataBO) {
		this.feriadoDataBO = feriadoDataBO;
	}
	
	/** M�todo getter para a propriedade loteBO
	 *
	 *  @return LoteBusiness de loteBO
	 */
	public LoteBusiness getLoteBO() {
		return loteBO;
	}
	
	/** M�todo setter para a propriedade loteBO
	 *
	 * @param loteBO LoteBusiness
	 */
	public void setLoteBO(LoteBusiness loteBO) {
		this.loteBO = loteBO;
	}

	/** M�todo getter para a propriedade revisaoBO
	 * 
	 *  @return RevisaoBusiness
	 *
	 */
	public RevisaoBusiness getRevisaoBO() {
		return revisaoBO;
	}

	/** M�todo setter para a propriedade revisaoBO
	 *
	 * @param revisaoBO 
	 *           <code>RevisaoBusiness</code> a ser designado para revisaoBO.
	 * 
	 */
	public void setRevisaoBO(RevisaoBusiness revisaoBO) {
		this.revisaoBO = revisaoBO;
	}

	/**
	 * M�todo getter para a propriedade itemBO
	 * @return  ItemBusiness de itemBO
	 */
	public ItemBusiness getItemBO() {
		return itemBO;
	}

	/**
	 * M�todo setter para a propriedade itemBO
	 * @param itemBO ItemBusiness
	 */
	public void setItemBO(ItemBusiness itemBO) {
		this.itemBO = itemBO;
	}
	
	      
}
