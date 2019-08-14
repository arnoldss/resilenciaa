package com.app.resiliencia.resilienciaDao;

import java.util.List;

import com.app.resiliencia.model.Catalog;

public interface QuestionDao {
	public Catalog getDataById(Integer id);

	public List<Catalog> getRows(Integer areaId, Integer subareaId);
	public void removeRow(Integer id);
	public void addRow(Catalog p);
	public Integer getId() ;
	public void update(Catalog p);

}
