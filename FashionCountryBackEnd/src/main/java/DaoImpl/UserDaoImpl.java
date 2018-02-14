package DaoImpl;

import javax.transaction.Transactional;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.Dao.UserDao;
import com.model.User;

public class UserDaoImpl implements UserDao
{
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	public UserDaoImpl(SessionFactory sessionFactory)
	{
		super();
		this.sessionFactory = sessionFactory;
		
	}
    @Transactional
	public void insertUser(User user)
    {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(user);
		session.getTransaction().commit();
	}
	public User findUserByEmail(String email)
	{
		Session session = sessionFactory.openSession();
		User u = null;
		try
		{
		session.beginTransaction();
		u = session.get(User.class, email);
		session.getTransaction().commit();
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
		}
		return u;
	}
		
}
