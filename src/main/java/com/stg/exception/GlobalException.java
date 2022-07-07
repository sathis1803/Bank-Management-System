package com.stg.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

import com.stg.entity.ResponseError;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(value = CustomException.class)
	public ResponseEntity<ResponseError> myFunction(CustomException customException, HttpServletRequest httpServletRequest){
		ResponseError responseError = new ResponseError(customException.getMessage(),httpServletRequest.getRequestURI());
		
		return new ResponseEntity<>(responseError,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<ResponseError> myFunction1(ConstraintViolationException customException, HttpServletRequest httpServletRequest){
		ResponseError responseError = new ResponseError(customException.getMessage(),httpServletRequest.getRequestURI());
		
		return new ResponseEntity<>(responseError,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = NumberFormatException.class)
	public ResponseEntity<ResponseError> myFunction2(NumberFormatException numberFormatException, HttpServletRequest httpServletRequest){
		ResponseError responseError = new ResponseError(numberFormatException.getMessage(),httpServletRequest.getRequestURI());
		
		return new ResponseEntity<>(responseError,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ResponseError> myFunction3(SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException, HttpServletRequest httpServletRequest){
		ResponseError responseError = new ResponseError("Already Gmail Registered!",httpServletRequest.getRequestURI());
		
		return new ResponseEntity<>(responseError,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value = NullPointerException.class)
	public ResponseEntity<ResponseError> myFunction4(NullPointerException nullPointerException, HttpServletRequest httpServletRequest){
		ResponseError responseError = new ResponseError(nullPointerException.getMessage(),httpServletRequest.getRequestURI());
		
		return new ResponseEntity<>(responseError,HttpStatus.BAD_REQUEST);
	}	
	//HttpServerErrorException
	@ExceptionHandler(value = HttpServerErrorException.class)
	public ResponseEntity<ResponseError> myFunction5(HttpServerErrorException httpServerErrorException, HttpServletRequest httpServletRequest){
		ResponseError responseError = new ResponseError(httpServerErrorException.getMessage(),httpServletRequest.getRequestURI());
		
		return new ResponseEntity<>(responseError,HttpStatus.BAD_REQUEST);
	}
	
	@Bean
	public MessageSource messageSource() {
	    ReloadableResourceBundleMessageSource messageSource
	      = new ReloadableResourceBundleMessageSource();
	    
	    messageSource.setBasename("classpath:messages");
	    messageSource.setDefaultEncoding("UTF-8");
	    return messageSource;
	}
	@Bean
	public LocalValidatorFactoryBean getValidator() {
	    LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
	    bean.setValidationMessageSource(messageSource());
	    return bean;
	}
}
