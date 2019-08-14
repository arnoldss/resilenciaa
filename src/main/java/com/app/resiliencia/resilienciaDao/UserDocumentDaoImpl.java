package com.app.resiliencia.resilienciaDao;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.app.resiliencia.model.UserDocument;
@Transactional
@Repository
public class UserDocumentDaoImpl implements UserDocumentDao {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    JdbcTemplate jdbcTemplate;
	@Override
	public List<UserDocument> getUserDocs(Integer Userid) {
		// TODO Auto-generated method stub
	    List<UserDocument> docs =  jdbcTemplate.query("select doc.id,doc.fileName,'http://localhost:8012/docsencuesta/' || doc.fileName  as docName,docc.name as docType from RS_USER_DOC as doc inner join  rs_document_catalog as  docc on doc.iddocumento=docc.id where doc.userid = ? ",
                new Object[] { Userid }, new BeanPropertyRowMapper<UserDocument>(UserDocument.class));

        return docs;
	}

	@Override
	public UserDocument getDocById(Integer id) {
		// TODO Auto-generated method stub
		UserDocument User = (UserDocument) jdbcTemplate.queryForObject("SELECT * FROM RS_USER_DOC where id = ? ",
				new Object[] { id },  new BeanPropertyRowMapper<UserDocument>(UserDocument.class));
		return User;	
	}

	@Override
	public void addDocument(UserDocument doc,byte[] bytes) {
		// TODO Auto-generated method stub
		final String sql = "INSERT INTO RS_USER_DOC (id,userId, createdAt, fileName,docName, "
        		+ "comments, status, docType,iddocumento,bytedoc"
        		+ ")  VALUES ( ?,?,?, ?, ?, ?, ?, ?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
    	jdbcTemplate.update(
    	    new PreparedStatementCreator() {
    	        public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
    	            PreparedStatement pst =
    	                con.prepareStatement(sql);
    	            pst.setInt(1, doc.getId());
     	            pst.setInt(2, doc.getUserId());
     	            pst.setTimestamp(3, new java.sql.Timestamp(new Date().getTime()));
     	            pst.setString(4, doc.getFileName()==null?"":doc.getFileName());
     	            pst.setString(5, doc.getDocName()==null?"":doc.getDocName());
     	            pst.setString(6, doc.getComments()==null?"":doc.getComments());
     	            pst.setInt(7, doc.getStatus()==null?0:doc.getStatus());
     	            pst.setString(8, doc.getDocType()==null?"":doc.getDocType());
     	            pst.setInt(9, doc.getIddocumento());
     	            
					pst.setBytes(10, bytes);

     	            return pst;
    	        }
    	    },
    	    keyHolder);
	}

	@Override
	public void updateDocument(UserDocument doc) {
		// TODO Auto-generated method stub
		final String sql = "UPDATE RS_USER_DOC SET fileName = ?, docName = ?, comments = ?, status = ?  WHERE id = ? ";
		jdbcTemplate.update(sql,
				doc.getFileName()==null?"":doc.getFileName(),
						doc.getDocName()==null?"":doc.getDocName(),
								doc.getComments()==null?"":doc.getComments(),
						doc.getStatus()
						,doc.getId());
	}

	@Override
	public void deleteDoc(Integer id,String pathFile,String fileName) {
		// TODO Auto-generated method stub
		
		File file = new File(pathFile+fileName); 
		file.delete();
		final String sql = "delete from rs_user_doc where id = ? ";
		jdbcTemplate.update(sql,id);
	}

	@Override
	public Integer getLastId() {
		// TODO Auto-generated method stub
				Integer id = (Integer) jdbcTemplate.queryForObject("SELECT (COALESCE(MAX(id) + 1, 1)) FROM  RS_USER_DOC",
						new Object[] { },   Integer.class );
				return id;
	}

}
