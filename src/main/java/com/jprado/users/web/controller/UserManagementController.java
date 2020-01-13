package com.jprado.users.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jprado.users.UserDontExistUserManagementException;
import com.jprado.users.web.domain.User;
import com.jprado.users.web.service.UserManagementService;

@RestController
public class UserManagementController {
	
	Logger logger = LoggerFactory.getLogger("user-management.controller");

    UserManagementService userManagementService;

    @Autowired
    public UserManagementController(UserManagementService userManagementService) {
    	this.userManagementService = userManagementService;
	}

    @RequestMapping(method = RequestMethod.GET, value = "user/{id}", produces = "application/json")
    public ResponseEntity<User> placeToken(@PathVariable String id) {
    	
    	logger.debug("User data requested for user {} ", id);
    	
    	User user = null;
    	
    	try {
    		user = userManagementService.getUser(id);
    	} catch (UserDontExistUserManagementException e) {
    		logger.debug("The user {} was not found", id);
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    	} catch (Exception e) {
			logger.error("Unexpected exception thrown. Type: {} Message: {}", e.getClass(), e.getMessage());
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
       return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.PUT, value = "user/{id}", produces = "application/json")
    public ResponseEntity<Void> placeToken(@RequestBody User user, @PathVariable String id) {
    	
    	logger.debug("User data updated for user {} ", id);
    	
    	try {
    		userManagementService.updateUser(id, user);
    	} catch (UserDontExistUserManagementException e) {
    		logger.debug("The user {} was not found", id);
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    	} catch (Exception e) {
			logger.error("Unexpected exception thrown. Type: {} Message: {}", e.getClass(), e.getMessage());
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
       return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
   
    
    
}
