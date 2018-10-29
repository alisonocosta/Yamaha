/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioDao.java
 *   .: Cria��o.....11 de julho, 15:04
 *   .: Autor.......Gisele Panosso
 *   .: Descri��o...Interface de acesso ao banco para relat�rios.
 */
package br.com.yamaha.sistemagarantia.dao;

import java.util.Date;
import java.util.List;

import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;

/** Interface de acesso ao banco para relat�rios.
 * 
 *  @author Gisele Panosso
 */
public interface RelatorioDao extends Dao {

	/** Obt�m as linhas do relat�rio: Relat�rio Zero Km. 
	 * 
	 *  @param dataIni
	 *  	Par�metro data in�cio  do relat�rio.
	 * 
	 *  @param dataFim
	 *  	Par�metro data fim do relat�rio.
	 *  
	 *  @param km
	 *  	Par�metro quilometragem do relat�rio.
	 *    
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioZeroKm(Date dataIni, Date dataFim, Long km, Long linha ) throws DaoException;

	/** Obt�m as linhas do relat�rio: Relat�rio Analise de Pe�as. 
	 * 
	 *  @param dataIni
	 *  	Par�metro data in�cio  do relat�rio.
	 * 
	 *  @param dataFim
	 *  	Par�metro data fim do relat�rio.
	 *  
	 *  @param tipoProduto
	 *  	Par�metro tipo de produto do relat�rio.
	 *  
	 *  @param peca
	 *  	Par�metro pe�a do relat�rio.	  
	 *  
	 *  @param modelo
	 *  	Par�metro modelo do relat�rio.
	 *  
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioAnalisePecas( Date   dataIni
			                             , Date   dataFim
			                             , Long   tipoProduto
			                             , Long   peca
			                             , String modelo) throws DaoException;	
	
	/** Obt�m as linhas do relat�rio: Relat�rio Pagamentos. 
	 * 
	 *  @param dataIni
	 *  	Par�metro data in�cio do relat�rio.
	 *  
	 *  @param dataFim
	 *  	Par�metro data fim do relat�rio.
	 * 
	 *  @param concessionaria
	 *  	Par�metro concessionaria do relat�rio.
	 *  
	 *  @param tipoServico
	 *  	Par�metro tipoServico do relat�rio.
	 *  
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioPagamentos(Date dataIni, Date dataFim, Long concessionaria, String tipoServico ) throws DaoException;
	
	/** Obt�m as linhas do relat�rio: Relat�rio Recusa. 
	 * 
	 *  @param dataIni
	 *  	Par�metro data in�cio do relat�rio.
	 *  
	 *  @param dataFim
	 *  	Par�metro data fim do relat�rio.
	 *  
	 *  @param peca
	 *  	Par�metro pe�a do relat�rio.
	 *  
	 *  @param modelo
	 *  	Par�metro modelo do relat�rio.  
	 *  
	 *  @param concessionaria
	 *  	Par�metro concessionaria do relat�rio.
	 *  
	 *  @param representante
	 *  	Par�metro representante do relat�rio.
	 *  
	 *  @param analista
	 *  	Par�metro analista do relat�rio.
	 *  
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioRecusa( Long   empresa
			                       , Long   concessionaria
			                       , Long   linha
			                       , String chassi
			                       , Date   dataIni
						           , Date   dataFim ) throws DaoException;	

	/** Obt�m as linhas do relat�rio: Relat�rio Verifica��o. 
	 * 
	 *  @param dataIni
	 *  	Par�metro data in�cio do relat�rio.
	 *  
	 *  @param dataFim
	 *  	Par�metro data fim do relat�rio.
	 * 
	 *  @param linha
	 *  	Par�metro linha do relat�rio.
	 *  
	 *  @param concessionaria
	 *  	Par�metro concessionaria do relat�rio.
	 *  
	 *  @param valorMinimo
	 *  	Par�metro valorMinimo do relat�rio.
	 *  
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioVerificacao( Date   dataIni
							            , Date   dataFim
							            , Long   linha
							            , Long   concessionaria			                            
							            , Long   valorMinimo) throws DaoException;
	
	/** Obt�m as linhas do relat�rio: Relat�rio Mensal por Modelo. 
	 * 
	 *  @param mesReferencia
	 *  	Par�metro m�s de refer�ncia do relat�rio.
     *  @param empresaId
     *      Empresa utilizada para busca.
	 *  
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioMensalModelo(String mesReferencia, Long empresaId) throws DaoException;
	
	/** Obt�m as linhas do relat�rio: Relat�rio Mala Direta.
	 * 
	 *  @param dataIni
	 *  	Par�metro data in�cio do relat�rio.
	 *  
	 *  @param dataFim
	 *  	Par�metro data fim do relat�rio.
	 *  
	 *  @param linha
	 *  	Par�metro linha do relat�rio.
	 *  
	 *  @param empresa
	 *  	Par�metro empresa do relat�rio.
	 *    
	 *  @param concessionaria
	 *  	Par�metro concessionaria do relat�rio.
	 *    
	 *  @param estado
	 *  	Par�metro estado do relat�rio.
	 *    
	 *  @param modelo
	 *  	Par�metro modelo do relat�rio.
	 *  	    
	 *  @param chassiIni
	 *  	Par�metro chassi inicial do relat�rio
	 *  	    
	 *  @param chassiFim
	 *  	Par�metro chassi final do relat�rio
	 *    
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */	
	public List listRelatorioMalaDireta( Date   dataIni
							           , Date   dataFim
							           , Long   empresa
							           , Long   concessionaria
							           , Long   linha
							           , String estado
							           , String modelo
							           , String chassiIni
							           , String chassiFim) throws DaoException;	
	
