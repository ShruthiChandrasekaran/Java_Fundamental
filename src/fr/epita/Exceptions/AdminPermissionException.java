/**
 * 
 */
package fr.epita.Exceptions;

/**
 * @author Shruthi C
 *
 */
public class AdminPermissionException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage(){
		return "Admin Password does not match.";
	}

}
