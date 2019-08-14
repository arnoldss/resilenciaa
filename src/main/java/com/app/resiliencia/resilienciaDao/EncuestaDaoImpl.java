package com.app.resiliencia.resilienciaDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

import com.app.resiliencia.model.Area;
import com.app.resiliencia.model.Catalog;
import com.app.resiliencia.model.Encuesta;
import com.app.resiliencia.model.Pregunta;
import com.app.resiliencia.model.Promedios;
import com.app.resiliencia.model.RespuestaEncuesta;
import com.app.resiliencia.model.SubArea;
import com.fasterxml.jackson.databind.ObjectMapper;
@Transactional
@Repository
public class EncuestaDaoImpl implements EncuestaDao {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	 @Autowired
	 ObjectMapper objectMapper;
    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<RespuestaEncuesta> getRespuestas(Integer preguntaId){
    	List<RespuestaEncuesta> pr =  jdbcTemplate.query("select distinct\n" + 
    			" --sub.id as id, sub.name as name\n" + 
    			" --question.id as id, question.name as name\n" + 
    			" response.id as id, response.number as number, response.name as name\n" + 
    			"from rs_area_catalog area\n" + 
    			"inner join  rs_subarea_catalog sub on sub.areaid = area.id \n" + 
    			"inner join rs_question question on question.subareaid = sub.id\n" + 
    			"inner join rs_response response on response.questionid = question.id\n" + 
    			"where response.questionid = ?"  ,
	                new Object[] {preguntaId}, new BeanPropertyRowMapper<RespuestaEncuesta>(RespuestaEncuesta.class));
		 

    	return pr;
    }
    public List<Pregunta> getPreguntas(Integer areaId, Integer subAreaId){
    	List<Pregunta> pregunta =  jdbcTemplate.query("select distinct\n" + 
    			" --sub.id as id, sub.name as name\n" + 
    			" question.id as id, question.name as name\n" + 
    			"--, response.id as responseid, response.number as number, response.name as responsename\n" + 
    			"from rs_area_catalog area\n" + 
    			"inner join  rs_subarea_catalog sub on sub.areaid = area.id \n" + 
    			"inner join rs_question question on question.subareaid = sub.id\n" + 
    			"inner join rs_response response on response.questionid = question.id\n" + 
    			"where question.areaid = ? and question.subareaid = ?"  ,
	                new Object[] {areaId,subAreaId}, new BeanPropertyRowMapper<Pregunta>(Pregunta.class));
    	for(int x=0; x<pregunta.size(); x++) {
    		pregunta.get(x).setRespuesta(getRespuestas(pregunta.get(x).getId()));;
		}

    	return pregunta;
    }
    public List<SubArea> getSubAreas(Integer areaId){
    	List<SubArea> subareas =  jdbcTemplate.query("select distinct\n" + 
    			" sub.id as id, sub.name as name\n" + 
    			"--, question.id as questionid, question.name as questionname\n" + 
    			"--, response.id as responseid, response.number as number, response.name as responsename\n" + 
    			"from rs_area_catalog area\n" + 
    			"inner join  rs_subarea_catalog sub on sub.areaid = area.id \n" + 
    			"inner join rs_question question on question.subareaid = sub.id\n" + 
    			"inner join rs_response response on response.questionid = question.id\n" + 
    			"where sub.areaid = ?",
	                new Object[] {areaId}, new BeanPropertyRowMapper<SubArea>(SubArea.class));
    	for(int x=0; x<subareas.size(); x++) {
    		subareas.get(x).setPregunta(getPreguntas(areaId,subareas.get(x).getId()));;
		}
    	return subareas;
    }
	@Override
	public Encuesta getRows() {
		  Encuesta encuesta = new Encuesta();
	  try {
		List<Area> areas =  jdbcTemplate.query("select distinct area.id as id, area.name as name\n" + 
		  		"--, sub.id as subareaid, sub.name as subareaname\n" + 
		  		"--, question.id as questionid, question.name as questionname\n" + 
		  		"--, response.id as responseid, response.number as number, response.name as responsename\n" + 
		  		"from rs_area_catalog area\n" + 
		  		"inner join  rs_subarea_catalog sub on sub.areaid = area.id \n" + 
		  		"inner join rs_question question on question.subareaid = sub.id\n" + 
		  		"inner join rs_response response on response.questionid = question.id  ORDER BY area.id",
	                new Object[] {}, new BeanPropertyRowMapper<Area>(Area.class));
			for(int x=0; x<areas.size(); x++) {
				areas.get(x).setSubarea(getSubAreas(areas.get(x).getId()));
			}
			encuesta.setArea(areas);
			logger.info(objectMapper.writeValueAsString(encuesta));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	        
	        return encuesta;
	        
	}

	@Override
	public void removeRow(Integer id) {
		// TODO Auto-generated method stub
		final String sql = "UPDATE RS_QUESTION SET status = ?  WHERE id = ? ";
		jdbcTemplate.update(sql,
				0,id);
	}

	@Override
	public void addRow(Catalog p) {
		// TODO Auto-generated method stub
		final String sql = "INSERT INTO RS_QUESTION (id, name, status, createdAt,areaId,subareaId "+")  VALUES ( ?,?,?,?, ?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
    	jdbcTemplate.update(
    	    new PreparedStatementCreator() {
    	        public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
    	            PreparedStatement pst =
    	                con.prepareStatement(sql, new String[] {"id"});
     	            pst.setInt(1, p.getId());
     	            pst.setString(2, p.getName()==null?"":p.getName());
     	            pst.setInt(3, p.getStatus());
     	            pst.setTimestamp(4, new java.sql.Timestamp(new Date().getTime()));
     	            pst.setInt(5, p.getAreaId());
     	            pst.setInt(6, p.getSubareaId());

     	            return pst;
    	        }
    	    },
    	    keyHolder);
	}

