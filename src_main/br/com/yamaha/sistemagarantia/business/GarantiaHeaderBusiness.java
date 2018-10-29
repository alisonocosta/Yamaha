/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......GarantiaHeaderBusiness.java
 *   .: Criação.....01 de junho, 12:29
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Objeto de negócios para a entidade "GarantiaHeader".
 */
package br.com.yamaha.sistemagarantia.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.BusinessRuleException;
import br.com.resource.infra.exception.DaoException;
import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.DateUtils;
import br.com.resource.infra.utils.NumberUtils;
import br.com.yamaha.sistemagarantia.controller.helper.ControllerHelper;
import br.com.yamaha.sistemagarantia.dao.CampaignDao;
import br.com.yamaha.sistemagarantia.dao.GarantiaHeaderDao;
import br.com.yamaha.sistemagarantia.model.AlertCupom;
import br.com.yamaha.sistemagarantia.model.AlertGarantia;
import br.com.yamaha.sistemagarantia.model.AutorizacaoEspecialSG;
import br.com.yamaha.sistemagarantia.model.Campaign;
import br.com.yamaha.sistemagarantia.model.CampaignBilling;
import br.com.yamaha.sistemagarantia.model.CampaignPiece;
import br.com.yamaha.sistemagarantia.model.CausaProblemaGarantia;
import br.com.yamaha.sistemagarantia.model.Concessionaria;
import br.com.yamaha.sistemagarantia.model.Condicao;
import br.com.yamaha.sistemagarantia.model.CondicaoProblemaGarantia;
import br.com.yamaha.sistemagarantia.model.Cupom;
import br.com.yamaha.sistemagarantia.model.DiagnosticoProblemaGarantia;
import br.com.yamaha.sistemagarantia.model.FatorGarantia;
import br.com.yamaha.sistemagarantia.model.Faturamento;
import br.com.yamaha.sistemagarantia.model.GarantiaHeader;
import br.com.yamaha.sistemagarantia.model.GarantiaLine;
import br.com.yamaha.sistemagarantia.model.InfoMercGarantia;
import br.com.yamaha.sistemagarantia.model.InfoMercado;
import br.com.yamaha.sistemagarantia.model.Item;
import br.com.yamaha.sistemagarantia.model.Linha;
import br.com.yamaha.sistemagarantia.model.Lote;
import br.com.yamaha.sistemagarantia.model.Modelo;
import br.com.yamaha.sistemagarantia.model.ParametroSistema;
import br.com.yamaha.sistemagarantia.model.Recall;
import br.com.yamaha.sistemagarantia.model.Servico;
import br.com.yamaha.sistemagarantia.model.ServicoGrupo;
import br.com.yamaha.sistemagarantia.model.Sintoma;
import br.com.yamaha.sistemagarantia.model.SolucaoProblemaGarantia;
import br.com.yamaha.sistemagarantia.model.StatusGarantia;
import br.com.yamaha.sistemagarantia.model.StatusLote;
import br.com.yamaha.sistemagarantia.model.TabelaPreco;
import br.com.yamaha.sistemagarantia.model.TempoPadrao;
import br.com.yamaha.sistemagarantia.model.Usuario;
import br.com.yamaha.sistemagarantia.model.ValorServico;
import br.com.yamaha.sistemagarantia.model.id.GarantiaLineId;
import br.com.yamaha.sistemagarantia.model.id.TabelaPrecoId;
import br.com.yamaha.sistemagarantia.model.to.GarantiaTO;
import br.com.yamaha.sistemagarantia.model.to.PecaTO;

/** Classe de negócios relacionada à entidade <b>GarantiaHeader</b>.
 * 
 *  @author Edson Luiz Sumensari
 */
public class GarantiaHeaderBusiness extends BusinessObject {


	/** Objeto garantiaHeaderDao para utilizado para este objeto de negócios. 
	 *  
	 */
	private GarantiaHeaderDao garantiaHeaderDao;

	/** Objeto FeriadoDataBusiness para utilizado para este objeto de negócios. 
	 *  
	 */
	private FeriadoDataBusiness feriadoDataBO;

	/** Objeto loteBusiness para utilizado para este objeto de negócios. 
	 *  
	 */
	private LoteBusiness loteBO;
	
	/** Objeto cupomBusiness para utilizado para este objeto de negócios. 
	 *  
	 */
	private CupomBusiness cupomBO;
	
	/** Objeto itemBusiness para utilizado para este objeto de negócios. 
	 *  
	 */
	private ItemBusiness itemBO;

	/** Objeto AlertGarantiaBusiness para utilizado para este objeto de negócios.
	 * 
	 */
	private AlertGarantiaBusiness alertGarantiaBO;
	
	/** Objeto campaignDao para utilizado para este objeto de negócios. 
	 *  
	 */
	private CampaignDao campaignDao;
	
	/**
	 *  Objeto transactionTemplate para controle de transação
	 */
	private TransactionTemplate transactionTemplate;

	/** 
    * recupera um lote de garantias e suas dependências.
    *      
    *  @param entityId
    *  	<code>Integer</code> Id do lote.
    *  
    *  @return
    *      <code>Lote</code> Uma entidade de Lote.
    *  
    *  @throws DaoException
    *      Se houverem erros durante a execução estes serão lançados
    *      como uma Exception deste tipo.
    */
	public Lote getLote(Integer entityId) throws BusinessException {

		try {

			return garantiaHeaderDao.getLote(entityId);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}

	}	
	
	/** Recupera uma GarantiaHeader pelo id.
	 *
	 *  @param entityId
	 *      Referência da entidade na camada de persistência.
	 *  @return
	 *      Um objeto <code>GarantiaHeader</code>.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */
	public GarantiaHeader get(Serializable entityId) throws BusinessException {

		try {

			return (GarantiaHeader)garantiaHeaderDao.get(GarantiaHeader.class, entityId);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}

	}

	/** Recupera uma entidade pelo id.
	 *
	 *  @param clazz
	 *      <code>Class</code> Classe base para pesquisa.
	 *      
	 *  @param entityId
	 *      Referência da entidade na camada de persistência.
	 *      
	 *  @return
	 *      Um objeto <code>GarantiaHeader</code>.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */
	public EntityObject get(Class clazz, Serializable entityId) throws BusinessException {

		try {

			return (EntityObject)garantiaHeaderDao.get(clazz, entityId);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}

	}

	/** Obtém uma lista de garantias para relacionar com notas fiscais.
	 *
	 *  @param concessionaria
	 *  	<code>Concessionaria</code> entidade concessionária
	 *  
	 *  @param statusLote
	 *  	<code>StatusLote</code> entidade statusLote
	 *  
	 *  @throws DaoException
	 *      Se houverem erros durante a execução estes serão lançados
	 *      como uma Exception deste tipo.
	 */
	public List listGarantias( Concessionaria concessionaria, StatusLote statusLote) throws BusinessException {

		try {

			return garantiaHeaderDao.listGarantias(concessionaria, statusLote);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}

	}

	/** Lista as garantias existentes no banco de dados de acordo
	 *  com lote informado.
	 *
	 * @param lote
	 *      lote para filtro.
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
	public List listByLote(Lote lote) throws BusinessException {

		try {

			List result = garantiaHeaderDao.listByParameter(GarantiaHeader.class, "lote", lote, "dataAberturaOS", "DESC");
			
			if(result != null) {
			
				GarantiaHeader gh = null;
				Campaign cp = null;
				Long campaignId = null;
				Iterator it = result.iterator();
				while(it.hasNext()){

					gh = (GarantiaHeader) it.next();
					campaignId = null;

					campaignId = campaignDao.getCampaignByGarantia((Integer)gh.getEntityId());

					if(campaignId != null){ 
						cp = campaignDao.getCampaignById(campaignId);
						if(cp != null)
							gh.setCampaign(cp);
					}					
				}
			}
			
			return result;
			
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
	 *  @param garantia
	 *      <code>Garatnia</code> representando a entidade a ser
	 *      criada/atualizada na camada de persistência.
	 *      
	 *  @param listAlertas
	 *      <code>List</code> Lista de alertas enviados das validações.	 
	 *      
	 *  @throws DaoException
	 *      Se houverem problemas nas camadas inferiores, estes serão 
	 *      convertidos para uma BusinessException.
	 */    
	public void save(GarantiaHeader garantia, List listAlertas) throws BusinessException {

		try {
			
			garantiaHeaderDao.makePersistent(garantia);

			if ( !listAlertas.isEmpty() && listAlertas != null ) {

				Iterator it = listAlertas.iterator();
				while ( it.hasNext() ) {

					AlertGarantia alertGarantia = (AlertGarantia) it.next();
					if ( alertGarantia.getAlertGarantiaKey().indexOf("warning") != -1 ) {
						
						alertGarantia.setGarantia(garantia);
						alertGarantiaBO.save(alertGarantia);
						
					}
				}
			}

		} catch ( DaoException daoExp ) {

			throw new BusinessException( daoExp );

		}

	}
	
