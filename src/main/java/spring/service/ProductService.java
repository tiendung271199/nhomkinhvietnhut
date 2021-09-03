package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.dao.ProductDAO;
import spring.model.Product;

@Service
public class ProductService implements ICRUDService<Product> {

	@Autowired
	private ProductDAO productDAO;

	@Override
	public List<Product> getAll() {
		return productDAO.getAll();
	}

	@Override
	public int save(Product product) {
		return productDAO.save(product);
	}

	@Override
	public int update(Product product) {
		return productDAO.update(product);
	}

	@Override
	public int del(int id) {
		return productDAO.del(id);
	}

	@Override
	public Product findById(int id) {
		return productDAO.findById(id);
	}

	@Override
	public List<Product> getList(int offset, int rowCount) {
		return productDAO.getList(offset, rowCount);
	}

	@Override
	public int totalRow() {
		return productDAO.totalRow();
	}

	public int updateView(int id) {
		return productDAO.updateView(id);
	}

	public List<Product> searchByName(String name, int offset, int rowCount) {
		return productDAO.searchByName(name, offset, rowCount);
	}

	public int totalRowByName(String name) {
		return productDAO.totalRowByName(name);
	}

	public List<Product> getListByCat(int catId, int offset, int rowCount) {
		return productDAO.getListByCat(catId, offset, rowCount);
	}

	public int totalRowByCat(int catId) {
		return productDAO.totalRowByCat(catId);
	}

	public Product getNewProduct() {
		return productDAO.getNewProduct();
	}

}
