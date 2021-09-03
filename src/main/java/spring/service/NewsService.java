package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.dao.NewsDAO;
import spring.model.News;

@Service
public class NewsService implements ICRUDService<News> {

	@Autowired
	private NewsDAO newsDAO;

	@Override
	public List<News> getAll() {
		return newsDAO.getAll();
	}

	@Override
	public int save(News news) {
		return newsDAO.save(news);
	}

	@Override
	public int update(News news) {
		return newsDAO.update(news);
	}

	public int updateView(int id) {
		return newsDAO.updateView(id);
	}

	@Override
	public int del(int id) {
		return newsDAO.del(id);
	}

	@Override
	public News findById(int id) {
		return newsDAO.findById(id);
	}

	@Override
	public List<News> getList(int offset, int rowCount) {
		return newsDAO.getList(offset, rowCount);
	}

	@Override
	public int totalRow() {
		return newsDAO.totalRow();
	}

	public List<News> searchByTitle(String title, int offset, int rowCount) {
		return newsDAO.searchByTitle(title, offset, rowCount);
	}

	public int totalRowByTitle(String title) {
		return newsDAO.totalRowByTitle(title);
	}

	public List<News> getListNew() {
		return newsDAO.getListNew();
	}
}