	/** Salva ou atualiza o estado de uma entidade relacionada a garantia.
	 *  <p>
	 *  Se a entidade recebida não existir na camada de persistência
	 *  da aplicação, será criada. Do contrário seus valores serão
	 *  atualizados de acordo com as informações alteradas.
	 *  
	 *  @param entityObject
	 *      <code>EntityObject</code> representando a entidade a ser
	 *      criada/atualizada na camada de persistência.
	 *      
	 *  @throws DaoException
	 *      Se houverem problemas nas camadas inferiores, estes serão 
	 *      convertidos para uma BusinessException.
	 */    
	public void saveEntity(EntityObject entityObject) throws BusinessException {
		
		try {

			garantiaHeaderDao.makePersistent(entityObject);
			
		} catch ( DaoException daoExp ) {

			throw new BusinessException( daoExp );

		}
	}
	
	
	/** Salva ou atualiza a garantia e as peças.
	 *  <p>
	 *  Este processo exige a unificação da transação, se cocorrer algum problema
	 *  o sistema realiza roolBack.
	 *  
	 *  @param garantiaTO
	 *      <code>garantiaTO</code> representando a entidade a ser
	 *      criada/atualizada na camada de persistência.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */    
	public AlertGarantia saveAll(GarantiaTO garantiaTO, boolean isEdit) throws BusinessException {
		
		// recuperamos as entidades
		final GarantiaHeader 		garantia 				= garantiaTO.getGarantia();
		final CampaignBilling       campaignBilling 		= garantiaTO.getCampaignBilling();
		//final Lote					loteFinal				= garantia.getLote();
		final AutorizacaoEspecialSG	autorizacaoEspecialSG 	= garantiaTO.getAutorizacaoEspecialSG();
		final InfoMercado			infoMercadoFinal		= garantiaTO.getInfoMercado();
		final List 					listGarantiaAlertas 	= garantiaTO.getListAlertas();		
		final List 					listGarantiaLine 		= garantiaTO.getListGarantiaLine();
		final boolean 				isEditFinal				= isEdit;
		
		AlertGarantia alerta = (AlertGarantia) transactionTemplate.execute(
                
	            new TransactionCallback() {
	                
	                public Object doInTransaction(TransactionStatus ts) {
	                    
	                	AlertGarantia alert = null;
		
						try {
							if ( isEditFinal )
								garantia.setListGarantiaLine(listGarantiaLine);
							//Salvamos a garantia e os alertas se existirem
							save(garantia, listGarantiaAlertas);
							
							//Campaign
							if(garantia.getHasCampaign() && campaignBilling != null){								
								saveEntity(campaignBilling);	
							}
							
							if(garantia.getGrupos() != null) {
								Iterator it = garantia.getGrupos().iterator();
								ServicoGrupo servicoGrupo = null;
								while(it.hasNext()) {
									servicoGrupo = (ServicoGrupo)it.next();
									saveEntity(servicoGrupo);
								}
							}														
							
							//System.out.println(" Garantia salva com sucesso! Is Edit:" + isEditFinal);

							//Atualizamos o status da autorização especial, se existir
							if ( autorizacaoEspecialSG != null ) {
							
								saveEntity(autorizacaoEspecialSG);							
								//System.out.println(" autorizacaoEspecialSG salva com sucesso!");
								
							}
														
			    			garantia.setGarantiaCode(garantia.getEntityId().toString());
			    			saveEntity(garantia);
			    			
			    			if(garantia.getCausaProblemaGarantia()!=null) {
				    			CausaProblemaGarantia causaProblemaGarantia = garantia.getCausaProblemaGarantia();
				    			causaProblemaGarantia.setGarantia(garantia);
				    			saveEntity(causaProblemaGarantia);
			    			}
			    			
			    			if(garantia.getCondicaoProblemaGarantia() != null) {
				    			CondicaoProblemaGarantia condicaoProblemaGarantia = garantia.getCondicaoProblemaGarantia();
				    			condicaoProblemaGarantia.setGarantia(garantia);
				    			saveEntity(condicaoProblemaGarantia);
			    			}
			    			
			    			if(garantia.getDiagnosticoProblemaGarantia() != null) {
				    			DiagnosticoProblemaGarantia diagnosticoProblemaGarantia = garantia.getDiagnosticoProblemaGarantia();
				    			diagnosticoProblemaGarantia.setGarantia(garantia);
				    			saveEntity(diagnosticoProblemaGarantia);
			    			}
			    			
			    			if(garantia.getSolucaoProblemaGarantia() != null) {
				    			SolucaoProblemaGarantia solucaoProblemaGarantia = garantia.getSolucaoProblemaGarantia();
				    			solucaoProblemaGarantia.setGarantia(garantia);
				    			saveEntity(solucaoProblemaGarantia);
			    			}
			    			
			    			//System.out.println(" Garantia atualizada com sucesso!");
			    			
			    			if ( !isEditFinal ) {
			    				
			    				// Iteramos pela coleção de peças e salvamos
			    					Iterator it = listGarantiaLine.iterator();
			    					
			    				while ( it.hasNext() ) {

			    					GarantiaLine garantiaLine = ( GarantiaLine ) it.next();

			    					if ( garantiaLine.getCompositeEntityId().getGarantiaId() == null ) 
			    						garantiaLine.getCompositeEntityId().setGarantiaId(garantia.getEntityId());

			    					saveEntity(garantiaLine);

			    					//System.out.println(" Peça salva com sucesso!");
			    				}
			    			}
			    			
			    			if ( !isEditFinal ) {
				    			if(infoMercadoFinal != null) {
				    				
				    				// Salvamos a relação entre garantia e Informação de Mercado
	    							InfoMercGarantia infoMercGarantia = new InfoMercGarantia();
	    							infoMercGarantia.setInfoMercado(infoMercadoFinal);
	    							infoMercGarantia.setGarantia(garantia);
	    							infoMercGarantia.setDataCriacao( new Date() );
	    							infoMercGarantia.setCriadoPor( garantia.getCriadoPor() );
	    							infoMercGarantia.setDataAtualizacao( new Date() );
	    							infoMercGarantia.setAtualizadoPor( garantia.getAtualizadoPor() );
	    							saveEntity(infoMercGarantia);				    				
				    			}
			    			}
			    			
			    			alert = new AlertGarantia();
							alert.setAlertGarantiaKey("garantia.msg.success.saved");
							
							//System.out.println(" Peças salvas com sucesso!");
				
						} catch ( BusinessException bExp ) {
							
							ts.setRollbackOnly();
							alert = new AlertGarantia();
							alert.setAlertGarantiaKey("garantia.form.msg.error.notSave");
							alert.setAlertGarantiaText("A operação não pode ser realizada! Problema:" + bExp.getMessage());
							
							System.out.println(" ----------> Rollback - Erro: " + bExp.getMessage());
							
						} 
						
						return alert;
        
	                }
	                
	            }
	                
	        );
	        
	        return alerta;		

	} 	
	
