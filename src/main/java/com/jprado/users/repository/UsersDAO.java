package com.jprado.users.repository;

import com.jprado.users.web.domain.User;

public interface UsersDAO {
	
	User getUserById(String id);
	
	void updateUser(String id, User user);

}
