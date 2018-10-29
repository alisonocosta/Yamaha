/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioDao.java
 *   .: Criação.....11 de julho, 15:04
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Interface de acesso ao banco para relatórios.
 */
package br.com.yamaha.sistemagarantia.dao;

import java.util.Date;
import java.util.List;

import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;

/** Interface de acesso ao banco para relatórios.
 * 
 *  @author Gisele Panosso
 */
public interface RelatorioDao extends Dao {

	/** Obtém as linhas do relatório: Relatório Zero Km. 
	 * 
	 *  @param dataIni
	 *  	Parâmetro data início  do relatório.
	 * 
	 *  @param dataFim
	 *  	Parâmetro data fim do relatório.
	 *  
	 *  @param km
	 *  	Parâmetro quilometragem do relatório.
	 *    
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioZeroKm(Date dataIni, Date dataFim, Long km, Long linha ) throws DaoException;

	/** Obtém as linhas do relatório: Relatório Analise de Peças. 
	 * 
	 *  @param dataIni
	 *  	Parâmetro data início  do relatório.
	 * 
	 *  @param dataFim
	 *  	Parâmetro data fim do relatório.
	 *  
	 *  @param tipoProduto
	 *  	Parâmetro tipo de produto do relatório.
	 *  
	 *  @param peca
	 *  	Parâmetro peça do relatório.	  
	 *  
	 *  @param modelo
	 *  	Parâmetro modelo do relatório.
	 *  
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioAnalisePecas( Date   dataIni
			                             , Date   dataFim
			                             , Long   tipoProduto
			                             , Long   peca
			                             , String modelo) throws DaoException;	
	
	/** Obtém as linhas do relatório: Relatório Pagamentos. 
	 * 
	 *  @param dataIni
	 *  	Parâmetro data início do relatório.
	 *  
	 *  @param dataFim
	 *  	Parâmetro data fim do relatório.
	 * 
	 *  @param concessionaria
	 *  	Parâmetro concessionaria do relatório.
	 *  
	 *  @param tipoServico
	 *  	Parâmetro tipoServico do relatório.
	 *  
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioPagamentos(Date dataIni, Date dataFim, Long concessionaria, String tipoServico ) throws DaoException;
	
	/** Obtém as linhas do relatório: Relatório Recusa. 
	 * 
	 *  @param dataIni
	 *  	Parâmetro data início do relatório.
	 *  
	 *  @param dataFim
	 *  	Parâmetro data fim do relatório.
	 *  
	 *  @param peca
	 *  	Parâmetro peça do relatório.
	 *  
	 *  @param modelo
	 *  	Parâmetro modelo do relatório.  
	 *  
	 *  @param concessionaria
	 *  	Parâmetro concessionaria do relatório.
	 *  
	 *  @param representante
	 *  	Parâmetro representante do relatório.
	 *  
	 *  @param analista
	 *  	Parâmetro analista do relatório.
	 *  
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioRecusa( Long   empresa
			                       , Long   concessionaria
			                       , Long   linha
			                       , String chassi
			                       , Date   dataIni
						           , Date   dataFim ) throws DaoException;	

	/** Obtém as linhas do relatório: Relatório Verificação. 
	 * 
	 *  @param dataIni
	 *  	Parâmetro data início do relatório.
	 *  
	 *  @param dataFim
	 *  	Parâmetro data fim do relatório.
	 * 
	 *  @param linha
	 *  	Parâmetro linha do relatório.
	 *  
	 *  @param concessionaria
	 *  	Parâmetro concessionaria do relatório.
	 *  
	 *  @param valorMinimo
	 *  	Parâmetro valorMinimo do relatório.
	 *  
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioVerificacao( Date   dataIni
							            , Date   dataFim
							            , Long   linha
							            , Long   concessionaria			                            
							            , Long   valorMinimo) throws DaoException;
	
	/** Obtém as linhas do relatório: Relatório Mensal por Modelo. 
	 * 
	 *  @param mesReferencia
	 *  	Parâmetro mês de referência do relatório.
     *  @param empresaId
     *      Empresa utilizada para busca.
	 *  
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioMensalModelo(String mesReferencia, Long empresaId) throws DaoException;
	
	/** Obtém as linhas do relatório: Relatório Mala Direta.
	 * 
	 *  @param dataIni
	 *  	Parâmetro data início do relatório.
	 *  
	 *  @param dataFim
	 *  	Parâmetro data fim do relatório.
	 *  
	 *  @param linha
	 *  	Parâmetro linha do relatório.
	 *  
	 *  @param empresa
	 *  	Parâmetro empresa do relatório.
	 *    
	 *  @param concessionaria
	 *  	Parâmetro concessionaria do relatório.
	 *    
	 *  @param estado
	 *  	Parâmetro estado do relatório.
	 *    
	 *  @param modelo
	 *  	Parâmetro modelo do relatório.
	 *  	    
	 *  @param chassiIni
	 *  	Parâmetro chassi inicial do relatório
	 *  	    
	 *  @param chassiFim
	 *  	Parâmetro chassi final do relatório
	 *    
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
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
	
	/** Obtém as linhas do relatório: Relatório Solicitação de Garantia. 
	 * 
	 *  @param lote
	 *  	Parâmetro lote do relatório.
	 *  
	 *  @param concessionaria
	 *  	Parâmetro concessionaria do relatório.	 
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioSolicitacaoGarantiaNota( Integer lote, Long concessionaria) throws DaoException;		

	/** Obtém as linhas do relatório: Relatório Solicitação de Garantia. 
	 * 
	 *  @param lote
	 *  	Parâmetro lote do relatório.
	 *  
	 *  @param concessionaria
	 *  	Parâmetro concessionaria do relatório.	 
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioSolicitacaoGarantia( Integer lote) throws DaoException;
	
	/** Obtém as linhas do relatório: Relatório Solicitação de Garantia. 
	 * 
	 *  @param lote
	 *  	Parâmetro lote do relatório.
	 *  
	 *  @param concessionaria
	 *  	Parâmetro concessionaria do relatório.	 
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioSolicitacaoGarantiaPeca( Integer lote, Long concessionaria) throws DaoException;
	
	/** Obtém as linhas do relatório: Relatório de Processamento de Revisoes - Fase1. 
	 * 
	 *  @param lote
	 *  	Parâmetro lote do relatório.
	 *  
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioProcRevisoesFase1(Long empresa, Integer lote) throws DaoException;	

	/** Obtém as linhas do relatório: Relatório de Processamento de Revisoes - Fase2. 
	 * 
	 *  @param lote
	 *  	Parâmetro lote do relatório.
	 *  
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioProcRevisoesFase2(Long empresa, Integer lote) throws DaoException;	
	
	/** Lista os dados das notas fiscais para o Relatório de Processamento de Peças - Fase2.
	 * 
	 * @param loteId
	 * 	<code>Integer</code> Id do lote
	 * 
	 * @return list
	 * 	<code>list</code> lista de notas do Relatório de Processamento de Peças - Fase2.
	 * 
	 * @throws DaoException
	 */
	public List listRelatorioNotasToProcPecasFase2( Integer loteId) throws DaoException;
	
