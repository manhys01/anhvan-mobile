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
@ToString(exclude= {"orderLines"})
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_ID")
	private Integer id;

	@Column(name = "PRODUCT_NAME")
	private String name;

	@Column(name = "PRODUCT_UNIT")
	private String unit;

	@Column(name = "QUANTITY_IN_STOCK")
	private Integer qtyInStock;

	private String thumbnail;
	
	private Boolean enabled;

	private Integer price;
	
	@Column(name = "SHORT_DESCRIPTION")
	private String shortDesc;

	@ManyToOne
	@JoinColumn(name = "BRAND_ID")
	private Brand brand;

	@OneToMany(mappedBy = "product")
	private List<ProductSpec> productSpecs;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<OrderLine> orderLines;

}