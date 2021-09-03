package spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	private int id;
	
	private String username;
	
	private String fullname;
	
	private String password;
	
	private String email;
	
	private String phone;
	
	private String avatar;
	
	private Role role;
	
	private int enabled;

	// join table
	public User(int id, String username, String fullname, String avatar) {
		super();
		this.id = id;
		this.username = username;
		this.fullname = fullname;
		this.avatar = avatar;
	}

	// demo add product
	public User(int id) {
		super();
		this.id = id;
	}
	
}
