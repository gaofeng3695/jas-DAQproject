package cn.jasgroup.jasframework.acquisitiondata.cathodic.cable.service;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jasgroup.jasframework.acquisitiondata.cathodic.cable.dao.CathodicCableProtectionDao;

@Service
@Transactional
public class CathodicCableProtectionService {

	@Autowired
	private CathodicCableProtectionDao cathodicCableProtectionDao;

	public List<Map<String, Object>> queryForSelect(String codeId, String pipeSegmentOid) {
		String lastNumner = codeId.substring(codeId.length()-3);
		//查询测试桩号
		if ("001".equals(lastNumner)) {
			return cathodicCableProtectionDao.queryTestStack(pipeSegmentOid);
		}
		//查询牺牲阳极编号
		if ("002".equals(lastNumner)) {
			return cathodicCableProtectionDao.querySacrificeAnode(pipeSegmentOid);
		}
		//查询阴保电源编号
		if ("003".equals(lastNumner)) {
			return null;
		}
		//查询辅助阳极地床编号
		if ("004".equals(lastNumner)) {
			return cathodicCableProtectionDao.queryAnodeBed(pipeSegmentOid);
		}
		return null;
	}
}