	/** Realiza as validações de uma garantia de campanha.
	 * 
	 *  @param campaignGarantiaTO
	 *      <code>CampaignGarantiaTO</code> representando a entidade a ser
	 *      criada/atualizada na camada de persistência.
	 *      
	 *  @param usuario
	 *  	<code>Usuario</code> Entidade de Usuario
	 *  
	 *  @return GarantiaTO
	 *  	Entidade de <code>GarantiaTO</code> representando as informações das validações realizadas    
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */    
	public GarantiaTO validationCampaign(GarantiaTO garantiaTO, Usuario usuario, boolean isEdit) throws BusinessException {
		
	                    
        	List alerts = new ArrayList();
        	AlertGarantia alert = null;
        	boolean isSaved = true;
        	
			try {							
				
				GarantiaHeader garantia  = garantiaTO.getGarantia();
				Campaign       campaign  = garantiaTO.getCampaign();
				Lote           lote      = garantia.getLote();
				
				Faturamento faturamento = (Faturamento)garantiaHeaderDao.getByParameter(Faturamento.class, "chassi", garantia.getModelo());
				
				lote        = (Lote)garantiaHeaderDao.getByParameter(Lote.class, "entityId", lote.getEntityId());
				//campaign    = (Campaign)garantiaHeaderDao.getByParameter(Campaign.class, "entityId", campaign.getEntityId());
				
				
				if ( campaign != null ) {
					
					if ( !campaign.isValidModel(garantia.getModelo()) ) {
						alert = AlertGarantia.getAlertGarantia("O produto não faz parte desta campanha!",AlertGarantia.SEVERIDADE_ERROR,"garantia.msg.error.notCampaign",garantia.getModelo());
						alerts.add(alert);	
						isSaved = false;
					} 
				
					// Se a linha do chassi informado é mesma do lote selecionado
					if ( !faturamento.getLinha().getEntityId().equals(lote.getLinha().getEntityId()) ) { 
						alert = AlertGarantia.getAlertGarantia("A linha do produto não confere coma linha do Lote!",AlertGarantia.SEVERIDADE_ERROR,"garantia.msg.error.invalidLine", lote.getLinha().getDescricao());
						alerts.add(alert);
						isSaved = false;
					}
					
					/*
					// Verificamos se já existe um cadastro do produto para a campanha					
					List campaigns = (ArrayList)garantiaHeaderDao.listByParameter(CampaignBilling.class, "billing", faturamento);
					
					boolean isCampaign = false;
					Iterator it = campaigns.iterator();
					
					while ( it.hasNext() ) {
						
						CampaignBilling campaignBilling = (CampaignBilling) it.next();
						
						if ( campaignBilling.getCampaign().getEntityId().equals(campaign.getEntityId()) )
							isCampaign = true;
						
					} 					
					if ( isCampaign ) {
						alert = AlertGarantia.getAlertGarantia("Este produto já participou da Campanha!",AlertGarantia.SEVERIDADE_ERROR,"garantia.msg.error.campaign.notValid", campaign.getCodeCampaign());
						alerts.add(alert);
						isSaved = false;
					} 
					*/
					
					// Verificamos se o chassi faturado é da mesma concessionária do usuário
			    	if (!usuario.compareIdAtribute("concessionaria",faturamento.getConcessionaria().getEntityId())){
			    							    		
			    		alert = AlertGarantia.getAlertGarantia("Chassi faturado para outra Concessionária!",AlertGarantia.SEVERIDADE_WARNING,"garantia.msg.warning.chassi" , garantia.getModelo());
			    		alerts.add(alert);
			    		
			    		if ( !usuario.getConcessionaria().getUf().equalsIgnoreCase(faturamento.getConcessionaria().getUf()) ) {
			    			
			    			alert = AlertGarantia.getAlertGarantia("Chassi Faturado para outro Estado!",AlertGarantia.SEVERIDADE_WARNING,"garantia.msg.warning.estado.chassi",garantia.getModelo());
				    		alerts.add(alert);

			    		} 
			    		
			    	}
			    	
			    	// **************************************************************** //
			    	// * Validação de restrição do chassi 							  * //
			    	// **************************************************************** //				
			    	if ( isRestriction(garantia.getModelo()) ) {
						
			    		alert = AlertGarantia.getAlertGarantia("O Produto apresenta restrição!",AlertGarantia.SEVERIDADE_ERROR,"garantia.form.msg.error.notGarantia");
			    		alerts.add(alert);
			    		isSaved = false;
			    	}
			    						    	
			    	// Calculando o número de dias entre a revisão de entrega ou da venda com o dia da abertura da SG
			    	garantia.setDiasUso(getDiasUso(garantia.getModelo(), faturamento.getDataNotaFiscal(), garantia.getDataAberturaOS()));			    	
			    	garantia.setCondicao(campaign.getCondition());
			    	garantia.setSintoma(campaign.getSintoma());
			    	
			    	// Valores temporários, pois os campos estão como obrigatórios e não estão especificados no MD 50		    	
			    	garantia.setGarantiaCode("-");
			    	garantia.setRecusaId(new Long(-1));
			    	garantia.setOrganizationId((Long)faturamento.getEmpresa().getEntityId());			    	
				    garantia.setLote(lote);
				    
			    	// **************************************************************** //
					// *           Validação da existência de uma Garantia com as     * //
					// *           mesmas informações de:							  * //
					// *           chassi, data de abertura da OS, Número da OS, 	  * //
					// * 		   KM (para linha MOTOCICLETA) e dias de uso (para    * //
					// *           linha de Náutica)								  * //
					// **************************************************************** //							
					if ( isEcxistsGarantia(garantia) && garantia.isNew() && garantia.getEntityId() != new Integer(0) ) {
						
						alert = AlertGarantia.getAlertGarantia("Já existe uma Garantia com essas características para este produto!",AlertGarantia.SEVERIDADE_ERROR,"garantia.msg.error.isEcxists");
			    		alerts.add(alert);
			    		isSaved = false;
					}
					
					// Validar prazo de envio da Garantia após fechamento da OS
		    		// O prazo é parametrizado e deve ser recuperado da table de parâmetros do sistema
					if(!Linha.TIPO_MOTOCICLETA.equals(faturamento.getLinha().getEntityId())) {
						alert = validatePrazoEnvio(garantia, usuario);
						if ( alert != null ) {
							alerts.add(alert);
							isSaved = false;
						}
					}
					
					//System.out.println("Campanha - Valor limite Terceiro:" + campaign.getServiceValue());
					// Validar o valor de terceiros
					if ( campaign.getServiceValue() < garantia.getValorServicoTerceiro()) {
					
						alert = AlertGarantia.getAlertGarantia("Valor serviço de terceiro excede o limite!!",AlertGarantia.SEVERIDADE_ERROR,"garantia.msg.warning.valor.terceiro", String.valueOf(garantia.getValorServicoTerceiro()));
						alerts.add(alert);
						isSaved = false;
					}
					
				} else {
					
					alert = AlertGarantia.getAlertGarantia("Não foi localizada a campanha!",AlertGarantia.SEVERIDADE_ERROR,"garantia.msg.error.campaign.notLocalized");
		    		alerts.add(alert);
		    		isSaved = false;
					
				}
				
				// Se não existir erro, podemos preparar as entidades para gravar
				if ( isSaved ) {
					
					//***********************************************************************//
					// Este trecho calcula o valor de cada serviço da campanaha				*//
					// 1. Pegamos o código da região										*//
					// 2. Percorremos a lista serviços da campanha							*//
					// 3. Recuperamos o tempo padrão e o valor para cada serviço			*//
					// 4. Realizamos o calculo do valor do serviços e populamos os objetos	*//
					//***********************************************************************//
					Long 		 codRegiao = usuario.getConcessionaria().getRegiao(lote.getLinha());
					
					TempoPadrao  tempo     	  = null;
					ValorServico valorServico = null;
					ServicoGrupo servicoGrupo = null;
					if ( codRegiao != null ) {
					
	    				// Vamos percorrer os serviços da campanha									
						Iterator itSv = campaign.getServices().iterator();
						
						while ( itSv.hasNext() ) {
							
							Servico servico = (Servico) itSv.next();
							servicoGrupo = new ServicoGrupo();	
							if(Linha.TIPO_MOTOCICLETA.equals(garantia.getLote().getLinha().getEntityId())){
								
								//Se for uma nova garantia, apensa calculamos e adicionamos o servico
								if ( garantia.isNew() || garantia.getEntityId().equals(new Integer(0))) {
											
									servicoGrupo.setValorServico(NumberUtils.round(campaign.getServiceValue(),2));
									servicoGrupo.setValorBonificacao(new Double(0.0));
									servicoGrupo.setValorServicoBasico(new Double(NumberUtils.round(campaign.getServiceValue(),2)));
									servicoGrupo.setServico(servico);
									ControllerHelper.prepare(servicoGrupo, (Long)usuario.getEntityId());
									garantia.addServicoGrupo(servicoGrupo);
									servicoGrupo.setGarantia(garantia);
									
								} else {
									// Quando for alteração apenas alteramos o serviço da garantia
									garantia.getServico(servico.getServicoCode()).setValorServico(NumberUtils.round(campaign.getServiceValue(),2));
									ControllerHelper.prepare(garantia.getServico(servico.getServicoCode()), (Long)usuario.getEntityId());
									
								}
								
							} else {
								// recuperamos o tempo padrão para o Serviço
								tempo = (TempoPadrao)getTempoPadrao(servico, garantia.getModelo());
								if ( tempo == null ) {
									
									alert = AlertGarantia.getAlertGarantia("Não foi localizado o tempo padrão para o serviço!",AlertGarantia.SEVERIDADE_ERROR,"garantia.msg.error.campaign.notTempoPadrao", servico.getServicoCode());
									//System.out.println("Alerta?" + (alert != null));
									alerts.add(alert);
									
								} else {
									
									// Recuperamos o valor para o servço 
									valorServico = (ValorServico)getValorServico(codRegiao, garantia.getModelo());
									if ( valorServico == null ) {
										
										alert = AlertGarantia.getAlertGarantia("Não foi localizado O valor para o serviço !",AlertGarantia.SEVERIDADE_ERROR,"garantia.msg.error.campaign.notValorServico", servico.getServicoCode());
										alerts.add(alert);
										
									} else {
										
										double valorServicoNum = tempo.getTempo() * valorServico.getPriValorMaoObra();
										
										//Se for uma nova garantia, apensa calculamos e adicionamos o servico
										if ( garantia.isNew() || garantia.getEntityId().equals(new Integer(0))) {
										
											servicoGrupo.setValorServico(NumberUtils.round(valorServicoNum,2));
											servicoGrupo.setServico(servico);
											ControllerHelper.prepare(servicoGrupo, (Long)usuario.getEntityId());
											garantia.addServicoGrupo(servicoGrupo);
											servicoGrupo.setGarantia(garantia);
											
										} else {
											// Quando for alteração apenas alteramos o serviço da garantia
											garantia.getServico(servico.getServicoCode()).setValorServico(NumberUtils.round(valorServicoNum,2));
											ControllerHelper.prepare(garantia.getServico(servico.getServicoCode()), (Long)usuario.getEntityId());
											
										}
									}
								} 
							}
						}
						
						// **************************************************************** //
				    	// * Atribuição do status para a Garantia	 	    			  * //
						// **************************************************************** //
				    	StatusGarantia statusGarantia = new StatusGarantia();
				    	statusGarantia.setEntityId(StatusGarantia.STATUS_AGUARDANDO_DIGITACAO_NOTA);
					    garantia.setStatusGarantia(statusGarantia);		
					    					    					    
						ControllerHelper.prepare(garantia, (Long)usuario.getEntityId());
						
						garantiaTO.setGarantia(garantia);
						garantiaTO.setCampaign(campaign);
						
						//***********************************************************************************//
						// Neste trecho vamos calcular o valor das peças									*//
						// 1. Percorremos as peças da campanha												*//
						// 2. Recuperamos a tabela de preço													*//
						// 3. Montamos o objeto garantiaLine para cada peça									*//
						//***********************************************************************************//		
						try {
							garantiaTO.setListGarantiaLine( createGarantiaLineByCampaign( campaign, usuario, garantia, isEdit ) );
						} catch ( BusinessException bExp ) {
							
							alert = AlertGarantia.getAlertGarantia("Erro na localização da Peças!",AlertGarantia.SEVERIDADE_ERROR,bExp.getMessage());							
							alerts.add(alert);
							
						}
						
					} else {
						
						alert = AlertGarantia.getAlertGarantia("Não foi localizado o Código da revisão da Concessionária!",AlertGarantia.SEVERIDADE_ERROR,"garantia.msg.error.regiao");
			    		alerts.add(alert);
						
					}
					
				}
				
				
				
			} catch ( DaoException bExp ) {
				
				alert = AlertGarantia.getAlertGarantia(bExp.getMessage() ,AlertGarantia.SEVERIDADE_ERROR,"garantia.form.msg.error.notSave");							
				alerts.add(alert);
				
			} catch ( BusinessException bExp ) {
				
				alert = AlertGarantia.getAlertGarantia(bExp.getMessage(),AlertGarantia.SEVERIDADE_ERROR,"garantia.form.msg.error.notSave");							
				alerts.add(alert);
				
			}
			
			garantiaTO.setListAlertas(alerts);
			
	        return garantiaTO;		

	} 
	
