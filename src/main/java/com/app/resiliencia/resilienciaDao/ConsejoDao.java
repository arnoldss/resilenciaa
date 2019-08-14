package com.app.resiliencia.resilienciaDao;

import java.util.List;

import com.app.resiliencia.model.Catalog;
import com.app.resiliencia.model.Consejo;
import com.app.resiliencia.model.GeneralData;
import com.app.resiliencia.model.Sustentabilidad;
import com.app.resiliencia.model.WhoAreWe;

public interface ConsejoDao {

	public Consejo getDataByUserId(Integer userId);
	public void update(Consejo Consejo);
	public void add(Consejo Consejo);
	public Integer getId();
	public List<Consejo> getDataByStatus(Integer userId,Integer status);

}
