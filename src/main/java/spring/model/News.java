package spring.model;

import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {

	private int id;

	@NotBlank
	private String title;
	
	private Category_new cat;

	@NotBlank
	private String detail;

	private User user;

	private int views;
	
	private String picture;

	private Timestamp createAt;

}
