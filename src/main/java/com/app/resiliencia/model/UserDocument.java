package com.app.resiliencia.model;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class UserDocument {

	private Integer id;
	private Integer userId;
	private String fileName;
	private String docName;
	private String comments;
	private Date createdAt;
	private Integer status;
	private String docType;
	public Integer iddocumento;
	
	public byte[] getBytedoc() {
		return bytedoc;
	}
	public void setBytedoc(byte[] bytedoc) {
		this.bytedoc = bytedoc;
	}
	public byte[] bytedoc;
	
	 
	public Integer getIddocumento() {
		return iddocumento;
	}
	public void setIddocumento(Integer iddocumento) {
		this.iddocumento = iddocumento;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
}
