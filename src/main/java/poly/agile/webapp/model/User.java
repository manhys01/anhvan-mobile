package poly.agile.webapp.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "USER_ID")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ROLE_ID")
	private Role role;

	private String username;

	private String password;

	private String email;

	private String address;

	private Date birthdate;

	private Boolean enabled;

	private Boolean gender;

	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_TIME")
	private Date createdTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_TIME")
	private Date updatedTime;

	@OneToMany(mappedBy = "user")
	private List<Order> orders;

}