package com.app.resiliencia.dto;

import java.io.InputStream;

public class DocumentLoad {

 	private String fileName;
	private String fileComments;
	private String documentName;
	
	public Integer iddocumento;
	
	
	public Integer getIddocumento() {
		return iddocumento;
	}
	public void setIddocumento(Integer iddocumento) {
		this.iddocumento = iddocumento;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileComments() {
		return fileComments;
	}
	public void setFileComments(String fileComments) {
		this.fileComments = fileComments;
	}
 
	
	
}
