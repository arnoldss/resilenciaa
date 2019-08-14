package com.app.resiliencia.controllers;


import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.resiliencia.model.Catalog;
import com.app.resiliencia.model.Consejo;
import com.app.resiliencia.model.Encuesta;
import com.app.resiliencia.model.GeneralData;
import com.app.resiliencia.model.Sustentabilidad;
import com.app.resiliencia.model.User;
import com.app.resiliencia.model.WhoAreWe;
import com.app.resiliencia.resilienciaDao.AreaDao;
import com.app.resiliencia.resilienciaDao.CityDao;
import com.app.resiliencia.resilienciaDao.ClasificationDao;
import com.app.resiliencia.resilienciaDao.ConsejoDao;
import com.app.resiliencia.resilienciaDao.DocumentCatalogDao;
import com.app.resiliencia.resilienciaDao.EncuestaDao;
import com.app.resiliencia.resilienciaDao.EncuestaGeneraExcel;
import com.app.resiliencia.resilienciaDao.GeneralDataDao;
import com.app.resiliencia.resilienciaDao.IncomeSourceDao;
import com.app.resiliencia.resilienciaDao.PositionDao;
import com.app.resiliencia.resilienciaDao.PropertyDao;
import com.app.resiliencia.resilienciaDao.QuestionDao;
import com.app.resiliencia.resilienciaDao.ResponseDao;
import com.app.resiliencia.resilienciaDao.StateDao;
import com.app.resiliencia.resilienciaDao.SubAreaDao;
import com.app.resiliencia.resilienciaDao.SustentabilidadDao;
import com.app.resiliencia.resilienciaDao.UserDao;
import com.app.resiliencia.resilienciaDao.WhoAreWeDao;
import com.app.resiliencia.service.MailMail;
import com.fasterxml.jackson.core.JsonProcessingException;
@RestController
@RequestMapping(value={"/catalogs"})
public class CatalogsController{
	
	private static final Integer ACTIVO = 1;


	private final Logger logger = LoggerFactory.getLogger(this.getClass());


	@Value("${mccmnc}")
	private Integer mccmnc;
	@Value("${apicore.endpoint}")
	private String apicoreUrl;
	@Autowired
	UserDao userDao;
	@Autowired
	MailMail mail;
	
	@Autowired 
	GeneralDataDao GeneralDataDao;
	@Autowired
	PropertyDao PropertyDao;
	@Autowired
	ClasificationDao ClasificationDao;
	@Autowired
	SustentabilidadDao SustentabilidadDao;
	@Autowired
	StateDao StateDao;
	@Autowired 
	CityDao CityDao;
	@Autowired
	WhoAreWeDao WhoAreWeDao;
	@Autowired
	IncomeSourceDao IncomeSourceDao;
	@Autowired
	PositionDao PositionDao;
	
	@Autowired
	DocumentCatalogDao DocumentCatalogDao;
	@Autowired
	AreaDao	AreaDao;
	
	@Autowired
	SubAreaDao SubAreaDao;
	@Autowired
	QuestionDao QuestionDao;
	@Autowired
	ConsejoDao ConsejoDao;
	@Autowired 
	EncuestaDao EncuestaDao;
	@Autowired
	ResponseDao ResponseDao;
	
