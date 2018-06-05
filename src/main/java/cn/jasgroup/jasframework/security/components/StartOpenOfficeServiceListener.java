package cn.jasgroup.jasframework.security.components;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jasgroup.jasframework.utils.ReadConfigUtil;
import cn.jasgroup.jasframework.utils.file.FileConvertUtil;

/***
  *<p>类描述：openOffice服务进行开启和关闭。</p>
  * @author 雷凯 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年5月31日 下午4:04:25。</p>
 */
public class StartOpenOfficeServiceListener implements ServletContextListener{

	private static final Logger logger = LoggerFactory.getLogger(StartOpenOfficeServiceListener.class);
	private static String convertTool = ReadConfigUtil.getPlatformConfig("filePreview.convertTool"); // 文档转换成PDF使用的工具
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try{
			if(!convertTool.equalsIgnoreCase("jacob")){
				FileConvertUtil.startService();
			}
		} catch(Exception e) {
			logger.error("OpenOffice服务启动失败，请确认OpenOffice已正确安装!");
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		try {
			if(!convertTool.equalsIgnoreCase("jacob")){
				FileConvertUtil.stopService();
			}
		} catch(Exception e) {
			logger.error("OpenOffice服务关闭失败！");
		}
	}

}
