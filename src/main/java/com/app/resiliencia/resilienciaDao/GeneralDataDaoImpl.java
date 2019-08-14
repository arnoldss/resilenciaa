package com.app.resiliencia.resilienciaDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.app.resiliencia.model.User;
import com.app.resiliencia.util.Util;
@Transactional
@Repository
public class GeneralDataDaoImpl implements GeneralDataDao {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    JdbcTemplate jdbcTemplate;
	@Override
	public GeneralData getDataByUserId(Integer id) {
		// TODO Auto-generated method stub
		logger.info("idUser = "+id);
		GeneralData pr = null;
		try {
			pr =	(GeneralData) jdbcTemplate.queryForObject("select * from RS_GENERAL_DATA where idUser= ?  ",
	                new Object[] { id }, new BeanPropertyRowMapper<GeneralData>(GeneralData.class));
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		

	        return pr;	}
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		Integer pr =  (Integer) jdbcTemplate.queryForObject("select case when max(id) > 0 then max(id)+1 else 1 end as valor from RS_GENERAL_DATA ",
	                new Object[] { }, Integer.class);

	        return pr;	}
	@Override
	public void update(GeneralData GeneralData) {
		// TODO Auto-generated method stub
		final String sql = "UPDATE RS_GENERAL_DATA SET proyectoReciente = ?, nombre = ?, razonSocial = ?, rfc = ?, clasificationId = ?, fechaConstitucion = ?, inicioOperacion = ?,"
				+ "propertyTypeId = ?, comentarios = ?, calle = ?, numero = ?, colonia =?, codigoPostal = ?, ciudadId =?, estadoId = ?, pais = ?, telefonoOficina =?, www=?,"
				+ "email = ?, nombreDelContacto = ?, telefonoDeContacto =? , emailDeContacto = ?  WHERE idUser = ? ";
		jdbcTemplate.update(sql,
				GeneralData.getProyectoReciente(),GeneralData.getNombre(),GeneralData.getRazonSocial(),GeneralData.getRfc(),GeneralData.getClasificationId(),(GeneralData.getFechaConstitucion())
				,(GeneralData.getInicioOperacion()),GeneralData.getPropertyTypeId(),GeneralData.getComentarios(),GeneralData.getCalle(),GeneralData.getNumero(),GeneralData.getColonia(),
				GeneralData.getCodigoPostal(),GeneralData.getCiudadId(),GeneralData.getEstadoId(),GeneralData.getPais(),GeneralData.getTelefonoOficina(),GeneralData.getWww(),
				GeneralData.getEmail(),GeneralData.getNombreDelContacto(),GeneralData.getTelefonoDeContacto(),GeneralData.getEmailDeContacto(),GeneralData.getIdUser());
	}

	
	@Override
	public  void add(GeneralData p) {
		// TODO Auto-generated method stub
		final String sql = "INSERT INTO RS_GENERAL_DATA (id, createdAt, idUser, proyectoReciente,nombre, razonSocial, rfc, clasificationId, fechaConstitucion,"
				+ "inicioOperacion,propertyTypeId,comentarios,calle, numero, colonia, codigoPostal, ciudadId, estadoId, pais, telefonoOficina, www, email, "
				+ "nombreDelContacto, telefonoDeContacto,  emailDeContacto, clasificationName, propertyName  "+")  VALUES (?,?, ?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
    	jdbcTemplate.update(
    	    new PreparedStatementCreator() {
    	        public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
    	            PreparedStatement pst =
    	                con.prepareStatement(sql, new String[] {"id"});
     	            pst.setInt(1, p.getId());
     	            pst.setTimestamp(2, new java.sql.Timestamp(new Date().getTime()));
     	            pst.setInt(3, p.getIdUser());
     	            pst.setString(4,p.getProyectoReciente());
     	            pst.setString(5, p.getNombre()==null?"":p.getNombre());
     	            pst.setString(6, p.getRazonSocial()==null?"":p.getRazonSocial());
    	            pst.setString(7, p.getRfc()==null?"":p.getRfc());
    	            pst.setInt(8, 0);
    	            pst.setTimestamp(9,  new java.sql.Timestamp((p.getFechaConstitucion()).getTime()));
    	            pst.setTimestamp(10, new java.sql.Timestamp((p.getInicioOperacion()).getTime()));
     	            pst.setInt(11, p.getPropertyTypeId());
     	            pst.setString(12, p.getComentarios()==null?"":p.getComentarios());
     	            pst.setString(13, p.getCalle()==null?"":p.getCalle());
     	            pst.setString(14, p.getNumero()==null?"":p.getNumero());
     	            pst.setString(15, p.getColonia()==null?"":p.getColonia());
     	            pst.setString(16, p.getCodigoPostal()==null?"":p.getCodigoPostal());
     	            pst.setInt(17, p.getCiudadId());
     	            pst.setInt(18, 0);
     	            pst.setString(19, p.getPais()==null?"":p.getPais());
     	            pst.setString(20, p.getTelefonoOficina()==null?"":p.getTelefonoOficina());
     	            pst.setString(21, p.getWww()==null?"":p.getWww());
     	            pst.setString(22, p.getEmail()==null?"":p.getEmail());
     	            pst.setString(23, p.getNombreDelContacto()==null?"":p.getNombreDelContacto());
     	            pst.setString(24, p.getTelefonoDeContacto()==null?"":p.getTelefonoDeContacto());
     	            pst.setString(25, p.getEmail()==null?"":p.getEmail());
     	            pst.setString(26, p.getClasificationName()==null?"":p.getClasificationName());
     	            pst.setString(27, p.getPropertyName()==null?"":p.getPropertyName());

     	            return pst;
    	        }
    	    },
    	    keyHolder);
	}

}