	/** Obtém as linhas do relatório: Relatório Consulta de Notas Fiscais. 
	 * 
	 *  @param dataIni
	 *  	Parâmetro data início do relatório.
	 *  
	 *  @param dataFim
	 *  	Parâmetro data fim do relatório.
	 *  
	 *  @param lote
	 *  	Parâmetro lote do relatório.
	 *  
	 *  @param tipoLote
	 *  	Parâmetro tipoLote do relatório.
	 *  
	 *  @param concessionaria
	 *  	Parâmetro concessionaria do relatório.
	 *      
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioConsultaNfEmp( Date    dataIni
		                                   , Date    dataFim
		                                   , Long    linha
		                                   , Integer lote
		                                   , Long    tipoLote
		                                   , Long    concessionaria
		                                   , Long    empresa) throws DaoException;	
	
	/** Obtém as linhas do relatório: Relatório Consulta de Notas Fiscais. 
	 * 
	 *  @param dataIni
	 *  	Parâmetro data início do relatório.
	 *  
	 *  @param dataFim
	 *  	Parâmetro data fim do relatório.
	 *  
	 *  @param lote
	 *  	Parâmetro lote do relatório.
	 *  
	 *  @param tipoLote
	 *  	Parâmetro tipoLote do relatório.
	 *  
	 *  @param concessionaria
	 *  	Parâmetro concessionaria do relatório.
	 *      
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioConsultaNfConc( Date    dataIni
		                                   , Date    dataFim
		                                   , Long    linha
		                                   , Integer lote
		                                   , Long    tipoLote
		                                   , Long    concessionaria
		                                   , Long    empresa) throws DaoException;		
	
	/** Obtém as linhas do relatório: Relatório Consulta de Notas Fiscais. 
	 * 
	 *  @param dataIni
	 *  	Parâmetro data início do relatório.
	 *  
	 *  @param dataFim
	 *  	Parâmetro data fim do relatório.
	 *  
	 *  @param lote
	 *  	Parâmetro lote do relatório.
	 *  
	 *  @param tipoLote
	 *  	Parâmetro tipoLote do relatório.
	 *  
	 *  @param concessionaria
	 *  	Parâmetro concessionaria do relatório.
	 *      
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioConsultaNfLote( Date    dataIni
		                                   , Date    dataFim
		                                   , Long    linha
		                                   , Integer lote
		                                   , Long    tipoLote
		                                   , Long    concessionaria
		                                   , Long    empresa) throws DaoException;		

	/** Obtém as linhas do relatório: Relatório Consulta de Notas Fiscais. 
	 * 
	 *  @param dataIni
	 *  	Parâmetro data início do relatório.
	 *  
	 *  @param dataFim
	 *  	Parâmetro data fim do relatório.
	 *  
	 *  @param lote
	 *  	Parâmetro lote do relatório.
	 *  
	 *  @param tipoLote
	 *  	Parâmetro tipoLote do relatório.
	 *  
	 *  @param concessionaria
	 *  	Parâmetro concessionaria do relatório.
	 *      
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioConsultaNfNota( Date    dataIni
		                                   , Date    dataFim
		                                   , Long    linha
		                                   , Integer lote
		                                   , Long    tipoLote
		                                   , Long    concessionaria
		                                   , Long    empresa) throws DaoException;	

	/** Obtém as linhas do relatório: Relatório Consulta de Lotes. 
	 * 
	 *  @param dataIni
	 *  	Parâmetro data início do relatório.
	 *  
	 *  @param dataFim
	 *  	Parâmetro data fim do relatório.
	 *  
	 *  @param lote
	 *  	Parâmetro lote do relatório.
	 *  
	 *  @param tipoLote
	 *  	Parâmetro tipoLote do relatório.
	 *  
	 *  @param concessionaria
	 *  	Parâmetro concessionaria do relatório.
	 *      
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioConsultaConc( Date    dataIni
	                                     , Date    dataFim
	                                     , Long    linha
	                                     , Integer lote
	                                     , Long    tipoLote
	                                     , Long    concessionaria) throws DaoException;

	/** Obtém as linhas do relatório: Relatório Consulta de Lotes. 
	 * 
	 *  @param dataIni
	 *  	Parâmetro data início do relatório.
	 *  
	 *  @param dataFim
	 *  	Parâmetro data fim do relatório.
	 *  
	 *  @param lote
	 *  	Parâmetro lote do relatório.
	 *  
	 *  @param tipoLote
	 *  	Parâmetro tipoLote do relatório.
	 *  
	 *  @param concessionaria
	 *  	Parâmetro concessionaria do relatório.
	 *      
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioConsultaLote( Date    dataIni
	                                     , Date    dataFim
	                                     , Long    linha
	                                     , Integer lote
	                                     , Long    tipoLote
	                                     , Long    concessionaria) throws DaoException;
	
	
	/** Obtém as linhas do relatório: Relatório Consulta de Lotes. 
	 * 
	 *  @param dataIni
	 *  	Parâmetro data início do relatório.
	 *  
	 *  @param dataFim
	 *  	Parâmetro data fim do relatório.
	 *  
	 *  @param lote
	 *  	Parâmetro lote do relatório.
	 *  
	 *  @param tipoLote
	 *  	Parâmetro tipoLote do relatório.
	 *  
	 *  @param concessionaria
	 *  	Parâmetro concessionaria do relatório.
	 *      
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioConsultaLoteStatus( Date    dataIni
		                                       , Date    dataFim
		                                       , Long    linha
		                                       , Integer lote
		                                       , Long    tipoLote
		                                       , Long    concessionaria) throws DaoException;
		
	
	/** Obtém as linhas do relatório: Relatório de Protocolo de Documentos de Revisão.
	 *   
	 *  @param lote
	 *  	Parâmetro lote do relatório.
	 *	 
	 *     
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioDocRevisao( Integer lote) throws DaoException;		
	
	/** Obtém as linhas do relatório: Relatório de Protocolo de Documentos de Revisão.
	 *   
	 *  @param lote
	 *  	Parâmetro lote do relatório.
	 *  
	 *  @param concessionaria
	 *  	Parâmetro concessionaria do relatório.
	 *     
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioDocRevisaoDetalhe( Integer lote) throws DaoException;

	/** Obtém as linhas do relatório: Relatório de Processamento de Peças - Fase1. 
	 * 
	 *  @param empresa
	 *  	Parâmetro empresa do relatório. (Não obrigatório)
	 *   
	 *  @param lote
	 *  	Parâmetro lote do relatório.
	 *  
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioProcPecasFase1(Long empresa, Integer lote) throws DaoException;		

	
	/** Obtém as linhas do relatório: Relatório de Processamento de Peças - Fase2. 
	 *
	 * 	@param empresa
	 *  	Parâmetro empresa do relatório.
	 *  
	 *  @param lote
	 *  	Parâmetro lote do relatório.
	 *  
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioProcPecasFase2(Long empresa, Integer lote) throws DaoException;		

	
	/** Obtém as linhas do relatório: Relatório Consulta Histórico Chassi, dados do Cabeçalho. 
	 * 
	 *  @param chassi
	 *  	Parâmetro chassi do relatório.
	 *  
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioHistChassiCabec(String chassi) throws DaoException;
	
	/** Obtém as linhas do relatório: Relatório Consulta Histórico Chassi, dados das Revisões. 
	 * 
	 *  @param chassi
	 *  	Parâmetro chassi do relatório.
	 *  
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioHistChassiRevisao(String chassi) throws DaoException;
	
	/** Obtém as linhas do relatório: Relatório Consulta Histórico Chassi, dados das Garantias. 
	 * 
	 *  @param chassi
	 *  	Parâmetro chassi do relatório.
	 *  
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioHistChassiGarantia(String chassi) throws DaoException;
	
	/** Obtém as linhas do relatório: Relatório Consulta Histórico Chassi, dados das Pecas. 
	 * 
	 * 	@param garantia
	 *  Parâmetro garantia do relatório.
	 *  
	 *  @param chassi
	 *  	Parâmetro chassi do relatório.
	 *  
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioHistChassiPeca(Long garantia, String chassi) throws DaoException;
	
	/** Obtém as linhas do relatório: Relatório Consulta Histórico Chassi, parecer do Analista. 
	 * 	
	 * @param garantia
	 *  	Parâmetro garantia do relatório.
	 *  
	 *  @param chassi
	 *  	Parâmetro chassi do relatório.
	 *  
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioHistChassiParecer(Long garantia, String chassi) throws DaoException;
	
	/** Obtém as linhas do relatório: Relatório Consulta Histórico Chassi, totais da garantia, peças e terceiros. 
	 * 
	 *  @param chassi
	 *  	Parâmetro chassi do relatório.
	 *  
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioHistChassiTotais(String chassi) throws DaoException;

	/** Obtém as linhas do relatório: Relatório de Garantia e Peças. 
	 * 
	 *  @param empresa
	 *  	Parâmetro empresa do relatório
	 *  
	 *  @param linha
	 *  	Parâmetro linha do relatório.
     * 
	 *  @param ano
	 *  	Parâmetro ano do relatório.	
	 *   
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioGarantiaPecas(Long empresa, Long linha, String ano) throws DaoException;
	
	/** Obtém as linhas do relatório: Relatório de Garantia e Peças.
	 * 
	 *  @param empresa
	 *  	Parâmetro empresa do relatório.
	 *   
	 *  @param linha
	 *  	Parâmetro linha do relatório.
     * 
	 *  @param ano
	 *  	Parâmetro ano do relatório.	
	 *   
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioGPOrcPcsMobra(Long empresa, Long linha, String ano) throws DaoException;

	/** Obtém as linhas do relatório: Relatório de Garantia e Peças.
	 * 
	 *  @param empresa
	 *  	Parâmetro empresa do relatório.
	 *   
	 *  @param linha
	 *  	Parâmetro linha do relatório.
     * 
	 *  @param ano
	 *  	Parâmetro ano do relatório.	
	 *   
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioGPRevisao(Long empresa, Long linha, String ano) throws DaoException;
	
	/** Obtém as linhas do relatório: Relatório de Garantia e Peças.
	 * 
	 *  @param empresa
	 *  	Parâmetro empresa do relatório.
	 *   
	 *  @param linha
	 *  	Parâmetro linha do relatório.
     * 
	 *  @param ano
	 *  	Parâmetro ano do relatório.	
	 *   
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioGPOrcRev(Long empresa, Long linha, String ano) throws DaoException;
	
	/** Obtém as linhas do relatório: Relatório de Garantia e Peças.
	 * 
	 *  @param empresa
	 *  	Parâmetro empresa do relatório.
	 *   
	 *  @param linha
	 *  	Parâmetro linha do relatório.
     * 
	 *  @param ano
	 *  	Parâmetro ano do relatório.	
	 *   
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioGPOrcTotal(Long empresa, Long linha, String ano) throws DaoException;
	
	/** Obtém as linhas do relatório: Relatório de Garantia e Peças.
	 * 
	 *  @param empresa
	 *  	Parâmetro empresa do relatório.
	 *   
	 *  @param linha
	 *  	Parâmetro linha do relatório.
     * 
	 *  @param ano
	 *  	Parâmetro ano do relatório.	
	 *   
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioGPVlFatur(Long empresa, Long linha, String ano) throws DaoException;
	
	/** Obtém as linhas do relatório: Relatório de Garantia e Peças.
	 * 
	 *  @param empresa
	 *  	Parâmetro empresa do relatório.
	 *   
	 *  @param linha
	 *  	Parâmetro linha do relatório.
     * 
	 *  @param ano
	 *  	Parâmetro ano do relatório.	
	 *   
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioGPQtdes(Long empresa, Long linha, String ano) throws DaoException;

	/** Obtém as linhas do relatório: Relatório de Garantia e Peças por Modelo.
	 * 
	 *  @param concessionaria
	 *  	Parâmetro concessionaria do relatório.
	 *   
	 *  @param linha
	 *  	Parâmetro linha do relatório.
     * 
	 *  @param ano
	 *  	Parâmetro ano (yyyy) do relatório.
	 *  	
	 *  @param modelo
	 *  	Parâmetro modelo do relatório.	
	 *   
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioGarantiaPecasModelo(Long concessionaria, Long linha, String ano, String modelo) throws DaoException;
	
	/** Obtém as linhas do relatório: Relatório de Garantia e Peças por Modelo.
	 * 
	 *  @param concessionaria
	 *  	Parâmetro concessionaria do relatório.
	 *   
	 *  @param linha
	 *  	Parâmetro linha do relatório.
     * 
	 *  @param ano
	 *  	Parâmetro ano (yyyy) do relatório.
	 *  	
	 *  @param modelo
	 *  	Parâmetro modelo do relatório.	
	 *   
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioGPMRevisao(Long concessionaria, Long linha, String ano, String modelo) throws DaoException;
	
	/** Obtém as linhas do relatório: Relatório de Garantia e Peças por Modelo.
	 * 
	 *  @param concessionaria
	 *  	Parâmetro concessionaria do relatório.
	 *   
	 *  @param linha
	 *  	Parâmetro linha do relatório.
     * 
	 *  @param ano
	 *  	Parâmetro ano (yyyy) do relatório.
	 *  	
	 *  @param modelo
	 *  	Parâmetro modelo do relatório.	
	 *   
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioGPMVlFatur(Long concessionaria, Long linha, String ano, String modelo) throws DaoException;
	
	/** Obtém as linhas do relatório: Relatório de Garantia e Peças por Modelo.
	 * 
	 *  @param concessionaria
	 *  	Parâmetro concessionaria do relatório.
	 *   
	 *  @param linha
	 *  	Parâmetro linha do relatório.
     * 
	 *  @param ano
	 *  	Parâmetro ano (yyyy) do relatório.
	 *  	
	 *  @param modelo
	 *  	Parâmetro modelo do relatório.	
	 *   
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioGPMQtdes(Long concessionaria, Long linha, String ano, String modelo) throws DaoException;

	/** Obtém as linhas do relatório: Relatório de Garantia e Peças por Modelo.
	 * 
	 *  @param ano
	 *  	Parâmetro ano (yyyy) do relatório.
	 *  
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioGPMDolar(String ano) throws DaoException;

	/** Obtém as linhas do relatório: Relatório Imported Parts Claim List.
	 * 
	 *  @param dataIni
	 *  	Parâmetro data início do relatório.
	 *  
	 *  @param linha
	 *  	Parâmetro linha do relatório.
	 *  
	 *  @param empresa
	 *  	Parâmetro empresa do relatório.
	 *  
	 *  @param fornecedor
	 *  	Parâmetro fornecedor do relatório.  
	 *  
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioImportedParts( Date   dataIni
								          , Date   dataFim
								          , Long   linha
								          , Long   empresa
								          , String fornecedor) throws DaoException;

	/** Obtém as linhas do relatório: Relatório Imported Parts Claim List - Invoice.
	 * 
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioImportedPartsInvoice1() throws DaoException;	
	
	/** Obtém as linhas do relatório: Relatório Imported Parts Claim List - Invoice.
	 * 
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioImportedPartsInvoice2( String agencia
			                                      , String banco
			                                      , String conta) throws DaoException;	
	
	/** Obtém as linhas do relatório: Relatório Imported Parts Claim List - Invoice.
	 * 
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioImportedPartsInvoice3( String ano
			                                      , String mes) throws DaoException;	
	
	/** Obtém as linhas do relatório: Relatório Imported Parts Claim List - Invoice.
	 * 
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioImportedPartsInvoice4( Long empresa) throws DaoException;
	
	/** Obtém as linhas do relatório: Relatório Imported Parts Claim List - Invoice.
	 * 
	 *  @return
	 *  	Listagem de registros.
	 *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
	 */
	public List listRelatorioImportedPartsInvoice5( String fornecedor) throws DaoException;	
	
