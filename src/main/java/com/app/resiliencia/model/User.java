package com.app.resiliencia.model;

import java.io.Serializable;
import java.util.Date;

public class User  implements Serializable {


 
    private Integer id;
 
    private Date createdAt;
     private String name;
     private String email;
	 private String password;
	 private Integer role;
	 private String since;
	 private Integer status;
	 private String fileName;
	 private String documentName;
	 private String comments;
	 private String telefono;
	 private Boolean choosed = false;
	 public boolean encuesta=true;
	 
	 
     public boolean getEncuesta() {
		return encuesta;
	}
	public void setEncuesta(boolean encuesta) {
		this.encuesta = encuesta;
	}
	public Boolean getChoosed() {
		return choosed;
	}
	public void setChoosed(Boolean choosed) {
		this.choosed = choosed;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getSince() {
		return since;
	}
	public void setSince(String since) {
		this.since = since;
	}
 
  
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
    
    
    
}
