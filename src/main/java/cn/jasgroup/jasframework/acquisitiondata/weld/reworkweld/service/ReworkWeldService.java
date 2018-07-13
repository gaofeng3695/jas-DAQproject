package cn.jasgroup.jasframework.acquisitiondata.weld.reworkweld.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jasgroup.jasframework.acquisitiondata.weld.reworkweld.dao.entity.ReworkWeldDao;

@Service
@Transactional
public class ReworkWeldService {

	@Autowired
	private ReworkWeldDao reworkWeldDao;
	
	public List<Map<String, Object>> getPersonByWorkUnit(String workUnitOid) {
		return reworkWeldDao.getPersonByWorkUnit(workUnitOid);
	}

}
