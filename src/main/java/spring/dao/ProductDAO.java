package spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import spring.model.Category;
import spring.model.Product;
import spring.model.User;

@Repository
public class ProductDAO extends AbstractDAO<Product> {

	@Override
	public List<Product> getAll() {
		String sql = "SELECT * FROM products p INNER JOIN categories c ON p.catId = c.id"
				+ " INNER JOIN users u ON p.userId = u.id ORDER BY p.id DESC";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Product>>() {
			List<Product> list = new ArrayList<Product>();

			@Override
			public List<Product> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					list.add(new Product(rs.getInt("p.id"), rs.getString("p.name"),
							new Category(rs.getInt("c.id"), rs.getString("c.name")), rs.getString("description"),
							rs.getString("detail"), new User(rs.getInt("u.id"), rs.getString("username"),
									rs.getString("fullname"), rs.getString("avatar")),
							rs.getInt("views"), rs.getTimestamp("p.createAt")));
				}
				return list;
			}
		});
	}

	@Override
	public Product findById(int id) {
		try {
			String sql = "SELECT * FROM products p INNER JOIN categories c ON p.catId = c.id"
					+ " INNER JOIN users u ON p.userId = u.id WHERE p.id = ?";
			return jdbcTemplate.query(sql, new ResultSetExtractor<Product>() {

				@Override
				public Product extractData(ResultSet rs) throws SQLException, DataAccessException {
					if (rs.next()) {
						return new Product(rs.getInt("p.id"), rs.getString("p.name"),
								new Category(rs.getInt("c.id"), rs.getString("c.name")), rs.getString("description"),
								rs.getString("detail"), new User(rs.getInt("u.id"), rs.getString("username"),
										rs.getString("fullname"), rs.getString("avatar")),
								rs.getInt("views"), rs.getTimestamp("p.createAt"));
					}
					return null;
				}
			}, id);
		} catch (Exception e) {
			System.out.println("Product by ID " + id + ": No data");
		}
		return null;
	}

	@Override
	public List<Product> getList(int offset, int rowCount) {
		String sql = "SELECT * FROM products p INNER JOIN categories c ON p.catId = c.id"
				+ " INNER JOIN users u ON p.userId = u.id ORDER BY p.id DESC LIMIT ?,?";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Product>>() {
			List<Product> list = new ArrayList<Product>();

			@Override
			public List<Product> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					list.add(new Product(rs.getInt("p.id"), rs.getString("p.name"),
							new Category(rs.getInt("c.id"), rs.getString("c.name")), rs.getString("description"),
							rs.getString("detail"), new User(rs.getInt("u.id"), rs.getString("username"),
									rs.getString("fullname"), rs.getString("avatar")),
							rs.getInt("views"), rs.getTimestamp("p.createAt")));
				}
				return list;
			}
		}, offset, rowCount);
	}

	@Override
	public int totalRow() {
		try {
			String sql = "SELECT COUNT(*) FROM products p INNER JOIN categories c ON p.catId = c.id"
					+ " INNER JOIN users u ON p.userId = u.id";
			return jdbcTemplate.queryForObject(sql, Integer.class);
		} catch (Exception e) {
			System.out.println("Total row product: No data");
		}
		return 0;
	}

	@Override
	public int save(Product product) {
		String sql = "INSERT INTO products(name,catId,description,detail,userId) VALUES (?,?,?,?,?)";
		return jdbcTemplate.update(sql, product.getName(), product.getCat().getId(), product.getDescription(),
				product.getDetail(), product.getUser().getId());
	}

	@Override
	public int update(Product product) {
		String sql = "UPDATE products SET name = ?, catId = ?, description = ?, detail = ? WHERE id = ?";
		return jdbcTemplate.update(sql, product.getName(), product.getCat().getId(), product.getDescription(),
				product.getDetail(), product.getId());
	}

	public int updateView(int id) {
		String sql = "UPDATE products SET views = views + 1 WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public int del(int id) {
		String sql = "DELETE FROM products WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}

	// tìm kiếm, phân trang tìm kiếm (theo title)
	public List<Product> searchByName(String name, int offset, int rowCount) {
		String sql = "SELECT * FROM products p INNER JOIN categories c ON p.catId = c.id"
				+ " INNER JOIN users u ON p.userId = u.id WHERE p.name LIKE ? ORDER BY p.id DESC LIMIT ?,?";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Product>>() {
			List<Product> list = new ArrayList<Product>();

			@Override
			public List<Product> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					list.add(new Product(rs.getInt("p.id"), rs.getString("p.name"),
							new Category(rs.getInt("c.id"), rs.getString("c.name")), rs.getString("description"),
							rs.getString("detail"), new User(rs.getInt("u.id"), rs.getString("username"),
									rs.getString("fullname"), rs.getString("avatar")),
							rs.getInt("views"), rs.getTimestamp("p.createAt")));
				}
				return list;
			}
		}, "%" + name + "%", offset, rowCount);
	}

	public int totalRowByName(String name) {
		try {
			String sql = "SELECT COUNT(*) FROM products p INNER JOIN categories c ON p.catId = c.id"
					+ " INNER JOIN users u ON p.userId = u.id WHERE p.name LIKE ?";
			return jdbcTemplate.queryForObject(sql, Integer.class, "%" + name + "%");
		} catch (Exception e) {
			System.out.println("Total row product by name: No data");
		}
		return 0;
	}

	// phân trang theo danh mục
	public List<Product> getListByCat(int catId, int offset, int rowCount) {
		String sql = "SELECT * FROM products p INNER JOIN categories c ON p.catId = c.id"
				+ " INNER JOIN users u ON p.userId = u.id WHERE p.catId = ? ORDER BY p.id DESC LIMIT ?,?";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Product>>() {
			List<Product> list = new ArrayList<Product>();

			@Override
			public List<Product> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					list.add(new Product(rs.getInt("p.id"), rs.getString("p.name"),
							new Category(rs.getInt("c.id"), rs.getString("c.name")), rs.getString("description"),
							rs.getString("detail"), new User(rs.getInt("u.id"), rs.getString("username"),
									rs.getString("fullname"), rs.getString("avatar")),
							rs.getInt("views"), rs.getTimestamp("p.createAt")));
				}
				return list;
			}
		}, catId, offset, rowCount);
	}

	public int totalRowByCat(int catId) {
		try {
			String sql = "SELECT COUNT(*) FROM products p INNER JOIN categories c ON p.catId = c.id"
					+ " INNER JOIN users u ON p.userId = u.id WHERE p.catId = ?";
			return jdbcTemplate.queryForObject(sql, Integer.class, catId);
		} catch (Exception e) {
			System.out.println("Total row product by Cat ID: No data");
		}
		return 0;
	}
	
	// lấy product mới nhất
	public Product getNewProduct() {
		try {
			String sql = "SELECT * FROM products p INNER JOIN categories c ON p.catId = c.id"
					+ " INNER JOIN users u ON p.userId = u.id ORDER BY p.id DESC LIMIT 1";
			return jdbcTemplate.query(sql, new ResultSetExtractor<Product>() {

				@Override
				public Product extractData(ResultSet rs) throws SQLException, DataAccessException {
					if (rs.next()) {
						return new Product(rs.getInt("p.id"), rs.getString("p.name"),
								new Category(rs.getInt("c.id"), rs.getString("c.name")), rs.getString("description"),
								rs.getString("detail"), new User(rs.getInt("u.id"), rs.getString("username"),
										rs.getString("fullname"), rs.getString("avatar")),
								rs.getInt("views"), rs.getTimestamp("p.createAt"));
					}
					return null;
				}
			});
		} catch (Exception e) {
			System.out.println("No data");
		}
		return null;
	}

}
