package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.dao.UserDAO;
import spring.model.User;

@Service
public class UserService implements ICRUDService<User> {

	@Autowired
	private UserDAO userDAO;

	@Override
	public List<User> getAll() {
		return userDAO.getAll();
	}

	@Override
	public int save(User user) {
		return userDAO.save(user);
	}

	@Override
	public int update(User user) {
		return userDAO.update(user);
	}

	public int updateEnabled(User user) {
		return userDAO.updateEnabled(user);
	}

	@Override
	public int del(int id) {
		return userDAO.del(id);
	}

	@Override
	public User findById(int id) {
		return userDAO.findById(id);
	}

	@Override
	public List<User> getList(int offset, int rowCount) {
		return userDAO.getList(offset, rowCount);
	}

	@Override
	public int totalRow() {
		return userDAO.totalRow();
	}

	public User findByUsername(String username) {
		return userDAO.findByUsername(username);
	}

}