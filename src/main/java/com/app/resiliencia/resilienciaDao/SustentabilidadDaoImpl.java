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
import com.app.resiliencia.model.GeneralData;
import com.app.resiliencia.model.Sustentabilidad;
import com.app.resiliencia.model.User;
@Transactional
@Repository
public class SustentabilidadDaoImpl implements SustentabilidadDao {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    JdbcTemplate jdbcTemplate;
    
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		Integer pr =  (Integer) jdbcTemplate.queryForObject("select case when max(id) > 0 then max(id)+1 else 1 end as valor from RS_SUSTENTABILIDAD ",
	                new Object[] { }, Integer.class);

	        return pr;	}
	@Override
	public List<Sustentabilidad> getDataByStatus(Integer userId,Integer status) {
		// TODO Auto-generated method stub
		List<Sustentabilidad>   pr =  jdbcTemplate.query("select * from RS_SUSTENTABILIDAD where idUser=? and status = ?  ",
	                new Object[] { userId,status }, new BeanPropertyRowMapper<Sustentabilidad>(Sustentabilidad.class));
			 
			 
	        return pr;	}

	@Override
	public void update(Sustentabilidad Sustentabilidad) {
		// TODO Auto-generated method stub
		final String sql = "UPDATE RS_SUSTENTABILIDAD SET sourceId=?, benefactor=?, porcentajeAnual=?, comentario=?, status=?  WHERE idUser = ? ";
		jdbcTemplate.update(sql,Sustentabilidad.getSourceId(),Sustentabilidad.getBenefactor(),Sustentabilidad.getPorcentajeAnual(),Sustentabilidad.getComentario(),Sustentabilidad.getStatus()
				,Sustentabilidad.getIdUser());
	}

	@Override
	public void add(Sustentabilidad p) {
		// TODO Auto-generated method stub
		final String sql = "INSERT INTO RS_SUSTENTABILIDAD (id, idUser, createdAt,sourceId, benefactor, porcentajeAnual, comentario, status,sourceName  "+")  VALUES ( ?,?, ?,?,?,?,?, ? ,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
    	jdbcTemplate.update(
    	    new PreparedStatementCreator() {
    	        public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
    	            PreparedStatement pst =
    	                con.prepareStatement(sql, new String[] {"id"});
     	            pst.setInt(1, p.getId());
     	            pst.setInt(2, p.getIdUser());
     	            pst.setTimestamp(3, new java.sql.Timestamp(new Date().getTime()));
     	            pst.setInt(4,p.getSourceId());
     	            pst.setString(5, p.getBenefactor());
     	            pst.setInt(6, p.getPorcentajeAnual());
    	            pst.setString(7, p.getComentario());
    	            pst.setInt(8, p.getStatus());
    	            pst.setString(9, p.getSourceName());

     	            return pst;
    	        }
    	    },
    	    keyHolder);
	}
	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		final String sql = "DELETE from RS_SUSTENTABILIDAD  WHERE id = ? ";
		jdbcTemplate.update(sql,id);
	}

}
