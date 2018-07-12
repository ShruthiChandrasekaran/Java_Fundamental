package fr.epita.Exceptions;

public class IdentityExceptions extends Exception {	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage(){
		return "Cannot connect to the database";
	}

}
