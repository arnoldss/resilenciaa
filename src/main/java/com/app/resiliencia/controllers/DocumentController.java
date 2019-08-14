package com.app.resiliencia.controllers;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.app.resiliencia.model.User;
import com.app.resiliencia.model.UserDocument;
import com.app.resiliencia.resilienciaDao.UserDao;
import com.app.resiliencia.resilienciaDao.UserDocumentDao;
import com.app.resiliencia.service.MailMail;
import com.fasterxml.jackson.core.JsonProcessingException;
@RestController
@RequestMapping(value={"/document"})
public class DocumentController{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String pathFile="C:\\home\\support\\appImages\\";//C:\\xampp\\htdocs\\docsencuesta\\
    //private final String pathFile="";
    

	@Value("${mccmnc}")
	private Integer mccmnc;
	@Value("${apicore.endpoint}")
	private String apicoreUrl;
	@Autowired
	UserDao userDao;
	@Autowired
	MailMail mail;
	 @Autowired 
	 UserDocumentDao UserDocumentDao;
	
	@RequestMapping(value = "/addDoc", method = RequestMethod.POST)
	public ModelAndView    addDoc(
			@RequestParam("file") MultipartFile file,
			@RequestParam("comments") String  comments,
			@RequestParam("docName") String  docName,
			@RequestParam("iddocumento") Integer  iddocumento,	
			HttpSession session,
			HttpServletRequest request,
			HttpServletResponse httpServletResponse
	) throws IOException {
		logger.info("Bef decode: "+file.getOriginalFilename());
		
		User user =	(User) session.getAttribute("User");
		HttpSession misession= (HttpSession) request.getSession();
		Integer idusuario=0;
		if(user.getRole()==0){	
			idusuario=(Integer) misession.getAttribute("idusuario");
		}else{	
					idusuario=user.getId();
		}
	
		if(file.isEmpty())
			logger.info("File is empty");
		else {
			logger.info("File is not empty File is: ");
	        
	    	 byte[] bytes = file.getBytes();
	            Path path = Paths.get(pathFile + file.getOriginalFilename());
	            Files.write(path, bytes);
	        logger.info("El nombre real es: "+file.getName());
	        
	        
	        
	        UserDocument doc	=	new UserDocument();
	        doc.setId(UserDocumentDao.getLastId());
	        doc.setUserId(idusuario);
	        doc.setFileName(file.getOriginalFilename());
	        doc.setDocName(docName);
	        doc.setComments(comments);
	        doc.setStatus(1);
	        doc.setDocType(file.getContentType());
	        doc.setIddocumento(iddocumento);
	       
	        
	        UserDocumentDao.addDocument(doc,bytes);
	        
	        
		}
		String fullUrl = request.getRequestURL().toString();
	    System.out.println("URllllllllll :---- ---"+request.getRequestURI() + "  Full url "+request.getRequestURL().toString());
	    

	    String[] namesList = request.getRequestURL().toString().split("/");


	    System.out.println(namesList[1] +"  " +namesList[2]);
		
	    
		return new ModelAndView( "redirect");
		
	}
	
	@RequestMapping(value = "/getDocuments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserDocument>  getDocuments(
			HttpSession session
			,HttpServletRequest request
	) throws JsonProcessingException {	
		
		HttpSession misession= (HttpSession) request.getSession();
		User user =  (User) misession.getAttribute("usuario_en_session");
		List<UserDocument> UserDocument  = null;
	try {
		Integer idusuario=0;
		
		if(user.getRole()==0){	
			idusuario=(Integer) misession.getAttribute("idusuario");
		}else{	
			idusuario=user.getId();
		}
		
		UserDocument = UserDocumentDao.getUserDocs(idusuario);		
		
		
		
	}catch(Exception e) {
		System.out.println(e);
		e.printStackTrace();
		
	}
       
		
	return UserDocument;
	}
	@RequestMapping(value = "/remove", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer removeCatalog(
 			@RequestParam("id") final Integer id,
 			@RequestParam("fileName") final String fileName,
			HttpSession session,
			HttpServletRequest request
	) throws JsonProcessingException {
		//logger.info("removeFile -- : "+" id: "+id);
	try {
		UserDocumentDao.deleteDoc(id,pathFile,fileName);

	}catch(Exception e) {
		e.printStackTrace();
	}
       
		
	return null;
	}
    @RequestMapping(value="attachedImageInfo",method = RequestMethod.GET)
    public Integer attachedImageInfo(@RequestParam("docName") final String docName,
    		@RequestParam("comments") final String comments, HttpSession session, 
    		@RequestParam("docType") final String docTypeId,
    		@RequestParam("iddocumento") final Integer iddocumento,
    		HttpServletRequest req,Model model) throws InterruptedException {
        logger.info("attachedImageInfo");
        User user =	null;
        while(true) {
        	user =  (User) session.getAttribute("User");
        	if(user.getFileName()!=null)
        		break;
        }
        UserDocument doc	=	new UserDocument();
        doc.setId(UserDocumentDao.getLastId());
        doc.setUserId(user.getId());
        doc.setComments(comments);
        doc.setDocName(docName);
        doc.setFileName(user.getFileName());
        doc.setStatus(1);
        doc.setDocType(docTypeId);
        doc.setIddocumento(iddocumento);
        
        //UserDocumentDao.addDocument(doc);
        logger.info("Saving file... ");


      
       return 200;
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
			@RequestParam("mail") final String mailto

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
		User.setPassword(uuid.toString().substring(0,8));
		User.setRole(1);
		User.setStatus(0);
		userDao.addUser(User);
	return 200;
	}
 
 

 


 

	@RequestMapping(value = "/confirm_pin", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String confirm_pin(
			@RequestParam("id_attempt") final String id_attempt,
			@RequestParam("id_carrier") final String id_carrier,
			@RequestParam("id_service_type") final String id_service_type,
			@RequestParam("id_promo") final String id_promo,
			@RequestParam("id_channel") final String id_channel,
			@RequestParam("pin") final String pin,
			@RequestParam("msisdn") final String msisdn,
			@RequestParam("id_service") final Integer id_service
	) {


		return null;
	}

	
	
	
	
	@RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
	public void downloadFile(
			HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam("idarchivo") Integer  idarchivo			
	) throws IOException  {
		
		
		  UserDocument userDocument=UserDocumentDao.getDocById(idarchivo); 
	      response.setContentType(userDocument.getDocType());
	      response.setHeader("Content-Disposition", "attachment;filename=" + userDocument.getFileName());
	      	    
	      BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());
	      	   
	      outStream.write(userDocument.getBytedoc());
	      outStream.flush();
	      
	      
	}
	
	
	
	 @RequestMapping("/redirect")
	    public String getAvailableBikes(Model model){
	       

	        return "redirect";
	    }
	
	
	
}
