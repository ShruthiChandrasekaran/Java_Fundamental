/**
 * 
 */
package fr.epita.Activities;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.epita.Datamodel.DataIdentity;
import fr.epita.Exceptions.IdentityExceptions;
import fr.epita.Services.IdentityDAO;
import fr.epita.launcher.Launcher;

/**
 * @author Shruthi C
 *
 */
public class ModifyIdentity implements Activity {
	
	private static final Logger log = Logger.getLogger(Launcher.class.getName());
	public static final PrintStream myout =  new PrintStream(new FileOutputStream(FileDescriptor.out));
	
	/**
	 * Update an Identity execute function
	 * @param scanner Read from console
	 */
	@Override
	public void execute(Scanner scanner){
		try {
			myout.println("Identity Updating...");
			IdentityDAO identityDAO = new IdentityDAO();
			List<DataIdentity> identities = identityDAO.readAll();
			if(!identities.isEmpty()) {
				myout.println("Select an Identity ID to update:");
				DataIdentity dataId = new DataIdentity();
				myout.println(dataId.toUpdateOrToSearch(identities));	
				String id = scanner.nextLine();
				DataIdentity foundIdentity = identityDAO.find(id);
				if (foundIdentity == null){
					myout.println("Identity Not Found : "+id);
				}
				else{
					if(updateIDentity(scanner,foundIdentity,identityDAO,dataId, id)) {
						myout.println("Identity Successfully Updated in IAM Core");
					}
					else
						myout.println("Update Operation Failed !");
				}		
			}
			else
				myout.println("There are no Identities in IAM Core. Please add some identities using Create operation !");		
		}
	catch(Exception ex)
	{
		log.log(Level.SEVERE, null, ex);
	}
		
	}

	private static boolean updateIDentity(Scanner scanner, DataIdentity foundIdentity, IdentityDAO identityDAO,DataIdentity dataId, String id ) throws IdentityExceptions, SQLException {
		boolean isUpdated = false;
		myout.println("Do you want to update identity: "+id+"  yes/no");
		String answer = scanner.nextLine();
		if (answer.equalsIgnoreCase("yes")){
			while(answer.equalsIgnoreCase("yes")){
				myout.println("Choose the field to update\n"
						+ "1. Username\n"
						+ "2. Firstname\n"
						+ "3. Lastname\n"
						+ "4. Email\n"
						+ "5. Password\n"
						+ "6. Update\n"); 
				String options = scanner.nextLine();
				switch(options){
					case "1":
						myout.println("Enter the new Username:");
						String username = scanner.nextLine();
						foundIdentity.setUserName(username);
						break;
					case "2":
						myout.println("Enter the new Firstname:");
						String firstname = scanner.nextLine();
						foundIdentity.setFirstname(firstname);
						break;
					case "3":
						myout.println("Enter the new Lastname:");
						String lastname = scanner.nextLine();
						foundIdentity.setLastname(lastname);
						break;
					case "4":
						myout.println("Enter the new Email");
						String email = scanner.nextLine();
						foundIdentity.setEmail(email);
						break;
					case "5":
						myout.println("Please enter new Password");
						String password = scanner.nextLine();
						foundIdentity.setPassword(password);
						break;
					case "6":
						answer = "no";
						break;
					default:
						answer = "yes";
				}
			}
			
			myout.println("This is the identity you updated: \n"+ dataId.identityInProcess(foundIdentity));
			identityDAO.update(foundIdentity);
			isUpdated = true;
		}
		return isUpdated;
	}
}
