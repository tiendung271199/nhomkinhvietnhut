package spring.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reviews {

	private int id;

	private Product product;

	private int rating;

	private String name;

	private String email;

	private String content;

	private int status;

	private Timestamp createAt;

}
