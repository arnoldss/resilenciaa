package com.app.resiliencia.resilienciaDao;

import java.util.List;

import com.app.resiliencia.model.Catalog;

public interface PropertyDao {
	public Catalog getDataById(Integer id);
	public Integer getId() ;

	public List<Catalog> getProperties();
	public void removeProperty(Integer id);
	public void addProperty(Catalog p);
	public void update(Catalog p);

}
