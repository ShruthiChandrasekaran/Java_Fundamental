package fr.epita.Datamodel;

import java.util.List;

import fr.epita.Services.TableBuilder;

public class DataIdentity {
	
	private String userName;
	private String uid;
	private String email;
	private String password;
	private String firstname;
	private String lastname;


public  DataIdentity() {
	super();
}

/**
 * DataIdentity Constructor
 * @param userName Username
 * @param email	User email
 */
public DataIdentity(String userName, String email) {
	super();
	this.userName = userName;
	this.email = email;
}

/**
 * DataIdentity Constructor
 * @param uid UserId
 * @param userName Usernaem
 * @param email User Email
 * @param password User Password
 * @param firstname User firstname
 * @param lastname User lastname
 */
public DataIdentity(String uid, String userName,
		String email, String password, String firstname, String lastname) {
	super();
	this.uid = uid;
	this.userName = userName;
	this.email = email;
	this.password = password;
	this.firstname = firstname;
	this.lastname = lastname;
}


//Getters
public String getUsername() {
	return userName;
}
public String getUid() {
	return uid;
}
public String getEmail() {
	return email;
}
public String getPassword() {
	return password;
}
public String getFirstname() {
	return firstname;
}
public String getLastname() {
	return lastname;
}


//Setters
public void setUserName(String userName) {
	this.userName = userName;
}

public void setUid(String uid) {
	this.uid = uid;
}

public void setEmail(String email) {
	this.email = email;
}
public void setPassword(String password) {
	this.password = password;
}
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
public void setLastname(String lastname) {
	this.lastname = lastname;
}

/**
 * Function to display in table format
 * @param identities DataIdentity
 * @return Table of Identities
 */
public String toTable(List<DataIdentity> identities) {
	TableBuilder tb= new TableBuilder();
	tb.addRow("UID","Username","FirstName","LastName","Email","Password");
	tb.addRow("---","--------","---------","--------","-----","--------");
	for(DataIdentity dataIdentity : identities) {
		tb.addRow(dataIdentity.getUid(),dataIdentity.getUsername(),dataIdentity.getFirstname(),dataIdentity.getLastname(),dataIdentity.getEmail(),dataIdentity.getPassword());
	}
	return tb.toString();	
}

/**
 * Display UserId and Username to update or search in a table format
 * @param identities DataIdentity
 * @return Table of UserId and Username
 */
public String toUpdateOrToSearch(List<DataIdentity> identities) {
	TableBuilder tb= new TableBuilder();
	tb.addRow("UserID","Username");
	tb.addRow("------","--------");
	for(DataIdentity dataIdentity : identities) {
		tb.addRow(dataIdentity.getUid(),dataIdentity.getUsername());
	}
	return tb.toString();
}

/**
 * Display an Identity in a table format
 * @param identity DataIdentity
 * @return Table of Identity created 
 */
public String identityInProcess(DataIdentity identity) {
	TableBuilder tb= new TableBuilder();
	if(identity.getUid() != null) {
		tb.addRow("UID","Username","FirstName","LastName","Email","Password");
		tb.addRow("---","--------","---------","--------","-----","--------");
		tb.addRow(identity.getUid(),identity.getUsername(),identity.getFirstname(),identity.getLastname(),identity.getEmail(),identity.getPassword());
	}
	else
	{
		tb.addRow("Username","FirstName","LastName","Email","Password");
		tb.addRow("--------","---------","--------","-----","--------");
		tb.addRow(identity.getUsername(),identity.getFirstname(),identity.getLastname(),identity.getEmail(),identity.getPassword());
	}
	return tb.toString();
}

}