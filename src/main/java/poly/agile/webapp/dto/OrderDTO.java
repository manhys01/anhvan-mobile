package poly.agile.webapp.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
	
	private Integer id;
	private String customerName;
	private String phoneNumber;
	private String address;
	private Date createdTime;
	private String status;
	
}
