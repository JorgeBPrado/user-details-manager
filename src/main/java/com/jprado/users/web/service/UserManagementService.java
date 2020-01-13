package com.jprado.users.web.service;

import com.jprado.users.web.domain.User;

public interface UserManagementService {
	
	User getUser(String userId);
	
	void updateUser(String id, User userdata);
}
