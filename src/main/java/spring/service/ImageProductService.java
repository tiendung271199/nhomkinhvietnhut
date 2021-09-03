package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.dao.ImageProductDAO;
import spring.model.ImageProduct;

@Service
public class ImageProductService implements ICRUDService<ImageProduct> {

	@Autowired
	private ImageProductDAO imageProductDAO;

	@Override
	public List<ImageProduct> getAll() {
		return imageProductDAO.getAll();
	}

	@Override
	public int save(ImageProduct imageProduct) {
		return imageProductDAO.save(imageProduct);
	}

	@Override
	public int update(ImageProduct imageProduct) {
		return imageProductDAO.update(imageProduct);
	}

	@Override
	public int del(int id) {
		return imageProductDAO.del(id);
	}
	
	public int delByProductId(int productId) {
		return imageProductDAO.delByProductId(productId);
	}

	@Override
	public ImageProduct findById(int id) {
		return imageProductDAO.findById(id);
	}

	@Override
	public List<ImageProduct> getList(int offset, int rowCount) {
		return imageProductDAO.getList(offset, rowCount);
	}

	public List<ImageProduct> findByProductId(int productId) {
		return imageProductDAO.findByProductId(productId);
	}

	@Override
	public int totalRow() {
		return imageProductDAO.totalRow();
	}
	
	public int totalRowByProductId(int productId) {
		return imageProductDAO.totalRowByProductId(productId);
	}

}
