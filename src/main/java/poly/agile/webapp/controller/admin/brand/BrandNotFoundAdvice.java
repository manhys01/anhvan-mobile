package poly.agile.webapp.controller.admin.brand;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BrandNotFoundAdvice {
	
	@ResponseBody
	@ExceptionHandler(BrandNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String brandNotFoundHandler(BrandNotFoundException ex) {
		return ex.getMessage();
	}
	
}
