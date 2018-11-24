package cn.jasgroup.jasframework.acquisitiondata.weld.cutweld.controller;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.jasgroup.framework.data.result.BaseResult;
import cn.jasgroup.framework.data.result.SimpleResult;
import cn.jasgroup.jasframework.acquisitiondata.weld.cutweld.dao.CutWeldDao;
import cn.jasgroup.jasframework.acquisitiondata.weld.cutweld.service.CutWeldService;
import cn.jasgroup.jasframework.importexcel.utils.ExcelParseUtil;
import cn.jasgroup.jasframework.utils.ReadConfigUtil;

@RestController
@RequestMapping("daq/weldcut")
public class CutWeldController {

	@Autowired
	private CutWeldService cutWeldService;

	@PostMapping("importExcelData")
	public Object importExcelData(HttpServletRequest request) {
		BaseResult baseResult = null;
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(Integer.parseInt(ReadConfigUtil.getPlatformConfig("fileUpload.memorySizeThreshold")));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(Integer.parseInt(ReadConfigUtil.getPlatformConfig("fileUpload.totalFileMaxsize")));
		upload.setHeaderEncoding("UTF-8");
		try{
			// 导入方式：初始导入initial || 更新导入update
//			String importType = request.getParameter("importType");
//			if(StringUtils.isNotBlank(importType)){
//				importType = URLDecoder.decode(importType, "UTF-8");
//			}else{
//				return new SimpleResult<>(-1, "400", "导入方式为空！");
//			}
			List<?> items = upload.parseRequest(request);
			FileItem item = null;
			int itemsSize = items.size();
			for (int i = 0; i < itemsSize; i++) {
				item = (FileItem) items.get(i);
			}
			StringBuffer msgBuffer = new StringBuffer();
			if (!item.isFormField() && item.getName().length() > 0) {
				Workbook workbook = ExcelParseUtil.getWorkbook(item);
				Map<String, Object> msgMap = cutWeldService.verifyExcelData(workbook);
				//没有对应sheet页
				String getSheet = (String)msgMap.get("getSheet");
				if (StringUtils.isNotBlank(getSheet)) {
					return new SimpleResult<>(-1,"400",getSheet);
				}
				//表中数据为空
				String getData = (String)msgMap.get("getData");
				if (StringUtils.isNotBlank(getData)) {
					return new SimpleResult<>(-1,"400",getData);
				}
				//违反长度精度、有效性、唯一性和域值转换规则
				String notNull = (String)msgMap.get("notNull");
				String invalid = (String)msgMap.get("invalid");
				String notUnique = (String)msgMap.get("notUnique");
				msgBuffer.append(notNull).append(invalid).append(notUnique);
				if (StringUtils.isNotBlank(msgBuffer.toString())) {
					return new SimpleResult<>(-1,"400", msgBuffer.toString());
				}
				cutWeldService.insertDataToDB(workbook);
			}
			baseResult = new SimpleResult<>(1, "200", "导入成功！");
		} catch (Exception e) {
			e.printStackTrace();
			baseResult = new SimpleResult<>(-1, "500", "导入程序异常！");
		}
		return baseResult;
	}
	

}