	/** Lista os dados de Faturamento de um determinado período, de um empresa( ou geral) e linha de produto.
	 *  
	 *      
	 *  @param  orgId - Id da Organização
	 *  	<code>Long</code> Número que representa o id da Organização
	 *  
	 *  @param  dataInicioAp - Data de início para apuração
	 *  	<code>Date</code> Data para início da apuração dos dados para o relatório
	 *  
	 *  @param  dataFinalAp - Data final para apuração
	 *  	<code>Date</code> Data para fechamento da apuração dos dados para o relatório
	 *  
	 *  @param  linhaId - Id da Organização
	 *  	<code>Long</code> Linha do Produto
	 *  
	 *  @param  modelo - modelo definido para incluir no gráfico
	 *  	<code>String</code> Grupo Modelo do Produto
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Gráfico de Faturamento x Garantia.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */
	public List listFaturamento ( Long orgId, Date dataInicioAp, Date dataFinalAp, Long linhaId, String modelo ) throws DaoException;
	
	/** Lista a quantidade de Faturamento de um determinado período, de um empresa( ou geral) por linha de produto.
	 *  
	 *      
	 *  @param  orgId - Id da Organização
	 *  	<code>Long</code> Número que representa o id da Organização
	 *  
	 *  @param  dataInicioAp - Data de início para apuração
	 *  	<code>Date</code> Data para início da apuração dos dados para o relatório
	 *  
	 *  @param  dataFinalAp - Data final para apuração
	 *  	<code>Date</code> Data para fechamento da apuração dos dados para o relatório
	 *  
	 *  @param  linhaId - Id da Organização
	 *  	<code>Long</code> Linha do Produto
	 *  
	 *  @param  modelo - modelo definido para incluir no gráfico
	 *  	<code>String</code> Grupo Modelo do Produto
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Gráfico de Faturamento x Garantia.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */
	public List listQtdeFaturamento ( Long orgId, Date dataInicioAp, Date dataFinalAp, Long linhaId, String modelo ) throws DaoException; 
	