	/** 
	 *  Retorna uma lista de entidades de GarantiaLine para uma campanha
	 *  Neste trecho vamos calcular o valor das peças
	 *  Recuperamos a tabela de preço
	 *  Montamos o objeto garantiaLine para cada peça	
	 *   
	 * @param campaign
	 * 
	 * @param usuario
	 * 
	 * @return List  
	 * 	Uma coleção de entidades de GarantiaLine
	 * 
	 * @throws BusinessException
	 */
	public List createGarantiaLineByCampaign( Campaign campaign, Usuario usuario, GarantiaHeader garantia, boolean isEdit ) throws BusinessException {
		
		List results = new ArrayList();
		Iterator itPeca = campaign.getPieces().iterator();
		int isCausing = 1;
		int noCausing = 2;
		
		try {
			
			while ( itPeca.hasNext() ) {
				
				CampaignPiece campaignPiece = (CampaignPiece) itPeca.next();
				//log.info( "Obtendo tabela de preços..." );
				
				TabelaPrecoId tabelaPrecoId = new TabelaPrecoId(new Long(91), campaignPiece.getPieceId());
				TabelaPreco   tabelaPreco   = (TabelaPreco) this.getGarantiaHeaderDao().getByParameter(TabelaPreco.class, "compositeEntityId", tabelaPrecoId);
				
				if ( tabelaPreco == null ) {
					throw new BusinessException("garantia.msg.error.tabela.preco.notFound");					
				}
				
				//Recuperamos o Fator de Garantia pelo estado da concessionária
				FatorGarantia fatorGarantia = (FatorGarantia)this.getGarantiaHeaderDao().getByParameter(FatorGarantia.class, "estado", usuario.getConcessionaria().getUf());
				
				if ( fatorGarantia == null )
					throw new BusinessException("Não foi localizado o fator de garantia para o estado de  " + usuario.getConcessionaria().getEstado()); 
							
				//log.info( "Fator de Garantia:" + (fatorGarantia != null ? fatorGarantia.getEstado() : "NULL") );
				//Para criar o objeto necessitamos controla o lineId para determinar a peça causadora
				GarantiaLine garantiaLine = null;
				
				//System.out.println("IsEdit:" + isEdit);
				
				if ( !isEdit )
					garantiaLine = new GarantiaLine();
				
				// Quando existir apenas uma peça ela será a peça causadora
				// Quandor for mais de uma peça, verificamos a propriedade CAUSING_PIECE
				if ( campaign.getPieces().size() == 1 ) {
					if ( isEdit ) {
						GarantiaLineId garantiaLineId = new GarantiaLineId(garantia.getEntityId(),new Long(isCausing));
						garantiaLine = garantia.getGarantiaLine(garantiaLineId);
					} else
						garantiaLine.setCompositeEntityId( null, new Long(isCausing) );
				
				} else {
					
					if ( CampaignPiece.VALUE_TRUE.equalsIgnoreCase(campaignPiece.getCausingPiece()) ) {
						
						if ( isEdit ) {
							//System.out.println("isCausing:" + isCausing + "- Garantia:" + garantia.getEntityId());
							GarantiaLineId garantiaLineId = new GarantiaLineId(garantia.getEntityId(),new Long(isCausing));
							garantiaLine = garantia.getGarantiaLine(garantiaLineId);
						} else
							garantiaLine.setCompositeEntityId( null, new Long(isCausing) );
					
					} else {
						if ( isEdit ) {
							//System.out.println("noCausing:" + noCausing + "- Garantia:" + garantia.getEntityId());
							GarantiaLineId garantiaLineId = new GarantiaLineId(garantia.getEntityId(),new Long(noCausing++));
							garantiaLine = garantia.getGarantiaLine(garantiaLineId);
						} else
							garantiaLine.setCompositeEntityId( null, new Long(noCausing++) );
						
					}
				}
				
				//System.out.println("garantiaLine is not null:" + (garantiaLine.getLineId() != null));
				//System.out.println("campaignPiece is not null:" + (campaignPiece.getQuantityPiece() != null));
				
				garantiaLine.setQuantidade(  campaignPiece.getQuantityPiece() 	);
				garantiaLine.setItem( 		 campaignPiece.getPiece() 			);
				garantiaLine.setEnviaPeca( 	 campaignPiece.getSendPiece() 		);
				garantiaLine.setCobraPeca( 	 campaignPiece.getRecoveredPiece() 	);
				garantiaLine.setPecaFaltante("N");
				
				if ( fatorGarantia == null ) {		
					
					//log.info( "Fator de Garantia não encontrado." );
					garantiaLine.setFatorGarantiaId( new Long(55) );
					garantiaLine.setValorPecaGarantia( tabelaPreco.getPrecoGarantia() * 1.33D );
					
				} else {
					
					garantiaLine.setFatorGarantiaId( (Long)fatorGarantia.getEntityId() );
					garantiaLine.setValorPecaGarantia( 
														NumberUtils.round( 
																			(tabelaPreco.getPrecoUnitario() * fatorGarantia.getFatorGarantia())
																			, 2
																		 ) 
													 );
					
				}
				
				garantiaLine.setValorPrecoFob(  	tabelaPreco.getPrecoFOB() 					);
				garantiaLine.setValorCustoPadraoSP( tabelaPreco.getPrecoUnitario() 				);
				garantiaLine.setValorPrecoSugerido( tabelaPreco.getPrecoPublico() 				);
				ControllerHelper.prepare( 			garantiaLine, (Long)usuario.getEntityId() 	);	
				garantiaLine.setCriadoPor((Long)usuario.getEntityId());
				garantiaLine.setDataCriacao(new Date());
				
				results.add(garantiaLine);
				
			}
			
		} catch (DaoException dExp) {
			
			throw new BusinessException( dExp );
			
		}
		
		return results;
	}
	
	/** Recupera um entidade de CampaignBilling de um Garantia
	 * 
	 * @param garantia
	 * 	<code>GarantiaHeader</code> Entidade de GarantiaHeader
	 * 
	 * @return campaignBilling
	 * 	<code>CampaignBilling</code> Quando a garantia for de uma campanha
	 * 
	 * @throws BusinessException
	 */
	public CampaignBilling getCampaignBillingByGarantia(GarantiaHeader garantia) throws BusinessException {
		
		try {
			
			return this.garantiaHeaderDao.getCampaignBillingByGarantia(garantia);
			
		} catch (DaoException dExp) {
			throw new BusinessException( dExp );
		}
		
	}
	
	/** Remove uma entidade do banco de dados.
	 * 
	 *  @param entityId
	 *  @throws BusinessException
	 */
	public void remove(Serializable entityId) throws BusinessException {
		try {

			GarantiaHeader garantiaHeader = get( entityId );

			if ( garantiaHeader.getLote().getStatusLote().getEntityId() == new Long(1) ) {

				// Quando o ID do statusLote for 1, é permitida
				// a remoção física dos dados.
				this.getGarantiaHeaderDao().makeTransient( garantiaHeader );

			} else {

				this.getGarantiaHeaderDao().makePersistent( garantiaHeader );

			}

		} catch (DaoException dExp) {
			throw new BusinessException( dExp );
		}
	}

	/** Remove uma Solicitação de Garantia do banco de dados.
	 * 
	 *  @param garatnia
	 *  @throws BusinessException
	 */
	public void remove(GarantiaHeader garantia) throws BusinessException {

		try {		

				this.getGarantiaHeaderDao().remove(garantia);


		} catch (DaoException dExp) {
			throw new BusinessException( dExp );
		}

	}  
	
	/** Remove uma Solicitação de Garantia do banco de dados.
	 * 
	 *  @param garatnia
	 *  @throws BusinessException
	 */
	public void remove(GarantiaHeader garantia, Usuario usuario) throws BusinessException {

		try {		

				this.getGarantiaHeaderDao().remove(garantia,usuario);


		} catch (DaoException dExp) {
			throw new BusinessException( dExp );
		}

	} 

	/** Recupera um entidade de InfoMercGarantia de um Garantia
	 * 
	 * @param garantia
	 * 	<code>InfoMercGarantia</code> populado com a Garantia e InfoMercado associada
	 * 
	 * @return InfoMercGarantia
	 * 	<code>InfoMercGarantia</code> Quando a garantia for de uma InfoMercGarantia
	 * 
	 * @throws DaoException
	 */
	public InfoMercGarantia getInfoMercGarantia( InfoMercGarantia infoMercGarantia ) throws BusinessException {

		try {		

				return this.getGarantiaHeaderDao().getInfoMercGarantia(infoMercGarantia);

		} catch (DaoException dExp) {
			throw new BusinessException( dExp );
		}

	} 
	
	
	/** Remove um objeto da camada de persistência da aplicação.
     * 
     *  @param baseClass
     *      <code>Class</code>. Tipo de classe a ser removida pelo método.
     *  
     *  @param entity
     *      <code>EntityObject<code>
     *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */    
	public void removeEntity(EntityObject entity) throws BusinessException {
		
		try {		

			this.getGarantiaHeaderDao().remove(entity);


		} catch (DaoException dExp) {
			throw new BusinessException( dExp );
		}		
		
	}
	
	/** Remove um objeto da camada de persistência da aplicação.
     * 
     *  @param baseClass
     *      <code>Class</code>. Tipo de classe a ser removida pelo método.
     *  
     *  @param entity
     *      <code>EntityObject<code>
     *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */    
	public void makeTransient(EntityObject entity) throws BusinessException {
		
		try {		

			this.getGarantiaHeaderDao().makeTransient(entity);


		} catch (DaoException dExp) {
			throw new BusinessException( dExp );
		}		
		
	}

	/**
	 * Recebe a lista de garantias que foram lançadas as notas e altera seu status,
	 * Verifica se existem garantias sem lançamento de notas para alterar o status do lote
	 * 
	 * @param garantias
	 *            <code>Collection</code> Lista de cupons para verificação.
	 *            
	 * @param usuario
	 * 			  <code>Usuario</code>  Entidade do usuário logado.
	 * 
	 * @throws BusinessException
	 *             Se houverem erros de execução ou nas camadas abaixo dos
	 *             serviços, serão encapsulados neste tipo de
	 *             <code>Exception</code>.
	 */
	public void updateStatus(Collection garantias, Usuario usuario) throws BusinessException {

		try {

			StatusLote statusLote = (StatusLote)garantiaHeaderDao.get(StatusLote.class, StatusLote.STATUS_EM_DIGITACAO);
			Lote lote = null;
			
			StatusGarantia statusGarantia = new StatusGarantia();
			statusGarantia.setEntityId(StatusGarantia.STATUS_NF_DIGITADA);
			
			Iterator iter = garantias.iterator();
			while ( iter.hasNext() ) {
				
				GarantiaHeader garantia = (GarantiaHeader)iter.next();
				
				lote = (Lote) garantia.getLote();
				
				garantia.setStatusGarantia(statusGarantia);
				
				this.saveEntity(garantia);

			}
			
			if ( lote != null ) {
				
				// Se o Status do Lote for diferente de "EM DIGITAÇÃO",
				// setamos para esse status
				if ( !StatusLote.STATUS_EM_DIGITACAO.equals(lote.getStatusLote().getEntityId()) ) {
					lote.setStatusLote(statusLote);
					lote.setDataAtualizacao(new Date());
					lote.setAtualizadoPor((Long)usuario.getEntityId());    			   
					loteBO.alterState(lote);
				}
				
			}
			
		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}

	}    

	/** Obtém uma entidade de parametro do sistema a partir de um nome de parâmetro passado.
	 * 
	 *  @param parameterName
	 *      <code>Serializable<code>. Nome do parâmetro(Coluna) pelo qual o objeto
	 *      deverá ser pesquisado na camada de persistência da aplicação.

	 *  @return
	 *      Um <code>ParametroSistema</code> populado com os dados da 
	 *      entidade específica, solicitada, ou caso nenhuma entidade
	 *      seja encontrada, retornará <code>null</code>.
	 *      
	 *  @throws DaoException
	 *      Se houverem problemas nas camadas inferiores, estes serão 
	 *      convertidos para uma DaoException.
	 */     
	public ParametroSistema getByParameterSystem(String parameterName) throws BusinessException {

		try {

			return (ParametroSistema) garantiaHeaderDao.getByParameter(ParametroSistema.class, "nomeParametro", parameterName);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}    	

	}

