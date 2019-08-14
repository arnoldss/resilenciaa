package com.app.resiliencia.model;

import java.util.Date;

public class WhoAreWe {

	private Integer id;
	private Integer idUser;
	private String mision;
	private String vision;
	private String valores;
	private String breveHistoria;
	private String premiosOrganizacion;
	private Date cretedAt;
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
	public String getMision() {
		return mision;
	}
	public void setMision(String mision) {
		this.mision = mision;
	}
	public String getVision() {
		return vision;
	}
	public void setVision(String vision) {
		this.vision = vision;
	}
	public String getValores() {
		return valores;
	}
	public void setValores(String valores) {
		this.valores = valores;
	}
	public String getBreveHistoria() {
		return breveHistoria;
	}
	public void setBreveHistoria(String breveHistoria) {
		this.breveHistoria = breveHistoria;
	}
	public String getPremiosOrganizacion() {
		return premiosOrganizacion;
	}
	public void setPremiosOrganizacion(String premiosOrganizacion) {
		this.premiosOrganizacion = premiosOrganizacion;
	}
	public Date getCretedAt() {
		return cretedAt;
	}
	public void setCretedAt(Date cretedAt) {
		this.cretedAt = cretedAt;
	}
	
	
}
