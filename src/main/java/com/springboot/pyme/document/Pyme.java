package com.springboot.pyme.document;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Document(collection = "Cliente-Pyme")
public class Pyme {

	@Id
	private String id;

	@NotNull(message = "User's tipoDoc must not be null")
	private String tipoDoc;

	@NotNull(message = "User's numDoc must not be null")
	@Indexed(unique = true)
	private String numDoc;

	@NotNull(message = "User's name must not be null")
	private String name;

	@NotNull(message = "User's address must not be null")
	private String address;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updateDate;

	private List<Account> listAccount;

	public Pyme() {

	}

}
