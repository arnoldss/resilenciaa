package com.app.resiliencia.resilienciaDao;

import java.util.List;

import com.app.resiliencia.model.Catalog;
import com.app.resiliencia.model.GeneralData;

public interface GeneralDataDao {

	public GeneralData getDataByUserId(Integer userId);
	public void update(GeneralData generalData);
	public  void add(GeneralData generalData);
	public Integer getId();
}
