package cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.dao.WeldDao;
import cn.jasgroup.jasframework.engine.hibernate.service.CommonDataHibernateService;

@Service
@Transactional
public class WeldService extends CommonDataHibernateService{

	@Resource
	private WeldDao weldDao;
	
	public List<Map<String,Object>> getWeldList(String pipeSegmentOrCrossOid){
		return this.weldDao.getWeldList(pipeSegmentOrCrossOid);
	}
	
	
}
