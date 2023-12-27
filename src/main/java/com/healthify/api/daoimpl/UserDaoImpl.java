package com.healthify.api.daoimpl;

import java.sql.Date;
import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.healthify.api.dao.UserDao;
import com.healthify.api.entity.Otp;
import com.healthify.api.entity.Role;
import com.healthify.api.entity.User;
import com.healthify.api.model.ResetPasswordDetail;
import com.healthify.api.security.CustomUserDetail;

@Repository
public class UserDaoImpl implements UserDao {
	private static Logger LOG = LogManager.getLogger(UserDaoImpl.class);

	@Autowired
	private SessionFactory sf;

	@Autowired
	public PasswordEncoder passwordEncoder;

	@Override
	public boolean addUser(User user) {
		boolean status = false;
		Session session = sf.getCurrentSession();
		try {

			User dbUser = session.get(User.class, user.getUsername());
			if (dbUser == null) {
				System.out.println(111111);
				session.save(user);
				// session.beginTransaction().commit();
				status = true;
			}

		} catch (PersistenceException e) {
			status = false;

		}
		return status;

	}

	@Override
	public User loginUser(User user) {
		Session session = sf.getCurrentSession();
		User usr = null;
		try {
			usr = session.get(User.class, user.getUsername());
			boolean matches = passwordEncoder.matches(user.getPassword(), usr.getPassword());
			if (matches) {
				return usr;
			} else {
				usr = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usr;

	}

	@Override
	public CustomUserDetail loadUserByUserId(String userId) {
		Session session = sf.getCurrentSession();
		CustomUserDetail user = new CustomUserDetail();
		User usr = null;
		try {
			usr = session.get(User.class, userId);
			if (usr != null) {
				user.setUserid(usr.getUsername());
				user.setPassword(usr.getPassword());
				user.setRoles(usr.getRoles());
			}
			System.out.println("load user ..." + user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean deleteUserById(String id) {
		Session session = sf.getCurrentSession();
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User getUserById(String id) {
		Session session = sf.getCurrentSession();
		User user = null;
		try {
			user = session.get(User.class, id);
		} catch (Exception e) {
			LOG.info(e.getMessage());

		}
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User updateUser(User user) {
		Session session = sf.getCurrentSession();
		try {
			User dbUser = session.get(User.class, user.getUsername());
			if (dbUser != null) {
				session.update(user);
				return user;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Long getUsersTotalCounts() {
		Session session = sf.getCurrentSession();
		Long count = 0L;
		try {

			Criteria criteria = session.createCriteria(User.class);
			criteria.setProjection(Projections.rowCount());
			count = (Long) criteria.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public Long getUsersTotalCounts(String type) {
		Session session = sf.getCurrentSession();
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Long getUserCountByDateAndType(Date registeredDate, String type) {
		Session session = sf.getCurrentSession();
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> getUserByFirstName(String firstName) {
		Session session = sf.getCurrentSession();
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean saveOtp(Otp otp) {
		Session session = sf.getCurrentSession();
		try {
			session.save(otp);
			return true;

		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	public User getUserByEmail(String email) {
		Session session = sf.getCurrentSession();
		try {

			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("emailid", email));
			Object uniqueResult = criteria.uniqueResult();
			if (uniqueResult != null) {
				return (User) uniqueResult;
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Otp getOtpByUser(String userId) {
		Session session = sf.getCurrentSession();
		try {
			Otp otp = session.get(Otp.class, userId);
			if (otp != null) {
				return otp;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	@Override
	public Role addRole(Role role) {
		Session session = sf.getCurrentSession();
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Role getRoleById(int roleId) {
		Session session = sf.getCurrentSession();
		Role role = null;
		try {
			role = session.get(Role.class, roleId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}

}
