package poly.agile.webapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
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
	private String shortDescription;
	
	@Column(name="CREATED_TIME", insertable=false, updatable=false)
	private Date createdTime;

	@ManyToOne
	@JoinColumn(name = "BRAND_ID")
	private Brand brand;

	@OneToMany(mappedBy = "product", cascade=CascadeType.ALL)
	private List<ProductSpec> productSpecs;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product", cascade=CascadeType.ALL)
	private List<OrderLine> orderLines;
	

}