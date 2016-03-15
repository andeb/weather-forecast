package me.andeb.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Cidade.
 * 
 * @author Anderson de Borba
 */
@SuppressWarnings("serial")
@Document(collection = "cities")
public class City implements Serializable {

	@Id
	private String id;

	@NotNull
	@Size(min = 1, max = 50)
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
