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
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ORDER_ID")
	private Integer id;

	private String address;

	private Float amount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_TIME")
	private Date createdTime;

	@Column(name="PHONE_NUMBER")
	private String phoneNumber;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATED_TIME")
	private Date updatedTime;

	@ManyToOne
	@JoinColumn(name="ORDER_STATUS_ID")
	private OrderStatus orderStatus;

	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;

	@OneToMany(mappedBy="order")
	private List<OrderLine> orderLines;

}