	/** Obt�m as linhas do relat�rio: Relat�rio Solicita��o de Garantia. 
	 * 
	 *  @param lote
	 *  	Par�metro lote do relat�rio.
	 *  
	 *  @param concessionaria
	 *  	Par�metro concessionaria do relat�rio.	 
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioSolicitacaoGarantiaNota( Integer lote, Long concessionaria) throws DaoException;		

	/** Obt�m as linhas do relat�rio: Relat�rio Solicita��o de Garantia. 
	 * 
	 *  @param lote
	 *  	Par�metro lote do relat�rio.
	 *  
	 *  @param concessionaria
	 *  	Par�metro concessionaria do relat�rio.	 
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioSolicitacaoGarantia( Integer lote) throws DaoException;
	
	/** Obt�m as linhas do relat�rio: Relat�rio Solicita��o de Garantia. 
	 * 
	 *  @param lote
	 *  	Par�metro lote do relat�rio.
	 *  
	 *  @param concessionaria
	 *  	Par�metro concessionaria do relat�rio.	 
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioSolicitacaoGarantiaPeca( Integer lote, Long concessionaria) throws DaoException;
	
	/** Obt�m as linhas do relat�rio: Relat�rio de Processamento de Revisoes - Fase1. 
	 * 
	 *  @param lote
	 *  	Par�metro lote do relat�rio.
	 *  
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioProcRevisoesFase1(Long empresa, Integer lote) throws DaoException;	

	/** Obt�m as linhas do relat�rio: Relat�rio de Processamento de Revisoes - Fase2. 
	 * 
	 *  @param lote
	 *  	Par�metro lote do relat�rio.
	 *  
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioProcRevisoesFase2(Long empresa, Integer lote) throws DaoException;	
	
	/** Lista os dados das notas fiscais para o Relat�rio de Processamento de Pe�as - Fase2.
	 * 
	 * @param loteId
	 * 	<code>Integer</code> Id do lote
	 * 
	 * @return list
	 * 	<code>list</code> lista de notas do Relat�rio de Processamento de Pe�as - Fase2.
	 * 
	 * @throws DaoException
	 */
	public List listRelatorioNotasToProcPecasFase2( Integer loteId) throws DaoException;
	
