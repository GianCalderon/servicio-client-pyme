package com.springboot.pyme.document;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Document(collection = "client-Pyme")
public class Pyme {

	@Id
	private String id;

	@NotNull(message = "User's tipoDoc must not be null")
	@NotEmpty(message = "tipoDoc may not be empty")
	private String tipoDoc;

	@NotNull(message = "User's numDoc must not be null")
	@NotEmpty(message = "numDoc may not be empty")
	private String numDoc;

	@NotNull(message = "User's name must not be null")
	@NotEmpty(message = "name may not be empty")
	private String name;

	@NotNull(message = "User's address must not be null")
	@NotEmpty(message = "address may not be empty")
	private String address;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updateDate;
	
	private List<Map<String, String>> idCuentas;

	public Pyme() {

	}

}
