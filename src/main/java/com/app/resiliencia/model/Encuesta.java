package com.app.resiliencia.model;

import java.util.List;

public class Encuesta {
	
	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
	}

	public int rol;
	
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

	public double setPromedio(double promedio) {
		return this.promedio = promedio;
	}

	public double promedio;
	List<Area> area;

	public List<Area> getArea() {
		return area;
	}

	public void setArea(List<Area> area) {
		this.area = area;
	}


}