	/** Obt�m as linhas do relat�rio: Relat�rio Consulta de Notas Fiscais. 
	 * 
	 *  @param dataIni
	 *  	Par�metro data in�cio do relat�rio.
	 *  
	 *  @param dataFim
	 *  	Par�metro data fim do relat�rio.
	 *  
	 *  @param lote
	 *  	Par�metro lote do relat�rio.
	 *  
	 *  @param tipoLote
	 *  	Par�metro tipoLote do relat�rio.
	 *  
	 *  @param concessionaria
	 *  	Par�metro concessionaria do relat�rio.
	 *      
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioConsultaNfEmp( Date    dataIni
		                                   , Date    dataFim
		                                   , Long    linha
		                                   , Integer lote
		                                   , Long    tipoLote
		                                   , Long    concessionaria
		                                   , Long    empresa) throws DaoException;	
	
	/** Obt�m as linhas do relat�rio: Relat�rio Consulta de Notas Fiscais. 
	 * 
	 *  @param dataIni
	 *  	Par�metro data in�cio do relat�rio.
	 *  
	 *  @param dataFim
	 *  	Par�metro data fim do relat�rio.
	 *  
	 *  @param lote
	 *  	Par�metro lote do relat�rio.
	 *  
	 *  @param tipoLote
	 *  	Par�metro tipoLote do relat�rio.
	 *  
	 *  @param concessionaria
	 *  	Par�metro concessionaria do relat�rio.
	 *      
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioConsultaNfConc( Date    dataIni
		                                   , Date    dataFim
		                                   , Long    linha
		                                   , Integer lote
		                                   , Long    tipoLote
		                                   , Long    concessionaria
		                                   , Long    empresa) throws DaoException;		
	
	/** Obt�m as linhas do relat�rio: Relat�rio Consulta de Notas Fiscais. 
	 * 
	 *  @param dataIni
	 *  	Par�metro data in�cio do relat�rio.
	 *  
	 *  @param dataFim
	 *  	Par�metro data fim do relat�rio.
	 *  
	 *  @param lote
	 *  	Par�metro lote do relat�rio.
	 *  
	 *  @param tipoLote
	 *  	Par�metro tipoLote do relat�rio.
	 *  
	 *  @param concessionaria
	 *  	Par�metro concessionaria do relat�rio.
	 *      
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioConsultaNfLote( Date    dataIni
		                                   , Date    dataFim
		                                   , Long    linha
		                                   , Integer lote
		                                   , Long    tipoLote
		                                   , Long    concessionaria
		                                   , Long    empresa) throws DaoException;		

	/** Obt�m as linhas do relat�rio: Relat�rio Consulta de Notas Fiscais. 
	 * 
	 *  @param dataIni
	 *  	Par�metro data in�cio do relat�rio.
	 *  
	 *  @param dataFim
	 *  	Par�metro data fim do relat�rio.
	 *  
	 *  @param lote
	 *  	Par�metro lote do relat�rio.
	 *  
	 *  @param tipoLote
	 *  	Par�metro tipoLote do relat�rio.
	 *  
	 *  @param concessionaria
	 *  	Par�metro concessionaria do relat�rio.
	 *      
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioConsultaNfNota( Date    dataIni
		                                   , Date    dataFim
		                                   , Long    linha
		                                   , Integer lote
		                                   , Long    tipoLote
		                                   , Long    concessionaria
		                                   , Long    empresa) throws DaoException;	

	/** Obt�m as linhas do relat�rio: Relat�rio Consulta de Lotes. 
	 * 
	 *  @param dataIni
	 *  	Par�metro data in�cio do relat�rio.
	 *  
	 *  @param dataFim
	 *  	Par�metro data fim do relat�rio.
	 *  
	 *  @param lote
	 *  	Par�metro lote do relat�rio.
	 *  
	 *  @param tipoLote
	 *  	Par�metro tipoLote do relat�rio.
	 *  
	 *  @param concessionaria
	 *  	Par�metro concessionaria do relat�rio.
	 *      
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioConsultaConc( Date    dataIni
	                                     , Date    dataFim
	                                     , Long    linha
	                                     , Integer lote
	                                     , Long    tipoLote
	                                     , Long    concessionaria) throws DaoException;

	/** Obt�m as linhas do relat�rio: Relat�rio Consulta de Lotes. 
	 * 
	 *  @param dataIni
	 *  	Par�metro data in�cio do relat�rio.
	 *  
	 *  @param dataFim
	 *  	Par�metro data fim do relat�rio.
	 *  
	 *  @param lote
	 *  	Par�metro lote do relat�rio.
	 *  
	 *  @param tipoLote
	 *  	Par�metro tipoLote do relat�rio.
	 *  
	 *  @param concessionaria
	 *  	Par�metro concessionaria do relat�rio.
	 *      
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioConsultaLote( Date    dataIni
	                                     , Date    dataFim
	                                     , Long    linha
	                                     , Integer lote
	                                     , Long    tipoLote
	                                     , Long    concessionaria) throws DaoException;
	
	
	/** Obt�m as linhas do relat�rio: Relat�rio Consulta de Lotes. 
	 * 
	 *  @param dataIni
	 *  	Par�metro data in�cio do relat�rio.
	 *  
	 *  @param dataFim
	 *  	Par�metro data fim do relat�rio.
	 *  
	 *  @param lote
	 *  	Par�metro lote do relat�rio.
	 *  
	 *  @param tipoLote
	 *  	Par�metro tipoLote do relat�rio.
	 *  
	 *  @param concessionaria
	 *  	Par�metro concessionaria do relat�rio.
	 *      
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioConsultaLoteStatus( Date    dataIni
		                                       , Date    dataFim
		                                       , Long    linha
		                                       , Integer lote
		                                       , Long    tipoLote
		                                       , Long    concessionaria) throws DaoException;
		
	
	/** Obt�m as linhas do relat�rio: Relat�rio de Protocolo de Documentos de Revis�o.
	 *   
	 *  @param lote
	 *  	Par�metro lote do relat�rio.
	 *	 
	 *     
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioDocRevisao( Integer lote) throws DaoException;		
	
	/** Obt�m as linhas do relat�rio: Relat�rio de Protocolo de Documentos de Revis�o.
	 *   
	 *  @param lote
	 *  	Par�metro lote do relat�rio.
	 *  
	 *  @param concessionaria
	 *  	Par�metro concessionaria do relat�rio.
	 *     
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioDocRevisaoDetalhe( Integer lote) throws DaoException;

	/** Obt�m as linhas do relat�rio: Relat�rio de Processamento de Pe�as - Fase1. 
	 * 
	 *  @param empresa
	 *  	Par�metro empresa do relat�rio. (N�o obrigat�rio)
	 *   
	 *  @param lote
	 *  	Par�metro lote do relat�rio.
	 *  
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioProcPecasFase1(Long empresa, Integer lote) throws DaoException;		

	
	/** Obt�m as linhas do relat�rio: Relat�rio de Processamento de Pe�as - Fase2. 
	 *
	 * 	@param empresa
	 *  	Par�metro empresa do relat�rio.
	 *  
	 *  @param lote
	 *  	Par�metro lote do relat�rio.
	 *  
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioProcPecasFase2(Long empresa, Integer lote) throws DaoException;		

	
	/** Obt�m as linhas do relat�rio: Relat�rio Consulta Hist�rico Chassi, dados do Cabe�alho. 
	 * 
	 *  @param chassi
	 *  	Par�metro chassi do relat�rio.
	 *  
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioHistChassiCabec(String chassi) throws DaoException;
	
	/** Obt�m as linhas do relat�rio: Relat�rio Consulta Hist�rico Chassi, dados das Revis�es. 
	 * 
	 *  @param chassi
	 *  	Par�metro chassi do relat�rio.
	 *  
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioHistChassiRevisao(String chassi) throws DaoException;
	
	/** Obt�m as linhas do relat�rio: Relat�rio Consulta Hist�rico Chassi, dados das Garantias. 
	 * 
	 *  @param chassi
	 *  	Par�metro chassi do relat�rio.
	 *  
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioHistChassiGarantia(String chassi) throws DaoException;
	
	/** Obt�m as linhas do relat�rio: Relat�rio Consulta Hist�rico Chassi, dados das Pecas. 
	 * 
	 * 	@param garantia
	 *  Par�metro garantia do relat�rio.
	 *  
	 *  @param chassi
	 *  	Par�metro chassi do relat�rio.
	 *  
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioHistChassiPeca(Long garantia, String chassi) throws DaoException;
	
	/** Obt�m as linhas do relat�rio: Relat�rio Consulta Hist�rico Chassi, parecer do Analista. 
	 * 	
	 * @param garantia
	 *  	Par�metro garantia do relat�rio.
	 *  
	 *  @param chassi
	 *  	Par�metro chassi do relat�rio.
	 *  
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioHistChassiParecer(Long garantia, String chassi) throws DaoException;
	
	/** Obt�m as linhas do relat�rio: Relat�rio Consulta Hist�rico Chassi, totais da garantia, pe�as e terceiros. 
	 * 
	 *  @param chassi
	 *  	Par�metro chassi do relat�rio.
	 *  
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioHistChassiTotais(String chassi) throws DaoException;

	/** Obt�m as linhas do relat�rio: Relat�rio de Garantia e Pe�as. 
	 * 
	 *  @param empresa
	 *  	Par�metro empresa do relat�rio
	 *  
	 *  @param linha
	 *  	Par�metro linha do relat�rio.
     * 
	 *  @param ano
	 *  	Par�metro ano do relat�rio.	
	 *   
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioGarantiaPecas(Long empresa, Long linha, String ano) throws DaoException;
	
	/** Obt�m as linhas do relat�rio: Relat�rio de Garantia e Pe�as.
	 * 
	 *  @param empresa
	 *  	Par�metro empresa do relat�rio.
	 *   
	 *  @param linha
	 *  	Par�metro linha do relat�rio.
     * 
	 *  @param ano
	 *  	Par�metro ano do relat�rio.	
	 *   
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioGPOrcPcsMobra(Long empresa, Long linha, String ano) throws DaoException;

	/** Obt�m as linhas do relat�rio: Relat�rio de Garantia e Pe�as.
	 * 
	 *  @param empresa
	 *  	Par�metro empresa do relat�rio.
	 *   
	 *  @param linha
	 *  	Par�metro linha do relat�rio.
     * 
	 *  @param ano
	 *  	Par�metro ano do relat�rio.	
	 *   
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioGPRevisao(Long empresa, Long linha, String ano) throws DaoException;
	
	/** Obt�m as linhas do relat�rio: Relat�rio de Garantia e Pe�as.
	 * 
	 *  @param empresa
	 *  	Par�metro empresa do relat�rio.
	 *   
	 *  @param linha
	 *  	Par�metro linha do relat�rio.
     * 
	 *  @param ano
	 *  	Par�metro ano do relat�rio.	
	 *   
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioGPOrcRev(Long empresa, Long linha, String ano) throws DaoException;
	
	/** Obt�m as linhas do relat�rio: Relat�rio de Garantia e Pe�as.
	 * 
	 *  @param empresa
	 *  	Par�metro empresa do relat�rio.
	 *   
	 *  @param linha
	 *  	Par�metro linha do relat�rio.
     * 
	 *  @param ano
	 *  	Par�metro ano do relat�rio.	
	 *   
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioGPOrcTotal(Long empresa, Long linha, String ano) throws DaoException;
	
	/** Obt�m as linhas do relat�rio: Relat�rio de Garantia e Pe�as.
	 * 
	 *  @param empresa
	 *  	Par�metro empresa do relat�rio.
	 *   
	 *  @param linha
	 *  	Par�metro linha do relat�rio.
     * 
	 *  @param ano
	 *  	Par�metro ano do relat�rio.	
	 *   
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioGPVlFatur(Long empresa, Long linha, String ano) throws DaoException;
	
	/** Obt�m as linhas do relat�rio: Relat�rio de Garantia e Pe�as.
	 * 
	 *  @param empresa
	 *  	Par�metro empresa do relat�rio.
	 *   
	 *  @param linha
	 *  	Par�metro linha do relat�rio.
     * 
	 *  @param ano
	 *  	Par�metro ano do relat�rio.	
	 *   
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioGPQtdes(Long empresa, Long linha, String ano) throws DaoException;

	/** Obt�m as linhas do relat�rio: Relat�rio de Garantia e Pe�as por Modelo.
	 * 
	 *  @param concessionaria
	 *  	Par�metro concessionaria do relat�rio.
	 *   
	 *  @param linha
	 *  	Par�metro linha do relat�rio.
     * 
	 *  @param ano
	 *  	Par�metro ano (yyyy) do relat�rio.
	 *  	
	 *  @param modelo
	 *  	Par�metro modelo do relat�rio.	
	 *   
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioGarantiaPecasModelo(Long concessionaria, Long linha, String ano, String modelo) throws DaoException;
	
	/** Obt�m as linhas do relat�rio: Relat�rio de Garantia e Pe�as por Modelo.
	 * 
	 *  @param concessionaria
	 *  	Par�metro concessionaria do relat�rio.
	 *   
	 *  @param linha
	 *  	Par�metro linha do relat�rio.
     * 
	 *  @param ano
	 *  	Par�metro ano (yyyy) do relat�rio.
	 *  	
	 *  @param modelo
	 *  	Par�metro modelo do relat�rio.	
	 *   
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioGPMRevisao(Long concessionaria, Long linha, String ano, String modelo) throws DaoException;
	
	/** Obt�m as linhas do relat�rio: Relat�rio de Garantia e Pe�as por Modelo.
	 * 
	 *  @param concessionaria
	 *  	Par�metro concessionaria do relat�rio.
	 *   
	 *  @param linha
	 *  	Par�metro linha do relat�rio.
     * 
	 *  @param ano
	 *  	Par�metro ano (yyyy) do relat�rio.
	 *  	
	 *  @param modelo
	 *  	Par�metro modelo do relat�rio.	
	 *   
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioGPMVlFatur(Long concessionaria, Long linha, String ano, String modelo) throws DaoException;
	
	/** Obt�m as linhas do relat�rio: Relat�rio de Garantia e Pe�as por Modelo.
	 * 
	 *  @param concessionaria
	 *  	Par�metro concessionaria do relat�rio.
	 *   
	 *  @param linha
	 *  	Par�metro linha do relat�rio.
     * 
	 *  @param ano
	 *  	Par�metro ano (yyyy) do relat�rio.
	 *  	
	 *  @param modelo
	 *  	Par�metro modelo do relat�rio.	
	 *   
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioGPMQtdes(Long concessionaria, Long linha, String ano, String modelo) throws DaoException;

	/** Obt�m as linhas do relat�rio: Relat�rio de Garantia e Pe�as por Modelo.
	 * 
	 *  @param ano
	 *  	Par�metro ano (yyyy) do relat�rio.
	 *  
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioGPMDolar(String ano) throws DaoException;

	/** Obt�m as linhas do relat�rio: Relat�rio Imported Parts Claim List.
	 * 
	 *  @param dataIni
	 *  	Par�metro data in�cio do relat�rio.
	 *  
	 *  @param linha
	 *  	Par�metro linha do relat�rio.
	 *  
	 *  @param empresa
	 *  	Par�metro empresa do relat�rio.
	 *  
	 *  @param fornecedor
	 *  	Par�metro fornecedor do relat�rio.  
	 *  
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioImportedParts( Date   dataIni
								          , Date   dataFim
								          , Long   linha
								          , Long   empresa
								          , String fornecedor) throws DaoException;

	/** Obt�m as linhas do relat�rio: Relat�rio Imported Parts Claim List - Invoice.
	 * 
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioImportedPartsInvoice1() throws DaoException;	
	
	/** Obt�m as linhas do relat�rio: Relat�rio Imported Parts Claim List - Invoice.
	 * 
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioImportedPartsInvoice2( String agencia
			                                      , String banco
			                                      , String conta) throws DaoException;	
	
	/** Obt�m as linhas do relat�rio: Relat�rio Imported Parts Claim List - Invoice.
	 * 
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioImportedPartsInvoice3( String ano
			                                      , String mes) throws DaoException;	
	
	/** Obt�m as linhas do relat�rio: Relat�rio Imported Parts Claim List - Invoice.
	 * 
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioImportedPartsInvoice4( Long empresa) throws DaoException;
	
	/** Obt�m as linhas do relat�rio: Relat�rio Imported Parts Claim List - Invoice.
	 * 
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioImportedPartsInvoice5( String fornecedor) throws DaoException;	
	
	/** Lista os dados de Faturamento de um determinado per�odo, de um empresa( ou geral) e linha de produto.
	 *  
	 *      
	 *  @param  orgId - Id da Organiza��o
	 *  	<code>Long</code> N�mero que representa o id da Organiza��o
	 *  
	 *  @param  dataInicioAp - Data de in�cio para apura��o
	 *  	<code>Date</code> Data para in�cio da apura��o dos dados para o relat�rio
	 *  
	 *  @param  dataFinalAp - Data final para apura��o
	 *  	<code>Date</code> Data para fechamento da apura��o dos dados para o relat�rio
	 *  
	 *  @param  linhaId - Id da Organiza��o
	 *  	<code>Long</code> Linha do Produto
	 *  
	 *  @param  modelo - modelo definido para incluir no gr�fico
	 *  	<code>String</code> Grupo Modelo do Produto
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relat�rio Gr�fico de Faturamento x Garantia.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
	 *      BusinessRuleException (subclasse de BusinessException) ser� 
	 *      lan�ada.
	 */
	public List listFaturamento ( Long orgId, Date dataInicioAp, Date dataFinalAp, Long linhaId, String modelo ) throws DaoException;
	
