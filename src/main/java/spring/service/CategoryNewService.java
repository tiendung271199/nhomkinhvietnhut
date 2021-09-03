package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.dao.CategoryNewDAO;
import spring.model.Category_new;

@Service
public class CategoryNewService implements ICRUDService<Category_new> {

	@Autowired
	private CategoryNewDAO categoryNewDAO;

	@Override
	public List<Category_new> getAll() {
		return categoryNewDAO.getAll();
	}

	@Override
	public int save(Category_new category) {
		return categoryNewDAO.save(category);
	}

	@Override
	public int update(Category_new category) {
		return categoryNewDAO.update(category);
	}

	@Override
	public int del(int id) {
		return categoryNewDAO.del(id);
	}

	@Override
	public Category_new findById(int id) {
		return categoryNewDAO.findById(id);
	}

	@Override
	public List<Category_new> getList(int offset, int rowCount) {
		return categoryNewDAO.getList(offset, rowCount);
	}

	@Override
	public int totalRow() {
		return categoryNewDAO.totalRow();
	}

	public Category_new findByName(String name) {
		return categoryNewDAO.findByName(name);
	}

	public List<Category_new> searchByName(String name, int offset, int rowCount) {
		return categoryNewDAO.searchByName(name, offset, rowCount);
	}

	public int totalRowByName(String name) {
		return categoryNewDAO.totalRowByName(name);
	}

}
