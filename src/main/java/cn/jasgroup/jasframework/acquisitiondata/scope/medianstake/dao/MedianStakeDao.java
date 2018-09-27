package cn.jasgroup.jasframework.acquisitiondata.scope.medianstake.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.acquisitiondata.scope.medianstake.dao.entity.MedianStake;
import cn.jasgroup.jasframework.acquisitiondata.scope.medianstake.service.bo.MedianStakeQueryBo;
import cn.jasgroup.jasframework.dataaccess.base.BaseEntityDao;
import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;

@Repository
public class MedianStakeDao extends BaseJdbcDao{
	
	@Resource
	private BaseJdbcDao baseJdbcDao;
	
	/**
	 * <p>功能描述：获取中线桩列表，用于下拉选选。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOid
	  * @param pipelineOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月14日 上午11:25:22。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getList(String projectOid,String pipelineOid){
		String sql = "select t.oid as key,t.median_stake_code as value from daq_median_stake t where t.active=1";
		if(StringUtils.isNotBlank(projectOid)){
			sql += " and t.project_oid='"+projectOid+"'";
		}
		if(StringUtils.isNotBlank(pipelineOid)){
			sql +=" and t.pipeline_oid='"+pipelineOid+"'";
		}
		sql += " order by t.mileage,t.median_stake_code";
		return this.baseJdbcDao.queryForList(sql, null);
	}
	
	public MedianStakeQueryBo get(String oid){
		String sql = "select t.*,p.project_name,l.pipeline_name,s.code_name as mark_stone_type_name "
				+ "from daq_median_stake t "
				+ "left join daq_project p on p.oid=t.project_oid "
				+ "left join daq_pipeline l on l.oid=t.pipeline_oid "
				+ "left join sys_domain s on s.code_id=t.mark_stone_type "
				+ "where t.active=1 and t.oid = ?";
		return (MedianStakeQueryBo) this.baseJdbcDao.queryForObject(sql, new Object[]{oid}, MedianStakeQueryBo.class);
	}
}
