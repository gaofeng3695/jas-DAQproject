package cn.jasgroup.jasframework.acquisitiondata.weld.reworkweld.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.weld.reworkweld.dao.ReworkWeldDao;
import cn.jasgroup.jasframework.acquisitiondata.weld.reworkweld.dao.entity.ReworkWeld;
import cn.jasgroup.jasframework.engine.jdbc.service.CommonDataJdbcService;

@Service
@Transactional
public class ReworkWeldService extends CommonDataJdbcService{
	
	@Resource
	private ReworkWeldDao reworkWeldDao;
	
	public void changeGeomColumn(ReworkWeld reworkWeld){
		reworkWeldDao.changeGeomColumn(reworkWeld.getOid(),reworkWeld.getWeldOid());
	}
}