	@RequestMapping(value = "/getEncuesta", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Encuesta getEncuesta(
			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {	 
       
	
		
	return EncuestaDao.getRows();
	}
	@RequestMapping(value = "/getGeneralData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public GeneralData generalData(
 			@RequestParam("userId") final Integer userId,

			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {
		GeneralData GeneralData = null;
		User user =	(User) session.getAttribute("User");
		HttpSession misession= (HttpSession) request.getSession();	
		Integer idusuario=0;
		if(user.getRole()==0){	
			idusuario=(Integer) misession.getAttribute("idusuario");
		}else{	
					idusuario=user.getId();
		}
		
	try {
  	      	 GeneralData = 	GeneralDataDao.getDataByUserId(idusuario);
  	      	 GeneralData.setPropertyChoosen(PropertyDao.getDataById(GeneralData.getPropertyTypeId()));
  	      	 GeneralData.setClasificationChoosen(ClasificationDao.getDataById(GeneralData.getClasificationId()));
  	      	 GeneralData.setCity(CityDao.getDataById(GeneralData.getCiudadId()));
	}catch(Exception e) {
		e.printStackTrace();
		GeneralData = new GeneralData();
	}
       
		
	return GeneralData;
	}
	@RequestMapping(value = "/getWhoAreWe", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public WhoAreWe getWhoAreWe(
 			@RequestParam("userId") final Integer userId,

			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {
		WhoAreWe GeneralData = null;
		User user =	(User) session.getAttribute("User");
		HttpSession misession= (HttpSession) request.getSession();	
		Integer idusuario=0;
		if(user.getRole()==0){	
			idusuario=(Integer) misession.getAttribute("idusuario");
		}else{	
					idusuario=user.getId();
		}
		
	try {
  	      	 GeneralData = 	WhoAreWeDao.getDataByUserId(idusuario);
  	      	 if(GeneralData==null) {
  	      		 GeneralData = new WhoAreWe();

  	      	 }
  	      
	}catch(Exception e) {
		e.printStackTrace();
		GeneralData = new WhoAreWe();
	}
    
		
	return GeneralData;
	}
	@RequestMapping(value = "/getSustentabilidad", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Sustentabilidad> getSustentabilidad(
 			@RequestParam("userId") final Integer userId,

			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {
		List<Sustentabilidad> Sustentabilidad = null;
		User user =	(User) session.getAttribute("User");
		HttpSession misession= (HttpSession) request.getSession();	
		Integer idusuario=0;
		if(user.getRole()==0){	
			idusuario=(Integer) misession.getAttribute("idusuario");
		}else{	
					idusuario=user.getId();
		}
	try {
		logger.info("userId: "+userId+ " ACTIVO: "+ACTIVO+" SOURCENME: ");
		Sustentabilidad = 	SustentabilidadDao.getDataByStatus(idusuario,ACTIVO);
	    logger.info("SIZE: "+Sustentabilidad.size());
	}catch(Exception e) {
		e.printStackTrace();
	}
       
		
	return Sustentabilidad;
	}
	@RequestMapping(value = "/getConsejo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Consejo> getConsejo(
 			@RequestParam("userId") final Integer userId,

			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {
		List<Consejo> Consejo = null;
		User user =	(User) session.getAttribute("User");
		HttpSession misession= (HttpSession) request.getSession();	
		Integer idusuario=0;
		if(user.getRole()==0){	
			idusuario=(Integer) misession.getAttribute("idusuario");
		}else{	
					idusuario=user.getId();
		}
	try {
		logger.info("userId: "+idusuario+ " ACTIVO: "+ACTIVO+" SOURCENME: ");
		Consejo = 	ConsejoDao.getDataByStatus(idusuario,ACTIVO);
	    //logger.info("SIZE: "+Sustentabilidad.size());
	}catch(Exception e) {
		e.printStackTrace();
	}
       
		
	return Consejo;
	}
	@RequestMapping(value = "/removeSustentabilidad", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer removeSustentabilidad(
 			@RequestParam("id") final Integer id,

			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {
		List<Sustentabilidad> Sustentabilidad = null;
	try {
		logger.info("id: "+id);
		SustentabilidadDao.remove(id);

	}catch(Exception e) {
		e.printStackTrace();
	}
       
		
	return 200;
	}
	@RequestMapping(value = "/getClasification", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Catalog> getClasification(
 			

			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {
		List<Catalog> catalogs  = null;
	try {
		catalogs = 	ClasificationDao.getRows();
	      
	}catch(Exception e) {
		e.printStackTrace();
	}
       
		
	return catalogs;
	}
	@RequestMapping(value = "/getDocuments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Catalog> getDocuments(
 			

			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {
		List<Catalog> catalogs  = null;
	try {
		catalogs = 	DocumentCatalogDao.getRows();
	      
	}catch(Exception e) {
		e.printStackTrace();
	}
       
		
	return catalogs;
	}
	@RequestMapping(value = "/getArea", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Catalog> getArea(
 			

			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {
		List<Catalog> catalogs  = null;
	try {
		catalogs = 	AreaDao.getRows();
	      
	}catch(Exception e) {
		e.printStackTrace();
	}
       
		
	return catalogs;
	}
	
	@RequestMapping(value = "/getSubArea", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Catalog> getSubArea(
 			 HttpSession session
			,HttpServletRequest request,
			Integer areaId
	) throws JsonProcessingException {
		List<Catalog> catalogs  = null;
	try {
		catalogs = 	SubAreaDao.getRows(areaId);
	      
	}catch(Exception e) {
		e.printStackTrace();
	}
       
		
	return catalogs;
	}
	@RequestMapping(value = "/getRespuestas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Catalog> getRespuestas(
 			@RequestParam(value = "preguntaId", required=false) final Integer preguntaId,


			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {
		List<Catalog> catalogs  = null;
	try {
 			catalogs =  ResponseDao.getRows(preguntaId);
	}catch(Exception e) {
		e.printStackTrace();
	}
	return catalogs;
	}
	@RequestMapping(value = "/getPreguntas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Catalog> getPreguntas(
 			@RequestParam(value = "areaId", required=false) final Integer areaId,
 			@RequestParam(value = "subareaId", required=false) final Integer subareaId,


			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {
		List<Catalog> catalogs  = null;
	try {
 			catalogs =  QuestionDao.getRows(areaId,subareaId);
	}catch(Exception e) {
		e.printStackTrace();
	}
	return catalogs;
	}
	@RequestMapping(value = "/getCatalog", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Catalog> getCatalog(
 			@RequestParam("catalog") final Integer catalog,
 			@RequestParam(value = "areaId", required=false) final Integer areaId,


			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {
		List<Catalog> catalogs  = null;
	try {
		if(catalog.equals(1))
			catalogs = 	ClasificationDao.getRows();
		else if(catalog.equals(2))
			catalogs = 	PropertyDao.getProperties();
		else if(catalog.equals(3))
			catalogs =  PositionDao.getRows();
		else if(catalog.equals(5))
			catalogs =  DocumentCatalogDao.getRows();
		else if(catalog.equals(6))
			catalogs =  IncomeSourceDao.getRows();
		else if(catalog.equals(8))
			catalogs =  AreaDao.getRows();
		else if(catalog.equals(9))  
 			catalogs =  SubAreaDao.getRows(areaId);
		else if(catalog.equals(10))  
 			catalogs =  SubAreaDao.getRows(areaId);
		else if(catalog.equals(11))  
 			catalogs =  SubAreaDao.getRows(areaId);
		
 
		
	}catch(Exception e) {
		e.printStackTrace();
	}
       
		
	return catalogs;
	}
	@RequestMapping(value = "/updateCatalog", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer updateCatalog(
 			@RequestParam("catalog") final Integer catalogid,
 			@RequestParam("text") final String text,
 			@RequestParam("id") final Integer id,



			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {
		logger.info("catalog: "+catalogid+" text: "+text );
	try {
		Catalog catalog = new Catalog();
		catalog.setName(text);
		catalog.setStatus(1);
		if(catalogid.equals(1)) {
			catalog.setId(ClasificationDao.getId());
			ClasificationDao.addRow(catalog);
		}
		else if(catalogid.equals(2)) {
			catalog.setId(id);
			  	PropertyDao.update(catalog);
			}
		else if(catalogid.equals(3)) {
			catalog.setId(id);
 			  PositionDao.update(catalog);
			}
		else if(catalogid.equals(5)) {
			catalog.setId(id);
 			  DocumentCatalogDao.update(catalog);
			}
		else if(catalogid.equals(6)) {
			catalog.setId(id);
 			  IncomeSourceDao.update(catalog);
			}
		else if(catalogid.equals(8)) {
			  catalog.setId(id);
 			  AreaDao.update(catalog);
			}
		else if(catalogid.equals(9)) {
			catalog.setId(id);
 			 SubAreaDao.update(catalog);
			}
		else if(catalogid.equals(10)) {
			catalog.setId(id);
			QuestionDao.update(catalog);
			}
		else if(catalogid.equals(11)) {
			catalog.setId(id);
			ResponseDao.update(catalog);
			}
	}catch(Exception e) {
		e.printStackTrace();
	}
       
		
	return 200;
	}
	public String decode(String s) {
	    return StringUtils.newStringUtf8(Base64.decodeBase64(s));
	}
	@RequestMapping(value = "/addCatalog", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer addCatalog(
 			@RequestParam("catalog") final Integer catalogid,
 			@RequestParam("name") final String name,
 			@RequestParam(value = "areaId", required=false) final String areaId,
 			@RequestParam(value = "subareaId", required=false) final String subAreaId,
 			@RequestParam(value = "preguntaId", required=false) final String preguntaId,
 			@RequestParam(value = "ppregunta", required=false)  String ppregunta,
 			@RequestParam(value = "spregunta", required=false)  String spregunta,
 			@RequestParam(value = "tpregunta", required=false)  String tpregunta,


			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {
		logger.info("catalog: "+catalogid+" name: "+name+" AreaId: "+areaId+ "SubAreaId: "+subAreaId+" preguntaId: "+preguntaId);
		ppregunta=decode(ppregunta);
		spregunta=decode(spregunta);
		tpregunta=decode(tpregunta);
		logger.info("ppregunta: "+ppregunta);
		logger.info("spregunta: "+spregunta);
		logger.info("tpregunta: "+tpregunta);

	try {
		Catalog catalog = new Catalog();
		catalog.setName(name);
		catalog.setStatus(1);
		if(catalogid.equals(1)) {
			catalog.setId(ClasificationDao.getId());
			ClasificationDao.addRow(catalog);
		}
		else if(catalogid.equals(2)) {
			catalog.setId(PropertyDao.getId());
			  	PropertyDao.addProperty(catalog);
			}
		else if(catalogid.equals(3)) {
			catalog.setId(PositionDao.getId());
 			  PositionDao.addRow(catalog);
			}
		else if(catalogid.equals(5)) {
			catalog.setId(DocumentCatalogDao.getId());
 			  DocumentCatalogDao.addRow(catalog);
			}
		else if(catalogid.equals(6)) {
			catalog.setId(IncomeSourceDao.getId());
 			  IncomeSourceDao.addRow(catalog);
			}
		else if(catalogid.equals(8)) {
			logger.info("Area Dao if");
			catalog.setId(AreaDao.getId());
 			  AreaDao.addRow(catalog);
			}
		else if(catalogid.equals(9)) {
			catalog.setId(SubAreaDao.getId());
			catalog.setAreaId(Integer.decode(areaId));
 			 SubAreaDao.addRow(catalog);
			}
		else if(catalogid.equals(10)) {
			catalog.setId(QuestionDao.getId());
			catalog.setAreaId(Integer.decode(areaId));
			catalog.setSubareaId(Integer.decode(subAreaId));
			QuestionDao.addRow(catalog);
			}
		else if(catalogid.equals(11)) {
			try {
			catalog.setAreaId(Integer.decode(areaId));
			catalog.setSubareaId(Integer.decode(subAreaId));
			catalog.setQuestionid(Integer.decode(preguntaId));
				if(ppregunta==null || ppregunta.equals(""))
					throw new Exception("");
			catalog.setId(ResponseDao.getId());
			catalog.setName(ppregunta);
			catalog.setNumber(1);
			ResponseDao.addRow(catalog);
			}catch(Exception e) {
				
			}
			try {
				if(spregunta==null  || spregunta.equals(""))
					throw new Exception("");
			catalog.setId(ResponseDao.getId());
			catalog.setName(spregunta);
			catalog.setNumber(2);
			ResponseDao.addRow(catalog);
			}catch(Exception e) {
				
			}
			if(tpregunta==null || tpregunta.equals(""))
				throw new Exception("");
			catalog.setId(ResponseDao.getId());
			catalog.setName(tpregunta);
			catalog.setNumber(3);
			ResponseDao.addRow(catalog);
			}
	}catch(Exception e) {
		e.printStackTrace();
	}
       
		
	return null;
	}
	@RequestMapping(value = "/removeCatalog", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer removeCatalog(
 			@RequestParam("catalog") final Integer catalogid,
 			@RequestParam("id") final Integer id,


			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {
		logger.info("removeCatalog -- catalog: "+catalogid+" id: "+id);
	try {
		
		if(catalogid.equals(1)) {
			ClasificationDao.removeRow(id);
		}
		else if(catalogid.equals(2)) {
			  	PropertyDao.removeProperty(id);
			}
		else if(catalogid.equals(3)) {
 			  PositionDao.removeRow(id);
			}
		else if(catalogid.equals(5)) {
 			  DocumentCatalogDao.removeRow(id);
			}
		else if(catalogid.equals(6)) {
 			  IncomeSourceDao.removeRow(id);
			}
		else if(catalogid.equals(8)) {
			logger.info("Area Dao if");
 			  AreaDao.removeRow(id);
			}
		else if(catalogid.equals(9)) {
 			 SubAreaDao.removeRow(id);
			}
		else if(catalogid.equals(10)) {
			
			QuestionDao.removeRow(id);
			}
		else if(catalogid.equals(11)) {
			
			ResponseDao.removeRow(id);
			}
	}catch(Exception e) {
		e.printStackTrace();
	}
       
		
	return null;
	}
	@RequestMapping(value = "/getCities", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Catalog> geCities(
 			

			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {
		List<Catalog> catalogs  = null;
	try {
		catalogs = 	CityDao.getRows();
	      
	}catch(Exception e) {
		e.printStackTrace();
	}
       
		
	return catalogs;
	}
	@RequestMapping(value = "/getIncomeSource", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Catalog> getIncomeSource(
 			

			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {
		List<Catalog> catalogs  = null;
	try {
		catalogs = 	IncomeSourceDao.getRows();
	      
	}catch(Exception e) {
		e.printStackTrace();
	}
       
		
	return catalogs;
	}
	 
	@RequestMapping(value = "/getStates", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Catalog> geStates(
 			

			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {
		List<Catalog> catalogs  = null;
	try {
		catalogs = 	StateDao.getRows();
	      
	}catch(Exception e) {
		e.printStackTrace();
	}
       
		
	return catalogs;
	}
	@RequestMapping(value = "/getProperties", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Catalog> getProperties(
 			

			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {
		List<Catalog> catalogs  = null;
	try {
		catalogs = 	PropertyDao.getProperties();
	      
	}catch(Exception e) {
		e.printStackTrace();
	}
       
		
	return catalogs;
	}
	@RequestMapping(value = "/getPositions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Catalog> getPositions(
 			

			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {
		List<Catalog> catalogs  = null;
	try {
		catalogs = 	PositionDao.getRows();
	      
	}catch(Exception e) {
		e.printStackTrace();
	}
       
		
	return catalogs;
	}
	@RequestMapping(value = "/addSustentabilidad", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public int addSustentabilidad(
			@RequestBody Sustentabilidad Sustentabilidad,
			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {
		logger.info("addSustentabilidad  : "+Sustentabilidad.getSourceName());
 		try {
	     
	    		  Sustentabilidad.setId(SustentabilidadDao.getId());
	    		  SustentabilidadDao.add(Sustentabilidad);
	    	 
 		      
		}catch(Exception e) {
			e.printStackTrace();
		}	 
	return 00;
	}
	@RequestMapping(value = "/addConsejo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public int addConsejo(
			@RequestBody Consejo consejo,
			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {
		logger.info("addCOnsejo  : "+consejo.getPositionName());
 		try {
	     
	    		  consejo.setId(ConsejoDao.getId());
	    		  ConsejoDao.add(consejo);
	    	 
 		      
		}catch(Exception e) {
			e.printStackTrace();
		}	 
	return 200;
	}
	@RequestMapping(value = "/addGeneralData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public int getUser(
			@RequestBody GeneralData GeneralData,
			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {
		logger.info("addGeneralData  : "+GeneralData.getComentarios());
		logger.info("GeneralData{}",GeneralData);
		GeneralData thereIsInfo	=	null;
		try {

	    	  thereIsInfo   = 	GeneralDataDao.getDataByUserId(GeneralData.getIdUser());
	    	  if(thereIsInfo==null) {
	    		  logger.info("is null adding info to generalDataDao");
	    		  GeneralData.setId(GeneralDataDao.getId());
	    		  GeneralDataDao.add(GeneralData);
	    	  }else {
	    		  logger.info("Updating information: "+GeneralData.getPropertyTypeId());
	    		  GeneralDataDao.update(GeneralData);
	    	  }
 		     
		}catch(Exception e) {
			e.printStackTrace();
		}	 
	return 00;
	}
	@RequestMapping(value = "/addWhoAreWe", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public int addWhoAreWe(
			@RequestBody WhoAreWe GeneralData,
			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {
		logger.info("addWhoAreWe  : ");
		WhoAreWe thereIsInfo	=	null;
		try {

	    	  thereIsInfo   = 	WhoAreWeDao.getDataByUserId(GeneralData.getIdUser());
	    	  if(thereIsInfo==null) {
	    		  GeneralData.setId(WhoAreWeDao.getId());
	    		  WhoAreWeDao.add(GeneralData);
	    	  }else {
	    		  logger.info("Updating information: ");
	    		  WhoAreWeDao.update(GeneralData);
	    	  }
 		     
		}catch(Exception e) {
			e.printStackTrace();
		}	 
	return 00;
	}
	@RequestMapping(value = "/activateUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer activateUser(
			@RequestParam("id") final Integer id,
			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {
		logger.info("activateUser " );
		User user = userDao.getUserById(id);
		if(user.getStatus().equals(1)) {
			user.setStatus(0);
		}else {
			logger.info("Mail to: "+user.getEmail());
			mail.sendMail("resilienciaapp@gmail.com", user.getEmail(), "Activacion de cuenta", "Tu usuario es: "+user.getEmail()+" y tu contrasena es: "+user.getPassword());
			user.setStatus(1);
		}
		userDao.updateUser(user);
	return 200;
	}
	 
	
	@RequestMapping(value = "/recover", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer recover(
			@RequestParam("mail") final String mailto,
			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {
		logger.info("activateUser " );
		User user = userDao.getUserByEmail(mailto);
		logger.info("Mail to: "+user.getEmail());
		mail.sendMail("resilienciaapp@gmail.com", user.getEmail(), "Informacion para acceder a portal. ", "Tu usuario es: "+user.getEmail()+" y tu contrasena es: "+user.getPassword());
 	 
 	return 200;
	}
	
	@RequestMapping(value = "/changeRole", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer changeRole(
			@RequestParam("id") final Integer id,
			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {
		logger.info("changeRole " );
		User user = userDao.getUserById(id);
		if(user.getRole().equals(1)) {
			user.setRole(0);
		}else {
			user.setRole(1);
		}
		userDao.updateUser(user);
	return 200;
	}
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUsers(
 	 
			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {
		logger.info("get Users: " );
		 
	 
	return userDao.getUsers();
	}
	@RequestMapping(value = "/validateByEmail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer validateByEmail(
			@RequestParam("email") final String email,
			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {
		logger.info("get Users: " );
		if(userDao.getUserByEmail(email)==null)
				{
			return 0;
				}
		else
			return 1;
	
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String logout(
 		
			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {
 		   logger.info("logout");
 	        String cookie = session.getId();
	        logger.info("JSESSION: "+cookie);
     	    session.setAttribute("allowed", false);
 	return "200";
	}
	@RequestMapping(value = "/register", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer register(
 			@RequestParam("username") final String username,
			@RequestParam("mail") final String mailto,
			@RequestParam("tel") final String tel,
			@RequestParam("pwd") final String pwd,
			@RequestParam("comment") final String comment


			,HttpServletRequest request
	) throws JsonProcessingException {
		logger.info("register: "+request.getQueryString());
		UUID uuid = UUID.randomUUID();
	
		//mail.sendMail("", mailto, "Activacion de cuenta", "Tu usuario es: y tu contrasena es:");
		Integer id	=	userDao.getLastId();
		logger.info("El id es: "+id);
		User User = new User();
		User.setId(id);
		User.setCreatedAt(new Date());
		User.setEmail(mailto);
		User.setName(username);
		User.setPassword(pwd);
		User.setTelefono(tel);
		User.setRole(1);
		User.setComments(comment);
		User.setStatus(0);
		userDao.addUser(User);
	return 200;
	}

}
