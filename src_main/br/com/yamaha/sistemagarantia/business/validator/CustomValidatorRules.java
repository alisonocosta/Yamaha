/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......CustomValidatorRules.java
 *   .: Cria��o.....23 de abril, 12:01
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Objeto de neg�cios para a valida��es do Struts Validation.
 */
package br.com.yamaha.sistemagarantia.business.validator;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.struts.action.ActionMessages;

/** Classes de neg�cios contendo regras de valida��o.
 *
 *  @author edson.luiz
 */
public class CustomValidatorRules implements Serializable {

   
	private static final long serialVersionUID = -5793649134672481527L;

	/** Verifica se um CPF ou CNPJ � v�lido.
     * 
     *  Este m�todo utiliza funcionalidades do pr�prio
     *  validator fornecido pela Jakarta.
     * 
     *  @param bean       O bean no qual a valida��o ser� efetuada.
     *  @param va         O <code>ValidatorAction</code> usado na valida��o.
     *  @param field      O objeto <code>Field</code> associado ao campo validado.
     *  @param errors     Objeto que guarda os erros obtidos na valida��o.
     *  @param validator  A inst�ncia do validador, usada para acessar outros campos.
     *  @param request    Request HTTP.
     * 
     *  @return   <code>true</code> se a valida��o correr bem, ou, caso contr�rio,
     *            retornar� <code>false</code>.
     */
	public static boolean validateCpfCnpj( Object bean,
										   ValidatorAction va, 
										   Field field,	
										   ActionMessages errors,						             	   					             	   
						             	   Validator validator, 
						             	   HttpServletRequest request) {
		
		boolean isValid = true;
		
		//System.out.println("Inicio da valida��o do cnpj! ");
		
		String cpfOrCnpj;
        if (isString(bean))
        	cpfOrCnpj = (String) bean;
        else
        	cpfOrCnpj = ValidatorUtils.getValueAsString(bean, field.getProperty());
		
        //System.out.println("valida��o da cnpj! " + cpfOrCnpj);
        if (cpfOrCnpj == null) return false;
        
        // Removemos a m�cara, caso exista
		String newCpfOrCnpj = cpfOrCnpj.replaceAll("[^0-9]*","");
		
		// Reconhece se � CNPJ ou CPF
		boolean isCnpj = newCpfOrCnpj.length() == 14;
		boolean isCpf  = newCpfOrCnpj.length() == 11;
		
		
		if (!isCpf && !isCnpj) 
			
			isValid = false;
		
		else {
			
		        int i; 
		        int j;                 	// just count 
		        int digit;      		// A number digit
		        int coeficient; 		// A coeficient  
		        int sum;        		// The sum of (Digit * Coeficient)
		        int[] foundDv = {0,0}; 	// The found Dv1 and Dv2
		        int dv1 = Integer.parseInt(String.valueOf(newCpfOrCnpj.charAt(newCpfOrCnpj.length()-2)));
		        int dv2 = Integer.parseInt(String.valueOf(newCpfOrCnpj.charAt(newCpfOrCnpj.length()-1)));    
		        
		        for (j = 0; j < 2; j++) {
		        	
		            sum = 0;
		            coeficient = 2;
		            
		            for (i = newCpfOrCnpj.length() - 3 + j; i >= 0 ; i--){
		            	
		                digit = Integer.parseInt(String.valueOf(newCpfOrCnpj.charAt(i)));               
		                sum += digit * coeficient;
		                coeficient ++;
		                if (coeficient > 9 && isCnpj) coeficient = 2;   
		                
		            }    
		            
		            foundDv[j] = 11 - sum % 11;
		            if (foundDv[j] >= 10) foundDv[j] = 0;
		            
		        }
		        
		        isValid = dv1 == foundDv[0] && dv2 == foundDv[1];
		}
		
		return isValid;
		
	}
	
	/** Realiza a valida��o de um campo conforme um crit�rio.
     * 
     *  Este m�todo utiliza funcionalidades do pr�prio
     *  validator fornecido pela Jakarta.
     * 
     *  @param bean       O bean no qual a valida��o ser� efetuada.
     *  @param va         O <code>ValidatorAction</code> usado na valida��o.
     *  @param field      O objeto <code>Field</code> associado ao campo validado.
     *  @param errors     Objeto que guarda os erros obtidos na valida��o.
     *  @param validator  A inst�ncia do validador, usada para acessar outros campos.
     *  @param request    Request HTTP.
     * 
     *  @return   <code>true</code> se a valida��o correr bem, ou, caso contr�rio,
     *            retornar� <code>false</code>.
     */
	public static boolean validateRequeridWhen( Object bean,
										   ValidatorAction va, 
										   Field field,	
										   ActionMessages errors,						             	   					             	   
						             	   Validator validator, 
						             	   HttpServletRequest request) {
		
		boolean isValid = true;
			
		//System.out.println("ValidateRequiredWhen  - Inicio da verifica��o! ");
		
		String fieldValue;
		
        if (isString(bean))
        	fieldValue = (String) bean;
        else
        	fieldValue = ValidatorUtils.getValueAsString(bean, field.getProperty());
		
        //System.out.println("ValidateRequiredWhen - Valor do campo! " + fieldValue);
        
        if ( fieldValue == null || fieldValue.equals("") ){
        	
        	isValid = false;
        	
        }
		
		return isValid;
	}
    
    /** Retorna <code>true</code> se o objeto especificado for uma string.
     *
     * @param o O objeto a ser testado
     * @return <code>true</code> ou <code>false</code>.
     */
    protected static boolean isString(Object o) {
        return (o == null) ? true : String.class.isInstance(o);
    }    
    
}
