package com.app.resiliencia.resilienciaDao;

import java.util.List;

import com.app.resiliencia.model.Catalog;

public interface ResponseDao {
	public Catalog getDataById(Integer id);

	public List<Catalog> getRows(Integer questionId);
	public void removeRow(Integer id);
	public void addRow(Catalog p);
	public Integer getId() ;
	public void update(Catalog p);

}
