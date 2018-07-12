package fr.epita.Services;
import java.util.List;

import fr.epita.Datamodel.DataIdentity;
import fr.epita.Exceptions.AdminPermissionException;
import fr.epita.Exceptions.IdentityExceptions;

/**
 * DAO Interface
 * @author Shruthi C
 *
 * @param <T> Interface
 */
public interface DAO<T> {

	public void create(T entity) throws IdentityExceptions;
	
	public void read(T entity) throws IdentityExceptions;

	public void delete(T entity) throws IdentityExceptions;

	public void update(T entity) throws IdentityExceptions;

	public List<DataIdentity> search(T criteria) throws IdentityExceptions;
	
	public List<DataIdentity> deleteALl(T criteria) throws AdminPermissionException;
	
	public T find(final Object id ) throws IdentityExceptions;
	
	List<T> readAll() throws IdentityExceptions;

}

