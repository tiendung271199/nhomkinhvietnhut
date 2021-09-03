package spring.model;

import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	private int id;

	@NotBlank
	private String name;

	private Category cat;

	@NotBlank
	private String description;

	@NotBlank
	private String detail;

	private User user;

	private int views;

	private Timestamp createAt;

	// join table
	public Product(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

}
