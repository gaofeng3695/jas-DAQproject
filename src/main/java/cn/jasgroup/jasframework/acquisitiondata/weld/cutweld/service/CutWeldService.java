package cn.jasgroup.jasframework.acquisitiondata.weld.cutweld.service;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jasgroup.jasframework.acquisitiondata.material.pipe.dao.entity.SteelPipe;
import cn.jasgroup.jasframework.acquisitiondata.weld.cutweld.dao.entity.CutWeld;
import cn.jasgroup.jasframework.engine.jdbc.service.CommonDataJdbcService;
import cn.jasgroup.jasframework.support.ThreadLocalHolder;

@Service
@Transactional
public class CutWeldService {

	@Autowired
	private CommonDataJdbcService commonDataJdbcService;
	
	/**
	 * <p>功能描述：钢管切管之后保存段钢管信息。</p>
	  * <p> 葛建。</p>	
	  * @param cutWeld
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月10日 上午9:00:49。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void savePipeAfterCut(CutWeld cutWeld){
		//获取原钢管对象
		SteelPipe pipe = (SteelPipe)commonDataJdbcService.get(SteelPipe.class, cutWeld.getPipeOid());
		for (int i = 0; i < cutWeld.getSegmentsNum(); i++) {
			//用于封装被切断的段钢管对象
			SteelPipe cutedPipe = new SteelPipe();
			//将原钢管数据赋值给段钢管
			try {
				BeanUtils.copyProperties(cutedPipe, pipe);
			} catch (Exception e) {
				e.printStackTrace();
			}
			cutedPipe.setOid(UUID.randomUUID().toString());
			cutedPipe.setPipeCode(pipe.getPipeCode() + i);
			setLengthAndWeight(cutedPipe,cutWeld,i,pipe);
			cutedPipe.setIsCut(0);
			cutedPipe.setIsUse(0);
			cutedPipe.setIsHotBend(0);
			//设置创建和修改信息
			cutedPipe.setCreateDatetime(new Date());
			cutedPipe.setCreateUserId(ThreadLocalHolder.getCurrentUserId());
			cutedPipe.setCreateUserName(ThreadLocalHolder.getCurrentUserName());
			cutedPipe.setModifyDatetime(new Date());
			cutedPipe.setModifyUserId(ThreadLocalHolder.getCurrentUserId());
			cutedPipe.setModifyUserName(ThreadLocalHolder.getCurrentUserName());
			//保存段钢管信息
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
