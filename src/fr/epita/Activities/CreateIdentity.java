/**
 * 
 */
package fr.epita.Activities;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.epita.Datamodel.DataIdentity;
import fr.epita.Services.IdentityDAO;
import fr.epita.launcher.Launcher;

/**
 * @author Shruthi C
 *
 */
public class CreateIdentity implements Activity {
	
	private static final Logger log = Logger.getLogger(Launcher.class.getName());
	public static final PrintStream myout =  new PrintStream(new FileOutputStream(FileDescriptor.out));
	
	/**
	 * Create an Identity execute function
	 * @param scanner Read from console
	 */
	@Override
	public void execute(Scanner scanner){
		myout.println("Creating the Identity..");
		myout.println("Enter the username:");
		String username = scanner.nextLine();
		myout.println("Enter the first name:");
		String firstName = scanner.nextLine();
		myout.println("Enter the last name:");
		String lastName = scanner.nextLine();
		myout.println("Enter the email address:");
		String email = scanner.nextLine();
		myout.println("Enter the password:");
		String password = scanner.nextLine();
		
		ArrayList<String> createIdentity = new ArrayList<String>();
		createIdentity.add(username);
		createIdentity.add(firstName);
		createIdentity.add(lastName);
		createIdentity.add(email);
		createIdentity.add(password);
		
		if(createIdentity.contains(null) || createIdentity.contains("")) {
			myout.println("Some of the user details are null. Please verify and enter the details to Create.");
		}
		else {
			try {
				DataIdentity identity = new DataIdentity(null,username,
						email,password,firstName,lastName);
				IdentityDAO identityDAO = new IdentityDAO();
				
				//Check the User if exists already in Database
				DataIdentity checkUser = identityDAO.checkUserExists(identity.getUsername(), identity.getEmail());
				if(checkUser == null){
					identityDAO.create(identity);
					DataIdentity dataId = new DataIdentity();
					myout.println("The Identity created was:\n"+dataId.identityInProcess(identity));
					myout.println("The User \""+username+"\" created in the IAM Core.");
				}
				else {
					myout.println("The username is not available. Please do create a new Identity.");
				}
				
			} catch (Exception e) {
				log.log(Level.SEVERE, null, e);
			}

		}
	}
}
