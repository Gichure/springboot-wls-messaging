/**
 * 
 */
package com.pgichure.poc.models;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Paul
 *
 */
@Data
@AllArgsConstructor
@Builder
public class Message {
	
	private Long id;
	
	@NotNull
	private String message;

}
