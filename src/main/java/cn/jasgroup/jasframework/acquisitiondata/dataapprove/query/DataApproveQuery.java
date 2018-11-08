package cn.jasgroup.jasframework.acquisitiondata.dataapprove.query;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.dataapprove.service.bo.DataApproveBo;
import cn.jasgroup.jasframework.acquisitiondata.material.base.coldbending.dao.entity.ColdBendingPipe;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;
import cn.jasgroup.jasframework.support.ModelFacade;

/***
  * 
  *<p>类描述：。</p>
  * @author 雷凯 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月21日 下午5:36:36。</p>
 */
@QueryConfig(
		scene="/dataApprove/getPage",
		resultClass=DataApproveBo.class
	)
public class DataApproveQuery extends BaseJavaQuery{
	
	private String businessOid;

	@Override
	public String getQuerySql() {
		String sql ="select t.oid,t.business_oid,t.approve_opinion,t.approve_status,"
				+ "case when t.approve_status = -1 then '驳回' "
					+ "when t.approve_status = 1 then '待审核' "
					+ "when t.approve_status = 2 then '审核通过' "
					+ "else '未上报' end as approve_status_name,"
				+ "t.create_user_name,t.create_datetime from daq_data_approve t where 1=1";
		if(StringUtils.isNotBlank(businessOid)){
			sql += " and t.business_oid = :businessOid order by t.create_datetime desc";
		}else{
			sql +=" and t.business_oid='' order by t.create_datetime desc";
		}
		return sql;
	}
	
	public String getBusinessOid() {
		return businessOid;
	}

	public void setBusinessOid(String businessOid) {
		this.businessOid = businessOid;
	}
	
}