	/** Lista os dados de Faturamento e Garantia para o Relatório de Gráficos Individuais .
	 *  
	 *      
	 *  @param  orgId - Id da Organização
	 *  	<code>Long</code> Número que representa o id da Organização
	 *  
	 *  @param  dataInicioAp - Data de início para apuração
	 *  	<code>Date</code> Data para início da apuração dos dados para o relatório
	 *  
	 *  @param  dataFinalAp - Data final para apuração
	 *  	<code>Date</code> Data para fechamento da apuração dos dados para o relatório
	 *  
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Gráfico de Faturamento x Garantia.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */
	public List listFaturamentoByGarantia ( Long orgId, Date dataInicioAp, Date dataFinalAp ) throws DaoException;
	
	
	/** Lista os dados de Faturamento e Garantia para o Relatório de Gráficos Individuais  por Linha - Gráfico 1.
	 *  
	 *      
	 *  @param  orgId - Id da Organização
	 *  	<code>Long</code> Número que representa o id da Organização
	 *  
	 *  @param  dataInicioAp - Data de início para apuração
	 *  	<code>Date</code> Data para início da apuração dos dados para o relatório
	 *  
	 *  @param  dataFinalAp - Data final para apuração
	 *  	<code>Date</code> Data para fechamento da apuração dos dados para o relatório
	 *  
	 *  @param  linhaId - Id da Organização
	 *  	<code>Long</code> Linha do Produto
	 *  
	 *  @param  modelo - Modelo do produto
	 *  	<code>String</code> modelo do produto para o filtro 
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Gráfico de Faturamento x Garantia.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */
	public List listFaturamentoByGarantiaANDLinha ( Long orgId, Date dataInicioAp, Date dataFinalAp, Long linhaId, String modelo) throws DaoException;
	
