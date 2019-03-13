package cn.jasgroup.jasframework.importexcel.support.strategy;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.sql.dialect.oracle.visitor.OracleSchemaStatVisitor;
import com.alibaba.druid.sql.dialect.postgresql.visitor.PGSchemaStatVisitor;
import com.alibaba.druid.sql.dialect.sqlserver.visitor.SQLServerSchemaStatVisitor;
import com.alibaba.druid.sql.visitor.SchemaStatVisitor;
import com.alibaba.druid.stat.TableStat.Column;
import com.alibaba.druid.util.JdbcConstants;

import cn.jasgroup.jasframework.base.data.MapQuery;
import cn.jasgroup.jasframework.dataaccess3.support.DatabaseSupport;
import cn.jasgroup.jasframework.engine.map.service.CommonDataMapService;
import cn.jasgroup.jasframework.importexcel.data.ExcelExportData;
import cn.jasgroup.jasframework.inject.sql.QuerySqlInjectStrategy;
import cn.jasgroup.jasframework.variate.VariateInjectService;

/**
 * @description 自定义sql处理策略
 * @author zhangyi
 * @date 2018年8月23日上午9:01:05
 * @version V1.0
 * @since JDK 1.80
 */

@Component
public class SqlSrategy implements QuerySqlInjectStrategy{ 

	@Autowired
	private CommonDataMapService commonDataMapService;

	@Autowired
	private VariateInjectService variateInjectService;
	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public static SqlSrategy sqlSrategy;

	@PostConstruct
	public void init() {
		sqlSrategy = this;
		sqlSrategy.variateInjectService = this.variateInjectService;
		sqlSrategy.commonDataMapService = this.commonDataMapService;
		sqlSrategy.jdbcTemplate = this.jdbcTemplate;
	}

	/**
	 * <p>功能描述：获取导出数据。</p>
	 * <p>张毅 </p>	
	 * @param excelExportData	导出配置类
	 * @param paramMap	查询条件
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2018年8月23日 上午10:10:42。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public static List<?> getList(ExcelExportData excelExportData, Map<String, Object> paramMap) {
		List<?> dataList = null;
		try{
			String dataBaseType = DatabaseSupport.getDatabaseType(sqlSrategy.jdbcTemplate.getDataSource());
			String dbType=null;
			SchemaStatVisitor visitor= null;
			String tableName= "test_temp";
			if(dataBaseType.equalsIgnoreCase("mysql")){
				dbType = JdbcConstants.MYSQL;
			}else if(dataBaseType.equalsIgnoreCase("oracle")){
				dbType = JdbcConstants.ORACLE;
			}else if(dataBaseType.equalsIgnoreCase("sqlserver")){
				dbType = JdbcConstants.SQL_SERVER;
			}else{
				dbType = JdbcConstants.POSTGRESQL;
			}
			String querySql = excelExportData.getDataSource();
			if (StringUtils.isNotBlank(querySql)) {
				List<String> paramList = excelExportData.getParamList();
				if (paramList.size() > 0) {
					for (String paramSql : paramList) {
						if(paramSql.toLowerCase().indexOf("order by")>-1){
							continue;
						}
						String aliasName = paramSql.substring(0, paramSql.indexOf(".")).replace("and ", "");
						String sql = "select * from " + tableName + " "+aliasName+"  where 1=1 " + paramSql;
						List<SQLStatement> statementList = SQLUtils.parseStatements(sql, dbType);
						for (SQLStatement statement : statementList) {
							if(dataBaseType.equalsIgnoreCase("mysql")){
								visitor =new MySqlSchemaStatVisitor();
							}else if(dataBaseType.equalsIgnoreCase("oracle")){
								visitor = new OracleSchemaStatVisitor();
							}else if(dataBaseType.equalsIgnoreCase("postgresql")){
								visitor =new PGSchemaStatVisitor();
							}else if(dataBaseType.equalsIgnoreCase("sqlserver")){
								visitor = new SQLServerSchemaStatVisitor();
							}else{
								visitor =new PGSchemaStatVisitor();
							}
							statement.accept(visitor);
							Set<Entry<String, Object>> entrySet = paramMap.entrySet();
							for (Entry<String, Object> entry : entrySet) {
								Object value = entry.getValue();
								if(value!=null && !value.equals("")){
									String columnName = underline(entry.getKey());
									Column column = visitor.addColumn(tableName, columnName);
									if (column != null && column.isWhere()) {
										querySql += " " + paramSql;
									}
								}
							}
							visitor=null;
						}
					}
				}
				MapQuery mapQuery = new MapQuery();
				mapQuery.setSql(querySql);
				mapQuery.setSqlStrategyCode("exportSql");
				mapQuery.setValueMap(paramMap);
				mapQuery.setModelId(excelExportData.getTemplateCode());
				dataList = sqlSrategy.commonDataMapService.getList(mapQuery);

			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}

	@Override
	public String getCode() {
		return "exportSql";
	}

	@Override
	public void injectSql(MapQuery query) {
		sqlSrategy.variateInjectService.injectValueForQuery(query);
	}
	/**
	 * 驼峰转下划线
	 * @param string
	 * @return
	 */
	public static String underline(String string) {
		Pattern pattern = Pattern.compile("[A-Z]");
		Matcher matcher = pattern.matcher(string);
		StringBuffer sb = new StringBuffer(string);
		if(matcher.find()) {
			sb = new StringBuffer();
			//将当前匹配子串替换为指定字符串，并且将替换后的子串以及其之前到上次匹配子串之后的字符串段添加到一个StringBuffer对象里。
			//正则之前的字符和被替换的字符
			matcher.appendReplacement(sb,"_"+matcher.group(0).toLowerCase());
			//把之后的也添加到StringBuffer对象里
			matcher.appendTail(sb);			
		}else {
			return sb.toString();
		}	
		return underline(sb.toString());
	}
}
