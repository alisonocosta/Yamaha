/* Resource Tecnologia
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......DigestGenerator.java
 *   .: Cria��o.....22 de abril de 2008, 08:23
 *   .: Autor.......Thiago Uriel M. Garcia
 *   				Edson Luiz Sumensari
 *   .: Descri��o...Classe utilit�ria para a obten��o de digests (hash).
 */
package br.com.resource.infra.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/** Classe utilit�ria para a obten��o de digests (hash).
 *  Permite ainda o envio de chaves (keys) para melhorar
 *  a seguran�a de um digest.
 * 
 * 
 *  @see http://java.sun.com/j2se/1.4.2/docs/guide/security/CryptoSpec.html
 *  @see http://javaalmanac.com/egs/java.security/pkg.html
 */
public final class DigestGenerator {

    public static final String ALGORITHM_SHA1 = "SHA-1";
    public static final String ALGORITHM_MD2  = "MD2";
    public static final String ALGORITHM_MD5  = "MD5";
    
    /** Cria um digest utilizando um algoritmo solicitado.
     *  @param expression   String. Express�o a ser criptografia.
     *  @param algorithm    String. Algoritmo a ser utilizado para obten��o do hash.
     *      
     *  @return   Um hash hexadecimal.
     */
    public static String getDigest(String expression, String algorithm) 
      throws NoSuchAlgorithmException {

        byte[] expressionBytes = expression.getBytes();
        return getDigest(expressionBytes, algorithm);
        
    }
    
    /** Cria um digest utilizando um algoritmo solicitado.
     *  @param expression   byte[]. Array de bytes com a express�o a ser criptografia.
     *  @param algorithm    String. Algoritmo a ser utilizado para obten��o do hash.
     *
     *  @return   Um hash hexadecimal.
     */
    public static String getDigest(byte[] expression, String algorithm) 
      throws NoSuchAlgorithmException {
        
        MessageDigest msgDigest = MessageDigest.getInstance(algorithm);
        byte[] digest = msgDigest.digest(expression);
        
        StringBuffer hexString = new StringBuffer();       
        String       temp;
        
        for (int i=0; i<digest.length; i++) {
            
            temp = Integer.toHexString(0xFF & digest[i]);
            if (temp.length() == 1)
                temp = "0" + temp;
            
            hexString.append(temp);
        }
        
        return hexString.toString();

    }
    
    /** Cria um digest utilizando um algoritmo solicitado e uma chave.
     *  @param expression   String. Express�o a ser criptografia.
     *  @param key          String. Uma chave especial para ser "colada" � express�o.
     *  @param algorithm    String. Algoritmo a ser utilizado para obten��o do hash.
     *      
     *  @return   Um hash hexadecimal.
     */
    public static String getKeyedDigest(String expression, String key, String algorithm) 
      throws NoSuchAlgorithmException {
        
        byte[] expressionBytes = expression.getBytes();
        byte[] keyBytes        = key.getBytes();
        
        return getKeyedDigest(expressionBytes, keyBytes, algorithm);
        
    }
    
    /** Cria um digest utilizando um algoritmo solicitado.
     *  @param expression   byte[]. Array de bytes com a express�o a ser criptografia.
     *  @param key          byte[]. Array de bytes com a chave especial para ser "colada" � express�o.
     *  @param algorithm    String. Algoritmo a ser utilizado para obten��o do hash.
     *
     *  @return   Um hash hexadecimal.
     */
    public static String getKeyedDigest(byte[] expression, byte[] key, String algorithm) 
      throws NoSuchAlgorithmException {
        
        MessageDigest msgDigest = MessageDigest.getInstance(algorithm);
        msgDigest.update(expression);
        
        byte[] digest = msgDigest.digest(key);

        StringBuffer hexString = new StringBuffer();       
        String       temp;
        
        for (int i=0; i<digest.length; i++) {
            
            temp = Integer.toHexString(0xFF & digest[i]);
            if (temp.length() == 1)
                temp = "0" + temp;
            
            hexString.append(temp);
        }
        
        return hexString.toString();
        
    }
    
    /** Faz uma compara��o de bytes entre dois digests.
     *  @param digestA      byte[]. Digest a ser utilizado na compara��o.
     *  @param digestB      byte[]. Digest a ser utilizado na compara��o.
     *
     *  @return   Resultado da compra��o.
     */
    public static boolean compareDigests(String digestA, String digestB) {
        
        byte[] byteDigestA = digestA.getBytes();
        byte[] byteDigestB = digestB.getBytes();
        
        return compareDigests(byteDigestA, byteDigestB);

    }    
    
    /** Faz uma compara��o de bytes entre dois digests.
     *  @param digestA      byte[]. Digest a ser utilizado na compara��o.
     *  @param digestB      byte[]. Digest a ser utilizado na compara��o.
     *
     *  @return   Resultado da compara��o.
     */
    public static boolean compareDigests(byte[] digestA, byte[] digestB) {
        
        StringBuffer hexStringA = new StringBuffer();
        StringBuffer hexStringB = new StringBuffer();    
        
        String tempA;
        String tempB;
        
        for (int i=0; i<digestA.length; i++) {
            
            tempA = Integer.toHexString(0xFF & digestA[i]);
            if (tempA.length() == 1)
                tempA = "0" + tempA;
            
            hexStringA.append(tempA);
        }

        for (int i=0; i<digestB.length; i++) {
            
            tempB = Integer.toHexString(0xFF & digestB[i]);
            if (tempB.length() == 1)
                tempB = "0" + tempB;
            
            hexStringB.append(tempB);
        }
        
        //System.out.println(hexStringA.toString());
        //System.out.println(hexStringB.toString());
        
        if(hexStringA.toString().equals(hexStringB.toString()))
            return true;
        else
            return false;
        
    }
    
}