	/** Lista os dados de Faturamento e Garantia para o Relatório de Gráficos Individuais Linha - Gráfico 1.
	 *  
	 *      
	 *  @param  orgId - Id da Organização
	 *  	<code>Long</code> Número que representa o id da Organização
	 *  
	 *  @param  dataInicioAp - Data de início para apuração
	 *  	<code>Date</code> Data para início da apuração dos dados para o relatório
	 *  
	 *  @param  dataFinalAp - Data final para apuração
	 *  	<code>Date</code> Data para fechamento da apuração dos dados para o relatório
	 *  
	 *  @param  modelo - Modelo do produto
	 *  	<code>String</code> modelo do produto para o filtro  
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Gráfico de Faturamento x Garantia.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */
	public List listGraphFaturamentoByGarantiaANDLinha ( Long orgId, Date dataInicioAp, Date dataFinalAp, String modelo ) throws DaoException;
	
	
	/** Lista os dados de Faturamento e Quant. Garantia para o Relatório de Gráficos Individuais - Gráfico 3.
	 *  
	 *      
	 *  @param  orgId - Id da Organização
	 *  	<code>Long</code> Número que representa o id da Organização
	 *  
	 *  @param  dataInicioAp - Data de início para apuração
	 *  	<code>Date</code> Data para início da apuração dos dados para o relatório
	 *  
	 *  @param  dataFinalAp - Data final para apuração
	 *  	<code>Date</code> Data para fechamento da apuração dos dados para o relatório
	 *  
	 *  @param  linhaId - Id da linha do produto
	 *  	<code>Long</code> Número que representa o id da linha
	 *
	 *  @param  modelo - modelo definido para incluir no gráfico
	 *  	<code>String</code> Grupo Modelo do Produto
	 *
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Gráfico de Faturamento x Garantia.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */
	public List listFaturamentoByQtdeGarantia ( Long orgId, Date dataInicioAp, Date dataFinalAp, Long linhaId, String modelo ) throws DaoException;
	
