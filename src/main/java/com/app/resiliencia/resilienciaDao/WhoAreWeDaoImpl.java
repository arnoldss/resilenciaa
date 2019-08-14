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
import com.app.resiliencia.model.WhoAreWe;
@Transactional
@Repository
public class WhoAreWeDaoImpl implements WhoAreWeDao {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    JdbcTemplate jdbcTemplate;
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		Integer pr =  (Integer) jdbcTemplate.queryForObject("select case when max(id) > 0 then max(id)+1 else 1 end as valor from RS_WHO_ARE_WE ",
	                new Object[] { }, Integer.class);

	        return pr;	}
	@Override
	public WhoAreWe getDataByUserId(Integer id) {
		// TODO Auto-generated method stub
		WhoAreWe pr =  null;
		try {		
				pr = (WhoAreWe) jdbcTemplate.queryForObject("select * from RS_WHO_ARE_WE where idUser= ?  ",
	                new Object[] { id }, new BeanPropertyRowMapper<WhoAreWe>(WhoAreWe.class));
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
	        return pr;	}

	@Override
	public void update(WhoAreWe WhoAreWe) {
		// TODO Auto-generated method stub
		final String sql = "UPDATE RS_WHO_ARE_WE SET mision=?, vision=?, valores=?, breveHistoria=?, premiosOrganizacion=?  WHERE idUser = ? ";
		jdbcTemplate.update(sql,WhoAreWe.getMision(),WhoAreWe.getVision(),WhoAreWe.getValores(),WhoAreWe.getBreveHistoria(),WhoAreWe.getPremiosOrganizacion(),WhoAreWe.getIdUser());
	}

	@Override
	public void add(WhoAreWe p) {
		// TODO Auto-generated method stub
		final String sql = "INSERT INTO RS_WHO_ARE_WE (id, idUser, mision, vision, valores, breveHistoria, premiosOrganizacion, createdAt  "+")  VALUES ( ?,?, ?,?,?,?,?, ? )";
		KeyHolder keyHolder = new GeneratedKeyHolder();
    	jdbcTemplate.update(
    	    new PreparedStatementCreator() {
    	        public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
    	            PreparedStatement pst =
    	                con.prepareStatement(sql, new String[] {"id"});
     	            pst.setInt(1, p.getId());
     	            pst.setInt(2, p.getIdUser());
     	            pst.setString(3,p.getMision());
     	            pst.setString(4,p.getVision());
     	            pst.setString(5, p.getValores());
     	            pst.setString(6, p.getBreveHistoria());
    	            pst.setString(7, p.getPremiosOrganizacion());
     	            pst.setTimestamp(8, new java.sql.Timestamp(new Date().getTime()));


     	            return pst;
    	        }
    	    },
    	    keyHolder);
	}

}
