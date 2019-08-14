package com.app.resiliencia.resilienciaDao;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.app.resiliencia.model.User;
import org.springframework.jdbc.core.PreparedStatementCreator;


@Transactional
@Repository
public class UserDaoImpl implements UserDao {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    JdbcTemplate jdbcTemplate;
	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
	    List<User> Users =  jdbcTemplate.query("select * from RS_USER where id >1 order by createdAt desc ",
                new Object[] {  }, new BeanPropertyRowMapper<User>(User.class));

        return Users;

	}
	
	@Override
	public List<User> getUsersEncuesta() {
		// TODO Auto-generated method stub
	    List<User> Users =  jdbcTemplate.query("select * from RS_USER where id in (select DISTINCT idusuario from rs_response_enc) order by createdAt desc ",
                new Object[] {  }, new BeanPropertyRowMapper<User>(User.class));

        return Users;

	}

	@Override
	public User getUserById(Integer id) {
		User User = (User) jdbcTemplate.queryForObject("SELECT * FROM rs_user where id = ? ",
				new Object[] { id },  new BeanPropertyRowMapper<User>(User.class));
		return User;		
	}
	@Override
	public User getUserByEmail(String email) {
		User User = null;
		try {
			 User = (User) jdbcTemplate.queryForObject("SELECT * FROM rs_user where email = ? ",
						new Object[] { email.trim() },  new BeanPropertyRowMapper<User>(User.class));
		}catch(Exception e) {
			
		}
		 
		return User;		
	}
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		final String sql = "INSERT INTO rs_user (id, createdAt, name,email, "
        		+ "password,comments, telefono, role, status"
        		+ ")  VALUES ( ?,?,?, ?, ?, ?, ?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
    	jdbcTemplate.update(
    	    new PreparedStatementCreator() {
    	        public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
    	            PreparedStatement pst =
    	                con.prepareStatement(sql, new String[] {"id"});
     	            pst.setInt(1, user.getId());

     	            pst.setTimestamp(2, new java.sql.Timestamp(new Date().getTime()));
     	            pst.setString(3, user.getName()==null?"":user.getName());
     	            pst.setString(4, user.getEmail()==null?"":user.getEmail());

     	            pst.setString(5, user.getPassword()==null?"":user.getPassword());
     	            pst.setString(6, user.getComments()==null?"":user.getComments());

     	            pst.setString(7, user.getTelefono()==null?"":user.getTelefono());

     	            pst.setInt(8, user.getRole()==null?0:user.getRole());
     	            pst.setInt(9, user.getStatus());
     	            return pst;
    	        }
    	    },
    	    keyHolder);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		final String sql = "UPDATE rs_user SET name = ?, password = ?, role = ?, status = ?  WHERE id = ? ";
		jdbcTemplate.update(sql,
				user.getName()==null?"":user.getName(),
						user.getPassword()==null?"":user.getPassword(),
						user.getRole()==null?"":user.getRole(),
						user.getStatus()
						,user.getId());
	}

	@Override
	public void deleteUser(Integer id) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.app.resiliencia.resilienciaDao.UserDao#userAllowed(java.lang.String)
	 * 0 not allowed
	 * 1 allowed
	 */
	@Override
	public User userAllowed(String name, String pwd) {
		Boolean allowed	=	false;
		User User = null;
		// TODO Auto-generated method stub
		try {
			 User = (User) jdbcTemplate.queryForObject("SELECT * FROM  rs_user where email = ? and password = ?",
					new Object[] { name,pwd },  new BeanPropertyRowMapper<User>(User.class));
//			 if(User!=null)
//					if(User.getStatus().equals(1))
//						allowed = true;
		}catch(Exception e) {
			logger.info("Exception throwed: "+e.getMessage());
		}
	
		
		
		return User;
	}

	@Override
	public Integer getLastId() {
		// TODO Auto-generated method stub
		Integer id = (Integer) jdbcTemplate.queryForObject("SELECT (max(id)+1) FROM  rs_user ",
				new Object[] { },   Integer.class );
		return id;
	}

	@Override
	public List<User> getUsersRoleUser() {
		// TODO Auto-generated method stub
	    List<User> Users =  jdbcTemplate.query("select * from RS_USER where id >1 and role = 1 order by createdAt desc ",
                new Object[] {  }, new BeanPropertyRowMapper<User>(User.class));

        return Users;
	}

}
