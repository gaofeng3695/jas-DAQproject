package cn.jasgroup.jasframework.acquisitiondata.basedata.weldproduct.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jasgroup.jasframework.acquisitiondata.basedata.weldproduct.dao.WeldProductDao;

@Service
@Transactional
public class WeldProductService {

	@Autowired
	private WeldProductDao weldProductDao;

	public List<Map<String, Object>> getListByCondition(String projectOid) {
		return weldProductDao.getListByProjectOid(projectOid);
	}

}
