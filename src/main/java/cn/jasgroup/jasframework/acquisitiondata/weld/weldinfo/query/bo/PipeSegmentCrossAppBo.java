package cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.query.bo;

import cn.jasgroup.jasframework.base.data.BaseBo;
import cn.jasgroup.jasframework.support.ThreadLocalHolder;

/**
 * 
  *<p>类描述：。</p>
  * @author 雷凯 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年9月27日 下午6:52:14。</p>
 */
public class PipeSegmentCrossAppBo extends BaseBo {
	/**
	 * oid
	 */
	private String oid;
	private String name;
	private String startStakeOid;
	private String endStakeOid;
	private String type;
	private String code;
	private String pipelineOid;
	private String userId = ThreadLocalHolder.getCurrentUserId();

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartStakeOid() {
		return startStakeOid;
	}

	public void setStartStakeOid(String startStakeOid) {
		this.startStakeOid = startStakeOid;
	}

	public String getEndStakeOid() {
		return endStakeOid;
	}

	public void setEndStakeOid(String endStakeOid) {
		this.endStakeOid = endStakeOid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPipelineOid() {
		return pipelineOid;
	}

	public void setPipelineOid(String pipelineOid) {
		this.pipelineOid = pipelineOid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
