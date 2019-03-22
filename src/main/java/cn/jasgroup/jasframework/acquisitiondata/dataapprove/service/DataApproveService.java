package cn.jasgroup.jasframework.acquisitiondata.dataapprove.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.service.WeldService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.dataapprove.dao.DataApproveDao;
import cn.jasgroup.jasframework.acquisitiondata.dataapprove.dao.entity.DataApprove;
import cn.jasgroup.jasframework.acquisitiondata.variate.UnitHierarchyEnum;
import cn.jasgroup.jasframework.engine.hibernate.service.CommonDataHibernateService;
import cn.jasgroup.jasframework.security.dao.entity.PriUnit;
import cn.jasgroup.jasframework.security.service.UnitService;
import cn.jasgroup.jasframework.support.ModelFacade;
import cn.jasgroup.jasframework.support.ThreadLocalHolder;

@Service
@Transactional
public class DataApproveService extends CommonDataHibernateService{
	
	@Resource
	private DataApproveDao dataApproveDao;
	
	@Resource
	private UnitService unitService;

	@Autowired
    private WeldService weldService;
	
	/***
	 * <p>功能描述：保存业务流程审批记录。</p>
	  * <p> 雷凯。</p>	
	  * @param businessOids
	  * @param className
	  * @param functionCode
	  * @param approveOpinion
	  * @param approveStatus
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月23日 下午1:49:54。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void saveData(List<String> businessOids,String className,String functionCode,String approveOpinion,String approveStatus,String privilegeCode){
		for(String businessOid : businessOids){
			DataApprove approve = new DataApprove();
			approve.setBusinessOid(businessOid);
			approve.setClassName(className);
			approve.setFunctionCode(functionCode);
			approve.setApproveOpinion(approveOpinion);
			approve.setApproveStatus(Integer.parseInt(approveStatus));
			approve.setPrivilegeCode(privilegeCode);
			this.save(approve);
		}
	}
	
	/***
	  * <p>功能描述：更改业务数据的审批状态。</p>
	  * <p> 雷凯。</p>	
	  * @param dataApprove
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月21日 下午5:23:01。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void changeBusinessApproveStatus(DataApprove dataApprove){
		String businessOid = dataApprove.getBusinessOid();
		Integer approveStatus = dataApprove.getApproveStatus();
		String functionCode = dataApprove.getFunctionCode();
		String privilegeCode = dataApprove.getPrivilegeCode();
		if(StringUtils.isNotBlank(dataApprove.getFunctionCode())){
			dataApproveDao.changeBusinessApproveStatus(null,functionCode, businessOid, approveStatus);
			dataApproveDao.saveOrUpdateApproveTipInfo(null,functionCode, businessOid, approveStatus,privilegeCode);
		}else if(StringUtils.isNotBlank(dataApprove.getClassName())){
			try {
				String tableName = ModelFacade.getTableName(dataApprove.getClassName());
				if(tableName.equalsIgnoreCase("daq_material_pipe_cold_bending") && approveStatus==2){
					dataApproveDao.chanageOriginalPipeUseState(tableName,businessOid);
				}
				//根据回填oid修改焊口信息表daq_construction_weld的is_backfill的字段状态
                if(tableName.equalsIgnoreCase("daq_lay_pipe_trench_backfill") && approveStatus==2){
                    //根据回填的oid 查询出焊口的oid列表
                    List<Map<String,String>> oids = weldService.queryOids(businessOid,"daq_lay_pipe_trench_backfill");
                    //更新焊口信息表daq_construction_weld的is_backfill的字段状态
                    if(oids != null && oids.size() != 0){
                        weldService.updateFieldState(oids,"is_backfill");
                    }
                }
                //根据地貌恢复oid修改焊口信息表daq_construction_weld的is_land_restoration的字段状态
                if(tableName.equalsIgnoreCase("daq_lay_land_restoration") && approveStatus==2){
                    List<Map<String,String>> oids = weldService.queryOids(businessOid,"daq_lay_land_restoration");
                    if(oids != null && oids.size() != 0) {
                        weldService.updateFieldState(oids, "is_land_restoration");
                    }
                }
				dataApproveDao.changeBusinessApproveStatus(tableName, null, businessOid, approveStatus);
				dataApproveDao.saveOrUpdateApproveTipInfo(tableName,null, businessOid, approveStatus,privilegeCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/***
	  * <p>功能描述：获取权限树对应的为审核的数据。</p>
	  * <p> 雷凯。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年2月15日 下午5:22:48。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> queryUnauditedInfo(){
		String unitOid = ThreadLocalHolder.getCurrentUser().getUnitId();
		PriUnit unitEntity = (PriUnit)unitService.get(PriUnit.class,unitOid);
		List<Map<String,Object>> resultData = new ArrayList<>();
		if(unitEntity==null){
			return resultData;
		}
		String hierarchy = unitEntity.getHierarchy();
		if(hierarchy.startsWith(UnitHierarchyEnum.supervision_unit.getHierarchy())){//监理单位
			resultData = dataApproveDao.queryUnauditedInfo(unitOid,1);
		}else if(hierarchy.startsWith(UnitHierarchyEnum.project_unit.getHierarchy())){//建设单位
			resultData = dataApproveDao.queryUnauditedInfo(unitOid,0);
		}
		return resultData;
	}
	
}
