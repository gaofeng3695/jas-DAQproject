package cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import cn.jasgroup.jasframework.acquisitiondata.material.pipe.dao.entity.SteelPipe;
import cn.jasgroup.jasframework.acquisitiondata.material.pipe.service.PipeService;
import cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.dao.entity.ConstructionWeld;
import cn.jasgroup.jasframework.base.data.BaseEntity;
import cn.jasgroup.jasframework.engine.jdbc.service.CommonDataJdbcService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.weld.measuredresult.dao.entity.WeldMeasuredResult;
import cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.dao.WeldDao;
import cn.jasgroup.jasframework.engine.hibernate.service.CommonDataHibernateService;
import cn.jasgroup.jasframework.engine.jdbc.service.CommonDataJdbcService;
import cn.jasgroup.jasframework.unique.UniqueResult;

@Service
@Transactional
public class WeldService extends CommonDataHibernateService{

	@Resource
	private WeldDao weldDao;

	@Autowired
    private PipeService pipeService;

	@Autowired
    private CommonDataJdbcService commonDataJdbcService;
	
	/***
	  * <p>功能描述：获取焊口列表（焊口+返修-割口）。</p>
	  * <p> 雷凯。</p>	
	  * @param pipeSegmentOrCrossOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午2:12:10。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getWeldList(String pipeSegmentOrCrossOid){
		return this.weldDao.getWeldList(pipeSegmentOrCrossOid);
	}
	/***
	  * <p>功能描述：获取焊口列表（焊口-返修-割口）。</p>
	  * <p> 雷凯。</p>	
	  * @param pipeSegmentOrCrossOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午2:12:17。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getOnlyWeldList(String pipeSegmentOrCrossOid){
		return this.weldDao.getOnlyWeldList(pipeSegmentOrCrossOid);
	}
	/***
	 * <p>功能描述：获取焊口列表（焊口+返修前焊口+返修后焊口-割口）。</p>
	  * <p> 雷凯。</p>	
	  * @param pipeSegmentOrCrossOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午2:12:50。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getAllWeldList(String pipeSegmentOrCrossOid,String detectionType){
		return this.weldDao.getAllWeldList(pipeSegmentOrCrossOid,detectionType);
	}
	
	
	
  	/**
  	 * <p>功能描述：唯一性校验提示信息。</p>
  	  * <p> 葛建。</p>	
  	  * @param uniqueResultList
  	  * @return
  	  * @since JDK1.8。
  	  * <p>创建日期:2018年10月18日 上午10:52:20。</p>
  	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
  	 */
	public String formatting(List<UniqueResult> uniqueResultList){
		String msg = "";
		String name = "";
		for(UniqueResult uniqueResult : uniqueResultList){
			if(StringUtils.isNotBlank(uniqueResult.getName())){
				name = uniqueResult.getName();
			}else{
				name = uniqueResult.getFieldName();
			}
			//获取对应的oid,查出对应的code
			String weldCode = "";
			if (StringUtils.isNotBlank(uniqueResult.getValue())) {
				weldCode = weldDao.getWeldCodeByOid(uniqueResult.getValue());
			}
			msg +=name+"重复:"+weldCode+"<br>"; 		
		}	
		return msg;
	}
	
	public List<Map<String,Object>> getDetectionInfoByWeldOid(String weldOid){
		return this.weldDao.getDetectionInfoByWeldOid(weldOid);
	}

	/**
	 * <p>功能描述: 根据前管件或者后管件类型是否有大小头，更新has_reducer字段信息。</p>
	 * <p> cuixianing。</p>
	 * @param constructionWeld
	 * @since JDK1.8
	 * <p>创建日期:2019/2/20 13:48</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]</p>
	 */
	public void updateConstructionWeldFiled(ConstructionWeld constructionWeld){
        String frontPipeType = constructionWeld.getFrontPipeType();
        String backPipeType = constructionWeld.getBackPipeType();

        constructionWeld.setHasReducer(0);
        constructionWeld.setHasBendPipe(0);
        constructionWeld.setHasCutPipe(0);
        //是否变径：前管件或者后管件类型有大小头的
        if("pipe_type_code_006".equals(frontPipeType) || "pipe_type_code_006".equals(backPipeType)){
            constructionWeld.setHasReducer(1);
        }
        //是否有弯管：前管件或者后管件类型为“热煨弯管”和“冷弯管”
        if("pipe_type_code_002".equals(frontPipeType) || "pipe_type_code_008".equals(frontPipeType) || "pipe_type_code_0021".equals(backPipeType) || "pipe_type_code_0081".equals(backPipeType)){
            constructionWeld.setHasBendPipe(1);
        }
        //是否存在切管：前管件或者后管件类型为直管时，直管是否切管
        if("pipe_type_code_001".equals(frontPipeType)){
            String frontPipeOid = constructionWeld.getFrontPipeOid();
            SteelPipe steelPipe = (SteelPipe)commonDataJdbcService.get(SteelPipe.class, frontPipeOid);
            Integer isChild = steelPipe.getIsChild();
            if(1 == isChild){
                constructionWeld.setHasCutPipe(1);
            }
        }
        if("pipe_type_code_0011".equals(backPipeType) && constructionWeld.getHasCutPipe() == 0){
            String backPipeOid = constructionWeld.getBackPipeOid();
            SteelPipe steelPipe = (SteelPipe)commonDataJdbcService.get(SteelPipe.class, backPipeOid);
            Integer isChild = steelPipe.getIsChild();
            if(1 == isChild){
                constructionWeld.setHasCutPipe(1);
            }
        }
    }

