package poly.agile.webapp.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="order_line")
@Data
@NoArgsConstructor
public class OrderLine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ODER_LINE_ID")
	private Integer id;

	private Float amount;

	private Integer quantity;

	@ManyToOne
	@JoinColumn(name="ORDER_ID")
	private Order order;

	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	private Product product;
	
}