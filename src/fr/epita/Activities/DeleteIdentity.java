/**
 * 
 */
package fr.epita.Activities;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;
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
public class DeleteIdentity implements Activity {
	
	private static final Logger log = Logger.getLogger(Launcher.class.getName());
	public static final PrintStream myout =  new PrintStream(new FileOutputStream(FileDescriptor.out));
	
	/**
	 * Delete an Identity execute function
	 * @param scanner Read from console
	 */
	@Override
	public void execute(Scanner scanner){
		myout.println("Deleting an Identity...");
		try {
			IdentityDAO identityDAO = new IdentityDAO();
			List<DataIdentity> identities = identityDAO.readAll();
			
			if(!identities.isEmpty()) {
				myout.println("Select an identity id");
				DataIdentity dataId = new DataIdentity();
				myout.println(dataId.toUpdateOrToSearch(identities));
				String identityId = scanner.nextLine();
				if (identityId.isEmpty() || !dataId.isInteger(identityId)) {
					myout.println("Entered Id is Invalid.");
					return;
				}
				DataIdentity foundIdentity = identityDAO.find(identityId);
				if (foundIdentity == null){
					myout.println("Identity Not Found in IAM Core Database : "+identityId);
				}
				else{
					myout.println("Do you really want to "
							+ "delete an Identity: " + identityId+ " yes/no");
					String answer = scanner.nextLine();
					if (answer.equalsIgnoreCase("yes")){
						myout.println("This is the identity you want to delete: \n"+dataId.identityInProcess(foundIdentity));
						identityDAO.delete(foundIdentity);
						myout.println("Identity Successfully Deleted in IAM Core");
					}
					else{
						myout.println("Delete Operation Failed !");
					}
				}
			}
			else
			{
				myout.println("There are no Identities in IAM Core. Please add some identities using Create operation !");		
			}	
		}
		catch(Exception ex) {
			log.log(Level.SEVERE, null, ex);
		
		}					
	}
}
