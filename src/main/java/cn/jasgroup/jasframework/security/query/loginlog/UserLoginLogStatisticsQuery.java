package cn.jasgroup.jasframework.security.query.loginlog;

import cn.jasgroup.jasframework.engine.jdbc.query.AbstractBaseQuery;
import cn.jasgroup.jasframework.support.ThreadLocalHolder;
import cn.jasgroup.jasframework.utils.ReadConfigUtil;

public class UserLoginLogStatisticsQuery extends AbstractBaseQuery{
	private String userId = ThreadLocalHolder.getCurrentUserId();
	private String database = ReadConfigUtil.getPlatformConfig("database");
	
	@Override
	public String getQuerySql(){
		
		StringBuffer buffer = new StringBuffer();
		if(database.equalsIgnoreCase("postgresql")){
			buffer.append( "select sl.*,sl2.client_ip,sl2.user_name,sl2.login_name from "
					+ "(select user_id,sum(extract(epoch from age(exit_datetime,login_datetime))) as totalsecond,"
					+ "count(oid)+1 as login_count,max(login_datetime) as lastlogindate "
					+ "from sys_login_log where user_id= :userId and exit_datetime is not null group by user_id) sl "
					+ "left join sys_login_log sl2 on sl.user_id=sl2.user_id and sl.lastlogindate = sl2.login_datetime "
					+ "and sl2.exit_datetime is not null ");
		}else{
			buffer.append( "select sl.*,sl2.client_ip,sl2.user_name,sl2.login_name from "
					+ "(select user_id,sum(exit_datetime - login_datetime) * 24 * 3600 as totalsecond,"
					+ "count(oid)+1 as login_count,max(login_datetime) as lastlogindate "
					+ "from sys_login_log where user_id= :userId and exit_datetime is not null group by user_id) sl "
					+ "left join sys_login_log sl2 on sl.user_id=sl2.user_id and sl.lastlogindate = sl2.login_datetime "
					+ "and sl2.exit_datetime is not null ");
		}
		return buffer.toString();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
