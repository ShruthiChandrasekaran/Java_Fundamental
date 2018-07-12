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

public class SearchIdentity implements Activity {
	
	private static final Logger log = Logger.getLogger(Launcher.class.getName());
	public static final PrintStream myout =  new PrintStream(new FileOutputStream(FileDescriptor.out));
	
	/**
	 * Search an Identity execute function
	 * @param scanner Read from console
	 */
	@Override
	public void execute(Scanner scanner){
		try {
			myout.println("Listing an Identity...");
			IdentityDAO identityDAO = new IdentityDAO();
			List<DataIdentity> identities = identityDAO.readAll();
			if(!identities.isEmpty()) {
				myout.println("Select an Identity ID to search:");
				DataIdentity dataId = new DataIdentity();
				myout.println(dataId.toUpdateOrToSearch(identities));	
				String id = scanner.nextLine();
				DataIdentity foundIdentity = identityDAO.find(id);
				if (foundIdentity == null){
					myout.println("Identity Not Found : "+id);
				}
				else{
						myout.println("The Identity you wanted: \n"+dataId.identityInProcess(foundIdentity));		
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
}
