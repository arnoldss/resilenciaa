package com.app.resiliencia.resilienciaDao;

import java.util.List;

import com.app.resiliencia.model.Catalog;

public interface PositionDao {
	public Integer getId() ;

	public List<Catalog> getRows();
	public void removeRow(Integer id);
	public void addRow(Catalog p);
	public void update(Catalog p);

}
