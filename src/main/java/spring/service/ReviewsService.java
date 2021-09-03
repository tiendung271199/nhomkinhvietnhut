package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.dao.ReviewsDAO;
import spring.model.Reviews;

@Service
public class ReviewsService implements ICRUDService<Reviews> {

	@Autowired
	private ReviewsDAO reviewsDAO;

	@Override
	public List<Reviews> getAll() {
		return reviewsDAO.getAll();
	}

	@Override
	public int save(Reviews reviews) {
		return reviewsDAO.save(reviews);
	}

	@Override
	public int update(Reviews reviews) {
		return 0;
	}

	public int updateStatus(Reviews reviews) {
		return reviewsDAO.updateStatus(reviews);
	}

	@Override
	public int del(int id) {
		return reviewsDAO.del(id);
	}

	public int delByProductId(int productId) {
		return reviewsDAO.delByProductId(productId);
	}

	@Override
	public Reviews findById(int id) {
		return reviewsDAO.findById(id);
	}

	@Override
	public List<Reviews> getList(int offset, int rowCount) {
		return reviewsDAO.getList(offset, rowCount);
	}

	@Override
	public int totalRow() {
		return reviewsDAO.totalRow();
	}

}
