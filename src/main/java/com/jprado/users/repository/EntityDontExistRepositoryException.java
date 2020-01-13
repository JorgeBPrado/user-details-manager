package com.jprado.users.repository;

import com.jprado.users.UserManagementApplicationException;;

public class EntityDontExistRepositoryException extends UserManagementApplicationException {

	public EntityDontExistRepositoryException(String id) {
		super(String.format("Entity with id %s does not exist", id));
	}

}
