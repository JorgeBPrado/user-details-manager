package com.jprado.users.repository;

import com.jprado.users.UserManagementApplicationException;;

public class RepositoryException extends UserManagementApplicationException {

	public RepositoryException(String pMessage) {
		super(pMessage);
	}

}
