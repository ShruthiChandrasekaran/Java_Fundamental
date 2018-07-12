/**
 * 
 */
package fr.epita.Exceptions;

/**
 * @author Shruthi C
 *
 */
public class ConfigurationException extends Exception{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage(){
		return "Did not find configuration properties";
	}

}
