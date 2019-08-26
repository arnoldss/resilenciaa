package com.app.resiliencia.controllers;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.app.resiliencia.dto.ResponseLogin;
import com.app.resiliencia.model.*;
import com.app.resiliencia.resilienciaDao.EncuestaDao;
import com.app.resiliencia.resilienciaDao.UserDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.app.resiliencia.service.*;
@RestController
@RequestMapping(value={"/user"})
public class UserController{
	
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
	EncuestaDao EncuestaDao;
	@RequestMapping(value = "/login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseLogin login(
 			@RequestParam("username") final String username,
			@RequestParam("pwd") final String pwd,
			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {
		logger.info("login: "+request.getQueryString());
		ResponseLogin response = new ResponseLogin();
		User User	=	userDao.userAllowed(username, pwd);
	    String cookie = session.getId();
	    HttpSession misession= (HttpSession) request.getSession();
	    misession.setAttribute("usuario_en_session", User);
	    
	        logger.info("JSESSION: "+cookie);
		 if(User!=null) {
			 SimpleDateFormat dt1 = new SimpleDateFormat("MMMMM. yyyy");
 			 User.setSince(dt1.format(User.getCreatedAt()));             	
			 if(User.getStatus().equals(1)) {
				 Integer isResponseEncuesta = null;
				  isResponseEncuesta=EncuestaDao.getCountEncuestaUser(User.getId());
				  
				if(isResponseEncuesta> 0 ){
					User.setEncuesta(true);
				}else{
					User.setEncuesta(false);
				}
				 
				 
				response.setAllowed(true);
			    session.setAttribute("allowed", true);
			    session.setAttribute("User", User);
			    session.setAttribute("rol",User.getRole() );

			 }
			 response.setUser(User);
		 }else {
			 response.setAllowed(false);
			 session.setAttribute("allowed", false);

		 }
			 
		
	 
	return response;
	}
	
	@RequestMapping(value = "/getUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public User getUser(
 	 
			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {
		logger.info("get User: "+request.getQueryString());
 		  String cookie = session.getId();
	        logger.info("JSESSION: "+cookie);
	        User user =	(User) session.getAttribute("User");
	 
	return user;
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
		//	logger.info("Mail to: "+user.getEmail());
		//	mail.sendMail("resilienciaapp@gmail.com", user.getEmail(), "Activacion de cuenta", "Tu usuario es: "+user.getEmail()+" y tu contrasena es: "+user.getPassword());
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
	@RequestMapping(value = "/getUsersRoleUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUsersRoleUser(
 	 
			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {
		logger.info("get Users: " );
		 
	 
	return userDao.getUsersRoleUser();
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
 
 

	@RequestMapping(value = "/saveUserSelected", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void saveUserSelected( 	 
			HttpSession session
			,HttpServletRequest request,
			@RequestParam("idusuario") final Integer idusuario
	) throws JsonProcessingException {
		HttpSession misession= (HttpSession) request.getSession();
		misession.setAttribute("idusuario", idusuario);
	}
 



}
