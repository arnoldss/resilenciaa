package com.app.resiliencia.model;

import java.util.List;

public class Pregunta {
	List<RespuestaEncuesta> respuesta;
	private Integer id;
	private String name;
	public List<RespuestaEncuesta> getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(List<RespuestaEncuesta> respuesta) {
		this.respuesta = respuesta;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
