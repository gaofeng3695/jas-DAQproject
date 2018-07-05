package cn.jasgroup.jasframework.acquisitiondata.material.pipe.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jasgroup.jasframework.acquisitiondata.material.pipe.dao.PipeDao;

@Service
@Transactional
public class PipeService {

	@Autowired
	private PipeDao pipeDao;

	public List<Map<String, Object>> getPipeList() {
		return pipeDao.getPipeList();
	}
	
	
}
