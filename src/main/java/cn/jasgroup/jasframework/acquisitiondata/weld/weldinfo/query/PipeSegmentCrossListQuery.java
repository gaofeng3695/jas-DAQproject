package cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.query;


import cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.query.bo.PipeSegmentCrossAppBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

@QueryConfig(
	scene = "/pipeSegmentCross/getList", 
	resultClass = PipeSegmentCrossAppBo.class, 
	queryBeforeProcess = {
		@Process(service = "daqInjectService", method = "injectDataAuthoritySql(dataAuthoritySql)") 
	}
)
public class PipeSegmentCrossListQuery extends BaseJavaQuery {
	
	private String lastDataTime;

	@Override
	public String getQuerySql() {
		String sql = "select * from v_daq_pipe_segment_cross t";
		if(lastDataTime!=null){
			
		}
		return sql;
	}

	public String getLastDataTime() {
		return lastDataTime;
	}

	public void setLastDataTime(String lastDataTime) {
		this.lastDataTime = lastDataTime;
	}
	
}