	/** Obtém uma entidade de Servico a partir de seu ID.
	 * 
	 *  @param entityId
	 *      <code>Long<code>. Id da entidade.
	 *      	    
	 *  @return
	 *      Um <code>Servico</code> populado com os dados da 
	 *      entidade específica, solicitada, ou caso nenhuma entidade
	 *      seja encontrada, retornará <code>null</code>.
	 *      
	 *  @throws BusinessException
	 *      Se houverem problemas nas camadas inferiores, estes serão 
	 *      convertidos para uma BusinessException.
	 */     
	public Servico getServicoById(Long servicoId) throws BusinessException {

		try {

			return (Servico) garantiaHeaderDao.getServicoById(servicoId);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}    	

	}
	/** Obtém uma entidade de Servico a partir de seu servicoCode.
	 * 
	 *  @param servicoCode
	 *      <code>String<code>. Código do Serviço.
	 *      
	 *  @param linha
	 *      <code>Linha<code> linha do produto relacionada.
	 *      	    
	 *  @return
	 *      Um <code>Servico</code> populado com os dados da 
	 *      entidade específica, solicitada, ou caso nenhuma entidade
	 *      seja encontrada, retornará <code>null</code>.
	 *      
	 *  @throws DaoException
	 *      Se houverem problemas nas camadas inferiores, estes serão 
	 *      convertidos para uma DaoException.
	 */     
	public Servico getServicoByCode(String servicoCode, Linha linha) throws BusinessException {

		try {

			return (Servico) garantiaHeaderDao.getServicoByCode(servicoCode.toUpperCase(), linha);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}    	

	}
	
	/** Obtém uma entidade de GarantiaLine a partir de seu id composto e da empresa do faturamento.
	 * 
	 *  @param garantiaLineId
	 *      <code>GarantiaLineId<code>. Entidade com o id composto.
	 *      
	 *  @param organizationId
	 *      <code>Long<code> id da empresa do faturamento.
	 *      	    
	 *  @return
	 *      Um <code>GarantiaLineId</code> populado com os dados da 
	 *      entidade específica, solicitada, ou caso nenhuma entidade
	 *      seja encontrada, retornará <code>null</code>.
	 *      
	 *  @throws DaoException
	 *      Se houverem problemas nas camadas inferiores, estes serão 
	 *      convertidos para uma DaoException.
	 */     
	public GarantiaLine getGarantiaLine(GarantiaLineId garantiaLineId, Long organizationId) throws BusinessException {

		try {

			return (GarantiaLine) garantiaHeaderDao.getGarantiaLine(garantiaLineId, organizationId);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}    	

	}
	
	/** Recupera uma Entidade de serviço pelo código e um determinado chassi.
	 *
	 *  @param code
	 *      <code>String<code> Valor do código do serviço.
	 *      
	 *  @param chassi
	 *      <code>String<code> chassi.
	 *      
	 *  @param linha
	 *      <code>Linha<code> linha do produto.
	 *  
	 *  @return
	 *      Um <code>Servico</code> entidade de Servico.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */	
	public Servico getServiceByChassi(String code, String chassi, Linha linha) throws BusinessException {

		try {

			return (Servico) garantiaHeaderDao.getServiceByChassi(code, chassi, linha);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}    	

	}

	/** Verifica se o chassi informado possui restrição gravado no banco de dados.
	 * 
	 *  @param chassi
	 *      <code>String<code>.Chassi.

	 *  @return
	 *      Um <code>boolean</code> indicando com true que existe restrição.
	 *      
	 *  @throws DaoException
	 *      Se houverem problemas nas camadas inferiores, estes serão 
	 *      convertidos para uma DaoException.
	 */     
	public boolean isRestriction(String chassi) throws BusinessException {

		boolean isRest = false;

		try {

			Item item = (Item) garantiaHeaderDao.getByParameter(Item.class, "itemCode", chassi);

			if ( item != null )
				isRest = "S".equalsIgnoreCase(item.getBloqueiaGarantia()) ? true : false;	        		

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}    		    	

		return isRest;
	}
	
	/** Retorna uma entidade de Recall pelo número da informação técnica.
	 * 
	 *  @param recallId
	 *      <code>Long<code> id do Recall.
	 *      	    
	 *  @return
	 *      Uma <code>Recall</code>.
	 *      
	 *  @throws DaoException
	 *      Se houverem problemas nas camadas inferiores, estes serão 
	 *      convertidos para uma DaoException.
	 */     
	public Recall getRecall(Long recallId) throws BusinessException {
		
		try {

			return (Recall)garantiaHeaderDao.get(Recall.class, recallId);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}   
	}

	/** Retorna uma lista de Recall pelo chassi informado.
	 *  O chassi informado deve estar dentro da faixa estipulada.
	 * 
	 *  @param chassi
	 *      <code>String<code>.Chassi.
	 *      	    
	 *  @return
	 *      Uma <code>List</code>.
	 *      
	 *  @throws DaoException
	 *      Se houverem problemas nas camadas inferiores, estes serão 
	 *      convertidos para uma DaoException.
	 */     
	public List listRecall(String chassi) throws BusinessException {

		try {

			return garantiaHeaderDao.listRecall(chassi);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}   
	}
	
	/** Obtém uma lista de  Recall não executados a partir do chassi.
	 * 
	 *  @param chassi
	 *      <code>String<code>     
	 *
	 *  @return
	 *      Um <code>List</code> populado com os objetos da 
	 *      entidade específica, solicitada, ou caso nenhuma entidade
	 *      seja encontrada, retornará <code>null</code>.
	 *      
	 *  @throws DaoException
	 *      Se houverem problemas nas camadas inferiores, estes serão 
	 *      convertidos para uma DaoException.
	 */     
	public List listRecallNotExByChassi(String chassi) throws BusinessException {

		try {

			return garantiaHeaderDao.listRecallNotExByChassi(chassi);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}   
	}

	/** Retorna uma lista de condições de uma Linha de produto..
	 *	    
	 *
	 *  @param linha
	 *  	<code>Linha<code> Entidade que representa a linha do produto. 
	 *  
	 *  @return
	 *      <code>List</code> de Condições.
	 *      
	 *  @throws DaoException
	 *      Se houverem problemas nas camadas inferiores, estes serão 
	 *      convertidos para uma DaoException.
	 */     
	public List listCondicoes( Linha linha ) throws BusinessException {

		try {

			return  garantiaHeaderDao.listByParameter(Condicao.class, "linha", linha, "condicaoCode", "asc");

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}  

	} 
	
	/** Retorna uma lista de serviços de uma Linha de produto..
	 *	    
	 *
	 *  @param linha
	 *  	<code>Linha<code> Entidade que representa a linha do produto. 
	 *  
	 *  @return
	 *      <code>List</code> de Serviços.
	 *      
	 *  @throws DaoException
	 *      Se houverem problemas nas camadas inferiores, estes serão 
	 *      convertidos para uma DaoException.
	 */     
	public List listServicos( Linha linha ) throws BusinessException {

		try {

			return  garantiaHeaderDao.listByParameter(Servico.class, "linha", linha, "servicoCode", "asc");

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}  

	}  

	/** Retorna uma lista de Sintomas de uma Linha de produto.
	 * 
	 *  @param linha
	 *  	<code>Linha<code> Entidade que representa a linha do produto. 
	 *	         
	 *  @return
	 *      <code>List</code> de Sintomas.
	 *      
	 *  @throws DaoException
	 *      Se houverem problemas nas camadas inferiores, estes serão 
	 *      convertidos para uma DaoException.
	 */     
	public List listSintomas( Linha linha ) throws BusinessException {

		try {

			return  garantiaHeaderDao.listByParameter(Sintoma.class, "linha", linha, "sintomaCode", "asc");

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}  
	}
	
	/** Retorna o número de dias entre a venda e a abertura da SG.	      
	 * 
	 * @param chassi
	 * 	<code>String</code> chassi.
	 * 
	 * @param dataNF
	 * 	<code>Date</code> Data da nota fiscal do faturamento para a concessionaria.
	 * 
	 * @param dataAberturaOS
	 * 	<code>Date</code> Data da nota fiscal do faturamento para a concessionaria.
	 * 
	 * @return diasUso
	 * 	<code>Long</code> Quantidade de dias do intervalo.
	 * 
	 * @throws BusinessException
	 */
	public Long getDiasUso(String chassi, Date dataNF, Date dataAberturaOS) throws BusinessException {

		Long diasUso = new Long(0);
		
		try {
			
			Cupom  cupom = (Cupom)garantiaHeaderDao.getCupomByRevisao(chassi, new Long(1));
			Date   dataVenda = null;
			if ( cupom != null ) {

				dataVenda = cupom.getDataVenda();

			} else {
				dataVenda = dataNF;
			}

			diasUso = new Long(DateUtils.dataDiff(dataVenda, dataAberturaOS)); 
			
		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}  
		
		//System.out.println(" Dias de Uso:" + diasUso);
		
		return diasUso;

	}

	/** Verifica se foram feitas as revisões anteriores a quilometragem informada.	      
	 * 
	 * @param isConcessionaria
	 * 	<code>boolean</code>  True se o chassi foi faturado para concessionária.
	 * 
	 * @param garantia
	 * 	<code>GarantiaHeader</code> Entidade GarantiaHeader populada com os dados do form.
	 * 
	 * * @param linha
	 * 	<code>Linha</code> Entidade Linha populada com os dados do form.
	 * 
	 * @return AlertGarantia
	 * 	<code>AlertGarantia</code> Uma entidade de AlertGarantia com o alerta da validação.
	 * 
	 * @throws BusinessException
	 * @throws BusinessRuleException
	 */
	public AlertGarantia validateKmCondiz(boolean isConcessionaria, GarantiaHeader garantia, Linha linha) throws BusinessException,BusinessRuleException {

		AlertGarantia alertGarantia = null;

		try {	    		

			alertGarantia =  garantiaHeaderDao.isRevisaoAnterior(garantia, linha);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}  

		return alertGarantia;

	}

