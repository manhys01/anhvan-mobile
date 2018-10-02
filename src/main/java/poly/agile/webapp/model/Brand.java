package poly.agile.webapp.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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

	@NotBlank(message = "Tên thương hiệu không được để trống!")
	@Column(name = "BRAND_NAME", length = 45, unique = true, nullable = false)
	private String name;

	@Column(name = "LOGO", length = 255)
	private String logo;

	@JsonIgnore
	@OneToMany(mappedBy = "brand", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Product> products;

	public Brand(String name) {
		super();
		this.name = name;
	}

}