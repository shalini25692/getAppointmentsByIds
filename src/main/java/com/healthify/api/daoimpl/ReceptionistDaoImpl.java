package com.healthify.api.daoimpl;

import java.sql.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.healthify.api.dao.ReceptionistDao;
import com.healthify.api.entity.DoctorsTimeOff;

@Repository
public class ReceptionistDaoImpl implements ReceptionistDao {
	@Autowired
	private SessionFactory sessionFactory;

	private static Logger log = LogManager.getLogger(ReceptionistDaoImpl.class);

	@Override
	public DoctorsTimeOff checkDoctorTimeOff(String doctorName, Date date)
	{
		Session session = sessionFactory.getCurrentSession();
		try
		{
	        Criteria criteria = session.createCriteria(DoctorsTimeOff.class);
	        criteria.add(Restrictions.eq("doctorUserame", doctorName)); 
	        criteria.add(Restrictions.eq("timeOffDate", date));

	       
	        return (DoctorsTimeOff) criteria.uniqueResult();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

}
