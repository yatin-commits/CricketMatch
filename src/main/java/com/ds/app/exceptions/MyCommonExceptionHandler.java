package com.ds.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyCommonExceptionHandler {

	@ExceptionHandler(StadiumFullException.class)
	public ResponseEntity<ExceptionResponse> handleStadiumFullException(StadiumFullException ex) {
		ExceptionResponse response = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BookingIdNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleBookingException(BookingIdNotFoundException ex) {
		ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoMatchOnThatDate.class)
	public ResponseEntity<ExceptionResponse> handleNoMatchOnThatDate(NoMatchOnThatDate ex) {
		ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NotEnoughSpaceException.class)
	public ResponseEntity<ExceptionResponse> handleNotEnoughSpace(NotEnoughSpaceException ex) {
		ExceptionResponse response = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(MatchAlreadyExistException.class)
	public ResponseEntity<ExceptionResponse> handleNotEnoughSpace(MatchAlreadyExistException ex) {
		ExceptionResponse response = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
	    String message = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
	    ExceptionResponse response =
	            new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), "Required Field Error: " + message);
	    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}