	/** Verificar se está dentro do prazo de garantia.	      
	 * 
	 * @param garantia
	 * 	<code>GarantiaHeader</code> Entidade GarantiaHeader populada com os dados do form.
	 * 
	 * @param dataNF
	 * 	<code>Date</code> Data da nota fiscal do faturamento para a concessionaria.
	 * 
	 * @return List
	 * 	<code>List</code> lista de AlertGarantia com os alertas da validação.
	 * 
	 * @throws BusinessException
	 * @throws BusinessRuleException
	 */
	public List validatePrazo(boolean isConcessionaria, GarantiaHeader garantia, Faturamento faturamento) throws BusinessException,BusinessRuleException {
		
		List alertasPrazo = new ArrayList();
		
		AlertGarantia alertGarantia = null;
		
		try {
			
			// Buscamos a revisão zero 
			Cupom  cupom = (Cupom)garantiaHeaderDao.getCupomByRevisao(garantia.getModelo(), new Long(1));
			Item   item  = (Item) itemBO.getItem(faturamento.getItemId());
			
			Date    startDate     = null;
			boolean isRevisaoZero = true; 
			boolean isLaser       = true;
			
			// Recuperamos a data Inicial para o prazo de garantia
			// Para a linha de Motocicletas a data Inicial é a data da Venda / data da revisão de entrega
			// Para a linha de Náutica a data Inicial deve ser a data de entrega, informada pelo Concessionário
			
			// Para a linha de Motocicletas, pegamos a propriedade dataVenda do cupom da revisão de entrega
			// Para a linha de Náutica, pegamos a propriedade dataEntrega do cupom da revisão de entrega
			
			// Se não foi realizada a revisão de entrega, pegamos a data da nota fiscal
			if ( cupom != null ) {
				
				// Verificamos se o produto é da linha de Motocicletas
				if ( Linha.TIPO_MOTOCICLETA.equals(garantia.getLote().getLinha().getEntityId()) ) 
					startDate = cupom.getDataVenda();
				else
					startDate = cupom.getDataEntrega() != null ? cupom.getDataEntrega() : faturamento.getDataNotaFiscal();
				
				// Se existir uma associação com um Tipo de Uso, verificamos se é laser ou comercial
				if ( cupom.getTipoUsoBarco() != null ) {
					
					isLaser = cupom.getTipoUsoBarco().getEntityId().equals(new Long(1)) ? true : false;
					
				}
				
			} else {
				
				isRevisaoZero = false;
				startDate = faturamento.getDataNotaFiscal();
			}
			
			// ********************************************************************************** //
			// *  Verificamos a linha do produto e recuperamos o parâmetro para saber o prazo   * //
			// *  de garantia.																	* //
			// ********************************************************************************** //			
			String paramName = new String();
			
			if ( Linha.TIPO_MOTOCICLETA.equals(garantia.getLote().getLinha().getEntityId()) ) { 
				
				// Precisamos recuperar o modelo do produto para determinar se é uma moto de competição ou não.
				Modelo modelo = null;
				
				if ( faturamento.getModelo() != null ) {
					
					modelo = (Modelo)this.garantiaHeaderDao.get(Modelo.class, new Long(faturamento.getModelo()));
					
				} else {
					
					alertGarantia = new AlertGarantia();
					alertGarantia.setAlertGarantiaText("Não foi localizado o modelo no faturamento do produto!");
					alertGarantia.setAlertGarantiaSeveridade(AlertGarantia.SEVERIDADE_ERROR);
					alertGarantia.setAlertGarantiaKey("garantia.msg.error.model.notFound");	
					alertasPrazo.add(alertGarantia);
					
					return alertasPrazo;
					
				}
				
				// Quando for Quadriciclo
				if ( Modelo.QUADRICICLO.equalsIgnoreCase(modelo.getGrupo()) ) {
					
					paramName = ParametroSistema.GARANTIA_MOTO_QUADRICICLO_DIAS;
					
				} else {  
				
					// Quando o modelo não possuir velocímetro, indica que é uma moto de competição
					if ( Modelo.VELOCIMETRO_NOT.equals(modelo.getVelocimetro()) ) 
						paramName = ParametroSistema.GARANTIA_MOTO_COMPETICAO_DIAS;
					else				
						paramName = ParametroSistema.GARANTIA_MOTO_DIAS;					
				}
			
			} else {				
				// Quando a for da linha Náutica e de uso para laser, verificamos a origem
				if ( isLaser ) {
					
					if ( Item.ORIGEM_NAC.equals(item.getOrigem()) ) {
						
						paramName = ParametroSistema.GARANTIA_NAUTICA_LASER_CKD_DIAS;
						
					} else {
						
						paramName = ParametroSistema.GARANTIA_NAUTICA_LASER_CBU_DIAS;
					} 
				
				// Quando o uso for comercial, verificamos se existe revisão Zero
				} else if ( isRevisaoZero ) {
					
					// Se o tipo de veículo não foi informado, lançamos um error
					if ( item.getTipoVeiculo() != null && !"".equals(item.getTipoVeiculo())) {
					
						// Verificamos o tipo de veículo
						if ( Item.TIPO_VEICULO_VA.equals(item.getTipoVeiculo()) ) {
							
							paramName = ParametroSistema.GARANTIA_NAUTICA_COMERCIAL_JET_DIAS;						
							
						} else if ( Item.TIPO_VEICULO_MP.equals(item.getTipoVeiculo()) ) {
							
							paramName = ParametroSistema.GARANTIA_NAUTICA_COMERCIAL_MP_DIAS;						
							
						}
						
					} else {
						
						alertGarantia = new AlertGarantia();
						alertGarantia.setAlertGarantiaText("Tipo de veículo não encontrado!");
						alertGarantia.setAlertGarantiaSeveridade(AlertGarantia.SEVERIDADE_ERROR);
						alertGarantia.setAlertGarantiaKey("garantia.msg.error.prazo.tipoVeiculo");	
						alertasPrazo.add(alertGarantia);
						
					}
				
				// Veículo de uso comercial e quando a revisão de zero km não foi realizada,
				// Usamos o parâmetro do MP
				} else {					
					
					paramName = ParametroSistema.GARANTIA_NAUTICA_COMERCIAL_MP_DIAS;
					
					alertGarantia = new AlertGarantia();
					alertGarantia.setAlertGarantiaText("Revisão de entrega e tipo de uso não encontrados!");
					alertGarantia.setAlertGarantiaSeveridade(AlertGarantia.SEVERIDADE_WARNING);
					alertGarantia.setAlertGarantiaKey("garantia.msg.warning.prazo.tipoUso");
					alertasPrazo.add(alertGarantia);
					
				}
				
			}
			
			// INI - sjoviano - 30-11-2015 #01
			// Agora precisa ver se é MOTO e a garantiaDif = 'S', se sim, fazer o diff e exibir a mensagem de erro
			System.out.println(" - paramName: " + paramName);
			String paramNameGarantiaDif = ParametroSistema.GARANTIA_DIFERENCIADA;
			ParametroSistema parametroGarantiaDif = this.getByParameterSystem(paramNameGarantiaDif);
			int paramDiasGarantiaDif = Integer.parseInt(parametroGarantiaDif.getValorParametro());
			//Modelo modelo = garantia.getModelo();
			Modelo modelo = (Modelo)this.garantiaHeaderDao.get(Modelo.class, new Long(faturamento.getModelo()));
			//Cupom cupomGaranDiff = (Cupom)this.garantiaHeaderDao.getCupomByRevisao(faturamento.getModelo(), new Long(1));
			Cupom  cupomGD = (Cupom)garantiaHeaderDao.getCupomByRevisao(garantia.getModelo(), new Long(1));
			//modelo.getGarantiaDif();
			Date dtHoje = new Date();
			int pula = 0;
			
			//System.out.println(" - cupom: " + cupom.toString());
			//System.out.println(" - cupomGD: " + cupomGD.toString());
			//System.out.println(" - modelo.getModelo(): " + modelo.getModelo());
			System.out.println(" - getGarantiaDif: " + modelo.getGarantiaDif());
			System.out.println(" - paramDiasGarantiaDif: " + paramDiasGarantiaDif);
			//System.out.println(" - cupom.getAlertCupomId(): " + cupomGD.getAlertCupomId());
			try {
				System.out.println(" - cupomGD.getDataVenda(): " + cupomGD.getDataVenda());
			} catch (NullPointerException e) {
				System.out.println(" - cupomGD.getDataVenda(): null");
			}
			try {
				System.out.println(" - cupom.getDataVenda(): " + cupom.getDataVenda());
			} catch (NullPointerException e) {
				System.out.println(" - cupom.getDataVenda(): null");
			}
			try {
				System.out.println(" - DateUtils.dataDiff(dtHoje, cupomGD.getDataVenda()): " + DateUtils.dataDiff(cupomGD.getDataVenda(), dtHoje));
			} catch (NullPointerException e) {
				System.out.println(" - DateUtils.dataDiff(dtHoje, cupomGD.getDataVenda()): tem null");
			}
			System.out.println(" - AlertGarantia.SEVERIDADE_WARNING");
			
			if ( Linha.TIPO_MOTOCICLETA.equals(garantia.getLote().getLinha().getEntityId()) && "S".equals(modelo.getGarantiaDif()) ) {
				
				try {
					if ( (DateUtils.dataDiff(cupomGD.getDataVenda(), dtHoje)) > paramDiasGarantiaDif && isRevisaoZero == false) {
						
						alertGarantia = new AlertGarantia();
						alertGarantia.setAlertGarantiaText("Chassi com garantia estendida fora do prazo de Garantia!");
						alertGarantia.setAlertGarantiaSeveridade(AlertGarantia.SEVERIDADE_ERROR);
						//alertGarantia.setAlertGarantiaKey("garantia.msg.warning.prazo.garantia.dif");	 
						alertGarantia.setAlertGarantiaKey("garantia.msg.error.prazo.garantia.dif");	 
						alertasPrazo.add(alertGarantia);
						
					}
				} catch (NullPointerException e) {
					
				}
				
			}
			// FIM - sjoviano - 30-11-2015 #01

			ParametroSistema parametro = this.getByParameterSystem(paramName);
			
			int paramDias = Integer.parseInt(parametro.getValorParametro());
			
			//System.out.println(" - Dta Inicial:" + startDate + " - Abertura OS:" + garantia.getDataAberturaOS() + " - Parametro:" + paramDias);
			
			//System.out.println("Diferença: " +  DateUtils.dataDiff(startDate, garantia.getDataAberturaOS()) + " - Validação:" + (DateUtils.dataDiff(startDate, garantia.getDataAberturaOS()) > paramDias));
			
			//if ( DateUtils.dataDiff(startDate, new Date()) > paramDias ) {	
			if ( (DateUtils.dataDiff(startDate, garantia.getDataAberturaOS()) > paramDias) && (!"S".equals(modelo.getGarantiaDif())) ) {
				
				System.out.println("Chassi fora do prazo de Garantia!");
				
				//if ( isConcessionaria ) {
				
					alertGarantia = new AlertGarantia();
					alertGarantia.setAlertGarantiaText("Chassi fora do prazo de Garantia!");
					alertGarantia.setAlertGarantiaSeveridade(AlertGarantia.SEVERIDADE_ERROR);
					alertGarantia.setAlertGarantiaKey("garantia.msg.error.prazo.garantia");	 
					alertasPrazo.add(alertGarantia);
				
				/*} else  {
					
					alertGarantia = new AlertGarantia();
					alertGarantia.setAlertGarantiaText("Chassi fora do prazo de Garantia!");
					alertGarantia.setAlertGarantiaSeveridade(AlertGarantia.SEVERIDADE_WARNING);
					alertGarantia.setAlertGarantiaKey("garantia.msg.warning.prazo.garantia");	 
					alertasPrazo.add(alertGarantia);					
					
				}*/
				
			}

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}
		
		//System.out.println("Alertas do prazo de garantia:" + alertasPrazo.size());
		
		return alertasPrazo;

	}

