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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserManagementServiceImpl implements UserManagementService {
	

	private final UsersDAO usersDAO;
	
	@Override
	public User getUser(String userId) {
	
		log.debug("Retrieving data from user {}", userId);
		
		return this.usersDAO.getUserById(userId);
	}

	@Override
	public void updateUser(String userId, User userdata) {
		
		log.debug("Updating data from user {}", userId);
		
		this.usersDAO.updateUser(userId, userdata);
	}	
}
