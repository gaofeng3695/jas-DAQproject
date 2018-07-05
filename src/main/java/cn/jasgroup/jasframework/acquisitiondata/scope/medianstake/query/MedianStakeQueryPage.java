package cn.jasgroup.jasframework.acquisitiondata.scope.medianstake.query;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.scope.medianstake.service.bo.MedianStakeQueryBo;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

@QueryConfig(
	scene="/medianStake/getPage",
	resultClass=MedianStakeQueryBo.class
)
public class MedianStakeQueryPage extends BaseJavaQuery{
	
	/**
	 * oids: ID集合
	 */
	 
	private List<String> oids;
	
	/**
	 * modelId: B 导出
	 */
	 
	private String modelId;
	
	private String projectOid;
	private String pipelineOid;
	private String medianStakeCode;
	
	@Override
	public String getSql() {
		String sql = "select t.*,p.project_name,l.pipeline_name "
				+ "from daq_median_stake t "
				+ "left join daq_project p on p.oid=t.project_oid "
				+ "left join daq_pipeline l on l.oid=t.pipeline_oid "
				+ "where t.active=1";
		if(StringUtils.isNotBlank(modelId)){	// 导出语句
			sql = "select "
					+ "p.project_name as project_oid,"
					+ "l.pipeline_name as pipeline_oid,"
					+ "median_stake_code,mileage,pointx,pointy,pointz "
					+ "from daq_median_stake t "
					+ "left join daq_project p on p.oid=t.project_oid "
					+ "left join daq_pipeline l on l.oid=t.pipeline_oid "
					+ "where t.active=1";
		}
		if(StringUtils.isNotBlank(medianStakeCode)){
			sql += " and t.median_stake_code like :medianStakeCode"; 
		}
		if(StringUtils.isNotBlank(projectOid)){
			sql += " and t.project_oid = :projectOid";
		}
		if(StringUtils.isNotBlank(pipelineOid)){
			sql += " and t.pipeline_oid = :pipelineOid";
		}
		if(null != oids && oids.size()>0){
			sql += " and t.oid in (:oids)";
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
	
	public List<String> getOids() {
		return oids;
	}

	public void setOids(List<String> oids) {
		this.oids = oids;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

}
