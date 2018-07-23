package cn.jasgroup.jasframework.acquisitiondata.material.appendages.electroniclabel.query;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.material.appendages.electroniclabel.query.bo.DaqAppendagesElectronicLabelBo;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;
import cn.jasgroup.jasframework.base.annotation.Process;

/**
 * @description 电子标签
 * @author zhangyi
 * @date 2018年7月21日上午11:25:11
 * @version V1.0
 * @since JDK 1.80
 */

@QueryConfig(
	scene = "/appendagesElectronicLabel/getPage",
	resultClass = DaqAppendagesElectronicLabelBo.class,
	queryBeforeProcess = {
		@Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
	}
)
public class DaqAppendagesElectronicLabelQuery extends BaseJavaQuery {

	private List<String> oids;
	private String projectOid;
	private String pipelineOid;
	private String tendersOid;
	private String pipeSegmentOrCrossOid;
	private String electronicLabelCode;
	private String productNum;
	private String medianStakeOid;
	
	@Override
	public String getSql() {
		String sql =  "select t.*,"
				+ " d1.code_name as featurePointTypeName,"
				+ "	p.project_name,"
				+ "	l.pipeline_name,"
				+ "	dt.tenders_name,"
				+ "	v.name as pipeSegmentOrCrossName,"
				+ "	ms.median_stake_code, "
				+ "	u1.unit_name as constructUnitName,"
				+ "	u2.unit_name as supervisionUnitName"				
				+ " from daq_appendages_electronic_label t "
				+ " left join sys_domain d1 on d1.code_id = t.feature_point_type"				
				+ " left join daq_project p on p.oid=t.project_oid "
				+ " left join daq_pipeline l on l.oid=t.pipeline_oid "
				+ " left join daq_tenders dt on dt.oid=t.tenders_oid "
				+ " left join (select m.oid,m.median_stake_code from daq_median_stake m where active=1) ms"
				+ " on ms.oid=t.median_stake_oid "
				+ " left join v_daq_pipe_segment_cross v on v.oid=t.pipe_segment_or_cross_oid "
				+ " left join pri_unit u1 on u1.oid=t.construct_unit "
				+ " left join pri_unit u2 on u2.oid=t.supervision_unit "				
				+ " where t.active = 1";
		if(StringUtils.isNotBlank(projectOid)){
			sql += " and t.project_oid = :projectOid ";
		}
		if(StringUtils.isNotBlank(pipelineOid)){
			sql += " and t.pipeline_oid = :pipelineOid ";
		}
		if(StringUtils.isNotBlank(tendersOid)){
			sql += " and t.tenders_oid = :tendersOid ";
		}
		if(StringUtils.isNotBlank(pipeSegmentOrCrossOid)){
			sql += " and t.pipe_segment_or_cross_oid = :pipeSegmentOrCrossOid ";
		}
		if(StringUtils.isNotBlank(electronicLabelCode)){
			sql += " and t.electronic_label_code like :electronicLabelCode ";
		}
		if(StringUtils.isNotBlank(productNum)){
			sql += " and t.product_num like :productNum ";
		}
		if(StringUtils.isNotBlank(medianStakeOid)){
			sql += " and t.median_stake_oid = :medianStakeOid ";
		}
		if (null != oids && oids.size() > 0) {
			sql += " and oid in (:oids) ";
		}
		sql +=" order by t.create_datetime desc";
		return sql;
	}

	public List<String> getOids() {
		return oids;
	}

	public void setOids(List<String> oids) {
		this.oids = oids;
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

	public String getTendersOid() {
		return tendersOid;
	}

	public void setTendersOid(String tendersOid) {
		this.tendersOid = tendersOid;
	}

	public String getPipeSegmentOrCrossOid() {
		return pipeSegmentOrCrossOid;
	}

	public void setPipeSegmentOrCrossOid(String pipeSegmentOrCrossOid) {
		this.pipeSegmentOrCrossOid = pipeSegmentOrCrossOid;
	}

	public String getMedianStakeOid() {
		return medianStakeOid;
	}

	public void setMedianStakeOid(String medianStakeOid) {
		this.medianStakeOid = medianStakeOid;
	}

	public String getElectronicLabelCode() {
		if(StringUtils.isNotBlank(electronicLabelCode)){
			return "%"+electronicLabelCode+"%";
		}
		return electronicLabelCode;
	}

	public void setElectronicLabelCode(String electronicLabelCode) {
		this.electronicLabelCode = electronicLabelCode;
	}

	public String getProductNum() {
		if(StringUtils.isNotBlank(productNum)){
			return "%"+productNum+"%";
		}
		return productNum;
	}

	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}
	
	
}