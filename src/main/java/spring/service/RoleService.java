package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.dao.RoleDAO;
import spring.model.Role;

@Service
public class RoleService implements ICRUDService<Role> {

	@Autowired
	private RoleDAO roleDAO;

	@Override
	public List<Role> getAll() {
		return roleDAO.getAll();
	}

	@Override
	public int save(Role t) {
		return 0;
	}

	@Override
	public int update(Role t) {
		return 0;
	}

	@Override
	public int del(int id) {
		return 0;
	}

	@Override
	public Role findById(int id) {
		return null;
	}

	@Override
	public List<Role> getList(int offset, int rowCount) {
		return null;
	}

	@Override
	public int totalRow() {
		return 0;
	}

}