package com.app.resiliencia.dto;

import java.util.ArrayList;

public class Pregunta {
  public int categoria;
  public int subcategoria;
  public int getCategoria() {
	return categoria;
}
public void setCategoria(int categoria) {
	this.categoria = categoria;
}
public int getSubcategoria() {
	return subcategoria;
}
public void setSubcategoria(int subcategoria) {
	this.subcategoria = subcategoria;
}
public ArrayList<RespuestaPregunta> getRespuestas() {
	return respuestas;
}
public void setRespuestas(ArrayList<RespuestaPregunta> respuestas) {
	this.respuestas = respuestas;
}
public int getPonderacion() {
	return ponderacion;
}
public void setPonderacion(int ponderacion) {
	this.ponderacion = ponderacion;
}
public ArrayList<RespuestaPregunta> respuestas;  
  public int ponderacion;
}
/*hacer un select de rs_question para sacar  la categoria y 
 * subcategoria despues iterar y  sacar   las posbles respuestas
 */
