package com.app.resiliencia.resilienciaDao;

import java.util.List;

import com.app.resiliencia.model.Catalog;
import com.app.resiliencia.model.GeneralData;
import com.app.resiliencia.model.Sustentabilidad;
import com.app.resiliencia.model.WhoAreWe;

public interface WhoAreWeDao {

	public WhoAreWe getDataByUserId(Integer userId);
	public void update(WhoAreWe WhoAreWe);
	public void add(WhoAreWe WhoAreWe);
	public Integer getId() ;

}
