package poly.agile.webapp.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@Data
@NoArgsConstructor
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ROLE_ID")
	private Integer id;

	@Column(name = "ROLE_NAME")
	private String name;

	@OneToMany(mappedBy = "role")
	private List<User> users;

}