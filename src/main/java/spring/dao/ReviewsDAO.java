package spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import spring.model.Product;
import spring.model.Reviews;

@Repository
public class ReviewsDAO extends AbstractDAO<Reviews> {

	@Override
	public List<Reviews> getAll() {
		String sql = "SELECT * FROM reviews r INNER JOIN products p ON r.productId = p.id ORDER BY id DESC";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Reviews>>() {
			List<Reviews> list = new ArrayList<Reviews>();

			@Override
			public List<Reviews> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					list.add(new Reviews(rs.getInt("r.id"), new Product(rs.getInt("p.id"), rs.getString("p.name")),
							rs.getInt("rating"), rs.getString("r.name"), rs.getString("email"), rs.getString("content"),
							rs.getInt("status"), rs.getTimestamp("r.createAt")));
				}
				return list;
			}
		});
	}

	@Override
	public List<Reviews> getList(int offset, int rowCount) {
		String sql = "SELECT * FROM reviews r INNER JOIN products p ON r.productId = p.id ORDER BY id DESC LIMIT ?,?";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Reviews>>() {
			List<Reviews> list = new ArrayList<Reviews>();

			@Override
			public List<Reviews> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					list.add(new Reviews(rs.getInt("r.id"), new Product(rs.getInt("p.id"), rs.getString("p.name")),
							rs.getInt("rating"), rs.getString("r.name"), rs.getString("email"), rs.getString("content"),
							rs.getInt("status"), rs.getTimestamp("r.createAt")));
				}
				return list;
			}
		}, offset, rowCount);
	}

	@Override
	public int totalRow() {
		try {
			String sql = "SELECT COUNT(*) FROM reviews r INNER JOIN products p ON r.productId = p.id";
			return jdbcTemplate.queryForObject(sql, Integer.class);
		} catch (Exception e) {
			System.out.println("Total row reviews: No data");
		}
		return 0;
	}

	@Override
	public int save(Reviews reviews) {
		String sql = "INSERT INTO reviews(productId,rating,name,email,content) VALUES (?,?,?,?,?)";
		return jdbcTemplate.update(sql, reviews.getProduct().getId(), reviews.getRating(), reviews.getName(),
				reviews.getEmail(), reviews.getContent());
	}

	public int updateStatus(Reviews reviews) {
		String sql = "UPDATE reviews SET status = ? WHERE id = ?";
		return jdbcTemplate.update(sql, reviews.getStatus(), reviews.getId());
	}

	@Override
	public int del(int id) {
		String sql = "DELETE FROM reviews WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}
	
	public int delByProductId(int productId) {
		String sql = "DELETE FROM reviews WHERE productId = ?";
		return jdbcTemplate.update(sql, productId);
	}

	@Override
	public Reviews findById(int id) {
		String sql = "SELECT * FROM reviews r INNER JOIN products p ON r.productId = p.id WHERE r.id = ?";
		List<Reviews> list = jdbcTemplate.query(sql, new ResultSetExtractor<List<Reviews>>() {
			List<Reviews> list = new ArrayList<Reviews>();

			@Override
			public List<Reviews> extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					list.add(new Reviews(rs.getInt("r.id"), new Product(rs.getInt("p.id"), rs.getString("p.name")),
							rs.getInt("rating"), rs.getString("r.name"), rs.getString("email"), rs.getString("content"),
							rs.getInt("status"), rs.getTimestamp("r.createAt")));
				}
				return list;
			}
		}, id);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
