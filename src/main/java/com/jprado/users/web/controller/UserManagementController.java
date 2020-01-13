package com.jprado.users.web.controller;

import javax.validation.Valid;

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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("user/")
public class UserManagementController {
	
	private final UserManagementService userManagementService;

    @RequestMapping(method = RequestMethod.GET, value = "{id}", produces = "application/json")
    public ResponseEntity<User> getUser(@PathVariable String id) {
    	
    	log.debug("User data requested for user {} ", id);
    	
    	return new ResponseEntity<User>(userManagementService.getUser(id), HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.PUT, value = "{id}", produces = "application/json")
    public ResponseEntity<Void> updateUser(@Valid @RequestBody User user, @PathVariable String id) {
    	
    	log.debug("User data updated for user {} ", id);
    	
    	userManagementService.updateUser(id, user);
   
    	return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
   
    
    
}
