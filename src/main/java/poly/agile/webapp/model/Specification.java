package poly.agile.webapp.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class Specification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SPECIFICATION_ID")
	private Integer id;

	@Column(name="SPECIFICATION_NAME")
	private String name;

	@JsonIgnore
	@OneToMany(mappedBy="specification")
	private List<ProductSpec> productSpecs;

}