	/** Verificar o valor de terceiro informado	      
	 * 
	 * @param garantia
	 * 	<code>GarantiaHeader</code> Entidade GarantiaHeader populada com os dados do form.
	 * 
	 * @param dataNF
	 * 	<code>Date</code> Data da nota fiscal do faturamento para a concessionaria.
	 * 
	 * @return AlertGarantia
	 * 	<code>AlertGarantia</code> Uma entidade de AlertGarantia com o alerta da validação.
	 * 
	 * @throws BusinessException
	 * @throws BusinessRuleException
	 */
	public AlertGarantia validateValorTerceiro(GarantiaHeader garantia) throws BusinessException,BusinessRuleException {

		AlertGarantia alertGarantia = null;

		ParametroSistema parametroSistema = this.getByParameterSystem(ParametroSistema.VALOR_SERVICOS_TERCEIROS);
		if ( parametroSistema != null ) {

			if ( Double.parseDouble(parametroSistema.getValorParametro()) < garantia.getValorServicoTerceiro() ){

				alertGarantia = new AlertGarantia();
				alertGarantia.setAlertGarantiaText("Valor serviço de terceiro excede o limite!");
				alertGarantia.setAlertGarantiaSeveridade(AlertGarantia.SEVERIDADE_WARNING);
				alertGarantia.setAlertGarantiaKey("garantia.msg.warning.valor.terceiro");	

			}	    		

		}	    	

		return alertGarantia;

	}


	/** Método que realiza a validação da data de envio da garantia
	 *  Antes de salvar 
	 * 
	 * @param garantia
	 * 		<code>GarantiaHeader</code> Entidade populado com o valores do formulário
	 * 
	 * @param usuario
	 * 	<code>Usuario</code> Entidade do usuário logado.
	 * 
	 * @return alertCupom
	 * 		<code>AlertCupom</code> Mensagem retornada pela validação Warning/Error/Success    
	 * 
	 * @throws BusinessException
	 * 
	 * @throws BusinessRuleException
	 * 		Se ocorrer algum erro recuperando algum objeto lançamos esta exception
	 *       com uma chave de properties da mensagem de erro.
	 */
	public AlertGarantia validatePrazoEnvio(GarantiaHeader garantia, Usuario usuario) throws BusinessException,BusinessRuleException {

		AlertGarantia alertGarantia = null;

		Date openDate 	= garantia.getDataAberturaOS();
		Date closeDate  = garantia.getDataFechamentoOS();
		Date finalDate        = new Date();
		//boolean isPrazoValido = false;
		
		// Recupera a quantidade de dias úteis
		//int daysUtils = feriadoDataBO.getDaysUtils(startDate, finalDate);
		
		// Recupera a quantidade de dias corridos da data de fechamento da OS
		Integer closeDays = null;
		if(closeDate != null) {
			int clDays = feriadoDataBO.getDaysCurrence(closeDate, finalDate);			
			closeDays = new Integer(clDays);
		}
		// Recupera a quantidade de dias corridos da data de abertura da OS
		int openDays = feriadoDataBO.getDaysCurrence(openDate, finalDate);
		
		// Recuperamos o parâmetro que para envio da garantia - dias corridos 
		//ParametroSistema parametroSistema = getByParameterSystem(ParametroSistema.PRAZO_ENVIO_GARANTIA_DIAS);
		
		ParametroSistema parametroSistema 	= null;
		ParametroSistema qtdeDiasFech 		= null;
		ParametroSistema qtdeDiasAber 		= null;
		
		
		if ( Linha.TIPO_MOTOCICLETA.equals(garantia.getLote().getLinha().getEntityId()) || 
			 Linha.TIPO_QUADRICICLO.equals(garantia.getLote().getLinha().getEntityId()) ) {
		   
		    parametroSistema = getByParameterSystem(ParametroSistema.PRAZO_ENVIO_MOTO_DIAS);
		    qtdeDiasFech	 = getByParameterSystem(ParametroSistema.QTDE_DIAS_FECHAMENTO_OS_GARANTIA_MOTO);
		    qtdeDiasAber	 = getByParameterSystem(ParametroSistema.QTDE_DIAS_ABERTURA_OS_GARANTIA_MOTO);
		   
		} else if ( Linha.TIPO_NAUTICA.equals(garantia.getLote().getLinha().getEntityId()) || 
		  		  Linha.TIPO_FORCA.equals(garantia.getLote().getLinha().getEntityId()) ) {
			   
			parametroSistema = getByParameterSystem(ParametroSistema.PRAZO_ENVIO_NAUTICA_DIAS);
			qtdeDiasFech	 = getByParameterSystem(ParametroSistema.QTDE_DIAS_FECHAMENTO_OS_GARANTIA_NAUTICA);
		    qtdeDiasAber	 = getByParameterSystem(ParametroSistema.QTDE_DIAS_ABERTURA_OS_GARANTIA_NAUTICA);
		    
		} else {
			   
			throw new BusinessException("Linha do produto não  identificada!");
			   
		}
				
		if ( parametroSistema == null ) {
			
			throw new BusinessException("Não foi encontrado o parâmetro com o prazo de envio da Garantia!");			
			
		}
		int daysPrazo = Integer.parseInt(parametroSistema.getValorParametro());
		
		int qtdeAbert = Integer.parseInt(qtdeDiasAber.getValorParametro());
		int qtdeFech  = Integer.parseInt(qtdeDiasFech.getValorParametro());
		
		/*
		try {
			
			// Verificamos se prazo de envio da revisão está dentro do intervalo de 15 dias 
			if ( daysUtils > 15 ) {
				
				// Verificamos se existem históricos de atrasos cadastrados para a concessionária
				List historico = this.garantiaHeaderDao.listByParameter(HistAtraso.class, "concessionaria", usuario.getConcessionaria());
				
				if ( historico != null ) {
					
					// Quando existir histórico, verificamos se a quantidade é maior que 3
					if ( historico.size() < 4 ) {
						// Flagamos para gravar e retorna um warning
						isPrazoValido = true;					
						
					} 
					
				} else {
					// Flagamos para gravar e retornar um warning
					isPrazoValido = true;
					
				}
				
				if ( isPrazoValido ) {
					
					HistAtraso histAtraso = new HistAtraso();
					histAtraso.setConcessionaria(usuario.getConcessionaria());
					ControllerHelper.prepare(histAtraso, (Long)usuario.getEntityId());
					
					this.garantiaHeaderDao.makePersistent(histAtraso);
					
					alertGarantia = new AlertGarantia();
					alertGarantia.setAlertGarantiaText("A Data de fechamento da OS está fora do prazo envio!");
					alertGarantia.setAlertGarantiaSeveridade(AlertCupom.SEVERIDADE_WARNING);
					alertGarantia.setAlertGarantiaKey("garantia.msg.warning.prazoEnvio.garantia");					
					
				} else {
				
					alertGarantia = new AlertGarantia();
					alertGarantia.setAlertGarantiaText("A Data de fechamento da OS está fora do prazo envio!");
					alertGarantia.setAlertGarantiaSeveridade(AlertCupom.SEVERIDADE_ERROR);
					alertGarantia.setAlertGarantiaKey("garantia.msg.error.prazoEnvio.garantia");	
				}
	
			}	   
	
			return alertGarantia;
		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}  
		*/
		
		//System.out.println("Validando o prazo de envio:" + days);
		
		// Alteração realizada em 06/12/2007 - Edson
		// Quando estiver fora do prazo - sempre lançar Error
		
		// Verificamos se prazo de envio da revisão está dentro do intervalo de dias parametrizado 
		if(closeDays != null) {
			if ( closeDays.intValue() > daysPrazo ) {
				alertGarantia = new AlertGarantia();
				alertGarantia.setAlertGarantiaText("A Data de fechamento da OS está fora do prazo envio de " + daysPrazo + " dias corridos!");
				alertGarantia.setAlertGarantiaSeveridade(AlertCupom.SEVERIDADE_ERROR);
				alertGarantia.setAlertGarantiaKey("garantia.msg.error.prazoEnvio.garantia");	
			}
			
			// Verificamos quantidade de dias do fechamento é maior que o valor parametrizado
			if (  alertGarantia == null && closeDays.intValue() > qtdeFech ) {
				alertGarantia = new AlertGarantia();
				alertGarantia.setAlertGarantiaText("A Data de fechamento da OS está fora do prazo de " + qtdeFech + " dias!");
				alertGarantia.setAlertGarantiaSeveridade(AlertCupom.SEVERIDADE_ERROR);
				alertGarantia.setAlertGarantiaKey("garantia.msg.error.prazoFechamento.garantia");	
				alertGarantia.setAtributo(String.valueOf(qtdeFech));
			}
		}
		
		// Verificamos quantidade de dias da abertura é maior que o valor parametrizado
		if ( alertGarantia == null &&  openDays > qtdeAbert ) {
			alertGarantia = new AlertGarantia();
			alertGarantia.setAlertGarantiaText("A Data de abertura da OS está fora do prazo de " + qtdeAbert + " dias!");
			alertGarantia.setAlertGarantiaSeveridade(AlertCupom.SEVERIDADE_ERROR);
			alertGarantia.setAlertGarantiaKey("garantia.msg.error.prazoAbertura.garantia");	
			alertGarantia.setAtributo(String.valueOf(qtdeAbert));
		}	
				
		return alertGarantia;
		
	}


	/** Determina se já foi cobrado anteriormente.
	 * 
	 *  @param itemId
	 *  @param modelo
	 *  @return
	 *  @throws BusinessException
	 *       Se houverem erros de execução ou nas camadas 
	 *       abaixo dos serviços, serão encapsulados neste
	 *       tipo de <code>Exception</code>.
	 */
	public boolean wasCharged(long itemId, String modelo) throws BusinessException {

		try {

			return garantiaHeaderDao.hasGarantiasCobradas(itemId, modelo);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}  		

	}
	
