package poly.agile.webapp.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_spec_detail")
@Data
@NoArgsConstructor
public class ProductSpecDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PROD_SPEC_DETAIL_ID")
	private Integer id;

	@Column(name = "PROD_SPEC_NAME")
	private String name;

	@Column(name = "PROD_SPEC_VALUE")
	private String value;

	@JsonIgnore
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name = "PROD_SPEC_ID")
	private ProductSpec productSpec;

}