package com.app.resiliencia.resilienciaDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.resiliencia.model.Catalog;
import com.app.resiliencia.model.Consejo;
import com.app.resiliencia.model.GeneralData;
import com.app.resiliencia.model.Sustentabilidad;
import com.app.resiliencia.model.User;
import com.app.resiliencia.model.WhoAreWe;
@Transactional
@Repository
public class ConsejoDaoImpl implements ConsejoDao {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    JdbcTemplate jdbcTemplate;
	@Override
	public List<Consejo> getDataByStatus(Integer userId,Integer status) {
		// TODO Auto-generated method stub
		List<Consejo>   pr =  jdbcTemplate.query("select * from RS_CONSEJO where idUser=? and activo = ?  ",
	                new Object[] { userId,status }, new BeanPropertyRowMapper<Consejo>(Consejo.class));
			 
			 
	        return pr;	}
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		Integer pr =  (Integer) jdbcTemplate.queryForObject("select case when max(id) > 0 then max(id)+1 else 1 end as valor from RS_CONSEJO ",
	                new Object[] { }, Integer.class);

	        return pr;	}
	@Override
	public Consejo getDataByUserId(Integer id) {
		// TODO Auto-generated method stub
		Consejo pr =  (Consejo) jdbcTemplate.query("select * from RS_CONSEJO where idUser= ?  ",
	                new Object[] { id }, new BeanPropertyRowMapper<Consejo>(Consejo.class));

	        return pr;	}

	@Override
	public void update(Consejo Consejo) {
		// TODO Auto-generated method stub
		final String sql = "UPDATE RS_CONSEJO SET activo=?, positionId=?, nombre=?, remove=?, fechaInicio=?,fechaFinal =?, apellido=?,fechaDeNacimiento=?,rfc=?  WHERE idUser = ? ";
		jdbcTemplate.update(sql,Consejo.getActivo(),Consejo.getPositionId(),Consejo.getNombre(),Consejo.getRemove(),Consejo.getFechaInicio(),Consejo.getFechaFinal(),
				Consejo.getApellido(),Consejo.getFechaDeNacimiento(),Consejo.getRfc(),Consejo.getIdUser());
	}

	@Override
	public void add(Consejo p) {
		// TODO Auto-generated method stub
		final String sql = "INSERT INTO RS_CONSEJO (id, idUser, createdAt, activo, positionId, nombre, fechaInicio, fechaFinal, apellido, positionName  "+")  VALUES ( ?,?,?,?,?, ?,?,?,?,? )";
		KeyHolder keyHolder = new GeneratedKeyHolder();
    	jdbcTemplate.update(
    	    new PreparedStatementCreator() {
    	        public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
    	            PreparedStatement pst =
    	                con.prepareStatement(sql, new String[] {"id"});
     	            pst.setInt(1, p.getId());
     	            pst.setInt(2, p.getIdUser());
     	            pst.setTimestamp(3, new java.sql.Timestamp(new Date().getTime()));
     	            pst.setInt(4,1);
     	            pst.setInt(5, 0);
     	            pst.setString(6, p.getNombre());
    	           // pst.setInt(7, p.getRemove());
     	            pst.setTimestamp(7, new java.sql.Timestamp(p.getFechaInicio().getTime()));
     	            pst.setTimestamp(8, new java.sql.Timestamp(p.getFechaFinal().getTime()));
    	            pst.setString(9, p.getApellido());
     	            //pst.setTimestamp(11, new java.sql.Timestamp(p.getFechaDeNacimiento().getTime()));
    	            //pst.setString(12, p.getRfc());
    	            pst.setString(10, p.getPositionName());


     	            return pst;
    	        }
    	    },
    	    keyHolder);
	}

}
