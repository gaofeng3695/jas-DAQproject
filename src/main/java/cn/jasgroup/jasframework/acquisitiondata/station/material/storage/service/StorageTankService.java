package cn.jasgroup.jasframework.acquisitiondata.station.material.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jasgroup.jasframework.acquisitiondata.station.material.storage.dao.StorageTankDao;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class StorageTankService {

	@Autowired
	private StorageTankDao storageTankDao;

	/**
	 * <p>功能描述:查询项目下的储罐列表</p>
	 * <p> cuixianing</p>
	 * @param projectOid
	 * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 * @since JDK1.8
	 * <p>创建日期:2019/1/4 14:18</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]</p>
	 */
	public List<Map<String, Object>> getStorageTankList(String projectOid) {
		return storageTankDao.getStorageTankList(projectOid);
	}
}
