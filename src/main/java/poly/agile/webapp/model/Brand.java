package poly.agile.webapp.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@ToString(exclude = "products")
public class Brand implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BRAND_ID")
	private Integer id;

	@Column(name = "BRAND_NAME", unique = true)
	private String name;

	@JsonIgnore
	@OneToMany(mappedBy = "brand")
	private List<Product> products;

	public Brand(String name) {
		super();
		this.name = name;
	}

}