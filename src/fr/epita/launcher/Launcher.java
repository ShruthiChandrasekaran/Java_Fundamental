package fr.epita.launcher;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.epita.Activities.CreateIdentity;
import fr.epita.Activities.DeleteAllIdentities;
import fr.epita.Activities.DeleteIdentity;
import fr.epita.Activities.ModifyIdentity;
import fr.epita.Activities.ReadIdentities;
import fr.epita.Activities.SearchIdentity;
import fr.epita.Services.ConnectionService;


public class Launcher {
	
	private static final Logger log = Logger.getLogger(Launcher.class.getName());
	public static final PrintStream myout =  new PrintStream(new FileOutputStream(FileDescriptor.out));
	
	public static void main(String[] args){
	
		String continueOperation = "yes";
		Scanner scanner = new Scanner(System.in);
		
		try {
			myout.println("--------------------------------");
			myout.println("Welcome to the IAM Core Software");
			myout.println("--------------------------------");
			
			if (authenticate(scanner)){
				while(continueOperation.equalsIgnoreCase("yes")){
					//Select the option to perform CRUD Operations on Identities
					myout.println("Select an operation: \n"
							+ "1. Create an Identity ?\n"
							+ "2. Search an Identity ?\n"
							+ "3. Modify an Identity ?\n"
							+ "4. Delete an Identity ?\n"
							+ "5. Search all the Identities. \n"
							+ "6. Delete all the Identities. \n"
							+ "7. Exit.");
					
					String identityOperation = scanner.nextLine();
					
					switch (identityOperation) {
						case "1":
							/**
							 * Create an Identity
							 */
							CreateIdentity create = new CreateIdentity();
							create.execute(scanner);
							break;
						case "2":
							/**
							 * Read an Identity
							 */
							SearchIdentity read = new SearchIdentity();
							read.execute(scanner);
							break;
						case "3":
							/**
							 * Update an Identity
							 */
							ModifyIdentity update = new ModifyIdentity();
							update.execute(scanner);
							break;
						case "4":
							/**
							 * Delete an Identity
							 */
							DeleteIdentity delete = new DeleteIdentity();
							delete.execute(scanner);
							break;
						case "5":
							/**
							 * Search all Identity
							 */
							ReadIdentities readAll = new ReadIdentities();
							readAll.execute(scanner);
							break;
						case "6":
							/**
							 * Delete all Identity
							 */
							DeleteAllIdentities deleteAll = new DeleteAllIdentities();
							deleteAll.execute(scanner);
							break;
						case "7":
							/**
							 * Quit
							 */
							myout.println("Thank you for using IAM Core Software");
							return;

						default:
							myout.println("Your Operation is not recognised.");
					}
					myout.println("Do you want to continue: yes/no?");
					continueOperation = scanner.nextLine();
					if (!continueOperation.equalsIgnoreCase("yes")){
						break;
					}
				}
			}
			end(scanner);
		}
		catch (Exception e) {
	        log.warning("Error in writing to file"+e);
	        }
		finally {
	        myout.close();
	    }
	}

	/**
	 * Exit Function
	 * @param scanner Exit
	 */
	private static void end(Scanner scanner) {
		myout.println("Thank you for using IAM Core Software");
		scanner.close();
	}
	
	/**
	 * Admin Authentication 
	 * @param scanner
	 * @return Permission to Access IAM Core
	 */
	private static boolean authenticate(Scanner scanner){
		myout.println("Enter your Admin ID:");
		String adminId = scanner.nextLine();
		myout.println("Enter your Admin Password:");
		String password = scanner.nextLine();
		boolean userAuthenticated = false;
		try{
			ResourceBundle props = ConnectionService.getConnectionProperties();
				if (adminId.equals(props.getString("user")) 
						&& password.equals(props.getString("password"))){
					myout.println("Admin Authenticated !");
					userAuthenticated = true;
				}
				else{
					myout.println("Admin Authentication failed. Check your ID and Password.");
					scanner.close();
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
