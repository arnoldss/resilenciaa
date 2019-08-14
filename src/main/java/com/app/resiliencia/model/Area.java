package com.app.resiliencia.model;

import java.util.List;

public class Area {

	List <SubArea> subarea;
	private Integer id;
	private String name;
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String img;
	public double getPromedio() {
		return promedio;
	}
	public void setPromedio(double promedio) {
		this.promedio = promedio;
	}
	public double promedio;
	public List<SubArea> getSubarea() {
		return subarea;
	}
	public void setSubarea(List<SubArea> subarea) {
		this.subarea = subarea;
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