	/** Lista os dados de VALOR ACUMULADO GARANTIA 0 KM x INDICE GARANTIA 0 KM para o Relatório de Gráficos Individuais - Gráfico 5.
	 *  
	 *      
	 *  @param  orgId - Id da Organização
	 *  	<code>Long</code> Número que representa o id da Organização
	 *  
	 *  @param  dataInicioAp - Data de início para apuração
	 *  	<code>Date</code> Data para início da apuração dos dados para o relatório
	 *  
	 *  @param  dataFinalAp - Data final para apuração
	 *  	<code>Date</code> Data para fechamento da apuração dos dados para o relatório
	 *  
	 *  @param  linhaId - Id da linha do produto
	 *  	<code>Long</code> Id da linha do produto
	 *  
	 *  @param  modelo - modelo definido para incluir no gráfico
	 *  	<code>String</code> Grupo Modelo do Produto
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Gráfico de Faturamento x Garantia.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */
	public List listAcumuladoGarantiaByIndice ( Long orgId, Date dataInicioAp, Date dataFinalAp, Long linhaId, String modelo ) throws DaoException;
	
	/** Lista os dados das PRINCIPAIS PEÇAS para o Relatório de Gráficos Individuais - Gráfico 6 (Tabela). 
	 *  
	 *      
	 *  @param  orgId - Id da Organização
	 *  	<code>Long</code> Número que representa o id da Organização
	 *  
	 *  @param  dataFinalAp - Data final para apuração
	 *  	<code>Date</code> Data para fechamento da apuração dos dados para o relatório  
	 *  
	 *  @param  linhaId - Id da Linha do produto
	 *  	<code>Long</code> Id da Linha do produto  
	 *  
	 *  @param  modelo - Modelo do produto
	 *  	<code>String</code> modelo do produto para o filtro
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Gráfico de PRINCIPAIS PEÇAS.
	 *      
	 * @throws DaoException
	 */
	public List listValuesTablePeca ( Long orgId, Date dataFinalAp, Long linhaId, String modelo ) throws DaoException;
	
