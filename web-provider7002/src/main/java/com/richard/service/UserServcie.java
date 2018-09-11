package com.richard.service;

import java.util.List;

import com.richard.entity.User;

public interface UserServcie {

	List<User> getUser(User user, int page, int size);

	void addUser(User user);

	User getUserById(Integer id);

	void deleteUser(Integer userId);

	void updateUser(User user);

}