	@Override
	public Catalog getDataById(Integer id) {
		// TODO Auto-generated method stub
		Catalog pr = null;
		try {
			pr =	(Catalog) jdbcTemplate.queryForObject("select * from RS_QUESTION where id= ?  ",
	                new Object[] { id }, new BeanPropertyRowMapper<Catalog>(Catalog.class));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		

	        return pr;		}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		Integer pr =  (Integer) jdbcTemplate.queryForObject("select case when max(id) > 0 then max(id)+1 else 1 end as valor from RS_QUESTION ",
	                new Object[] { }, Integer.class);

	        return pr;
	}
	
	
	@Override
	public boolean saveEncuesta(ArrayList <RespuestaEncuesta>  encuesta,int idusuario) {
		// TODO Auto-generated method stub
		boolean response=true;
		
		try {
		
				for(Iterator<RespuestaEncuesta> i = encuesta.iterator(); i.hasNext(); ) {
					  RespuestaEncuesta item = i.next();
					  saveQuestion(item.getId() , item.getNumber(),idusuario,item.getComentario());			  
				}
		} catch(Exception e) {
			response=false;
		    System.out.println("ERROR: al guardar encuaesta el usuario " + response);
		}
		
		
		return response;
		
	}
	
	public boolean saveQuestion(int idpregunta , int idx,int idusuario,String comentario){
		boolean response=true;
		final String sql = "INSERT INTO RS_RESPONSE_ENC (id, createdEn, idrespuesta,ponderacion, idusuario , comentario)  VALUES ( ?,?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
    	jdbcTemplate.update(
    	    new PreparedStatementCreator() {
    	        public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
    	            PreparedStatement pst =
    	                con.prepareStatement(sql, new String[] {"id"});
     	            pst.setInt(1, getIdResponseEncuesta());
     	            pst.setTimestamp(2, new java.sql.Timestamp(new Date().getTime()));
     	            pst.setInt(3, idpregunta);
     	            pst.setInt(4, idx);
     	            pst.setInt(5, idusuario);
     	            pst.setString(6, comentario);
     	           

     	            return pst;
    	        }
    	    },
    	    keyHolder);
		System.out.println("Se Guardo en  base de datos ID: "+ idpregunta+ "Number:"+ idx+ " Usuario: "+idusuario); 
		return response;
	}
	
	
	@Override
	public Integer getIdResponseEncuesta() {
		// TODO Auto-generated method stub
		Integer pr =  (Integer) jdbcTemplate.queryForObject("select case when max(id) > 0 then max(id)+1 else 1 end as valor from RS_RESPONSE_ENC ",
	                new Object[] { }, Integer.class);

	        return pr;
	}
	
	
	
	
	public void excel(Encuesta encuesta){	
		
		
		/*Categoria*/
		for(Iterator<Area> i = encuesta.getArea().iterator(); i.hasNext(); ) {
			Area item = i.next();
			    
			    System.out.println("categorias : -"+item.getId() +""+item.getName());	
			    
			    for(Iterator<SubArea> a = item.getSubarea().iterator(); a.hasNext(); ) {
			    	SubArea itemSa = a.next();
			    	System.out.println("Subcategoria : -"+itemSa.getName());
			    	for(Iterator<Pregunta> b = itemSa.getPregunta().iterator(); b.hasNext(); ) {
			    		Pregunta itemPr = b.next();
			    		System.out.println("Pregunta : -"+itemPr.getName());
			    		for(Iterator<RespuestaEncuesta> c = itemPr.getRespuesta().iterator(); c.hasNext(); ) {
			    			RespuestaEncuesta itmRes= c.next();
			    			System.out.println("Respuesta : -"+itmRes.getName());
			    		}
			    		
			    	}
			    	
			    }
			    
			    
			    
		}
		
		
		
		
	}
	
	

	
	
	
	
	@Override
	public List<Catalog> getRespeuestasUsuario(int iduser) {
		// TODO Auto-generated method stub
	    List<Catalog> Catalogs =  jdbcTemplate.query("select idrespuesta as id,ponderacion as status  from RS_RESPONSE_ENC  where idusuario= ? ",
                new Object[] { iduser }, new BeanPropertyRowMapper<Catalog>(Catalog.class));

        return Catalogs;

	}
	
	
	
	

	@Override
	public List<Promedios> getPromedioSubcategoriaUsuario(int iduser) {
		// TODO Auto-generated method stub
	    List<Promedios> promedios =  jdbcTemplate.query("select  subcategoria.id,subcategoria.name,sum(respuestauser.ponderacion)/(select count(*) from rs_question where subareaid=subcategoria.id) as promedio   from RS_RESPONSE_ENC respuestauser inner join rs_response respuestas on respuestauser.idrespuesta=respuestas.id inner join rs_question question on respuestas.questionid=question.id inner join rs_subarea_catalog subcategoria on subcategoria.id=question.subareaid where respuestauser.idusuario= ? group by subcategoria.id,subcategoria.name order by subcategoria.areaid; ",
                new Object[] { iduser }, new BeanPropertyRowMapper<Promedios>(Promedios.class));

        return promedios;

	}
	
	
	@Override
	public Integer getCountPreguntas() {
		// TODO Auto-generated method stub		
				
		Integer totalPreguntas =  (Integer) jdbcTemplate.queryForObject("select count(*) from rs_question; ",
                new Object[] {  },Integer.class);

        return totalPreguntas;

	}
	
	
	
	@Override
	public Integer getCountEncuestaUser(int iduser) {
		// TODO Auto-generated method stub		
				
		Integer isResponseEncuesta =  (Integer) jdbcTemplate.queryForObject("select count(*) from rs_response_enc where idusuario= ?; ",
                new Object[] { iduser  },Integer.class);

        return isResponseEncuesta;

	}
	
	
	@Override
	public Integer deleteEncuestaUser(int iduser) {
		// TODO Auto-generated method stub		
		Integer isResponseDeleteEncuesta=0;		
		/*Integer isResponseDeleteEncuesta =  (Integer) */
		jdbcTemplate.queryForObject("delete from rs_response_enc where idusuario = ?; ",
                new Object[] { iduser  },Integer.class);
		System.out.println("Eliminando encuesta de usuario :" + iduser + " Respuesta de base de datos :"+ isResponseDeleteEncuesta);

        return isResponseDeleteEncuesta;

	}
	
	

}
