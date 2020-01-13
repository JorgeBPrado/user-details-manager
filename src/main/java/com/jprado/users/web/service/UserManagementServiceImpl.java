package com.jprado.users.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jprado.users.UserDontExistUserManagementException;
import com.jprado.users.UserManagementApplicationException;
import com.jprado.users.repository.EntityDontExistRepositoryException;
import com.jprado.users.repository.RepositoryException;
import com.jprado.users.repository.UsersDAO;
import com.jprado.users.web.domain.User;

@Service
public class UserManagementServiceImpl implements UserManagementService {
	
	Logger logger = LoggerFactory.getLogger("user-management.service");

	private UsersDAO usersDAO;
	
	@Autowired
	public UserManagementServiceImpl(UsersDAO usersDAO) {
		this.usersDAO = usersDAO;
	}
	
	@Override
	public User getUser(String userId) {
		User user = null;
		
		logger.debug("Retrieving data from user {}", userId);
		
		try {
			user = this.usersDAO.getUserById(userId);
		} catch (EntityDontExistRepositoryException e) {
			String msg = String.format("User with id %s does not exist", userId);
			logger.error(msg);
			throw new UserDontExistUserManagementException(msg);
		} catch (RepositoryException e) {
			String msg = String.format("An error has occurred while retrieving user {}", userId);
			logger.error(msg);
			throw new UserManagementApplicationException(msg);
		}
		return user;
	}

	@Override
	public void updateUser(String userId, User userdata) {
		
		logger.debug("Updating data from user {}", userId);
		
		try {
			this.usersDAO.updateUser(userId, userdata);
		} catch (EntityDontExistRepositoryException e) {
			String msg = String.format("User with id %s does not exist and cant be updated", userId);
			logger.error(msg);
			throw new UserDontExistUserManagementException(msg);
		} catch (RepositoryException e) {
			String msg = String.format("An error has occurred while updating user {}", userId);
			logger.error(msg);
			throw new UserManagementApplicationException(msg);
		}
	}	
}