	/** Recupera um entidade de Tempo Padrão para um serviço
	 *  Necessário para para definir o valor de serviço para garantia
	 *  
	 * @param garantiaId
	 * 	<code>Long</code>
	 * 
	 * @param chassi
	 * 	<code>String</code>
	 * 
	 * @return tempoPadrao
	 * 	<code>TempoPadrao</code> uma Entidade de TempoPadrao.
	 * 
	 * @throws DaoException
	 * 	Se houverem erros de execução ou nas camadas 
	 *   abaixo dos serviços, serão encapsulados neste
	 *   tipo de <code>BusinessException</code>.
	 */
	public TempoPadrao getTempoPadrao(Servico servico, String chassi)throws BusinessException {
		
		try {

			return garantiaHeaderDao.getTempoPadrao(servico, chassi);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}  	
		
	}
	
	/** Recupera um entidade de ValorServico para um serviço
	 *  Necessário para para definir o valor de serviço para garantia
	 *  
	 * @param codRegiao
	 * 	<code>Long</code> Código da região da concessionária
	 * 
	 * @param chassi
	 * 	<code>String</code>
	 * 
	 * @return valorServico
	 * 	<code>ValorServico</code> uma Entidade de ValorServico.
	 * 
	 * @throws DaoException
	 * 	Se houverem erros de execução ou nas camadas 
	 *   abaixo dos serviços, serão encapsulados neste
	 *   tipo de <code>BusinessException</code>.
	 */
	public ValorServico getValorServico(Long codRegiao, String chassi)throws BusinessException {
		
		try {

			return garantiaHeaderDao.getValorServico(codRegiao, chassi);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}  	
		
	}
	

	/** Recupera a lista de peças da garantia relacionada.
	 *
	 *  @param garantiaId
	 *      <code>Integer<code> id da entidade garantia relacionada.
	 *  @return
	 *      Um <code>List</code> lista de peças.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */	
	public List listPecas(GarantiaHeader garantia) throws BusinessException {
		
		List pecas 			= new ArrayList();
		List garantiaLines 	= new ArrayList();
		
		try {
			
			if ( !garantia.isNew() )
				//garantiaLines = this.garantiaHeaderDao.listByParameter(GarantiaLine.class, "compositeEntityId.garantiaId", garantia.getEntityId(), "compositeEntityId.lineId", "ASC");
			//else
				garantiaLines = this.garantiaHeaderDao.listPecas(garantia);
			
			//System.out.println(" GarantiaLine:" + garantiaLines.size());
			Boolean hasCausadora = Boolean.FALSE;
			Iterator it = garantiaLines.iterator();
			while ( it.hasNext() ) {
				
				PecaTO peca = new PecaTO();
				GarantiaLine garantiaLine = new GarantiaLine();
				garantiaLine = (GarantiaLine)it.next();
				peca.setLineId(((Long)garantiaLine.getCompositeEntityId().getLineId()).longValue());
				peca.setPecaId(((Long)garantiaLine.getItem().getEntityId()).longValue());
				peca.setPecaCode(garantiaLine.getItem().getItemCode());
				peca.setDescricao(garantiaLine.getItem().getDescricao());
				peca.setCobrar("N".equals(garantiaLine.getCobraPeca()) ? false : true);
				peca.setEnviar("N".equals(garantiaLine.getEnviaPeca()) ? false : true);
				peca.setFaltante("N".equals(garantiaLine.getPecaFaltante()) ? false : true);
				peca.setQuantidade(garantiaLine.getQuantidade().intValue());
				
				if(garantiaLine.getPecaCausadora() != null){
					peca.setCausadora("S".equals(garantiaLine.getPecaCausadora()) ? Boolean.TRUE : Boolean.FALSE);
					hasCausadora = Boolean.TRUE;
				}
				
				//System.out.println("---> pecaId:" + peca.getPecaId());
				//System.out.println("---> descrição:" + peca.getDescricao());
				//System.out.println("---> Cobrar:" + peca.isCobrar());
				//System.out.println("---> Enviar:" + peca.isEnviar());
				//System.out.println("---> Faltante:" + peca.isFaltante());
				
				pecas.add(peca);
				
			}
			
			//Quando não existir peça causadora definida
			//definimos com a primeira linha
			if(!hasCausadora.booleanValue() && pecas != null && !pecas.isEmpty()) {				
				PecaTO peca  = (PecaTO)pecas.get(0);	
				peca.setCausadora(Boolean.TRUE);
			}			
		
		} catch ( DaoException daoExp ) {

			throw new BusinessException( daoExp );

		}
		
		return pecas;
	}
	
	/** Recupera uma GarantiaLine pelo id.
	 *
	 *  @param compositeId
	 *      Referência da entidade na camada de persistência.
	 *  @return
	 *      Um objeto <code>GarantiaLine</code>.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */	
	public GarantiaLine getGarantiaLine(GarantiaLineId compositeId)  throws BusinessException {
		
		try {

			return garantiaHeaderDao.getGarantiaLine(compositeId);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}  	
	}
	
	/** Recupera a lista de serviços aproximada de uma determinada linha.
	 *
	 *  @param code
	 *      <code>String<code> Valor aproximado do código do serviço.
	 *      
	 *  @param linhaId
	 *      <code>Long<code> id da linha do produto.
	 *  
	 *  @return
	 *      Um <code>List</code> lista de peças.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */	
	public List listApproachServices(String code, Long linhaId) throws BusinessException {
		
		try {
			
			return  garantiaHeaderDao.listApproachServices(code, linhaId);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}  		
		
	}
	
	/** Recupera a lista de serviços aproximada de um determinado chassi.
	 *
	 *  @param code
	 *      <code>String<code> Valor aproximado do código do serviço.
	 *      
	 *  @param chassi
	 *      <code>String<code> chassi.
	 *  
	 *  @return
	 *      Um <code>List</code> lista de peças.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */	
	public List listApproachServicesByChassi(String partCode, String chassi) throws BusinessException {
		
		try {
			
			return  garantiaHeaderDao.listApproachServicesByChassi(partCode, chassi);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}  		
		
	}
	/** Verifica se já existe uma garantia cadastrada com os parâmetros passados
	 *  
	 * @param garantia
	 * 	<code>GarantiaHeader</code> Entidade de Garantia com os parâmetros para validação.
	 * 
	 * @return boolean
	 * 	<code>TRUE</code> quando já existir uma garantia.
	 * 		
	 * @throws DaoException
	 * 	Se houverem erros de execução ou nas camadas 
	 *   abaixo dos serviços, serão encapsulados neste
	 *   tipo de <code>BusinessException</code>.
	 */
   public boolean isEcxistsGarantia( GarantiaHeader garantia ) throws BusinessException {
	   
	   try {

			return garantiaHeaderDao.isEcxistsGarantia(garantia);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		} 
	   
   }

	/** Método getter para "garantiaHeaderDao".
	 *  @return
	 *      <code>GarantiaHeaderDao</code>. O valor atual de garantiaHeaderDao.
	 */
	public GarantiaHeaderDao getGarantiaHeaderDao() {
		return this.garantiaHeaderDao;
	}

	/** Método setter para "garantiaHeaderDao".
	 *  @param entityId
	 *      <code>GarantiaHeaderDao</code> a ser designado para GarantiaHeaderDao.
	 */
	public void setGarantiaHeaderDao(GarantiaHeaderDao garantiaHeaderDao) {
		this.garantiaHeaderDao = garantiaHeaderDao;
	}  

	/** Método getter para a propriedade feriadoDataBO
	 *
	 *  @return FeriadoDataBusiness de feriadoDataBO
	 */
	public FeriadoDataBusiness getFeriadoDataBO() {
		return feriadoDataBO;
	}

	/** Método setter para a propriedade feriadoDataBO
	 *
	 * @param feriadoDataBO FeriadoDataBusiness
	 */
	public void setFeriadoDataBO(FeriadoDataBusiness feriadoDataBO) {
		this.feriadoDataBO = feriadoDataBO;
	}

	/** Método getter para a propriedade loteBO
	 *
	 *  @return LoteBusiness de loteBO
	 */
	public LoteBusiness getLoteBO() {
		return loteBO;
	}

	/** Método setter para a propriedade loteBO
	 *
	 * @param loteBO LoteBusiness
	 */
	public void setLoteBO(LoteBusiness loteBO) {
		this.loteBO = loteBO;
	}

	/** Método getter para a propriedade alertGarantiaBO
	 *
	 *  @return AlertGarantiaBusiness de alertGarantiaBO
	 */
	public AlertGarantiaBusiness getAlertGarantiaBO() {
		return alertGarantiaBO;
	}

	/** Método setter para a propriedade alertGarantiaBO
	 *
	 * @param alertGarantiaBO AlertGarantiaBusiness
	 */
	public void setAlertGarantiaBO(AlertGarantiaBusiness alertGarantiaBO) {
		this.alertGarantiaBO = alertGarantiaBO;
	}

	/** Método getter para a propriedade itemBO
	 * 
	 *  @return ItemBusiness
	 *
	 */
	public ItemBusiness getItemBO() {
		return itemBO;
	}

	/** Método setter para a propriedade itemBO
	 *
	 * @param itemBO 
	 *           <code>ItemBusiness</code> a ser designado para itemBO.
	 * 
	 */
	public void setItemBO(ItemBusiness itemBO) {
		this.itemBO = itemBO;
	}
	
	 /**
	 * Método getter para "transactionTemplate".
	 * 
	 * @return <code>TransactionTemplate</code>. O valor atual de
	 *         transactionTemplate.
	 */
    public TransactionTemplate getTransactionTemplate() {
        return this.transactionTemplate;
    }
    
    /**
	 * Método setter para "transactionTemplate".
	 * 
	 * @param transactionTemplate
	 *            <code>transactionTemplate</code> a ser designado para
	 *            transactionTemplate.
	 */
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

	/**
	 * Método getter para a propriedade cupomBO
	 * @return  CupomBusiness de cupomBO
	 */
	public CupomBusiness getCupomBO() {
		return cupomBO;
	}

	/**
	 * Método setter para a propriedade cupomBO
	 * @param cupomBO CupomBusiness
	 */
	public void setCupomBO(CupomBusiness cupomBO) {
		this.cupomBO = cupomBO;
	}

	/** Método getter para a propriedade campaignDao
	 *
	 * @return campaignDao do tipo CampaignDao
	 *
	 */
	
	public CampaignDao getCampaignDao() {
		return campaignDao;
	}

	/** Método setter para a propriedade campaignDao
	 *
	 * @param campaignDao 
	 *       <code>campaignDao<code> a ser designado para GarantiaHeaderBusiness.java
	 *
	 */
	public void setCampaignDao(CampaignDao campaignDao) {
		this.campaignDao = campaignDao;
	}
	
	

}
