/* Yamaha
 * Copyright (c) 2012 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......GarantiaArquivoDao.java
 *   .: Criação.....04 de Outubro de 2012
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Objeto de negócios para a entidade "GarantiaArquivoDao".
 */

package br.com.yamaha.sistemagarantia.dao;

import java.util.List;

import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.model.GarantiaArquivo;
import br.com.yamaha.sistemagarantia.model.GarantiaArquivoMoto;
import br.com.yamaha.sistemagarantia.model.GarantiaArquivoNtc;

public interface GarantiaArquivoDao extends Dao {
	
	/** Lista os arquivos por garantia
	 * 
	 * @param Integer garantiaId
	 * @return list
	 * 	<code>list</code> lista de arquivos.
	 * 
	 * @throws DaoException
	 */	
	 public List listFile(Integer garantiaId) throws DaoException;
	 
	 /** Recupera um arquivo de moto
	  * 
	  * @param GarantiaArquivo garantiaArquivo
	  * 
	  * @return byte[]
	  * 	<code>byte[]</code> Arquivo de moto.
	  * 
	  * @throws DaoException
	  */	
	 public byte[] getFileMoto(GarantiaArquivo garantiaArquivo) throws DaoException;
	 /** Recupera um arquivo de náutica
	  * 
	  * @param GarantiaArquivo garantiaArquivo
	  * 
	  * @return byte[] 
	  * 	<code>byte[] </code> Arquivo de moto.
	  * 
	  * @throws DaoException
	  */	
	 public byte[]  getFileNtc(GarantiaArquivo garantiaArquivo) throws DaoException;
	
}
