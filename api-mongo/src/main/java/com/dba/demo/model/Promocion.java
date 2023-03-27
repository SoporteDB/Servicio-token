package com.dba.demo.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(value ="Promocion")
@Data
public class Promocion implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	 
	@Id
	private String id;
	private String celularAsesor;
	private String nombreAsesor;
	private String nombreCliente;
	private String numeroTelefono;
	private String correo;
	private String fecha;
	private String hora;
	private String calificacion;
	private String estadoCliente;
	
	
}
