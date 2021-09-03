package spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import spring.model.ImageProduct;
import spring.model.Product;

@Repository
public class ImageProductDAO extends AbstractDAO<ImageProduct> {

	@Override
	public List<ImageProduct> getAll() {
		String sql = "SELECT * FROM image_product i INNER JOIN products p ON i.productId = p.id ORDER BY i.id DESC";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<ImageProduct>>() {
			List<ImageProduct> list = new ArrayList<ImageProduct>();

			@Override
			public List<ImageProduct> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					list.add(new ImageProduct(rs.getInt("i.id"), rs.getString("i.picture"),
							new Product(rs.getInt("p.id"), rs.getString("p.name"))));
				}
				return list;
			}
		});
	}

	@Override
	public List<ImageProduct> getList(int offset, int rowCount) {
		String sql = "SELECT * FROM image_product i INNER JOIN products p ON i.productId = p.id ORDER BY i.id DESC LIMIT ?,?";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<ImageProduct>>() {
			List<ImageProduct> list = new ArrayList<ImageProduct>();

			@Override
			public List<ImageProduct> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					list.add(new ImageProduct(rs.getInt("i.id"), rs.getString("i.picture"),
							new Product(rs.getInt("p.id"), rs.getString("p.name"))));
				}
				return list;
			}
		}, offset, rowCount);
	}

	@Override
	public int totalRow() {
		try {
			String sql = "SELECT COUNT(*) FROM image_product i INNER JOIN products p ON i.productId = p.id";
			return jdbcTemplate.queryForObject(sql, Integer.class);
		} catch (Exception e) {
			System.out.println("Total row ImageProduct: No data");
		}
		return 0;
	}

	@Override
	public int save(ImageProduct imageProduct) {
		String sql = "INSERT INTO image_product(picture, productId) VALUES (?,?)";
		return jdbcTemplate.update(sql, imageProduct.getPicture(), imageProduct.getProduct().getId());
	}

	@Override
	public int update(ImageProduct imageProduct) {
		String sql = "UPDATE image_product SET picture = ?, productId = ? WHERE id = ?";
		return jdbcTemplate.update(sql, imageProduct.getPicture(), imageProduct.getProduct().getId(),
				imageProduct.getId());
	}

	@Override
	public int del(int id) {
		String sql = "DELETE FROM image_product WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}

	public int delByProductId(int productId) {
		String sql = "DELETE FROM image_product WHERE productId = ?";
		return jdbcTemplate.update(sql, productId);
	}

	@Override
	public ImageProduct findById(int id) {
		try {
			String sql = "SELECT * FROM image_product i INNER JOIN products p ON i.productId = p.id WHERE i.id = ?";
			return jdbcTemplate.query(sql, new ResultSetExtractor<ImageProduct>() {
				ImageProduct imgProduct = new ImageProduct();

				@Override
				public ImageProduct extractData(ResultSet rs) throws SQLException, DataAccessException {
					if (rs.next()) {
						imgProduct = new ImageProduct(rs.getInt("i.id"), rs.getString("i.picture"),
								new Product(rs.getInt("p.id"), rs.getString("p.name")));

					}
					return imgProduct;
				}
			}, id);
		} catch (Exception e) {
			System.out.println("ImageProduct by ID " + id + ": No data");
		}
		return null;
	}

	public List<ImageProduct> findByProductId(int productId) {
		String sql = "SELECT * FROM image_product i INNER JOIN products p ON i.productId = p.id WHERE productId = ?";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<ImageProduct>>() {
			List<ImageProduct> list = new ArrayList<ImageProduct>();

			@Override
			public List<ImageProduct> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					list.add(new ImageProduct(rs.getInt("i.id"), rs.getString("i.picture"),
							new Product(rs.getInt("p.id"), rs.getString("p.name"))));
				}
				return list;
			}
		}, productId);
	}

	public int totalRowByProductId(int productId) {
		try {
			String sql = "SELECT COUNT(*) FROM image_product i INNER JOIN products p ON i.productId = p.id WHERE productId = ?";
			return jdbcTemplate.queryForObject(sql, Integer.class, productId);
		} catch (Exception e) {
			System.out.println("Total row ImageProduct by Product ID: " + productId + " No data");
		}
		return 0;
	}

}
