package fr.epita.Activities;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.epita.Datamodel.DataIdentity;
import fr.epita.Services.ConnectionService;
import fr.epita.Services.IdentityDAO;
import fr.epita.launcher.Launcher;

public class DeleteAllIdentities implements Activity {

	private static final Logger log = Logger.getLogger(Launcher.class.getName());
	public static final PrintStream myout =  new PrintStream(new FileOutputStream(FileDescriptor.out));
	/**
	 * Delete All execute function
	 * @param scanner Read from console
	 */
	@Override
	public void execute(Scanner scanner){
		myout.println("Deleting all the Identities from IAM Core");
		IdentityDAO identityDAO = new IdentityDAO();
		List<DataIdentity> identities;
		try {
			identities = identityDAO.readAll();
			deleteAllIdentity(identities, scanner);
		} catch (SQLException ex) {
			log.warning("Can not read from Database"+ ex);
		}
}
	
	private static void deleteAllIdentity(List<DataIdentity> identities, Scanner scanner) {
		if(!identities.isEmpty()) {
			myout.println("Are you sure you want to delete all the Identities? yes/no");
			String answer = scanner.nextLine();	
			
			if (answer.equalsIgnoreCase("yes")){			
			try {
				if(authenticate(scanner)) {
					deleteIdentity();
				}
				else {
					myout.println("Deleting all the Identity failed !");
				}
			} catch (Exception e) {
				log.log(Level.SEVERE, null, e);
			}
		}
	}
	else
		myout.println("There are no Identities in IAM Core. Please add some identities using Create operation !");				
		
	}

	private static void deleteIdentity() {
		IdentityDAO identityDAO = new IdentityDAO();
		try {
			identityDAO.deleteAll();
			myout.println("All the Identities are deleted from IAM Core");
		}
		catch(Exception ex) {
			log.log(Level.SEVERE, null, ex);
		}
	}

	private static boolean authenticate(Scanner scanner) {
		myout.println("If you are sure to delete all the Identites from IAM Core,\nPlease Enter Admin password:");
		String password = scanner.nextLine();
		boolean userAuthenticated = false;
		try{
			ResourceBundle props = ConnectionService.getConnectionProperties();
				if (password.equals(props.getString("password"))){
					userAuthenticated = true;
				}
				else{
					myout.println("Admin Authentication failed. Check your Password.");
					userAuthenticated = false;
				}
		}
		catch(Exception e){
			log.log(Level.SEVERE, null, e);
			return false;
		}
		return userAuthenticated;
	}
}
