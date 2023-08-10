package com.becoder.dao;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.becoder.entity.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public int saveUser(User user) {
		// TODO Auto-generated method stub
		int i=(Integer) hibernateTemplate.save(user);
		return i;
	}

	@Override
	public User loginUser(String email, String password) {
		// TODO Auto-generated method stub
		
		String sql="from User Where email=:em and password=:pwd";
		
		User us=(User) hibernateTemplate.execute(s->{
			Query q=s.createQuery(sql);
			q.setString("em", email);
			q.setString("pwd",password);
			return q.uniqueResult();
		});
		
		return us;
	}
	
	

}
