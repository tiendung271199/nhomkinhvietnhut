package spring.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import spring.model.Category_new;

@Repository
public class CategoryNewDAO extends AbstractDAO<Category_new> {
	
	@Override
	public List<Category_new> getAll() {
		String sql = "SELECT * FROM categories_new ORDER BY id DESC";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category_new.class));
	}

	@Override
	public List<Category_new> getList(int offset, int rowCount) {
		String sql = "SELECT * FROM categories_new ORDER BY id DESC LIMIT ?,?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category_new.class), offset, rowCount);
	}

	@Override
	public int totalRow() {
		try {
			String sql = "SELECT COUNT(*) FROM categories_new";
			return jdbcTemplate.queryForObject(sql, Integer.class);
		} catch (Exception e) {
			System.out.println("Total row Category_New: No data");
		}
		return 0;
	}

	@Override
	public int save(Category_new Category_new) {
		String sql = "INSERT INTO categories_new(name) VALUES (?)";
		return jdbcTemplate.update(sql, Category_new.getName());
	}

	@Override
	public int update(Category_new Category_new) {
		String sql = "UPDATE categories_new SET name = ? WHERE id = ?";
		return jdbcTemplate.update(sql, Category_new.getName(), Category_new.getId());
	}

	@Override
	public int del(int id) {
		String sql = "DELETE FROM categories_new WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public Category_new findById(int id) {
		try {
			String sql = "SELECT * FROM categories_new WHERE id = ?";
			return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Category_new.class), id);
		} catch (Exception e) {
			System.out.println("Category_New by ID " + id + ": No data");
		}
		return null;
	}

	// find by name => check Category_New duplicate
	public Category_new findByName(String name) {
		try {
			String sql = "SELECT * FROM categories_new WHERE name = ?";
			return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Category_new.class), name);
		} catch (Exception e) {
			System.out.println("Category_New by name '" + name + "': No data");
		}
		return null;
	}

	// search (có phân trang)
	public List<Category_new> searchByName(String name, int offset, int rowCount) {
		String sql = "SELECT * FROM categories_new WHERE name LIKE ? ORDER BY id DESC LIMIT ?,?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category_new.class), "%" + name + "%", offset,
				rowCount);
	}

	public int totalRowByName(String name) {
		try {
			String sql = "SELECT COUNT(*) FROM categories_new WHERE name LIKE ?";
			return jdbcTemplate.queryForObject(sql, Integer.class, "%" + name + "%");
		} catch (Exception e) {
			System.out.println("Total row Category_New by name: No data");
		}
		return 0;
	}
}
