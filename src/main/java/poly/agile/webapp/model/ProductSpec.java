package poly.agile.webapp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name="product_spec")
@Data
@NoArgsConstructor
@ToString(exclude= {"productSpecDetails"})
public class ProductSpec implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PROD_SPEC_ID")
	private Integer id;

	@JsonIgnore
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="PRODUCT_ID")
	private Product product;

	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="SPECIFICATION_ID")
	private Specification specification;

	@OneToMany(mappedBy="productSpec", cascade=CascadeType.ALL)
	private List<ProductSpecDetail> productSpecDetails;

}