    /**
     * <p>功能描述: 根据回填的oid 查询出焊口的oid列表。</p>
     * <p> cuixianing。</p>
     * @param oid
     * @since JDK1.8
     * <p>创建日期:2019/2/20 15:44</p>
     * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]</p>
     */
    public List<Map<String,String>> queryOids(String oid,String tableName){
	    return weldDao.queryOids(oid,tableName);
    }

    /**
     * <p>功能描述: 更新焊口信息表daq_construction_weld的is_backfill的字段状态。</p>
     * <p> cuixianing。</p>
     * @param oids
     * @since JDK1.8
     * <p>创建日期:2019/2/20 16:06</p>
     * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]</p>
     */
    public Integer updateFieldState(List<Map<String,String>> oids,String filedName){
        return weldDao.updateFieldState(oids,filedName);
    }

	/**
	 * <p>功能描述：根据线路段查询审核状态为1和2的焊口列表(焊口表中未返修未割口且未测量的数据，返修表中未割口且未测量的数据)。</p>
	  * <p> 葛建。</p>	
	  * @param pipeSegmentOrCrossOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年2月20日 上午10:18:59。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getWeldByCondition(String pipeSegmentOrCrossOid) {
		return weldDao.getWeldByCondition(pipeSegmentOrCrossOid);
	}
	
	/**
	 * <p>功能描述：根据测量控制点类型修改对应表中的is_measure字段</p>
	  * <p> 葛建。</p>	
	  * @param weldMeasuredResult
	  * @since JDK1.8。
	  * <p>创建日期:2019年2月20日 下午5:00:57。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void changeMeasuredFieldAfterSave(WeldMeasuredResult weldMeasuredResult){
		// 中线测量若测量控制点为焊口，保存后修改焊口/返修表中对应的is_measured字段
		String weldOid = weldMeasuredResult.getWeldOid();
		if (StringUtils.isNotBlank(weldOid)) {
			weldDao.changeIsMeasuredField(weldOid, null, 1);
		}
		// 中线测量若测量控制点为转角点，保存后修改热煨/冷弯表中对应的is_measured字段
		String bendingOid = weldMeasuredResult.getBendingOid();
		if (StringUtils.isNotBlank(bendingOid)) {
			weldDao.changeIsMeasuredField(null, bendingOid, 1);
		}
	}

	/**
	 * <p>功能描述：根据测量控制点类型修改对应表中的is_measure字段。</p>
	  * <p> 葛建。</p>	
	  * @param weldMeasuredResult
	  * @since JDK1.8。
	  * <p>创建日期:2019年2月20日 下午5:21:04。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void changeMeasuredFieldBeforeUpdate(WeldMeasuredResult weldMeasuredResult){
		WeldMeasuredResult oldWeldMeasuredResult = (WeldMeasuredResult)commonDataJdbcService.get(WeldMeasuredResult.class,weldMeasuredResult.getOid());
		// 中线测量若测量控制点为焊口，更新前修改焊口/返修表中对应的is_measured字段
		String weldOid = oldWeldMeasuredResult.getWeldOid();
		if (StringUtils.isNotBlank(weldOid)) {
			weldDao.changeIsMeasuredField(weldOid, null, 0);
		}
		// 中线测量若测量控制点为转角点，更新前修改热煨/冷弯表中对应的is_measured字段
		String bendingOid = oldWeldMeasuredResult.getBendingOid();
		if (StringUtils.isNotBlank(bendingOid)) {
			weldDao.changeIsMeasuredField(null, bendingOid, 0);
		}
	}

	/**
	 * <p>功能描述：根据测量控制点类型修改对应表中的is_measure字段。</p>
	  * <p> 葛建。</p>	
	  * @param weldMeasuredResult
	  * @since JDK1.8。
	  * <p>创建日期:2019年2月20日 下午5:21:04。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void changeMeasuredFieldAfterUpdate(WeldMeasuredResult weldMeasuredResult){
		// 中线测量若测量控制点为焊口，更新后修改焊口/返修表中对应的is_measured字段
		String weldOid = weldMeasuredResult.getWeldOid();
		if (StringUtils.isNotBlank(weldOid)) {
			weldDao.changeIsMeasuredField(weldOid, null, 1);
		}
		// 中线测量若测量控制点为转角点，更新后修改热煨/冷弯表中对应的is_measured字段
		String bendingOid = weldMeasuredResult.getBendingOid();
		if (StringUtils.isNotBlank(bendingOid)) {
			weldDao.changeIsMeasuredField(null, bendingOid, 1);
		}
	}
	
	/**
	 * <p>功能描述：根据测量控制点类型修改对应表中的is_measure字段。</p>
	 * <p> 葛建。</p>	
	 * @param weldMeasuredResult
	 * @since JDK1.8。
	 * <p>创建日期:2019年2月20日 下午5:21:04。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void changeMeasuredFieldAfterDelete(WeldMeasuredResult weldMeasuredResult){
		WeldMeasuredResult newWeldMeasuredResult = (WeldMeasuredResult)commonDataJdbcService.get(WeldMeasuredResult.class,weldMeasuredResult.getOid());
		// 中线测量若测量控制点为焊口，删除后修改焊口/返修表中对应的is_measured字段
		String weldOid = newWeldMeasuredResult.getWeldOid();
		if (StringUtils.isNotBlank(weldOid)) {
			weldDao.changeIsMeasuredField(weldOid, null, 0);
		}
		// 中线测量若测量控制点为转角点，删除后修改热煨/冷弯表中对应的is_measured字段
		String bendingOid = newWeldMeasuredResult.getBendingOid();
		if (StringUtils.isNotBlank(bendingOid)) {
			weldDao.changeIsMeasuredField(null, bendingOid, 0);
		}
	}
}
