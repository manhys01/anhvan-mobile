package poly.agile.webapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_ID")
	private Integer id;

	@NotBlank
	@Column(name = "PRODUCT_NAME", unique = true, length = 255)
	private String name;

	@Column(name = "PRODUCT_UNIT", length = 45)
	private String unit;

	@Min(value = 0)
	@Max(value = Long.MAX_VALUE)
	@Column(name = "QUANTITY_IN_STOCK")
	private Integer qtyInStock;

	@Column(name = "THUMBNAIL", length = 255)
	private String thumbnail;

	@Column(name = "ENABLED")
	private Boolean enabled;

	@Min(value = 0)
	@Max(value = Integer.MAX_VALUE)
	@Column(name = "PRICE")
	private Integer price;

	@Column(name = "SHORT_DESCRIPTION", length=255)
	private String shortDescription;

	@Column(name = "CREATED_TIME", insertable = false, updatable = false)
	private Date createdTime;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "BRAND_ID")
	private Brand brand;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ProductSpec> productSpecs;

	@JsonIgnore
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OrderLine> orderLines;

}