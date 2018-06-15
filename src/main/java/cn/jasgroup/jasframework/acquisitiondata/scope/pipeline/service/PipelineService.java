package cn.jasgroup.jasframework.acquisitiondata.scope.pipeline.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.scope.pipeline.dao.PipelineDao;
import cn.jasgroup.jasframework.engine.hibernate.service.CommonDataHibernateService;

@Service
@Transactional
public class PipelineService extends CommonDataHibernateService{
	
	@Resource
	private PipelineDao pipelineDao;
	
	public List<Map<String,Object>> getPipeLineList(String projectOid){
		return pipelineDao.getPipeLineList(projectOid);
	}
}
