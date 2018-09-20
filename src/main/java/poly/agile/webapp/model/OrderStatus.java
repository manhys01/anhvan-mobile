package poly.agile.webapp.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name="order_status")
@Data
@NoArgsConstructor
public class OrderStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ORDER_STATUS_ID")
	private byte orderStatusId;

	@Column(name="ORDER_STATUS_NAME")
	private String orderStatusName;

	@OneToMany(mappedBy="orderStatus")
	private List<Order> orders;


}