	/** Lista os dados das PRINCIPAIS PEÇAS para o Relatório de Gráficos Individuais por Linha- Gráfico 6 (Tabela). 
	 *  
	 *      
	 *  @param  orgId - Id da Organização
	 *  	<code>Long</code> Número que representa o id da Organização
	 *  
	 *  @param  dataFinalAp - Data final para apuração
	 *  	<code>Date</code> Data para fechamento da apuração dos dados para o relatório  
	 *  
	 *  @param  modelo - Modelo
	 *  	<code>String</code> Modelo do produto  
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Gráfico de PRINCIPAIS PEÇAS.
	 *      
	 * @throws DaoException
	 */
	public List listValuesTablePecaByModelo ( Long orgId, Date dataFinalAp, String modelo ) throws DaoException;
	
	/** Lista os dados para os gráficos relatório Service Report. 
	 *  
	 *      
	 *  @param  orgId - Id da Organização
	 *  	<code>Long</code> Número que representa o id da Organização
	 *  
	 *  @param  dataAp - Datapara apuração
	 *  	<code>Date</code> Data para fechamento da apuração dos dados para o relatório  
	 *  
	 *  @param part - parte
	 *  	<code>int</code> parte do relatório solicitada  
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Gráfico Service Report.
	 *      
	 * @throws DaoException
	 */
	public List listValuesServiceReportParte ( Long orgId, Date dataAp, int part ) throws DaoException;
	
	/** Lista os dados de Garantia Paga por Modelo para o Relatório Gráfico - Garantia Paga por Modelo de Produto.
	 *  
	 *      
	 *  @param  orgId - Id da Organização
	 *  	<code>Long</code> Número que representa o id da Organização
	 *  
	 *   *  @param  grupoModelo - Grupo do modelo
	 *  	<code>String</code> Quando for nulo, recupera todos os grupos
	 *  
	 *  @param  dataInicioAp - Data de início para apuração
	 *  	<code>Date</code> Data para início da apuração dos dados para o relatório
	 *  
	 *  @param  dataFinalAp - Data final para apuração
	 *  	<code>Date</code> Data para fechamento da apuração dos dados para o relatório
	 *  
	 *  @param  typeGraph - Tipo da recuperação dos dados
	 *  	<code>String</code> True para acumulado	
	 *  
	 *  @param linhaId 
	 *  	<code>Long</code> Id da linha do produto
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Gráfico de Faturamento x Garantia.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */
	public List listGarantiaPagaByModelo ( Long orgId, String grupoModelo, Date dataInicioAp, Date dataFinalAp, String typeGraph, Long linhaId ) throws DaoException;
	
