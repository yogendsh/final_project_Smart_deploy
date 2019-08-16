package com.cg.smartdeploy.exception;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/*
 * This exception class will acts as a custom exception class
 * which prints the appropriate messages and Status
 */
@RestControllerAdvice
public class CustomExceptionController {

	   	
	
		@ExceptionHandler(value = { ProgramException.class })
	    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
		/**
	   	 * This method is used to handle all the program Exception using customize Exception
	   	 * 
	   	 */
	    public ErrorMessageStructure KnownException(String Message)
	    {
	        return new ErrorMessageStructure(500,Message);
	    }
	

}
