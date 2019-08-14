package com.app.resiliencia.model;

import java.util.Date;

public class Sustentabilidad {
	private Integer id;
	private Integer idUser;
	private Date createdAt;
	private Integer sourceId;
	private String benefactor;
	private Integer porcentajeAnual;
	private String comentario;
	private Integer status;
	private String sourceName;
	
	
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Integer getSourceId() {
		return sourceId;
	}
	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}
	public String getBenefactor() {
		return benefactor;
	}
	public void setBenefactor(String benefactor) {
		this.benefactor = benefactor;
	}
	public Integer getPorcentajeAnual() {
		return porcentajeAnual;
	}
	public void setPorcentajeAnual(Integer porcentajeAnual) {
		this.porcentajeAnual = porcentajeAnual;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

}
