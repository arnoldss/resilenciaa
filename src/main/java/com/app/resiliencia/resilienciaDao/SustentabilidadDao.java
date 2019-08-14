package com.app.resiliencia.resilienciaDao;

import java.util.List;

import com.app.resiliencia.model.Catalog;
import com.app.resiliencia.model.GeneralData;
import com.app.resiliencia.model.Sustentabilidad;

public interface SustentabilidadDao {

	public List<Sustentabilidad> getDataByStatus(Integer userId, Integer status);
	public void update(Sustentabilidad Sustentabilidad);
	public void add(Sustentabilidad Sustentabilidad);
	public Integer getId();
	public void remove(Integer id);

}
