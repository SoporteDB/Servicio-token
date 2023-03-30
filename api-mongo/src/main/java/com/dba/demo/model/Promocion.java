package com.dba.demo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(value ="Promocion")
@Data
public class Promocion implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	 
	@Id
	private String id;
	
	private String name_Camp;
	
	private String name_Customer;
	
	private Map<String, Object> datos;
	
	public Promocion() {}
	
	public Promocion(String name_Camp, String name_Customer,Map<String, Object> datos) {
		this.name_Camp=name_Camp;
		this.name_Customer=name_Customer;
        this.datos = datos;
    }
	
	
	//private String celularAsesor;
	//private String nombreAsesor;
	//private String nombreCliente;
	//private String numeroTelefono;
	//private String correo;
	//private String fecha;
	//private String hora;
	//private String calificacion;
	//private String estadoCliente;
	
	
}
