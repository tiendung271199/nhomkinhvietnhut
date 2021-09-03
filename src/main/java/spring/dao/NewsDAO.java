package spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import spring.model.Category_new;
import spring.model.News;
import spring.model.User;

@Repository
public class NewsDAO extends AbstractDAO<News> {

	@Override
	public List<News> getAll() {
		String sql = "SELECT * FROM news n INNER JOIN users u ON n.userId = u.id "
				+ "INNER JOIN categories_new c ON n.catId = c.id ORDER BY n.id DESC";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<News>>() {
			List<News> list = new ArrayList<News>();

			@Override
			public List<News> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					list.add(new News(rs.getInt("n.id"), rs.getString("title"),
							new Category_new(rs.getInt("c.id"), rs.getString("c.name")), rs.getString("detail"),
							new User(rs.getInt("u.id"), rs.getString("username"), rs.getString("fullname"),
									rs.getString("avatar")),
							rs.getInt("views"), rs.getString("picture"), rs.getTimestamp("n.createAt")));
				}
				return list;
			}
		});
	}

	@Override
	public News findById(int id) {
		try {
			String sql = "SELECT * FROM news n INNER JOIN users u ON n.userId = u.id "
					+ "INNER JOIN categories_new c ON n.catId = c.id WHERE n.id = ?";
			return jdbcTemplate.query(sql, new ResultSetExtractor<News>() {

				@Override
				public News extractData(ResultSet rs) throws SQLException, DataAccessException {
					if (rs.next()) {
						return new News(rs.getInt("n.id"), rs.getString("title"),
								new Category_new(rs.getInt("c.id"), rs.getString("c.name")), rs.getString("detail"),
								new User(rs.getInt("u.id"), rs.getString("username"), rs.getString("fullname"),
										rs.getString("avatar")),
								rs.getInt("views"), rs.getString("picture"), rs.getTimestamp("n.createAt"));
					}
					return null;
				}
			}, id);
		} catch (Exception e) {
			System.out.println("News by ID " + id + ": No data");
		}
		return null;
	}

	@Override
	public List<News> getList(int offset, int rowCount) {
		String sql = "SELECT * FROM news n INNER JOIN users u ON n.userId = u.id"
				+ " INNER JOIN categories_new c ON n.catId = c.id ORDER BY n.id DESC LIMIT ?,?";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<News>>() {
			List<News> list = new ArrayList<News>();

			@Override
			public List<News> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					list.add(new News(rs.getInt("n.id"), rs.getString("title"),
							new Category_new(rs.getInt("c.id"), rs.getString("c.name")), rs.getString("detail"),
							new User(rs.getInt("u.id"), rs.getString("username"), rs.getString("fullname"),
									rs.getString("avatar")),
							rs.getInt("views"), rs.getString("picture"), rs.getTimestamp("n.createAt")));
				}
				return list;
			}
		}, offset, rowCount);
	}

	public List<News> getListNew() {
		String sql = "SELECT * FROM news n INNER JOIN users u ON n.userId = u.id "
				+ "INNER JOIN categories_new c ON n.catId = c.id ORDER BY n.id DESC LIMIT 6";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<News>>() {
			List<News> list = new ArrayList<News>();

			@Override
			public List<News> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					list.add(new News(rs.getInt("n.id"), rs.getString("title"),
							new Category_new(rs.getInt("c.id"), rs.getString("c.name")), rs.getString("detail"),
							new User(rs.getInt("u.id"), rs.getString("username"), rs.getString("fullname"),
									rs.getString("avatar")),
							rs.getInt("views"), rs.getString("picture"), rs.getTimestamp("n.createAt")));
				}
				return list;
			}
		});
	}

	@Override
	public int totalRow() {
		try {
			String sql = "SELECT COUNT(*) FROM news n INNER JOIN users u ON n.userId = u.id "
					+ "INNER JOIN categories_new c ON n.catId = c.id";
			return jdbcTemplate.queryForObject(sql, Integer.class);
		} catch (Exception e) {
			System.out.println("Total row news: No data");
		}
		return 0;
	}

	@Override
	public int save(News news) {
		String sql = "INSERT INTO news(title,catId,detail,userId,picture) VALUES (?,?,?,?,?)";
		return jdbcTemplate.update(sql, news.getTitle(), news.getCat().getId(), news.getDetail(),
				news.getUser().getId(), news.getPicture());
	}

	@Override
	public int update(News news) {
		String sql = "UPDATE news SET catId = ?, title = ?, detail = ?, picture = ? WHERE id = ?";
		return jdbcTemplate.update(sql, news.getCat().getId(), news.getTitle(), news.getDetail(), news.getPicture(),
				news.getId());
	}

	public int updateView(int id) {
		String sql = "UPDATE news SET views = views + 1 WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public int del(int id) {
		String sql = "DELETE FROM news WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}

	// tìm kiếm, phân trang tìm kiếm (theo title)
	public List<News> searchByTitle(String title, int offset, int rowCount) {
		String sql = "SELECT * FROM news n INNER JOIN users u ON n.userId = u.id"
				+ " INNER JOIN categories_new c ON n.catId = c.id WHERE title LIKE ? ORDER BY n.id DESC LIMIT ?,?";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<News>>() {
			List<News> list = new ArrayList<News>();

			@Override
			public List<News> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					list.add(new News(rs.getInt("n.id"), rs.getString("title"),
							new Category_new(rs.getInt("c.id"), rs.getString("c.name")), rs.getString("detail"),
							new User(rs.getInt("u.id"), rs.getString("username"), rs.getString("fullname"),
									rs.getString("avatar")),
							rs.getInt("views"), rs.getString("picture"), rs.getTimestamp("n.createAt")));
				}
				return list;
			}
		}, "%" + title + "%", offset, rowCount);
	}

	public int totalRowByTitle(String title) {
		try {
			String sql = "SELECT COUNT(*) FROM news n INNER JOIN users u ON n.userId = u.id "
					+ "INNER JOIN categories_new c ON n.catId = c.id WHERE title LIKE ?";
			return jdbcTemplate.queryForObject(sql, Integer.class, "%" + title + "%");
		} catch (Exception e) {
			System.out.println("Total row news by title: No data");
		}
		return 0;
	}

}
