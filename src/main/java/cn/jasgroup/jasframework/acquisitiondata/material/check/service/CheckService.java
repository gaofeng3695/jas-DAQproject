package cn.jasgroup.jasframework.acquisitiondata.material.check.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.material.check.dao.CheckDao;
import cn.jasgroup.jasframework.engine.hibernate.service.CommonDataHibernateService;

@Service
@Transactional
public class CheckService extends CommonDataHibernateService{
	
	@Autowired
	private CheckDao checkDao;
	
	public List<Map<String,Object>> getManufacturerCode(){
		return checkDao.getManufacturerCode();
	}

}