	/** Lista a quantidade de Faturamento de um determinado per�odo, de um empresa( ou geral) por linha de produto.
	 *  
	 *      
	 *  @param  orgId - Id da Organiza��o
	 *  	<code>Long</code> N�mero que representa o id da Organiza��o
	 *  
	 *  @param  dataInicioAp - Data de in�cio para apura��o
	 *  	<code>Date</code> Data para in�cio da apura��o dos dados para o relat�rio
	 *  
	 *  @param  dataFinalAp - Data final para apura��o
	 *  	<code>Date</code> Data para fechamento da apura��o dos dados para o relat�rio
	 *  
	 *  @param  linhaId - Id da Organiza��o
	 *  	<code>Long</code> Linha do Produto
	 *  
	 *  @param  modelo - modelo definido para incluir no gr�fico
	 *  	<code>String</code> Grupo Modelo do Produto
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relat�rio Gr�fico de Faturamento x Garantia.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
	 *      BusinessRuleException (subclasse de BusinessException) ser� 
	 *      lan�ada.
	 */
	public List listQtdeFaturamento ( Long orgId, Date dataInicioAp, Date dataFinalAp, Long linhaId, String modelo ) throws DaoException; 
	
	/** Lista os dados de Faturamento e Garantia para o Relat�rio de Gr�ficos Individuais .
	 *  
	 *      
	 *  @param  orgId - Id da Organiza��o
	 *  	<code>Long</code> N�mero que representa o id da Organiza��o
	 *  
	 *  @param  dataInicioAp - Data de in�cio para apura��o
	 *  	<code>Date</code> Data para in�cio da apura��o dos dados para o relat�rio
	 *  
	 *  @param  dataFinalAp - Data final para apura��o
	 *  	<code>Date</code> Data para fechamento da apura��o dos dados para o relat�rio
	 *  
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relat�rio Gr�fico de Faturamento x Garantia.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
	 *      BusinessRuleException (subclasse de BusinessException) ser� 
	 *      lan�ada.
	 */
	public List listFaturamentoByGarantia ( Long orgId, Date dataInicioAp, Date dataFinalAp ) throws DaoException;
	
	
	/** Lista os dados de Faturamento e Garantia para o Relat�rio de Gr�ficos Individuais  por Linha - Gr�fico 1.
	 *  
	 *      
	 *  @param  orgId - Id da Organiza��o
	 *  	<code>Long</code> N�mero que representa o id da Organiza��o
	 *  
	 *  @param  dataInicioAp - Data de in�cio para apura��o
	 *  	<code>Date</code> Data para in�cio da apura��o dos dados para o relat�rio
	 *  
	 *  @param  dataFinalAp - Data final para apura��o
	 *  	<code>Date</code> Data para fechamento da apura��o dos dados para o relat�rio
	 *  
	 *  @param  linhaId - Id da Organiza��o
	 *  	<code>Long</code> Linha do Produto
	 *  
	 *  @param  modelo - Modelo do produto
	 *  	<code>String</code> modelo do produto para o filtro 
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relat�rio Gr�fico de Faturamento x Garantia.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
	 *      BusinessRuleException (subclasse de BusinessException) ser� 
	 *      lan�ada.
	 */
	public List listFaturamentoByGarantiaANDLinha ( Long orgId, Date dataInicioAp, Date dataFinalAp, Long linhaId, String modelo) throws DaoException;
	
