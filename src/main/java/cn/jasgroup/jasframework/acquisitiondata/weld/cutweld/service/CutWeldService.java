package cn.jasgroup.jasframework.acquisitiondata.weld.cutweld.service;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.jasgroup.jasframework.acquisitiondata.material.pipe.dao.entity.SteelPipe;
import cn.jasgroup.jasframework.acquisitiondata.weld.cutweld.dao.entity.CutWeld;
import cn.jasgroup.jasframework.engine.jdbc.service.CommonDataJdbcService;

public class CutWeldService {

	@Autowired
	private CommonDataJdbcService commonDataJdbcService;
	
	@Autowired
	public void savePipeAfterCut(CutWeld cutWeld){
		//用于封装被切断的段钢管对象
		SteelPipe cutedPipe = new SteelPipe();
		//获取原钢管对象
		SteelPipe pipe = (SteelPipe)commonDataJdbcService.get(SteelPipe.class, cutWeld.getPipeOid());
		//将原钢管数据赋值给段钢管
		try {
			BeanUtils.copyProperties(cutedPipe, pipe);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < cutWeld.getSegmentsNum(); i++) {
			cutedPipe.setPipeCode(pipe.getPipeCode() + i);
			setLengthAndWeight(cutedPipe,cutWeld,i,pipe);
			cutedPipe.setIsCut(0);
			cutedPipe.setIsUse(0);
			cutedPipe.setIsHotBend(0);
			commonDataJdbcService.save(cutedPipe);
		}
		
	}

	//设置切管的长度和重量
	private void setLengthAndWeight(SteelPipe cutedPipe, CutWeld cutWeld, int i, SteelPipe pipe) {
		if (i == 0) {
			cutedPipe.setPipeLength(cutWeld.getFirstParagraphLength());
		}else if (i == 1) {
			cutedPipe.setPipeLength(cutWeld.getSecondParagraphLength());
		}else if (i == 2) {
			cutedPipe.setPipeLength(cutWeld.getThirdParagraphLength());
		}else if (i == 3) {
			cutedPipe.setPipeLength(cutWeld.getFourthParagraphLength());
		}else if (i == 4) {
			cutedPipe.setPipeLength(cutWeld.getFifthParagraphLength());
		}
		cutedPipe.setPipeWeight(pipe.getPipeWeight()*(cutedPipe.getPipeLength()/pipe.getPipeLength()));
	}

}
