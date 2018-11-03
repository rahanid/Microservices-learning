package com.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result<T> {
	
	private T data;
	
	private Throwable exception;
	
	

}
