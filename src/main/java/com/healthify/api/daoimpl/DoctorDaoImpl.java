package com.healthify.api.daoimpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.healthify.api.dao.DoctorDao;
import com.healthify.api.entity.DoctorsTimeOff;
import com.healthify.api.service.ReceptionistService;


@Repository
public class DoctorDaoImpl implements DoctorDao {

	private static Logger log = LogManager.getLogger(DoctorDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ReceptionistService receptionistService;
	
	
	@Override
	public int setTimeOff(DoctorsTimeOff doctorsTimeOff) 
	{
		DoctorsTimeOff doctorsTimeOffDB = receptionistService.checkDoctorTimeOff(doctorsTimeOff.getDoctorUserame(), doctorsTimeOff.getTimeOffDate());
		System.out.println(doctorsTimeOffDB);
		try
		{
			Session session= sessionFactory.getCurrentSession();
			
			if (doctorsTimeOffDB== null)
			{
				session.save(doctorsTimeOff);
				return 1;
			}
			else
			{
				session.delete(doctorsTimeOffDB); 
				session.save(doctorsTimeOff);
				return 2;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			log.error("{}", e.getMessage());
			return 3;
		}
	}
	
	
	@Override
	public List<DoctorsTimeOff> getDoctorTimeOff(String doctorUsername, Date date) 
	{

		 // Use Hibernate session to query for doctor's time-off schedules by username and date
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(DoctorsTimeOff.class);
        criteria.add(Restrictions.eq("doctorUserame", doctorUsername));
        criteria.add(Restrictions.eq("timeOffDate", date));

        return criteria.list();

	}
	 

	@Override
	public List<DoctorsTimeOff> getDoctorTimeOff(String doctorUsername)
	{
		try 
		{
            Session session = sessionFactory.getCurrentSession();
            
            Criteria criteria = session.createCriteria(DoctorsTimeOff.class);
            
            criteria.add(Restrictions.eq("doctorUserame", doctorUsername));
            
            return criteria.list();
        }
		catch (Exception e)
		{
            log.error("Error fetching time-off schedules for doctor: " + e.getMessage());
            return new ArrayList<>(); // Return an empty list or handle the error accordingly
        }
    }
	

}