	/** Lista os dados de Faturamento e Garantia para o Relat�rio de Gr�ficos Individuais Linha - Gr�fico 1.
	 *  
	 *      
	 *  @param  orgId - Id da Organiza��o
	 *  	<code>Long</code> N�mero que representa o id da Organiza��o
	 *  
	 *  @param  dataInicioAp - Data de in�cio para apura��o
	 *  	<code>Date</code> Data para in�cio da apura��o dos dados para o relat�rio
	 *  
	 *  @param  dataFinalAp - Data final para apura��o
	 *  	<code>Date</code> Data para fechamento da apura��o dos dados para o relat�rio
	 *  
	 *  @param  modelo - Modelo do produto
	 *  	<code>String</code> modelo do produto para o filtro  
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relat�rio Gr�fico de Faturamento x Garantia.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
	 *      BusinessRuleException (subclasse de BusinessException) ser� 
	 *      lan�ada.
	 */
	public List listGraphFaturamentoByGarantiaANDLinha ( Long orgId, Date dataInicioAp, Date dataFinalAp, String modelo ) throws DaoException;
	
	
	/** Lista os dados de Faturamento e Quant. Garantia para o Relat�rio de Gr�ficos Individuais - Gr�fico 3.
	 *  
	 *      
	 *  @param  orgId - Id da Organiza��o
	 *  	<code>Long</code> N�mero que representa o id da Organiza��o
	 *  
	 *  @param  dataInicioAp - Data de in�cio para apura��o
	 *  	<code>Date</code> Data para in�cio da apura��o dos dados para o relat�rio
	 *  
	 *  @param  dataFinalAp - Data final para apura��o
	 *  	<code>Date</code> Data para fechamento da apura��o dos dados para o relat�rio
	 *  
	 *  @param  linhaId - Id da linha do produto
	 *  	<code>Long</code> N�mero que representa o id da linha
	 *
	 *  @param  modelo - modelo definido para incluir no gr�fico
	 *  	<code>String</code> Grupo Modelo do Produto
	 *
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relat�rio Gr�fico de Faturamento x Garantia.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
	 *      BusinessRuleException (subclasse de BusinessException) ser� 
	 *      lan�ada.
	 */
	public List listFaturamentoByQtdeGarantia ( Long orgId, Date dataInicioAp, Date dataFinalAp, Long linhaId, String modelo ) throws DaoException;
	
