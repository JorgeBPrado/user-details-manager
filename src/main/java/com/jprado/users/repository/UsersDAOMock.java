package com.jprado.users.repository;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.jprado.users.web.domain.User;

@Component
public class UsersDAOMock implements UsersDAO {
	
	HashMap<String, User> users;
	
	public UsersDAOMock() {
		
		User user1 = new User();
		user1.setFullname("John Doe");
		user1.setDescription("Contact me only at night");
		user1.setEmail("Idontexist@fake.com");
		
		User user2 = new User();
		user2.setFullname("Juan Exposito");
		user2.setDescription("Hey, I'm using FakeMessages");
		user2.setEmail("imnotreal@fake.com");
		
		users = new HashMap<String, User>();
		users.put("FAKE_ID_1", user1);
		users.put("FAKE_ID_2", user2);
			
	}

	@Override
	public User getUserById(String id) {
		if (isUserExist(id)) {
			return users.get(id);
		} else {
			throw new EntityDontExistRepositoryException(id);
		}
	}

	@Override
	public void updateUser(String id, User user) {
		if (isUserExist(id)) {
			users.put(id, user);
		} else {
			throw new EntityDontExistRepositoryException(id);
		}
	}
	
	private boolean isUserExist(String id) {
		return users.containsKey(id);
	}

}
