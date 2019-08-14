package com.app.resiliencia.resilienciaDao;

import java.util.List;

import com.app.resiliencia.model.Catalog;

public interface SubAreaDao {
	public Catalog getDataById(Integer id);

	public List<Catalog> getRows(Integer areaId);
	public void removeRow(Integer id);
	public void addRow(Catalog p);
	public Integer getId() ;
	public void update(Catalog p);

}
