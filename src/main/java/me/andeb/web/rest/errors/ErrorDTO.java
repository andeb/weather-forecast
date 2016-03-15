package me.andeb.web.rest.errors;

import java.io.Serializable;

/**
 * Data Tranfer Object para transferir mensagens de erros.
 */
public class ErrorDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String message;
	private final String description;

	public ErrorDTO(String message) {
		this(message, null);
	}

	public ErrorDTO(String message, String description) {
		this.message = message;
		this.description = description;
	}

	public String getMessage() {
		return message;
	}

	public String getDescription() {
		return description;
	}

}
