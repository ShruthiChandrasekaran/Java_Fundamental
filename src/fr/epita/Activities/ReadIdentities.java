/**
 * 
 */
package fr.epita.Activities;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.epita.Datamodel.DataIdentity;
import fr.epita.Services.ConnectionService;
import fr.epita.Services.IdentityDAO;
import fr.epita.launcher.Launcher;

/**
 * @author Shruthi C
 *
 */
public class ReadIdentities implements Activity{
	public static final PrintStream myout =  new PrintStream(new FileOutputStream(FileDescriptor.out));
	/**
	 * Search all Identities execute function
	 * @param scanner Read from console
	 */
	@Override
	public void execute(Scanner scanner){
		
		myout.println("All the Identities from IAM Core");
		IdentityDAO identityDAO = new IdentityDAO();
		
		try {
			if(authenticate(scanner)) {
				List<DataIdentity> identities =identityDAO.readAll();
				searchAllIdentities(identities);
			}
		}
			catch (Exception e) {
			Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, e);
			}
		}

	private static void searchAllIdentities(List<DataIdentity> identities) {
		if(!identities.isEmpty()) {
			DataIdentity dataId = new DataIdentity();
			myout.println(dataId.toTable(identities));			
		}
		else
			myout.println("There are no Identities in IAM Core. Please add some identities using Create operation !");		
	}

	private static boolean authenticate(Scanner scanner) {
		myout.println("Enter your Admin Password:");
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
			Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, e);
			return false;
		}
		return userAuthenticated;
	}
}
