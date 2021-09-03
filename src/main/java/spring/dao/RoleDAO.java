package spring.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import spring.model.Role;

@Repository
public class RoleDAO extends AbstractDAO<Role> {

	@Override
	public List<Role> getAll() {
		String sql = "SELECT * FROM roles";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Role.class));
	}
	
}
