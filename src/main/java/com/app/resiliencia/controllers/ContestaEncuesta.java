package com.app.resiliencia.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.resiliencia.model.Area;
import com.app.resiliencia.model.Catalog;
import com.app.resiliencia.model.Encuesta;
import com.app.resiliencia.model.Promedios;
import com.app.resiliencia.model.RespuestaEncuesta;
import com.app.resiliencia.model.SubArea;
import com.app.resiliencia.model.User;
import com.app.resiliencia.resilienciaDao.EncuestaDao;
import com.app.resiliencia.resilienciaDao.EncuestaGeneraExcel;
import com.app.resiliencia.resilienciaDao.UserDao;
import com.fasterxml.jackson.core.JsonProcessingException;


@RestController
@RequestMapping(value={"/contestaEncuesta"})
public class ContestaEncuesta {
	@Autowired 
	EncuestaDao EncuestaDao;
	@Autowired
	UserDao userDao;

	
	@RequestMapping(value = "/getUsersEncuesta", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUsers(
 	 
			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {
		
		System.out.println("Entro por getUsersEncuesta----");
	 
	return userDao.getUsersEncuesta();
	}
	
	
	@RequestMapping(value = "/saveEncuesta", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean saveEncuesta(
			@RequestBody final ArrayList<RespuestaEncuesta>  respuestasEncuesta,
 			                                                 
			HttpSession session
			,HttpServletRequest request
	) {
		
		 boolean response=true;
		 HttpSession misession= (HttpSession) request.getSession();
		 User usuario=(User) misession.getAttribute("usuario_en_session");	
		 
		 try {
			 EncuestaDao.saveEncuesta(respuestasEncuesta, usuario.getId());		
			  System.out.println("Usuario en session :  "+usuario.getId() +" - "+usuario.getEmail());
		  	      	 

			} catch(Exception e) {

			    System.out.println("ERROR: el valor de tipo String contiene caracteres no numéricos");

			}
		
	return response;
	}
	
	
	
	

	
	
	
	@RequestMapping(value = "/generaExcel", method = RequestMethod.GET)
	public String generaExcel(
			HttpServletResponse response,
			HttpServletRequest request
	)  {
		
		
        int idusuario=Integer.parseInt(request.getParameter("idusuario"));
		
		EncuestaGeneraExcel excelDispatcher = EncuestaGeneraExcel.getInstance();
		try {
			List<Catalog> respuestasUser=EncuestaDao.getRespeuestasUsuario(idusuario);
			OutputStream out = response.getOutputStream();
			excelDispatcher.generateExcelFile(out,EncuestaDao.getRows(),respuestasUser);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=reporte.xls");
		 
		
	
	    return null;
	}
	
	
	
	
	
	@RequestMapping(value = "/reporteWebEncuesta", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Encuesta getEncuesta(
			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {	 
		
		User user=new User();
		 HttpSession misession= (HttpSession) request.getSession();
		 user=(User) misession.getAttribute("usuario_en_session");
		 
		 
		 int usuarioFilter=0;
		 if(user.getRole()==0){	
			 usuarioFilter=Integer.parseInt(request.getParameter("idusuario"));			 
		 }else{	
			 usuarioFilter= user.getId(); 
		 }
		 
		 //int usuarioRol=User.getRole();
		 List<Promedios> promedios=null;
				 
		 //usuarioFilter=User.getId();
		 System.out.println("Ususari de parametro : "+usuarioFilter);
		 
		 /*if(usuarioRol==0){	 
			 usuarioFilter=User.getId();
			 System.out.println("Ususari de parametro : "+usuarioFilter);

		 }else{
			 User  usuario=(User) misession.getAttribute("usuario_en_session");
			 usuarioFilter=usuario.getId();
			 System.out.println("Ususari de parametro : "+usuarioFilter);
		 }*/
		 
       
		 promedios= EncuestaDao.getPromedioSubcategoriaUsuario(usuarioFilter);
		 Encuesta areas=EncuestaDao.getRows();
	     
		
	
     
	
		Double sumaPromedios = null;		
		List<Double> promedioTotal = new  ArrayList<Double>();
		for(Iterator<Area> b = areas.getArea().iterator(); b.hasNext(); ) {
			   Area item2 = b.next();
			   
			   for(Iterator<Promedios> i = promedios.iterator(); i.hasNext(); ) {			   
			
					Promedios item = i.next();					
			   
					      
					      
							   //areaCount=item2.getSubarea().size();
							   for(Iterator<SubArea> c = item2.getSubarea().iterator(); c.hasNext(); ) {
								   SubArea item3 = c.next();
								   
								   if(item3.getId()==item.getId()){
									   //System.out.println(item3.getName() + "Promedio : " + item.getPromedio());									  
									    ///if(usuario.getRole()==0){
											   item3.setPromedio(item.getPromedio()); 
											   
											   System.out.println(item3.getName()+ "----" +item.getPromedio());
											   

									    /// }
									   
								   }
								   
							   }
							   
							  
							   
							   
							   
							   
							   
							
						
							 
							 sumaPromedios = item2.getSubarea().stream().mapToDouble(sla -> sla.getPromedio()).sum();
							 double promedioArea=sumaPromedios/item2.getSubarea().size();	
							 
							 
							 DecimalFormat df3 = new DecimalFormat("###.##");
						     Double doublevar = Double.parseDouble(df3.format(promedioArea));
							 System.out.println("Numero normal ---------  "+promedioArea+ " Redondeooooo " +doublevar);
							
							 //if(usuario.getRole()==0){
								 item2.setPromedio(promedioArea); 
						    /// }
								
								
								 
							 if(doublevar>0 && doublevar <=1.99){
						      System.out.println("roja---------------------------");
								 item2.setImg("roja.png");
							 }
							 
						     if(doublevar>= 2 && doublevar <=2.99){
						    	 System.out.println("amarilla---------------------------");
						    	 item2.setImg("amarilla.png");								 
							}
						     if(doublevar ==3 ){
						    	 System.out.println("verde---------------------------");
						    	 item2.setImg("verde.png");	
						    }
							       
						     
						    
			   
			   } 
	            
			   promedioTotal.add(item2.getPromedio());
	           ///areas.setPromedio(promedio);
			   
		}
		
		double  promedio = areas.setPromedio(promedioTotal.stream().mapToDouble(sla -> sla).sum()/promedioTotal.size());
		System.out.println("Promedio total de la encuesta " + promedioTotal.stream().mapToDouble(sla -> sla).sum()/promedioTotal.size());
		areas.setPromedio(promedio);
		
		
		

		 if(promedio>1 || promedio <1.99){
			 areas.setImg("roja.png");
		 }
		 
	     if(promedio>2 || promedio <2.99){
	    	 areas.setImg("amarilla.png");								 
		}
	     if(promedio ==3 ){
	    	 areas.setImg("verde.png");	
	    }
	     
	     areas.setRol(user.getRole());
	     
	     System.out.println("Roleeeeeee" + user.getRole());
	     
		promedioTotal.isEmpty();
		
		
	
	      
		
	return areas;
	}
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/getCountPreguntas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer getCountPreguntas( 			                                                 
			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {		
		Integer totalPreguntas = null;
	try {					
		totalPreguntas=EncuestaDao.getCountPreguntas();			
  	      	 
	}catch(Exception e) {
		e.printStackTrace();
	}
       
		
	return totalPreguntas;
	}
	
	
	
	
	
	@RequestMapping(value = "/getValidaIsEncuesta", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer getValidaIsEncuesta( 			                                                 
			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {		
		Integer isResponseEncuesta = null;
		
		HttpSession misession= (HttpSession) request.getSession();
		User usuario=(User) misession.getAttribute("usuario_en_session");				
		System.out.println("Usuario en session :  "+usuario.getId() +" - "+usuario.getEmail());
		
		
	try {					
		isResponseEncuesta=EncuestaDao.getCountEncuestaUser(usuario.getId());			
  	      	 
	}catch(Exception e) {
		e.printStackTrace();
	}
       
		
	return isResponseEncuesta;
	}
	
	
	
	
	
	@RequestMapping(value = "/deleteEncuestaUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteEncuestaUser( 			                                                 
			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {
		
		Integer idusuario=Integer.parseInt(request.getParameter("idusuario"));				
		System.out.println("Eliminando encuesta de usuario  :  "+idusuario);
		
		
	try {					
		    EncuestaDao.deleteEncuestaUser(idusuario);			
  	      	 
	}catch(Exception e) {
		e.printStackTrace();
	}
       
		
	//return isResponseEncuesta;
	}
	
	
	
	
	
	
	
	
	
	

}

	
	
	
	


