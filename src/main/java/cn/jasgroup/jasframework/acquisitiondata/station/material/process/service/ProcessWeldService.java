package cn.jasgroup.jasframework.acquisitiondata.station.material.process.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jasgroup.jasframework.acquisitiondata.station.material.process.dao.ProcessWeldDao;

@Service
@Transactional
public class ProcessWeldService {

	@Autowired
	private ProcessWeldDao processWeldDao;

	/**
	 * <p>功能描述：通过站场阀室编号查询工艺管道焊口。</p>
	  * <p> 葛建。</p>	
	  * @param pipeStationOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月3日 上午8:59:20。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getProcessWeldList(String pipeStationOid) {
		return processWeldDao.getProcessWeldList(pipeStationOid);
	}
}