	/** Lista os dados de VALOR ACUMULADO GARANTIA 0 KM x INDICE GARANTIA 0 KM para o Relat�rio de Gr�ficos Individuais - Gr�fico 5.
	 *  
	 *      
	 *  @param  orgId - Id da Organiza��o
	 *  	<code>Long</code> N�mero que representa o id da Organiza��o
	 *  
	 *  @param  dataInicioAp - Data de in�cio para apura��o
	 *  	<code>Date</code> Data para in�cio da apura��o dos dados para o relat�rio
	 *  
	 *  @param  dataFinalAp - Data final para apura��o
	 *  	<code>Date</code> Data para fechamento da apura��o dos dados para o relat�rio
	 *  
	 *  @param  linhaId - Id da linha do produto
	 *  	<code>Long</code> Id da linha do produto
	 *  
	 *  @param  modelo - modelo definido para incluir no gr�fico
	 *  	<code>String</code> Grupo Modelo do Produto
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relat�rio Gr�fico de Faturamento x Garantia.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
	 *      BusinessRuleException (subclasse de BusinessException) ser� 
	 *      lan�ada.
	 */
	public List listAcumuladoGarantiaByIndice ( Long orgId, Date dataInicioAp, Date dataFinalAp, Long linhaId, String modelo ) throws DaoException;
	
