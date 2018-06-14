package cn.jasgroup.jasframework.acquisitiondata.scope.medianstake.query;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.scope.medianstake.service.bo.MedianStakeQueryBo;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

@QueryConfig(
	scene="/medianStake/getPage",
	resultClass=MedianStakeQueryBo.class
)
public class MedianStakeQueryPage extends BaseJavaQuery{
	
	private String projectOid;
	private String pipelineOid;
	private String medianStakeCode;
	
	@Override
	public String getSql() {
		String sql = "select t.*,p.project_name,l.pipeline_name,s.code_name as mark_stone_type_name "
				+ "from daq_median_stake t "
				+ "left join daq_project p on p.oid=t.project_oid "
				+ "left join daq_pipeline l on l.oid=t.pipeline_oid "
				+ "left join sys_domain s on s.code_id=t.mark_stone_type "
				+ "where t.active=1";
		if(StringUtils.isNotBlank(medianStakeCode)){
			sql += " and t.median_stake_code like :medianStakeCode"; 
		}
		if(StringUtils.isNotBlank(projectOid)){
			sql += " and t.project_oid = :projectOid";
		}
		if(StringUtils.isNotBlank(pipelineOid)){
			sql += "and t.pipeline_oid = :pipelineOid";
		}
		sql +=" order by t.create_datetime desc";
		return sql;
	}

	public String getProjectOid() {
		return projectOid;
	}

	public void setProjectOid(String projectOid) {
		this.projectOid = projectOid;
	}

	public String getPipelineOid() {
		return pipelineOid;
	}

	public void setPipelineOid(String pipelineOid) {
		this.pipelineOid = pipelineOid;
	}

	public String getMedianStakeCode() {
		if(StringUtils.isNotBlank(medianStakeCode)){
			return "%"+medianStakeCode+"%";
		}
		return null;
	}

	public void setMedianStakeCode(String medianStakeCode) {
		this.medianStakeCode = medianStakeCode;
	}
	
}
