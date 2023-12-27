package com.healthify.api.serviceimpl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthify.api.dao.DoctorDao;
import com.healthify.api.entity.DoctorsTimeOff;
import com.healthify.api.service.DoctorService;
/**
 * @author RAM
 *
 */
@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private DoctorDao dao;

	@Override
	public int setTimeOff(DoctorsTimeOff doctorsTimeOff) 
	{
		
		String timeOffId=new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());

		doctorsTimeOff.setTimeoffId(Long.parseLong(timeOffId));
		
		if(doctorsTimeOff.getUnavailableTimeSlots()!=null)
		{
			doctorsTimeOff.getUnavailableTimeSlots().forEach(slots -> slots.setDoctorTimeOff(doctorsTimeOff));
			doctorsTimeOff.setDayOff(false);
		}
		else
		{
			doctorsTimeOff.setDayOff(true);
		}
		
		return dao.setTimeOff(doctorsTimeOff);

	}
	
	@Override
	public List<DoctorsTimeOff> getDoctorTimeOff(String doctorUsername, Date date)
	{
		return dao.getDoctorTimeOff(doctorUsername, date);
	}

	@Override
	public List<DoctorsTimeOff> getDoctorTimeOff(String doctorUsername) 
	{
		
		return dao.getDoctorTimeOff(doctorUsername);
	}

	

	

}