	/** Lista os dados das PRINCIPAIS PE�AS para o Relat�rio de Gr�ficos Individuais - Gr�fico 6 (Tabela). 
	 *  
	 *      
	 *  @param  orgId - Id da Organiza��o
	 *  	<code>Long</code> N�mero que representa o id da Organiza��o
	 *  
	 *  @param  dataFinalAp - Data final para apura��o
	 *  	<code>Date</code> Data para fechamento da apura��o dos dados para o relat�rio  
	 *  
	 *  @param  linhaId - Id da Linha do produto
	 *  	<code>Long</code> Id da Linha do produto  
	 *  
	 *  @param  modelo - Modelo do produto
	 *  	<code>String</code> modelo do produto para o filtro
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relat�rio Gr�fico de PRINCIPAIS PE�AS.
	 *      
	 * @throws DaoException
	 */
	public List listValuesTablePeca ( Long orgId, Date dataFinalAp, Long linhaId, String modelo ) throws DaoException;
	
	/** Lista os dados das PRINCIPAIS PE�AS para o Relat�rio de Gr�ficos Individuais por Linha- Gr�fico 6 (Tabela). 
	 *  
	 *      
	 *  @param  orgId - Id da Organiza��o
	 *  	<code>Long</code> N�mero que representa o id da Organiza��o
	 *  
	 *  @param  dataFinalAp - Data final para apura��o
	 *  	<code>Date</code> Data para fechamento da apura��o dos dados para o relat�rio  
	 *  
	 *  @param  modelo - Modelo
	 *  	<code>String</code> Modelo do produto  
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relat�rio Gr�fico de PRINCIPAIS PE�AS.
	 *      
	 * @throws DaoException
	 */
	public List listValuesTablePecaByModelo ( Long orgId, Date dataFinalAp, String modelo ) throws DaoException;
	
	/** Lista os dados para os gr�ficos relat�rio Service Report. 
	 *  
	 *      
	 *  @param  orgId - Id da Organiza��o
	 *  	<code>Long</code> N�mero que representa o id da Organiza��o
	 *  
	 *  @param  dataAp - Datapara apura��o
	 *  	<code>Date</code> Data para fechamento da apura��o dos dados para o relat�rio  
	 *  
	 *  @param part - parte
	 *  	<code>int</code> parte do relat�rio solicitada  
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relat�rio Gr�fico Service Report.
	 *      
	 * @throws DaoException
	 */
	public List listValuesServiceReportParte ( Long orgId, Date dataAp, int part ) throws DaoException;
	
	/** Lista os dados de Garantia Paga por Modelo para o Relat�rio Gr�fico - Garantia Paga por Modelo de Produto.
	 *  
	 *      
	 *  @param  orgId - Id da Organiza��o
	 *  	<code>Long</code> N�mero que representa o id da Organiza��o
	 *  
	 *   *  @param  grupoModelo - Grupo do modelo
	 *  	<code>String</code> Quando for nulo, recupera todos os grupos
	 *  
	 *  @param  dataInicioAp - Data de in�cio para apura��o
	 *  	<code>Date</code> Data para in�cio da apura��o dos dados para o relat�rio
	 *  
	 *  @param  dataFinalAp - Data final para apura��o
	 *  	<code>Date</code> Data para fechamento da apura��o dos dados para o relat�rio
	 *  
	 *  @param  typeGraph - Tipo da recupera��o dos dados
	 *  	<code>String</code> True para acumulado	
	 *  
	 *  @param linhaId 
	 *  	<code>Long</code> Id da linha do produto
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relat�rio Gr�fico de Faturamento x Garantia.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
	 *      BusinessRuleException (subclasse de BusinessException) ser� 
	 *      lan�ada.
	 */
	public List listGarantiaPagaByModelo ( Long orgId, String grupoModelo, Date dataInicioAp, Date dataFinalAp, String typeGraph, Long linhaId ) throws DaoException;
	