	/** Lista os dados para o Relatório Gráfico Garantia Mensal Percentual. 
	 *  
	 *      
	 *  @param  orgId - Id da Organização
	 *  	<code>Long</code> Número que representa o id da Organização
	 *  
	 *  @param  grupoModelo - Grupo do modelo
	 *  	<code>String</code> Quando for nulo, recupera todos os grupos
	 *  
	 *  @param  dataInicioAp - Data de início para apuração
	 *  	<code>Date</code> Data para início da apuração dos dados para o relatório
	 *  
	 *  @param  dataFinalAp - Data final para apuração
	 *  	<code>Date</code> Data para fechamento da apuração dos dados para o relatório
	 *  
	 *  @param linhaId 
	 *  	<code>Long</code> Id da linha do produto
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Gráfico de PRINCIPAIS PEÇAS.
	 *      
	 * @throws DaoException
	 */
	public List listValuesGraphGarantiaMensalPercentual ( Long orgId, String grupoModelo, Date dataInicioAp, Date dataFinalAp, Long linhaId ) throws DaoException;
	
	/** Lista os dados para o Relatório Gerenciamento Mês. 
	 *  
	 *  
	 *  @param  dataInicioAp - Data de início para apuração
	 *  	<code>Date</code> Data para início da apuração dos dados para o relatório
	 *  
	 *  @param  dataFinalAp - Data final para apuração
	 *  	<code>Date</code> Data para fechamento da apuração dos dados para o relatório
	 *  
	 *  @param linhaId 
	 *  	<code>Long</code> Id da linha do produto
	 *  
	 *  @param concessionariaId 
	 *  	<code>Long</code> Id da concessionária
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Gráfico de PRINCIPAIS PEÇAS.
	 *      
	 * @throws DaoException
	 */
	public List listValuesGerenciamentoMes ( Date dataInicioAp, Date dataFinalAp, Long linhaId, Long concessionariaId ) throws DaoException;
	
	/** Lista os dados para o Relatório Gerenciamento Mês. 
	 *  
	 *  
	 *  @param  dataInicioAp - Data de início para apuração
	 *  	<code>Date</code> Data para início da apuração dos dados para o relatório
	 *  
	 *  @param  dataFinalAp - Data final para apuração
	 *  	<code>Date</code> Data para fechamento da apuração dos dados para o relatório
	 *  
	 *  @param linhaId 
	 *  	<code>Long</code> Id da linha do produto
	 *  
	 *  @param concessionariaId 
	 *  	<code>Long</code> Id da concessionária
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Gráfico de PRINCIPAIS PEÇAS.
	 *      
	 * @throws DaoException
	 */
	public int getQtLotesLiberadosGerenciamentoMes ( Date dataInicioAp, Date dataFinalAp, Long linhaId, Long concessionariaId ) throws DaoException;
	
	/** Retorna a quantidade de lotes devolvidos de um determinado período de uma concessionária e de uma determinada linha. 
	 *  
	 *  
	 *  @param  dataInicioAp - Data de início para apuração
	 *  	<code>Date</code> Data para início da apuração dos dados para o relatório
	 *  
	 *  @param  dataFinalAp - Data final para apuração
	 *  	<code>Date</code> Data para fechamento da apuração dos dados para o relatório
	 *  
	 *  @param linhaId 
	 *  	<code>Long</code> Id da linha do produto
	 *  
	 *  @param concessionariaId 
	 *  	<code>Long</code> Id da concessionária
	 *  
	 *  @return
	 *      Um valor <code>int</code>
	 *            
	 * @throws DaoException
	 */
	public int getQtLotesDevolvidosGerenciamentoMes ( Date dataInicioAp, Date dataFinalAp, Long linhaId, Long concessionariaId ) throws DaoException;
	
	/** Retorna a quantidade média de dias entre a abertura  e o fechamento dos lotes
	 *  de um determinado período de uma concessionária.
	 *  
	 *  @param  dataInicioAp - Data de início para apuração
	 *  	<code>Date</code> Data para início da apuração dos dados para o relatório
	 *  
	 *  @param  dataFinalAp - Data final para apuração
	 *  	<code>Date</code> Data para fechamento da apuração dos dados para o relatório
	 *  
	 *  @param linhaId 
	 *  	<code>Long</code> Id da linha do produto
	 *  
	 *  @param concessionariaId 
	 *  	<code>Long</code> Id da concessionária
	 *  
	 *  @return
	 *      Um valor <code>int</code>
	 *            
	 * @throws DaoException
	 */
	public int getMediaAberturaFechamentoLote ( Date dataInicioAp, Date dataFinalAp, Long linhaId, Long concessionariaId ) throws DaoException;
	
	/** Método temporário. 
	 *  
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Gráfico Service Report.
	 *      
	 * @throws DaoException
	 */
	public String processa ( String texto ) throws DaoException;
}