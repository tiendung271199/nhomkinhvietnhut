package spring.model;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageProduct {
	private int id;
	
	@NotBlank
	private String picture;
	
	private Product product;

	public ImageProduct(Product product) {
		super();
		this.product = product;
	}
	
}