	/** Lista os dados para o Relat�rio Gr�fico Garantia Mensal Percentual. 
	 *  
	 *      
	 *  @param  orgId - Id da Organiza��o
	 *  	<code>Long</code> N�mero que representa o id da Organiza��o
	 *  
	 *  @param  grupoModelo - Grupo do modelo
	 *  	<code>String</code> Quando for nulo, recupera todos os grupos
	 *  
	 *  @param  dataInicioAp - Data de in�cio para apura��o
	 *  	<code>Date</code> Data para in�cio da apura��o dos dados para o relat�rio
	 *  
	 *  @param  dataFinalAp - Data final para apura��o
	 *  	<code>Date</code> Data para fechamento da apura��o dos dados para o relat�rio
	 *  
	 *  @param linhaId 
	 *  	<code>Long</code> Id da linha do produto
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relat�rio Gr�fico de PRINCIPAIS PE�AS.
	 *      
	 * @throws DaoException
	 */
	public List listValuesGraphGarantiaMensalPercentual ( Long orgId, String grupoModelo, Date dataInicioAp, Date dataFinalAp, Long linhaId ) throws DaoException;
	
	/** Lista os dados para o Relat�rio Gerenciamento M�s. 
	 *  
	 *  
	 *  @param  dataInicioAp - Data de in�cio para apura��o
	 *  	<code>Date</code> Data para in�cio da apura��o dos dados para o relat�rio
	 *  
	 *  @param  dataFinalAp - Data final para apura��o
	 *  	<code>Date</code> Data para fechamento da apura��o dos dados para o relat�rio
	 *  
	 *  @param linhaId 
	 *  	<code>Long</code> Id da linha do produto
	 *  
	 *  @param concessionariaId 
	 *  	<code>Long</code> Id da concession�ria
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relat�rio Gr�fico de PRINCIPAIS PE�AS.
	 *      
	 * @throws DaoException
	 */
	public List listValuesGerenciamentoMes ( Date dataInicioAp, Date dataFinalAp, Long linhaId, Long concessionariaId ) throws DaoException;
	
	/** Lista os dados para o Relat�rio Gerenciamento M�s. 
	 *  
	 *  
	 *  @param  dataInicioAp - Data de in�cio para apura��o
	 *  	<code>Date</code> Data para in�cio da apura��o dos dados para o relat�rio
	 *  
	 *  @param  dataFinalAp - Data final para apura��o
	 *  	<code>Date</code> Data para fechamento da apura��o dos dados para o relat�rio
	 *  
	 *  @param linhaId 
	 *  	<code>Long</code> Id da linha do produto
	 *  
	 *  @param concessionariaId 
	 *  	<code>Long</code> Id da concession�ria
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relat�rio Gr�fico de PRINCIPAIS PE�AS.
	 *      
	 * @throws DaoException
	 */
	public int getQtLotesLiberadosGerenciamentoMes ( Date dataInicioAp, Date dataFinalAp, Long linhaId, Long concessionariaId ) throws DaoException;
	
	/** Retorna a quantidade de lotes devolvidos de um determinado per�odo de uma concession�ria e de uma determinada linha. 
	 *  
	 *  
	 *  @param  dataInicioAp - Data de in�cio para apura��o
	 *  	<code>Date</code> Data para in�cio da apura��o dos dados para o relat�rio
	 *  
	 *  @param  dataFinalAp - Data final para apura��o
	 *  	<code>Date</code> Data para fechamento da apura��o dos dados para o relat�rio
	 *  
	 *  @param linhaId 
	 *  	<code>Long</code> Id da linha do produto
	 *  
	 *  @param concessionariaId 
	 *  	<code>Long</code> Id da concession�ria
	 *  
	 *  @return
	 *      Um valor <code>int</code>
	 *            
	 * @throws DaoException
	 */
	public int getQtLotesDevolvidosGerenciamentoMes ( Date dataInicioAp, Date dataFinalAp, Long linhaId, Long concessionariaId ) throws DaoException;
	
	/** Retorna a quantidade m�dia de dias entre a abertura  e o fechamento dos lotes
	 *  de um determinado per�odo de uma concession�ria.
	 *  
	 *  @param  dataInicioAp - Data de in�cio para apura��o
	 *  	<code>Date</code> Data para in�cio da apura��o dos dados para o relat�rio
	 *  
	 *  @param  dataFinalAp - Data final para apura��o
	 *  	<code>Date</code> Data para fechamento da apura��o dos dados para o relat�rio
	 *  
	 *  @param linhaId 
	 *  	<code>Long</code> Id da linha do produto
	 *  
	 *  @param concessionariaId 
	 *  	<code>Long</code> Id da concession�ria
	 *  
	 *  @return
	 *      Um valor <code>int</code>
	 *            
	 * @throws DaoException
	 */
	public int getMediaAberturaFechamentoLote ( Date dataInicioAp, Date dataFinalAp, Long linhaId, Long concessionariaId ) throws DaoException;
	
	/** M�todo tempor�rio. 
	 *  
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relat�rio Gr�fico Service Report.
	 *      
	 * @throws DaoException
	 */
	public String processa ( String texto ) throws DaoException;
}