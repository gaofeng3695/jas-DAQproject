package cn.jasgroup.jasframework.acquisitiondata.scope.medianstake.query;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.scope.medianstake.service.bo.MedianStakeQueryBo;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

@QueryConfig(
	scene="/medianStake/get",
	resultClass=MedianStakeQueryBo.class
)
public class MedianStakeQuery extends BaseJavaQuery{
	
	private String oid;
	
	@Override
	public String getSql() {
		String sql = "select t.*,p.project_name,l.pipeline_name,s.code_name as mark_stone_type_name "
				+ "from daq_median_stake t "
				+ "left join daq_project p on p.oid=t.project_oid "
				+ "left join daq_pipeline l on l.oid=t.pipeline_oid "
				+ "left join sys_domain s on s.code_id=t.mark_stone_type "
				+ "where t.active=1";
		if(StringUtils.isNotBlank(oid)){
			sql +=" and t.oid = :oid";
		}
		return sql;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	
}
