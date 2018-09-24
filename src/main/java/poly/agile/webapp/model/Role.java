package poly.agile.webapp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
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