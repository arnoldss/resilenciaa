package com.app.resiliencia.resilienciaDao;

import java.util.ArrayList;
import java.util.List;

import com.app.resiliencia.model.Catalog;
import com.app.resiliencia.model.Encuesta;
import com.app.resiliencia.model.Promedios;
import com.app.resiliencia.model.RespuestaEncuesta;

public interface EncuestaDao {
	public Catalog getDataById(Integer id);

	public Encuesta getRows();
	public void removeRow(Integer id);
	public void addRow(Catalog p);
	public Integer getId() ;
	public boolean saveEncuesta(ArrayList <RespuestaEncuesta>  encuesta,int idusuario);	
	public Integer getIdResponseEncuesta() ;
	//public ArrayList <Pregunta> getPreguntas();	

	List<Catalog> getRespeuestasUsuario(int iduser);

	public List<Promedios> getPromedioSubcategoriaUsuario(int iduser);
	public Integer getCountPreguntas() ;
	public Integer getCountEncuestaUser(int iduser) ;
	public Integer deleteEncuestaUser(int iduser) ;
	
}



